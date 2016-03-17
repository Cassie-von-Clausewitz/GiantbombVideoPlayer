package com.kyleriedemann.giantbombvideoplayer.Video;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyleriedemann.giantbombvideoplayer.Base.RxCallback;
import com.kyleriedemann.giantbombvideoplayer.Base.RxSubscriber;
import com.kyleriedemann.giantbombvideoplayer.Models.Result;
import com.kyleriedemann.giantbombvideoplayer.Models.Video;
import com.kyleriedemann.giantbombvideoplayer.Network.GiantbombApiClient;
import com.kyleriedemann.giantbombvideoplayer.Network.ServiceGenerator;
import com.kyleriedemann.giantbombvideoplayer.R;
import com.kyleriedemann.giantbombvideoplayer.UI.BaseFragment;
import com.kyleriedemann.giantbombvideoplayer.UI.VideoCard;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by kyle on 3/31/15.
 */
public class VideoListFragment extends BaseFragment implements RxCallback<Result> {

    @Bind(R.id.videolist)
    CardRecyclerView videoList;

    CardArrayRecyclerViewAdapter videoAdapter;
    List<Card> videoCards;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.video_list_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        videoList = ButterKnife.findById(view, R.id.videolist);

        videoList.setHasFixedSize(true);
        videoList.setLayoutManager(new LinearLayoutManager(getActivity()));

        GiantbombApiClient client = ServiceGenerator.createService(GiantbombApiClient.class);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String apiKey = sharedPref.getString("API_KEY", "No Saved API Key");

        mCompositeSubscription.add(client.getVideos(apiKey, "json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<>(this)));
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.video_list_fragment_layout;
    }

    @Override
    public void onDataReady(Result data) {
        Timber.d("Got " + data.getResults().size() + " results");

        videoCards = new ArrayList<Card>();
        for (Video v : data.getResults()){
            videoCards.add(new VideoCard(getActivity(), v));
        }

        videoAdapter = new CardArrayRecyclerViewAdapter(getActivity(), videoCards);
        videoList.setAdapter(videoAdapter);
        videoAdapter.notifyDataSetChanged();
        videoList.refreshDrawableState();
        videoList.invalidate();
    }

    @Override
    public void onDataError(Throwable e) {
        Timber.e(e.getClass() + " " + e.getMessage());

        if (e.getCause() != null)
            Timber.e(e.getCause().getClass() + " " + e.getCause().getMessage());
    }
}

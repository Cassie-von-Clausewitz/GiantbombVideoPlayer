package com.kyleriedemann.giantbombvideoplayer.VideoList;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyleriedemann.giantbombvideoplayer.Base.BaseFragment;
import com.kyleriedemann.giantbombvideoplayer.GiantbombApp;
import com.kyleriedemann.giantbombvideoplayer.Models.Result;
import com.kyleriedemann.giantbombvideoplayer.Network.GiantbombApiClient;
import com.kyleriedemann.giantbombvideoplayer.Network.ServiceGenerator;
import com.kyleriedemann.giantbombvideoplayer.R;
import com.kyleriedemann.giantbombvideoplayer.Utils.PrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VideoListFragment extends BaseFragment {

    @BindView(R.id.videolist)
    RecyclerView videoList;

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

        String apiKey = PrefManager.with(GiantbombApp.instance()).getString("API_KEY", "No Saved API Key");

        disposables.add(client.getVideos(apiKey, "json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onDataReady, this::onDataError));
    }

    public void onDataReady(Result data) {
//        Timber.d("Got " + data.getResults().size() + " results");
//
//        videoCards = new ArrayList<Card>();
//        for (Video v : data.getResults()){
//            videoCards.add(new VideoCard(getActivity(), v));
//        }
//
//        videoAdapter = new CardArrayRecyclerViewAdapter(getActivity(), videoCards);
//        videoList.setAdapter(videoAdapter);
//        videoAdapter.notifyDataSetChanged();
//        videoList.refreshDrawableState();
//        videoList.invalidate();
    }

    public void onDataError(Throwable e) {
//        Timber.e(e.getClass() + " " + e.getMessage());
//
//        if (e.getCause() != null)
//            Timber.e(e.getCause().getClass() + " " + e.getCause().getMessage());
    }
}

package com.kyleriedemann.giantbombvideoplayer.Video;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyleriedemann.giantbombvideoplayer.Models.Result;
import com.kyleriedemann.giantbombvideoplayer.Models.Video;
import com.kyleriedemann.giantbombvideoplayer.Network.GiantbombApiClient;
import com.kyleriedemann.giantbombvideoplayer.R;
import com.kyleriedemann.giantbombvideoplayer.UI.DefaultFragment;
import com.kyleriedemann.giantbombvideoplayer.UI.VideoCard;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;
import retrofit.RestAdapter;

/**
 * Created by kyle on 3/31/15.
 */
public class VideoListFragment extends DefaultFragment {

    @InjectView(R.id.videolist)
    CardRecyclerView videoList;

    CardArrayRecyclerViewAdapter mVideoAdapter;
    List<Card> videoCards;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.video_list_fragment_layout, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.inject(this, view);
        videoList.setHasFixedSize(true);
        videoList.setLayoutManager(new LinearLayoutManager(getActivity()));

        AsyncTest asyncTest = new AsyncTest();
        asyncTest.execute();


//        testRequest();
    }


    public class AsyncTest extends AsyncTask<Void, Void, Boolean> {

        private final String LOG_TAG = AsyncTest.class.getSimpleName();

        @Override
        protected Boolean doInBackground(Void... params) {
            RestAdapter restAdapter = GiantbombApiClient.buildRestAdapter(false);
            GiantbombApiClient.Videos giantbombApiClient = restAdapter.create(GiantbombApiClient.Videos.class);

            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            String apiKey = sharedPref.getString("API_KEY", "No Saved API Key");

            Result videos = giantbombApiClient.getVideos(apiKey, "json");

            videoCards = new ArrayList<Card>();
            for (Video v : videos.getResults()){
//                Log.d(VideoListFragment.class.getSimpleName(), v.toString());
                videoCards.add(new VideoCard(getActivity(), v));
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if(aBoolean) {
                mVideoAdapter = new CardArrayRecyclerViewAdapter(getActivity(), videoCards);
                videoList.setAdapter(mVideoAdapter);
                mVideoAdapter.notifyDataSetChanged();
                videoList.refreshDrawableState();
                videoList.invalidate();
            }
        }
    }

//    private void testRequest() {
//    RestAdapter restAdapter = GiantbombApiClient.buildRestAdapter(true);
//    GiantbombApiClient.Videos giantbombApiClient = restAdapter.create(GiantbombApiClient.Videos.class);
//
//        giantbombApiClient.getVideos("9370044ca3820c6695420ea259a2c484730ff5a2", "json", new Callback<List<Video>>() {
//            @Override
//            public void success(List<Video> videos, Response response) {
//                videoCards = new ArrayList<Card>();
//                for (Video v : videos){
//                    Log.d(VideoListFragment.class.getSimpleName(), v.toString());
//                    videoCards.add(new VideoCard(getActivity(), v));
//                }
//                mVideoAdapter = new CardArrayRecyclerViewAdapter(getActivity(), videoCards);
//                videoList.setAdapter(mVideoAdapter);
//                mVideoAdapter.notifyDataSetChanged();
//                videoList.refreshDrawableState();
//                videoList.invalidate();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.d(VideoListFragment.class.getSimpleName(), error.getKind().toString());
//            }
//        });
//    }

}

package com.kyleriedemann.giantbombvideoplayer.VideoList;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyleriedemann.giantbombvideoplayer.Models.Video;
import com.kyleriedemann.giantbombvideoplayer.Network.Connectivity;
import com.kyleriedemann.giantbombvideoplayer.R;
import com.kyleriedemann.giantbombvideoplayer.VideoPlayer.VideoViewActivity;
import com.kyleriedemann.giantbombvideoplayer.VideoPlayer.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoCard extends CardView {

    Video v;
    @BindView(R.id.video_thumb)
    ImageView videoThumb;
    @BindView(R.id.video_name)
    TextView videoName;
    @BindView(R.id.deck)
    TextView deck;

    public VideoCard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context, attrs);
    }

    public VideoCard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateLayout(context, attrs);
    }

    public void inflateLayout(Context context, AttributeSet attrs) {
        inflate(context, R.layout.video_card, this);
        ButterKnife.bind(this);
    }

    public void setupInnerViewElements(ViewGroup parent, View view) {

        videoThumb = ButterKnife.findById(view, R.id.video_thumb);
        videoName = ButterKnife.findById(view, R.id.video_name);
        deck = ButterKnife.findById(view, R.id.deck);

        if(v != null) {
            videoName.setText(v.getName());

            String deckStr = v.getDeck();
            if(deckStr.length() > 100) {
                deckStr = deckStr.substring(0, 96) + "...";
            }
            deck.setText(deckStr);

            String url = v.getVideoImage().getThumbImageUrl().replaceAll(" ", "%20").replaceAll(".png", ".jpg");
            Log.d("THUMB URL", url);
//            Picasso.with(getContext())
//                    .load(url)
//                    .error(R.drawable.ic_launcher)
//                    .into(videoThumb);

        }
    }

    public void onClick(View view) {
        Intent i = new Intent(getContext(), VideoViewActivity.class);
//        Intent i = new Intent(getContext(), WebViewActivity.class);
        if(Connectivity.isConnectedFast(getContext())) {
            i.putExtra("url", v.getHighUrl());
        } else {
            i.putExtra("url", v.getLowUrl());
        }

        getContext().startActivity(i);
    }

    public boolean onLongClick(View view) {
//        Intent i = new Intent(getContext(), VideoViewActivity.class);
        Intent i = new Intent(getContext(), WebViewActivity.class);
        if(Connectivity.isConnectedFast(getContext())) {
            i.putExtra("url", v.getHighUrl());
        } else {
            i.putExtra("url", v.getLowUrl());
        }

        getContext().startActivity(i);

        return true;
    }
}

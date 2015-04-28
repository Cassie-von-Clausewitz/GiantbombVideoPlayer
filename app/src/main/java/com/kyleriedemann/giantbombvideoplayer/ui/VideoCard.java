package com.kyleriedemann.giantbombvideoplayer.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyleriedemann.giantbombvideoplayer.Models.Video;
import com.kyleriedemann.giantbombvideoplayer.Network.Connectivity;
import com.kyleriedemann.giantbombvideoplayer.R;
import com.kyleriedemann.giantbombvideoplayer.VideoViewActivity;
import com.kyleriedemann.giantbombvideoplayer.WebViewActivity;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by kyle on 4/21/15.
 */
public class VideoCard extends Card implements Card.OnCardClickListener, Card.OnLongCardClickListener {

    Video v;
    @InjectView(R.id.video_thumb)
    ImageView videoThumb;
    @InjectView(R.id.video_name)
    TextView videoName;
    @InjectView(R.id.deck)
    TextView deck;

    public VideoCard(Context context, Video v) {
        super(context, R.layout.video_card);
        this.v = v;
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);
        ButterKnife.inject(this, view);
        if(v != null) {
            videoName.setText(v.getName());

            String deckStr = v.getDeck();
            if(deckStr.length() > 100) {
                deckStr = deckStr.substring(0, 96) + "...";
            }
            deck.setText(deckStr);

            String url = v.getVideoImage().getThumbImageUrl().replaceAll(" ", "%20").replaceAll(".png", ".jpg");
            Log.d("THUMB URL", url);
            Picasso.with(getContext())
                    .load(url)
                    .error(R.drawable.ic_launcher)
                    .into(videoThumb);

        }
    }

    @Override
    public void onClick(Card card, View view) {
        Intent i = new Intent(getContext(), VideoViewActivity.class);
//        Intent i = new Intent(getContext(), WebViewActivity.class);
        if(Connectivity.isConnectedFast(getContext())) {
            i.putExtra("url", v.getHighUrl());
        } else {
            i.putExtra("url", v.getLowUrl());
        }

        getContext().startActivity(i);
    }

    @Override
    public boolean onLongClick(Card card, View view) {
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

package com.ryanwahle.videoanalyzer;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;


public class PlayerActivity extends Activity implements MediaPlayer.OnPreparedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Intent intent = getIntent();
        Uri videoUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        Log.v("videoURI: ", "" + videoUri);

        VideoView playerVideoView = (VideoView) findViewById(R.id.playerVideoView);
        MediaController playerMediaController = new MediaController(this);

        playerVideoView.setVideoURI(videoUri);
        playerVideoView.setMediaController(playerMediaController);
        playerVideoView.setOnPreparedListener(this);

        playerVideoView.start();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.v("MEDIA: ", "READY");

        TextView textViewTotalDuration = (TextView) findViewById(R.id.textViewTotalDuration);
        TextView textViewVideoWidth = (TextView) findViewById(R.id.textViewVideoWidth);
        TextView textViewVideoHeight = (TextView) findViewById(R.id.textViewVideoHeight);

        Integer totalDuration = mediaPlayer.getDuration();
        Integer videoWidth = mediaPlayer.getVideoWidth();
        Integer videoHeight = mediaPlayer.getVideoHeight();

        textViewTotalDuration.setText(totalDuration.toString() + " ms");
        textViewVideoWidth.setText(videoWidth.toString() + " px");
        textViewVideoHeight.setText(videoHeight.toString() + " px");
    }
}

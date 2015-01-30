/*
 * Copyright (C) 2014 Sony Mobile Communications Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.sonymobile.android.media.testmediaplayer.GUI;

import com.sonymobile.android.media.MediaPlayer;

import android.os.Handler;
import android.widget.SeekBar;

public class SeekbarUpdater {

    private SeekBar mSeekbar;
    private Handler mHandler;

    private Runnable mSeekbarUpdateRunner;
    private MediaPlayer mMediaPlayer;

    public SeekbarUpdater(SeekBar seekbar, MediaPlayer mp){
        mSeekbar = seekbar;
        mMediaPlayer = mp;
        mHandler = new Handler();
        mSeekbarUpdateRunner = new Runnable() {

            @Override
            public void run() {
                updateSeekbar();

            }

        };

    }

    public void updateSeekbar(){
        int currentPosition = mMediaPlayer.getCurrentPosition();
        if (currentPosition > 0) {
            int position = (int)(((double)currentPosition / mMediaPlayer.getDuration()) * 1000);
            mSeekbar.setProgress(position);
        }
        mHandler.postDelayed(mSeekbarUpdateRunner, 100);
    }

    public void activate(){
        mHandler.postDelayed(mSeekbarUpdateRunner, 100);
    }

    public void deactivate(){
        mHandler.removeCallbacks(mSeekbarUpdateRunner);
    }

    public void setMediaPlayer(MediaPlayer mp) {
        mMediaPlayer = mp;
    }

}

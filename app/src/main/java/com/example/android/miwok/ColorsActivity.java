/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final AudioManager.OnAudioFocusChangeListener audioListener = new AudioManager.OnAudioFocusChangeListener(){
            @Override
            public void onAudioFocusChange(int focusChange) {
                if (mp == null) return;
                if (focusChange == AudioManager.AUDIOFOCUS_GAIN) mp.start();
                else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    mp.release();
                    audioManager.abandonAudioFocus(this);
                }
                else {
                    mp.pause();
                    mp.seekTo(0);
                }
            }
        };



        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word(getString(R.string.red_default), getString(R.string.red_miwok), R.drawable.color_red, R.raw.color_red));
        words.add(new Word(getString(R.string.green_default), getString(R.string.green_miwok), R.drawable.color_green, R.raw.color_green));
        words.add(new Word(getString(R.string.brown_default), getString(R.string.brown_miwok), R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word(getString(R.string.gray_default), getString(R.string.gray_miwok), R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word(getString(R.string.black_default), getString(R.string.black_miwok), R.drawable.color_black, R.raw.color_black));
        words.add(new Word(getString(R.string.white_default), getString(R.string.white_miwok), R.drawable.color_white, R.raw.color_white));
        words.add(new Word(getString(R.string.dusty_yellow_default), getString(R.string.dusty_yellow_miwok), R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word(getString(R.string.mustard_yellow_default), getString(R.string.nustard_yellow_miwok), R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.word_list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mp != null) mp.release();
                mp = MediaPlayer.create(ColorsActivity.this, words.get(position).getAudioId());
                if (audioManager.requestAudioFocus(audioListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mp.start();
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            mp = null;
                            audioManager.abandonAudioFocus(audioListener);
                        }
                    });
                }
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mp != null) {mp.release(); mp = null;}
    }
}

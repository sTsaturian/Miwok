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

public class PhrasesActivity extends AppCompatActivity {

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

        words.add(new Word(getString(R.string.where_going_default), getString(R.string.where_going_miwok), R.raw.phrase_where_are_you_going));
        words.add(new Word(getString(R.string.what_name_default), getString(R.string.what_name_miwok), R.raw.phrase_what_is_your_name));
        words.add(new Word(getString(R.string.my_name_default), getString(R.string.my_name_miwok), R.raw.phrase_my_name_is));
        words.add(new Word(getString(R.string.how_feeling_default), getString(R.string.how_feeling_miwok), R.raw.phrase_how_are_you_feeling));
        words.add(new Word(getString(R.string.feeling_good_default), getString(R.string.feeling_good_miwok), R.raw.phrase_im_feeling_good));
        words.add(new Word(getString(R.string.are_coming_default), getString(R.string.are_coming_miwok), R.raw.phrase_are_you_coming));
        words.add(new Word(getString(R.string.yes_coming_default), getString(R.string.yes_coming_miwok), R.raw.phrase_yes_im_coming));
        words.add(new Word(getString(R.string.coming_default), getString(R.string.coming_miwok), R.raw.phrase_im_coming));
        words.add(new Word(getString(R.string.lets_go_default), getString(R.string.lets_go_miwok), R.raw.phrase_lets_go));
        words.add(new Word(getString(R.string.come_here__default), getString(R.string.come_here_miwok), R.raw.phrase_come_here));

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.word_list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mp != null) mp.release();
                mp = MediaPlayer.create(PhrasesActivity.this, words.get(position).getAudioId());
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

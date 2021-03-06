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

public class FamilyActivity extends AppCompatActivity {

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

        words.add(new Word(getString(R.string.father_default), getString(R.string.father_miwok), R.drawable.family_father, R.raw.family_father));
        words.add(new Word(getString(R.string.mother_default), getString(R.string.mother_miwok), R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word(getString(R.string.son_default), getString(R.string.son_miwok), R.drawable.family_son, R.raw.family_son));
        words.add(new Word(getString(R.string.daughter_default), getString(R.string.daughter_miwok), R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word(getString(R.string.older_brother_default), getString(R.string.older_brother_miwok), R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word(getString(R.string.younger_brother_default), getString(R.string.younger_brother_miwok), R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word(getString(R.string.older_sister_default), getString(R.string.older_sister_miwok), R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word(getString(R.string.younger_sister_default), getString(R.string.younger_sister_miwok), R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word(getString(R.string.grandmother_default), getString(R.string.grandmother_miwok), R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word(getString(R.string.grandfather_default), getString(R.string.grandfather_miwok), R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.word_list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mp != null) mp.release();
                mp = MediaPlayer.create(FamilyActivity.this, words.get(position).getAudioId());
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

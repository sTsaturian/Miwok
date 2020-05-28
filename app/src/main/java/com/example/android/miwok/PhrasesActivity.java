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

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word(getString(R.string.where_going_default), getString(R.string.where_going_miwok)));
        words.add(new Word(getString(R.string.what_name_default), getString(R.string.what_name_miwok)));
        words.add(new Word(getString(R.string.my_name_default), getString(R.string.my_name_miwok)));
        words.add(new Word(getString(R.string.how_feeling_default), getString(R.string.how_feeling_miwok)));
        words.add(new Word(getString(R.string.feeling_good_default), getString(R.string.feeling_good_miwok)));
        words.add(new Word(getString(R.string.are_coming_default), getString(R.string.are_coming_miwok)));
        words.add(new Word(getString(R.string.yes_coming_default), getString(R.string.yes_coming_miwok)));
        words.add(new Word(getString(R.string.coming_default), getString(R.string.coming_miwok)));
        words.add(new Word(getString(R.string.lets_go_default), getString(R.string.lets_go_miwok)));
        words.add(new Word(getString(R.string.come_here__default), getString(R.string.come_here_miwok)));

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.word_list);
        listView.setAdapter(itemsAdapter);
    }
}

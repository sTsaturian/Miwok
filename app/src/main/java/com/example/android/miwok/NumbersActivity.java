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
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word(getString(R.string._1_default), getString(R.string._1_miwok), R.drawable.number_one));
        words.add(new Word(getString(R.string._2_default), getString(R.string._2_miwok), R.drawable.number_two));
        words.add(new Word(getString(R.string._3_default), getString(R.string._3_miwok), R.drawable.number_three));
        words.add(new Word(getString(R.string._4_default), getString(R.string._4_miwok), R.drawable.number_four));
        words.add(new Word(getString(R.string._5_default), getString(R.string._5_miwok), R.drawable.number_five));
        words.add(new Word(getString(R.string._6_default), getString(R.string._6_miwok), R.drawable.number_six));
        words.add(new Word(getString(R.string._7_default), getString(R.string._7_miwok), R.drawable.number_seven));
        words.add(new Word(getString(R.string._8_default), getString(R.string._8_miwok), R.drawable.number_eight));
        words.add(new Word(getString(R.string._9_default), getString(R.string._9_miwok), R.drawable.number_nine));
        words.add(new Word(getString(R.string._10_default), getString(R.string._10_miwok), R.drawable.number_ten));

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.word_list);
        listView.setAdapter(itemsAdapter);
    }
}

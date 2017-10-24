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

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        final ArrayList<Word> words = new ArrayList<Word>();

        // the input into the words list is a new Word object which is created in line by using the new keyword
        words.add(new Word("Where are you going?", "Dove stai andando?", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name", "Come ti chiami?", R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "Mi chiamo...", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "Come stai? or Come ti senti?", R.raw.phrase_what_is_your_name));
        words.add(new Word("I'm feeling good.", "Bene. or Mi sento bene.", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "Stai venendo?", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I'm coming.", "Si, sto venendo.", R.raw.phrase_yes_im_coming));
        words.add(new Word("I'm coming.", "sto venendo.", R.raw.phrase_im_coming));
        words.add(new Word("Let's go.", "andiamo", R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "vieni qua.", R.raw.phrase_come_here));


        /**
         *
         *  Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
         *  adapter knows how to create layouts for each item in the list, using the
         *  simple_list_item_1.xml layout resource defined in the Android framework.
         *  This list item layout contains a single {@link TextView}, which the adapter will set to
         *  display a single word.
         *
         *  I'm also passing in an argument:
         *  1. Where (this) is the Context which refers to the NumbersActivity.java class
         *  2. This refers to the simple_list_item_1.xml layout file
         *  3. (words) are the list of Objects, which is also known as the data source for the {@link ArrayAdapter}
         *
         */
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Checking word that was clicked on; and storing that word in a variable called (word).
                // Now that I know which word was clicked on and storing it into a Variable; I can then
                // get the AudioResourceId of it and then  pass that into the MediaPlayer
                Word word = words.get(position);
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());
                mMediaPlayer.start(); // no need to call prepare(); create() does that for you
//                Toast.makeText(NumbersActivity.this, "List Items Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

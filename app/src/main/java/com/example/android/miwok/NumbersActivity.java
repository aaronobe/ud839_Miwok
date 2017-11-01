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

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mMediaPlayer;

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.
                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0); // start at the beginning
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    /**
     * **Global**_**This is a Single Instance***
     * <p>
     * This listener gets triggered when the {@Link MediaPlayer} has completed playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
//          Toast.makeText(NumbersActivity. this, "Finished ", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        //Create and setup the {@Link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        // the input into the words list is a new Word object which is created in line by using the new keyword
        words.add(new Word("one", "uno", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "two", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tre", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "quattro", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "cinque", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "sei", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "sette", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "otto", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "nove", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "dieci", R.drawable.number_ten, R.raw.number_ten));

        //        Log.v("NumbersActivity", "Current word: " + words.toString()); //****Look at the toString() method in the Word Class****


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
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

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

                // Release the media player if it currently exists because we are about play a different sound
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                // If the result is equal to audio focus request granted
                // Then we've successfully gained audio focus, and can
                // proceed with playing audio on the current app.
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // we have AF so we start media player, and set up the {@Link mCompletionListener}
                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start(); // no need to call prepare(); create() does that for you
//                Toast.makeText(NumbersActivity.this, "List Items Clicked", Toast.LENGTH_SHORT).show();

                    // Setup a listener on the media player, so that we can stop and
                    // release the media player once the sound is done playing
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop(); //This will execute instructions within the onStop method of the CurrentActivity
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;


            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks. // Abandon audio focus when playback complete
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}

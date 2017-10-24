package com.example.android.miwok;

import android.media.MediaPlayer;

/**
 * Created by Aaron on 16/10/2017.
 *
 * {@Link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a miwok translation for that word.
 */

public class Word {

    /** -------------> STATES or ATTRIBUTES <------------- */

    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mMiwokTranslation;

    /**
     * Images resource ID for the word. Setting the image variable to start at the no image CONSTANT by default.
     *
     */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /**
     * This is a CONSTANT that represents the no image state.
     * This is set to -1 because it's out of the range of all the possible valid resource ID
     *
     */
    private static final int NO_IMAGE_PROVIDED = -1;

    /** Audio resource ID for the Word */
    private int mAudioResourceId;


    /** -------------> CONSTRUCTORS <------------- */

    /**
     * This Constructor will serve the Phrases Class
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with (such as English).
     * @param miwokTranslation is the word in the Italian language.
     * @param audioResourceId is resource ID for the audio file associated with this word.
     */
    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.mAudioResourceId = audioResourceId;
    }

    /**
     * This Constructor will serve the Number, Colors, and Family Classes
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with (such as English).
     * @param miwokTranslation is the word in the Italian language.
     * @param imageResourceId is the drawable resource ID for the image associated with the word.
     * @param audioResourceId is resource ID for the audio file associated with this word.
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audioResourceId){
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.mImageResourceId = imageResourceId;
        this.mAudioResourceId = audioResourceId;
    }


    /** -------------> METHODS <------------- */

    /**
     * Get the default translation of the word.
     * @return a String mDefaultTranslation of the word.
     */
    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the miwokTranslation of the word.
     * @return a String mMiwokTranslation.
     */
    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    /**
     *
     * @return an Integer ID to store images
     */
    public int getmImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this word.
     *
     * Checking whether the image variable is equal or not equal to the image provided CONSTANT.
     * Therefore, if the image resource variable is not equal to negative one (-1), then there is
     * a valid image, and the method should return TRUE.
     *
     * However, if the image variable is equal to negative one (-1), then there is no image for
     * this word and the method should return FALSE.
     *
     * @return the image resource ID of the word.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     *
     *
     * @return the audio resource ID of the word.
     */
    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}

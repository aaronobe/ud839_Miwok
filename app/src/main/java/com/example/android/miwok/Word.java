package com.example.android.miwok;

/**
 * Created by Aaron on 16/10/2017.
 *
 * {@Link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a miwok translation for that word.
 */

public class Word {

    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mMiwokTranslation;

    public Word(String mDefaultTranslation, String mMiwokTranslation) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
    }

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
}

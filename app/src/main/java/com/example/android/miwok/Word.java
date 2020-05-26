package com.example.android.miwok;

public class Word {
    /**
     * Default translation for the word
     */
    private String mDefaultTranslation;

    /**
     * Miwok translation for the word
     */
    private String mMiwokTranslation;

    private int image_res_id = -1;


    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation   is the word in the Miwok language
     */
    public Word(String defaultTranslation, String miwokTranslation, int image_res_id) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        this.image_res_id = image_res_id;
    }

    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        image_res_id = -1;
    }

    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of the word.
     */
    public String getMiwokTranslation() { return mMiwokTranslation; }

    /**
     * Get the id of the corresponding image.
     */
    public int getImageId() { return image_res_id; }
}

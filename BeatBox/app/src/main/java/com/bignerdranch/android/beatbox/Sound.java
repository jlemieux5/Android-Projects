package com.bignerdranch.android.beatbox;

/**
 * Created by Josh on 6/19/2016.
 */
public class Sound {
    private String mAssetPath;
    private String mName;
    private Integer mSoundID;

    public Sound(String assetPath){
        mAssetPath = assetPath;
        String []components = assetPath.split("/");
        String fileName = components[components.length - 1];
        mName = fileName.replace(".wav", "");
    }

    public int getSoundID(){
        return mSoundID;
    }

    public void setSoundID(int soundID){
        mSoundID = soundID;
    }

    public String getAssetPath(){
        return mAssetPath;
    }

    public String getName(){
        return mName;
    }
}

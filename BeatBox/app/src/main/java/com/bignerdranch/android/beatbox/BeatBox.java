package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh on 6/19/2016.
 */
public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;
    private List<Sound> mSounds = new ArrayList<>();
    private AssetManager mAssets;
    private SoundPool mSoundPool;

    public BeatBox(Context context){
        mAssets = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loudSounds();
    }
    private void load(Sound sound) throws IOException {
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundID = mSoundPool.load(afd, 1);
        sound.setSoundID(soundID);
    }

    public void release(){
        mSoundPool.release();
    }

    public void play(Sound sound) {
        Integer soundID = sound.getSoundID();
        if(soundID == null){
            return;
        }
        mSoundPool.play(soundID, 1.0f, 1.0f, 1, 0, 1.0f);
    }
    private void loudSounds(){
        String [] soundNames;
        try{
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + "sounds.");
        } catch (IOException e){
            Log.e(TAG, "Could not load assets", e);
            return;
        }

        for(String fileName: soundNames){
            try {
            String assetPath = SOUNDS_FOLDER + "/" + fileName;
            Sound sound = new Sound(assetPath);
            load(sound);
            mSounds.add(sound); } catch (IOException e){
                Log.e(TAG, "Could not load sound " + fileName, e);
            }
        }
    }

    public List<Sound> getSounds(){
        return mSounds;
    }
}

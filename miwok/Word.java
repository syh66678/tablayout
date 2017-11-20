package com.example.android.miwok;

/**
 * Created by Administrator on 2017/9/27.
 */

public class Word {

    private String mDefaultTRANSLATION;

    private String mMiwokTranslation;

    private int imgpath=NO_IMAGE_PROVIDED;

    //判断资源是否存在标识
    private static final int NO_IMAGE_PROVIDED=-1;
    //存放音频资源ID
    private int mAudioResourceId;

    public Word(String defaultTranslation,String miwokTranslation,int imgpath) {
        mDefaultTRANSLATION = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        this.imgpath=imgpath;
    }
    public Word(String defaultTranslation,String miwokTranslation,int imgpath,int audioResourceId) {
        mDefaultTRANSLATION = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        this.imgpath=imgpath;
        mAudioResourceId=audioResourceId;
    }

    public String getDefaultTranslation(){
        return mDefaultTRANSLATION;
    }

    public int getImgpath(){
        return imgpath;
    }

    public String getmMiwokTranslation(){
        return mMiwokTranslation;
    }

    //检查图片是否存在
    public boolean hasImage(){
        return imgpath != NO_IMAGE_PROVIDED;
    }

    //获取音频资源ID
    public int getmAudioResourceId(){return mAudioResourceId;}

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTRANSLATION='" + mDefaultTRANSLATION + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", imgpath=" + imgpath +
                ", mAudioResourceId=" + mAudioResourceId +
                '}';
    }
}

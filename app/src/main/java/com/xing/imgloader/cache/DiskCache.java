package com.xing.imgloader.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;

import com.xing.imgloader.MyApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author: chuanxing
 * Date: 18-9-5
 * Function:
 */
public class DiskCache implements ImageCache {

    private String mCachePath;

    private String getDiskCacheDir(){
        if(!TextUtils.isEmpty(mCachePath)){
            return mCachePath;
        }
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()){
            //sd外存可用
            mCachePath = MyApplication.getContext().getExternalCacheDir().getPath();
        }else{
            mCachePath = MyApplication.getContext().getCacheDir().getPath();
        }
        return mCachePath;
    }

    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(getDiskCacheDir()+ File.separator+url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(getDiskCacheDir()+ File.separator+url);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;
            }
        }
    }
}

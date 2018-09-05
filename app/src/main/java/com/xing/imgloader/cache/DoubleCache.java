package com.xing.imgloader.cache;

import android.graphics.Bitmap;

/**
 * Author: chuanxing
 * Date: 18-9-5
 * Function:
 */
public class DoubleCache implements ImageCache {

    ImageCache mMemoryCache = new MemoryCache();
    ImageCache mSdCache = new DiskCache();

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
//        if(bitmap==null){
//            bitmap = mSdCache.get(url);
//        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url,bitmap);
//        mSdCache.put(url,bitmap);
    }
}

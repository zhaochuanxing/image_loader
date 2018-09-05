package com.xing.imgloader.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.xing.imgloader.cache.ImageCache;

/**
 * Author: chuanxing
 * Date: 18-9-5
 * Function: 内存缓存
 */
public class MemoryCache implements ImageCache {

    private LruCache<String,Bitmap> mMemoryCache;

    public MemoryCache(){
        //虚拟机也就是app可以获取到的最大的内存
        int memory = (int) (Runtime.getRuntime().maxMemory()/1024);
        //选取了1/4
        final int cacheSize = memory/4;
        mMemoryCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
//                return bitmap.getRowBytes() * bitmap.getHeight() /1024;
                return bitmap.getAllocationByteCount()/1024;
            }
        };


    }

    @Override
    public Bitmap get(String url) {
        return mMemoryCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url,bitmap);
    }
}

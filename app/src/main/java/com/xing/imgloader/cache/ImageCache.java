package com.xing.imgloader.cache;

import android.graphics.Bitmap;

/**
 * Author: chuanxing
 * Date: 18-9-5
 * Function:
 */
public interface ImageCache {

    //从缓存中取出
    public Bitmap get(String url);
    //将图片bitmap放入的缓存中
    public void put(String url,Bitmap bitmap);
}

package com.xing.imgloader.img;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.xing.imgloader.cache.DoubleCache;
import com.xing.imgloader.cache.ImageCache;
import com.xing.imgloader.cache.MemoryCache;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: chuanxing
 * Date: 18-9-5
 * Function:
 */
public class ImageLoader {

    private static ImageLoader sImageLoader = new ImageLoader();

    private ImageLoader(){

    }

    public static ImageLoader getInstance(){
        return sImageLoader;
    }

    ImageCache mImageCache = new DoubleCache();

    private Handler mHandler = new Handler(Looper.getMainLooper());

    //根据cpu的核心数,创建固定数量的线程池
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void displayImage(final String url, final ImageView imageView) {
        Bitmap bitmap = mImageCache.get(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            downloadImgAsync(url, imageView);
        }
    }

    private void downloadImgAsync(final String url, final ImageView imageView) {
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = downloadImage(url);
                if (bitmap == null) {
                    return;
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (imageView.getTag().equals(url)) {
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                });

                mImageCache.put(url, bitmap);
            }
        });
    }

    private Bitmap downloadImage(String url) {
        Bitmap bitmap = null;
        try {
            URL bitmapUrl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) bitmapUrl.openConnection();
//            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            httpURLConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}

package com.xing.imgloader.img;

import java.io.Closeable;

/**
 * Author: chuanxing
 * Date: 18-9-5
 * Function:
 */
public class CloseUtil {

    public static void close(Closeable closeable){
        if(closeable!=null){
            try{
                closeable.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

package com.xing.imgloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xing.imgloader.img.ImageLoader;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Button mLoadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView)findViewById(R.id.iv_img);
        mLoadBtn = (Button)findViewById(R.id.btn_load);
        mLoadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageLoader imageLoader = ImageLoader.getInstance();
                String imgUrl = "http://www.qqma.com/imgpic2/cpimagenew/2018/4/5/6e1de60ce43d4bf4b9671d7661024e7a.jpg";
                imageLoader.displayImage(imgUrl,mImageView);
            }
        });

    }
}

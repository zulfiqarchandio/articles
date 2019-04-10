package com.smartgovt.nytimes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class ArticleDetailActivity extends Activity {

    public TextView titleTxt, autherTxt, dateTxt, detailTxt;
    public ImageView image, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_detail);

        titleTxt = (TextView) findViewById(R.id.titleTxt);
        autherTxt = (TextView) findViewById(R.id.autherTxt);
        dateTxt = (TextView) findViewById(R.id.dateTxt);
        detailTxt = (TextView) findViewById(R.id.detailTxt);
        image = (ImageView) findViewById(R.id.image);
        backBtn = (ImageView) findViewById(R.id.backBtn);


        String title = getIntent().getExtras().getString("title");
        String byline = getIntent().getExtras().getString("byline");
        String published_date = getIntent().getExtras().getString("published_date");
        String abstractData = getIntent().getExtras().getString("abstractData");
        String imageUrl = getIntent().getExtras().getString("image");

        titleTxt.setText(title);
        autherTxt.setText(byline);
        dateTxt.setText(published_date);
        detailTxt.setText(abstractData);
        ImageLoader.getInstance().displayImage(imageUrl, image, MyApp.getInstance().getDisplayOptions());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}

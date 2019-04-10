package com.smartgovt.nytimes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.smartgovt.nytimes.adapter.ArticleAdapter;
import com.smartgovt.nytimes.model.Article;
import com.smartgovt.nytimes.rest.ApiClient;
import com.smartgovt.nytimes.utils.Constants;
import com.smartgovt.nytimes.utils.JSON;
import com.smartgovt.nytimes.utils.RecyclerTouchListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView articlesRecyclerView;
    private ArticleAdapter adapter;
    List<Article> articles = new ArrayList<Article>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        articlesRecyclerView = (RecyclerView) findViewById(R.id.articlesRecyclerView);
        adapter = new ArticleAdapter(articles);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        articlesRecyclerView.setLayoutManager(mLayoutManager);
        articlesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        articlesRecyclerView.setAdapter(adapter);

        articlesRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), articlesRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Article article = articles.get(position);
                //Toast.makeText(getApplicationContext(), article.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this, ArticleDetailActivity.class);
                i.putExtra("title", article.getTitle());
                i.putExtra("byline", article.getByline());
                i.putExtra("published_date", article.getPublished_date());
                i.putExtra("abstractData", article.getAbstractData());
                i.putExtra("image", article.getImage());
                startActivity(i);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        getArticles("7");
    }

    private void getArticles(String period) {

        String url = ApiClient.BASE_URL + ApiClient.PATH_URL + ApiClient.Sections + "/" + period + ".json?api-key=" + Constants.api_key;

        ApiClient api = ApiClient.getInstance(this);
        api.getUrlRequest(url, api.new GetCallback() {
            @Override
            public void onResponseReceived(boolean success, JSONObject response) {
                if (success) {


                    try {


                        if (JSON.getStringValue(response, "status").equals("OK")) {

                            JSONArray array = JSON.getJSONArray(response, "results");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                Article article = new Article(obj);
                                articles.add(article);

                            }

                            adapter.notifyDataSetChanged();

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}

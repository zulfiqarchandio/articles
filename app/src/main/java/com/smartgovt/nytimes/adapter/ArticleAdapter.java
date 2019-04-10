package com.smartgovt.nytimes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.smartgovt.nytimes.MyApp;
import com.smartgovt.nytimes.R;
import com.smartgovt.nytimes.model.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {

    private List<Article> articleList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTxt, autherTxt, dateTxt;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            titleTxt = (TextView) view.findViewById(R.id.titleTxt);
            autherTxt = (TextView) view.findViewById(R.id.autherTxt);
            dateTxt = (TextView) view.findViewById(R.id.dateTxt);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }


    public ArticleAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.titleTxt.setText(article.getTitle());
        holder.autherTxt.setText(article.getAbstractData());
        holder.dateTxt.setText(article.getPublished_date());
        ImageLoader.getInstance().displayImage(article.getImage(), holder.image, MyApp.getInstance().getDisplayOptions());

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

}

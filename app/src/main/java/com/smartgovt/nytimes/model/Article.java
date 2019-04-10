package com.smartgovt.nytimes.model;

import com.smartgovt.nytimes.utils.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Article {

    private String id;
    private String title;
    private String byline;
    private String published_date;
    private String abstractData;
    private String image;


    public Article(JSONObject obj) {

        try {
            setId(JSON.getStringValue(obj, "id"));
            setTitle(JSON.getStringValue(obj, "title"));
            setByline(JSON.getStringValue(obj, "byline"));
            setPublished_date(JSON.getStringValue(obj, "published_date"));
            setAbstractData(JSON.getStringValue(obj, "abstract"));

            // geting media url
            JSONArray mediaArray = JSON.getJSONArray(obj, "media");
            if (mediaArray.length() > 0) {
                JSONObject mediaObj = (JSONObject) mediaArray.get(0);
                JSONArray imageArray = JSON.getJSONArray(mediaObj, "media-metadata");
                if (imageArray.length() > 0) {

                    JSONObject imageObj = (JSONObject) imageArray.get(0);
                    setImage(JSON.getStringValue(imageObj, "url"));
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getAbstractData() {
        return abstractData;
    }

    public void setAbstractData(String abstractData) {
        this.abstractData = abstractData;
    }
}

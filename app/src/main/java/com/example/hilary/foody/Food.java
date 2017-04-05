package com.example.hilary.foody;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by hilary on 3/28/17.
 */

public class Food {
    String title;
    String description;
    int price;
    String imageUrl;

    public Food(){

    }
    public Food(String title, String description, int price, String imageUrl){
        this.title = title;
        this.description =  description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public  void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }


}

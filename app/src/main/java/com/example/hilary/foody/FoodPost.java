package com.example.hilary.foody;

/**
 * Created by hilary on 22/03/17.
 */

public class FoodPost {
    int imageId;
    String subheading;
    String txtFooter;

    FoodPost(String subheading, String txtFooter, int imageId){
        this.subheading = subheading;
        this.txtFooter = txtFooter;
        this.imageId = imageId;
    }
}

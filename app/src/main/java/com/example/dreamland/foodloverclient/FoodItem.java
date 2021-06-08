package com.example.dreamland.foodloverclient;

import com.squareup.picasso.Picasso;

public class FoodItem {
    String name,foodimage,restaurantname, description, price, foodid ;

    public FoodItem(String name, String foodimage, String restaurantname, String description, String price, String foodid) {
        this.name = name;
        this.foodimage = foodimage;
        this.restaurantname = restaurantname;
        this.description = description;
        this.price = price;
        this.foodid = foodid;
    }

    public FoodItem() {
    }

    public String getName() {
        return name;
    }

    public String getFoodimage() {
        return foodimage;
    }

    public String getRestaurantname() {
        return restaurantname;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getFoodid() {
        return foodid;
    }
}

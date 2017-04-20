package com.yuqian.food.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by yangy on 2017/4/11.
 */
@Table("food")
public class Food {
    @Id
    private int id;
    @Column
    private String foodName;

    @Column
    private String foodPrice;

    @Column
    private String foodDescribe;

    @Column
    private String foodPhotoUrl;

    @Column Integer restaurantNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodDescribe() {
        return foodDescribe;
    }

    public void setFoodDescribe(String foodDescribe) {
        this.foodDescribe = foodDescribe;
    }

    public String getFoodPhotoUrl() {
        return foodPhotoUrl;
    }

    public void setFoodPhotoUrl(String foodPhotoUrl) {
        this.foodPhotoUrl = foodPhotoUrl;
    }

    public void setRestaurantNum(Integer restaurantNum) {
        this.restaurantNum = restaurantNum;
    }
}

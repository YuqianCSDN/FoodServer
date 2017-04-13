package com.yuqian.food.controller;

import com.yuqian.food.entity.Food;
import com.yuqian.food.util.MD5;
import com.yuqian.food.util.QiNiuUpPhotoUtil;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.upload.TempFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Qian on 2017-4-11.
 */
@IocBean
public class FoodModule {
    @Inject
    Dao dao;
    @At("getFoodList")
    @Ok("json")
    @Fail("http:500")
    @GET
    public Object getFoodList()
    {
        int pageNumber=0,pageSize=100;
        Pager pager = dao.createPager(pageNumber, pageSize);
        List<Food> list = dao.query(Food.class, null, pager);
        pager.setRecordCount(dao.count(Food.class));
        QueryResult queryResult= new QueryResult(list, pager);
        HashMap<String,Object> hashMap=new HashMap<>();
        if(queryResult.getPager().getPageSize()==0){
            hashMap.put("msg","没有数据了。");
            hashMap.put("state",0);
            return hashMap;
        }
        hashMap.put("msg","success");
        hashMap.put("state",1);
        hashMap.put("foods",queryResult.getList());

        return hashMap;
    }
    @At("addFood")
    @Ok("json")
    @Fail("http:500")
    @POST
    public Object addFood(@Param("foodName")String foodName,
                          @Param("foodPrice")String foodPrice,
                          @Param("foodDescribe")String foodDescribe,
                          @Param("foodPictureFile")TempFile foodPictureFile){
        HashMap<String,Object> hashMap=new HashMap<>();
        if(foodName==null||"".equals(foodName)||foodPrice==null||"".equals(foodPrice)
                ||foodDescribe==null||"".equals(foodDescribe)) {
            hashMap.put("msg","参数错误");
            hashMap.put("state",0);
            return hashMap;
        }
        File file=foodPictureFile.getFile();
        final String urlhead = "http://oe1rqbymq.bkt.clouddn.com/";
        String keyString= MD5.encryptTimeStamp(System.currentTimeMillis()+"_"+foodName);
        String photoUrl=urlhead+keyString;
        QiNiuUpPhotoUtil qiNiuUpPhotoUtil=new QiNiuUpPhotoUtil();
        if (qiNiuUpPhotoUtil.upload(file, keyString)) {
            hashMap.put("msg","添加成功");
            hashMap.put("state",1);
            Food food=new Food();
            food.setFoodDescribe(foodDescribe);
            food.setFoodName(foodName);
            food.setFoodPhotoUrl(photoUrl);
            food.setFoodPrice(foodPrice);
            dao.insert(food);
            try {
                foodPictureFile.delete();
            } catch (IOException e) {
                e.printStackTrace();
                hashMap.put("msg","失败");
                hashMap.put("state",0);
                return hashMap;
            }
            return hashMap;
        }else {
            hashMap.put("msg","图片文件上传失败");
            hashMap.put("state",0);
            return hashMap;
        }
    }

}

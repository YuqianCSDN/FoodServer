package com.yuqian.food.controller;

import com.yuqian.food.entity.Food;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;

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

}

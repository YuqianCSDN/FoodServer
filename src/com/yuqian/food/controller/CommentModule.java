package com.yuqian.food.controller;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yangy on 2017/4/11.
 */
@IocBean
public class CommentModule {
    @Inject
    Dao dao;
    @At("getCommentByFoodId")
    @Ok("json")
    @Fail("http:500")
    public Object getCommentByFoodId(@Param("foodId")Integer foodId){
        HashMap<String,Object> hashMap=new HashMap<>();
        if(foodId==null){
            hashMap.put("msg","");
            hashMap.put("state",0);
            return hashMap;
        }
        int pageNumber=0,pageSize=100;
        Pager pager = dao.createPager(pageNumber, pageSize);

        Sql sql = Sqls.queryRecord("select a.id,a.userName,b.* from user a left join comment b on a.id = b.userId where b.foodid= "+foodId+"");
        dao.execute(sql);
        List<Record> list = sql.getList(Record.class);
        if(list.isEmpty()){
            hashMap.put("msg","没有数据了。");
            hashMap.put("state",0);
            return hashMap;
        }
        hashMap.put("msg","success");
        hashMap.put("state",1);
        hashMap.put("comments",list);
        return hashMap;
    }

}
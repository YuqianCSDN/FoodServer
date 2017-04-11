package com.yuqian.food.controller;


import com.yuqian.food.entity.User;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@IocBean
public class UserModule {
    @Inject
    Dao dao;
    @At("public/login")
    @Ok("json")
    @Fail("http:500")
    @GET
    public Object login(HttpServletRequest request, HttpSession session, HttpServletResponse response, @Param("userName") String userName, @Param("password")String password){
        if(userName==null||password==null||"".equals(userName)||"".equals(password)){
            request.setAttribute("error_msg","参数有误");
            return "jsp:/public/login";
        }
        User user=dao.fetch(User.class, Cnd.where("userName","=",userName).and("password","=",password));
        if(user!=null){
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("msg","success");
            hashMap.put("state",1);
            hashMap.put("userModel",user);
            return hashMap;
        }else {
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("msg","用户名或密码错误！");
            hashMap.put("state",0);
            return hashMap;

        }
    }
}

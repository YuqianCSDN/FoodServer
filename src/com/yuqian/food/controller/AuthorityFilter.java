package com.yuqian.food.controller;

import com.yuqian.food.entity.User;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ServerRedirectView;


@IocBean
public class AuthorityFilter implements ActionFilter {
	@Inject
    Dao dao;
	
	@Override
	public View match(ActionContext actionContext) {
		int userId=(int)actionContext.getRequest().getAttribute("Id");
		String accessKey=(String)actionContext.getRequest().getAttribute("accessKey");
		if(accessKey==null||"".equals(accessKey)){

		}
		User user=dao.fetch(User.class, Cnd.where("id","=",userId).and("accessKey","=",accessKey));
		if (user!=null){
			return null;
		}else{
			return new ServerRedirectView("/public/autoLogin.php");

		}


	}
}

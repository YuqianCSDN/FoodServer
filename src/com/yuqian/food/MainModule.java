package com.yuqian.food;


import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@SetupBy(value=MainSetup.class)
@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/",
    "*anno", "com.yuqian.food",
    "*tx",
    "*async"})
@IocBean
@Modules(scanPackage=true)
public class MainModule {
	
}
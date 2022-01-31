package com.yuanhao.boot;

import com.yuanhao.boot.bean.Pet;
import com.yuanhao.boot.bean.User;
import com.yuanhao.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 主程序类;主配置类
 * @SpringBootApplication 这是一个 SpringBoot 应用
 * @author Yuanhao
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        //1.返回 IOC 容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        //2.查看容器内部组件
        String[] names = run.getBeanDefinitionNames();
        for(String name : names) {
            System.out.println(name);
        }

        //3.从容器中获取组件 默认单实例
        Pet tom01 = run.getBean("tom", Pet.class);
        Pet tom02 = run.getBean("tom", Pet.class);
        System.out.println("组件:" + (tom01 == tom02));

        //4.com.yuanhao.boot.config.MyConfig$$EnhancerBySpringCGLIB$$472fe3fd@3044e9c7
        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean);

        //如果代理对象调用方法 proxyBeanMethods = true，SpringBoot会检查这个组件是否在容器中，保持组件单实例
        User user01 = bean.user01();
        User user02 = bean.user01();
        System.out.println(user01 == user02);

        //组件依赖 proxyBeanMethods
        System.out.println("用户的宠物:" + (user01.getPet() == user02.getPet()));

        //5.获取组件
        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        for(String s : beanNamesForType) {
            System.out.println(s);
        }
    }
}

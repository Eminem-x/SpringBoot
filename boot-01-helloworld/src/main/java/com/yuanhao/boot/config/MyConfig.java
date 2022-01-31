package com.yuanhao.boot.config;

import com.yuanhao.boot.bean.Car;
import com.yuanhao.boot.bean.Pet;
import com.yuanhao.boot.bean.User;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 1. 配置类中可以使用 @Bean 标注方法上给容器注册组件 默认单实例
 * 2. 配置类本身也是组件
 * 3. proxyBeanMethods: 代理 bean 的方法
 *    Full(proxyBeanMethods = true)
 *    Lite(proxyBeanMethods = false) 轻量级
 * 4. @Import 给容器中自动创建出这类型的组件，默认组件的名字就是全类名
 * 5. @EnableConfigurationProperties 开启配置绑定功能并且将组件注册到容器
 * @author Yuanhao
 */
@Import({User.class})
@Configuration(proxyBeanMethods = true) //告诉SpringBoot这是一个配置类 == 配置文件
@EnableConfigurationProperties(Car.class)
public class MyConfig {

    /**
     * 外部无论对配置类中的这个组件注册方法调用多少次
     * 获取的都是之前注册容器中的单实例对象
     * @return
     */
    @Bean
    public User user01() {
        //向容器中添加组件 以方法名作为组件的id，返回类型就是组件类型，返回值就是组件在容器中的实例
        User user = new User("zhangsan", 18);
        //user组件依赖pet组件
        user.setPet(tomcatPet());
        return user;
    }

    @Bean("tom")
    public Pet tomcatPet() {
        return new Pet("tomcat");
    }
}

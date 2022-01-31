package com.yuanhao.boot.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 只有在容器中的组件，才会拥有容器提供的强大功能
 *
 * @author Yuanhao
 */
//@Component

@Data
@ToString
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String brand;
    private Integer price;
}

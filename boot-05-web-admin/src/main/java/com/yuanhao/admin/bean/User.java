package com.yuanhao.admin.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Yuanhao
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String password;
}

package com.yuanhao.admin.mapper;

import com.yuanhao.admin.bean.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Yuanhao
 */
@Mapper
public interface AccountMapper {
    public Account getAccount(String name);
}

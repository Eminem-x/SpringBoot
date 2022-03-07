package com.yuanhao.modules.system.service.mapstruct;

import com.yuanhao.base.BaseMapper;
import com.yuanhao.modules.system.domain.User;
import com.yuanhao.modules.system.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Yuanhao
 */
@Mapper(componentModel = "spring",uses = {RoleMapper.class, DeptMapper.class, JobMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, User> {
}

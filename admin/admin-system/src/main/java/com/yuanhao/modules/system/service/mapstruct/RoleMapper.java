package com.yuanhao.modules.system.service.mapstruct;

import com.yuanhao.base.BaseMapper;
import com.yuanhao.modules.system.domain.Role;
import com.yuanhao.modules.system.service.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Yuanhao
 */
@Mapper(componentModel = "spring", uses = {MenuMapper.class, DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends BaseMapper<RoleDto, Role> {

}

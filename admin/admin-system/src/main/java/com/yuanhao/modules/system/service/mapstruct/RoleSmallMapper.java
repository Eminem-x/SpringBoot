package com.yuanhao.modules.system.service.mapstruct;

import com.yuanhao.base.BaseMapper;
import com.yuanhao.modules.system.domain.Role;
import com.yuanhao.modules.system.service.dto.RoleSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Yuanhao
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleSmallMapper extends BaseMapper<RoleSmallDto, Role> {
}

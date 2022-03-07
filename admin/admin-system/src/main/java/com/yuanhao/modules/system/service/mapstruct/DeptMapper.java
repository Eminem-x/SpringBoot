package com.yuanhao.modules.system.service.mapstruct;

import com.yuanhao.base.BaseMapper;
import com.yuanhao.modules.system.domain.Dept;
import com.yuanhao.modules.system.service.dto.DeptDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Yuanhao
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapper extends BaseMapper<DeptDto, Dept> {
}

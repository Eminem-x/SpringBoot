package com.yuanhao.modules.system.service.mapstruct;

import com.yuanhao.base.BaseMapper;
import com.yuanhao.modules.system.domain.Job;
import com.yuanhao.modules.system.service.dto.JobDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Yuanhao
 */
@Mapper(componentModel = "spring",uses = {DeptMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper extends BaseMapper<JobDto, Job>{
}

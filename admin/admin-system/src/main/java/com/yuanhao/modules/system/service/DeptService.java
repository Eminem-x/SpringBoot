package com.yuanhao.modules.system.service;

import com.yuanhao.modules.system.domain.Dept;

import java.util.List;
import java.util.Set;

/**
 * @author Yuanhao
 */
public interface DeptService {
    /**
     * 根据PID查询
     * @param pid /
     * @return /
     */
    List<Dept> findByPid(long pid);

    /**
     * 根据角色ID查询
     * @param id /
     * @return /
     */
    Set<Dept> findByRoleId(Long id);

    /**
     * 获取
     * @param deptList
     * @return
     */
    List<Long> getDeptChildren(List<Dept> deptList);
}

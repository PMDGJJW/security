package cn.nyse.fsxjjw.serivce;

import cn.nyse.fsxjjw.entity.SysPermission;

import java.util.List;

public interface SysPermissionService  {

    List<SysPermission> selectListByUser(Integer userId);

    /**
     * 查询具体某个接口的权限
     *
     * @param path
     * @return
     */
    List<SysPermission> selectListByPath(String path);

}

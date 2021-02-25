package cn.nyse.fsxjjw.serivce;

import cn.nyse.fsxjjw.entity.SysUser;

public interface SysUserService  {

    SysUser selectByUserName(String userName);

    int update(SysUser sysUser);

}

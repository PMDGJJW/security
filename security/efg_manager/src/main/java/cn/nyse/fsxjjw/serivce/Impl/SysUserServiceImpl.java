package cn.nyse.fsxjjw.serivce.Impl;

import cn.nyse.fsxjjw.dao.SysUserDao;
import cn.nyse.fsxjjw.entity.SysUser;
import cn.nyse.fsxjjw.serivce.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public SysUser selectByUserName(String userName) {
        return sysUserDao.selectByUserName(userName);
    }

    @Override
    public int update(SysUser sysUser) {

        return sysUserDao.updateAllColumnById(sysUser);
    }
}

package cn.nyse.fsxjjw.serivce.Impl;

import cn.nyse.fsxjjw.dao.SysPermissionDao;
import cn.nyse.fsxjjw.entity.SysPermission;
import cn.nyse.fsxjjw.serivce.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionDao sysPermissionDao;

    @Override
    public List<SysPermission> selectListByUser(Integer userId) {
        return sysPermissionDao.selectListByUser(userId);
    }

    @Override
    public List<SysPermission> selectListByPath(String path) {
        return sysPermissionDao.selectListByPath(path);
    }
}

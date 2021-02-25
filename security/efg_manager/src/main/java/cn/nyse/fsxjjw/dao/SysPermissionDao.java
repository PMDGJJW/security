package cn.nyse.fsxjjw.dao;

import cn.nyse.fsxjjw.entity.SysPermission;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPermissionDao extends BaseMapper<SysPermission> {

    List<SysPermission> selectListByUser (@Param("userId") Integer userId);

    /**
     * 查询具体某个接口的权限
     *
     * @param path 接口路径
     * @return
     */
    List<SysPermission> selectListByPath(String path);

}

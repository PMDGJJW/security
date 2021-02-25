package cn.nyse.fsxjjw.dao;

import cn.nyse.fsxjjw.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {

    SysUser selectByUserName(@Param("userName") String userName);

}

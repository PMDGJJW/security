package cn.nyse.fsxjjw.config.service.Impl;

import cn.nyse.fsxjjw.entity.SysPermission;
import cn.nyse.fsxjjw.entity.SysUser;
import cn.nyse.fsxjjw.serivce.SysPermissionService;
import cn.nyse.fsxjjw.serivce.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        if (StringUtils.isEmpty(userName)){
            throw new RuntimeException("用户不能为空");
        }
        //根据用户名查询用户
        SysUser sysUser = sysUserService.selectByUserName(userName);
        if (sysUser == null) {
            throw new RuntimeException("用户不存在");
        }

        if (sysUser != null){
            List<SysPermission> sysPermissions = sysPermissionService.selectListByUser(sysUser.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            // 声明用户授权
            sysPermissions.forEach(sysPermission -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermissionCode());
                grantedAuthorities.add(grantedAuthority);
            });

            User user = new User(sysUser.getAccount(), sysUser.getPassword(), sysUser.getEnabled(), sysUser.getNotExpired(), sysUser.getCredentialsNotExpired(), sysUser.getAccountNotLocked(), grantedAuthorities);
            return user;
        }
            return  null;
    }


}

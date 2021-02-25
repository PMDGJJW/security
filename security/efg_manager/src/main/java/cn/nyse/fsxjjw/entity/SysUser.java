package cn.nyse.fsxjjw.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser  implements Serializable {

    private static final long serialVersionUID = 915478504870211231L;

    private Integer id;

    private String account;

    private String userName;

    private String password;

    private Date lastLoginTime;

    private Boolean enabled;

    private Boolean notExpired;

    private Boolean accountNotLocked;

    private Boolean credentialsNotExpired;

    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
    //创建人
    private Integer createUser;
    //修改人
    private Integer updateUser;

    private Integer isLogin;

}

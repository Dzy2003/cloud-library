package com.duan.library.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")

@Data
public class User implements Serializable {
    private static final String prefix = "user_";
    /**
     * 用户id
     */
    @TableId(value = prefix + "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名称
     */
    @TableField(prefix + "name")
    private String name;

    /**
     * 用户密码
     */
    @TableField(prefix + "password")
    private String password;

    /**
     * 用户邮箱（用户账号）
     */
    @TableField(prefix + "email")
    private String email;

    /**
     * 用户入职时间
     */
    @TableField(prefix + "hiredate")
    private String hiredate;


    /**
     * 用户角色
     */
    @TableField(prefix + "role")
    private String role;

    /**
     * 用户离职时间
     */
    @TableField(prefix + "departuredate")
    private String departuredate;

    /**
     * 用户状态（0:正常,1:禁用）
     */
    @TableField(prefix + "status")
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getHiredate() == null ? other.getHiredate() == null : this.getHiredate().equals(other.getHiredate()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()))
            && (this.getDeparturedate() == null ? other.getDeparturedate() == null : this.getDeparturedate().equals(other.getDeparturedate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getHiredate() == null) ? 0 : getHiredate().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        result = prime * result + ((getDeparturedate() == null) ? 0 : getDeparturedate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", hiredate=").append(hiredate);
        sb.append(", role=").append(role);
        sb.append(", departuredate=").append(departuredate);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
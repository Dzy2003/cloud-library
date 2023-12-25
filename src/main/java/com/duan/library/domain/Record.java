package com.duan.library.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName record
 */
@TableName(value ="record")
@Data
public class Record implements Serializable {
    /**
     * 借阅记录id
     */
    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 借阅的图书名称
     */
    @TableField(value = "record_bookname")
    private String bookname;

    /**
     * 借阅的图书的ISBN编号
     */
    @TableField(value = "record_bookisbn")
    private String bookisbn;

    /**
     * 图书借阅人
     */
    @TableField(value = "record_borrower")
    private String borrower;

    /**
     * 图书借阅时间
     */
    @TableField(value = "record_borrowtime")
    private String borrowTime;

    /**
     * 图书归还时间
     */
    @TableField(value = "record_remandtime")
    private String remandTime;

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
        Record other = (Record) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBookname() == null ? other.getBookname() == null : this.getBookname().equals(other.getBookname()))
            && (this.getBookisbn() == null ? other.getBookisbn() == null : this.getBookisbn().equals(other.getBookisbn()))
            && (this.getBorrower() == null ? other.getBorrower() == null : this.getBorrower().equals(other.getBorrower()))
            && (this.getBorrowTime() == null ? other.getBorrowTime() == null : this.getBorrowTime().equals(other.getBorrowTime()))
            && (this.getRemandTime() == null ? other.getRemandTime() == null : this.getRemandTime().equals(other.getRemandTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBookname() == null) ? 0 : getBookname().hashCode());
        result = prime * result + ((getBookisbn() == null) ? 0 : getBookisbn().hashCode());
        result = prime * result + ((getBorrower() == null) ? 0 : getBorrower().hashCode());
        result = prime * result + ((getBorrowTime() == null) ? 0 : getBorrowTime().hashCode());
        result = prime * result + ((getRemandTime() == null) ? 0 : getRemandTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bookname=").append(bookname);
        sb.append(", bookisbn=").append(bookisbn);
        sb.append(", borrower=").append(borrower);
        sb.append(", borrowtime=").append(borrowTime);
        sb.append(", remandtime=").append(remandTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
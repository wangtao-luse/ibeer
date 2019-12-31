package com.ibeer.model;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author wangtao
 * @since 2019-12-31
 */
@Data
@TableName("t_a_account")
public class Account extends Model<Account> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("UID")
    private Integer uid;

    @TableField("SEX")
    private String sex;

    @TableField("CREATEDATE")
    private Long createdate;

    @TableField("CREATEIP")
    private String createip;

    @TableField("LASTTIME")
    private Long lasttime;

    @TableField("STATUS")
    private String status;   

    @Override
    protected Serializable pkVal() {
        return null;
    }

  
}

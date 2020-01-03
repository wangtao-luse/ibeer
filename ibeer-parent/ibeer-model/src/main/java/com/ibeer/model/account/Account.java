package com.ibeer.model.account;
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
    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户唯一号
     */
    @TableField("UID")
    private String uid;
    /**
     * 性别
     */
    @TableField("SEX")
    private String sex;
    /**
     * 注册日期
     */
    @TableField("CREATEDATE")
    private Long createdate;
    /**
     * 注册IP
     */
    @TableField("CREATEIP")
    private String createip;
    /**
     * 上次登录时间
     */
    @TableField("LASTTIME")
    private Long lasttime;
    /*
     * 用户状态
     */
    @TableField("STATUS")
    private String status;   
   /**
    * 用户类型 1:个人；2:公司
    */
    @TableField("UTYPE")
    private String utype;
    @Override
    protected Serializable pkVal() {
        return null;
    }

  
}

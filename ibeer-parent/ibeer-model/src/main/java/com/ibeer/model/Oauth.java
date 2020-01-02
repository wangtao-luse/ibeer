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
 * @since 2020-01-02
 */
@Data
@TableName("t_a_oauth")
public class Oauth extends Model<Oauth> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("U_ID")
    private Integer uId;

    @TableField("OAUTH_ID")
    private String oauthId;

    @TableField("OAUTH_TYPE")
    private String oauthType;

    @TableField("CREDENTIAL")
    private String credential;

    @TableField("NICKNAME")
    private String nickname;

    @TableField("AVATAR")
    private String avatar;

   

    @Override
    protected Serializable pkVal() {
        return null;
    }

   
}

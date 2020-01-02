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
 * @since 2020-01-02
 */
@Data
@TableName("t_a_oauth")
public class Oauth extends Model<Oauth> {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户唯一号
     */
    @TableField("U_ID")
    private Integer uId;
    /**
     * 第三方登录唯一ID
     */
    @TableField("OAUTH_ID")
    private String oauthId;
    /**
     * 第三方登录唯一标识(手机：phone;邮箱：email;QQ:qq;微信：wechat;用户名：uid;微博:weibo)
     */
    @TableField("OAUTH_TYPE")
    private String oauthType;
    /**
     * 密码凭证 (站内保存密码，站外保存token或不保存)
     */
    @TableField("CREDENTIAL")
    private String credential;
    /**
     * 昵称
     */
    @TableField("NICKNAME")
    private String nickname;
   /**
    * 头像
    */
    @TableField("AVATAR")
    private String avatar;

   

    @Override
    protected Serializable pkVal() {
        return null;
    }

   
}

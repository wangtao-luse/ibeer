package com.ibeer.model.account;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;


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
    private String uId;
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
   /**
    * 盐
    */
    private String pwd;

    public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getuId() {
	return uId;
}

public void setuId(String uId) {
	this.uId = uId;
}

public String getOauthId() {
	return oauthId;
}

public void setOauthId(String oauthId) {
	this.oauthId = oauthId;
}

public String getOauthType() {
	return oauthType;
}

public void setOauthType(String oauthType) {
	this.oauthType = oauthType;
}

public String getCredential() {
	return credential;
}

public void setCredential(String credential) {
	this.credential = credential;
}

public String getNickname() {
	return nickname;
}

public void setNickname(String nickname) {
	this.nickname = nickname;
}

public String getAvatar() {
	return avatar;
}

public void setAvatar(String avatar) {
	this.avatar = avatar;
}

public String getPwd() {
	return pwd;
}

public void setPwd(String pwd) {
	this.pwd = pwd;
}

	@Override
    protected Serializable pkVal() {
        return null;
    }

   
}

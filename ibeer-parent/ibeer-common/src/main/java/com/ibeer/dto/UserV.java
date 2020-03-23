package com.ibeer.dto;
public class UserV {

    /**
     * 用户唯一号
     */
    private Integer uId;
   
    /**
     * 第三方登录唯一标识(手机：phone;邮箱：email;QQ:qq;微信：wechat;用户名：uid;微博:weibo)
     */
    private String oauthType;
   
    /**
     * 昵称
     */
    private String nickname;
   /**
    * 头像
    */
    private String avatar;
    /**
     * 凭证
     */
    private String credential;
    /**
     * 第三方登录唯一号
     */
    private String oauthId;
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public String getOauthType() {
		return oauthType;
	}
	public void setOauthType(String oauthType) {
		this.oauthType = oauthType;
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
	public String getCredential() {
		return credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	public String getOauthId() {
		return oauthId;
	}
	public void setOauthId(String oauthId) {
		this.oauthId = oauthId;
	}

    

}

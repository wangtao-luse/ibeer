package com.ibeer.common.req;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
@Data
public class RequestBody implements Serializable {
private static final long serialVersionUID = 1L;
private JSONObject content;
/**
 * 用户唯一号
 */
private Integer uId;
/**
 * 第三方登录唯一ID
 */
private String oauthId;
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


}

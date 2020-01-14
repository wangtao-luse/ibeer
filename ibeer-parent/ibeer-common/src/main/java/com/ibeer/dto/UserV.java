package com.ibeer.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
@Data
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

    

}

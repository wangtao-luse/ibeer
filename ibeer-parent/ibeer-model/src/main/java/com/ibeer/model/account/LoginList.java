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
 * @since 2020-01-07
 */
@TableName("t_a_login_list")
public class LoginList extends Model<LoginList> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("U_ID")
    private String uId;

    @TableField("LOGIN_TIME")
    private Long loginTime;

    @TableField("LOGIN_IP")
    private String loginIp;

    @TableField("LOGIN_IP_LOOKUP")
    private String loginIpLookup;

    
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


	public Long getLoginTime() {
		return loginTime;
	}


	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}


	public String getLoginIp() {
		return loginIp;
	}


	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}


	public String getLoginIpLookup() {
		return loginIpLookup;
	}


	public void setLoginIpLookup(String loginIpLookup) {
		this.loginIpLookup = loginIpLookup;
	}


	@Override
    protected Serializable pkVal() {
        return this.id;
    }

   
}

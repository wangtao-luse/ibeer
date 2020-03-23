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
 * @since 2019-12-31
 */
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
        return this.id;
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Long getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Long createdate) {
		this.createdate = createdate;
	}
	public String getCreateip() {
		return createip;
	}
	public void setCreateip(String createip) {
		this.createip = createip;
	}
	public Long getLasttime() {
		return lasttime;
	}
	public void setLasttime(Long lasttime) {
		this.lasttime = lasttime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	
}

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
 * @since 2020-01-03
 */
@TableName("t_a_company_info")
public class CompanyInfo extends Model<CompanyInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("U_ID")
    private String uId;

    @TableField("COMPANY_TYPE")
    private String companyType;

    @TableField("COMPANY_NAME")
    private String companyName;

    @TableField("CREDIT_ID")
    private String creditId;

   

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



	public String getCompanyType() {
		return companyType;
	}



	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}



	public String getCompanyName() {
		return companyName;
	}



	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	public String getCreditId() {
		return creditId;
	}



	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}



	@Override
    protected Serializable pkVal() {
        return this.id;
    }

}

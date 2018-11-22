package com.huisou.po;

import java.util.Date;

import javax.persistence.*;

@Table(name = "crm_customer")
public class CustomerPo {
    /**
     * 客户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer custid;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 微信号
     */
    private String weixin;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 公司名称
     */
    private String companyname;

    /**
     * 公司简介
     */
    private String companyintro;

    /**
     * 区
     */
    private String province;
    
    /**
     * 市
     */
    private String city;

    private String area;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 客户状态：1，正常；2，禁用
     */
    private Integer custstatus;

    /**
     * 关联业务员id
     */
    private Integer saleid;

    private Integer createby;

    /**
     * 创建时间
     */
    private Date createdate;

    /**
     * 更新人
     */
    private Integer updateby;

    private Date updatedate;

    /**
     * 评价
     */
    private String evaluate;

    /**
     * 图片的id
     */
    private String picid;
    
    /**
     * 关联客户id
     */
    private Integer relatecustid;
    
    /**
     * 备用字段1
     */
    private String standby1;
    
    /**
     * 备用字段2
     */
    private String standby2;
    
    /**
     * 客服备用
     */
    private String custremark;
    
    private Integer replaceuserid;
    private String replaceusername;
    
	public Integer getReplaceuserid() {
		return replaceuserid;
	}

	public void setReplaceuserid(Integer replaceuserid) {
		this.replaceuserid = replaceuserid;
	}

	public String getReplaceusername() {
		return replaceusername;
	}

	public void setReplaceusername(String replaceusername) {
		this.replaceusername = replaceusername;
	}

	public String getStandby1() {
		return standby1;
	}

	public void setStandby1(String standby1) {
		this.standby1 = standby1;
	}

	public String getStandby2() {
		return standby2;
	}

	public void setStandby2(String standby2) {
		this.standby2 = standby2;
	}

	public String getCustremark() {
		return custremark;
	}

	public void setCustremark(String custremark) {
		this.custremark = custremark;
	}

	/**
     * 获取客户id
     *
     * @return custid - 客户id
     */
    public Integer getCustid() {
        return custid;
    }

    /**
     * 设置客户id
     *
     * @param custid 客户id
     */
    public void setCustid(Integer custid) {
        this.custid = custid;
    }

    /**
     * 获取联系人
     *
     * @return contact - 联系人
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系人
     *
     * @param contact 联系人
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取微信号
     *
     * @return weixin - 微信号
     */
    public String getWeixin() {
        return weixin;
    }

    /**
     * 设置微信号
     *
     * @param weixin 微信号
     */
    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取身份证
     *
     * @return idcard - 身份证
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 设置身份证
     *
     * @param idcard 身份证
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    /**
     * 获取公司名称
     *
     * @return companyname - 公司名称
     */
    public String getCompanyname() {
        return companyname;
    }

    /**
     * 设置公司名称
     *
     * @param companyname 公司名称
     */
    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    /**
     * 获取公司简介
     *
     * @return companyintro - 公司简介
     */
    public String getCompanyintro() {
        return companyintro;
    }

    /**
     * 设置公司简介
     *
     * @param companyintro 公司简介
     */
    public void setCompanyintro(String companyintro) {
        this.companyintro = companyintro;
    }

    /**
     * 获取区
     *
     * @return province - 区
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置区
     *
     * @param province 区
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市
     *
     * @return city - 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取详细地址
     *
     * @return address - 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详细地址
     *
     * @param address 详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取客户状态：1，正常；2，禁用
     *
     * @return custstatus - 客户状态：1，正常；2，禁用
     */
    public Integer getCuststatus() {
        return custstatus;
    }

    /**
     * 设置客户状态：1，正常；2，禁用
     *
     * @param custstatus 客户状态：1，正常；2，禁用
     */
    public void setCuststatus(Integer custstatus) {
        this.custstatus = custstatus;
    }

    /**
     * 获取关联业务员id
     *
     * @return saleid - 关联业务员id
     */
    public Integer getSaleid() {
        return saleid;
    }

    /**
     * 设置关联业务员id
     *
     * @param saleid 关联业务员id
     */
    public void setSaleid(Integer saleid) {
        this.saleid = saleid;
    }

    /**
     * @return createby
     */
    public Integer getCreateby() {
        return createby;
    }

    /**
     * @param createby
     */
    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    /**
     * 获取创建时间
     *
     * @return createdate - 创建时间
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置创建时间
     *
     * @param createdate 创建时间
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 获取更新人
     *
     * @return updateby - 更新人
     */
    public Integer getUpdateby() {
        return updateby;
    }

    /**
     * 设置更新人
     *
     * @param updateby 更新人
     */
    public void setUpdateby(Integer updateby) {
        this.updateby = updateby;
    }

    /**
     * @return updatedate
     */
    public Date getUpdatedate() {
        return updatedate;
    }

    /**
     * @param updatedate
     */
    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}

	public String getPicid() {
		return picid;
	}

	public void setPicid(String picid) {
		this.picid = picid;
	}

	public Integer getRelatecustid() {
		return relatecustid;
	}

	public void setRelatecustid(Integer relatecustid) {
		this.relatecustid = relatecustid;
	}

}
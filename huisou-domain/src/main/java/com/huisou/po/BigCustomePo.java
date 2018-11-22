package com.huisou.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "crm_big_customer")
public class BigCustomePo {
    /**
     * 大客户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bigid;

    /**
     * 联系人
     */
    private String bigcontact;

    /**
     * 手机号
     */
    private String bigphone;

    /**
     * 公司名称
     */
    private String bigcompanyname;

    /**
     * 区
     */
    private String bigprovince;

    /**
     * 市
     */
    private String bigcity;

    private String bigarea;

    /**
     * 详细地址
     */
    private String bigaddress;

    /**
     * 大客户状态：1，正常；2，禁用
     */
    private Integer bigcontstatus;

    /**
     * 大客户分配状态：1未分配，2已经分配
     */
    private Integer bigsalestatus;

    /**
     * 关联业务员id
     */
    private Integer saleid;
    
    
    /**
     * 关联业务员名字
     */
    private String salename;

    /**
     * 评价类型：1好;2一般，3差
     */
    private Integer evaluationtype;
   

    /**
     * 备注
     */
    private String bigremark;

    @Column(name = "createBy")
    private Integer createby;

    /**
     * 创建时间
     */
    @Column(name = "createDate")
    private Date createdate;

    /**
     * 更新人
     */
    @Column(name = "updateBy")
    private Integer updateby;

    @Column(name = "updateDate")
    private Date updatedate;

    /**
     * 备用字段
     */
    private String standby1;

    /**
     * 备用字段2
     */
    private String standby2;

    /**
     * 获取大客户id
     *
     * @return bigid - 大客户id
     */
    public Integer getBigid() {
        return bigid;
    }

    /**
     * 设置大客户id
     *
     * @param bigid 大客户id
     */
    public void setBigid(Integer bigid) {
        this.bigid = bigid;
    }

    /**
     * 获取联系人
     *
     * @return bigcontact - 联系人
     */
    public String getBigcontact() {
        return bigcontact;
    }

    /**
     * 设置联系人
     *
     * @param bigcontact 联系人
     */
    public void setBigcontact(String bigcontact) {
        this.bigcontact = bigcontact;
    }

    /**
     * 获取手机号
     *
     * @return bigphone - 手机号
     */
    public String getBigphone() {
        return bigphone;
    }

    /**
     * 设置手机号
     *
     * @param bigphone 手机号
     */
    public void setBigphone(String bigphone) {
        this.bigphone = bigphone;
    }

    /**
     * 获取公司名称
     *
     * @return bigcompanyname - 公司名称
     */
    public String getBigcompanyname() {
        return bigcompanyname;
    }

    /**
     * 设置公司名称
     *
     * @param bigcompanyname 公司名称
     */
    public void setBigcompanyname(String bigcompanyname) {
        this.bigcompanyname = bigcompanyname;
    }

    /**
     * 获取区
     *
     * @return bigprovince - 区
     */
    public String getBigprovince() {
        return bigprovince;
    }

    /**
     * 设置区
     *
     * @param bigprovince 区
     */
    public void setBigprovince(String bigprovince) {
        this.bigprovince = bigprovince;
    }

    /**
     * 获取市
     *
     * @return bigcity - 市
     */
    public String getBigcity() {
        return bigcity;
    }

    /**
     * 设置市
     *
     * @param bigcity 市
     */
    public void setBigcity(String bigcity) {
        this.bigcity = bigcity;
    }

    /**
     * @return bigarea
     */
    public String getBigarea() {
        return bigarea;
    }

    /**
     * @param bigarea
     */
    public void setBigarea(String bigarea) {
        this.bigarea = bigarea;
    }

    /**
     * 获取详细地址
     *
     * @return bigaddress - 详细地址
     */
    public String getBigaddress() {
        return bigaddress;
    }

    /**
     * 设置详细地址
     *
     * @param bigaddress 详细地址
     */
    public void setBigaddress(String bigaddress) {
        this.bigaddress = bigaddress;
    }

    /**
     * 获取大客户状态：1，正常；2，禁用
     *
     * @return bigcontstatus - 大客户状态：1，正常；2，禁用
     */
    public Integer getBigcontstatus() {
        return bigcontstatus;
    }

    /**
     * 设置大客户状态：1，正常；2，禁用
     *
     * @param bigcontstatus 大客户状态：1，正常；2，禁用
     */
    public void setBigcontstatus(Integer bigcontstatus) {
        this.bigcontstatus = bigcontstatus;
    }

    /**
     * 获取大客户分配状态：1未分配，2已经分配
     *
     * @return bigsalestatus - 大客户分配状态：1未分配，2已经分配
     */
    public Integer getBigsalestatus() {
        return bigsalestatus;
    }

    /**
     * 设置大客户分配状态：1未分配，2已经分配
     *
     * @param bigsalestatus 大客户分配状态：1未分配，2已经分配
     */
    public void setBigsalestatus(Integer bigsalestatus) {
        this.bigsalestatus = bigsalestatus;
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

    
    public String getSalename() {
		return salename;
	}

	public void setSalename(String salename) {
		this.salename = salename;
	}


	public Integer getEvaluationtype() {
		return evaluationtype;
	}

	public void setEvaluationtype(Integer evaluationtype) {
		this.evaluationtype = evaluationtype;
	}
	
	
    /**
     * 获取备注
     *
     * @return bigremark - 备注
     */
    public String getBigremark() {
        return bigremark;
    }

    /**
     * 设置备注
     *
     * @param bigremark 备注
     */
    public void setBigremark(String bigremark) {
        this.bigremark = bigremark;
    }

    /**
     * @return createBy
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
     * @return createDate - 创建时间
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
     * @return updateBy - 更新人
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
     * @return updateDate
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

    /**
     * 获取备用字段
     *
     * @return standby1 - 备用字段
     */
    public String getStandby1() {
        return standby1;
    }

    /**
     * 设置备用字段
     *
     * @param standby1 备用字段
     */
    public void setStandby1(String standby1) {
        this.standby1 = standby1;
    }

    /**
     * 获取备用字段2
     *
     * @return standby2 - 备用字段2
     */
    public String getStandby2() {
        return standby2;
    }

    /**
     * 设置备用字段2
     *
     * @param standby2 备用字段2
     */
    public void setStandby2(String standby2) {
        this.standby2 = standby2;
    }  
    
}
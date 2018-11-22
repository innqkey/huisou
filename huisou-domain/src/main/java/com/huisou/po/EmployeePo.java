package com.huisou.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "crm_employee")
public class EmployeePo {
    /**
     * 员工id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empid;

    /**
     * 名称
     */
    private String empname;

    /**
     * 手机号
     */
    private String empphone;

    /**
     * 身份证
     */
    private String empidcard;

    /**
     * 微信号
     */
    private String empweixin;

    /**
     * 邮箱
     */
    private String empemail;

    /**
     * qq号
     */
    private String empqq;

    /**
     * 入职日期
     */
    private Date entrydate;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 职务
     */
    private String duty;

    /**
     * 地址
     */
    private String address;

    /**
     * 籍贯
     */
    private String nativeplace;

    /**
     * 员工状态：1在岗;2离职;3出差;4请假
     */
    private Integer empstatus;

    /**
     * 紧急联系人
     */
    private String urgentcont;

    /**
     * 紧急联系人手机号
     */
    private String urgentcontphone;

    /**
     * 备注
     */
    private String empremark;

    /**
     * 部门id(备用)
     */
    private Integer depid;

    /**
     * 创建人
     */
    private Integer createby;

    /**
     * 创建时间
     */
    private Date createdate;

    /**
     * 更新人
     */
    private Integer updateby;

    /**
     * 更新时间
     */
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
     * 获取员工id
     *
     * @return empid - 员工id
     */
    public Integer getEmpid() {
        return empid;
    }

    /**
     * 设置员工id
     *
     * @param empid 员工id
     */
    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    /**
     * 获取名称
     *
     * @return empname - 名称
     */
    public String getEmpname() {
        return empname;
    }

    /**
     * 设置名称
     *
     * @param empname 名称
     */
    public void setEmpname(String empname) {
        this.empname = empname;
    }

    /**
     * 获取手机号
     *
     * @return empphone - 手机号
     */
    public String getEmpphone() {
        return empphone;
    }

    /**
     * 设置手机号
     *
     * @param empphone 手机号
     */
    public void setEmpphone(String empphone) {
        this.empphone = empphone;
    }

    /**
     * 获取身份证
     *
     * @return empidcard - 身份证
     */
    public String getEmpidcard() {
        return empidcard;
    }

    /**
     * 设置身份证
     *
     * @param empidcard 身份证
     */
    public void setEmpidcard(String empidcard) {
        this.empidcard = empidcard;
    }

    /**
     * 获取微信号
     *
     * @return empweixin - 微信号
     */
    public String getEmpweixin() {
        return empweixin;
    }

    /**
     * 设置微信号
     *
     * @param empweixin 微信号
     */
    public void setEmpweixin(String empweixin) {
        this.empweixin = empweixin;
    }

    /**
     * 获取邮箱
     *
     * @return empemail - 邮箱
     */
    public String getEmpemail() {
        return empemail;
    }

    /**
     * 设置邮箱
     *
     * @param empemail 邮箱
     */
    public void setEmpemail(String empemail) {
        this.empemail = empemail;
    }

    /**
     * 获取qq号
     *
     * @return empqq - qq号
     */
    public String getEmpqq() {
        return empqq;
    }

    /**
     * 设置qq号
     *
     * @param empqq qq号
     */
    public void setEmpqq(String empqq) {
        this.empqq = empqq;
    }

    /**
     * 获取入职日期
     *
     * @return entrydate - 入职日期
     */
    public Date getEntrydate() {
        return entrydate;
    }

    /**
     * 设置入职日期
     *
     * @param entrydate 入职日期
     */
    public void setEntrydate(Date entrydate) {
        this.entrydate = entrydate;
    }

    /**
     * 获取生日
     *
     * @return birthday - 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取职务
     *
     * @return duty - 职务
     */
    public String getDuty() {
        return duty;
    }

    /**
     * 设置职务
     *
     * @param duty 职务
     */
    public void setDuty(String duty) {
        this.duty = duty;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取籍贯
     *
     * @return nativeplace - 籍贯
     */
    public String getNativeplace() {
        return nativeplace;
    }

    /**
     * 设置籍贯
     *
     * @param nativeplace 籍贯
     */
    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    /**
     * 获取员工状态：1在岗;2离职;3出差;4请假
     *
     * @return empstatus - 员工状态：1在岗;2离职;3出差;4请假
     */
    public Integer getEmpstatus() {
        return empstatus;
    }

    /**
     * 设置员工状态：1在岗;2离职;3出差;4请假
     *
     * @param empstatus 员工状态：1在岗;2离职;3出差;4请假
     */
    public void setEmpstatus(Integer empstatus) {
        this.empstatus = empstatus;
    }

    /**
     * 获取紧急联系人
     *
     * @return urgentcont - 紧急联系人
     */
    public String getUrgentcont() {
        return urgentcont;
    }

    /**
     * 设置紧急联系人
     *
     * @param urgentcont 紧急联系人
     */
    public void setUrgentcont(String urgentcont) {
        this.urgentcont = urgentcont;
    }

    /**
     * 获取紧急联系人手机号
     *
     * @return urgentcontphone - 紧急联系人手机号
     */
    public String getUrgentcontphone() {
        return urgentcontphone;
    }

    /**
     * 设置紧急联系人手机号
     *
     * @param urgentcontphone 紧急联系人手机号
     */
    public void setUrgentcontphone(String urgentcontphone) {
        this.urgentcontphone = urgentcontphone;
    }

    /**
     * 获取备注
     *
     * @return empremark - 备注
     */
    public String getEmpremark() {
        return empremark;
    }

    /**
     * 设置备注
     *
     * @param empremark 备注
     */
    public void setEmpremark(String empremark) {
        this.empremark = empremark;
    }

    /**
     * 获取部门id(备用)
     *
     * @return depid - 部门id(备用)
     */
    public Integer getDepid() {
        return depid;
    }

    /**
     * 设置部门id(备用)
     *
     * @param depid 部门id(备用)
     */
    public void setDepid(Integer depid) {
        this.depid = depid;
    }

    /**
     * 获取创建人
     *
     * @return createby - 创建人
     */
    public Integer getCreateby() {
        return createby;
    }

    /**
     * 设置创建人
     *
     * @param createby 创建人
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
     * 获取更新时间
     *
     * @return updatedate - 更新时间
     */
    public Date getUpdatedate() {
        return updatedate;
    }

    /**
     * 设置更新时间
     *
     * @param updatedate 更新时间
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
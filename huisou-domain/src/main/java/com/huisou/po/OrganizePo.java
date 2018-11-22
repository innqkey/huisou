package com.huisou.po;

import javax.persistence.*;

@Table(name = "crm_organize")
public class OrganizePo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orgid;
//
//    /**
//     * 用户id
//     */
//    private Integer userid;

    /**
     * 部门名称
     */
    private String departname;

    /**
     * 二叉树的左值
     */
    private Integer left;

    /**
     * 二叉树的右值
     */
    private Integer right;

    /**
     * 备用1
     */
    private String standby1;

    /**
     * 备用2 
     */
    private String standby2;

    public Integer getOrgid() {
        return orgid;
    }

    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }

//    /**
//     * 获取用户id
//     *
//     * @return userid - 用户id
//     */
//    public Integer getUserid() {
//        return userid;
//    }
//
//    /**
//     * 设置用户id
//     *
//     * @param userid 用户id
//     */
//    public void setUserid(Integer userid) {
//        this.userid = userid;
//    }

    /**
     * 获取部门名称
     *
     * @return departname - 部门名称
     */
    public String getDepartname() {
        return departname;
    }

    /**
     * 设置部门名称
     *
     * @param departname 部门名称
     */
    public void setDepartname(String departname) {
        this.departname = departname;
    }

    /**
     * 获取二叉树的左值
     *
     * @return left - 二叉树的左值
     */
    public Integer getLeft() {
        return left;
    }

    /**
     * 设置二叉树的左值
     *
     * @param left 二叉树的左值
     */
    public void setLeft(Integer left) {
        this.left = left;
    }

    /**
     * 获取二叉树的右值
     *
     * @return right - 二叉树的右值
     */
    public Integer getRight() {
        return right;
    }

    /**
     * 设置二叉树的右值
     *
     * @param right 二叉树的右值
     */
    public void setRight(Integer right) {
        this.right = right;
    }

    /**
     * 获取备用1
     *
     * @return standby1 - 备用1
     */
    public String getStandby1() {
        return standby1;
    }

    /**
     * 设置备用1
     *
     * @param standby1 备用1
     */
    public void setStandby1(String standby1) {
        this.standby1 = standby1;
    }

    /**
     * 获取备用2 
     *
     * @return standby2 - 备用2 
     */
    public String getStandby2() {
        return standby2;
    }

    /**
     * 设置备用2 
     *
     * @param standby2 备用2 
     */
    public void setStandby2(String standby2) {
        this.standby2 = standby2;
    }
}
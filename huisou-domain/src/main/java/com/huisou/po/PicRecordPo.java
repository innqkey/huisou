package com.huisou.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "crm_pic_record")
public class PicRecordPo {
    /**
     * 图片记录id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer picid;

    /**
     * 关联项目id
     */
    private Integer itemid;

    /**
     * 图片类型：1身份证;2合同，3营业执照 4.发票的照片
     */
    private Integer pictype;

    /**
     * 图片状态：1正常;2删除
     */
    private Integer picstatus;

    /**
     * 图片地址
     */
    private String picurl;

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
     * custid
     */
    private Integer custid;

    /**
     *  图片关联的类型
     */
    private String stemfrom;

    /**
     *  图片关联的类型记录id
     */
    private Integer fromid;

    public Integer getCustid() {
		return custid;
	}

	public void setCustid(Integer custid) {
		this.custid = custid;
	}

	/**
     * 获取图片记录id
     *
     * @return picid - 图片记录id
     */
    public Integer getPicid() {
        return picid;
    }

    /**
     * 设置图片记录id
     *
     * @param picid 图片记录id
     */
    public void setPicid(Integer picid) {
        this.picid = picid;
    }

    /**
     * 获取关联项目id
     *
     * @return itemid - 关联项目id
     */
    public Integer getItemid() {
        return itemid;
    }

    /**
     * 设置关联项目id
     *
     * @param itemid 关联项目id
     */
    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    /**
     * 获取图片类型：1身份证;2合同，3营业执照
     *
     * @return pictype - 图片类型：1身份证;2合同，3营业执照
     */
    public Integer getPictype() {
        return pictype;
    }

    /**
     * 设置图片类型：1身份证;2合同，3营业执照
     *
     * @param pictype 图片类型：1身份证;2合同，3营业执照
     */
    public void setPictype(Integer pictype) {
        this.pictype = pictype;
    }

    /**
     * 获取图片状态：1正常;2删除
     *
     * @return picstatus - 图片状态：1正常;2删除
     */
    public Integer getPicstatus() {
        return picstatus;
    }

    /**
     * 设置图片状态：1正常;2删除
     *
     * @param picstatus 图片状态：1正常;2删除
     */
    public void setPicstatus(Integer picstatus) {
        this.picstatus = picstatus;
    }

    /**
     * 获取图片地址
     *
     * @return picurl - 图片地址
     */
    public String getPicurl() {
        return picurl;
    }

    /**
     * 设置图片地址
     *
     * @param picurl 图片地址
     */
    public void setPicurl(String picurl) {
        this.picurl = picurl;
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

    public String getStemfrom() {
        return stemfrom;
    }

    public void setStemfrom(String stemfrom) {
        this.stemfrom = stemfrom;
    }

    public Integer getFromid() {
        return fromid;
    }

    public void setFromid(Integer fromid) {
        this.fromid = fromid;
    }
}
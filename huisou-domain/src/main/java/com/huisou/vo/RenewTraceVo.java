package com.huisou.vo;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import com.common.DateUtils;
import com.huisou.constant.DictConConstant;


public class RenewTraceVo {
    /**
     * 项目id
     */
 
	private Integer traceid;
	
    @NotEmpty(message = "项目的id不能为空")
    private Integer itemid;

    private Integer visitway;
    
    //续费意向：1-差；2一般；3中等；4良好；5优
    private Integer renewintention;
    
    //客户回访态度：1-差；2一般；3中等；4良好；5优
    private Integer custvistiattitude;
    
    //客户评价：1-差；2一般；3中等；4良好；5优
    private Integer custevaluate;
    
    //客户是否有新项目：无/有
    private Integer custnewitem;
    
    //客户倾向项目类型（项目类型）
    private Integer custitemtype;
    //汇搜云权限
    private Integer itemtypedetail;
    //汇搜云类型名称
    private String custitemName;
    //客服评价（是否优质客户）：1-差；2一般；3中等；4良好；5优
    private Integer serviceevaluate;
    //是否续费
    private Integer custrenewstatus;
	
    private String itemname;
    private String contact;
    private String visitwayName;
    
    private String renewintentionName;
    private String custvistiattitudeName;
    private String custitemtypeName;
    private String custnewitemName;
    private String custrenewstatusName;
    private String custevaluateName;
    private String serviceevaluateName;
    
    private String petname;
    
    
	public Integer getItemtypedetail() {
		return itemtypedetail;
	}

	public void setItemtypedetail(Integer itemtypedetail) {
		this.itemtypedetail = itemtypedetail;
	}

	public String getCustitemName() {
		return DictConConstant.getDicName("itemDetailType", itemtypedetail);
	}

	public void setCustitemName(String custitemName) {
		this.custitemName = custitemName;
	}

	/**
     * 项目开始时间
     */
    private String  renewitembegindate;

    /**
     * 项目结束时间
     */
    private String renewitemenddate;

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
     * 续费时间
     */
    private String renewdate;
    
    /**
     * 客户跟踪备注：说明客户不续费原因、续费意向等
     */
    private String tracedesc;

    
	public String getRenewitembegindate() {
		if(StringUtils.isBlank(renewitembegindate)){
			return "";
		}
		try {
			String string = DateUtils.format(renewitembegindate, DateUtils.Y_M_D_HMS, "yyyy-MM-dd");
			return string;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public void setRenewitembegindate(String renewitembegindate) {
		this.renewitembegindate = renewitembegindate;
	}

	public String getRenewitemenddate() {
		if(StringUtils.isBlank(renewitemenddate)){
			return "";
		}
		try {
			String string = DateUtils.format(renewitemenddate, DateUtils.Y_M_D_HMS, "yyyy-MM-dd");
			return string;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public void setRenewitemenddate(String renewitemenddate) {
		this.renewitemenddate = renewitemenddate;
	}

	public String getRenewdate() {
		if(StringUtils.isBlank(renewdate)){
			return "";
		}
		try {
			String string = DateUtils.format(renewdate, DateUtils.Y_M_D_HMS, "yyyy-MM-dd");
			return string;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public void setRenewdate(String renewdate) {
		this.renewdate = renewdate;
	}

	public String getPetname() {
		return petname;
	}

	public void setPetname(String petname) {
		this.petname = petname;
	}

	public Integer getTraceid() {
		return traceid;
	}

	public void setTraceid(Integer traceid) {
		this.traceid = traceid;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}


	public Integer getVisitway() {
		return visitway;
	}

	public void setVisitway(Integer visitway) {
		this.visitway = visitway;
	}

	public Integer getRenewintention() {
		return renewintention;
	}

	public void setRenewintention(Integer renewintention) {
		this.renewintention = renewintention;
	}

	public Integer getCustvistiattitude() {
		return custvistiattitude;
	}

	public void setCustvistiattitude(Integer custvistiattitude) {
		this.custvistiattitude = custvistiattitude;
	}

	public Integer getCustevaluate() {
		return custevaluate;
	}

	public void setCustevaluate(Integer custevaluate) {
		this.custevaluate = custevaluate;
	}

	public Integer getCustnewitem() {
		return custnewitem;
	}

	public void setCustnewitem(Integer custnewitem) {
		this.custnewitem = custnewitem;
	}

	public Integer getCustitemtype() {
		return custitemtype;
	}

	public void setCustitemtype(Integer custitemtype) {
		this.custitemtype = custitemtype;
	}

	public Integer getServiceevaluate() {
		return serviceevaluate;
	}

	public void setServiceevaluate(Integer serviceevaluate) {
		this.serviceevaluate = serviceevaluate;
	}

	public Integer getCustrenewstatus() {
		return custrenewstatus;
	}

	public void setCustrenewstatus(Integer custrenewstatus) {
		this.custrenewstatus = custrenewstatus;
	}

	

	public Integer getCreateby() {
		return createby;
	}

	public void setCreateby(Integer createby) {
		this.createby = createby;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Integer getUpdateby() {
		return updateby;
	}

	public void setUpdateby(Integer updateby) {
		this.updateby = updateby;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}


	public String getTracedesc() {
		return tracedesc;
	}

	public void setTracedesc(String tracedesc) {
		this.tracedesc = tracedesc;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getVisitwayName() {
		return DictConConstant.getDicName("VisitWay", this.visitway);
	}

	public void setVisitwayName(String visitwayName) {
		this.visitwayName = visitwayName;
	}

	public String getRenewintentionName() {
		return DictConConstant.getDicName("Evaluatetype", this.renewintention);
	}

	public void setRenewintentionName(String renewintentionName) {
		this.renewintentionName = renewintentionName;
	}

	public String getCustvistiattitudeName() {
		return  DictConConstant.getDicName("Evaluatetype", this.custvistiattitude);
	}

	public void setCustvistiattitudeName(String custvistiattitudeName) {
		this.custvistiattitudeName = custvistiattitudeName;
	}

	public String getCustitemtypeName() {
		return  DictConConstant.getDicName("Itemtype", this.custitemtype);
	}

	public void setCustitemtypeName(String custitemtypeName) {
		this.custitemtypeName = custitemtypeName;
	}

	public String getCustnewitemName() {
		return  DictConConstant.getDicName("YesOrNoType", this.custnewitem);
	}

	public void setCustnewitemName(String custnewitemName) {
		this.custnewitemName = custnewitemName;
	}

	public String getCustrenewstatusName() {
		return DictConConstant.getDicName("YesOrNoType", this.custrenewstatus);
	}

	public void setCustrenewstatusName(String custrenewstatusName) {
		this.custrenewstatusName = custrenewstatusName;
	}

	public String getCustevaluateName() {
		return DictConConstant.getDicName("Evaluatetype", this.custevaluate);
	}

	public void setCustevaluateName(String custevaluateName) {
		this.custevaluateName = custevaluateName;
	}

	public String getServiceevaluateName() {
		return DictConConstant.getDicName("Evaluatetype", this.serviceevaluate);
	}

	public void setServiceevaluateName(String serviceevaluateName) {
		this.serviceevaluateName = serviceevaluateName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
    
}
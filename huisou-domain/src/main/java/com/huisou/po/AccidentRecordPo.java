package com.huisou.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "crm_accident_record")
public class AccidentRecordPo {
    /**
     * 事故记录id
     */
    @Id
    private Integer accidentid;

    /**
     * 项目名称
     */
    private String itemname;

    /**
     * 事故描述
     */
    private String description;

    /**
     * 事故起因
     */
    private String cause;

    /**
     * 解决方案
     */
    private String solution;

    /**
     * 是否首次: 0：是；1 否
     */
    private Integer status;

    /**
     * 部门id
     */
    private Integer department;

    /**
     * 事故责任人
     */
    private String responsible;

    /**
     * 事故小组负责人
     */
    private String groupresponsible;

    /**
     * 事故发生时间
     */
    private Date time;

    /**
     * 备用字段
     */
    private String standby1;

    /**
     * 备用字段2
     */
    private String standby2;
    
    
    /**
     * 关联项目id
     */
    private Integer itemid;
    /**
     * 是否解决0：是；1 否
     */
    private Integer solve;
    /**
     * 责任人id
     */
    private Integer responsibleid;
    /**
     * 小组负责人id
     */
    private Integer groupresid;
    
    

    public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public Integer getSolve() {
		return solve;
	}

	public void setSolve(Integer solve) {
		this.solve = solve;
	}

	public Integer getResponsibleid() {
		return responsibleid;
	}

	public void setResponsibleid(Integer responsibleid) {
		this.responsibleid = responsibleid;
	}

	public Integer getGroupresid() {
		return groupresid;
	}

	public void setGroupresid(Integer groupresid) {
		this.groupresid = groupresid;
	}

	
	/**
     * 获取事故记录id
     *
     * @return accidentid - 事故记录id
     */
    public Integer getAccidentid() {
        return accidentid;
    }

    /**
     * 设置事故记录id
     *
     * @param accidentid 事故记录id
     */
    public void setAccidentid(Integer accidentid) {
        this.accidentid = accidentid;
    }

    /**
     * 获取项目名称
     *
     * @return itemname - 项目名称
     */
    public String getItemname() {
        return itemname;
    }

    /**
     * 设置项目名称
     *
     * @param itemname 项目名称
     */
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    /**
     * 获取事故描述
     *
     * @return description - 事故描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置事故描述
     *
     * @param description 事故描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取事故起因
     *
     * @return cause - 事故起因
     */
    public String getCause() {
        return cause;
    }

    /**
     * 设置事故起因
     *
     * @param cause 事故起因
     */
    public void setCause(String cause) {
        this.cause = cause;
    }

    /**
     * 获取解决方案
     *
     * @return solution - 解决方案
     */
    public String getSolution() {
        return solution;
    }

    /**
     * 设置解决方案
     *
     * @param solution 解决方案
     */
    public void setSolution(String solution) {
        this.solution = solution;
    }

    /**
     * 获取是否首次: 0：是；1 否
     *
     * @return status - 是否首次: 0：是；1 否
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否首次: 0：是；1 否
     *
     * @param status 是否首次: 0：是；1 否
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取部门id
     *
     * @return department - 部门id
     */
    public Integer getDepartment() {
        return department;
    }

    /**
     * 设置部门id
     *
     * @param department 部门id
     */
    public void setDepartment(Integer department) {
        this.department = department;
    }

    /**
     * 获取事故责任人
     *
     * @return responsible - 事故责任人
     */
    public String getResponsible() {
        return responsible;
    }

    /**
     * 设置事故责任人
     *
     * @param responsible 事故责任人
     */
    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    /**
     * 获取事故小组负责人
     *
     * @return groupresponsible - 事故小组负责人
     */
    public String getGroupresponsible() {
        return groupresponsible;
    }

    /**
     * 设置事故小组负责人
     *
     * @param groupresponsible 事故小组负责人
     */
    public void setGroupresponsible(String groupresponsible) {
        this.groupresponsible = groupresponsible;
    }

    /**
     * 获取事故发生时间
     *
     * @return time - 事故发生时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置事故发生时间
     *
     * @param time 事故发生时间
     */
    public void setTime(Date time) {
        this.time = time;
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
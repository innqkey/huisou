package com.huisou.po;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="lp_regions")
public class RegionPo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2038986092620583758L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer parent;
	private String path;
	private Integer grade;
	private String name;
	private String first_word;
	private String enabled;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirst_word() {
		return first_word;
	}
	public void setFirst_word(String first_word) {
		this.first_word = first_word;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return "RegionPo [id=" + id + ", parent=" + parent + ", path=" + path
				+ ", grade=" + grade + ", name=" + name + ", first_word="
				+ first_word + ", enabled=" + enabled + "]";
	}
	
	
}

package com.huisou.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.huisou.po.InviteRecordPo;
import com.huisou.vo.InviteRecordVo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午4:44:47 
* 类说明 
*/
public interface InviteRecordService {
	
	/**
	 * 根据意向客户id获取该客户的最大应约次数（如果之前没有则返回0）
	 * @param intentioncustid
	 * @return
	 */
	Integer getInviteidcount(Integer intentioncustid);

	void addInviteRecord(InviteRecordPo inviteRecordPo);

	PageInfo<InviteRecordVo> getInviteRecordList(String customerName, String companyname,String petname,
			String coursename, String startDate, String endDate, boolean leader, Integer createby,PageTemp pageTemp);

	void update(Integer inviterecordid,Integer courseid);

	InviteRecordVo getDetail(int inviterecordid);

}

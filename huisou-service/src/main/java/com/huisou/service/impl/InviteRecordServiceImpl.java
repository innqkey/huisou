package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.InviteRecordPoMapper;
import com.huisou.po.InviteRecordPo;
import com.huisou.service.InviteRecordService;
import com.huisou.vo.InviteRecordVo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午4:46:18 
* 类说明 
*/
@Service
public class InviteRecordServiceImpl implements InviteRecordService{

	@Autowired
	private InviteRecordPoMapper inviteRecordPoMapper;
	
	@Override
	public Integer getInviteidcount(Integer intentioncustid) {
		// TODO Auto-generated method stub
		Integer inviteidcount = 0;
		InviteRecordPo inviteRecordPo = inviteRecordPoMapper.getInviteidcountMax(intentioncustid);
		if(inviteRecordPo!=null){
			inviteidcount = inviteRecordPo.getInviteidcount();
		}
		return inviteidcount;
	}

	@Override
	public void addInviteRecord(InviteRecordPo inviteRecordPo) {
		// TODO Auto-generated method stub
		inviteRecordPoMapper.insert(inviteRecordPo);
	}

	@Override
	public PageInfo<InviteRecordVo> getInviteRecordList(String name, String companyname,
			String petname, String coursename, String startDate, String endDate, boolean leader, Integer createby,PageTemp pageTemp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<InviteRecordVo> list = inviteRecordPoMapper.getInviteRecordByUserid(name,companyname,petname,coursename,startDate,endDate,leader,createby);
		return new PageInfo<>(list);
	}

	@Override
	public void update(Integer inviterecordid, Integer courseid) {
		// TODO Auto-generated method stub
		inviteRecordPoMapper.update(inviterecordid,courseid);
	}

	@Override
	public InviteRecordVo getDetail(int inviterecordid) {
		// TODO Auto-generated method stub
		InviteRecordVo inviteRecordVo = inviteRecordPoMapper.getDetail(inviterecordid);
		return inviteRecordVo;
	}


}

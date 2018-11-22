package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.huisou.po.InviteRecordPo;
import com.huisou.vo.InviteRecordVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2017年12月26日 下午3:03:43 
* 类说明 
*/
public interface InviteRecordPoMapper extends MyMapper<InviteRecordPo>{

	InviteRecordPo getInviteidcountMax(@Param("intentioncustid")Integer intentioncustid);

	List<InviteRecordVo> getInviteRecordByUserid(@Param("name")String name, @Param("companyname")String companyname, @Param("petname")String petname,
			@Param("coursename")String coursename, @Param("startDate")String startDate,  @Param("endDate")String endDate, @Param("leader")boolean leader, @Param("createby")Integer createby);

	void update(@Param("inviterecordid")Integer inviterecordid, @Param("courseid")Integer courseid);

	InviteRecordVo getDetail(@Param("inviterecordid")int inviterecordid);

}

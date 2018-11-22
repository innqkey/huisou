package com.huisou.service;

import java.util.List;

import com.huisou.po.IntentionItemPo;
import com.huisou.vo.ItemCourseVo;

/** 
* @author qinkai 
* @date 2017年12月26日
*/

public interface IntentionItemService {

	/**
	 * 保存一条新增项目
	 * @param name
	 * @param type
	 * @param custid
	 * @param userId
	 * @param courseId
	 */
	void saveItem(String name, Integer type, Integer custid, Integer userid, Integer courseid);

	/**
	 * 根据客户id 和 课程id 查找项目
	 * @param intentioncustid
	 * @param courseid
	 * @param userId 
	 * @return
	 */
	List<ItemCourseVo> selectByParams(int intentioncustid, int courseid, int userId);

	/**
	 * 根据销售id,客户id,修改意向客户的课程id
	 * @param courseid
	 * @param intentioncustid
	 * @param userId
	 */
	void updateItemByCourseid(int courseid, int intentioncustid, int userid);

}

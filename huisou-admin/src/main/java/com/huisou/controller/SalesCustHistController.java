package com.huisou.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.service.SalesCustHistoryService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.SaleCustHistUserVo;

@RestController
@RequestMapping("/salesCustHistController")
public class SalesCustHistController  extends BaseController {

	@Autowired
	private SalesCustHistoryService salesCustHistoryService;
	
	@RequestMapping(value = "/histList")
    public String listMap(HttpServletRequest request, PageTemp page) throws IllegalAccessException, InstantiationException {
        String searchValue = request.getParameter("searchValue");
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
		PageInfo<SaleCustHistUserVo> list = salesCustHistoryService.findCustomerHistUserList(page, searchValue, beginDate, endDate);
        return ResUtils.okRes(list);
    }
}

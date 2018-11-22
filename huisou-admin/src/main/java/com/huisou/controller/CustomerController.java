package com.huisou.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.Table;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.common.ConvertUtil;
import com.common.DateUtil;
import com.common.DateUtils;
//import com.common.JdbcSystemUtils;
import com.common.ResUtils;
import com.common.UploadUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.po.CustomerPo;
import com.huisou.po.ItemsPo;
import com.huisou.po.NoticePo;
import com.huisou.po.PicRecordPo;
import com.huisou.po.RegionPo;
import com.huisou.po.SalesCustHistoryPo;
import com.huisou.po.SalesManPo;
import com.huisou.po.TradeCecordPo;
import com.huisou.po.UniversalPo;
import com.huisou.po.UserPo;
import com.huisou.service.CustomerService;
import com.huisou.service.ItemService;
import com.huisou.service.NoticeService;
import com.huisou.service.PicRecordService;
import com.huisou.service.RegionService;
import com.huisou.service.SaleRemSetService;
import com.huisou.service.SalesCustHistoryService;
import com.huisou.service.SalesmanService;
import com.huisou.service.ServiceItemService;
import com.huisou.service.TradeRecordService;
import com.huisou.vo.CustomerInfoVo;
import com.huisou.vo.CustomerVO;
import com.huisou.vo.ItemCustSaleVo;
import com.huisou.vo.ItemDetailVo;
import com.huisou.vo.ItemVo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.SaleCustHistoryVo;
import com.huisou.vo.TradeCecordVo;

@RequestMapping(value = "/customer")
@RestController
public class CustomerController extends BaseController {

	private  static  final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private CustomerService customerService;

	@Autowired
	private PicRecordService picRecordSerice;

	@Autowired
	private SalesCustHistoryService salesCustHistoryService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private TradeRecordService tradeRecordService;
	@Autowired
	private SalesmanService salemanService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Value(value = "${image.url}")
	private String imageUrl;
	@Value(value = "${image.prefix}")
	private String imagePrefix;
	@Value(value="${doc.prefix}")
	private String docPrefix;
	
	
	@Autowired
	private RegionService regionService;

	@Autowired
	private ServiceItemService serviceItem;
	
	@Autowired
	private SaleRemSetService remSetSer;
	
	/**
	 * 用于项目待处理标记
	 */
	@RequestMapping(value="/signPendingItem")
	public String signPendingItem(HttpServletRequest request,@RequestParam("itemIds[]") List<Integer> itemids,String markstatus){
		
		if(itemids.size()<1||StringUtils.isBlank(markstatus)){
			return ResUtils.exceCode;
		}
		if(!(markstatus.equals("0")||markstatus.equals("1"))){
			return ResUtils.exceCode;
		}
		itemService.markStatus(itemids,markstatus);
		return ResUtils.okRes();
		
	}
	
	/**
	 * 用于首页服务列表，代结尾款，预付费客户数量的显示
	 */
	@RequestMapping(value = "/countByItemTradetype")
	public String  countByItemTradetype(HttpServletRequest request,String tradetype,String pendingflag){
		
		SalesManPo sale = getSale(request);
		boolean leader = getLeader(request);
		Integer countInteger=customerService.countByItemTradetype(tradetype,sale,leader,request.getParameter("beginDate"),request.getParameter("endDate"),pendingflag);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("count", countInteger);
		return ResUtils.okRes(map);		
	}
	
	/**
	 * 用于项目文档的下载
	 */
	@RequestMapping(value = "/docDonwload/{dir}/{docName}")
	public String docDonwload(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("dir") String dir,
			@PathVariable("docName") String docName) {
		logger.info("dir" + dir + "----------docName" + docName);
		String requestURI = request.getRequestURI();
		String suffix = requestURI.substring(requestURI.lastIndexOf(".")+1);
		if (StringUtils.isBlank(docName)) {
			return ResUtils.execRes();
		}
		OutputStream outputStream = null;
		FileInputStream fileInputStream = null;
		try {
		docName = docName + "." + suffix;
		String url = imageUrl + dir + "/" + docName;
	
			fileInputStream = new FileInputStream(url);
			logger.info(docName);
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(docName.getBytes(), "iso-8859-1"));
			response.setContentType("application/octet-stream");
			outputStream = response.getOutputStream();
			int available = fileInputStream.available();
			byte[] data = new byte[available];
			int read = fileInputStream.read(data);
			outputStream.write(data);
			outputStream.flush();
			return ResUtils.okRes();
		} catch (IOException e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}

	/**
	 * 用于返回对应的省市区的数据
	 */
	@RequestMapping(value = "/getregion")
	public String getRegion(HttpServletRequest request, String flag,
			Integer regionId) {
		List<RegionPo> result = null;
		if (flag == null) {
			result = regionService.findAllProvince();
			return ResUtils.okRes(result);
		}
		// 为1就是返回所有的省
		if (flag.equals("1") || regionId == null) {
			result = regionService.findAllProvince();
		} else if (flag.equals("2") && regionId != null) {
			// 为2的话根据id查询对应的市或者区。
			result = regionService.findCityOrAreaByParentId(regionId);
		} else if (flag.equals("3") && regionId != null) {
			RegionPo regionById = regionService.findRegionById(String.valueOf(regionId));
			return ResUtils.okRes(regionById);
		}

		return ResUtils.okRes(result);

	}

	/**
	 * 用来真删除添加项目的时候的图片
	 * 
	 * @param requeset
	 * @return
	 */
	@RequestMapping(value = "/realdelete")
	public String realDeleteImage(HttpServletRequest request, String imageName) {
		String[] split = imageName.split("/");
		int length = split.length;
		imageName = split[length - 1];
		int useid = getUserId(request);
		File file = new File(imageUrl + useid + "/" + imageName);
		if (file.exists()) {
			file.delete();
		}
		return ResUtils.okRes();

	}

	/**
	 * 用来保存图片的上传的路径
	 */
	@RequestMapping(value = "/uploadimage", method = RequestMethod.POST)
	public String uploadImage(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, Integer imageType) {
		if (!file.isEmpty()) {
			// 保存的目录使用userid
			int userId = getUserId(request);
			// 保存图片到对应的服务器中去
			String imagename = UploadUtils.uploadimage(file, imageUrl, userId);
			if (imagename.startsWith("70") && imagename.length() < 4) {
				return ResUtils.execRes(imagename);
			}
			HashMap<String, Object> map = new HashMap<String, Object>();
			// 访问图片的路径
			String url = imagePrefix + userId + "/" + imagename;
			map.put("imageurl", url);
			map.put("imageType", imageType);
			return ResUtils.okRes(map);
		} else {
			return ResUtils.errRes("102", "文件不能为空");
		}

	}

	@RequestMapping(value = "/uploadRequireDoc", method = RequestMethod.POST)
	public String uploadrequiredoc(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		if (!file.isEmpty()) {
			// 保存的目录使用userid
			int userId = getUserId(request);
			// 保存图片到对应的服务器中去
			String imagename = UploadUtils.uploaddoc(file, imageUrl, userId);
			if (imagename.startsWith("70") && imagename.length() < 4) {
				return ResUtils.execRes(imagename);
			}
			HashMap<String, Object> map = new HashMap<String, Object>();
			// 访问图片的路径
			String url = docPrefix + userId + "/" + imagename;
			map.put("docurl", url);
			map.put("docname", imagename);
			return ResUtils.okRes(map);
		} else {
			return ResUtils.errRes("102", "文件不能为空");
		}
	}

	/**
	 * 用来显示图片
	 */
	@RequestMapping(value = "/displayimage/{dir}/{image}")
	public String displayImage(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("image") String image,
			@PathVariable("dir") String dir) {

		String requestURI = request.getRequestURI();
		String suffix = requestURI.substring(requestURI.lastIndexOf(".")+1);
		if (StringUtils.isBlank(image) ) {
			return ResUtils.exceCode;
		}
		OutputStream outputStream = null;
		FileInputStream fileInputStream = null;
		try {

			String url = imageUrl + dir + "/" + image + "." + suffix;
			// 度图片
			fileInputStream = new FileInputStream(url);
			response.setContentType("image/png");
			outputStream = response.getOutputStream();
			int available = fileInputStream.available();
			byte[] data = new byte[available];
			int read = fileInputStream.read(data);
			outputStream.write(data);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.exceCode;
		} finally {
			try {
				if (fileInputStream != null)
					fileInputStream.close();
				if (outputStream != null)
					outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				return ResUtils.exceCode;
			}
		}
		return ResUtils.okRes();
	}
	

	/**
	 * 用于修改的页面的保存
	 * 
	 * @throws Exception
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "/update/save", method = RequestMethod.POST)
	public String updateSave(
			HttpServletRequest request,
			@RequestParam(required = false, value = "IDImageUrl[]") List<String> IDImageUrl,
			@RequestParam(required = false, value = "contractImageUrl[]") List<String> contractImageUrl,
			@RequestParam(required = false, value = "licenseImageUrl[]") List<String> licenseImageUrl,
			@RequestParam(required = false, value = "licenseImageUrl[]") List<String> invoiceImageUrl,
			@RequestParam(required = false, value = "itemdocUrl[]") List<String> itemdocUrl

	) throws Exception {
		List<Integer> picids = null;
		CustomerPo customerPo = new CustomerPo();
		ItemVo itemVo = new ItemVo();
		Map<String, String[]> parameterMap = request.getParameterMap();
		registerConvert();
		BeanUtils.copyProperties(customerPo, parameterMap);
		BeanUtils.copyProperties(itemVo, parameterMap);
		ItemsPo itemsPo = new ItemsPo();
		//只有是阿凡提、会搜云才更新项目进度
		if(itemVo.getItemtype()<6){
			itemVo.setRelatecontstatus(null);
		}
		BeanUtils.copyProperties(itemsPo, itemVo);
		removeRegister();
		if (customerPo == null || itemsPo == null) {
			return ResUtils.errRes("102", "请求参数错误");
		}
		if (itemsPo.getCustid() == null || itemsPo.getSaleid() == null
				|| itemsPo.getItemid() == null) {
			return ResUtils.errRes("102", "请求参数错误");
		}
		if (null != itemsPo && itemsPo.getItemtype() != 7){
			itemsPo.setItemtypedetail(0);
		}
		// 判断是否是app
		String appSign = request.getParameter("appSign");
		if (appSign != null && appSign.equals("1")) {
			String saveInfo = customerService.updateInfo(customerPo, itemsPo,
					null, null, null, null, null, getSale(request), picids);
			if (saveInfo != null) {
				return ResUtils.execRes(saveInfo);
			}
			return ResUtils.okRes(saveInfo);
		}
		String saveInfo = customerService.updateInfo(customerPo, itemsPo,
				IDImageUrl, contractImageUrl, licenseImageUrl, invoiceImageUrl,
				itemdocUrl, getSale(request), picids);
		if (saveInfo != null) {
			return ResUtils.execRes(saveInfo);
		}
		return ResUtils.okRes(saveInfo);
	}

	/**
	 * 用于对应的修改页面的展示
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/index")
	public String UpdateIndex(HttpServletRequest request, Integer itemid)
			throws Exception {
		if (itemid == null)
			return ResUtils.errRes("102", "请求参数错误");
		ItemDetailVo itemDetailVo = itemService.findItemInfoByItemid(itemid);
		String province = StringUtils.isNotBlank(itemDetailVo.getProvince()) ? itemDetailVo
				.getProvince() : "-1";
		String city = StringUtils.isNotBlank(itemDetailVo.getCity()) ? itemDetailVo
				.getCity() : "-1";
		String area = StringUtils.isNotBlank(itemDetailVo.getArea()) ? itemDetailVo
				.getArea() : "-1";
				
		RegionPo proPo = regionService.findRegionById(province);
		RegionPo cityPo = regionService.findRegionById(city);
		RegionPo areaPo = regionService.findRegionById(area);
		String proName = null != proPo ? proPo.getName() : "";
		String cityName = null != cityPo ? cityPo.getName() : "";
		String areaName = null != areaPo ? areaPo.getName() : "";
		itemDetailVo.setProvincename(proName);
		itemDetailVo.setCityname(cityName);
		itemDetailVo.setAreaname(areaName);
		return ResUtils.okRes(itemDetailVo);
	}

	/**
	 * 用于显示首页的信息(已作废)
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, PageTemp pageTemp)
			throws Exception {
		PageInfo<CustomerVO> result = null;
		SalesManPo sale = getSale(request);
		boolean leader = getLeader(request);
		String tradetype = request.getParameter("tradetype");
		PageInfo<UniversalPo> pageInfo = customerService.findCustomer(
				pageTemp.getPageNum(), pageTemp.getPageSize(), leader, sale,
				tradetype);
		if (pageInfo == null) {
			return null;
		}
		List<UniversalPo> list = pageInfo.getList();
		if (list == null && list.size() < 0.8) {
			ResUtils.okRes(null);
		}
		List<CustomerVO> customerVOs = ConvertUtil.convertDtoAndVo(list,
				CustomerVO.class);
		result = (PageInfo<CustomerVO>) ConvertUtil.convertDtoAndVo(pageInfo,
				PageInfo.class);
		result.setList(customerVOs);
		return ResUtils.okRes(result);
	}

	@RequestMapping(value = "/destopIndex")
	public String destopIndex(HttpServletRequest request, PageTemp pageTemp) {
		SalesManPo sale = getSale(request);
		boolean leader = getLeader(request);
		String orderby = request.getParameter("orderby");
		String tradetype = request.getParameter("tradetype");
		String salename = request.getParameter("salename");
		String salenull = request.getParameter("salenull");
		String itemType = request.getParameter("itemType");
		String contractStatus = request.getParameter("contractStatus");
		String pendingflag = request.getParameter("pendingflag");
		PageInfo<UniversalPo> pageInfo = customerService.searchByCondition(
				null, null, request.getParameter("begindate"), request.getParameter("endDate"), null, tradetype, pageTemp, leader, sale,
				 null,null,orderby,salename,salenull,null,itemType,contractStatus,null,pendingflag);
		if (pageInfo == null) {
			return null;
		}
		List<UniversalPo> list = pageInfo.getList();
		if (list != null && list.size() < 0.5) {
			return ResUtils.okRes(null);
		}
		List<CustomerVO> customerVOs;
		PageInfo<CustomerVO> result;
		try {
			customerVOs = ConvertUtil.convertDtoAndVo(list, CustomerVO.class);
			result = (PageInfo<CustomerVO>) ConvertUtil.convertDtoAndVo(
					pageInfo, PageInfo.class);
			result.setList(customerVOs);
			return ResUtils.okRes(result);

		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.exceCode;
		}
	}

	/**
	 * 根据条件查询
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@RequestMapping(value = "/search")
	public String search(HttpServletRequest request, PageTemp pageTemp)
			throws InstantiationException, IllegalAccessException {
		PageInfo<CustomerVO> result = null;
		SalesManPo sale = getSale(request);
		boolean leader = getLeader(request);
		String searchType = request.getParameter("searchType");
		String value = request.getParameter("typevalue");
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		String custstatus = request.getParameter("custstatus");
		String begindate = request.getParameter("begindate");
		String endDate = request.getParameter("endDate");
		String tradetype = request.getParameter("tradetype");
		String isrenew = request.getParameter("isrenew");
		String phonetype = request.getParameter("phonetype");
		String salename = request.getParameter("salename");
		String salenull = request.getParameter("salenull");
		String overdue = request.getParameter("overdue");
		String itemType = request.getParameter("itemType");
		String contractStatus = request.getParameter("contractStatus");
		String sortCondition = request.getParameter("sortCondition");
		String pendingflag = request.getParameter("pendingflag");
		if(StringUtils.isNotBlank(phonetype)&&!phonetype.equals("1")){
			phonetype=null;
		}
		PageInfo<UniversalPo> pageInfo = customerService.searchByCondition(
				searchType, value, begindate, endDate, custstatus, tradetype,
				pageTemp, leader, sale, isrenew, getTimeSpan(request),phonetype,salename,salenull,overdue,itemType,contractStatus,sortCondition,pendingflag);
		if (pageInfo == null) {
			return null;
		}
		List<UniversalPo> list = pageInfo.getList();
		if (list != null && list.size() < 0.5) {
			return ResUtils.okRes(null);
		}
		List<CustomerVO> customerVOs = ConvertUtil.convertDtoAndVo(list,
				CustomerVO.class);
		result = (PageInfo<CustomerVO>) ConvertUtil.convertDtoAndVo(pageInfo,
				PageInfo.class);
		result.setList(customerVOs);
		return ResUtils.okRes(result);
	}

	/**
	 * 用于保存新添加的信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			HttpServletRequest request,
			@RequestParam(required = false, value = "IDImageUrl[]") List<String> IDImageUrl,
			@RequestParam(required = false, value = "contractImageUrl[]") List<String> contractImageUrl,
			@RequestParam(required = false, value = "licenseImageUrl[]") List<String> licenseImageUrl,
			@RequestParam(required = false, value = "licenseImageUrl[]") List<String> invoiceImageUrl,
			@RequestParam(required = false, value = "itemdocUrl[]") List<String> itemdocUrl)
			throws Exception {
		CustomerPo customerPo = new CustomerPo();
		ItemVo itemVo = new ItemVo();
		Map<String, String[]> parameterMap = request.getParameterMap();
		registerConvert();
		BeanUtils.copyProperties(customerPo, parameterMap);
		BeanUtils.copyProperties(itemVo, parameterMap);
		ItemsPo itemsPo = new ItemsPo();
		BeanUtils.copyProperties(itemsPo, itemVo);
		removeRegister();
		if (customerPo == null || itemsPo == null||customerPo.getSaleid()==null) {
			return ResUtils.errRes("102", "请求参数错误");
		}

		//判断用户和项目唯一性
		if(null==customerPo.getCustid()||customerPo.getCustid()==0){
//			boolean checkCust = customerService.checkCustByContAndPhone(customerPo.getContact(),customerPo.getPhone());
			boolean checkItem = customerService.checkItemByNameAndType(itemsPo.getItemname(),itemsPo.getItemtype(),itemsPo.getItemtypedetail());
			if(checkItem){
				return ResUtils.errRes("102", "项目全称与项目类型已经存在，不能添加！"); 
			}
		}
		Integer itemid =  customerService.saveInfo(customerPo, itemsPo, IDImageUrl,
				contractImageUrl, licenseImageUrl, invoiceImageUrl, itemdocUrl,
				getSale(request));
		NoticePo noticePo = new NoticePo();
		noticePo.setItemid(itemid);
		noticePo.setItemname(itemsPo.getItemname());
		noticePo.setNoticetype(ContextConstant.NOTICETYPE_ADDITEM);
		noticePo.setNoticemessage(itemsPo.getItemname());
		noticePo.setNoticeacceptype(ContextConstant.NOTICEACCEPTYPE_SERVICE);
		noticePo.setCreateby(super.getUserId(request));
		noticeService.insert(noticePo);
		return ResUtils.okRes();
	}

	@RequestMapping(value = "/deleteImage")
	public String deleteImage(HttpServletRequest request, Integer picid,
			Integer pictype) {
		if (picid == null) {
			return ResUtils.errRes("102", "请求参数错误");
		}
		picRecordSerice.deleteImage(picid, pictype);
		return ResUtils.okRes();

	}

	@RequestMapping(value = "/changeSaleman")
	public String changeSaleman(HttpServletRequest request,
			SalesManPo salesManPo, SalesCustHistoryPo salesCustHistoryPo) {
		String custid = request.getParameter("custid");
		if (salesCustHistoryPo == null
				|| salesCustHistoryPo.getEndsaleid() == null
				|| StringUtils.isBlank(custid)) {
			return ResUtils.errRes("102", "请求参数异常");
		} else {
			salesManPo.setSaleid(salesCustHistoryPo.getEndsaleid());
			salesCustHistoryPo.setCreatedate(new Date());
			salesCustHistoryPo.setCreateby(getSaleId(request));
			if(null==salesCustHistoryPo.getBeforesaleid()){
				salesCustHistoryPo.setBeforesaleid(0);
			}
			salesCustHistoryService.save(salesCustHistoryPo);
			itemService.updateSalesman(salesCustHistoryPo);
			CustomerPo customerPo = new CustomerPo();
			customerPo.setCustid(Integer.valueOf(custid));
			customerPo.setSaleid(salesCustHistoryPo.getEndsaleid());
			customerService.updateCustomerOfSaleId(customerPo);

//			JdbcSystemUtils.changeHsAppOne(salemanService.findSaleById(salesCustHistoryPo.getBeforesaleid()), 
//					salemanService.findSaleById(salesCustHistoryPo.getEndsaleid()), itemService.findItemPoById(salesCustHistoryPo.getItemid()));
		}
		return ResUtils.okRes(salesManPo);

	}

	/**
	 * 批量修改
	 * 
	 * @param request
	 * @param custids
	 * @param custStatus
	 * @return
	 */
	@RequestMapping(value = "/batch/changeCustomerStatus")
	public String batchChangeCustomerStatus(HttpServletRequest request,
			@RequestParam("custids") ArrayList<String> custids,
			String custStatus) {
		if (custids == null || custids.isEmpty() || custStatus == null) {
			return ResUtils.errRes("102", "请求参数错误");
		}
		customerService.batchChangeCustomerStatus(custids, custStatus);
		return ResUtils.okRes();
	}

	// 改变客户状态
	@RequestMapping(value = "/changeCustomerStatus")
	public String changCustomerStatus(Integer custid, Integer status) {
		if (custid == null || status == null) {
			return ResUtils.errRes("102", "请求参数错误");
		}
		customerService.changCustomerStatus(custid, status);
		return ResUtils.okRes(status);

	}

	// 查询用户的详细信息
	@RequestMapping(value = "/detailedInfo")
	public String detailedInfo(Integer itemid) throws Exception {
		if (itemid == null) {
			return ResUtils.errRes("102", "请求参数错误");
		}
		UniversalPo mixPo = customerService.findDetailedInfo(itemid);
		TradeCecordPo tradePo = new TradeCecordPo();
		tradePo.setItemid(itemid);
		List<UniversalPo> tradeCecordPos = tradeRecordService
				.findTradeReByParasAndUnviersalPo(tradePo);
		List<TradeCecordVo> converttradeCecordPos = ConvertUtil
				.convertDtoAndVo(tradeCecordPos, TradeCecordVo.class);
		SalesCustHistoryPo salesHistoryPo = new SalesCustHistoryPo();

		salesHistoryPo.setItemid(itemid);
		List<SalesCustHistoryPo> saleHistoryPos = salesCustHistoryService
				.findHistoryBypara(salesHistoryPo);
		CustomerInfoVo customerInfoVo = (CustomerInfoVo) ConvertUtil
				.convertDtoAndVo(mixPo, CustomerInfoVo.class);
		List<SaleCustHistoryVo> list = new ArrayList<SaleCustHistoryVo>();
		List<PicRecordPo> piclist = picRecordSerice
				.findDocByItemId(customerInfoVo.getItemid());
		customerInfoVo.setUploadDocs(piclist);
		// 变更信息
		for (int i=0;null!=saleHistoryPos&&i<saleHistoryPos.size();i++) {
			SalesCustHistoryPo salesCustHistoryPo = saleHistoryPos.get(i);
			Integer endsaleid = salesCustHistoryPo.getEndsaleid();
			Integer beforesaleid = salesCustHistoryPo.getBeforesaleid();
			if (endsaleid.equals(beforesaleid)) {
				continue;
			}
			SalesCustHistoryPo sale = new SalesCustHistoryPo();
			sale.setEndsaleid(beforesaleid);
			sale.setItemid(itemid);
			// 查询是否有后面的变更记录
			List<SalesCustHistoryPo> change = salesCustHistoryService
					.findHistoryBypara(sale);

			SaleCustHistoryVo saleCustHistoryVo = new SaleCustHistoryVo();
			saleCustHistoryVo.setChangecause(salesCustHistoryPo
					.getChangecause());
			saleCustHistoryVo.setSaleid(beforesaleid);
			SalesManPo salesManPo = salemanService.findSaleById(beforesaleid);
			if(null!=salesManPo){
				saleCustHistoryVo.setSalename(salesManPo.getSalename());
			}

			saleCustHistoryVo.setEndDate(DateUtil.formatDate(
					salesCustHistoryPo.getCreatedate(), "yyyy-MM-dd"));
			if(i==saleHistoryPos.size()-1){
				saleCustHistoryVo.setEndDate(null);
			}
			// 不等于空的话，说明这不是第一次的变更,变更的时间是2次记录相减的。
			if (change != null && !change.isEmpty()) {
				SalesCustHistoryPo changedHis = change.get(0);
				saleCustHistoryVo.setBeginDate(DateUtil.formatDate(
						changedHis.getCreatedate(), "yyyy-MM-dd"));
			} else {
				// 说明这是第一次变更，时间是项目开始时间，减去这个记录的时间
				saleCustHistoryVo.setBeginDate(DateUtil.formatDate(
						customerInfoVo.getItembegindate(), "yyyy-MM-dd"));

			}
			list.add(saleCustHistoryVo);
		}
		customerInfoVo.setSalesCustHistoryVos(list);
		customerInfoVo.setTradeCecordPos(converttradeCecordPos);
		return ResUtils.okRes(customerInfoVo);
	}

	// 待续费用户的展示
	@RequestMapping("/renew")
	public String renew(HttpServletRequest request, PageTemp pageTemp)
			throws Exception {
		SalesManPo sale = getSale(request);
		boolean leader = getLeader(request);
		PageInfo<CustomerVO> result = null;
		// 通过request获取当前登录的用户的信息
		PageInfo<UniversalPo> pageInfos = customerService.findRenewCustomer(
				leader, sale, pageTemp, getTimeSpan(request),request.getParameter("beginDate"),request.getParameter("endDate"));
		if (pageInfos == null) {
			return null;
		}
		List<UniversalPo> list = pageInfos.getList();
		List<CustomerVO> customerVOs = ConvertUtil.convertDtoAndVo(list,
				CustomerVO.class);
		result = (PageInfo<CustomerVO>) ConvertUtil.convertDtoAndVo(pageInfos,
				PageInfo.class);
		result.setList(customerVOs);
		return ResUtils.okRes(result);
	}
	
	//客服的续费追踪
	@RequestMapping("/serviceRenew")
	public String serviceRenew(HttpServletRequest request, PageTemp pageTemp)
			throws Exception {
		SalesManPo sale = getSale(request);
		boolean leader = getLeader(request);
		Integer userid = super.getUserId(request);
		String searchValue = request.getParameter("searchValue");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String itemType = request.getParameter("itemType");
		PageInfo<CustomerVO> result = null;
		// 通过request获取当前登录的用户的信息
		PageInfo<UniversalPo> pageInfos = customerService.findRenewCustomerRenew(
				searchValue, itemType, userid, leader, sale, pageTemp, remSetSer.getTimeSpan(null, 1),beginDate,endDate);
		if (pageInfos == null) {
			return null;
		}
		List<UniversalPo> list = pageInfos.getList();
		List<CustomerVO> customerVOs = ConvertUtil.convertDtoAndVo(list,
				CustomerVO.class);
		result = (PageInfo<CustomerVO>) ConvertUtil.convertDtoAndVo(pageInfos,
				PageInfo.class);
		result.setList(customerVOs);
		return ResUtils.okRes(result);
	}
	
	
	
	// 待续费用户的数量
	@RequestMapping("/renew/count")
	public String renewCount(HttpServletRequest request) {
		SalesManPo sale = getSale(request);
		Integer timeSpan = getTimeSpan(request);
		boolean leader = getLeader(request);
		Integer count = customerService.findCountBySaleid(leader,sale, timeSpan,request.getParameter("beginDate"),request.getParameter("endDate"));
		ConcurrentHashMap<String, Integer> result = new ConcurrentHashMap<String, Integer>();
		result.put("count", count);
		return ResUtils.okRes(result);

	}

	@RequestMapping("/dateExport")
	public String dateExport(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("itemids[]") ArrayList<String> itemids) {
		// 获取到itemids,然后从后
		return download(response, itemids, "待续费客户数据");

	}

	@RequestMapping("/dateExportList")
	public String dateExportList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("itemids[]") ArrayList<String> itemids) {
		// 获取到itemids,然后从后台加载数据
		return download(response, itemids, "客户数据");

	}

	private String download(HttpServletResponse response,
			ArrayList<String> itemids, String name) {
		if (itemids == null) {
			return null;
		}
		List<UniversalPo> universalPos = customerService.findInfos(itemids);
		OutputStream outputStream = null;
		try {
			if (universalPos == null && universalPos.size() < 1) {
				return null;
			}
			List<CustomerVO> conDto = ConvertUtil.convertDtoAndVo(universalPos,
					CustomerVO.class);
			HSSFWorkbook hssfBook = export(conDto);
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String((name + ".xls").getBytes(), "iso-8859-1"));
			outputStream = response.getOutputStream();
			hssfBook.write(outputStream);
			outputStream.flush();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				outputStream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return ResUtils.okRes();
	}

	/*
	 * // 根据 不同的条件查询用户的信息
	 * 
	 * @RequestMapping("/changecustomer/searchCustomerByCondition") public
	 * String searchCustomerByCondition(HttpServletRequest request, PageTemp
	 * pageTemp) throws Exception { String condition =
	 * request.getParameter("condition"); String content =
	 * request.getParameter("content"); String custstatus =
	 * request.getParameter("custstatus"); SalesManPo sale = getSale(request);
	 * PageInfo<UniversalPo> info = customerService.searchCustomerByCondition(
	 * condition, content,custstatus,sale, pageTemp); List<CustomerInfoVo>
	 * convertDtoAndVo = ConvertUtil.convertDtoAndVo( info.getList(),
	 * CustomerInfoVo.class); PageInfo<CustomerInfoVo> result =
	 * (PageInfo<CustomerInfoVo>) ConvertUtil .convertDtoAndVo(info,
	 * PageInfo.class); if (result.getList() == null && result.getList().size()
	 * < 0.5) { return ResUtils.errRes("102", "请求参数错误"); }
	 * result.setList(convertDtoAndVo); return ResUtils.okRes(result); }
	 */

	// 客户变更
	@RequestMapping("/changecustomer/index")
	public String index1(HttpServletRequest request, PageTemp pageTemp)
			throws Exception {
		SalesManPo sale = getSale(request);
		boolean leader = getLeader(request);
		PageInfo<UniversalPo> info = customerService.searchCustomerByCondition(
				leader, null, null, null, sale, pageTemp);
		List<CustomerInfoVo> convertDtoAndVo = ConvertUtil.convertDtoAndVo(
				info.getList(), CustomerInfoVo.class);
		PageInfo<CustomerInfoVo> result = (PageInfo<CustomerInfoVo>) ConvertUtil
				.convertDtoAndVo(info, PageInfo.class);
		if (result == null) {
			return null;
		}
		result.setList(convertDtoAndVo);
		return ResUtils.okRes(result);
	}

	@RequestMapping("/changecustomer/customerChangeSaleman")
	public String customerChangeSaleman(HttpServletRequest request,
			@RequestParam("custids[]") List<Integer> custids, Integer saleid) {
		if (custids.size() < 1 || saleid == null) {
			return ResUtils.errRes("102", "请求参数错误");
		}
		/*
		 * SalesManPo sale = getSale(request); if(sale==null){ return
		 * ResUtils.nologin; }
		 */
		UserPo userPo = super.getUserPo(request);
		String result = customerService
				.customerChangeSalemanBySalePhoneAndCustids(custids, saleid,
						userPo);
		return result;
	}

	// 查看详细情况,根据customerid来查询对应的历史记录
	@RequestMapping(value = "/changecustomer/changeHistory", method = RequestMethod.GET)
	public String changeHistory(HttpServletRequest request, Integer custid)
			throws Exception {
		List<SaleCustHistoryVo> saleCustHistoryVos;
		saleCustHistoryVos = salesCustHistoryService
				.findCustomerHistoryBycustid(custid);
		return ResUtils.okRes(saleCustHistoryVos);
	}

	/**
	 * 用于修改的页面的保存
	 * 
	 * @throws Exception
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "/updateItemPo", method = RequestMethod.POST)
	public String updateItemPo(HttpServletRequest request, ItemsPo po) {
		if (null != po.getItemid() && po.getItemid() > 0) {
			po.setUpdateby(super.getUserId(request));
			itemService.updateItemByItemid(po);
			return ResUtils.okRes();
		}
		return ResUtils.errRes("102", "请求参数错误");
	}

	/**
	 * 根据项目id查询业务员和客户信息
	 * 
	 * @param request
	 * @param itemid
	 * @return
	 */
	@RequestMapping(value = "/findSaleAndCustomer")
	public String findSaleAndCustomer(HttpServletRequest request, String itemid) {
		try {
			if (StringUtils.isEmpty(itemid)) {
				return ResUtils.errRes("102", "请求参数错误");
			}
			ItemCustSaleVo itemCustSaleVo = customerService
					.findVoByitemid(Integer.parseInt(itemid));
			if (itemCustSaleVo == null) {
				return ResUtils.errRes("102", "请求参数错误");
			}
			return ResUtils.okRes(itemCustSaleVo);
		} catch (Exception e) {
			return ResUtils.execRes();
		}

	}
	
	/**
	 * 根据项目id查询业务员和客户信息
	 * 
	 * @param request
	 * @param itemid
	 * @return
	 */
	@RequestMapping(value = "/checkCustByContAndPhone")
	public String checkCustByContAndPhone(HttpServletRequest request, String contact, String phone) {
		if(StringUtils.isBlank(contact)||StringUtils.isBlank(phone)){
			return ResUtils.okRes();
		}
		if(customerService.checkCustByContAndPhone(contact, phone)){
			return ResUtils.okRes();
		}else{
			return ResUtils.errRes("102", "客户姓名与手机号已经存在");
		}
	}
	
	/**
	 * 根据项目id查询业务员和客户信息
	 * 
	 * @param request
	 * @param itemid
	 * @return
	 */
	@RequestMapping(value = "/checkItemByNameAndType")
	public String checkItemByNameAndType(HttpServletRequest request, String itemname, Integer itemtype,Integer itemtypedetail) {
		if(StringUtils.isBlank(itemname)||StringUtils.isBlank(String.valueOf(itemtype))||StringUtils.isBlank(String.valueOf(itemtypedetail))){
			return ResUtils.okRes();
		}
		if(customerService.checkItemByNameAndType(itemname, itemtype,itemtypedetail)){
			return ResUtils.okRes();
		}else{
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 客服管理-项目列表
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@RequestMapping(value = "/serviceItem")
	public String serviceItem(HttpServletRequest request, PageTemp pageTemp)
			throws InstantiationException, IllegalAccessException {
		PageInfo<CustomerVO> result = null;
		SalesManPo sale = getSale(request);
//		boolean leader = getLeader(request);
		
		Map reMap = getReqMap(request);
		// 判断是否是个人还是经理,客服个人只能查看被指派的项目
		String pointMeStr = request.getParameter("pointme");
		if (StringUtils.isNotBlank(pointMeStr)&&"true".equals(pointMeStr)) {
			reMap.put("userid", super.getUserId(request));
		}
		
		PageInfo<UniversalPo> pageInfo = serviceItem.searchCustomerByCondition(pageTemp, reMap);
		if (pageInfo == null) {
			return null;
		}
		List<UniversalPo> list = pageInfo.getList();
		if (list != null && list.size() < 0.5) {
			return ResUtils.okRes(null);
		}
		List<CustomerVO> customerVOs = ConvertUtil.convertDtoAndVo(list,
				CustomerVO.class);
		result = (PageInfo<CustomerVO>) ConvertUtil.convertDtoAndVo(pageInfo,
				PageInfo.class);
		result.setList(customerVOs);
		return ResUtils.okRes(result);
	}
	
	private Map getReqMap(HttpServletRequest request){
		Map resMap = new HashMap();
		String searchvalue = request.getParameter("searchvalue");
		if(StringUtils.isNotBlank(searchvalue)){
			resMap.put("searchvalue", searchvalue);
		}
		String custstatus = request.getParameter("custstatus");
		if(StringUtils.isNotBlank(custstatus)){
			resMap.put("custstatus", custstatus);
		}
		String begindate = request.getParameter("begindate");
		if(StringUtils.isNotBlank(begindate)){
			resMap.put("begindate", begindate);
		}
		String enddate = request.getParameter("enddate");
		if(StringUtils.isNotBlank(enddate)){
			resMap.put("enddate", enddate);
		}
		String tradetype = request.getParameter("tradetype");
		if(StringUtils.isNotBlank(tradetype)){
			resMap.put("tradetype", tradetype);
		}
		String isrenew = request.getParameter("isrenew");
		if(StringUtils.isNotBlank(isrenew)){
			resMap.put("isrenew", isrenew);
		}
		String salenull = request.getParameter("salenull");
		if(StringUtils.isNotBlank(salenull)){
			resMap.put("salenull", salenull);
		}
		String overdue = request.getParameter("overdue");
		if(StringUtils.isNotBlank(overdue)){
			resMap.put("overdue", overdue);
		}
		String itemtype = request.getParameter("itemtype");
		if(StringUtils.isNotBlank(itemtype)){
			resMap.put("itemtype", itemtype);
		}
		String contractstatus = request.getParameter("contractstatus");
		if(StringUtils.isNotBlank(contractstatus)){
			resMap.put("contractstatus", contractstatus);
		}
		String sortcondition = request.getParameter("sortcondition");
		if(StringUtils.isNotBlank(sortcondition)){
			resMap.put("sortcondition", sortcondition);
		}
		
		return resMap;
	}
	

	/*
	 * 项目名称输入框选择
	 */
	@RequestMapping(value = "/findAllItems")
	public String findAllItems(HttpServletRequest request){		
		try {
			List<ItemsPo> list = itemService.findAll();
			List<Map<String,Object>> valMap = new ArrayList<Map<String,Object>>();
			if(list!=null&&list.size()>0){
				for(ItemsPo po:list){
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("value", po.getItemname());
					map.put("itemid", po.getItemid());
					valMap.add(map);
				}
			}
			return ResUtils.okRes(valMap);
		} catch (Exception e) {
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 查询项目的状态
	 */
	@RequestMapping(value = "/findItemByItemid")
	public String findItemByItemid(HttpServletRequest request, String itemid){
		try {
			if (StringUtils.isEmpty(itemid)) {
				return ResUtils.errRes("102", "请求参数错误");
			}
			ItemsPo itemsPo = itemService.findItemPoById(Integer.parseInt(itemid));
			return ResUtils.okRes(itemsPo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	// 改变客户状态(对应表里的 status字段)
	@RequestMapping(value = "/changItemStatus")
	public String changItemStatus(Integer itemid, Integer status) {
		if (itemid == null || status == null) {
			return ResUtils.errRes("102", "请求参数错误");
		}
		itemService.changItemStatus(itemid, status);
		return ResUtils.okRes(status);
	}
	
	/**
	 * 根据custid获取对应的项目
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findItemsByCustid")
	public String findItemsByCustid(HttpServletRequest request){
		try {
			String custid = request.getParameter("custid");
			if(StringUtils.isBlank(custid)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<ItemsPo> list = itemService.findItemsByCustid(Integer.parseInt(custid));
			return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}

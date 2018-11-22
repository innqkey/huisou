package com.huisou.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.naming.ldap.BasicControl;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.common.ConvertUtil;
import com.common.CreatAndReadExcel;
import com.common.FileUtil;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.po.BigCustomePo;
import com.huisou.po.SalesManPo;
import com.huisou.po.SourcesPo;
import com.huisou.po.TradeCustomerPo;
import com.huisou.service.BigCustomerService;
import com.huisou.service.SalesmanService;
import com.huisou.service.TradeCustomerService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.SourcesVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* Created by qinkai 
* 2017年7月24日
*/
@RestController
@SuppressWarnings("all")
@RequestMapping(value = "/bigcustomer")
public class BigCustomerController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(BigCustomerController.class);
	
	@Autowired
	BigCustomerService bigCustomerService;
	@Autowired
	TradeCustomerService tradeCustomerService;
	
	/*
	 * 添加新的大客户 
	 * @param BigCustomePo 
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(BigCustomePo po, HttpServletRequest request){
		po.setCreateby(super.getUserId(request));
		po.setCreatedate(new Date());
		return bigCustomerService.insertBigCustomer(po);
	}
	
	/*
	 * 修改大客户属性
	 * @param BigCustomePo
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String update(BigCustomePo po, HttpServletRequest request){
		try {
			if (StringUtils.isNotBlank(po.getBigid().toString()) 
					&& Integer.valueOf(po.getBigid())>0) {
				po.setUpdateby(super.getUserId(request));
				po.setCreatedate(new Date());
				return bigCustomerService.updateBigCustomer(po);
			}
			return ResUtils.errRes("异常", "请求的类中id不为正数");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.errRes("异常", "请求的类中id不为正数");
		}
	}
	
	/*
	 * 查看大客户详情
	 * @param 大客户id
	 */
	@RequestMapping(value = "/detail")
	public String detail(String userId){
		if(StringUtils.isNotBlank(userId)&&Integer.valueOf(userId)>0){
			int bigId = Integer.parseInt(userId);
			BigCustomePo bigCustomePo = bigCustomerService.selectByBigCusId(bigId);
			return ResUtils.okRes(bigCustomePo);
		}
		return ResUtils.errRes("102", "请求参数错误");
	}
	
	/*
	 * 禁用/启动/移除  大客户
	 * @param bigid
	 * @param bigstatus
	 */
	@RequestMapping(value = "/status")
	public String status(HttpServletRequest request){
		
		if(StringUtils.isNotBlank(request.getParameter("bigId")) && 
				Integer.valueOf(request.getParameter("bigId"))>0){
			
			int bigid = Integer.parseInt(request.getParameter("bigId"));
			String bigsalestatus = request.getParameter("bigStatus");
			if (bigsalestatus == null){
				return ResUtils.errRes("102", "请求参数错误");
			}
			else if (bigsalestatus.equals("1")){
				return bigCustomerService.forbidBigStatus(bigid,Integer.parseInt(bigsalestatus));
			}
			if (bigsalestatus.equals("3")){
				return bigCustomerService.removeBigStatus(bigid);
			}else{
				return bigCustomerService.forbidBigStatus(bigid,Integer.parseInt(bigsalestatus));
			}
		}
		return ResUtils.errRes("102", "请求参数错误");
	}
	
	/*
	 * 大客户列表查询
	 * @param bigContStatus
	 * @param bigSaleStatus
	 * @param bigContact
	 * @param bigPhone
	 */
	@RequestMapping(value = "/list" )
	public String list(HttpServletRequest request, PageTemp pageTemp){
		try {
			String bigcontstatus = request.getParameter("bigContStatus");
			String bigsalestatus = request.getParameter("bigSaleStatus");
			String bigcontact = StringUtils.stripToEmpty(request.getParameter("bigContact"));
			String bigphone = StringUtils.stripToEmpty(request.getParameter("bigPhone"));
			Map<String, String> maps = new HashMap<String, String>();
			maps.put("bigcontstatus", bigcontstatus);
			maps.put("bigsalestatus", bigsalestatus);
			maps.put("bigcontact", bigcontact);
			maps.put("bigphone", bigphone);
			//bigsalestatus=2,如果是业务员只能查看自己的分配客户
			if("2".equals(bigsalestatus)){
				if(super.getLeader(request)){
					maps.put("saleid", String.valueOf(super.getSaleId(request)>0?super.getSaleId(request):""));
				}
			}
			
			PageInfo<BigCustomePo> poList = bigCustomerService.queryByBigCusParas(maps,pageTemp);
	        return ResUtils.okRes(poList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.exceCode;
		}
	}
	
	/*
	 * 批量指配客户给指定业务员
	 * @param bigSaleStatus
	 * @param saleid
	 * @param bigId
	 * @param salename
	 */
	
	@RequestMapping(value="/allocate")
	public String allocate(HttpServletRequest request,@RequestParam("bigIds[]") ArrayList<String> bigIds){
		try {
			String bigsalestatus = request.getParameter("bigSaleStatus");
			String saleid = request.getParameter("saleId");
			String salename = request.getParameter("saleName");
			
			Map map = new HashMap();
			map.put("bigids", bigIds);
			map.put("saleid", saleid);
			map.put("salename", salename);
			
			if (StringUtils.isNotBlank(bigsalestatus) && 
					Integer.valueOf(bigsalestatus) == 1) {
				bigsalestatus = "2";
				map.put("bigsalestatus", bigsalestatus);
				return bigCustomerService.allocatedBigCustomer(map);
			} else if (StringUtils.isNotBlank(bigsalestatus) && Integer.valueOf(bigsalestatus) == 2){
				for (String bigid : bigIds) {
//					TradeCustomerPo tradeCustomerPo = bigCustomerService.copyPropertiesById(bigid,saleid,salename);
					TradeCustomerPo tradeCustomerPo = bigCustomerService.copyPropertiesById(bigid);
					tradeCustomerService.insertTradeCustomer(tradeCustomerPo);
				}
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			return ResUtils.exceCode;
		}
		
	}
	
	/*
	 * 加载大客户数据
	 * @param bigid
	 * @param bigstatus
	 */
	@RequestMapping(value = "/importExcel",method= RequestMethod.POST)
	public String importExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request){
		File destF = null;
		if (!file.isEmpty()) { 
			double size = (double) file.getSize()/1024/1024;
			logger.info("==="+size+"M");
            try {  
                // 文件保存路径  
                String filePath = FileUtil.getApplicationPro("crm.big.cust.url") + file.getOriginalFilename();
                // 转存文件  
                destF = new File(filePath);
                file.transferTo(destF);
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }else{
        	return ResUtils.errRes("102","文件不能为空");
        }
		
		long begin = System.currentTimeMillis();
		
		Integer userId = super.getUserId(request);
		
		
		try {
//			List<List<Object>> objList = CreatAndReadExcel.readExcel(file);
//			List<List<Object>> objList = CreatAndReadExcel.readExcel(file);
//			List<BigCustomePo> bigList = new ArrayList<BigCustomePo>();
//			for(int i=0;null!=objList&&i<objList.size();i++){
//				List<Object> oneList = objList.get(i);
//				BigCustomePo po = new BigCustomePo();
//				po.setBigcontact(String.valueOf(null!=oneList.get(0)?oneList.get(0):""));
//				po.setBigphone(String.valueOf(null!=oneList.get(1)?oneList.get(1):""));
//				po.setBigcompanyname(String.valueOf(null!=oneList.get(2)?oneList.get(2):""));
//				po.setCreateby(super.getUserId(request));
//				po.setCreatedate(new Date());
//				bigList.add(po);
////				bigCustomerService.insertBigCustomer(po);
//			}
			
			String fileName = destF.getName();
			String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
					.substring(fileName.lastIndexOf(".") + 1);
			if ("xls".equals(extension)) {
				read2003Excel(destF, bigCustomerService, userId);
			} else if ("xlsx".equals(extension)) {
				read2007Excel(destF, bigCustomerService, userId);
			} else {
				throw new IOException("不支持的文件类型");
			}
			
			long end1 = System.currentTimeMillis();
			logger.info("导入excel时间==============="+(end1-begin));
//			read2007Excel(file, bigCustomerService, userId);
//			insert(objList, bigCustomerService, userId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResUtils.errRes("102", "请求参数错误");
	}
	
	
	/**
	 * 读取 office 2003 excel
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void read2003Excel(File file, BigCustomerService bigCustomerService, Integer userId)
			throws IOException {
		List<BigCustomePo> list = new LinkedList<BigCustomePo>();
		HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = hwb.getSheetAt(0);
		HSSFRow row = null;
		logger.info("读取office 2003 excel内容如下：");
		for (int i = sheet.getFirstRowNum(); i <= sheet
				.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			
			BigCustomePo linkedPo = row2003ToList(row,userId);
			list.add(linkedPo);
			if(list.size()==20000){
				insert(list, bigCustomerService, userId);
				list.clear();
			}
		}

	}
	
	private BigCustomePo row2003ToList(HSSFRow row,Integer userId){
		Object value = null;
		HSSFCell cell = null;
		List<Object> linked = new LinkedList<Object>();
		for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
			cell = row.getCell(j);
			if (cell == null) {
				continue;
			}
			DecimalFormat df = new DecimalFormat("0");// 格式化 number String
			// 字符
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
			DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
			switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_STRING:
				// logger.info(i + "行" + j + " 列 is String type");
				value = cell.getStringCellValue();
				break;
			case XSSFCell.CELL_TYPE_NUMERIC:
				// logger.info(i + "行" + j
				// + " 列 is Number type ; DateFormt:"
				// + cell.getCellStyle().getDataFormatString());
				if ("@".equals(cell.getCellStyle().getDataFormatString())) {
					value = df.format(cell.getNumericCellValue());

				} else if ("General".equals(cell.getCellStyle()
						.getDataFormatString())) {
					value = nf.format(cell.getNumericCellValue());
				} else {
					value = sdf.format(HSSFDateUtil.getJavaDate(cell
							.getNumericCellValue()));
				}
				break;
			case XSSFCell.CELL_TYPE_BOOLEAN:
				// logger.info(i + "行" + j + " 列 is Boolean type");
				value = cell.getBooleanCellValue();
				break;
			case XSSFCell.CELL_TYPE_BLANK:
				// logger.info(i + "行" + j + " 列 is Blank type");
				value = "";
				break;
			default:
				// logger.info(i + "行" + j + " 列 is default type");
				value = cell.toString();
			}
			if (value == null || "".equals(value)) {
				continue;
			}
			linked.add(value);

		}
		BigCustomePo po = new BigCustomePo();
		po.setBigcontact(String.valueOf(null!=linked.get(0)?linked.get(0):""));
		po.setBigphone(String.valueOf(null!=linked.get(1)?linked.get(1):""));
		po.setBigcompanyname(String.valueOf(null!=linked.get(2)?linked.get(2):""));
		po.setCreateby(userId);
		po.setCreatedate(new Date());
		return po;
	}
	private void read2007Excel(File file, BigCustomerService bigCustomerService, Integer userId)
			throws IOException {

		List<BigCustomePo> list = new LinkedList<BigCustomePo>();
		// String path = System.getProperty("user.dir") +
		// System.getProperty("file.separator")+"dd.xlsx";
		// logger.info("路径："+path);
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));

		// 读取第一章表格内容
		for(int k=0;k<xwb.getNumberOfSheets();k++){
			XSSFSheet sheet = xwb.getSheetAt(k);
			XSSFRow row = null;
			XSSFCell cell = null;
			for (int i = sheet.getFirstRowNum(); i <= sheet
					.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				BigCustomePo linkedPo = row2007ToList(row,userId);
				list.add(linkedPo);
				if(list.size()==20000){
					insert(list, bigCustomerService, userId);
					list.clear();
				}
			}
		}
		//剩余数据添加
		if(null!=list&&list.size()>0){
			insert(list, bigCustomerService, userId);
			list.clear();
		}
	}

	private BigCustomePo row2007ToList(XSSFRow row,Integer userId) {
		XSSFCell cell = null;
		Object value = null;
		List<Object> linked = new LinkedList<Object>();
		for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
			cell = row.getCell(j);
			if (cell == null) {
				continue;
			}
			DecimalFormat df = new DecimalFormat("0");// 格式化 number String
			// 字符
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
			DecimalFormat nf = new DecimalFormat("0");// 格式化数字

			switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_STRING:
				// logger.info(i + "行" + j + " 列 is String type");
				value = cell.getStringCellValue();
//				logger.info("  " + value + "  ");
				break;
			case XSSFCell.CELL_TYPE_NUMERIC:
				// logger.info(i + "行" + j
				// + " 列 is Number type ; DateFormt:"
				// + cell.getCellStyle().getDataFormatString());
				if ("@".equals(cell.getCellStyle().getDataFormatString())) {
					value = df.format(cell.getNumericCellValue());

				} else if ("General".equals(cell.getCellStyle()
						.getDataFormatString())) {
					value = nf.format(cell.getNumericCellValue());
				} else {
					value = sdf.format(HSSFDateUtil.getJavaDate(cell
							.getNumericCellValue()));
				}
				break;
			case XSSFCell.CELL_TYPE_BOOLEAN:
				// logger.info(i + "行" + j + " 列 is Boolean type");
				value = cell.getBooleanCellValue();
				break;
			case XSSFCell.CELL_TYPE_BLANK:
				// logger.info(i + "行" + j + " 列 is Blank type");
				value = "";
				// logger.info(value);
				break;
			default:
				// logger.info(i + "行" + j + " 列 is default type");
				value = cell.toString();
			}
			if (value == null || "".equals(value)) {
				continue;
			}
			linked.add(value);
		}
		BigCustomePo po = new BigCustomePo();
		po.setBigcontact(String.valueOf(null!=linked.get(0)?linked.get(0):""));
		po.setBigphone(String.valueOf(null!=linked.get(1)?linked.get(1):""));
		po.setBigcompanyname(String.valueOf(null!=linked.get(2)?linked.get(2):""));
		po.setCreateby(userId);
		po.setCreatedate(new Date());
		return po;
	}
	
	private void insert(List<BigCustomePo> list,BigCustomerService bigCustomerService, Integer userId){
		
		if(null==list||list.size()==0){
			return ;
		}
		//总记录数
		int rows=list.size();  
		//每页显示的记录数
		int pageCount=5000;  
		//页数
		int threads=(rows-1)/pageCount+1;
		
		logger.info("总记录数=="+rows+";页数=="+threads);
		
		//计数器
		CountDownLatch cdl = new CountDownLatch(threads);
		//创建固定大小线程池
		ExecutorService executor = Executors.newFixedThreadPool(threads);
		
		for(int j=0;j<threads;j++){
			if(threads!=(j+1)){
				executor.execute(new BigCust(list.subList(j*pageCount, (j+1)*pageCount), cdl, bigCustomerService));
			}else{
				executor.execute(new BigCust(list.subList(j*pageCount, list.size()), cdl, bigCustomerService));
			}
		}
		try {
			cdl.await();
			executor.shutdown();
			logger.info("over");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static class BigCust extends Thread{
		private List<BigCustomePo> list;
		private CountDownLatch cdl;
		private BigCustomerService bigCustomerService;
		public BigCust(List<BigCustomePo> list, CountDownLatch cdl, BigCustomerService bigCustomerService){
			this.list = list;
			this.cdl = cdl;
			this.bigCustomerService = bigCustomerService;
		}
		
		public void run(){
//			List<BigCustomePo> bigList = new ArrayList<BigCustomePo>();
//			for(int i=0;i<list.size();i++){
//				List<Object> oneList = list.get(i);
//				BigCustomePo po = new BigCustomePo();
//				po.setBigcontact(String.valueOf(null!=oneList.get(0)?oneList.get(0):""));
//				po.setBigphone(String.valueOf(null!=oneList.get(1)?oneList.get(1):""));
//				po.setBigcompanyname(String.valueOf(null!=oneList.get(2)?oneList.get(2):""));
//				po.setCreateby(userId);
//				po.setCreatedate(new Date());
//				bigList.add(po);
//			}
			
			bigCustomerService.addBatchList(list);
			cdl.countDown();
		}
	}
}

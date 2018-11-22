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

import javax.servlet.http.HttpServletRequest;

import com.huisou.po.UserPo;
import com.huisou.po.UserRolePo;
import com.huisou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.common.ConvertUtil;
import com.common.DateUtils;
import com.common.FileUtil;
import com.common.ReflectionUtil;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.constant.DictConConstant;
import com.huisou.po.EmployeePo;
import com.huisou.po.OrganizePo;
import com.huisou.service.EmployeeService;
import com.huisou.service.OrganizeService;
import com.huisou.service.RoleService;
import com.huisou.service.UserRoleService;
import com.huisou.vo.EluiSelectVo;
import com.huisou.vo.EmployeeVo;
import com.huisou.vo.OrgPostVo;
import com.huisou.vo.PageTemp;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* Created by qinkai 
* 2017年7月19日
*/

@RestController
@RequestMapping("/employee")
public class EmployeeController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
    UserService userService;
	
	@Autowired
	UserRoleService userRoleService;

	@Autowired
	OrganizeService organizeService;
	
    @Autowired
    RoleService roleService;
	/*
	 * 添加新的员工 
	 * @param EmployeePo 
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(HttpServletRequest request){
		try {			
			String orgid = request.getParameter("orgid");
			String postid1 = request.getParameter("postid");
			 Integer postid = 	0;
			if(StringUtils.isNotBlank(postid1)){
				postid = 	Integer.parseInt(postid1); 
			}
			if(StringUtils.isBlank(orgid) ){
				return ResUtils.errRes("102", "请求参数部门和岗位id错误");
			}
			boolean phoneIsExist=employeeService.phoneIsExist(request.getParameter("empphone"));
			if(phoneIsExist){
				return ResUtils.errRes(ContextConstant.EXIST_PHONE, "手机号已存在");
			}			
			EmployeePo employeePo = new EmployeePo();
			employeePo.setCreateby(super.getUserId(request));
			employeePo.setCreatedate(new Date());
			if (StringUtils.isNotBlank(request.getParameter("birthday"))){
				employeePo.setBirthday(DateUtils.format(request.getParameter("birthday"), DateUtils.Y_M_D));
			}
			if (StringUtils.isNotBlank(request.getParameter("entrydate"))){
				employeePo.setEntrydate(DateUtils.format(request.getParameter("entrydate"), DateUtils.Y_M_D));
			}
			employeePo.setEmpname(request.getParameter("empname"));
			employeePo.setEmpphone(request.getParameter("empphone"));
			employeePo.setEmpidcard(request.getParameter("empidcard"));
			employeePo.setEmpweixin(request.getParameter("empweixin"));
			employeePo.setEmpqq(request.getParameter("empqq"));
			employeePo.setEmpemail(request.getParameter("empemail"));
			employeePo.setDuty(request.getParameter("duty"));
			employeePo.setAddress(request.getParameter("address"));
			employeePo.setNativeplace(request.getParameter("nativeplace"));
//			if (StringUtils.isNotBlank(request.getParameter("empstatus"))){
//				employeePo.setEmpstatus(Integer.parseInt(request.getParameter("empstatus")));
//			}
			employeePo.setUrgentcont(request.getParameter("urgentcont"));
			employeePo.setUrgentcontphone(request.getParameter("urgentcontphone"));
			employeePo.setEmpremark(request.getParameter("empremark"));
			employeeService.insertEmployee(employeePo);
			employeePo.setDepid(Integer.parseInt(orgid));

            //新增 2017年09月08日09:17:48 by 薛园
            // 在增加员工的时候，默认插入与当前员工对应的user ，即同时创建了登录账号
            
            UserPo userPo = new UserPo();
            userPo.setEmpid(employeePo.getEmpid());
            userPo.setUsername(employeePo.getEmpphone());
            userPo.setPetname(employeePo.getEmpname());
            userPo.setOrgid(Integer.parseInt(orgid));
            userPo.setLeader(0);
            userPo.setPostid(postid);
            if (postid > 0){
            	userPo.setLeader(1);
            }
            // 约定员工的id为887
            if (StringUtils.isNotBlank(request.getParameter("roleid"))){
              userService.insertUser(userPo, Integer.parseInt(request.getParameter("roleid")));
            } else{
            	return ResUtils.errRes("102", "请求参数解析错误");
            }
            			
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.errRes("102", "请求参数解析错误"); 
		}

	}
	
	/*
	 * 根据员工id号修改属性
	 * @param Employee
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String update(HttpServletRequest request){
		try {
			String orgid = request.getParameter("orgid");
			String postid1 = request.getParameter("postid");
			String empstatus = request.getParameter("empstatus");
			 Integer postid = 	0;
			if(StringUtils.isNotBlank(postid1)){
				postid = 	Integer.parseInt(postid1); 
			}
			if(StringUtils.isBlank(orgid) ){
				return ResUtils.errRes("102", "请求参数部门和岗位id错误");
			}
			if (StringUtils.isNotBlank(request.getParameter("empid")) 
					&& Integer.valueOf(request.getParameter("empid"))>0){
				EmployeePo employeePo = 
						employeeService.selectByEmpId(Integer.valueOf(request.getParameter("empid")));
				
//				boolean phoneIsExist=employeeService.phoneIsExist(request.getParameter("empphone"));
//				if(phoneIsExist){
//					return ResUtils.errRes(ContextConstant.EXIST_PHONE, "手机号已存在");
//				}
				
				if (StringUtils.isNotBlank(request.getParameter("birthday"))){
					employeePo.setBirthday(DateUtils.format(request.getParameter("birthday"), DateUtils.Y_M_D));
				}
				if (StringUtils.isNotBlank(request.getParameter("entrydate"))){
					employeePo.setEntrydate(DateUtils.format(request.getParameter("entrydate"), DateUtils.Y_M_D));
				}
				employeePo.setEmpname(request.getParameter("empname"));
				employeePo.setEmpphone(request.getParameter("empphone"));
				employeePo.setEmpidcard(request.getParameter("empidcard"));
				employeePo.setEmpweixin(request.getParameter("empweixin"));
				employeePo.setEmpqq(request.getParameter("empqq"));
				employeePo.setEmpemail(request.getParameter("empemail"));
				employeePo.setDuty(request.getParameter("duty"));
				employeePo.setAddress(request.getParameter("address"));
				employeePo.setNativeplace(request.getParameter("nativeplace"));
				if (StringUtils.isNotBlank(empstatus)){
					employeePo.setEmpstatus(Integer.parseInt(empstatus));
				}
				employeePo.setUrgentcont(request.getParameter("urgentcont"));
				employeePo.setUrgentcontphone(request.getParameter("urgentcontphone"));
				employeePo.setEmpremark(request.getParameter("empremark"));
				employeePo.setUpdateby(super.getUserId(request));
				
	            UserPo userPo = userService.findUserByEmpid(Integer.valueOf(request.getParameter("empid")));
	            if (StringUtils.isNotBlank(empstatus) && empstatus.equals("2")){
	            	userPo.setStatus(0);
	            } else {
	            	userPo.setStatus(1);
	            }
	            if (postid > 0){
	            	userPo.setLeader(1);
	            }
	            userPo.setPetname(request.getParameter("empname"));
	            userService.update(userPo);
	            
	            UserRolePo userRole = userRoleService.findOne(userPo.getUserid());
	            userRole.setRoleid(Integer.parseInt(request.getParameter("roleid")));
	            userRoleService.updateUserRole(userRole);
	           		
		        userService.updateOrgAndPostid(employeePo.getEmpid(), Integer.parseInt(orgid), postid);
				return employeeService.updateEmployee(employeePo);
			}
			return ResUtils.errRes("102", "请求的类中id不为正数");	
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.errRes("102", "请求的类中id不为正数");
		}
	}
	
	
	/*
	 * 根据员工员手机号，时间查找 
	 * @param empphone date 
	 */
	@RequestMapping(value = "/list" )
	public String list(HttpServletRequest request, PageTemp pageTemp){
		try {
			String empphone = StringUtils.stripToEmpty(request.getParameter("empphone"));
			String empname = StringUtils.stripToEmpty(request.getParameter("empname"));
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			
			Map<String, String> maps = new HashMap<String, String>();
			maps.put("empphone", empphone);
			maps.put("empname", empname);
			maps.put("beginDate", beginDate);
			maps.put("endDate", endDate);
			
			PageInfo<EmployeePo> poList = employeeService.queryByEmpParas(maps,pageTemp);
	        return ResUtils.okRes(poList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.exceCode;
		}
	}
	/*
	 * 返回所有员工,不要分页信息
	 */
	@RequestMapping(value = "/findAll")
	public String findAll(){
		List<EmployeePo> poList = employeeService.findAllEmpolyee();
		return ResUtils.okRes(poList);
	}
	/*
	 * 加载员工数据
	 * @param file
	 */
	@RequestMapping(value = "/importExcel",method= RequestMethod.POST)
	public String importExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request){
		File destF = null;
		if (!file.isEmpty()) { 
			double size = (double) file.getSize()/1024/1024;
			logger.info("==="+size+"M");
            try {  
                // 文件保存路径  
                String filePath = FileUtil.getApplicationPro("crm.emp.cust.url") + file.getOriginalFilename();
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
//		Integer userId = Integer.valueOf(41);
		
		
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
				read2003Excel(destF, employeeService, userId);
			} else if ("xlsx".equals(extension)) {
				read2007Excel(destF, employeeService, userId);
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
		return ResUtils.okRes();
	}
	
	
	/**
	 * 读取 office 2003 excel
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void read2003Excel(File file, EmployeeService employeeService, Integer userId)
			throws IOException {
		List<EmployeePo> list = new LinkedList<EmployeePo>();
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
			
			EmployeePo linkedPo = row2003ToList(row,userId);
			list.add(linkedPo);
			if(list.size()==20000){
				insert(list, employeeService, userId);
				list.clear();
			}
		}
		if (null != list && list.size() > 0){
			insert(list, employeeService, userId);
			list.clear();
		}
	}

	/*
	 * 读取2003excel表格中每行数据
	 */
	private EmployeePo row2003ToList(HSSFRow row,Integer userId){
		Object value = null;
		HSSFCell cell = null;
		List<Object> linked = new LinkedList<Object>();
		for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
			cell = row.getCell(j);
			if (cell == null) {
				linked.add(null);
				continue;
			}
			DecimalFormat df = new DecimalFormat("0");// 格式化 number String
			// 字符
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
			DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
			if (j == 1){
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			}
//			cell.setCellType(XSSFCell.CELL_TYPE_STRING);
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
		EmployeePo po = new EmployeePo();
		po.setEmpname(String.valueOf(null!=linked.get(0)?linked.get(0):""));
		po.setEmpphone(String.valueOf(null!=linked.get(1)?linked.get(1):""));
		if (2 < linked.size()){
			po.setEmpweixin(String.valueOf(null!=linked.get(2)?linked.get(2):""));
		}
		if (3 < linked.size()){
			po.setEmpemail(String.valueOf(null!=linked.get(3)?linked.get(3):""));
		}	
		if (4 < linked.size() && null!=linked.get(4)){
			po.setEntrydate(DateUtils.format(String.valueOf(null!=linked.get(4)?linked.get(4):""), DateUtils.Y_M_D));
		}
		po.setCreateby(userId);
		po.setCreatedate(new Date());
		return po;
	}
    /*
     * 根据员工id号查看 详情
     * @param 员工id
     */
    @RequestMapping(value = "/detail")
    public String detail(String userId) throws IllegalAccessException, InstantiationException {
        if (StringUtils.isNotBlank(userId) && Integer.valueOf(userId) > 0) {
            int empId = Integer.parseInt(userId);
            EmployeePo selectByEmpId = employeeService.selectByEmpId(empId);
            EmployeeVo employeeVo = (EmployeeVo) ConvertUtil.convertDtoAndVo(selectByEmpId, EmployeeVo.class);
            UserPo userPo = userService.findUserByEmpid(empId);
            
            UserRolePo roleUser = userRoleService.findOne(userPo.getUserid());
            Integer roleid = roleUser.getRoleid();
//            String roleValue = null;
//            if (roleService.findOne(roleid) != null) {
//            	roleValue = roleService.findOne(roleid).getRolename();
//            }
            Integer orgid =userPo.getOrgid();
            Integer postid = userPo.getPostid();
            if(userPo.getOrgid()==null){
            	 orgid = 0;
            }
            if(userPo.getPostid()==null){
            	postid = 0;
            }
            employeeVo.setOrgid(orgid);
            employeeVo.setPostid(postid);
            employeeVo.setRolevalue(roleid);
            
            return ResUtils.okRes(employeeVo);
        }
        return ResUtils.errRes("102", "请求参数错误");
    }

    
    @RequestMapping(value = "/findPostByEmpid")
    public String findPostByEmpid(HttpServletRequest request,String empid){
    	try {
    		if(StringUtils.isBlank(empid)){
    			return ResUtils.errRes("102", "请求参数错误");
    		}
    		
    		 UserPo po = userService.findUserByEmpid(Integer.parseInt(empid));
    		return ResUtils.okRes(po);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	/*
	 * 读取2007Excel
	 */
	private void read2007Excel(File destF, EmployeeService employeeService, Integer userId)
			throws IOException {

		List<EmployeePo> list = new LinkedList<EmployeePo>();
		// String path = System.getProperty("user.dir") +
		// System.getProperty("file.separator")+"dd.xlsx";
		// logger.info("路径："+path);
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(destF));

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
				EmployeePo linkedPo = row2007ToList(row,userId);
				list.add(linkedPo);
				if(list.size()==20000){
					insert(list, employeeService, userId);
					list.clear();
				}
			}
		}
		//剩余数据添加
		if(null!=list&&list.size()>0){
			insert(list, employeeService, userId);
			list.clear();
		}
	}
	/*
	 * 读取2007Excel每行数据
	 */
	private EmployeePo row2007ToList(XSSFRow row,Integer userId) {
		XSSFCell cell = null;
		Object value = null;
		List<Object> linked = new LinkedList<Object>();
		for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
			cell = row.getCell(j);
			if (cell == null) {
				linked.add(null);
				continue;
			}
			DecimalFormat df = new DecimalFormat("0");// 格式化 number String
			// 字符
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
			DecimalFormat nf = new DecimalFormat("0");// 格式化数字

			if (j == 1){
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			}
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
		EmployeePo po = new EmployeePo();
		po.setEmpname(String.valueOf(null!=linked.get(0)?linked.get(0):""));
		po.setEmpphone(String.valueOf(null!=linked.get(1)?linked.get(1):""));
		if (2 < linked.size()){
			po.setEmpweixin(String.valueOf(null!=linked.get(2)?linked.get(2):""));
		}
		if (3 < linked.size()){
			po.setEmpemail(String.valueOf(null!=linked.get(3)?linked.get(3):""));
		}	
		if (4 < linked.size() && null!=linked.get(4)){
			po.setEntrydate(DateUtils.format(String.valueOf(null!=linked.get(4)?linked.get(4):""), DateUtils.Y_M_D));
		}
		po.setCreateby(userId);
		po.setCreatedate(new Date());
		return po;
	}
	/*
	 * 插入员工数据
	 */
	private void insert(List<EmployeePo> list,EmployeeService employeeService, Integer userId){
		
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
				executor.execute(new empInsert(list.subList(j*pageCount, (j+1)*pageCount), cdl, employeeService));
			}else{
				executor.execute(new empInsert(list.subList(j*pageCount, list.size()), cdl, employeeService));
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
	
	static class empInsert extends Thread{
		private List<EmployeePo> list;
		private CountDownLatch cdl;
		private EmployeeService employeeService;
		public empInsert(List<EmployeePo> list, CountDownLatch cdl, EmployeeService employeeService){
			this.list = list;
			this.cdl = cdl;
			this.employeeService = employeeService;
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
			
			employeeService.addBatchList(list);
			cdl.countDown();
		}
	}
	
	/**
	 * 根据userid获取当前用户所有下属的员工的信息（包含自身）
	 * @param request
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/findChildEmployeeByUserid")
	public String findChildEmployeeByUserid(HttpServletRequest request,String userid){
		try {
			if(StringUtils.isBlank(userid)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			UserPo userPo = userService.findOneById(Integer.parseInt(userid));
			List<Integer> orgids = organizeService.findChildOrgidsByorgid(userPo.getOrgid());
			orgids.add(userPo.getOrgid());
			
			List<UserPo> userPos = userService.findAllByOgrId(orgids);
			List<Integer> userids = ReflectionUtil.convertElementPropertyToList(userPos,"userid");
			List<OrgPostVo> orgPostVos = organizeService.findMoreInfoByUserId(userids);
			
			List<EluiSelectVo> orgList = new ArrayList<>();
				for (OrgPostVo orgPostVo : orgPostVos) {
					EluiSelectVo eluiSelectVo = new EluiSelectVo();
					eluiSelectVo.setSearchkey(orgPostVo.getUserid());
					eluiSelectVo.setValue(orgPostVo.getPetname());
					orgList.add(eluiSelectVo);
				}
			
			return ResUtils.okRes(orgList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}

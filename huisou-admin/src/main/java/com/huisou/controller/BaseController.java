package com.huisou.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.common.FileUtil;
import com.common.MD5Util;
import com.huisou.constant.ContextConstant;
import com.huisou.po.CustomerUserPo;
import com.huisou.po.SalesManPo;
import com.huisou.po.UserPo;
import com.huisou.vo.CustomerVO;

@Controller
@SessionAttributes
public class BaseController {
	private  static  final Logger logger = LoggerFactory.getLogger(BaseController.class);
	private Converter converter=null;

	public UserPo getUserPo(HttpServletRequest request){
		String token = "";
		if("1".equals(FileUtil.getApplicationPro("http.token"))){
			token = request.getHeader(ContextConstant.SESSION_TOKEN);
			if(StringUtils.isBlank(token)){
				token = request.getParameter(ContextConstant.SESSION_TOKEN);
			}
    	}
		UserPo userPo = (UserPo)request.getSession().getAttribute(ContextConstant.USER_SESSION+token);
		return userPo;
	}
	public int getUserId(HttpServletRequest request){
		return null!=getUserPo(request)?getUserPo(request).getUserid():0;
	}
	//获取客户Po
	public CustomerUserPo getCustomerUserPo(HttpServletRequest request){
		String token = "";
		if("1".equals(FileUtil.getApplicationPro("http.token"))){
			token = request.getHeader(ContextConstant.SESSION_TOKEN);
			if(StringUtils.isBlank(token)){
				token = request.getParameter(ContextConstant.SESSION_TOKEN);
			}
		}
		CustomerUserPo custUserPo = (CustomerUserPo)request.getSession().getAttribute(ContextConstant.CUST_SESSION+token);
		return custUserPo;
	}
	
	//获取客户id
	public int getCustomerUserId(HttpServletRequest request){
		return null!=getCustomerUserPo(request)?getCustomerUserPo(request).getCustuserid():0;
	}
	//业务员返回true，其余返回false
	public boolean getLeader(HttpServletRequest request){
		if(null!=getUserPo(request)&&null!=getUserPo(request).getLeader()&&getUserPo(request).getLeader()==0){
			return true;
		}
		return false;
	}
	
	public String getUserName(HttpServletRequest request){
		return null!=getUserPo(request)?getUserPo(request).getUsername():"";
	}
	
	public SalesManPo getSale(HttpServletRequest request){
		String token = "";
		if("1".equals(FileUtil.getApplicationPro("http.token"))){
			token = request.getHeader(ContextConstant.SESSION_TOKEN);
			if(StringUtils.isBlank(token)){
				token = request.getParameter(ContextConstant.SESSION_TOKEN);
			}
    	}
		SalesManPo salePo = (SalesManPo)request.getSession().getAttribute(ContextConstant.SALE_SESSION+token);
		if(null==salePo){
			salePo = new SalesManPo();
		}
		return salePo;
	}
	
	public int getSaleId(HttpServletRequest request){
		return null!=getSale(request).getSaleid()?getSale(request).getSaleid():0;
	}
	
	public String getSaleName(HttpServletRequest request){
		return StringUtils.isNotBlank(getSale(request).getSalename())?getSale(request).getSalename():"";
	}
	
	public Integer getTimeSpan(HttpServletRequest request){
		String token = "";
		if("1".equals(FileUtil.getApplicationPro("http.token"))){
			token = request.getHeader(ContextConstant.SESSION_TOKEN);
			if(StringUtils.isBlank(token)){
				token = request.getParameter(ContextConstant.SESSION_TOKEN);
			}
    	}
		return (Integer)request.getSession().getAttribute(ContextConstant.SALE_TIMESPAN+token);
	}
	/**
	 * 用来在beanutils中注入对应的转换器，防止转换data的类型的时候报错
	 */
	
	public void registerConvert(){
		 converter = new Converter()  
        {  
            @SuppressWarnings("rawtypes")  
            @Override  
            public Object convert(Class arg0, Object arg1)  
            {  
                logger.info("注册字符串转换为date类型转换器");  
                if(arg1 == null)  
                {  
                    return null;  
                }  
               if(arg1 instanceof String[]) {
                	String[] a = (String[]) arg1;
                	if ((a[0] instanceof String)) {
                		return transDate(a[0]);
					}
				}
               
               if (arg1 instanceof String) {
            	   return transDate(arg1);  
               }
                   
               throw new ConversionException("只支持字符串转换 !和date");  
            }

			private Object transDate(Object arg1) {
				String str = (String)arg1;  
                if(str.trim().equals(""))  
                {  
                    return null;  
                }  
                   
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");  
                   
                try{  
                    return sd.parse(str);  
                }  
                catch(ParseException e)  
                {  
                    throw new RuntimeException(e);  
                }
			}  
               
        };
		  ConvertUtils.register(converter, java.util.Date.class);  
	}
	
	public void removeRegister(){
		ConvertUtils.deregister();;
	}
	public HSSFWorkbook export(List<CustomerVO> list) {
		String[] excelHeader = { "联系人", "手机号", "微信号", "邮箱", "公司地址", "业务员",
				"项目类型", "到期时间", "项目名称" ,"客户状态","缴费状况","项目简称","文本"};
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("待续费客户列表");
		// 设置列的宽度
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFRow row = sheet.createRow((int) 0);
		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
		}
		sheet.autoSizeColumn(4, true);
		for (int i = 0; i < list.size(); i++) {
			CustomerVO customerVO = list.get(i);
			row = sheet.createRow(i + 1);
			HSSFCell createCell = row.createCell(0);
			createCell.setCellValue(customerVO.getContact());
			createCell.setCellStyle(style);

			HSSFCell createCell1 = row.createCell(1);
			createCell1.setCellValue(customerVO.getPhone());
			createCell1.setCellStyle(style);

			HSSFCell createCell2 = row.createCell(2);
			createCell2.setCellValue(customerVO.getWeixin());
			createCell2.setCellStyle(style);

			HSSFCell createCell3 = row.createCell(3);
			createCell3.setCellValue(customerVO.getEmail());
			createCell3.setCellStyle(style);

			HSSFCell createCell4 = row.createCell(4);
			createCell4.setCellValue(customerVO.getAddressInfo());
			createCell4.setCellStyle(style);

			HSSFCell createCell5 = row.createCell(5);
			createCell5.setCellValue(customerVO.getSalename());
			createCell5.setCellStyle(style);

			HSSFCell createCell6 = row.createCell(6);
			createCell6.setCellValue(customerVO.getItemtypename());
			createCell6.setCellStyle(style);

			HSSFCell createCell7 = row.createCell(7);
			createCell7.setCellValue(customerVO.getExpirationTime());
			createCell7.setCellStyle(style);

			HSSFCell createCell8 = row.createCell(8);
			createCell8.setCellValue(customerVO.getItemname());
			createCell8.setCellStyle(style);
			
			HSSFCell createCell9 = row.createCell(9);
			createCell9.setCellValue(customerVO.getCuststatusName());
			createCell9.setCellStyle(style);
			
			HSSFCell createCell10 = row.createCell(10);
			createCell10.setCellValue(customerVO.getAuditstatusname());
			createCell10.setCellStyle(style);
			
			HSSFCell createCell11 = row.createCell(11);
			createCell11.setCellValue(customerVO.getStandby2());
			createCell11.setCellStyle(style);
			
			HSSFCell createCell12 = row.createCell(12);
			createCell12.setCellValue(customerVO.getStandby4());
			createCell12.setCellStyle(style);
		}
		sheet.setColumnWidth(0, 256 * 14 );
		sheet.setColumnWidth(1, 256 * 20 + 184);
		sheet.setColumnWidth(2, 256 * 20 + 184);
		sheet.setColumnWidth(3, 256 * 20 + 184);
		sheet.autoSizeColumn(4, true);
		sheet.setColumnWidth(5, 256 * 14 );
		sheet.setColumnWidth(6, 256 * 14 );
		sheet.setColumnWidth(7, 256 * 14 );
		sheet.setColumnWidth(8, 256 * 14 );
		sheet.setColumnWidth(9, 256 * 14 );
		sheet.setColumnWidth(10, 256 * 14 );
		sheet.setColumnWidth(11, 256 * 14 );
		sheet.setColumnWidth(12, 256 * 14 );

		return wb;
	}
}

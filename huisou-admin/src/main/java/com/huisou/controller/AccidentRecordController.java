package com.huisou.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.common.ConvertUtil;
import com.common.DateUtils;
import com.common.ResUtils;
import com.common.UploadUtils;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.huisou.constant.ContextConstant;
import com.huisou.po.AccidentRecordPo;
import com.huisou.po.BigCustomePo;
import com.huisou.po.EmployeePo;
import com.huisou.po.SalesManPo;
import com.huisou.po.UserPo;
import com.huisou.po.ItemsPo;
import com.huisou.po.PicRecordPo;
import com.huisou.service.AccidentRecordService;
import com.huisou.service.EmployeeService;
import com.huisou.service.ItemService;
import com.huisou.service.OrganizeService;
import com.huisou.service.PicRecordService;
import com.huisou.service.UserService;
import com.huisou.vo.AccidentVo;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2017年10月13日
*/

@RestController
@RequestMapping(value = "/accident")
public class AccidentRecordController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(AccidentRecordController.class);
	
	@Autowired
	AccidentRecordService accidentRecordService;
	
    @Autowired
    OrganizeService organizeService;
	
	@Autowired
	ItemService itemService;
	
    @Autowired
    PicRecordService picRecordService;
    
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
    UserService userService;
	
    @Value(value = "${image.url}")
    private String docUrl;
    @Value(value = "${doc.prefix3}")
    private String docPrefix;
    
    
	/*
	 * 事故记录查询列表
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,PageTemp pageTemp){
		try {
			String itemname = StringUtils.stripToEmpty(request.getParameter("itemname"));
			String responsible = StringUtils.stripToEmpty(request.getParameter("responsible"));
			String groupresponsible = StringUtils.stripToEmpty(request.getParameter("groupresponsible"));
			String accidentid = request.getParameter("accidentid");
			Map<String, String> maps = new HashMap<String, String>();
			maps.put("itemname", itemname);
			maps.put("responsible", responsible);
			maps.put("groupresponsible", groupresponsible);
			
			if (StringUtils.isNotBlank(accidentid)){
				if(Integer.parseInt(accidentid) > 0){
					AccidentRecordPo po = 
							accidentRecordService.queryAccidentPoById(Integer.parseInt(accidentid));
					AccidentVo accidentVo = new AccidentVo();
					accidentVo = (AccidentVo) ConvertUtil.convertDtoAndVo(po, AccidentVo.class);
					List<String> zips = picRecordService.
			        		findInfoByTypeAndId(ContextConstant.FILES_STEM_SG, Integer.parseInt(accidentid), ContextConstant.ACCIDENT_FILE);
					accidentVo.setZips(zips);
					return ResUtils.okRes(accidentVo);
				}
				else {
					return ResUtils.errRes("102", "请求的事故记录id有误"); 
				}
			}else{
				PageInfo<AccidentRecordPo> poList = accidentRecordService.queryByMultiParas(maps,pageTemp);
		        return ResUtils.okRes(poList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.exceCode;
		}
	}
	
	/*
	 * 保存一条事故记录
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam(required = false, value = "zips[]") List<String> zips,
			HttpServletRequest request){
		try {			
			String department = request.getParameter("department");
			if(StringUtils.isBlank(department)){
				return ResUtils.errRes("102", "请求参数部门id错误"); 
			}
			
			AccidentRecordPo accidentRecordPo = new AccidentRecordPo();
			
			if (StringUtils.isNotBlank(request.getParameter("time"))){
				accidentRecordPo.setTime(DateUtils.format(request.getParameter("time"), DateUtils.Y_M_D));
			}
			
			if (StringUtils.isNotBlank(request.getParameter("itemid"))){
				accidentRecordPo.setItemid(Integer.parseInt(request.getParameter("itemid")));
			}
			if (StringUtils.isNotBlank(request.getParameter("responsibleid"))){
				accidentRecordPo.setResponsibleid(Integer.parseInt(request.getParameter("responsibleid")));
			}
			if (StringUtils.isNotBlank(request.getParameter("groupresid"))){
				accidentRecordPo.setGroupresid(Integer.parseInt(request.getParameter("groupresid")));
			}

			accidentRecordPo.setItemname(request.getParameter("itemname"));
			accidentRecordPo.setDescription(request.getParameter("description"));
			accidentRecordPo.setCause(request.getParameter("cause"));
			accidentRecordPo.setSolution(request.getParameter("solution"));
			accidentRecordPo.setStatus(Integer.valueOf(request.getParameter("status")));
			accidentRecordPo.setDepartment(Integer.valueOf(request.getParameter("department")));
			accidentRecordPo.setResponsible(request.getParameter("responsible"));
			accidentRecordPo.setGroupresponsible(request.getParameter("groupresponsible"));
			int accidentid = accidentRecordService.insertAccidentPo(accidentRecordPo);
			
			// 文档保存
			if (accidentid > 0) {
				List<PicRecordPo> list = new ArrayList<>();
				if (zips != null && zips.size() > 0) {
		            createPic(list, zips, ContextConstant.FILES_STEM_SG, ContextConstant.ACCIDENT_FILE, accidentid, getUserId(request));
		        }
		        if (list.size() > 0) {
		            picRecordService.insertList(list);
		        }
		        return ResUtils.okRes();
			}
			return ResUtils.execRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.errRes("102", "请求参数解析错误"); 
		}
	}
	
    /**
     * 构造 pic对象
     *
     * @param list    pic对象集合
     * @param urls    图片或者文档地址集合
     * @param stem    来源于哪个（当前是日志）
     * @param type    类型( 图片还是文档）
     * @param dailyid 日志id
     * @param userid  用户id
     * @return
     */
    public List<PicRecordPo> createPic(List<PicRecordPo> list, List<String> urls, String stem, Integer type, Integer accidentid, Integer userid) {
        for (String url : urls) {
            PicRecordPo picRecordPo = new PicRecordPo();
            picRecordPo.setCreateby(userid);
            picRecordPo.setCreatedate(new Date());
            picRecordPo.setFromid(accidentid);
            picRecordPo.setStemfrom(stem);
            picRecordPo.setPictype(type);
            picRecordPo.setPicurl(url);
            picRecordPo.setItemid(-1);  // 用不到 但是不能为null 就设为-1
            picRecordPo.setPicstatus(1);
            list.add(picRecordPo);
        }
        return list;
    }
	/*
	 * 更新一条事故记录
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@RequestParam(required = false, value = "zips[]") List<String> zips,
			HttpServletRequest request){
		try {			
			String accidentid = request.getParameter("accidentid");
			if(StringUtils.isBlank(accidentid) || Integer.parseInt(accidentid) < 0){
				return ResUtils.errRes("102", "请求的事故记录id有误"); 
			}
			String department = request.getParameter("department");
			if(StringUtils.isBlank(department)){
				return ResUtils.errRes("102", "请求参数部门id错误"); 
			}
			AccidentRecordPo accidentRecordPo = 
					accidentRecordService.queryAccidentPoById(Integer.parseInt(accidentid));
			
			if (StringUtils.isNotBlank(request.getParameter("time"))){
				accidentRecordPo.setTime(DateUtils.format(request.getParameter("time"), DateUtils.Y_M_D));
			}
			
			if (StringUtils.isBlank(request.getParameter("itemid"))){
				accidentRecordPo.setItemid(0);
			}else{
				accidentRecordPo.setItemid(Integer.parseInt(request.getParameter("itemid")));
			}
			if (StringUtils.isBlank(request.getParameter("responsibleid"))){
				accidentRecordPo.setResponsibleid(0);
			}else {
				accidentRecordPo.setResponsibleid(Integer.parseInt(request.getParameter("responsibleid")));
			}
			if (StringUtils.isBlank(request.getParameter("groupresid"))){
				accidentRecordPo.setGroupresid(0);
			}else {
				accidentRecordPo.setGroupresid(Integer.parseInt(request.getParameter("groupresid")));
			}
	
			accidentRecordPo.setItemname(request.getParameter("itemname"));
			accidentRecordPo.setDescription(request.getParameter("description"));
			accidentRecordPo.setCause(request.getParameter("cause"));
			accidentRecordPo.setSolution(request.getParameter("solution"));
			accidentRecordPo.setStatus(Integer.valueOf(request.getParameter("status")));
			accidentRecordPo.setSolve(Integer.valueOf(request.getParameter("solve")));
			accidentRecordPo.setDepartment(Integer.valueOf(request.getParameter("department")));
			accidentRecordPo.setResponsible(request.getParameter("responsible"));
			accidentRecordPo.setGroupresponsible(request.getParameter("groupresponsible"));
			
			if (accidentRecordService.updateAccidentPo(accidentRecordPo) > 0){
				List<PicRecordPo> list = new ArrayList<>();
				if (zips != null && zips.size() > 0) {
		            createPic(list, zips, ContextConstant.FILES_STEM_SG, ContextConstant.ACCIDENT_FILE, Integer.parseInt(accidentid), getUserId(request));
		        }
		        if (list.size() > 0) {
		            picRecordService.insertList(list);
		        }
		        return ResUtils.okRes();
			}
			
			return ResUtils.execRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.errRes("102", "请求参数解析错误"); 
		}
	}
	
    /**
     * 上传文件
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploaddoc", method = RequestMethod.POST)
    public String accidentUploadDoc(MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {
            // 保存的目录使用userid
            int userId = getUserId(request);
            // 保存文件到对应的服务器中去
            String docname = UploadUtils.uploaddoc(file, docUrl, userId);
            if (docname.startsWith("70") && docname.length() < 4) {
                return ResUtils.execRes(docname);
            }
            HashMap<String, Object> map = new HashMap<String, Object>();
            // 访问文件的路径
            String url = docPrefix + userId + "/" + docname;
            map.put("docurl", url);
            map.put("docname", docname);
            return ResUtils.okRes(map);
        } else {
            return ResUtils.errRes("102", "文件不能为空");
        }
    }
    
    /*
     * 更新上传文件的状态为删除
     */
    public void deleteNotContentFile(String deleteUrl, Integer fromid){
    	picRecordService.selectByFromId(deleteUrl, fromid);
    }
    
    /**
     * 删除服务器上面已存在的文件
     *
     * @param docName
     * @return
     */
    @RequestMapping(value = "/delfile", method = RequestMethod.GET)
    public String deleteFile(String docName, HttpServletRequest request) {
    	if (StringUtils.isNotBlank(request.getParameter("accidentid"))){
        	int accidentid = Integer.parseInt(request.getParameter("accidentid"));
        	deleteNotContentFile(docName, accidentid);
    	}
        String[] split = docName.split("/");
        int length = split.length;
        docName = split[length - 1];
        int userId = getUserId(request);
        File file = new File(docUrl + userId + "/" + docName);        
        if (file.exists()) {
            file.delete();
        }
        return ResUtils.okRes();
    }
    
    /*
     * 事故记录文件回显
     */
    @RequestMapping(value = "/doRead", method = RequestMethod.POST)
    public String doRead(String accidentid, HttpServletRequest request){
    	try {
			if (StringUtils.isNotBlank(accidentid) && Integer.parseInt(accidentid) > 0){
		        List<String> zips = picRecordService.
		        		findInfoByTypeAndId(ContextConstant.FILES_STEM_SG, Integer.parseInt(accidentid), ContextConstant.ACCIDENT_FILE);
		        HashMap<String, List<String>> zipMap = new HashMap<>();
		        zipMap.put("zips", zips);
		        return ResUtils.okRes(zipMap);
			} else{
				return ResUtils.errRes("102", "请求参数解析错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.errRes("102", "请求参数解析错误");
		}

    }
}

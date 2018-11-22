package com.huisou.controller;

import com.common.*;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.huisou.constant.ContextConstant;
import com.huisou.po.DailyPo;
import com.huisou.po.PicRecordPo;
import com.huisou.po.PostPo;
import com.huisou.po.UserPo;
import com.huisou.service.*;
import com.huisou.vo.DailyInfoVo;
import com.huisou.vo.DailyVo;
import com.huisou.vo.OrgPostVo;
import com.huisou.vo.PageTemp;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * author： xueyuan
 * date  ： 2017-08-30 下午4:03
 */
@RestController
@RequestMapping(value = "/daily")
public class DailyController extends BaseController {


    @Autowired
    DailyService dailyService;

    @Autowired
    OrganizeService organizeService;

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    PicRecordService picRecordService;

    @Value(value = "${image.url}")
    private String imageUrl;
    @Value(value = "${image.prefix2}")
    private String imagePrefix;
    @Value(value = "${doc.prefix2}")
    private String docPrefix;

    /**
     * 获取当前用户可查看的所有日志
     *
     * @param userid        当前用户的id（视为接受者）
     * @param status        日志被查看的状态标志
     * @param seacherUserId 查询的id,可视查询的用户id
     * @param type          日志类型：日 周 月
     * @param beginDate     起
     * @param endDate       止
     * @param pageTemp      分页
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(Integer userid, Integer status, Integer seacherUserId, String type, Integer orgId,
                       String beginDate, String endDate, PageTemp pageTemp, HttpServletRequest request) throws IllegalAccessException, InstantiationException {

        Map map = new HashMap<>();
        // 如果status 不为null 那说明是需要获取的是未查看的日志
        if (userid == null || userid < 0) {
            return ResUtils.errRes(ContextConstant.PARAM_NULL, "请求参数错误");
        }
        if (beginDate != null && !"".equals(beginDate)) {
            map.put("beginDate", beginDate);
        }
        if (endDate != null && !"".equals(endDate)) {
            map.put("endDate", endDate);
        }
        if (status != null) {
            map.put("status", status);
        }
        if (type != null && !type.equals("")) {
            map.put("type", type);
        }
        List<Integer> userids = new ArrayList<>();

        //=============== begin    ===============
        //  获取当前用户所有有权查看的用户的id
        List<Integer> orgIds = new ArrayList<>();


        // 判断是否有岗位信息，如果有岗位信息，则说明是领导，如果没有，则是组员，没有权限看其他的人的信息
        UserPo checkUser = userService.findOneById(userid);
        if (checkUser.getPostid() == null || checkUser.getPostid() < 0) {
            PageInfo pageInfo = new PageInfo();
            return ResUtils.okRes(pageInfo);
        }

        PostPo postPo = postService.findByFkId(checkUser.getPostid());

        orgIds = ReflectionUtil.convertElementPropertyToList(
                organizeService.findAllOrgId(postPo.getOrganizeid()), "orgid");
        // 如果大于1 则说明还有其他组织存在，需要岗位信息中，得到对应领导的信息
        if (orgIds.size() > 1) {
//            orgIds
            List<Integer> _postids = ReflectionUtil.convertElementPropertyToList(
                    postService.findByOrgId(orgIds), "postid");
            userids.addAll(ReflectionUtil.convertElementPropertyToList(
                    userService.findAllByPostId(_postids), "userid"));
            //把自己去除
            userids.remove(checkUser.getUserid());
        }
        //得到所有部门的员工
        userids.addAll(ReflectionUtil.convertElementPropertyToList(
                userService.findAllByOgrId(orgIds), "userid"));
        // 加上当前用户本身的 数据
//        userids.add(userid);
        //=============== end   ===============

        if (seacherUserId != null) {
            //只查询一个用户的日志情况
            List<Integer> tmp = new ArrayList<>();
            //只有当前用户有权限查看 方可继续查询
            if (userids.contains(seacherUserId)) {
                tmp.add(seacherUserId);
                map.put("userids", tmp);
            }
        } else if (orgId != null) {
            //根据depId查询部门的所有用户的日志
            List<Integer> _orgIds = new ArrayList<>();
            _orgIds.add(orgId);
            //获取部门所有的组员信息
            List<Integer> _userids = ReflectionUtil.convertElementPropertyToList(
                    userService.findAllByOgrId(_orgIds), "userid");
            //获取部分负责人信息
            List<Integer> _postids = ReflectionUtil.convertElementPropertyToList(
                    postService.findByOrgId(_orgIds), "postid");

            _userids.addAll(ReflectionUtil.convertElementPropertyToList(
                    userService.findAllByPostId(_postids), "userid"));
//            _userids.addAll(ReflectionUtil.convertElementPropertyToList(_postPos, "userid"));
            map.put("userids", _userids);
//            List<Integer> _orgIds = ReflectionUtil.convertElementPropertyToList(
//                    organizeService.findAllOrgId(orgId), "orgid");
//            List<Integer> tmp = new ArrayList<>();
//            _orgIds.add(orgId);
//
//            List<PostPo> _postPos = postService.findByOrgId(_orgIds);
//            tmp = ReflectionUtil.convertElementPropertyToList(_postPos, "userid");
//
//            if (tmp.size() > 0) {
//                userids.retainAll(tmp);
//                map.put("userids", userids);
//            }

        } else {
            //查询所有 有权查看的userid
            map.put("userids", userids);
        }

        map.put("pageNum", pageTemp.getPageNum());
        map.put("pageSize", pageTemp.getPageSize());

//        Map resMap = new HashMap();
        List<DailyPo> dailyPos = dailyService.findAll(map);
        PageInfo pageInfo = new PageInfo(dailyPos);

        //获取所有有权查看的 以及用户自己的信息，方便组织
        userids.add(userid);
        List<OrgPostVo> orgPostVos = organizeService.findMoreInfoByUserId(userids);

        List<DailyInfoVo> list = new ArrayList<>();
        for (DailyPo d : dailyPos) {
            DailyInfoVo tmp = (DailyInfoVo) ConvertUtil.convertDtoAndVo(d, DailyInfoVo.class);
            for (OrgPostVo o : orgPostVos) {
                //如果是领导
                if (o.getUserid() == d.getUserid()) {
                    if (StringUtils.isNotBlank(o.getDep2())) {
                        tmp.setOrgid(o.getOrgid());
                        tmp.setDepartname(o.getDep2());
                        tmp.setPostid(o.getPostid());
                        tmp.setPetname(o.getPetname());
                        tmp.setPostname(o.getPostname());
                    } else {
                        tmp.setOrgid(o.getOrgid());
                        tmp.setDepartname(o.getDepartname());
                        tmp.setPostid(o.getPostid());
                        tmp.setPetname(o.getPetname());
                        tmp.setPostname("成员");
                    }
                }
            }
            if (d.getReader() != null) {
                UserPo userPo = userService.findOneById(d.getReader());
                tmp.setReadername(userPo.getPetname());
            }
            list.add(tmp);
        }

        pageInfo.setList(list);
//        resMap.put("pageInfo", pageInfo);
        return ResUtils.okRes(pageInfo);
    }


    /**
     * 查看自己的所有日志详情
     *
     * @return
     */
    @RequestMapping(value = "/mylist", method = RequestMethod.POST)
    public String myList(Integer userid, String type, String beginDate, String endDate, PageTemp pageTemp, HttpServletRequest request) throws InstantiationException, IllegalAccessException {
        Map map = new HashMap<>();
        // 如果status 不为null 那说明是需要获取的是未查看的日志
        if (userid == null || userid < 0) {
            return ResUtils.errRes(ContextConstant.PARAM_NULL, "请求参数错误");
        }
        if (type != null && !type.equals("")) {
            map.put("type", type);
        }
        if (beginDate != null && !"".equals(beginDate)) {
            map.put("beginDate", beginDate);
        }
        if (endDate != null && !"".equals(endDate)) {
            map.put("endDate", endDate);
        }
        List<Integer> userids = new ArrayList<>();
        userids.add(userid);

        map.put("userids", userids);
        map.put("pageNum", pageTemp.getPageNum());
        map.put("pageSize", pageTemp.getPageSize());

        List<DailyPo> dailyPos = new ArrayList<>();

        dailyPos = dailyService.findAll(map);
        PageInfo pageInfo = new PageInfo(dailyPos);

        //获取所有有权查看的 用户信息
        List<OrgPostVo> orgPostVos = organizeService.findMoreInfoByUserId(userids);

        List<DailyInfoVo> list = new ArrayList<>();
        for (DailyPo d : dailyPos) {
            DailyInfoVo tmp = new DailyInfoVo();
            tmp = (DailyInfoVo) ConvertUtil.convertDtoAndVo(d, DailyInfoVo.class);
            for (OrgPostVo o : orgPostVos) {
                if (o.getUserid() == d.getUserid()) {
                    if (StringUtils.isNotBlank(o.getDep2())) {
                        tmp.setOrgid(o.getOrgid());
                        tmp.setDepartname(o.getDep2());
                        tmp.setPostid(o.getPostid());
                        tmp.setPetname(o.getPetname());
                        tmp.setPostname(o.getPostname());
                    } else {
                        tmp.setOrgid(o.getOrgid());
                        tmp.setDepartname(o.getDepartname());
                        tmp.setPostid(o.getPostid());
                        tmp.setPetname(o.getPetname());
                        tmp.setPostname("成员");
                    }
                }
            }
            if (d.getReader() != null) {
                UserPo userPo = userService.findOneById(d.getReader());
                tmp.setReadername(userPo.getPetname());
            }
            list.add(tmp);
        }

        pageInfo.setList(list);
        return ResUtils.okRes(pageInfo);
    }

    /**
     * 对提交的日志已读后 做出对应更新
     *
     * @param dailyid
     * @return
     */
    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public String read(Integer dailyid, HttpServletRequest request) throws InstantiationException, IllegalAccessException {
        DailyPo dailyPo = dailyService.findOne(dailyid);
        DailyInfoVo tmp = new DailyInfoVo();
        //如果当前数据未被阅读，现在是取得当前数据 且更改状态
        if (dailyPo != null) {
            if (dailyPo.getStatus() == 0 && dailyPo.getUserid() != getUserId(request)) {
                dailyPo.setStatus(1);
                dailyService.updateStatus(dailyid);
            }
            tmp = (DailyInfoVo) ConvertUtil.convertDtoAndVo(dailyPo, DailyInfoVo.class);

            List<Integer> userids = new ArrayList<>();
            userids.add(tmp.getUserid());
            List<OrgPostVo> orgPostVos = organizeService.findMoreInfoByUserId(userids);

            if (StringUtils.isNotBlank(orgPostVos.get(0).getDep2())) {
                tmp.setOrgid(orgPostVos.get(0).getOrgid());
                tmp.setDepartname(orgPostVos.get(0).getDep2());
                tmp.setPostid(orgPostVos.get(0).getPostid());
                tmp.setPetname(orgPostVos.get(0).getPetname());
                tmp.setPostname(orgPostVos.get(0).getPostname());
            } else {
                tmp.setOrgid(orgPostVos.get(0).getOrgid());
                tmp.setDepartname(orgPostVos.get(0).getDepartname());
                tmp.setPostid(orgPostVos.get(0).getPostid());
                tmp.setPetname(orgPostVos.get(0).getPetname());
                tmp.setPostname("成员");
            }
            UserPo userPo = userService.findOneById(tmp.getReader());
            tmp.setReadername(userPo.getPetname());

            // 当前日志 所有图片
            List<String> imgs = picRecordService.findInfoByTypeAndId(ContextConstant.FILES_STEM_RZ, dailyid, ContextConstant.DAILI_PIC);
            //当前日志 所有附件压缩包
            List<String> zips = picRecordService.findInfoByTypeAndId(ContextConstant.FILES_STEM_RZ, dailyid, ContextConstant.DAILI_FILE);
            tmp.setImgs(imgs);
            tmp.setZips(zips);
//            dailyVo.setOrgPostVo(orgPostVo);
            return ResUtils.okRes(tmp);
        }
        return ResUtils.execRes();
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
     * 更新 评论
     *
     * @param dailyVo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(DailyVo dailyVo, @RequestParam(required = false, value = "imgs[]") List<String> imgs,
                         @RequestParam(required = false, value = "zips[]") List<String> zips, HttpServletRequest request) throws InstantiationException, IllegalAccessException {

        DailyPo dailyPo = (DailyPo) ConvertUtil.convertDtoAndVo(dailyVo, DailyPo.class);
        if (dailyService.updateDaily(dailyPo) > 0) {
	        List<PicRecordPo> list = new ArrayList<>();
	        if (imgs != null && imgs.size() > 0) {
	            createPic(list, imgs, ContextConstant.FILES_STEM_RZ, ContextConstant.DAILI_PIC, dailyPo.getDailyid(), getUserId(request));
	        }
	        if (zips != null && zips.size() > 0) {
	            createPic(list, zips, ContextConstant.FILES_STEM_RZ, ContextConstant.DAILI_FILE, dailyPo.getDailyid(), getUserId(request));
	        }
	        if (list.size() > 0) {
	            picRecordService.insertList(list);
	        }
	        return ResUtils.okRes();
        }
        return ResUtils.execRes();
    }
    
    /*
     * 更新上传文件的状态为删除
     */
    public void deleteNotContentFile(String deleteUrl, Integer fromid){
    	picRecordService.selectByFromId(deleteUrl, fromid);
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
    public List<PicRecordPo> createPic(List<PicRecordPo> list, List<String> urls, String stem, Integer type, Integer dailyid, Integer userid) {
        for (String url : urls) {
            PicRecordPo picRecordPo = new PicRecordPo();
            picRecordPo.setCreateby(userid);
            picRecordPo.setCreatedate(new Date());
            picRecordPo.setFromid(dailyid);
            picRecordPo.setStemfrom(stem);
            picRecordPo.setPictype(type);
            picRecordPo.setPicurl(url);
            picRecordPo.setItemid(-1);  // 用不到 但是不能为null 就设为-1
            picRecordPo.setPicstatus(1);
            list.add(picRecordPo);
        }
        return list;
    }

    /**
     * 保存日志
     *
     * @param dailyVo
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(DailyVo dailyVo, @RequestParam(required = false, value = "imgs[]") List<String> imgs,
                       @RequestParam(required = false, value = "zips[]") List<String> zips, HttpServletRequest request) throws InstantiationException, IllegalAccessException {
        if (dailyVo.getUserid() == null || dailyVo.getUserid() < 0) {
            return ResUtils.errRes(ContextConstant.PARAM_NULL, "请求参数错误");
        }
        DailyPo dailyPo = (DailyPo) ConvertUtil.convertDtoAndVo(dailyVo, DailyPo.class);
        //插入新数据时候，当前数据未被阅读
        dailyPo.setStatus(0);
        dailyPo.setCreatetime(DateUtil.formatDate(new Date()));

        if (dailyService.insert(dailyPo) > 0) {
            List<PicRecordPo> list = new ArrayList<>();
            if (imgs != null && imgs.size() > 0) {
                createPic(list, imgs, ContextConstant.FILES_STEM_RZ, ContextConstant.DAILI_PIC, dailyPo.getDailyid(), getUserId(request));
            }
            if (zips != null && zips.size() > 0) {
                createPic(list, zips, ContextConstant.FILES_STEM_RZ, ContextConstant.DAILI_FILE, dailyPo.getDailyid(), getUserId(request));
            }
            if (list.size() > 0) {
                picRecordService.insertList(list);
            }
            return ResUtils.okRes();
        }
        return ResUtils.execRes();
    }


    /**
     * 未阅读的日志条数 不包含自己
     *
     * @param userid
     * @return
     */
    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public String unReadDaily(Integer userid) {
        Map map = new HashMap<>();
        // 如果status 不为null 那说明是需要获取的是未查看的日志
        if (userid == null || userid < 0) {
            return ResUtils.errRes(ContextConstant.PARAM_NULL, "请求参数错误");
        }
        map.put("status", 0);
        map.put("reader", userid);
        List<DailyPo> list = dailyService.findAllUnRead(map);

        return ResUtils.okRes(list.size());
    }


    /**
     * 上传图片
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadimgs", method = RequestMethod.POST)
    public String dailyUploadImg(MultipartFile file, HttpServletRequest request) {
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
            return ResUtils.okRes(map);
        } else {
            return ResUtils.errRes("102", "文件不能为空");
        }
    }

    /**
     * 上传压缩文件
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploaddoc", method = RequestMethod.POST)
    public String dailyUploadDoc(MultipartFile file, HttpServletRequest request) {
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

    @RequestMapping(value = "/filedown/{dir}/{docName}")
    public String docDonwload(HttpServletRequest request,
                              HttpServletResponse response, @PathVariable("dir") String dir,
                              @PathVariable("docName") String docName) {
        String requestURI = request.getRequestURI();
        String suffix = requestURI.substring(requestURI.lastIndexOf(".") + 1);
        if (StringUtils.isBlank(docName)) {
            return ResUtils.execRes();
        }
        OutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        try {
            docName = docName + "." + suffix;
            String url = imageUrl + dir + "/" + docName;

            fileInputStream = new FileInputStream(url);
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
     * 删除服务器上面已存在的文件
     *
     * @param imageName
     * @return
     */
    @RequestMapping(value = "/delfile", method = RequestMethod.GET)
    public String deleteFile(String imageName, HttpServletRequest request) {
//    	String deleteUrl = StringUtils.substringBefore(imageName, "?");
    	if (StringUtils.isNotBlank(request.getParameter("dailyid"))){
        	int dailyid = Integer.parseInt(request.getParameter("dailyid"));
        	deleteNotContentFile(imageName, dailyid);
    	}
        String[] split = imageName.split("/");
        int length = split.length;
        imageName = split[length - 1];
//        imageName = StringUtils.substringBefore(imageName, "?");
        int userId = getUserId(request);
        File file = new File(imageUrl + userId + "/" + imageName);        
        if (file.exists()) {
            file.delete();
        }
        return ResUtils.okRes();
    }

}

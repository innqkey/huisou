package com.huisou.constant;

import org.apache.commons.lang.StringUtils;


public class DictConConstant {

	public enum Itemtype{
        //通过括号赋值,而且必须带有一个参构造器和一个属性跟方法，否则编译出错
        //赋值必须都赋值或都不赋值，不能一部分赋值一部分不赋值；如果不赋值则不能写构造器，赋值编译也出错
		//1，企业版  2，门户版 3，电商版 4，微商app 5，小程序
		itemtype1(1,"企业版"), itemtype2(2,"门户版"), itemtype3(3,"电商版"), itemtype4(4,"微商app"), itemtype5(5,"独立小程序"), itemtype6(6,"阿凡提"), itemtype7(7,"汇搜云");
        
		private int i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        Itemtype(int i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public int getI() {
            return i;
        }
    }
	public enum ItemDetailType{
		itemdetailtype0(0,""), itemdetailtype1(1,"基础版"), itemdetailtype2(2,"小程序版"), itemdetailtype3(3,"高级版"), itemdetailtype4(4,"至尊版");
        
		private int i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        ItemDetailType(int i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public int getI() {
            return i;
        }
    }
	public enum VisitWay{
		visitway1(1,"电话回访"),visitway2(2,"到访"),visitway3(3,"邮件回访"),visitway4(4,"微信回访"),visitway5(5,"qq回访"),visitway6(6,"日常备注");
		
		private Integer i;
		private String val;
		
		private VisitWay(Integer i,String val) {
			this.i=i;
			this.val=val;
		}

		public Integer getI() {
			return i;
		}

		public void setI(Integer i) {
			this.i = i;
		}

		public String getVal() {
			return val;
		}

		public void setVal(String val) {
			this.val = val;
		}
		
		
	}
	
	
	
	public enum Custstatus{
		//客户状态:1,正常;2,禁用;3,待续费
		custstatus1(1,"正常"), custstatus2(2,"禁用"), custstatus3(3,"待续费");
        
		private int i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        Custstatus(int i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public int getI() {
            return i;
        }
    }
	
	public enum Dealstatus{
		//需求处理状态:0,未处理;1,已处理
		dealstatus1(0,"未处理"), dealstatus2(1,"已处理");
        
		private int i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        Dealstatus(int i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public int getI() {
            return i;
        }
    }
	
	public enum Tradeway{
		//付费方式：1支付宝;2微信;3网银
		Tradeway1(1,"支付宝"), Tradeway2(2,"微信"), Tradeway3(3,"网银");
        
		private int i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        Tradeway(int i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public int getI() {
            return i;
        }
    }
	
	public enum Tradetype{
		//缴费类型：1正常；2订金；3尾款;4续费;5退款
		Tradetype0(0,"新建"),Tradetype1(1,"正常"), Tradetype2(2,"订金"), Tradetype3(3,"尾款"),Tradetype4(4,"续费"), Tradetype5(5,"退款"),Tradetype6(6,"全款"),Tradetype7(7,"终止");
        
		private int i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        Tradetype(int i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public int getI() {
            return i;
        }
    }
	
	public enum Evaluationtype{
		//评价类型：1好;2一般;3差
		Evaluationtype1(1,"好"), Evaluationtype2(2,"一般"), Evaluationtype3(3,"差");
        
		private int i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        Evaluationtype(int i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public int getI() {
            return i;
        }
    }
	
	public enum Evaluatetype{
		Evaluatetype1(1,"差"),Evaluatetype2(2,"一般"),Evaluatetype3(3,"中等"),Evaluatetype4(4,"良好"),Evaluatetype5(5,"优");
		
		private int i;
        private String val;
        Evaluatetype(int i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public int getI() {
            return i;
        }
	}
	
	public enum YesOrNoType{
		YesOrNoType1(1,"是"),YesOrNoType2(0,"否");
		private int i;
        private String val;
        YesOrNoType(int i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public int getI() {
            return i;
        }
	}
	
	
	public enum Auditstatus{
		//审核状态：1审核通过;2审核不通过
		Auditstatus0(0,"未审核"),Auditstatus1(1,"审核通过"), Auditstatus2(2,"审核不通过");
        
		private int i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        Auditstatus(int i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public int getI() {
            return i;
        }
    }
	
	public enum ReplyStatus{
		ReplyStatus0(0,"不需要回复"),ReplyStatus1(1,"需要回复"),ReplyStatus2(2,"已回复");
		
		private int i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        ReplyStatus(int i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public int getI() {
            return i;
        }
	}
	
	public enum Commitstatus{
		//提交状态:1未提交-;2-已提交;3-审核通过;4-审核不通过
		Commitstatus0(0,""),Commitstatus1(1,"未提交"),Commitstatus2(2,"已提交"), Commitstatus3(3,"审核通过"),Commitstatus4(4,"审核不通过");
        
		private int i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        Commitstatus(int i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public int getI() {
            return i;
        }
    }
	


	public enum Relatecontstatus{
		//-1  等待客服审核  -2  审核失败  1  应用正常  2 美工上传素材   3  客服进行APP抢注   4 程序生成  5  系统初始化   6  客服添加数据   7  应用生成   8 应用上传 
		Relatecontstatus1(-1,"等待客服审核"), Relatecontstatus2(-2," 审核失败"), Relatecontstatus3(1,"应用正常"), Relatecontstatus4(2,"美工上传素材"), 
		Relatecontstatus5(3,"客服进行APP抢注"), Relatecontstatus6(4,"程序生成"), Relatecontstatus7(5,"系统初始化"), Relatecontstatus8(6,"客服添加数据"),
		Relatecontstatus9(7,"应用生成"), Relatecontstatus10(8,"应用上传");
        
		private int i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        Relatecontstatus(int i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public int getI() {
            return i;
        }
    }

	public enum NoticeType{
		//提交状态:1新增项目-;2-需求变更;3-业务员回复;4-需业务员回复
		NoticeType0(0,"提示"),NoticeType1(1,"新增项目"),NoticeType2(2,"需求变更"), NoticeType3(3,"业务员回复"),NoticeType4(4,"需业务员回复");
        
		private int i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        NoticeType(int i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public int getI() {
            return i;
        }
    }

	public static String getDicName(String type,Integer i){
		if(StringUtils.isBlank(type)||null==i){
			return "";
		}
		type = type.toUpperCase();
		if(DictConConstant.Itemtype.class.getName().toUpperCase().contains(type)){
			for(Itemtype itmeType : Itemtype.values()){
				if(itmeType.i==i){
					return itmeType.val;
				}
	        }
		}
		if(DictConConstant.ItemDetailType.class.getName().toUpperCase().contains(type)){
			for(ItemDetailType itmeType : ItemDetailType.values()){
				if(itmeType.i==i){
					return itmeType.val;
				}
			}
		}
		
		if(DictConConstant.Custstatus.class.getName().toUpperCase().contains(type)){
			for(Custstatus itmeType : Custstatus.values()){
				if(itmeType.i==i){
					return itmeType.val;
				}
	        }
		}
		
		if(DictConConstant.Dealstatus.class.getName().toUpperCase().contains(type)){
			for(Dealstatus itmeType : Dealstatus.values()){
				if(itmeType.i==i){
					return itmeType.val;
				}
	        }
		}
		
		if(DictConConstant.Tradeway.class.getName().toUpperCase().contains(type)){
			for(Tradeway itmeType : Tradeway.values()){
				if(itmeType.i==i){
					return itmeType.val;
				}
	        }
		}
		
		if(DictConConstant.Evaluationtype.class.getName().toUpperCase().contains(type)){
			for(Evaluationtype itmeType : Evaluationtype.values()){
				if(itmeType.i==i){
					return itmeType.val;
				}
	        }
		}
		
		if(DictConConstant.Tradetype.class.getName().toUpperCase().contains(type)){
			for(Tradetype itmeType : Tradetype.values()){
				if(itmeType.i==i){
					return itmeType.val;
				}
	        }
		}
		
		if(DictConConstant.Auditstatus.class.getName().toUpperCase().contains(type)){
			for(Auditstatus itmeType : Auditstatus.values()){
				if(itmeType.i==i){
					return itmeType.val;
				}
	        }
		}
		
		if(DictConConstant.VisitWay.class.getName().toUpperCase().contains(type)){
			for(VisitWay itmeType : VisitWay.values()){
				if(itmeType.i==i){
					return itmeType.val;
				}
	        }
		}
		if(DictConConstant.Commitstatus.class.getName().toUpperCase().contains(type)){
			for(Commitstatus itmeType : Commitstatus.values()){
				if(itmeType.i==i){
					return itmeType.val;
				}
	        }
		}


		if(DictConConstant.Relatecontstatus.class.getName().toUpperCase().contains(type)){
			for(Relatecontstatus itmeType : Relatecontstatus.values()){
				if(itmeType.i==i){
					return itmeType.val;
				}
	        }
		}



		if(DictConConstant.ReplyStatus.class.getName().toUpperCase().contains(type)){
			for(ReplyStatus itmeType : ReplyStatus.values()){
				if(itmeType.i==i){
					return itmeType.val;
				}
			}
		}
		if(DictConConstant.YesOrNoType.class.getName().toUpperCase().contains(type)){
			for(YesOrNoType itmeType : YesOrNoType.values()){
				if(itmeType.i==i){
					return itmeType.val;
				}
			}
		}
		
		if(DictConConstant.Evaluatetype.class.getName().toUpperCase().contains(type)){
			for(Evaluatetype itmeType : Evaluatetype.values()){
				if(itmeType.i==i){
					return itmeType.val;
				}
			}
		}
		
		

		if(DictConConstant.NoticeType.class.getName().toUpperCase().contains(type)){
			for(NoticeType itmeType : NoticeType.values()){
				if(itmeType.i==i){
					return itmeType.val;
				}
	        }
		}
		
		return null;
	}
	
}

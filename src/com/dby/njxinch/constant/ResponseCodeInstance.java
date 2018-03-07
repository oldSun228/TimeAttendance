package com.dby.njxinch.constant;

public enum ResponseCodeInstance {
	CODE_0000("0", "执行失败"),
	CODE_0001("1", "执行成功"),
	CODE_0002("0002" , "参数不正确"),
	CODE_0003("0003" , "当月已评价"),
	CODE_0010("10" , "用户凭证token错误"),
	CODE_1035("1035" , "司机姓名不能为空"),
	CODE_1036("1036" , "司机联系方式不能为空"),
	CODE_1037("1037" , "司机车牌号不能为空"),;
	

	private final String code;
	private final String message;

	private ResponseCodeInstance(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	public static void main(String[] args) {
		System.out.println(ResponseCodeInstance.CODE_0000.getMessage());
	}

}

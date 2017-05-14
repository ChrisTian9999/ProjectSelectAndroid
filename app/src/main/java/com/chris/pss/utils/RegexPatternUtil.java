package com.chris.pss.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author 
 *
 */
public class RegexPatternUtil {

	private static final String URL = "[a-zA-z]+://[^\\s]*";
	private static final String EMAIL = "\\w+@(\\w+.)+[a-z]{2,3}";
	private static final String LOGINNAME = "^[a-zA-Z0-9_]{3,20}$";
	private static final String PASSWORD = "^[a-zA-Z0-9_]{6,31}$";
	private static final String CHARS = "[`~!@#$%^&*()+ =|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
	private static final String TEL = "\\d{3}-\\d{8}|\\d{4}-\\d{8}|\\d{4}-\\d{7}|\\d{3}-\\d{7}|\\d{3}\\d{8}|\\d{4}\\d{8}|\\d{3}\\d{7}|\\d{4}\\d{7}";
	private static final String ZIP_CODE = "[1-9]\\d{5}(?!\\d)";
	private static final String ID_CARD_NO15 ="^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
	private static final String ID_CARD_NO18 ="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$";
	private static final String PHONE = "^((13[0-9])|(15[0,0-9])|(18[0,0-9])|(17[0,0-9])|(14[0,0-9]))\\d{8}$";
	private static final String NUMBER="^[0-9]+$";
	private static final String AMOUNT="^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$";//判断金额
	
	private static final String BANK_CARD_VJ="^[0-9]{16,19}$";
	private static final String ID_CARD_VJ="^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$";
	
	
    private static final String PASSID_CARD_NO ="^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
	
	private static final String PASSID_CARD_NO1 ="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
	
	public static boolean isNumber(String value){
		return value.matches(NUMBER);
	}
	/**
	 * 匹配手机号码
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isPhone(String value) {
		
		return value.matches(PHONE);
	}
	/**
	 * 匹配金额
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isAmount(String value) {
		
		return value.matches(AMOUNT);
	}
	/**
	 * 匹配身份证
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isIdCardNo(String value) {
		if(value.matches(ID_CARD_NO15)){
			return true;
		}
		if(value.matches(ID_CARD_NO18)){
			return true;
		}
		return false;
	}
	public static boolean isPASSIdCardNo(String value) {
		return value.matches(PASSID_CARD_NO);
	}
	public static boolean isPASSIdCardNo1(String value) {
		return value.matches(PASSID_CARD_NO1);
	}
	
	/**
	 * 
	  * isBankCard 匹配卡号
	  *
	  * @Title: isBankCard
	
	  * @Description: TODO
	
	  * @param @param value
	  * @param @return    设定文件
	
	  * @return boolean    返回类型
	
	  * @throws
	 */
	public static boolean isBankCardVj(String value){
		return value.matches(BANK_CARD_VJ);
	}
	/**
	 * 
	  * vj 匹配身份证号
	  *
	  * @Title: isBankCard
	
	  * @Description: TODO
	
	  * @param @param value
	  * @param @return    设定文件
	
	  * @return boolean    返回类型
	
	  * @throws
	 */
	public static boolean isIdCardVj(String value){
		return value.matches(ID_CARD_VJ);
	}
	/**
	 * 匹配密码是否合法(密码长度4-10字母和数字)
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isPassWord(String value) {
		return value.matches(PASSWORD);
	}

	/**
	 * 匹配帐号是否合法(字母开头，允许4-10字节，允许字母数字下划线)
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isLoginName(String value) {
		return value.matches(LOGINNAME);
	}

	/**
	 * 匹配中国邮政编码
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isZipCode(String value) {
		return value.matches(ZIP_CODE);
	}

	/**
	 * 匹配国内电话号码
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isTel(String value) {
		if (value.trim().equals("")) {
			return true;
		}
		return value.matches(TEL);
	}

	/**
	 * 检测非法字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isChars(String str) {
		Pattern p = Pattern.compile(CHARS);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 检测Email
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		return Pattern.matches(EMAIL, email);
	}

	public static boolean isUrl(String url) {
		return Pattern.matches(URL, url);
	}

	public static String encode(String regex) {
		if (regex.indexOf("[") != -1)
			regex = regex.replace("[", "\\[");
		if (regex.indexOf("]") != -1)
			regex = regex.replace("]", "\\]");
		if (regex.indexOf("(") != -1)
			regex = regex.replace("(", "\\(");
		if (regex.indexOf(")") != -1)
			regex = regex.replace(")", "\\)");
		return regex;
	}
	/**
	 * 手机号码后 补齐 空格
	 * @param mAddCardNumEdt
	 */
		public  static  void  fillTelSpeace(final EditText mAddCardNumEdt){
	  	  mAddCardNumEdt.addTextChangedListener(new TextWatcher() {
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						if (count == 1) {
							if (s.length() == 3) {
								mAddCardNumEdt.setText(s + "-");
								mAddCardNumEdt.setSelection(4);
							}
							if (s.length() == 8) {
								mAddCardNumEdt.setText(s + "-");
								mAddCardNumEdt.setSelection(9);
							}
//							if (s.length() == 13) {
//								mAddCardNumEdt.setText(s + "-");
//								mAddCardNumEdt.setSelection(14);
//							}
						} else if (count == 0) {
							if (s.length() == 3) {
								mAddCardNumEdt.setText(s.subSequence(0,s.length() - 1));
								mAddCardNumEdt.setSelection(2);
							}
							if (s.length() == 8) {
								mAddCardNumEdt.setText(s.subSequence(0,s.length() - 1));
								mAddCardNumEdt.setSelection(7);
							}
//							if (s.length() == 11) {
//								mAddCardNumEdt.setText(s.subSequence(0,s.length() - 1));
//								mAddCardNumEdt.setSelection(10);
//							}
						}
					}
					public void beforeTextChanged(CharSequence s, int start, int count,
												  int after) {
					}
					public void afterTextChanged(Editable s) {
					}
				});
		}
		/**
		 * 银行卡补齐 空格
		 * 
		 */
		public  static  void  fillBankNumSpeace(final EditText mAddCardNumEdt){
		  	  mAddCardNumEdt.addTextChangedListener(new TextWatcher() {
						public void onTextChanged(CharSequence s, int start, int before,
												  int count) {
							if (count == 1) {
								if (s.length() == 4) {
									mAddCardNumEdt.setText(s + "-");
									mAddCardNumEdt.setSelection(5);
								}
								if (s.length() == 9) {
									mAddCardNumEdt.setText(s + "-");
									mAddCardNumEdt.setSelection(10);
								}
								if (s.length() == 14) {
									mAddCardNumEdt.setText(s + "-");
									mAddCardNumEdt.setSelection(15);
								}
								if (s.length() == 19) {
									mAddCardNumEdt.setText(s + "-");
									mAddCardNumEdt.setSelection(20);
								}
							} else if (count == 0) {
								if (s.length() == 4) {
									mAddCardNumEdt.setText(s.subSequence(0,s.length() - 1));
									mAddCardNumEdt.setSelection(3);
								}
								if (s.length() == 9) {
									mAddCardNumEdt.setText(s.subSequence(0,s.length() - 1));
									mAddCardNumEdt.setSelection(8);
								}
								if (s.length() == 14) {
									mAddCardNumEdt.setText(s.subSequence(0,s.length() - 1));
									mAddCardNumEdt.setSelection(13);
								}
								if (s.length() == 19) {
									mAddCardNumEdt.setText(s.subSequence(0,s.length() - 1));
									mAddCardNumEdt.setSelection(18);
								}
							}
						}
						public void beforeTextChanged(CharSequence s, int start, int count,
													  int after) {
						}
						public void afterTextChanged(Editable s) {
						}
					});
		    }
}

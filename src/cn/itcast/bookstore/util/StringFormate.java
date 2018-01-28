package cn.itcast.bookstore.util;

import java.text.MessageFormat;

import org.junit.Test;

/**
 * 
 * @see        [相关类/方法] 
 * @since      [产品/模块版本]
 */
public class StringFormate {
	@Test
	public void fun1(){
		String s = MessageFormat.format("{0}或{1}错误", "用户名","密码");
		System.out.println(s);
	}
}

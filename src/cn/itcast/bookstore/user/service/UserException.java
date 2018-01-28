package cn.itcast.bookstore.user.service;
/**
 * 自定义异常
 * 重写父类构造方法
 */
public class UserException  extends Exception{

	public UserException() {
		super();
	}

	public UserException(String message) {
		super(message);
	}

}

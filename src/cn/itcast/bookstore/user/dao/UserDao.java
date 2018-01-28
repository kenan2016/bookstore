package cn.itcast.bookstore.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.bookstore.user.domain.User;
import cn.itcast.jdbc.TxQueryRunner;

/**
 * user持久层
 * @author kenan 
 */
public class UserDao {
	private QueryRunner qr  = new TxQueryRunner(); 
	/**
	 * 校验用户名是否被注册
	 */
	public User findByUsername(String username){
		String sql = "select * from tb_user where username = ?";
		try {
			User user = (User)qr.query(sql, new BeanHandler<User>(User.class), username);
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 安邮箱查询
	 */
	public User findByEmail(String email){
		String sql = "select * from tb_user where email = ?";
		try {
			User user = (User)qr.query(sql, new BeanHandler<User>(User.class), email);
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	/**
	 * 添加用户
	 */
	public void addUser(User user){
		String sql = "insert into tb_user values(?,?,?,?,?,?)";
		Object[] params = {user.getUid(),user.getUsername(),user.getPassword(),user.getEmail(),user.getCode(),user.isState()};
		try {
			int res = qr.update(sql, params);
			return ;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 根据激活码查询用户
	 */
	public User findByCode(String code){
		String sql = "select * from tb_user where code = ?";
		User user = null;
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class), code);
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 更新用户状态
	 */
	public int updateState(boolean state, String uid ){
		Object[] params = { state , uid };
		//数据库中Boolean 的 true 类型会被 转换成 tiny类型的 1
		String sql = "update tb_user set state = ? where uid = ?";
		try {
			int res = qr.update(sql, params);
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}

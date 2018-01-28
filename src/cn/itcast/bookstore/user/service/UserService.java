package cn.itcast.bookstore.user.service;

import cn.itcast.bookstore.user.dao.UserDao;
import cn.itcast.bookstore.user.domain.User;
/**user业务层
 * @author kenan 
 * @see        [相关类/方法] 
 * @since      [产品/模块版本]
 */
public class UserService {
	private UserDao userDao = new UserDao();
	public void regist(User form ) throws UserException{
		//校验用户名是否被注册
		User user = userDao.findByUsername(form.getUsername());
		if (user != null) throw new UserException("该用户名已被注册！");

		//校验邮箱是否被注册
		user = userDao.findByEmail(form.getEmail());
		if (user != null) throw new UserException("该邮箱已被注册！");

		//添加用户
		userDao.addUser(form);
	}
}

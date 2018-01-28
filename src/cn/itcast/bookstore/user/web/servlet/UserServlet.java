package cn.itcast.bookstore.user.web.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.user.domain.User;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.bookstore.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;

/**
 * User控制器层
 */

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	public String regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		// 补全form对象
		form.setUid(CommonUtils.uuid());
		form.setCode(CommonUtils.uuid() + CommonUtils.uuid());

		/*
		 * 输入校验
		 */
		Map<String, String> errors = new HashMap<String, String>();
		/**
		 * 获取form信息并校验
		 */

		String username = form.getUsername();
		if (username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空！");
		} else if (username.length() < 3 || username.length() > 10) {
			errors.put("username", "用户名长度必须在 3到10位之间");
		}

		String password = form.getPassword();
		if (password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空！");
		} else if (password.length() < 3 || password.length() > 10) {
			errors.put("password", "密碼长度必须在 3到10位之间");
		}

		String email = form.getEmail();
		if (email == null || email.trim().isEmpty()) {
			errors.put("email", "邮箱能为空！");
		} else if (email.matches("\\w+@\\w+\\.w+")) {// djoj@sjdsj.com
			errors.put("email", "email格式错误");
		}
		/**
		 * 判断是否存在错误信息
		 */

		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}

		/**
		 * 调用regist
		 */
		try {
			userService.regist(form);

		} catch (Exception e) {
			/**
			 * 保存异常信息 保存form 转发到regist.jsp
			 */
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		/**
		 * 发邮件 准备配置文件 获取配置文件内容
		 */
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		String host = props.getProperty("host");// 获取邮箱服务器主机
		String uname = props.getProperty("uname");// 获取用户名
		String pwd = props.getProperty("pwd");// 获取密码
		String from = props.getProperty("from");// 获取发件人
		String to = form.getEmail();// 获取收件人
		String subject = props.getProperty("subject");
		String content = props.getProperty("content");// 获取邮件发送内容模板
		// 占位符替换 替换code
		content = MessageFormat.format(content, form.getCode());// 替换{0}
		// 获取 session
		Session session = MailUtils.createSession(host, uname, pwd);
		// 创建Mail 对象
		Mail mail = new Mail(from, to, subject, content);
		// 发送邮件
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		/**
		 * 执行到这里说明userService执行成功没有有出错 保存成功信息
		 */
		request.setAttribute("msg", "恭喜注册成功，请马上到邮箱激活");
		return "f:/jsps/msg.jsp";
	}

	/**
	 * 激活
	 * 
	 * @author kenan 2018年1月28日 下午5:33:11
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @since [产品/模块版本]
	 */
	public String active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		UserService userService = new UserService();
		// 激活
		try {
			userService.active(code);
			// 转发成功信息到msg页面
			request.setAttribute("msg", "恭喜您已激活！,请登录!");
		} catch (UserException e) {
			// 转发错误信息到列表页
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/jsps/msg.jsp";

	}

	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		try {
			User user = userService.login(form);
			request.setAttribute("msg", "登陸成功!");
			//用户保存到user
			request.getSession().setAttribute("session_user", user);
			//一般登录成功后重定向到主页
			return "r:/index.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			//回显
			request.setAttribute("form", form);
			//转发到登录页
			return "f:/jsps/user/login.jsp";
		}
	}
	
	/**
	 * 用户退出
	 * 一般登录的和退出都是使用重定向
	 */
	public String  quit(HttpServletRequest request, HttpServletResponse response){
		try{
			//销毁session
			request.getSession().invalidate();
		} catch(Exception e){
			
		}
		return "r:/index.jsp";
	}
	

}

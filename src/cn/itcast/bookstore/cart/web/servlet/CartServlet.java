package cn.itcast.bookstore.cart.web.servlet;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.cart.domain.CartItem;
import cn.itcast.servlet.BaseServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet(value= "/CartServlet")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 添加购物条目
	 * @author kenan 2018年2月1日 下午9:59:52
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException 
	 * @since [产品/模块版本] 
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到车:只要用户登录成功就可以获取一辆购物车
		//得到车
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		
		//得到条目、
		/***
		 * 得到图书和数量
		 * 先得到图示的Bid，然后我妈们需要通过Bid查询数据库得到Book
		 * 数量表单中有
		 * 结论：最终我们传递的只有Bid 和数量
		 */
		String bid = request.getParameter("bid");
		Book book =	new BookService().load(bid);
		int count = Integer.parseInt(request.getParameter("count"));
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		cart.add(cartItem);
		return "f:/jsps/cart/list.jsp";
	}
	/**
	 * 清空购物条目
	 * @author kenan 2018年2月1日 下午9:59:52
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException 
	 * @since [产品/模块版本] 
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到车
		//调用车的clear
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		cart.clear();
		return "f:/jsps/cart/list.jsp";
	}
	/**
	 * 删除购物条目
	 * @author kenan 2018年2月1日 下午9:59:52
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException 
	 * @since [产品/模块版本] 
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到车，得到要删除的bid 最后删除该条目
		String bid = request.getParameter("bid");
		Cart cart= (Cart)request.getSession().getAttribute("cart");
		cart.delete(bid);
		return "f:/jsps/cart/list.jsp";
	}
}

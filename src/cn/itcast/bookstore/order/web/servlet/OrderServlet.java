package cn.itcast.bookstore.order.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.cart.domain.CartItem;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.domain.OrderItem;
import cn.itcast.bookstore.order.service.OrderService;
import cn.itcast.bookstore.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new  OrderService();
	
	/**
	 * 从session中获取Cart
	 * 使用Cart生成Order对象
	 * 调用service方法完成添加订单
	 * 保存order到request域中，转发到/jsps/order/desc.jsp
	 * @author kenan 2018年2月4日 下午6:34:01
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException 
	 * @since [产品/模块版本]
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{	/**
		 * Cart 到 Order
	 	*/
	
		//把cart转换成Order 对象
		Order order = new Order();
		//设置订单编号： 订单主键
		order.setOid(CommonUtils.uuid());
		//设置订单生成时间
		order.setOrdertime(new Date());
		//设置订单状态表示未付款
		order.setState(1);
		User user = (User)request.getSession().getAttribute("session_user");
		//设置订单所有者
		order.setOwner(user);
		//获取Cart对象
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		//设置订单的合计  订单合计从cart中获取
		order.setTotal(cart.getTotal());
		//设置订单条目 （其实车里条目就是订单的条目）
		
		//创建订单条目集合
		/**
		 * cartItemList 到orderItemList
		 */
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		//遍历Cart中的所有CartItem,使用cartitem创建OrderItem 对象，并添加到集合中去
		for(CartItem cartItem : cart.getCartItems() ){
			OrderItem oi = new OrderItem();//创建订单条目
			oi.setIid(CommonUtils.uuid());//设置条目的Id
			oi.setCount(cartItem.getCount());//设置条目的数量
			//设置条目的图书
			oi.setBook(cartItem.getBook());
			//设置订单小计
			oi.setSubtotal(cart.getTotal());
			//设置所属订单
			oi.setOrder(order);
			//把条目添加到订单集合中去
			orderItemList.add(oi);
		}
		//把所有的订单条目添加到订单中
		order.setOrderItemList(orderItemList);
		//清空购物车
		cart.clear();
		/**
		 * 3 调用orderService添加订单
		 */
		orderService.add(order);
		/**
		 * 4 保存order 到request域中，转发到/jsp/order/desc.jsp
		 */
		request.setAttribute("order", order);
		return "f:/jsp/order/desc.jsp";
		
	}

}

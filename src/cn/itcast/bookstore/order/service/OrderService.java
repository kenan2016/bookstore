package cn.itcast.bookstore.order.service;

import java.sql.SQLException;

import cn.itcast.bookstore.order.dao.OrderDao;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.jdbc.JdbcUtils;

public class OrderService {
	private OrderDao orderDao = new  OrderDao();
	/**
	 * 添加订单
	 * 这里需要处理事务
	 * 把session中的车用来生成Order对象 
	 * */
	public void add(Order order){
		try {
			//开启 事务
			JdbcUtils.beginTransaction();
			orderDao.addOrder(order);//插入订单
			orderDao.addOrderItemList(order.getOrderItemList());//插入订单中的 条目
			//上边的两步被绑定成一个事务
			//提交事务
			JdbcUtils.commitTransaction();
		} catch (Exception e) {
			//回滚事务
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		}
	}
}

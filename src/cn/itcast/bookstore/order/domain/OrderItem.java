package cn.itcast.bookstore.order.domain;

import cn.itcast.bookstore.book.domain.Book;

/**
 * 订单和订单下的订单条目存在双向关联关系
 * 订单 条目类
 * @author kenan 
 * @see        [相关类/方法] 
 * @since      [产品/模块版本]
 */
public class OrderItem {
	private String iid;//条目主键
	private int count;//数量
	private double subtotal;//小计
	private  Order order;//所属订单
	private Book book;//所需要购买的图书
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
}

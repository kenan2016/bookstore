package cn.itcast.bookstore.order.domain;

import java.util.Date;
import java.util.List;

import cn.itcast.bookstore.user.domain.User;
/**
 * 订单类
 * @author kenan 
 * @see        [相关类/方法] 
 * @since      [产品/模块版本]
 */
public class Order {
	private String oid;
	private Date ordertime;//下單時間
	private double total;//合計
	private int state;//订单状态 1：未付款  2：已付款但未发货  3：已发货但是未确认收货 4：已确认交易成功
	private User owner;//订单所有者（当数据库中关联字段为另外一张的主键时建议申明这张表的对象作为类成员）如uid那么 我们就将user作为类成员
	private String address; //收货地址
	
	//补充：  当前订单下所有条目
	private List<OrderItem> orderItemList;
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", total=" + total + ", state=" + state + ", owner="
				+ owner + ", address=" + address + "]";
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
}

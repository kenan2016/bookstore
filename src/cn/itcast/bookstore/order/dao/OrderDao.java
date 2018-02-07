package cn.itcast.bookstore.order.dao;

import java.sql.Timestamp;
import java.util.List;

import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.domain.OrderItem;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {
	private TxQueryRunner qr = new TxQueryRunner();
	/**
	 * 添加订单
	 */
	public void addOrder(Order order){
		try {
			String sql = "insert into orders values(?,?,?,?,?,?)";
			Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
			Object[] params = {order.getOid(), timestamp, order.getTotal(),
					order.getState(), order.getOwner().getUid(),
					order.getAddress()};
			qr.update(sql, params);
		} catch(Exception e){
			throw new RuntimeException();
		}
	}
	
	/**
	 * 插入订单条目
	 * 批处理
	 * @author kenan 2018年2月4日 下午5:13:47
	 * @param orderItemList 
	 * @since [产品/模块版本]
	 */
	public void  addOrderItemList(List<OrderItem> orderItemList){
		try {//是params一个二维数组object[][]
			//是多个一位数组
			//每个一维数组都与sql在一起执行一次，多个一维数组就执行多次
			String sql = "insert into orderitem values(?,?,?,?,?)";
			//我们需要把orderItemList 转换成二维数组
			/** *
			 * 转换规则 把一个orderItem 转换成一个一维数组
			 * list转换成多个一维数组
			 *  Object[i][j] 其中 i 代表二维数组中一维数组的个数  j表示一位数组中能装几个 元素 j 可以不指定
			 */
			Object[][] params = new Object[orderItemList.size()][];
			//循环遍历orderItemList,使用每个orderItem 为一维数组赋值
			for(int i = 0;i< orderItemList.size(); i++){
				
				OrderItem item = orderItemList.get(i);
				//使用item给 一位数组赋值
				params[i] =  new Object[]{item.getIid(), item.getCount(), item.getSubtotal(), item.getOrder().getOid(), item.getBook().getBid()};
			}
			//执行批处理
			qr.batch(sql, params);
		} catch (Exception e ){
			throw new RuntimeException();
			
		}
		
	}
}

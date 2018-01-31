package cn.itcast.bookstore.cart.domain;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 购物车实体类
 * @author kenan 
 * @see        [相关类/方法] 
 * @since      [产品/模块版本]
 */
public class Cart {
	//创建购物车里的条目
	//注意购物车里 的数据显示时是有顺序的，因此 需要用 LinkedHashMap
	private Map<String,Cartitem> map =  new LinkedHashMap<String, Cartitem>();
	
	//合计
	//合计等于所有条目的小计之和
	//使用bigDecimal 处二进制运算出错的问题
	public double getTotal(){
		//防止浮点数计算出错
		BigDecimal total  = new BigDecimal(0+"");
		
		for(Cartitem cartItem : map.values()){
			BigDecimal subtotal  = new BigDecimal( cartItem.getSubtatal() + "");
			total = total.add(subtotal);
		}
		return total.doubleValue();
	}
	
	//购物车对其条目的操作功能
	
	//添加条目到购物车
	public void add(Cartitem cartItem){
		//添加条目:当某个条目存在时只要修改老条目的的 数量（根据书的Id去标识某个条目）
		if(map.containsKey(cartItem.getBook().getBid())){//已存在 
			Cartitem _cartItem = map.get(cartItem.getBook().getBid());//获取原条目
			//修改老条目数量
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
			map.put(_cartItem.getBook().getBid(), _cartItem);
		} else {//不存在则新增
			map.put(cartItem.getBook().getBid(),cartItem);
		}
	}
	
	//清空购物车（删除所有条目）
	public void clear(){
		map.clear();
	}
	
	//删除条目（根据Id删除条目）  
	//注意本项目 里的购物车的条目其实就是 book的数量和单价信息的组合
	//因此可以知道图书的bid 可以用来做商品条目的Id
	public void delete(String bid){
		map.remove(bid);
	}
	
	//获取所有条目
	public Collection<Cartitem> getCartItems(){
		//我们其实就是要 获取所有的值 
		return map.values();
	}
	
}

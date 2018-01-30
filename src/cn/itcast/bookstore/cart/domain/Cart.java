package cn.itcast.bookstore.cart.domain;
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
	
	//购物车对其条目的操作功能
	
	//添加条目到购物车
	public void add(Cartitem cartItem){
		
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

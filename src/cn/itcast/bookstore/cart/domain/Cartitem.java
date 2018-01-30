package cn.itcast.bookstore.cart.domain;

import cn.itcast.bookstore.book.domain.Book;

/**
 * 条目实体类
 * @author kenan 
 * @see        [相关类/方法] 
 * @since      [产品/模块版本]
 */
public class Cartitem {
	private Book book;//商品
	private int count;//商品数量
	
	//通常每一个条目都还会有一个小计（是根据数量和商品价格计算出来的）
	public double getSubtatal(){
		return book.getPrice() * count;
	}
	

	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}

package cn.itcast.bookstore.book.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.jdbc.TxQueryRunner;

public class BookDao {
	private TxQueryRunner qr = new TxQueryRunner();
	
	/**
	 * 查询所有图书
	 */
	public List<Book> findAll(){
		String sql = "select * from book";
		List<Book> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (Exception e) {
			throw  new RuntimeException("查询所有图书出错！");
		}
		return list;
	}

	/**
	 * 按分类查询图书
	 */
	public List<Book> findByCategory(String cid){
		String sql = "select * from book where cid = ?";
		
		List<Book> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<Book>(Book.class),cid);
		} catch (Exception e) {
			throw  new RuntimeException("按分类查询图书出错！");
		}
		return list;
	}

	/**
	 * 根据案件bid 加载图示件详情
	 * @author kenan 2018年1月30日 上午12:15:07
	 * @param bid
	 * @return  
	 * @since [产品/模块版本]
	 */
	public Book findByBid(String bid) {
		Book book =  null;
		String sql = "select * from book where bid = ?";
		try {
			book = qr.query(sql, new BeanHandler<Book>(Book.class),bid);
		} catch (Exception e) {
			throw  new RuntimeException("按分类查询图书出错！");
		}
		return book;
	}
	
}

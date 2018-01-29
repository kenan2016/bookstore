package cn.itcast.bookstore.book.service;

import java.util.List;

import cn.itcast.bookstore.book.dao.BookDao;
import cn.itcast.bookstore.book.domain.Book;

public class BookService {
	BookDao bookDao = new BookDao();
	/**
	 * 查询所有图书
	 * @author kenan 2018年1月29日 下午11:51:19
	 * @return 
	 * @since [产品/模块版本]
	 */
	public List<Book> findAll(){
		List<Book> list = bookDao.findAll();
		return list;
	}

	public List<Book> findByCategory(String cid){
		List<Book> list = bookDao.findByCategory(cid);
		return list;
	}

	/**
	 * 根據bid 加载book详情
	 * @author kenan 2018年1月30日 上午12:12:42
	 * @param bid
	 * @return 
	 * @since [产品/模块版本]
	 */
	public Book load(String bid) {
		return bookDao.findByBid(bid);
	}
}

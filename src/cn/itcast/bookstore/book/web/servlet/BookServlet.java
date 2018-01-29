package cn.itcast.bookstore.book.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.servlet.BaseServlet;

@WebServlet(value="/BookServlet")
public class BookServlet  extends BaseServlet{
	private BookService bookService = new BookService();
	private static final long serialVersionUID = 1L;
	/**
	 * 查詢所有圖書
	 * @author kenan 2018年1月29日 下午11:52:39
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException 
	 * @since [产品/模块版本]
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		List<Book> list = bookService.findAll();
		//转发
		request.setAttribute("list", list);
		return "f:jsps/book/list.jsp";
	}

	/**
	 * 按分类查询所有图书
	 * @author kenan 2018年1月29日 下午11:53:15
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException 
	 * @since [产品/模块版本]
	 */
	public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String cid = request.getParameter("cid");
		List<Book> list = bookService.findByCategory(cid);
		//转发
		request.setAttribute("list", list);
		return "f:jsps/book/list.jsp";
	} 
	
	/**
	 * 根据id查看图书 详情
	 * */
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String bid = request.getParameter("bid");
		Book book  = bookService.load(bid);
		//轉發
		request.setAttribute("book", book);
		return "jsps/book/desc.jsp";
	}
	

}

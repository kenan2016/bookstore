package cn.itcast.bookstore.category.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.category.domian.Category;
import cn.itcast.bookstore.category.service.CategoryService;
import cn.itcast.servlet.BaseServlet;

@WebServlet(value="/CategoryServlet")
public class CategoryServlet  extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private CategoryService categoryService  = new CategoryService();
	public String  findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			List<Category> list =  categoryService.findAll();
	 		request.setAttribute("list",list);
		} catch (Exception e) {
			request.setAttribute("msg", "查询分类出错！");
		}
		return "f:/jsps/left.jsp";
	}
}

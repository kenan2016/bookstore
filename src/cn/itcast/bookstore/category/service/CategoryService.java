package cn.itcast.bookstore.category.service;

import java.util.List;

import cn.itcast.bookstore.category.dao.CategoryDao;
import cn.itcast.bookstore.category.domian.Category;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	public List<Category> findAll(){
		List<Category> list = categoryDao.findAll();
		return list;
	}
} 

package cn.itcast.bookstore.category.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.bookstore.category.domian.Category;
import cn.itcast.jdbc.TxQueryRunner;

public class CategoryDao {
	private TxQueryRunner qr = new TxQueryRunner();
	
	/**查询所有分类
	 * @author kenan 2018年1月28日 下午8:56:12 
	 * @since [产品/模块版本]
	 */
	public List<Category> findAll(){
		String sql = "select * from category";
		List<Category> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<>(Category.class));
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return list;
	}
}

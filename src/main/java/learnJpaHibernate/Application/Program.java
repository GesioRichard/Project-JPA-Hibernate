package learnJpaHibernate.Application;

import java.util.List;

import learnJpaHibernate.model.dao.DepartmentDao;
import learnJpaHibernate.model.entities.Department;

public class Program {

	public static void main(String[] args) {
		
		DepartmentDao dao = new DepartmentDao();
		
		//dao.update(null);
		
		List<Department> dps = dao.findAll();
		
		Department d = dao.findById(8);
		//dao.delete(8);
		
		dps.forEach(System.out::println);
		System.out.println(d);

	}

}

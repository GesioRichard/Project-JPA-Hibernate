package learnJpaHibernate.model.dao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import learnJpaHibernate.model.entities.Department;
import learnJpaHibernate.model.exceptions.DataException;

public class DepartmentDao {

	private EntityManagerFactory emf;
	private EntityManager em;

	public DepartmentDao() {
		emf = Persistence.createEntityManagerFactory("dbConfig");
		em = emf.createEntityManager();
	}

	public void insert(Department d) {

		try {
			em.getTransaction().begin();
			
			if (d == null) {
				throw new DataException("Not permited an null department");
			}
			
			em.persist(d);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			em.getTransaction().rollback();
		}
	}

	public void update(Department d) {

		try {
			em.getTransaction().begin();

			if (d == null) {
				throw new DataException("Not permited an null department");
			}
			else if (d.getId() == null) {
				throw new DataException("Not permited null id");
			}
			em.merge(d);
			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			em.getTransaction().rollback();
		}
	}

	public void delete(Integer id) {
		try {
			
			em.getTransaction().begin();
			Department d = em.find(Department.class, id);
			
			if(d == null) {
				throw new DataException("This id doesnt exist in database");
			}
			
			em.remove(d);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			em.getTransaction().rollback();
		}
	}
	
	public Department findById(Integer id) {
		
		try {
			em.getTransaction().begin();
			Department d = em.find(Department.class, id);
			
			if(d == null) {
				throw new DataException("This id doesnt exist in database");
			}
			
			em.getTransaction().commit();
			
			return d;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			em.getTransaction().rollback();
		}
		
		return null;
	}
	
	public List<Department> findAll(){
		
		
		try {
			em.getTransaction().begin();
			List<Department> departments = em.createQuery("from Department d", Department.class).getResultList();
			em.getTransaction().commit();
			return departments;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			em.getTransaction().rollback();
		}
		
		return null;
	}
}

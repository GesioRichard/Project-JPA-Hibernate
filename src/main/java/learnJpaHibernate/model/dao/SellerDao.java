package learnJpaHibernate.model.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import learnJpaHibernate.model.entities.Department;
import learnJpaHibernate.model.entities.Seller;
import learnJpaHibernate.model.exceptions.DataException;

public class SellerDao {

	private EntityManagerFactory emf;
	private EntityManager em;

	public SellerDao() {
		emf = Persistence.createEntityManagerFactory("dbConfig");
		em = emf.createEntityManager();
	}

	public void insert(Seller s) {

		try {
			em.getTransaction().begin();

			if (s == null) {
				throw new DataException("Not permited an null seller");
			}

			em.persist(s);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			em.getTransaction().rollback();
		}
	}

	public void update(Seller s) {

		try {
			em.getTransaction().begin();

			if (s == null) {
				throw new DataException("Not permited an null seller");
			}

			em.merge(s);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			em.getTransaction().rollback();
		}
	}

	public void delete(Integer id) {
		try {

			em.getTransaction().begin();
			Seller s = em.find(Seller.class, id);

			if (s == null) {
				throw new DataException("This id doesnt exist in database");
			}

			em.remove(s);
			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			em.getTransaction().rollback();
		}
	}

	public Seller findById(Integer id) {

		try {
			Seller seller = em.find(Seller.class, id);
			
			if(seller == null) {
				throw new DataException("This id doesnt exist in database");
			}
			
			return seller;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

		return null;
	}
	
	public List<Seller> findByDepartment(Department d){
		
		try {
			
			if(d == null) {
				throw new DataException("Not permited a null department");
			}else if(d.getId() == null) {
				throw new DataException("Not permited a null department id");
			}
			
			@SuppressWarnings("unchecked")
			List<Seller> sellers = em.createNativeQuery("SELECT s.*, d.Name AS DepartmentName "
					+ "FROM seller s "
					+ "INNER JOIN department d "
					+ "ON s.DepartmentId = d.Id "
					+ "WHERE d.Id = :DepartmentID", Seller.class).setParameter("DepartmentID", d.getId()).getResultList();

			return sellers;
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return null;
	}
	
	public List<Seller> findAll(){
		
		try {
			List<Seller> sellers = em.createQuery("from Seller s", Seller.class).getResultList();
			
			return sellers;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return null;
	}
}

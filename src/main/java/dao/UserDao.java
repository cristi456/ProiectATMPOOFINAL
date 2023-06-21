package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import model.Account;

public class UserDao extends GenericDao<Account> {

	private EntityManagerFactory factory;

	public UserDao(EntityManagerFactory factory) {
		super(Account.class);
		this.factory = factory;
	}

	@Override
	public EntityManager getEntityManager() {
		try {
			return factory.createEntityManager();
		} catch (Exception ex) {
			System.out.println("The entity manager cannot be created!");
			return null;
		}
	}

	// for login
	public List<Account> find(String name) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Account> q = cb.createQuery(Account.class);

		Root<Account> c = q.from(Account.class);
		ParameterExpression<String> paramName = cb.parameter(String.class);
		q.select(c).where(cb.equal(c.get("accountNumber"), paramName));
		TypedQuery<Account> query = em.createQuery(q);
		query.setParameter(paramName, name);

		List<Account> results = query.getResultList();
		return results;
	}
	
	public void saveAccount(Account account) {
	    EntityManager em = getEntityManager();
	    try {
	        em.getTransaction().begin();
	        if (account.getIduser() == 0) {
	            em.persist(account); // save
	        } else {
	            em.merge(account); // update
	        }
	        em.getTransaction().commit();
	    } catch (Exception ex) {
	        em.getTransaction().rollback();
	        System.out.println("An error occurred while saving the account: " + ex.getMessage());
	    } finally {
	        em.close();
	    }
	}
	
	@Override
    public void remove(Account account, int entityId) {
        super.remove(account, entityId);
    }

	
}

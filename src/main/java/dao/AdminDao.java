package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Admin;

public class AdminDao 
{
	public EntityManager getEm()
	{
		return Persistence.createEntityManagerFactory("arun").createEntityManager();
	}
	
	public Admin saveData(Admin admin)
	{
		EntityManager em = getEm();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(admin);
		et.commit();
		
		return admin;
	}
	
	public Admin findId(int id)
	{
		EntityManager em = getEm();
		//2EntityTransaction et = em.getTransaction();
		
		Admin admin = em.find(Admin.class, id);
		
		if(admin!=null)
		{
			return admin;
		}
		else
		{
			return null;
		}
	}
	
	public Admin deletData(int id)
	{
		EntityManager em = getEm();
		EntityTransaction et = em.getTransaction();
		
		Admin admin = em.find(Admin.class, id);
		
		if(admin!=null)
		{
			et.begin();
			em.remove(admin);
			et.commit();
			return admin;
		}
		
		return null;
	}
	
	public Admin updateData(Admin admin)
	{
		EntityManager em = getEm();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.merge(admin);
		et.commit();
		return admin;
		
		/*Admin admin1 = findId(admin.getAdminId());
		
		if(admin1!=null)
		{
			et.begin();
			em.merge(admin1);
			et.commit();
			return admin1;
		}
		else
		{
			return null;
		}*/
		
	}

}

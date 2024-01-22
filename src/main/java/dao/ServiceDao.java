package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Service;

public class ServiceDao 
{
	public EntityManager getEm()
	{
		return Persistence.createEntityManagerFactory("arun").createEntityManager();
	}
	
	public Service saveData(Service service)
	{
		EntityManager em = getEm();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(service);
		et.commit();
		
		return service;
	}
	
	public Service findId(int id)
	{
		EntityManager em = getEm();
		//EntityTransaction et = em.getTransaction();
		
		Service service = em.find(Service.class, id);
		return service;
		/*if(service!=null)
		{
			return service;
		}
		else
		{
			return null;
		}*/
	}
	
	public Service deletData(int id)
	{
		EntityManager em = getEm();
		EntityTransaction et = em.getTransaction();
		
		Service service = em.find(Service.class, id);
		
		if(service!=null)
		{
			et.begin();
			em.remove(service);
			et.commit();
			return service;
		}
		
		return null;
	}
	
	public Service updateData(Service service)
	{
		EntityManager em = getEm();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.merge(service);
		et.commit();
		return service;
		//Service service1 = findId(service.getServiceId());
		
		/*if(service1!=null)
		{
			et.begin();
			em.merge(service1);
			et.commit();
			return service1;
		}
		else
		{
			return null;
		}*/
		
	}
}

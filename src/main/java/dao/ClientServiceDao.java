package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.ClientService;

public class ClientServiceDao 
{
	public EntityManager getEm()
	{
		return Persistence.createEntityManagerFactory("arun").createEntityManager();
	}
	
	public ClientService saveData(ClientService clientservice)
	{
		EntityManager em = getEm();
		EntityTransaction et = em.getTransaction();
		et.begin();
		//em.merge(clientservice);
		em.persist(clientservice);
		et.commit();
		
		return clientservice;
	}
	
	public ClientService findId(int id)
	{
		EntityManager em = getEm();
		//EntityTransaction et = em.getTransaction();
		
		ClientService clientservice = em.find(ClientService.class, id);
		
		if(clientservice!=null)
		{
			return clientservice;
		}
		else
		{
			return null;
		}
	}
	
	public ClientService deletData(int id)
	{
		EntityManager em = getEm();
		EntityTransaction et = em.getTransaction();
		
		ClientService clientservice = em.find(ClientService.class, id);
		
		if(clientservice!=null)
		{
			et.begin();
			em.remove(clientservice);
			et.commit();
			return clientservice;
		}
		
		return null;
	}
	
	public ClientService updateData(ClientService clientservice)
	{
		EntityManager em = getEm();
		EntityTransaction et = em.getTransaction();
		
		ClientService clientservice1 = findId(clientservice.getClientserviceId());
		
		if(clientservice1!=null)
		{
			et.begin();
			em.merge(clientservice1);
			et.commit();
			return clientservice1;
		}
		else
		{
			return null;
		}
		
	}
}

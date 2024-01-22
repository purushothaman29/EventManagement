package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.ClientEvent;

public class ClientEventDao 
{
	public EntityManager getEm()
	{
		return Persistence.createEntityManagerFactory("arun").createEntityManager();
	}
	
	public ClientEvent saveData(ClientEvent clientEvent)
	{
		EntityManager em = getEm();
		EntityTransaction et = em.getTransaction();
		et.begin();
		//em.merge(clientEvent);
		em.persist(clientEvent);
		et.commit();
		
		return clientEvent;
	}
	
	public ClientEvent findId(int id)
	{
		EntityManager em = getEm();
		//EntityTransaction et = em.getTransaction();
		
		ClientEvent clientEvent = em.find(ClientEvent.class, id);
		
		if(clientEvent!=null)
		{
			return clientEvent;
		}
		else
		{
			return null;
		}
	}
	
	public ClientEvent deletData(int id)
	{
		EntityManager em = getEm();
		EntityTransaction et = em.getTransaction();
		
		ClientEvent clientEvent = em.find(ClientEvent.class, id);
		
		if(clientEvent!=null)
		{
			et.begin();
			em.remove(clientEvent);
			et.commit();
			return clientEvent;
		}
		
		return null;
	}
	
	public ClientEvent updateData(ClientEvent clientEvent)
	{
		EntityManager em = getEm();
		EntityTransaction et = em.getTransaction();
		
		ClientEvent clientEvent1 = findId(clientEvent.getClientEventId());
		
		if(clientEvent1!=null)
		{
			et.begin();
			em.merge(clientEvent1);
			et.commit();
			return clientEvent1;
		}
		else
		{
			return null;
		}
		
	}
}

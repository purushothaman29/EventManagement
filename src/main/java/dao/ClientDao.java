package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Client;

public class ClientDao 
{
	public EntityManager getEm()
	{
		return Persistence.createEntityManagerFactory("arun").createEntityManager();
	}
	
	public Client saveData(Client client)
	{
		EntityManager em = getEm();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(client);
		et.commit();
		
		return client;
	}
	
	public Client findId(int id)
	{
		EntityManager em = getEm();
		//EntityTransaction et = em.getTransaction();
		
		Client client = em.find(Client.class, id);
		
		if(client!=null)
		{
			return client;
		}
		else
		{
			return null;
		}
	}
	
	public Client deletData(int id)
	{
		EntityManager em = getEm();
		EntityTransaction et = em.getTransaction();
		
		Client client = em.find(Client.class, id);
		
		if(client!=null)
		{
			et.begin();
			em.remove(client);
			et.commit();
			return client;
		}
		
		return null;
	}
	
	public Client updateData(Client client)
	{
		EntityManager em = getEm();
		EntityTransaction et = em.getTransaction();
		
		Client client1 = findId(client.getClientId());
		
		if(client1!=null)
		{
			et.begin();
			em.merge(client1);
			et.commit();
			return client1;
		}
		else
		{
			return null;
		}
		
	}
}

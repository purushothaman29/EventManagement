package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import dao.AdminDao;
import dao.ClientDao;
import dao.ClientEventDao;
import dao.ClientServiceDao;
import dao.ServiceDao;
import dto.Admin;
import dto.Client;
import dto.ClientEvent;
import dto.ClientService;
import dto.EventType;
import dto.Service;

public class EventManagement 
{
	AdminDao adao = new AdminDao();
	ClientDao cdao = new ClientDao();
	ClientEventDao cedao = new ClientEventDao();
	ClientServiceDao csdao = new ClientServiceDao();
	ServiceDao sdao = new ServiceDao();
	
	Scanner sc = new Scanner(System.in);
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("arun");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public Admin saveAdmin()
	{
		Admin admin = new Admin();
		
		System.out.println("Enter Admin Name :");
		admin.setAdminName(sc.next());
		System.out.println("Enter Admin Mail :");
		admin.setAdminEmail(sc.next());
		System.out.println("Enter Admin Password :");
		admin.setAdminPassword(sc.next());
		System.out.println("Enter Admin Contact :");
		admin.setAdminContact(sc.nextLong());
		
		return adao.saveData(admin);
	}
	
	public Admin adminLogin()
	{
		System.out.println("Enter the Email : ");
		String mail = sc.next();
		System.out.println("Enter Passord : ");
		String pass = sc.next();
		Query query = em.createQuery("select a from Admin a where a.adminEmail = ?1 and a.adminPassword = ?2");
		query.setParameter(1, mail);
		query.setParameter(2, pass);
		Admin a = (Admin) query.getSingleResult();
		if(a != null)
		{
			if(a.getAdminEmail().equals(mail) && a.getAdminPassword().equals(pass))
			{
				return a;
			}
			return null;
		}
		return null;
	}
	
	public Service saveService()
	{
		Service service = new Service();
		System.out.println("Enter the ServiceName :");
		service.setServiceName(sc.next());
		System.out.println("Enter the CostPerDay :");
		service.setServiceCostPerDay(sc.nextDouble());
		System.out.println("Enter the CostPerPerson : ");
		service.setServiceCostPerPerson(sc.nextDouble());
		
		if(adminLogin()!=null)
		{
			List<Service> ser = getAllServices();
			ser.add(sdao.saveData(service));
			Admin admin = adao.findId(1);
			admin.setService(ser);
			adao.updateData(admin);
			return service;
		}
		return null;
	}
	
	public List<Service> getAllServices()
	{		
		Query query = em.createQuery("select s from Service s");
		List<Service> service = query.getResultList();
		return service;
	}
	
	public String updateService()
	{
		List<Service> service = getAllServices();
		for(Service s : service)
		{
			System.out.println("Service id"+"Service Name"+"Cost Per Person"+"Cost Per Day");
			System.out.println(s.getServiceId()+"   "+s.getServiceName()+"   "+s.getServiceCostPerDay()+"   "+s.getServiceCostPerPerson());
		}
		System.out.println("Enter the id to update : ");
		int id = sc.nextInt();
		Service ser = em.find(Service.class, id);
		System.out.println("Update cost per day");
		double cpd = sc.nextDouble();
		ser.setServiceCostPerDay(cpd);
		System.out.println("Update cost per person");
		double cpp = sc.nextDouble();
		ser.setServiceCostPerPerson(cpp);
		Service updated = sdao.updateData(ser);
		if(updated!=null)
		{
			return "Updated Successfully";
		}
		return null;

	}
	
	public Service deleteService()
	{
		System.out.println("Enter the Admin Cerdentials :");
		Admin admin = adminLogin();
		if(admin!=null)
		{
			List<Service> services = getAllServices();
			for(Service s : services)
			{
				System.out.println("Service id"+"Service Name"+"Cost Per Person"+"Cost Per Day");
				System.out.println(s.getServiceId()+"   "+s.getServiceName()+"   "+s.getServiceCostPerDay()+"   "+s.getServiceCostPerPerson());
			}
			System.out.println("Enter the id to be Deleted: ");
			int id = sc.nextInt();
			Service delete = em.find(Service.class, id);
			sdao.deletData(delete.getServiceId());
			adao.updateData(admin);
			
			return delete;
		}
		return null;
	}
	
	public Client saveClient()
	{
		Client client = new Client();
		
		System.out.println("Enter client Name");
		client.setClientName(sc.next());
		System.out.println("Enter client Mail");
		client.setClientMail(sc.next());
		System.out.println("Enter client Contact");
		client.setClientContact(sc.nextLong());
		
		return cdao.saveData(client);
	}
	
	public Client clientLogin()
	{
		System.out.println("Enter the Client Email : ");
		String mail = sc.next();
		Query query = em.createQuery("select c from Client c where c.clientMail = ?1");
		query.setParameter(1, mail);
		Client c = (Client) query.getSingleResult();
		if(c!=null)
		{
			if(c.getClientMail().equals(mail))
			{
				return c;
			}
		}
		return null;
	}
	
	public ClientEvent saveClientEvent()
	{
		ClientEvent clientEvent = new ClientEvent();
		
		Client client = clientLogin();
		if(client!=null)
		{
			
			clientEvent.setClient(client);
			System.out.println("Enter clientEvent Name : ");
			clientEvent.setClientEventName(sc.next());
			System.out.println("Enter the Start Date : ");
			clientEvent.setStartDate(LocalDate.now());
			System.out.println("Enter the No. of Days : ");
			int noOfDays = sc.nextInt();
			clientEvent.setClientEventNoOfDays(noOfDays);
			System.out.println("Enter the Location : ");
			clientEvent.setClientEventLocation(sc.next());
			
			System.out.println("Enter the Event");
			System.out.println("Enter 1 - Marriage ");
			System.out.println("Enter 2 - Engagement ");
			System.out.println("Enter 3 - Bithday ");
			System.out.println("Enter 4 - Anniversary ");
			System.out.println("Enter 5 - BabyShower ");
			System.out.println("Enter 6 - Reunion ");
			System.out.println("Enter 7 - NamingCermony ");
			System.out.println("Enter 8 - Bachelors Party ");
			
			int option = sc.nextInt();
			switch (option) 
			{
			case 1:clientEvent.setEventType(EventType.Marriage);
					break;
			case 2:clientEvent.setEventType(EventType.Engagement);
			 		break;
			case 3:clientEvent.setEventType(EventType.Birthday);
	 				break;
			case 4:clientEvent.setEventType(EventType.Anniversary);
	 				break;
			case 5:clientEvent.setEventType(EventType.BabyShower);
	 				break;
			case 6:clientEvent.setEventType(EventType.Reuion);
	 				break;
			case 7:clientEvent.setEventType(EventType.NamingCeremony);
	 				break;
			case 8:clientEvent.setEventType(EventType.BachelorParty);
	 				break;
			}
			
			System.out.println("Choose the ClientServices");
			System.out.println();
			System.out.println();
			
			List<Service> service = getAllServices();
			for(Service s :service)
			{
				System.out.println(s.getServiceId()+" "+s.getServiceName()+" "+s.getServiceCostPerDay()+" "+s.getServiceCostPerPerson());
			}
			
			System.out.println();
			System.out.println("Enter How many Service required");
			int noOfService = sc.nextInt();
			
			System.out.println("Enter the Service id one by one");
			System.out.println();
			double clientEventCost = 0;
			int count = 1;
			ClientService clientservice = new ClientService();
			List<ClientService> clientservice1 = new ArrayList<ClientService>();
			while(count<=noOfService)
			{
				System.out.println("Enter the Service Id");
				Service service1 = em.find(Service.class, sc.nextInt());
				clientservice.setClientServiceName(service1.getServiceName());
				clientservice.setClientServiceNoOfDays(noOfDays);
				clientservice.setClientserviceCostPerPerson(service1.getServiceCostPerPerson());
				clientservice.setClientServiceCost(service1.getServiceCostPerDay());
				clientEventCost = (noOfDays*service1.getServiceCostPerPerson()*service1.getServiceCostPerDay());
				ClientService clientservice2 = csdao.saveData(clientservice);
				clientservice1.add(clientservice2);
				//clientservice1.add(csdao.saveData(clientservice));
				count++;
			}
			clientEvent.setClientEventCost(clientEventCost);
			clientEvent.setClientService(clientservice1);
			cedao.saveData(clientEvent);
			
			return clientEvent;
		}
		return null;	
	}
	
	public static void main(String[] args) 
	{
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("arun");
//		EntityManager em = emf.createEntityManager();
//		EntityTransaction et = em.getTransaction();
		
		EventManagement em = new EventManagement();
		//System.out.println(em.saveAdmin());
		
		//System.out.println(em.adminLogin());
		
		//System.out.println(em.saveService());
//		Service service = em.find(Service.class,1);
//		
//		Admin admin = em.find(Admin.class, 1);
//		
//		List<Service> ser = new ArrayList<Service>();
//		ser.add(service);
//		
//		admin.setService(ser);
//		
//		et.begin();
//		em.merge(admin);
//		et.commit();
		
		//System.out.println(em.adao.findId(1));
//		List<Service> ser = new ArrayList<Service>();
//		ser.add(em.find(Service.class, 1));
//		ser.add(em.find(Service.class, 2));
//		
//		Admin admin = em.find(Admin.class, 1);
//		
//		admin.setService(ser);
//		
//		et.begin();
//		em.merge(admin);
//		et.commit();
		
//		System.out.println(em.saveClientService());
//		System.out.println(em.getAllClientService());
		
		System.out.println(em.saveClientEvent());
		
	}
	


}

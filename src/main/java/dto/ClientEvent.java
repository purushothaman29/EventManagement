package dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ClientEvent 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientEventId;
	private String clientEventName;
	private LocalDate startDate;
	private int clientEventNoOfDays;
	private String clientEventLocation;
	private double clientEventCost;
	@ManyToOne(cascade = CascadeType.ALL)
	private Client client;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ClientService> clientService;
	private EventType eventType;
	
	public int getClientEventId() {
		return clientEventId;
	}
	public String getClientEventName() {
		return clientEventName;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public int getClientEventNoOfDays() {
		return clientEventNoOfDays;
	}
	public String getClientEventLocation() {
		return clientEventLocation;
	}
	public double getClientEventCost() {
		return clientEventCost;
	}
	public Client getClient() {
		return client;
	}
	public List<ClientService> getClientService() {
		return clientService;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setClientEventId(int clientEventId) {
		this.clientEventId = clientEventId;
	}
	public void setClientEventName(String clientEventName) {
		this.clientEventName = clientEventName;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public void setClientEventNoOfDays(int clientEventNoOfDays) {
		this.clientEventNoOfDays = clientEventNoOfDays;
	}
	public void setClientEventLocation(String clientEventLocation) {
		this.clientEventLocation = clientEventLocation;
	}
	public void setClientEventCost(double clientEventCost) {
		this.clientEventCost = clientEventCost;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public void setClientService(List<ClientService> clientService) {
		this.clientService = clientService;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	
	@Override
	public String toString() {
		return "ClientEvent [clientEventId=" + clientEventId + ", clientEventName=" + clientEventName + ", startDate="
				+ startDate + ", clientEventNoOfDays=" + clientEventNoOfDays + ", clientEventLocation="
				+ clientEventLocation + ", clientEventCost=" + clientEventCost + ", eventType=" + eventType + "]";
	}
	
	
	
	
	
}

package dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClientService 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientserviceId;
	private String clientServiceName;
	private double clientServiceCost;
	private int clientServiceNoOfDays;
	private double clientserviceCostPerPerson;
	
	public int getClientserviceId() {
		return clientserviceId;
	}
	public String getClientServiceName() {
		return clientServiceName;
	}
	public double getClientServiceCost() {
		return clientServiceCost;
	}
	public int getClientServiceNoOfDays() {
		return clientServiceNoOfDays;
	}
	public double getClientserviceCostPerPerson() {
		return clientserviceCostPerPerson;
	}
	public void setClientserviceId(int clientserviceId) {
		this.clientserviceId = clientserviceId;
	}
	public void setClientServiceName(String clientServiceName) {
		this.clientServiceName = clientServiceName;
	}
	public void setClientServiceCost(double clientServiceCost) {
		this.clientServiceCost = clientServiceCost;
	}
	public void setClientServiceNoOfDays(int clientServiceNoOfDays) {
		this.clientServiceNoOfDays = clientServiceNoOfDays;
	}
	public void setClientserviceCostPerPerson(double clientserviceCostPerPerson) {
		this.clientserviceCostPerPerson = clientserviceCostPerPerson;
	}
	
	@Override
	public String toString() {
		return "ClientService [clientserviceId=" + clientserviceId + ", clientServiceName=" + clientServiceName
				+ ", clientServiceCost=" + clientServiceCost + ", clientServiceNoOfDays=" + clientServiceNoOfDays
				+ ", clientserviceCostPerPerson=" + clientserviceCostPerPerson + "]";
	}
	
	
}

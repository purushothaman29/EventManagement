package dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientId;
	private String clientName;
	private long clientContact;
	private String clientMail;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ClientEvent> clientEvent;
	
	public int getClientId() {
		return clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public long getClientContact() {
		return clientContact;
	}
	public String getClientMail() {
		return clientMail;
	}
	public List<ClientEvent> getClientEvent() {
		return clientEvent;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public void setClientContact(long clientContact) {
		this.clientContact = clientContact;
	}
	public void setClientMail(String clientMail) {
		this.clientMail = clientMail;
	}
	public void setClientEvent(List<ClientEvent> clientEvent) {
		this.clientEvent = clientEvent;
	}
	
	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName + ", clientContact=" + clientContact
				+ ", clientMail=" + clientMail + "]";
	}
	

}

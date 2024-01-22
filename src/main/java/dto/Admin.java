package dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Admin 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	private String adminName;
	private long adminContact;
	private String adminEmail;
	private String adminPassword;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Service> service;
	
	public int getAdminId() {
		return adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public long getAdminContact() {
		return adminContact;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public List<Service> getService() {
		return service;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public void setAdminContact(long adminContact) {
		this.adminContact = adminContact;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public void setService(List<Service> service) {
		this.service = service;
	}
	
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminContact=" + adminContact
				+ ", adminEmail=" + adminEmail + ", adminPassword=" + adminPassword + "]";
	}
	
	

}

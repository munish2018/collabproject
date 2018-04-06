package ms.niitmrt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserDetail {
	@Id
	@Column(length=50)
	String loginname;
	@Column(length=50)
	String password;
	@Column(length=50)
	String username;
	@Column(length=15)
	String mobileno;
	@Column(length=100)
	String address;
	@Column(length=5)
	String isonline;
	@Column(length=15)
	String role;
	
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIsonline() {
		return isonline;
	}
	public void setIsonline(String isonline) {
		this.isonline = isonline;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserDetail [loginname=" + loginname + ", password=" + password + ", username=" + username
				+ ", mobileno=" + mobileno + ", address=" + address + ", isonline=" + isonline + ", role=" + role + "]";
	}
}

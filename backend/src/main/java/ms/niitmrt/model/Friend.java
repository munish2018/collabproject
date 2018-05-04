package ms.niitmrt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
@SequenceGenerator(name="friendidseq", sequenceName="friendidseq", initialValue=1, allocationSize=1)
public class Friend {
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="friendidseq")
	private int friendid;
	@Column(length=50)
	String loginname;
	@Column(length=50)
	String friendloginname;
	@Column(length=10)
	String status;
	public int getFriendid() {
		return friendid;
	}
	public void setFriendid(int friendid) {
		this.friendid = friendid;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getFriendloginname() {
		return friendloginname;
	}
	public void setFriendloginname(String friendloginname) {
		this.friendloginname = friendloginname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Friend [friendid=" + friendid + ", loginname=" + loginname + ", friendloginname=" + friendloginname
				+ ", status=" + status + "]";
	}
	
	
	
}

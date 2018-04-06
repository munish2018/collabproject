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
@SequenceGenerator(name="jobapplidseq", sequenceName="jobapplidseq", initialValue=1, allocationSize=1)
public class JobAppl {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="jobapplidseq")
	private int appid;
	private int jobid;
	@Column(length=50)
	private String  username;
	public int getAppid() {
		return appid;
	}
	public void setAppid(int appid) {
		this.appid = appid;
	}
	public int getJobid() {
		return jobid;
	}
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "JobAppl [appid=" + appid + ", jobid=" + jobid + ", username=" + username + "]";
	}
	
	
}

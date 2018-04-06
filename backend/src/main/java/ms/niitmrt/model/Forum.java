package ms.niitmrt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
@SequenceGenerator(name="forumidseq", sequenceName="forumidseq", initialValue=1, allocationSize=1)
public class Forum {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="forumidseq")
	private int forumid;
	@Column(length=50)
	private String forumname;
	@Column(length=100)
	private String forumcontent;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	private Date createdate;
	@Column(length=50)
	private String username;
	@Column(length=5)
	private String status;
	public int getForumid() {
		return forumid;
	}
	public void setForumid(int forumid) {
		this.forumid = forumid;
	}
	public String getForumname() {
		return forumname;
	}
	public void setForumname(String forumname) {
		this.forumname = forumname;
	}
	public String getForumcontent() {
		return forumcontent;
	}
	public void setForumcontent(String forumcontent) {
		this.forumcontent = forumcontent;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Forum [forumid=" + forumid + ", forumname=" + forumname + ", forumcontent=" + forumcontent
				+ ", createdate=" + createdate + ", username=" + username + ", status=" + status + "]";
	}
	
	
	
}

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
@SequenceGenerator(name="blogidseq", sequenceName="blogidseq", initialValue=1, allocationSize=1)
public class Blog {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="blogidseq")
	private int blogid;
	@Column(length=50)
	private String blogname;
	@Column(length=100)
	private String blogcontent;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	private Date createdate;
	@Column(length=50)
	private String username;
	@Column(length=5)
	private String status;
	private int likes;
	
	
	public int getBlogid() {
		return blogid;
	}
	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}
	public String getBlogname() {
		return blogname;
	}
	public void setBlogname(String blogname) {
		this.blogname = blogname;
	}
	public String getBlogcontent() {
		return blogcontent;
	}
	public void setBlogcontent(String blogcontent) {
		this.blogcontent = blogcontent;
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
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	@Override
	public String toString() {
		return "Blog [blogid=" + blogid + ", blogname=" + blogname + ", blogcontent=" + blogcontent + ", createdate="
				+ createdate + ", username=" + username + ", status=" + status + ", likes=" + likes + "]";
	}
	
	
}

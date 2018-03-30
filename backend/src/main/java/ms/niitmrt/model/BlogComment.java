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
@SequenceGenerator(name="blogcommidseq", sequenceName="blogcommidseq", initialValue=1, allocationSize=1)
public class BlogComment {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="blogcommidseq")
	private int blogcommentid;
	private int blogid;
	@Column(length=100)
	private String blogcommenttext;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	private Date commentdate;
	@Column(length=50)
	private String username;
	public int getBlogcommentid() {
		return blogcommentid;
	}
	public void setBlogcommentid(int blogcommentid) {
		this.blogcommentid = blogcommentid;
	}
	public int getBlogid() {
		return blogid;
	}
	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}
	public String getBlogcommenttext() {
		return blogcommenttext;
	}
	public void setBlogcommenttext(String blogcommenttext) {
		this.blogcommenttext = blogcommenttext;
	}
	public Date getCommentdate() {
		return commentdate;
	}
	public void setCommentdate(Date commentdate) {
		this.commentdate = commentdate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "BlogComment [blogcommentid=" + blogcommentid + ", blogid=" + blogid + ", blogcommenttext="
				+ blogcommenttext + ", commentdate=" + commentdate + ", username=" + username + "]";
	}

	
}

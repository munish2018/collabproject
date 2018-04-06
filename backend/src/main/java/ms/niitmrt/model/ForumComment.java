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
@SequenceGenerator(name="forumcommidseq", sequenceName="forumcommidseq", initialValue=1, allocationSize=1)
public class ForumComment {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="forumcommidseq")
	private int forumcommentid;
	private int forumid;
	@Column(length=100)
	private String forumcommenttext;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	private Date commentdate;
	@Column(length=50)
	private String username;
	public int getForumcommentid() {
		return forumcommentid;
	}
	public void setForumcommentid(int forumcommentid) {
		this.forumcommentid = forumcommentid;
	}
	public int getForumid() {
		return forumid;
	}
	public void setForumid(int forumid) {
		this.forumid = forumid;
	}
	public String getForumcommenttext() {
		return forumcommenttext;
	}
	public void setForumcommenttext(String forumcommenttext) {
		this.forumcommenttext = forumcommenttext;
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
		return "ForumComment [forumcommentid=" + forumcommentid + ", forumid=" + forumid + ", forumcommenttext="
				+ forumcommenttext + ", commentdate=" + commentdate + ", username=" + username + "]";
	}
	
	
	
	
}

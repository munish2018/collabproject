package ms.niitmrt.dao;

import java.util.List;

import ms.niitmrt.model.Forum;
import ms.niitmrt.model.ForumComment;

public interface ForumDAO {

	public boolean addForum(Forum forum);
	public boolean deleteForum(Forum forum);
	public boolean updateForum(Forum forum);
	public Forum getForum(int forumid);
	public boolean approveForum(Forum forum);
	public boolean rejectForum(Forum forum);
	public List<Forum> listForum(String username);
		
	public boolean addForumComment(ForumComment forumComment);
	public boolean deleteForumComment(ForumComment forumComment);
	public ForumComment  getForumComment(int forumcommentid);
	public List<ForumComment>  listForumComments(int forumid);
	
}

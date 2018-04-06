package ms.niitmrt.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ms.niitmrt.dao.ForumDAO;
import ms.niitmrt.model.Forum;
import ms.niitmrt.model.ForumComment;

@Repository("forumdao")
public class ForumDAOImpl implements ForumDAO {


	@Autowired
    SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public boolean addForum(Forum forum) {
		try
		{
			sessionFactory.getCurrentSession().save(forum);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Transactional
	@Override
	public boolean deleteForum(Forum forum) {
		try
		{
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}

	@Transactional
	@Override
	public boolean updateForum(Forum forum) {
		try
		{
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public Forum getForum(int forumid) {
		try
		{
			Session session=sessionFactory.openSession();
			Forum forum=(Forum)session.get(Forum.class,forumid);
			session.close();
			return forum;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Transactional
	@Override
	public boolean approveForum(Forum forum) {
		try
		{
			forum.setStatus("A");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	@Transactional
	@Override
	public boolean rejectForum(Forum forum) {
		try
		{
			forum.setStatus("NA");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public List<Forum> listForum(String username) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Forum where username=:username");
		query.setParameter("username",username);
		List<Forum> forum=query.list();
		return forum;
	}
	@Transactional
	@Override
	public boolean addForumComment(ForumComment forumComment) {
		try
		{
			sessionFactory.getCurrentSession().save(forumComment);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	@Transactional
	@Override
	public boolean deleteForumComment(ForumComment forumComment) {
		try
		{
			sessionFactory.getCurrentSession().delete(forumComment);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public ForumComment getForumComment(int forumcommentid) {
		try
		{
			Session session=sessionFactory.openSession();
			ForumComment forumcomment=(ForumComment)session.get(ForumComment.class,forumcommentid);
			session.close();
			return forumcomment;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public List<ForumComment> listForumComments(int forumid) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from ForumComment where forumid=:forumid");
		query.setParameter("forumid",forumid);
		List<ForumComment> forumcomments=query.list();
		return forumcomments;
	}

}

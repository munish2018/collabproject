package ms.niitmrt.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ms.niitmrt.dao.BlogDAO;
import ms.niitmrt.model.Blog;
import ms.niitmrt.model.BlogComment;

@Repository("blogdao")
public class BlogDAOImpl implements BlogDAO {

	@Autowired
    SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public boolean addBlog(Blog blog) {
		try
		{
			sessionFactory.getCurrentSession().save(blog);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	@Transactional
	@Override
	public boolean deleteBlog(Blog blog) {
		try
		{
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Transactional
	@Override
	public boolean updateBlog(Blog blog) {
		try
		{
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public Blog getBlog(int blogId) {
		try
		{
			Session session=sessionFactory.openSession();
			Blog blog=(Blog)session.get(Blog.class,blogId);
			session.close();
			return blog;
		}
		catch(Exception e)
		{
			return null;
		}

	}
	@Override
	public BlogComment getBlogComment(int blogcommentid) {
		try
		{
			Session session=sessionFactory.openSession();
			BlogComment blogcomment=(BlogComment)session.get(BlogComment.class,blogcommentid);
			session.close();
			return blogcomment;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	
	@Transactional
	@Override
	public boolean incrementBlog(Blog blog) {
		try
		{
			int like=blog.getLikes();
			like++;
			blog.setLikes(like);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	@Transactional
	@Override
	public boolean approveBlog(Blog blog) {
		try
		{
			blog.setStatus("A");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}

	}
	
	@Transactional
	@Override
	public boolean rejectBlog(Blog blog) {
		try
		{
			blog.setStatus("NA");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public List<Blog> listBlog(String username) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Blog where username=:username");
		query.setParameter("username",username);
		List<Blog> blog=query.list();
		return blog;

	}
	@Transactional
	@Override
	public boolean addBlogComment(BlogComment blogComment) {
		try
		{
			sessionFactory.getCurrentSession().save(blogComment);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Transactional
	@Override
	public boolean deleteBlogComment(BlogComment blogComment) {
		try
		{
			sessionFactory.getCurrentSession().delete(blogComment);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	
	@Override
	public List <BlogComment> listBlogComments(int blogid) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from BlogComment where blogid=:blogid");
		query.setParameter("blogid",blogid);
		List<BlogComment> blogcomments=query.list();
		return blogcomments;
	}

}

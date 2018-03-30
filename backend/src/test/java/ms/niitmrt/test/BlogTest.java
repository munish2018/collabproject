package ms.niitmrt.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ms.niitmrt.dao.BlogDAO;
import ms.niitmrt.model.Blog;
import ms.niitmrt.model.BlogComment;

public class BlogTest {

private static AnnotationConfigApplicationContext context;
@Autowired
private static BlogDAO blogdao;
private Blog blog;
private BlogComment blogcomment;

	
	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("ms.niitmrt");
		context.refresh();
		blogdao =(BlogDAO) context.getBean("blogdao");
	}
	
	@Ignore
	@Test
	public void testaddblog()
	{ 
		blog=new Blog();
		blog.setBlogname("Junit");
		blog.setBlogcontent("UNit testing ");
		blog.setCreatedate(new java.util.Date());
		blog.setLikes(0);
		blog.setStatus("A");
		blog.setUsername("munish");
		assertEquals("Failed to add data into blog table", true, blogdao.addBlog(blog));
	}
	@Ignore
	@Test
	public void testupdblog()
	{ 
		 blog=blogdao.getBlog(1);
		  blog.setBlogcontent("change in content");
		  assertEquals("failed to  updated  a Blog table",true, blogdao.updateBlog(blog));
	}
	@Ignore
	@Test
	public void testdelblog()
	{ 
		 blog=blogdao.getBlog(2);
		 assertEquals("failed to  updated  a Blog table",true, blogdao.deleteBlog(blog));
	}
	@Ignore
	@Test
	public void testlistblog()
	{
		 assertEquals("failed to fetch  the blog details from  the table",1 ,blogdao.listBlog("munish").size());
	}
	
	@Ignore
	@Test
	public void testappblog()
	{
		blog=blogdao.getBlog(1);
		 assertEquals("failed to approve  the blog details from  the table",true ,blogdao.approveBlog(blog));
	}
	@Ignore
	@Test
	public void testrejectblog()
	{
		blog=blogdao.getBlog(1);
		 assertEquals("failed to reject   the blog details from  the table",true ,blogdao.rejectBlog(blog));
	}
	
	@Ignore
	@Test
	public void testincblog()
	{
		blog=blogdao.getBlog(1);
		 assertEquals("failed to reject   the blog details from  the table",true ,blogdao.incrementBlog(blog));
	}
	@Ignore
	@Test
	public void testaddblogcomment()
	{ 
		blogcomment=new BlogComment();
		blogcomment.setBlogid(1);
		blogcomment.setUsername("munish");
		blogcomment.setBlogcommenttext("blog comment text 2");
		blogcomment.setCommentdate(new java.util.Date());
		assertEquals("Failed to add data into blog comment table", true, blogdao.addBlogComment(blogcomment));
	}
	
	@Ignore
	@Test
	public void testdelblogcomment()
	{ 
		 blogcomment=blogdao.getBlogComment(2);
		 assertEquals("failed to  delete  a Blog table",true, blogdao.deleteBlogComment(blogcomment));
	}
	@Test
	public void testlistblogcomment()
	{ 
		assertEquals("failed to fetch  the blog comment details from  the table",1 ,blogdao.listBlogComments(1).size());
	}
}

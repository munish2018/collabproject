package ms.niitmrt.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ms.niitmrt.dao.BlogDAO;
import ms.niitmrt.dao.ForumDAO;
import ms.niitmrt.model.Blog;
import ms.niitmrt.model.BlogComment;
import ms.niitmrt.model.Forum;
import ms.niitmrt.model.ForumComment;

public class ForumTest {

	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static ForumDAO forumdao;
	private Forum forum;
	private ForumComment forumcomment;
	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("ms.niitmrt");
		context.refresh();
		forumdao =(ForumDAO) context.getBean("forumdao");
	}
	@Ignore
	@Test
	public void testaddforum()
	{ 
		forum=new Forum();
		forum.setCreatedate(new java.util.Date());
		forum.setForumname("hollywood");
		forum.setForumcontent("movies");
		forum.setStatus("A");
		forum.setUsername("munish");
		assertEquals("Failed to add data into forum table", true, forumdao.addForum(forum));
	}
	@Ignore
	@Test
	public void testupdforum()
	{ 
		 forum=forumdao.getForum(4);
		  forum.setForumcontent("change in content");
		  assertEquals("failed to  updated  a forum table",true, forumdao.updateForum(forum));
	}
	
	@Ignore
	@Test
	public void testdelforum()
	{ 
		 forum=forumdao.getForum(2);
		 assertEquals("failed to  delete  a forum table",true, forumdao.deleteForum(forum));
	}
	
	@Ignore
	@Test
	public void testlistforum()
	{
		 assertEquals("failed to fetch  the forum details from  the table",4 ,forumdao.listForum("munish").size());
	}

	@Ignore
	@Test
	public void testappforum()
	{
		forum=forumdao.getForum(1);
		 assertEquals("failed to approve  the forum details from  the table",true ,forumdao.approveForum(forum));
	}
	
	@Ignore
	@Test
	public void testrejectforum()
	{
		 forum=forumdao.getForum(1);
		 assertEquals("failed to reject  the forum details from  the table",true ,forumdao.rejectForum(forum));
	}
	
	
	@Ignore
	@Test
	public void testaddforumcomment()
	{ 
		forumcomment=new ForumComment();
		forumcomment.setForumcommenttext("comment 3");
		forumcomment.setForumid(1);
		forumcomment.setUsername("sharma");
		forumcomment.setCommentdate(new java.util.Date());
		assertEquals("Failed to add data into forum comment table", true, forumdao.addForumComment(forumcomment));
	}
	
	@Ignore
	@Test
	public void testdelforumcomment()
	{ 
		 forumcomment=forumdao.getForumComment(2);
		 assertEquals("failed to  delete  a forumcomment table",true, forumdao.deleteForumComment(forumcomment));
	}
		
	@Test
	public void testlistforumcomment()
	{ 
		assertEquals("failed to fetch  the blog comment details from  the table",2 ,forumdao.listForumComments(1).size());
	}
	
}

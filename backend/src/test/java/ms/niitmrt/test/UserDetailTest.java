package ms.niitmrt.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ms.niitmrt.dao.UserDetailDAO;
import ms.niitmrt.model.UserDetail;

public class UserDetailTest {

	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static UserDetailDAO userdetaildao;
	private UserDetail userdetail;
	
	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("ms.niitmrt");
		context.refresh();
		userdetaildao =(UserDetailDAO) context.getBean("userdetaildao");
	}
	@Ignore
	@Test
	public void testadduserdet()
	{ 
		userdetail=new UserDetail();
		userdetail.setAddress("Meeerut");
		userdetail.setIsonline("N");
		userdetail.setLoginname("munish@gmail.com");
		userdetail.setPassword("munish");
		userdetail.setMobileno("9410607295");
		userdetail.setRole("roleadmin");
		userdetail.setUsername("munish");
		
		assertEquals("Failed to add data into userdetail table", true, userdetaildao.registeruser(userdetail));
	}
	
	@Test
	public void testchecklogin()
	{
		UserDetail ud=new UserDetail();
		ud.setLoginname("munish@gmail.com");
		ud.setPassword("munish");
		assertTrue("Failed to check login",  userdetaildao.checklogin(ud));
		}
	
	@Ignore
	@Test
	public void testupdateonlinestatus()
	{
		UserDetail ud=userdetaildao.getuser("munish@gmail.com");
		System.out.println(" in test username :"+ud.getUsername());
		assertTrue("Failed to update",userdetaildao.updateonlinestatus("Y", ud));
	}
	
}

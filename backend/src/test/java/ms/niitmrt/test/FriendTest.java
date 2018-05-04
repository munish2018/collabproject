package ms.niitmrt.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ms.niitmrt.dao.FriendDAO;
import ms.niitmrt.model.Friend;
import ms.niitmrt.model.UserDetail;

public class FriendTest {

	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static FriendDAO frienddao;
	private Friend friend;
	
	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("ms.niitmrt");
		context.refresh();
		frienddao =(FriendDAO) context.getBean("frienddao");
	}
	
	@Ignore
	@Test
	public void testaddfriend()
	{ 
		friend=new Friend();
		friend.setFriendloginname("sharma@gmail.com");
		friend.setLoginname("munish@gmail.com");
		assertEquals("Failed to add data into blog table", true, frienddao.sendfriendrequest(friend));
	}
	@Ignore
	@Test
	public void testacceptfriendrequest()
	{
            assertTrue("friend request accepted",frienddao.acceptfriendrequest(1));
	}
	@Ignore
	@Test
	public void testfriendrequestdelete()
	{

		assertTrue("Friend request deleted",frienddao.deletefriendrequest(2));
	
	}
	
	@Ignore
	@Test
	public void testfriendshowallrequest()
	{
		List<Friend> listFriend=frienddao.showallfriends("sharma@gmail.com");
		assertNotNull("Problem found null pointer",listFriend);
		
		for(Friend friend:listFriend)
		{
			System.out.println(friend.getLoginname()+":"+friend.getFriendloginname());
		}
	}
	
	@Ignore
	@Test
	public void testfriendrequestpending()
	{
	
		List<Friend> listfriendpending=frienddao.showrequestpendinglist("munish@gmail.com");
		assertNotNull("Problem found null pointer",listfriendpending);
		for(Friend friend:listfriendpending)
		{
			System.out.println(friend.getLoginname()+":"+friend.getFriendloginname());
		}
		
	}
	
	@Test
	public void testfriendshowsuggested()
		{
		List<UserDetail> suggestfriend=(List<UserDetail>)frienddao.showsuggestedfriend("sharma@gmail.com");
		assertNotNull("Problem found null pointer",suggestfriend);
		
		for(UserDetail user:suggestfriend)
		{
			System.out.println(user.getLoginname());
		}
	}
}

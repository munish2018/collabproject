package ms.niitmrt.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ms.niitmrt.dao.FriendDAO;
import ms.niitmrt.model.Friend;
import ms.niitmrt.model.UserDetail;

@RestController
public class FriendController {

	@Autowired
	FriendDAO frienddao;
	
	@PostMapping(value="/sendfriendrequest")
	public ResponseEntity<String> sendfrreq(@RequestBody Friend friend) {
		if (frienddao.sendfriendrequest(friend))
		{
			return new ResponseEntity<String>("Request Sent - Successfully", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Request failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/deletefriendrequest/{friendid}")
	public ResponseEntity<String> delfrreq(@PathVariable("friendid") int friendid)
	{
		if (frienddao.deletefriendrequest(friendid))
			return new ResponseEntity<String>("Delete done  - Successfully", HttpStatus.OK);
		else	
			return new ResponseEntity<String>("Delete failed", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/acceptfriendrequest/{friendid}")
	public ResponseEntity<String> accfrreq(@PathVariable("friendid") int friendid)
	{
		if (frienddao.acceptfriendrequest(friendid))
			return new ResponseEntity<String>("friend request accepted", HttpStatus.OK);
		else	
			return new ResponseEntity<String>("friend request  failed", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/showallfriends")
	public ResponseEntity<List<Friend>> showallfr(HttpSession session)
	{
		String loginname=(String)session.getAttribute("loginname");
		List<Friend>listallfriend=frienddao.showallfriends(loginname);
		
		if (listallfriend.size()>0)
			return new ResponseEntity<List<Friend>>(listallfriend, HttpStatus.OK);
		else	
			return new ResponseEntity<List<Friend>>(listallfriend, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/showpendingrequest")
	public ResponseEntity<List<Friend>> showpendingreq(HttpSession session)
	{
		String loginname=(String)session.getAttribute("loginname");
		List<Friend>listpendingfriend=frienddao.showrequestpendinglist(loginname);
		
		
		if (listpendingfriend.size()>0)
			return new ResponseEntity<List<Friend>>(listpendingfriend, HttpStatus.OK);
		else	
			return new ResponseEntity<List<Friend>>(listpendingfriend, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/showsuggestedfriends")
	public ResponseEntity<List<UserDetail>> showsuggestedfriend(HttpSession session)
	{
		//String loginname=((UserDetail)session.getAttribute("userdetail")).getLoginname();
		String loginname=(String)session.getAttribute("loginname");
		List<UserDetail> listSuggestedFriend=frienddao.showsuggestedfriend(loginname);
		
		if(listSuggestedFriend.size()>0)
		{
			return new ResponseEntity<List<UserDetail>>(listSuggestedFriend,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<UserDetail>>(listSuggestedFriend,HttpStatus.NOT_FOUND);
		}
	}	
	
	
}

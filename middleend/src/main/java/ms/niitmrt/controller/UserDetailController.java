package ms.niitmrt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ms.niitmrt.dao.UserDetailDAO;
import ms.niitmrt.model.UserDetail;

@RestController
public class UserDetailController {
	@Autowired
	UserDetailDAO userdetailDAO;
	
	// ---------------- REgister User Detail -----------------------------------

			@PostMapping(value = "/adduser")
			public ResponseEntity<String> adduser(@RequestBody UserDetail userdetail) {
				userdetail.setRole("roleuser");
				userdetail.setIsonline("N");					
				if (userdetailDAO.registeruser(userdetail)) {
					return new ResponseEntity<String>("User Detail  Added- Success", HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("User Detail  insert failed", HttpStatus.NOT_FOUND);
				}
			}
			
			@PostMapping(value = "/login")
			public ResponseEntity<UserDetail> checklogin(@RequestBody UserDetail userdetail,HttpSession session) {
				
				if (userdetailDAO.checklogin(userdetail))
				{
					UserDetail tempuser=(UserDetail)userdetailDAO.getuser(userdetail.getLoginname());
					userdetailDAO.updateonlinestatus("Y", tempuser);
					session.setAttribute("username", tempuser.getLoginname());
			    	session.setAttribute("role", tempuser.getRole());
			    	System.out.println(" inside login():"+tempuser.getLoginname());
			    	System.out.println(" inside login():"+tempuser.getRole());
					return new ResponseEntity<UserDetail>(tempuser,HttpStatus.OK);
				} 
				else {
					return new ResponseEntity<UserDetail>(userdetail, HttpStatus.NOT_FOUND);
					
				}
			}
			
			@RequestMapping(value="/logout",method=RequestMethod.GET)
		    public ResponseEntity<?> logout(HttpSession session){

		    	if(session.getAttribute("username")==null){
		    		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		    	}

		    	String username=(String)session.getAttribute("username");

		    	UserDetail userDetails=userdetailDAO.getuser(username);
				userdetailDAO.updateonlinestatus("Y",userDetails);
		    	session.removeAttribute("username");
		    	session.invalidate();
		    	return new ResponseEntity<Void>(HttpStatus.OK);

		    }

			
}

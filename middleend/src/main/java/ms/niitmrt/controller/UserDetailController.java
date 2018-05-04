package ms.niitmrt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
				System.out.println("INSIDE ADDUSER :USERNAME"+ userdetail.getUsername()); 
				if (userdetailDAO.registeruser(userdetail)) {
					System.out.println("user detail saved successfully ");
					
					return new ResponseEntity<String>("User Detail  Added- Success", HttpStatus.OK);
					
				} else {
					System.out.println("user detail saved fAILED ");
					return new ResponseEntity<String>("User Detail  insert failed", HttpStatus.NOT_FOUND);
				}
			}
			
			@PostMapping(value="/login")
			public ResponseEntity<UserDetail> checklogindetail(@RequestBody UserDetail userdetail,HttpSession session) {
				System.out.println("  login():"+userdetail.getLoginname());
		    	if (userdetailDAO.checklogin(userdetail))
				{
					UserDetail tempuser=(UserDetail)userdetailDAO.getuser(userdetail.getLoginname());
					userdetailDAO.updateonlinestatus("Y", tempuser);
					session.setAttribute("loginname", tempuser.getLoginname());
					session.setAttribute("username", tempuser.getUsername());
			    	session.setAttribute("role", tempuser.getRole());
			    	System.out.println(" inside login():"+tempuser.getLoginname());
			    	System.out.println(" inside login():"+tempuser.getRole());
					return new ResponseEntity<UserDetail>(tempuser,HttpStatus.OK);
				} 
				else {
					System.out.println(" Error in Login");
					return new ResponseEntity<UserDetail>(userdetail, HttpStatus.NOT_FOUND);
					
				}
			}
			
			@GetMapping(value="/logout")
		    public ResponseEntity<?> logout(HttpSession session){

				System.out.println("Logout function");
				
				
		    	if(session.getAttribute("loginname")==null){
		    		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		    	}

		    	String loginname=(String)session.getAttribute("loginname");
		    	System.out.println("Logout function"+loginname);
		    	UserDetail tempuser=(UserDetail)userdetailDAO.getuser(loginname);
				userdetailDAO.updateonlinestatus("N", tempuser);
		    			    	
		    	session.removeAttribute("username");
		    	session.removeAttribute("loginname");
				session.removeAttribute("role");
		    	session.invalidate();
		    	return new ResponseEntity<Void>(HttpStatus.OK);

		    }

			
}

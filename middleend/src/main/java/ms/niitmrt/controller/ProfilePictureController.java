package ms.niitmrt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ms.niitmrt.dao.ProfilePictureDAO;
import ms.niitmrt.model.ProfilePicture;

@RestController
public class ProfilePictureController {

	@Autowired
	ProfilePictureDAO profilepictureDAO;
	
	@RequestMapping(value="/doUpload",method=RequestMethod.POST)
	public ResponseEntity<?> uploadPicture(@RequestParam(value="file")CommonsMultipartFile fileupload,HttpSession session)
	{
	
		String username=(String)session.getAttribute("loginname");
		
		if(username==null) 
		{
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		else
		{
			System.out.println("Success");
			ProfilePicture profilePicture=new ProfilePicture();
			profilePicture.setLoginname(username);
			profilePicture.setImage(fileupload.getBytes());
			profilepictureDAO.save(profilePicture);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/getImage")
	public @ResponseBody byte[] getProfilePic(HttpSession session)
	{
		String loginname=(String)session.getAttribute("loginname");
		System.out.println("profile  getimAGE 11 "+loginname);
		if(loginname==null)
				return null;
		ProfilePicture profilePicture=profilepictureDAO.get(loginname);
		System.out.println("profile  getimAGE 222 "+loginname);
		
		if(profilePicture==null)
		{
			return null;
		}
		else
		{
			return profilePicture.getImage();
		}
	}
}

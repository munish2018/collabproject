package ms.niitmrt.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ms.niitmrt.dao.ProfilePictureDAO;
import ms.niitmrt.model.ProfilePicture;

@Repository("profilePictureDAO")
public class ProfilePictureDAOImpl implements ProfilePictureDAO {

	@Autowired
	SessionFactory sessionfactory;
	
	@Override
	public void save(ProfilePicture profilePicture) {
		Session session=sessionfactory.openSession();
		session.save(profilePicture);
		session.flush();
		session.close();
	}

	@Override
	public ProfilePicture get(String loginname) {
		Session session=sessionfactory.openSession();
		ProfilePicture profilePicture=(ProfilePicture)session.get(ProfilePicture.class, loginname);
		session.close();
		return profilePicture;
	}

}

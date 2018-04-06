package ms.niitmrt.dao;

import ms.niitmrt.model.ProfilePicture;

public interface ProfilePictureDAO {
	
public void save(ProfilePicture profilePicture);
public ProfilePicture get(String loginname);
}

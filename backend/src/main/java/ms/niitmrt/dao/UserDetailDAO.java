package ms.niitmrt.dao;

import ms.niitmrt.model.UserDetail;

public interface UserDetailDAO {
	public boolean registeruser(UserDetail userdetail);
	public boolean checklogin(UserDetail userdetail);
	public boolean updateonlinestatus(String status,UserDetail userdetail);
	public UserDetail getuser(String loginname);
		
}

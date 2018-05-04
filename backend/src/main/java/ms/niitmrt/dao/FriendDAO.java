package ms.niitmrt.dao;

import java.util.List;

import ms.niitmrt.model.Friend;
import ms.niitmrt.model.UserDetail;

public interface FriendDAO {
	
	public boolean sendfriendrequest(Friend friend);
	public boolean deletefriendrequest(int friendid);
	public boolean acceptfriendrequest(int friendid);
	public List<UserDetail>showsuggestedfriend(String loginname);
	public List<Friend>showallfriends(String loginname);
	public List<Friend> showrequestpendinglist(String loginname);
}

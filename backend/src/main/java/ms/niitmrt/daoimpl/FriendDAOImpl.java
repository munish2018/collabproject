package ms.niitmrt.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ms.niitmrt.dao.FriendDAO;
import ms.niitmrt.model.Friend;
import ms.niitmrt.model.UserDetail;

@Repository("frienddao")
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	SessionFactory sessionfactory;
	
	@Transactional
	@Override
	public boolean sendfriendrequest(Friend friend) {
		try
		{
			friend.setStatus("P");
			sessionfactory.getCurrentSession().save(friend);
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}

	}

	@Transactional
	@Override
	public boolean deletefriendrequest(int friendid) {
		try
		{
			Session session=sessionfactory.openSession();
			Friend friend =(Friend)session.get(Friend.class,friendid);
			sessionfactory.getCurrentSession().delete(friend);
			session.close();
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
		
	}

	@Transactional
	@Override
	public boolean acceptfriendrequest(int friendid) {
		try
		{
		Session session=sessionfactory.openSession();
		Friend friend=(Friend)session.get(Friend.class, friendid);
		friend.setStatus("A");
		sessionfactory.getCurrentSession().update(friend);
		session.close();
		return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}

	@Override
	public List<UserDetail> showsuggestedfriend(String loginname) {
		
		Session session=sessionfactory.openSession();
		SQLQuery query=session.createSQLQuery("select loginname from UserDetail where loginname not in (select friendloginname from Friend where loginname='"+loginname+"')and loginname!='"+loginname+"'");
		List<Object> suggestFriendname=(List<Object>)query.list();
		List<UserDetail> suggestFriendList=new ArrayList<UserDetail>();
		int i=0;
		while(i<suggestFriendname.size())
		{
			UserDetail userDetail=(UserDetail)session.get(UserDetail.class, (String)suggestFriendname.get(i));
			suggestFriendList.add(userDetail);
			i++;
		}
		return suggestFriendList;
	}

	@Override
	public List<Friend> showallfriends(String loginname) {
		Session session=sessionfactory.openSession();
		Query query=session.createQuery("from  Friend where loginname=:currentuser and status='A'");
		query.setParameter("currentuser", loginname);
		List<Friend>listfriends=query.list();
		return listfriends;
	}

	@Override
	public List<Friend> showrequestpendinglist(String loginname) {
		Session session=sessionfactory.openSession();
		Query query=session.createQuery("from  Friend where loginname=:currentuser and status='P'");
		query.setParameter("currentuser", loginname);
		List<Friend>listfriends=query.list();
		return listfriends;
	}

}

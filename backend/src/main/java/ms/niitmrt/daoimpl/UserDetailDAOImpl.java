package ms.niitmrt.daoimpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ms.niitmrt.dao.UserDetailDAO;
import ms.niitmrt.model.UserDetail;


@Repository("userdetaildao")
public class UserDetailDAOImpl implements UserDetailDAO {

	@Autowired
    SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public boolean registeruser(UserDetail userdetail) {
		try
		{
			sessionFactory.getCurrentSession().save(userdetail);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean checklogin(UserDetail userdetail) {
		try
		{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from UserDetail where loginname=:lname and password=:pword");
			query.setParameter("lname",userdetail.getLoginname());
			query.setParameter("pword",userdetail.getPassword());
			UserDetail ud=(UserDetail)query.list().get(0);
			if(ud==null)
				return false;
			else			
				return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Transactional
	@Override
	public boolean updateonlinestatus(String status, UserDetail userdetail) {
		try
		{
			userdetail.setIsonline(status);
			sessionFactory.getCurrentSession().update(userdetail);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public UserDetail getuser(String loginname) {
		Session session=sessionFactory.openSession();
		UserDetail ud=(UserDetail)session.get(UserDetail.class, loginname);
		System.out.println("username:"+ ud.getUsername());
		return ud;
	}

}

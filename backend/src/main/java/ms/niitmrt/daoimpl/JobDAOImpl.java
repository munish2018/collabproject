package ms.niitmrt.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ms.niitmrt.dao.JobDAO;
import ms.niitmrt.model.Job;
import ms.niitmrt.model.JobAppl;


@Repository("jobdao")
public class JobDAOImpl implements JobDAO {

	@Autowired
    SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public boolean insertJob(Job job) {
		try
		{
			sessionFactory.getCurrentSession().save(job);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	@Transactional
	@Override
	public boolean updateJob(Job job) {
		try
		{
			sessionFactory.getCurrentSession().update(job);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	@Transactional
	@Override
	public boolean deleteJob(Job job) {
		try
		{
			sessionFactory.getCurrentSession().delete(job);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public List<Job> getJob() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Job");
		List<Job> joblist=query.list();
		return joblist;
	}

	@Override
	public Job getJobById(int jobId) {
		try
		{
			Session session=sessionFactory.openSession();
			Job job=(Job)session.get(Job.class,jobId);
			session.close();
			return job;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Transactional
	@Override
	public boolean applyForJob(JobAppl applyForJob) {
		try
		{
			sessionFactory.getCurrentSession().save(applyForJob);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}

	}

	public List<JobAppl> getAllJobApplication()
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from JobAppl");
		List<JobAppl> jobappllist=query.list();
		return jobappllist;
	}
	
	@Override
	public List<JobAppl> getAllAppliedbyjobid(int jobId) {
		try
		{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from JobAppl where jobid=:jid");
			query.setParameter("jid",jobId);
			List<JobAppl> jobappllist=(List<JobAppl>)query.list();
			return jobappllist;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	
	@Override
	public boolean checkIfApplied(int jobId, String username) {
		
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from JobAppl where jobid=:jid and username=:uname");
		query.setParameter("jid",jobId);
		query.setParameter("uname",username);
		int l=query.list().size();
		if(l>0)
			return true;
		else
			return false;
	}

	
	
	
}

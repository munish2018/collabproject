package ms.niitmrt.daoimpl;

import java.util.List;

import ms.niitmrt.dao.JobDAO;
import ms.niitmrt.model.Job;
import ms.niitmrt.model.JobAppl;

public class JobDAOImpl implements JobDAO {

	@Override
	public boolean insertOrUpdateJob(Job job) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteJob(Job job) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Job> getJob() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Job getJobById(int jobId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobAppl> getAllAppliedUser(int jobId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void applyForJob(JobAppl applyForJob) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkIfApplied(int jobId, String username) {
		// TODO Auto-generated method stub
		return false;
	}

}

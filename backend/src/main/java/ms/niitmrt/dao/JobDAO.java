package ms.niitmrt.dao;

import java.util.List;

import ms.niitmrt.model.Job;
import ms.niitmrt.model.JobAppl;

public interface JobDAO {

	public boolean insertOrUpdateJob(Job job);
	public boolean deleteJob(Job job);
	public List<Job> getJob();
	public Job getJobById(int jobId);
	public List<JobAppl> getAllAppliedUser(int jobId);
	public void applyForJob(JobAppl applyForJob);
	public boolean checkIfApplied(int jobId,String username);
	
}

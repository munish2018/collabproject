package ms.niitmrt.dao;

import java.util.List;

import ms.niitmrt.model.Job;
import ms.niitmrt.model.JobAppl;

public interface JobDAO {

	public boolean insertJob(Job job);
	public boolean updateJob(Job job);
	public boolean deleteJob(Job job);
	public List<Job> getJob();
	public Job getJobById(int jobId);
	public List<JobAppl> getAllAppliedbyjobid(int jobId);
	public boolean applyForJob(JobAppl applyForJob);
	public boolean checkIfApplied(int jobId,String username);
	public List<JobAppl> getAllJobApplication();
}

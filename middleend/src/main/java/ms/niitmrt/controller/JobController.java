package ms.niitmrt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ms.niitmrt.dao.JobDAO;
import ms.niitmrt.model.Job;
import ms.niitmrt.model.JobAppl;

@RestController
public class JobController {
	@Autowired
	JobDAO jobdao;
	
	// ---------------- Add Job Detail -----------------------------------

				@PostMapping(value = "/addjob")
				public ResponseEntity<String> addjob(@RequestBody Job job) {
					job.setPosted_on(new java.util.Date());					
					if (jobdao.insertJob(job)) {
						return new ResponseEntity<String>("Job Added- Success", HttpStatus.OK);
					} else {
						return new ResponseEntity<String>("Job insert failed", HttpStatus.NOT_FOUND);
					}
				}
	
				// ------------------Update Job -----------------------------------

				@PutMapping(value = "/updatejob/{jobid}")
				public ResponseEntity<String> updatejob(@PathVariable("jobid") int jobid, @RequestBody Job job) {
					System.out.println("Job " + jobid);
					Job tempjob = jobdao.getJobById(jobid);
					
					if (tempjob == null) {
						System.out.println("Job with jobid " + jobid + " Not Found");
						return new ResponseEntity<String>("Update Job Failue", HttpStatus.NOT_FOUND);
					}
					
					tempjob.setCompany_name(job.getCompany_name());
					tempjob.setLocation(job.getLocation());	
					tempjob.setContact(job.getContact());
					tempjob.setExperience(job.getExperience());
					tempjob.setJob_description(job.getJob_description());
					tempjob.setSalary(job.getSalary());
					tempjob.setSkills_required(job.getSkills_required());
					tempjob.setVacancies(job.getVacancies());
					tempjob.setJob_title(job.getJob_title());
					
					
					jobdao.updateJob(tempjob);
					return new ResponseEntity<String>("Update job Success", HttpStatus.OK);
				}
				
				// -------------------------Delete Job ---------------------

				@PostMapping(value = "/deletejob/{jobId}")
				public ResponseEntity<String> deletejob(@PathVariable("jobId") int jobId) {
					System.out.println("Delete job with job  Id: " + jobId);
					Job tempjob = jobdao.getJobById(jobId);
					
					if (tempjob == null) {
						System.out.println("No job " + jobId + " found to delete");
						return new ResponseEntity<String>("No job with job Id: " + jobId + " found to delete",
								HttpStatus.NOT_FOUND);
					} else {
						jobdao.deleteJob(tempjob);
						return new ResponseEntity<String>("job with job Id " + jobId + " deleted successfully", HttpStatus.OK);
					}
				}	
				
				// -----------------list jobs ---------------------------------

				@GetMapping(value = "/listjobs")
				public ResponseEntity<List<Job>> listjob() {
					List<Job> listjobs = jobdao.getJob();
					if (listjobs.size() != 0) {
						return new ResponseEntity<List<Job>>(listjobs, HttpStatus.OK);
					} else {
						return new ResponseEntity<List<Job>>(listjobs, HttpStatus.NOT_FOUND);
					}
				}
				
				// -----------------------Get Job ------------------------------------

				@GetMapping(value = "/getjob/{jobid}")
				public ResponseEntity <Job> getjob(@PathVariable("jobid") int jobid)
				{
					Job job = jobdao.getJobById(jobid);
					
					if (job == null) {
						return new ResponseEntity <Job>(job, HttpStatus.NOT_FOUND);
					} else {
						return new ResponseEntity<Job>(job, HttpStatus.OK);
					}
				}
			
				// ---------------- Add Job Application Detail -----------------------------------

				@PostMapping(value = "/addjobappl")
				public ResponseEntity<String> addjobappl(@RequestBody JobAppl jobappl) {
									
					if (jobdao.applyForJob(jobappl)) {
						return new ResponseEntity<String>("Job Application  Added- Success", HttpStatus.OK);
					} else {
						return new ResponseEntity<String>("Job Application insert failed", HttpStatus.NOT_FOUND);
					}
				}	
				
				// -----------------list jobs application ---------------------------------

				@GetMapping(value = "/listjobappl")
				public ResponseEntity<List<JobAppl>> listjobappl() {
					List<JobAppl> jobappls = jobdao.getAllJobApplication();
					if (jobappls.size() != 0) {
						return new ResponseEntity<List<JobAppl>>(jobappls, HttpStatus.OK);
					} else {
						return new ResponseEntity<List<JobAppl>>(jobappls, HttpStatus.NOT_FOUND);
					}
				}	
				
				// -----------------list jobs application by job ID---------------------------------

				@GetMapping(value = "/listjobapplbyjobid/{jobid}")
				public ResponseEntity<List<JobAppl>> listjobapplbyjobid(@PathVariable("jobid") int jobid) {
					List<JobAppl> jobappls = jobdao.getAllAppliedbyjobid(jobid);
					if (jobappls.size() != 0) {
						return new ResponseEntity<List<JobAppl>>(jobappls, HttpStatus.OK);
					} else {
						return new ResponseEntity<List<JobAppl>>(jobappls, HttpStatus.NOT_FOUND);
					}
				}	
				
}

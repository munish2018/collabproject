package ms.niitmrt.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import ms.niitmrt.dao.JobDAO;
import ms.niitmrt.model.Job;
import ms.niitmrt.model.JobAppl;

public class JobTest {

	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static JobDAO jobdao;
	private Job job;
	private JobAppl jobappl;
	
	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("ms.niitmrt");
		context.refresh();
		jobdao =(JobDAO) context.getBean("jobdao");
	}
	
	@Ignore
	@Test
	public void testaddjob()
	{ 
		job=new Job();
		job.setCompany_name("abcd");
		job.setContact("9890283");
		job.setExperience(2);
		job.setJob_description("programmer");
		job.setJob_title("programmer");
		job.setLocation("Delhi NCR");
		job.setPosted_on(new java.util.Date());
		job.setSalary(21000);
		job.setSkills_required("java knowledge");
		job.setVacancies(10);
		assertEquals("Failed to add data into Job table", true, jobdao.insertJob(job));
	}
	@Ignore
	@Test
	public void testupdatejob()
	{ 
		job=jobdao.getJobById(2);
		job.setCompany_name("CESR");
		assertEquals("Failed to update data into Job table", true, jobdao.updateJob(job));
	}
	@Ignore
	@Test
	public void testdeletejob()
	{ 
		job=jobdao.getJobById(2);
		assertEquals("Failed to update data into Job table", true, jobdao.deleteJob(job));
	}
	
	@Ignore
	@Test
	public void testgetdispalljob()
	{ 
		assertEquals("Failed to display  data from  Job table", 1, jobdao.getJob().size());
	}
	
	@Ignore
	@Test
	public void testaddjobappl()
	{ 
		jobappl=new JobAppl();
		jobappl.setJobid(1);
		jobappl.setUsername("munish");
		assertEquals("Failed to add data into Job Application  table", true, jobdao.applyForJob(jobappl));
	}
	
	@Ignore
	@Test
	public void testgetdispalljobappl()
	{ 
		assertEquals("Failed to display  data from  Job application table", 2, jobdao.getAllJobApplication().size());
	}
	
	@Test
	public void testgetdispalljobapplbyjobid()
	{ 
		assertEquals("Failed to display  data from  Job application table", 2, jobdao.getAllAppliedbyjobid(1).size());
	}

}

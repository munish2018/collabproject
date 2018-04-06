package ms.niitmrt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
@SequenceGenerator(name="jobidseq", sequenceName="jobidseq", initialValue=1, allocationSize=1)
public class Job {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="jobidseq")
	private int jobid;
	@Column(length=50)
	private String job_title;
	@Column(length=100)
	private String job_description;
	@Column(length=100)
	private String skills_required;
	private int salary;
	private int vacancies;
	private int experience;
	@Column(length=50)
	private String location;
	@Column(length=50)
	private String company_name;
	@Column(length=20)
	private String  contact; 
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	private Date posted_on;
	
	public int getJobid() {
		return jobid;
	}
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	public String getJob_description() {
		return job_description;
	}
	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}
	public String getSkills_required() {
		return skills_required;
	}
	public void setSkills_required(String skills_required) {
		this.skills_required = skills_required;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getVacancies() {
		return vacancies;
	}
	public void setVacancies(int vacancies) {
		this.vacancies = vacancies;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Date getPosted_on() {
		return posted_on;
	}
	public void setPosted_on(Date posted_on) {
		this.posted_on = posted_on;
	}
	
	@Override
	public String toString() {
		return "Job [jobid=" + jobid + ", job_title=" + job_title + ", job_description=" + job_description
				+ ", skills_required=" + skills_required + ", salary=" + salary + ", vacancies=" + vacancies
				+ ", experience=" + experience + ", location=" + location + ", company_name=" + company_name
				+ ", contact=" + contact + ", posted_on=" + posted_on + "]";
	}

	
}

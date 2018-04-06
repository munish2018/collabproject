package ms.niitmrt.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ms.niitmrt.model.Blog;
import ms.niitmrt.model.BlogComment;
import ms.niitmrt.model.Forum;
import ms.niitmrt.model.ForumComment;
import ms.niitmrt.model.Job;
import ms.niitmrt.model.JobAppl;
import ms.niitmrt.model.ProfilePicture;
import ms.niitmrt.model.UserDetail;

@Configuration
@ComponentScan("ms.niitmrt.*")
@EnableTransactionManagement
public class hiberconfig {

	@Bean(name="dataSource")
	public DataSource getDataSource() {
		System.out.println(" data source start");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/XE");
		dataSource.setUsername("system");
		dataSource.setPassword("Admin_123");
		System.out.println(" data source created");
		return dataSource;
	}

	private Properties getHibernateProperties() {
		System.out.println(" hib prop start");
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		System.out.println(" hib prop ends");
		return properties;
	}

	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		System.out.println(" sess factory start");
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(BlogComment.class);
		sessionBuilder.addAnnotatedClass(UserDetail.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(ForumComment.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(JobAppl.class);
		sessionBuilder.addAnnotatedClass(ProfilePicture.class);
		System.out.println(" sess factory ends");
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		System.out.println(" tran manager  start");
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		System.out.println(" tran manager  ends");
		return transactionManager;
	}
	
}

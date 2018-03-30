package ms.niitmrt.test;

import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class configtest {

private static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("ms.niitmrt");
		context.refresh();
	}
	
	@Test
	public void test()
	{ 
		fail("failed");
		
	}
	
}

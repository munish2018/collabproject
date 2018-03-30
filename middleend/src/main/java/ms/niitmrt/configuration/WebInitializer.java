package ms.niitmrt.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import ms.niitmrt.config.hiberconfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
	return new Class[] { WebResolver.class , hiberconfig.class};
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;}

	@Override
	protected String[] getServletMappings() {
	return new String[] { "/" };
	}
}
package org.tbwork.anole.loader.core.impl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tbwork.anole.loader.util.StringUtil; 

/**
 * <p> This is for web applications to load anole configurations.
 * <p> <b>Pay attention !!!</b> Dislike the spring context loader, in web 
 * mode, anole allows and only allows to load configuration files 
 * within the class path. So make sure you added your configuration
 *  files under the class path directories. Maybe you will ask why?
 * No meaningful answer, just to "make life easier".
 * <p> Usage example:
 * <p> In the <b>web.xml</b>:
 * <pre>
 *      &lt;context-param&gt;
 *    	&lt;param-name&gt;anoleConfigLocation&lt;/param-name&gt
 *    	&lt;param-value&gt;
 *    	    	dev.anole
 *    	    	prd.anole
 *    	    	config/common.custom_suffix
 *    	&lt;/param-value&gt;
 *      &lt;/context-param&gt; 
 *      ...
 *      &lt;listener&gt;
 *            &lt;listener-class&gt;org.tbwork.anole.subscriber.core.impl.WebAnoleLoaderListener&lt;/listener-class&gt;
 *      &lt;/listener&gt; 
 * </pre> 
 * <p> <b>Tips:</b> If you use the default naming style of configuration
 * files which suffixes files with ".anole" and put them under the 
 * class-path folder directly, you do not have to set the anoleConfigLocation 
 * <b>context-param</b> in the web.xml. This is due to that anole will 
 * search and read configurations from all anole-style files under the 
 * class-path folder if you do not set the <b>context-param</b>.
 *  @author Tommy.Tang
 */ 
public class WebAnoleLoaderListener extends AnoleClasspathLoader implements ServletContextListener{

	private static Logger logger = LoggerFactory.getLogger(WebAnoleLoaderListener.class);
  
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		 String configLocationString =  sce.getServletContext().getInitParameter("anoleConfigLocation");
		 if(configLocationString==null || configLocationString.isEmpty()){
			 logger.warn("[!] There is no anole configuration file specified in this web application. Anole will load all *.anole files from the classpath directory.");
			 this.load();
		 }
		 else  
			 this.load(StringUtil.splitConfigLocations(configLocationString));  
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) { 
		logger.info("[:)] Application is shutting down..." ); 
	}
   
}

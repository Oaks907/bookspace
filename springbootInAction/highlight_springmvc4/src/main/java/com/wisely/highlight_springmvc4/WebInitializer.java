package com.wisely.highlight_springmvc4;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Create by haifei on 25/8/2018.
 */
public class WebInitializer implements WebApplicationInitializer{


  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    ctx.register(MyMvcConfig.class);
    ctx.setServletContext(servletContext);

    ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
    servlet.addMapping("/");
    servlet.setLoadOnStartup(1);
    servlet.setAsyncSupported(true);
  }
}

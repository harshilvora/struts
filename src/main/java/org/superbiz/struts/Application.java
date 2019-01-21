package org.superbiz.struts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public FilterRegistrationBean sitemeshFilter() {
        FilterRegistrationBean casFilter = new FilterRegistrationBean();
        casFilter.setFilter(filter("com.opensymphony.sitemesh.webapp.SiteMeshFilter"));
        casFilter.addUrlPatterns("/*");
        casFilter.setOrder(1);

        return casFilter;
    }

    @Bean
    public FilterRegistrationBean struts2Filter() {
        FilterRegistrationBean casFilter = new FilterRegistrationBean();
        casFilter.setFilter(filter("org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter"));
        casFilter.addUrlPatterns("/*");
        casFilter.setOrder(2);

        return casFilter;
    }

    private Filter filter(String className) {
        Class clazz = null;
        try {
            clazz = Class.forName(className);
            Object obj = clazz.newInstance();
            return (Filter) obj;
        } catch (Exception e) {
            throw new RuntimeException("instance filter fail", e);
        }

    }

}

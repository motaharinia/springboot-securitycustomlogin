package com.motaharinia.base.configuration.securityconfig;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Step 3
 * Now, load WebSecurityConfig in our existing ApplicationInitializer and add into the getRootConfigClasses() method.
 * گام 3
 * اکنون WebSecurityConfig را در ApplicationInitializer موجود خود بارگذاری کرده و به متد getRootConfigClasses () اضافه کنید.
 */
public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { WebSecurityConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}

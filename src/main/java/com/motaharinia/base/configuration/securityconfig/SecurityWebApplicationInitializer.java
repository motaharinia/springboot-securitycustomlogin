package com.motaharinia.base.configuration.securityconfig;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Step 2
 * Now, we will register springSecurityFilterChain with the war.
 * To register, Spring Security provides a base class AbstractSecurityWebApplicationInitializer that we need to extend.
 * For Spring MVC application, SecurityWebApplicationInitializer will look like below.
 * This code will register the springSecurityFilterChain for every URL in our application.
 * گام 2
 * اکنون ، SpringSecurityFilterChain را با war ثبت خواهیم کرد.
 * برای ثبت نام ، Spring Security یک کلاس پایه AbstractSecurityWebApplicationInitializer را فراهم می کند که باید آن را گسترش دهیم.
 * برای برنامه Spring MVC ، SecurityWebApplicationInitializer مانند زیر ظاهر خواهد شد.
 * با استفاده از این کد ، SpringSecurityFilterChain برای هر URL در برنامه ما ثبت می شود.
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
}

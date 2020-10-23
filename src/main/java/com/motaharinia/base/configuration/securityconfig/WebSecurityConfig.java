package com.motaharinia.base.configuration.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * https://www.baeldung.com/java-config-spring-security
 * https://www.baeldung.com/spring-security-expressions
 *
 * 4.HTTP Security:
 * To enable HTTP Security in Spring, we need to extend the WebSecurityConfigurerAdapter to provide a default configuration in the configure(HttpSecurity http) method:
 * برای فعال کردن امنیت HTTP در Spring ، باید WebSecurityConfigurerAdapter را گسترش دهیم تا پیکربندی پیش فرض را در متد پیکربندی (HttpSecurance http) ارائه دهیم:
 *     protected void configure(HttpSecurity http) throws Exception {
 *          http.authorizeRequests()
 *            .anyRequest().authenticated()
 *            .and().httpBasic();
 *      }
 * The above default configuration makes sure any request to the application is authenticated with form based login or HTTP basic authentication.
 * پیکربندی پیش فرض فوق اطمینان می دهد که هر درخواستی برای برنامه با ورود به فرم یا HTTP basic authentication تأیید شود.
 *
 * 4.1. hasRole, hasAnyRole:
 * These expressions are responsible for defining the access control or authorization to specific URLs or methods in your application.
 *  این عبارات وظیفه تعیین کنترل دسترسی یا مجوز به URL یا متدهای خاص در برنامه شما را دارند.
 *    http.authorizeRequests()
 *     .antMatchers("/auth/admin/*").hasRole("ADMIN")
 *     .antMatchers("/auth/*").hasAnyRole("ADMIN","USER")
 *
 * 4.2. hasAuthority, hasAnyAuthority:
 * Roles and authorities are similar in Spring.
 * The main difference is that, roles have special semantics – starting with Spring Security 4, the ‘ROLE_‘ prefix is automatically added (if it's not already there) by any role related method.
 * So hasAuthority(‘ROLE_ADMIN') is similar to hasRole(‘ADMIN') because the ‘ROLE_‘ prefix gets added automatically.
 * But the good thing about using authorities is that we don't have to use the ROLE_ prefix at all
 * نقش ها و authorities در Spring مشابه هستند.
 * تفاوت اصلی در این است که ، نقش ها معنایی ویژه دارند - از شروع Spring Security 4 ، پیشوند ‘ROLE_' به طور خودکار با هر متد (اگر از قبل وجود نداشته باشد) مرتبط با نقش اضافه می شود.
 * بنابراین hasAuthority (‘ROLE_ADMIN ') مشابه hasRole (‘ ADMIN') است زیرا پیشوند ‘ROLE_' به صورت خودکار اضافه می شود.
 * اما نکته خوب در استفاده از authorities این است که ما اصلاً لازم نیست از پیشوند ROLE_ استفاده کنیم
 *     http.authorizeRequests()
 *     .antMatchers("/auth/admin/*").hasAuthority("ADMIN")
 *     .antMatchers("/auth/*").hasAnyAuthority("ADMIN", "USER")
 *
 * 4.3. permitAll, denyAll:
 * Those two annotations are also quite straightforward. We either may permit access to some URL in our service or we may deny access.
 * Let's have a look at the example:
 * آن دو annotations نیز کاملاً ساده هستند. ما یا ممکن است دسترسی به برخی از نشانی های اینترنتی را در سرویس خود مجاز کنیم یا ممکن است دسترسی را محروم کنیم.
 * بیایید نگاهی به مثال بزنیم:
 *      http.authorizeRequests().antMatchers("/*").permitAll()
 * With this config, we will authorize all users (both anonymous and logged in) to access the page starting with ‘/' (for example, our homepage).
 * We can also deny access to our entire URL space:
 * با استفاده از این پیکربندی ، ما به همه کاربران (اعم از ناشناس و وارد شده) اجازه می دهیم تا از صفحه شروع با ‘/ '(به عنوان مثال صفحه اصلی ما) به صفحه دسترسی پیدا کنند.
 * ما همچنین می توانیم دسترسی به کل فضای URL خود را انکار کنیم:
 *     http.authorizeRequests().antMatchers("/*").denyAll()
 *
 * 4.4. isAnonymous, isRememberMe, isAuthenticated, isFullyAuthenticated:
 * In this subsection we focus on expressions related to login status of the user.
 * Let's start with user that didn't log in to our page.
 * By specifying following in Java config, we enable all unauthorized users to access our main page:
 * در این بخش ما به عبارات مربوط به وضعیت ورود کاربر متمرکز شده ایم.
 * بیایید با کاربرانی شروع کنیم که به صفحه ما وارد نشده اند.
 * با مشخص کردن موارد زیر در تنظیمات جاوا ، همه کاربران غیرمجاز را قادر می کنیم تا به صفحه اصلی ما دسترسی پیدا کنند:
 *    http.authorizeRequests().antMatchers("/*").anonymous()
 * If we want to secure the website that everybody who uses it will be required to login, we need to use isAuthenticated() method:
 * اگر می خواهیم وب سایت ایمن کنیم که هرکسی که از آن استفاده می کند لازمه ورود به سیستم باشد ، باید از متد isAuthenticated () استفاده کنیم:
 *    http.authorizeRequests().antMatchers("/*").authenticated()
 *Moreover, we have two additional expressions, isRememberMe() and isFullyAuthenticated().
 * Through the use of cookies, Spring enables remember-me capabilities so there is no need to log into the system each time.
 * In order to give the access to users that were logged in only by remember me function, we may use this:
 * علاوه بر این ، ما دو عبارت اضافی داریم ، isRememberMe () و isFullyAuthenticated ().
 * اسپرینگ با استفاده از کوکی ها ، قابلیت های یادآوری را قادر می سازد ، بنابراین دیگر نیازی به ورود به سیستم نیست.
 *به منظور دسترسی به کاربرانی که فقط با استفاده از یادآوری عملکرد من وارد سیستم شده اند ، ممکن است از این موارد استفاده کنیم:
 *     http.authorizeRequests().antMatchers("/*").rememberMe()
 * Finally, some parts of our services require the user to be authenticated again even if the user is already logged in.
 * For example, user wants to change settings or payment information; it's of course good practice to ask for manual authentication in the more sensitive areas of the system.
 * In order to do that, we may specify isFullyAuthenticated(), which returns true if the user is not an anonymous or a remember-me user:
 * سرانجام ، برخی از قسمت های خدمات ما حتی اگر کاربر از قبل وارد سیستم شده باشد ، نیاز به تأیید مجدد کاربر دارد.
 * به عنوان مثال ، کاربر می خواهد تنظیمات یا اطلاعات پرداخت را تغییر دهد. البته این کار خوب است که در مناطق حساس تر سیستم درخواست احراز هویت دستی کنید.
 * برای انجام این کار ، می توانیم isFullyAuthenticated () را مشخص کنیم که در صورت عدم استفاده کاربر ناشناس یا یادآوری من ، درست است.
 *     http.authorizeRequests().antMatchers("/*").fullyAuthenticated()
 *
 * 4.5. principal, authentication:
 * These expressions allow accessing the principal object representing the current authorized (or anonymous) user and the current Authentication object from the SecurityContext, respectively.
 * We can, for example, use principal to load a user's email, avatar, or any other data that is accessible in the logged in user.
 * And authentication provides information about the full Authentication object, along with its granted authorities.
 * این عبارات امکان دسترسی به شیی principal را دارند که به ترتیب کاربر مجاز فعلی (یا ناشناس) و شیء Authentication فعلی را از SecurityContext به نمایش می گذارد.
 * به عنوان مثال ، می توانیم از principal استفاده کنیم تا ایمیل کاربر ، نماد یا هر داده دیگری را که در ورود به سیستم کاربر در دسترس است بارگیری کنیم.
 * و احراز هویت اطلاعاتی راجع به شیی کامل Authentication ، همراه با دسترسی های مجاز ارائه می کند.
 *
 * 4.6. hasPermission APIs:
 * This expression is documented and intended to bridge between the expression system and Spring Security’s ACL system, allowing us to specify authorization constraints on individual domain objects, based on abstract permissions.
 * Let's have a look at an example.
 * We have a service that allows cooperative writing articles, with a main editor, deciding which article proposed by other authors should be published.
 * In order to allow usage of such a service, we may create following methods with access control methods:
 * این عبارت مستندسازی شده و در نظر گرفته شده است تا بین سیستم expression و سیستم Spring Security’s ACL ارتباط برقرار کند ، به ما این امکان را می دهد تا محدودیت های مجوز را بر روی اشیاء دامنه فردی ، بر اساس مجوزهای انتزاعی مشخص کنیم.
 * بیایید نگاهی به مثال بزنیم.
 * ما خدماتی را ارائه می دهیم که مقالات نوشتن مشارکتی را با یک ویرایشگر اصلی در اختیار شما قرار می دهد تا تصمیم بگیرد کدام مقاله پیشنهاد شده توسط نویسندگان دیگر منتشر شود.
 * به منظور استفاده از چنین سرویس هایی ، می توان با متدهای کنترل دسترسی متدهای زیر را ایجاد کرد:
 *      @PreAuthorize("hasPermission(#articleId, 'isEditor')")
 *      public void acceptArticle(Article article) {
 *         …
 *      }
 * Only authorized user can call this method, and also user needs to have permission isEditor in the service.
 * We also need to remember to explicitly configure a PermissionEvaluator in our application context:
 * فقط کاربر مجاز می تواند با این متد تماس بگیرد و همچنین کاربر باید مجوز isEditor را در سرویس داشته باشد.
 * همچنین لازم است به یاد داشته باشیم که صریحاً یک PermissionEvaluator را در متن برنامه خود پیکربندی کنیم:
 *     @Override
 *      protected MethodSecurityExpressionHandler expressionHandler() {
 *          DefaultMethodSecurityExpressionHandler expressionHandler =
 *            new DefaultMethodSecurityExpressionHandler();
 *          expressionHandler.setPermissionEvaluator(new CustomInterfaceImplementation());
 *          return expressionHandler;
 *      }
 *
 * 4.7. httpBasic:
 * this is enough to enable Basic Authentication for the entire application.
 * httpBasic ():
 * این کافی است تا Basic Authentication را برای کل برنامه فعال کند.
 *
 *
 *
 * 5. Form Login:
 * Interestingly, Spring Security generates a login page automatically, based on the features that are enabled and using standard values for the URL which processes the submitted login:
 *جالب اینجاست که Spring Security بر اساس ویژگی هایی که فعال شده است و با استفاده از مقادیر استاندارد برای URL که ورود به سیستم ارسال شده را پردازش می کند ، به طور خودکار یک صفحه ورود ایجاد می کند:
 * protected void configure(HttpSecurity http) throws Exception {
 *     http.authorizeRequests()
 *       .anyRequest().authenticated()
 *       .and().formLogin()
 *       .loginPage("/login").permitAll();
 * }
 * Here the automatically generated login page is convenient to get up and running quickly
 * در اینجا صفحه ورود به صورت خودکار ایجاد شده راحت است که به سرعت اجرا شوید و کار کنید
 *
 * 6. Authorization With Roles:
 * Let's now configure some simple authorization on each URL using roles:
 * اکنون بیایید مجوزهای ساده را در هر URL با استفاده از نقشها پیکربندی کنیم:
 *      protected void configure(HttpSecurity http) throws Exception {
 *          http.authorizeRequests()
 *            .antMatchers("/", "/home").access("hasRole('USER')")
 *            .antMatchers("/admin/**").hasRole("ADMIN")
 *            .and()
 *            // some more method calls
 *            .formLogin();
 *      }
 *Notice how we're using both the type-safe API – hasRole – but also the expression based API, via access.
 * توجه کنید که چگونه ما از API نوع type-safe API – hasRole – مبتنی بر عبارات ، از طریق دسترسی استفاده می کنیم.
 *
 * 7. Logout:
 * As many other aspects of Spring Security, logout has some great defaults provided by the framework.
 * By default, a logout request invalidates the session, clears any authentication caches, clears the SecurityContextHolder and redirects to login page.
 * Here is a simple logout config:
 * به عنوان بسیاری از جنبه های دیگر امنیت امنیتی ، ورود به سیستم دارای پیش فرض های بسیار خوبی است که توسط این چارچوب ارائه شده است.
 * به طور پیش فرض ، یک درخواست خروج جلسه را باطل می کند ، هرگونه حافظه پنهانی را پاک می کند ، SecurityContextHolder را پاک می کند و برای ورود به صفحه تغییر مسیر می دهد.
 * در اینجا پیکربندی ورود به سیستم ساده است:
 *       protected void configure(HttpSecurity http) throws Exception {
 *           http.logout();
 *       }
 *However, if you want to get more control over the available handlers, here's what a more complete implementation will look like:
 * با این حال ، اگر می خواهید کنترل بیشتری را بر روی handlers موجود داشته باشید ، در اینجا نحوه اجرای کامل تر به نظر می رسد:
 *       protected void configure(HttpSecurity http) throws Exception {
 *            http.logout().logoutUrl("/my/logout")
 *              .logoutSuccessUrl("/my/index")
 *              .logoutSuccessHandler(logoutSuccessHandler)
 *              .invalidateHttpSession(true)
 *              .addLogoutHandler(logoutHandler)
 *              .deleteCookies(cookieNamesToClear)
 *              .and()
 *              // some other method calls
 *        }
 */
@EnableWebSecurity
@ComponentScan(basePackages = {"com.motaharinia"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService()  {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123456789").roles("ADMIN").build());
        return manager;
    }

    protected void configure(HttpSecurity http) throws Exception {
        //with custom login
        http.authorizeRequests().
                antMatchers("/index", "/user","/").permitAll()
                .antMatchers("/admin").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}

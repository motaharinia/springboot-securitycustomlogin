package com.motaharinia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *https://www.javatpoint.com/spring-security-custom-login
 * Spring Security Custom Login:
 * Spring Security provides it's own built-in login module to authenticate the user.
 * It validates the user credentials and provide accessibility into the application.
 * The login page rendered by the module is built-in. So, we does not require to create new jsp page.
 * But if we want to customize the login page then how we can?
 * The answer is, we can create our own jsp login page and integrate to the application.
 * In this topic we will create a custom login page and will use it to get login.
 * ورود به سیستم امنیتی سفارشی Spring:
 * اسپرینگ سکیوریتی برای تأیید اعتبار کاربر ، ماژول ورود به سیستم داخلی خود را فراهم می کند.
 * این اعتبار کاربر را تأیید می کند و قابلیت دسترسی به برنامه را فراهم می کند.
 * صفحه ورود ارائه شده توسط ماژول داخلی است. بنابراین ، ما نیازی به ایجاد صفحه جدید jsp نداریم.
 * اما اگر می خواهیم صفحه ورود را شخصی سازی کنیم ، پس چگونه می توانیم؟
 * پاسخ این است که ما می توانیم صفحه ورود به سیستم jsp خود را ایجاد کرده و در برنامه ادغام شویم.
 * در این مبحث یک صفحه ورود به سیستم ایجاد می کنیم و از آن برای ورود به سیستم استفاده خواهیم کرد.
 *
 * Output:
 * Now, login by providing user credentials.
 * See, it's working fine. Now, we can create it more decorative and custom according to the need.
 * خروجی:
 * اکنون با ارائه اعتبار کاربر وارد شوید.
 * ببینید ، خوب است حال می توانیم با توجه به نیاز ، آنرا تزئینی و سفارشی تر ایجاد کنیم.
 */
@SpringBootApplication(scanBasePackages = {"com.motaharinia"})
public class SecurityCustomLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityCustomLoginApplication.class, args);
    }

}

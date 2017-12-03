package pl.edu.agh.iet.dts.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @author Bartłomiej Grochal
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableOAuth2Sso
public class EdgeServer extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(EdgeServer.class, args);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                    .antMatchers("/", "/app", "/app/components/login", "/app/components/unauthenticated", "/login**",
                                 "/index.html", "/templates/login.html", "/templates/unauthenticated.html",
                                 "/angularMain.js", "/router.js", "/controller/indexController.js", "/controller/loginController.js",
                                 "/webjars/**")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .permitAll()
                .and()
                    .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

}

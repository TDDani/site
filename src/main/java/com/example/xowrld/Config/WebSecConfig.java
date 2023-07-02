package com.example.xowrld.Config;

import com.example.xowrld.Model.ROLE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.security.SecureRandom;


@EnableWebSecurity
@Configuration
public class WebSecConfig extends WebSecurityConfigurerAdapter {
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.requestCache()
                .requestCache(new CustomRequestCache())
                .and().
                formLogin()
                .permitAll()
                .loginPage("/login")
                .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
                .failureUrl("/loginerror")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                .authorizeRequests()
                .antMatchers("/adminhub", "/editbeat/*", "/removebeat/*","/newupcomingevent","/controlevents","/newbeat", "/controlprizes","/newarticle", "/summary", "/sendnewsletter", "/usersummary").hasAuthority(String.valueOf(ROLE.ADMIN))
                .antMatchers("/buyfloaters", "/myaccount", "/sendfloaters", "/*/successfullpurchase1").authenticated()
                .antMatchers("/findbeat", "/contact", "/about", "/view/*", "/sendverification/*","/coverphoto/*", "/photo/*", "/*.css", "/charge", "/*.png", "/images/*", "/*", "/readarticle/*", "/register", "https://www.dropbox.com/*", "/viewbeat/*").permitAll()
                .anyRequest().authenticated();



    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public String tokengenerator(){
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=[]{};:<>,.?/|";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

}

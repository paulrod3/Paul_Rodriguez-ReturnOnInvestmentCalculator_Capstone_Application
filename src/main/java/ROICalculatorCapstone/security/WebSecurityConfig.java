package ROICalculatorCapstone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/registration**",
                        "/login",
                        "/js/**",
                        "/css/**",
                        "/img/**").permitAll()
                .antMatchers(HttpMethod.GET, "/properties/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/properties/new").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/properties/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/financialdetails/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/financialdetails/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/financialdetails/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/renovationexpenses/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/renovationexpenses/property/{address}/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/renovationexpenses/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/renovationexpenses/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/properties", true)
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user@user.com")
                .password("user")
                .roles("USER")
                .and()
                .withUser("admin@admin.com")
                .password("admin")
                .roles("ADMIN");
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

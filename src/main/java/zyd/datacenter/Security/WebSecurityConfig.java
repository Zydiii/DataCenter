package zyd.datacenter.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import zyd.datacenter.Security.jwt.AuthEntryPointJwt;
import zyd.datacenter.Security.jwt.AuthTokenFilter;
import zyd.datacenter.Security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/auth/offline/**").permitAll()
                .antMatchers("/auth/online/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers("/announceboard/manage/**").hasRole("ADMIN")
                .antMatchers("/announceboard/user/**").permitAll()
                .antMatchers("/asset/manage/**").hasRole("ADMIN")
                .antMatchers("/asset/user/**").permitAll()
                .antMatchers("/room/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers("/rank/getRank/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers("/rank/setting/").hasRole("ADMIN")
                .antMatchers("/game/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers("/publicChat/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers("/api/test/**").permitAll()
                .antMatchers("/manage/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

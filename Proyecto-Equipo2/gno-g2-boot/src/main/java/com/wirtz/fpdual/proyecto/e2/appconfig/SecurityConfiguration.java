package com.wirtz.fpdual.proyecto.e2.appconfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .successForwardUrl("/index")
            .and()
            .logout()
            .permitAll()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login");
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder, DataSource dataSource) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT teacher_login, teacher_password, teacher_is_active FROM gnotas_g2_proyectoservidor.gnotas_g2_teacher WHERE teacher_login = ?")
                .authoritiesByUsernameQuery("SELECT teacher_login, teacher_password, teacher_is_active FROM gnotas_g2_proyectoservidor.gnotas_g2_teacher WHERE teacher_login = ?")
                .passwordEncoder(passwordEncoder);
    }

}

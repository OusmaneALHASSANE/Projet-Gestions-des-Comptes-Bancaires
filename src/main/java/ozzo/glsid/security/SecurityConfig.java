package ozzo.glsid.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
@Autowired
private DataSource dataSource;
	@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN","USER");
	auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("USER");
	/*auth.jdbcAuthentication()
	.dataSource(dataSource) // Data source de l’application
	.usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?") // Requête SQL pour chercher si l’Utilisateur existe
	.authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?") // Requête SQL pour connaitre les rôles de l’utilisateurs
	.rolePrefix("ROLE_"); // Préfixe ajouté au Rôle par EX ROLE_ADMIN
	//.passwordEncoder(new Md4PasswordEncoder());*/

}
@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/operations","/consultercompte").hasRole("USER");
		http.authorizeRequests().antMatchers("/Saveoperation").hasRole("ADMIN");
       http.exceptionHandling().accessDeniedPage("/403");
}
}

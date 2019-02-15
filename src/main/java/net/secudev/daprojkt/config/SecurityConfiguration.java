package net.secudev.daprojkt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.secudev.daprojkt.security.AccessDenyHandler;
import net.secudev.daprojkt.security.JPAAuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	JPAAuthenticationProvider jpaAuthenticationProvider;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		//contourne la sécurité mais exécute quand même le filtre...donc la requete sql
		//si on veut eviter çà il faut créer son propre filtre
		//de tt facon il est bien de protéger l'accès aux images
		web.ignoring().antMatchers("files/**");
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(jpaAuthenticationProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//.antMatchers("files/**").anonymous()
		.antMatchers("public/**").permitAll()
		.anyRequest().authenticated().and()
		.csrf().disable()
		.httpBasic().and()
		.formLogin().disable()
		.logout().disable()
		.rememberMe().disable()
		.exceptionHandling().accessDeniedHandler(new AccessDenyHandler()).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}
}

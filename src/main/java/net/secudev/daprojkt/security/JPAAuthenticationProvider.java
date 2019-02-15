package net.secudev.daprojkt.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import net.secudev.daprojkt.model.role.Role;
import net.secudev.daprojkt.model.user.IUserRepository;
import net.secudev.daprojkt.model.user.User;

@Component
public class JPAAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	IUserRepository users;

	@Autowired
	PasswordEncoder passwordEncoder;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// Si le user existe

		// si il n'est pas désactivé

		// si le password est le bon

		// recupération des rôles
		String username = authentication.getName().toString();

		User user = users.findByName(username);

		if (user == null) {
			String msg = "Utilisateur non trouvé : " + username;
			logger.trace(msg);
			throw new UsernameNotFoundException(msg);
		}

		if (!passwordEncoder.matches((CharSequence) authentication.getCredentials(), user.getPasswordH())) {
			String msg = "Mauvais mot de passe pour " + username;
			logger.trace(msg);
			throw new BadCredentialsException(msg);
		}

		List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();

		for (Role role : user.getRoles()) {
			roles.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		}

		logger.trace("Authentification OK : " + user.getName() + " " + roles.toString());

		return new UsernamePasswordAuthenticationToken(user.getName(), null, roles);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}

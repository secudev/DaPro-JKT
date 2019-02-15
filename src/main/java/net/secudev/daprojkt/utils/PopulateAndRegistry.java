package net.secudev.daprojkt.utils;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import net.secudev.daprojkt.model.role.IRoleRepository;
import net.secudev.daprojkt.model.role.Role;
import net.secudev.daprojkt.model.user.IUserRepository;
import net.secudev.daprojkt.model.user.User;

@Component
public class PopulateAndRegistry {

	@Autowired
	PasswordEncoder passwordEncoder;

	
	@Autowired
	public IRoleRepository roles;

	@Autowired
	public IUserRepository users;

	public void cleanAll()
	{
		users.deleteAll();
		roles.deleteAll();
	}
	
	public void CreateUserAndRoles() {
		Role admin = new Role("admin");
		roles.save(admin);
		Role regular = new Role("regular");
		roles.save(regular);
		Role vip = new Role("vip");
		roles.save(vip);

		User adminUser = new User("admin", passwordEncoder.encode("password"), "admin@simplon.co", Arrays.asList(admin));
		users.save(adminUser);
		User regularUser = new User("regular", passwordEncoder.encode("password"), "regular@simplon.co", Arrays.asList(regular));
		users.save(regularUser);
		User vipUser = new User("vip", passwordEncoder.encode("password"), "vip@simplon.co", Arrays.asList(regular, vip));
		users.save(vipUser);
	}

}

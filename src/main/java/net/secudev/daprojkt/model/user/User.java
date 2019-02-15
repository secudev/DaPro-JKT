package net.secudev.daprojkt.model.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.secudev.daprojkt.model.common.AbstractEntity;
import net.secudev.daprojkt.model.role.Role;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AbstractEntity {

	@Getter
	@Setter
	@Column(unique = true)
	@NotBlank
	@NotNull
	private String name;

	@Getter
	@Setter
	@NotBlank
	@NotNull
	private String passwordH;
	
	@Getter
	@Setter
	private boolean enabled;
	
	@Getter
	@Setter	
	private String validationCode;

	@Getter
	@Setter
	@Column(unique = true)
	@Email
	private String email;

	@ManyToMany
	private Set<Role> roles = new HashSet<Role>();

	public User(String name, String passwordH, String email) {
		this.name = name;
		this.passwordH = passwordH;
		this.email = email;
	}

	public User(String name, String passwordH, String email, List<Role> roles) {
		this.name = name;
		this.passwordH = passwordH;
		this.email = email;
		
		for(Role role : roles)
		{
			this.roles.add(role);
		}
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public void removeRole(Role role) {
		this.roles.remove(role);
	}

	public List<Role> getRoles() {
		return new ArrayList<Role>(roles);
	}
}

package net.secudev.daprojkt.model.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.secudev.daprojkt.model.common.AbstractEntity;

@Entity
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Role extends AbstractEntity {

	@Getter
	@Setter
	@Column(unique = true)
	@NotBlank
	@NotNull
	private String name;

	public Role(String name) {
		this.name = name;
	}
}

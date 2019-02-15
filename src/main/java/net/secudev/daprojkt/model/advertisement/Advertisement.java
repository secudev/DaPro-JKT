package net.secudev.daprojkt.model.advertisement;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.secudev.daprojkt.model.common.AbstractEntity;
import net.secudev.daprojkt.model.user.User;

@Entity
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Advertisement extends AbstractEntity {

	@ManyToOne
	@Getter
	private User creator;
	
	@Getter
	@Setter
	private String content;
	
	public Advertisement(User creator, String content)
	{
		this.creator=creator;
		this.content=content;
	}
}

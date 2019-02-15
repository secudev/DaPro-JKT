package net.secudev.daprojkt.model.common;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class AbstractEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter
	@Setter(AccessLevel.PROTECTED)
	private long id;
	
}

package com.tamnc.entity;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseEntity<ID> {
	private ID id;
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		BaseEntity<?> that = (BaseEntity<?>) obj;
		return id.equals(that.getId());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}

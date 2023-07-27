package com.tamnc.objects;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseId<T> {
	private final T value;
	
	protected BaseId(T id) {
		this.value = id;
	}
}

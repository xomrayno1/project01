package com.tamnc.domain.objects;

import java.util.UUID;

import com.tamnc.objects.BaseId;

public class TrackingId extends BaseId<UUID>{

	public TrackingId(UUID id) {
		super(id);
	}

}

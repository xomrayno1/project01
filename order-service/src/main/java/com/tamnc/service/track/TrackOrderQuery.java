package com.tamnc.service.track;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TrackOrderQuery {
	private final UUID orderTrackingId;

}

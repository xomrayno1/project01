package com.tamnc.domain.ports.input.service;

import org.springframework.stereotype.Component;

import com.tamnc.service.track.TrackOrderQuery;
import com.tamnc.service.track.TrackOrderResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderTrackCommandHandler {

	public TrackOrderResponse trackOrderResponse(TrackOrderQuery trackOrderQuery) {
		return null;
	}
	
}

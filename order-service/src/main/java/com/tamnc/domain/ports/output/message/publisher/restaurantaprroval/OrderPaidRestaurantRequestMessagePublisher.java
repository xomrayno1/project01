package com.tamnc.domain.ports.output.message.publisher.restaurantaprroval;

import com.tamnc.domain.event.OrderPaintEvent;
import com.tamnc.event.publisher.DomainEventPublisher;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaintEvent>{

}

package com.app.model.outbox;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.app.enums.OrderStatus;
import com.app.enums.OutboxStatus;
import com.app.enums.SagaStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@Setter
public class OrderApprovalOutboxMessage {
	private UUID id;
    private UUID sagaId;
    private ZonedDateTime createdAt;
    private ZonedDateTime processedAt;
    private String type;
    private String payload;
    private SagaStatus sagaStatus;
    private OrderStatus orderStatus;
    private OutboxStatus outboxStatus;
    private int version;
}

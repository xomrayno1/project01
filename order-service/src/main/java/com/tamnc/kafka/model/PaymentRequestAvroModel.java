package com.tamnc.kafka.model;

import org.apache.avro.specific.AvroGenerated;

@AvroGenerated
public class PaymentRequestAvroModel extends org.apache.avro.specific.SpecificRecordBase
		implements org.apache.avro.specific.SpecificRecord {

	private java.lang.String id;
	private java.lang.String sagaId;
	private java.lang.String customerId;
	private java.lang.String orderId;
	private java.math.BigDecimal price;
	private java.time.Instant createdAt;
	private PaymentOrderStatus paymentOrderStatus;

	public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse(
			"{\"type\":\"record\",\"name\":\"PaymentRequestAvroModel\",\"namespace\":\"com.food.ordering.system.kafka.order.avro.model\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"sagaId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"customerId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"orderId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"price\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":10,\"scale\":2}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}},{\"name\":\"paymentOrderStatus\",\"type\":{\"type\":\"enum\",\"name\":\"PaymentOrderStatus\",\"symbols\":[\"PENDING\",\"CANCELLED\"]}}]}");

	/**
	 * Default constructor. Note that this does not initialize fields to their
	 * default values from the schema. If that is desired then one should use
	 * <code>newBuilder()</code>.
	 */
	public PaymentRequestAvroModel() {
	}

	/**
	 * All-args constructor.
	 * 
	 * @param id                 The new value for id
	 * @param sagaId             The new value for sagaId
	 * @param customerId         The new value for customerId
	 * @param orderId            The new value for orderId
	 * @param price              The new value for price
	 * @param createdAt          The new value for createdAt
	 * @param paymentOrderStatus The new value for paymentOrderStatus
	 */
	public PaymentRequestAvroModel(java.lang.String id, java.lang.String sagaId, java.lang.String customerId,
			java.lang.String orderId, java.math.BigDecimal price, java.time.Instant createdAt,
			PaymentOrderStatus paymentOrderStatus) {
		this.id = id;
		this.sagaId = sagaId;
		this.customerId = customerId;
		this.orderId = orderId;
		this.price = price;
		this.createdAt = createdAt.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
		this.paymentOrderStatus = paymentOrderStatus;
	}

	@Override
	public org.apache.avro.Schema getSchema() {
		return SCHEMA$;
	}

	@Override
	public java.lang.Object get(int field$) {
		switch (field$) {
		case 0:
			return id;
		case 1:
			return sagaId;
		case 2:
			return customerId;
		case 3:
			return orderId;
		case 4:
			return price;
		case 5:
			return createdAt;
		case 6:
			return paymentOrderStatus;
		default:
			throw new IndexOutOfBoundsException("Invalid index: " + field$);
		}
	}

	// Used by DatumReader. Applications should not call.
	@Override
	@SuppressWarnings(value = "unchecked")
	public void put(int field$, java.lang.Object value$) {
		switch (field$) {
		case 0:
			id = value$ != null ? value$.toString() : null;
			break;
		case 1:
			sagaId = value$ != null ? value$.toString() : null;
			break;
		case 2:
			customerId = value$ != null ? value$.toString() : null;
			break;
		case 3:
			orderId = value$ != null ? value$.toString() : null;
			break;
		case 4:
			price = (java.math.BigDecimal) value$;
			break;
		case 5:
			createdAt = (java.time.Instant) value$;
			break;
		case 6:
			paymentOrderStatus = (PaymentOrderStatus) value$;
			break;
		default:
			throw new IndexOutOfBoundsException("Invalid index: " + field$);
		}
	}

	public static org.apache.avro.Schema getClassSchema() {
		return SCHEMA$;
	}

}

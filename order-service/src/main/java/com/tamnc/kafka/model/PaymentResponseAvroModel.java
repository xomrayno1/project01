package com.tamnc.kafka.model;

import org.apache.avro.specific.AvroGenerated;
import org.apache.avro.specific.SpecificData;

import com.tamnc.objects.PaymentStatus;

@AvroGenerated
public class PaymentResponseAvroModel extends org.apache.avro.specific.SpecificRecordBase
		implements org.apache.avro.specific.SpecificRecord {
	private java.lang.String id;
	private java.lang.String sagaId;
	private java.lang.String paymentId;
	private java.lang.String customerId;
	private java.lang.String orderId;
	private java.math.BigDecimal price;
	private java.time.Instant createdAt;
	private PaymentStatus paymentStatus;
	private java.util.List<java.lang.String> failureMessages;

	public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse(
			"{\"type\":\"record\",\"name\":\"PaymentResponseAvroModel\",\"namespace\":\"com.food.ordering.system.kafka.order.avro.model\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"sagaId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"paymentId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"customerId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"orderId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"price\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":10,\"scale\":2}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}},{\"name\":\"paymentStatus\",\"type\":{\"type\":\"enum\",\"name\":\"PaymentStatus\",\"symbols\":[\"COMPLETED\",\"CANCELLED\",\"FAILED\"]}},{\"name\":\"failureMessages\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}}]}");

	public static org.apache.avro.Schema getClassSchema() {
		return SCHEMA$;
	}

	private static final SpecificData MODEL$ = new SpecificData();

	/**
	 * Default constructor. Note that this does not initialize fields to their
	 * default values from the schema. If that is desired then one should use
	 * <code>newBuilder()</code>.
	 */
	public PaymentResponseAvroModel() {
	}

	/**
	 * All-args constructor.
	 * 
	 * @param id              The new value for id
	 * @param sagaId          The new value for sagaId
	 * @param paymentId       The new value for paymentId
	 * @param customerId      The new value for customerId
	 * @param orderId         The new value for orderId
	 * @param price           The new value for price
	 * @param createdAt       The new value for createdAt
	 * @param paymentStatus   The new value for paymentStatus
	 * @param failureMessages The new value for failureMessages
	 */
	public PaymentResponseAvroModel(java.lang.String id, java.lang.String sagaId, java.lang.String paymentId,
			java.lang.String customerId, java.lang.String orderId, java.math.BigDecimal price,
			java.time.Instant createdAt, PaymentStatus paymentStatus,
			java.util.List<java.lang.String> failureMessages) {
		this.id = id;
		this.sagaId = sagaId;
		this.paymentId = paymentId;
		this.customerId = customerId;
		this.orderId = orderId;
		this.price = price;
		this.createdAt = createdAt.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
		this.paymentStatus = paymentStatus;
		this.failureMessages = failureMessages;
	}

	@Override
	public org.apache.avro.Schema getSchema() {
		return SCHEMA$;
	}

	// Used by DatumWriter. Applications should not call.
	@Override
	public java.lang.Object get(int field$) {
		switch (field$) {
		case 0:
			return id;
		case 1:
			return sagaId;
		case 2:
			return paymentId;
		case 3:
			return customerId;
		case 4:
			return orderId;
		case 5:
			return price;
		case 6:
			return createdAt;
		case 7:
			return paymentStatus;
		case 8:
			return failureMessages;
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
			paymentId = value$ != null ? value$.toString() : null;
			break;
		case 3:
			customerId = value$ != null ? value$.toString() : null;
			break;
		case 4:
			orderId = value$ != null ? value$.toString() : null;
			break;
		case 5:
			price = (java.math.BigDecimal) value$;
			break;
		case 6:
			createdAt = (java.time.Instant) value$;
			break;
		case 7:
			paymentStatus = (PaymentStatus) value$;
			break;
		case 8:
			failureMessages = (java.util.List<java.lang.String>) value$;
			break;
		default:
			throw new IndexOutOfBoundsException("Invalid index: " + field$);
		}
	}

}

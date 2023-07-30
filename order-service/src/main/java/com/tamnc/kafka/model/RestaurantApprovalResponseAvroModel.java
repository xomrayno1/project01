package com.tamnc.kafka.model;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.AvroGenerated;
import org.apache.avro.specific.SpecificData;

@AvroGenerated
public class RestaurantApprovalResponseAvroModel extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord{
	 private static final long serialVersionUID = -3431989201238018220L;


	  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"RestaurantApprovalResponseAvroModel\",\"namespace\":\"com.food.ordering.system.kafka.order.avro.model\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"sagaId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"restaurantId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"orderId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}},{\"name\":\"orderApprovalStatus\",\"type\":{\"type\":\"enum\",\"name\":\"OrderApprovalStatus\",\"symbols\":[\"APPROVED\",\"REJECTED\"]}},{\"name\":\"failureMessages\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}}]}");
	  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

	  private static final SpecificData MODEL$ = new SpecificData();
	 

	  private static final BinaryMessageEncoder<RestaurantApprovalResponseAvroModel> ENCODER =
	      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

	  private static final BinaryMessageDecoder<RestaurantApprovalResponseAvroModel> DECODER =
	      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

	  /**
	   * Return the BinaryMessageEncoder instance used by this class.
	   * @return the message encoder used by this class
	   */
	  public static BinaryMessageEncoder<RestaurantApprovalResponseAvroModel> getEncoder() {
	    return ENCODER;
	  }

	  /**
	   * Return the BinaryMessageDecoder instance used by this class.
	   * @return the message decoder used by this class
	   */
	  public static BinaryMessageDecoder<RestaurantApprovalResponseAvroModel> getDecoder() {
	    return DECODER;
	  }

	  /**
	   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
	   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
	   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
	   */
	  public static BinaryMessageDecoder<RestaurantApprovalResponseAvroModel> createDecoder(SchemaStore resolver) {
	    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
	  }

	  /**
	   * Serializes this RestaurantApprovalResponseAvroModel to a ByteBuffer.
	   * @return a buffer holding the serialized data for this instance
	   * @throws java.io.IOException if this instance could not be serialized
	   */
	  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
	    return ENCODER.encode(this);
	  }
	  

	  private java.lang.String id;
	  private java.lang.String sagaId;
	  private java.lang.String restaurantId;
	  private java.lang.String orderId;
	  private java.time.Instant createdAt;
	  private OrderApprovalStatus orderApprovalStatus;
	  private java.util.List<java.lang.String> failureMessages;

	  /**
	   * Default constructor.  Note that this does not initialize fields
	   * to their default values from the schema.  If that is desired then
	   * one should use <code>newBuilder()</code>.
	   */
	  public RestaurantApprovalResponseAvroModel() {}
	  
	  /**
	   * All-args constructor.
	   * @param id The new value for id
	   * @param sagaId The new value for sagaId
	   * @param restaurantId The new value for restaurantId
	   * @param orderId The new value for orderId
	   * @param createdAt The new value for createdAt
	   * @param orderApprovalStatus The new value for orderApprovalStatus
	   * @param failureMessages The new value for failureMessages
	   */
	  public RestaurantApprovalResponseAvroModel(java.lang.String id, java.lang.String sagaId, java.lang.String restaurantId, java.lang.String orderId, java.time.Instant createdAt, OrderApprovalStatus orderApprovalStatus, java.util.List<java.lang.String> failureMessages) {
	    this.id = id;
	    this.sagaId = sagaId;
	    this.restaurantId = restaurantId;
	    this.orderId = orderId;
	    this.createdAt = createdAt.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
	    this.orderApprovalStatus = orderApprovalStatus;
	    this.failureMessages = failureMessages;
	  }
 
	  @Override
	  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

	  // Used by DatumWriter.  Applications should not call.
	  @Override
	  public java.lang.Object get(int field$) {
	    switch (field$) {
	    case 0: return id;
	    case 1: return sagaId;
	    case 2: return restaurantId;
	    case 3: return orderId;
	    case 4: return createdAt;
	    case 5: return orderApprovalStatus;
	    case 6: return failureMessages;
	    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
	    }
	  }

	  // Used by DatumReader.  Applications should not call.
	  @Override
	  @SuppressWarnings(value="unchecked")
	  public void put(int field$, java.lang.Object value$) {
	    switch (field$) {
	    case 0: id = value$ != null ? value$.toString() : null; break;
	    case 1: sagaId = value$ != null ? value$.toString() : null; break;
	    case 2: restaurantId = value$ != null ? value$.toString() : null; break;
	    case 3: orderId = value$ != null ? value$.toString() : null; break;
	    case 4: createdAt = (java.time.Instant)value$; break;
	    case 5: orderApprovalStatus = (OrderApprovalStatus)value$; break;
	    case 6: failureMessages = (java.util.List<java.lang.String>)value$; break;
	    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
	    }
	  }


}

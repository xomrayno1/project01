package com.tamnc.kafka.model;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.AvroGenerated;
import org.apache.avro.specific.SpecificData;

@AvroGenerated
public class CustomerAvroModel  extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord{
	private static final long serialVersionUID = -861064569672548277L;


	  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"CustomerAvroModel\",\"namespace\":\"com.food.ordering.system.kafka.order.avro.model\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"username\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"firstName\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"lastName\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
	  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

	  private static final SpecificData MODEL$ = new SpecificData();

	  private static final BinaryMessageEncoder<CustomerAvroModel> ENCODER =
	      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

	  private static final BinaryMessageDecoder<CustomerAvroModel> DECODER =
	      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

	  /**
	   * Return the BinaryMessageEncoder instance used by this class.
	   * @return the message encoder used by this class
	   */
	  public static BinaryMessageEncoder<CustomerAvroModel> getEncoder() {
	    return ENCODER;
	  }

	  /**
	   * Return the BinaryMessageDecoder instance used by this class.
	   * @return the message decoder used by this class
	   */
	  public static BinaryMessageDecoder<CustomerAvroModel> getDecoder() {
	    return DECODER;
	  }

	  /**
	   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
	   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
	   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
	   */
	  public static BinaryMessageDecoder<CustomerAvroModel> createDecoder(SchemaStore resolver) {
	    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
	  }

	  /**
	   * Serializes this CustomerAvroModel to a ByteBuffer.
	   * @return a buffer holding the serialized data for this instance
	   * @throws java.io.IOException if this instance could not be serialized
	   */
	  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
	    return ENCODER.encode(this);
	  }

	  private java.lang.String id;
	  private java.lang.String username;
	  private java.lang.String firstName;
	  private java.lang.String lastName;

	  /**
	   * Default constructor.  Note that this does not initialize fields
	   * to their default values from the schema.  If that is desired then
	   * one should use <code>newBuilder()</code>.
	   */
	  public CustomerAvroModel() {}

	  /**
	   * All-args constructor.
	   * @param id The new value for id
	   * @param username The new value for username
	   * @param firstName The new value for firstName
	   * @param lastName The new value for lastName
	   */
	  public CustomerAvroModel(java.lang.String id, java.lang.String username, java.lang.String firstName, java.lang.String lastName) {
	    this.id = id;
	    this.username = username;
	    this.firstName = firstName;
	    this.lastName = lastName;
	  }
	  
	  @Override
	  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

	  // Used by DatumWriter.  Applications should not call.
	  @Override
	  public java.lang.Object get(int field$) {
	    switch (field$) {
	    case 0: return id;
	    case 1: return username;
	    case 2: return firstName;
	    case 3: return lastName;
	    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
	    }
	  }

	  // Used by DatumReader.  Applications should not call.
	  @Override
	  @SuppressWarnings(value="unchecked")
	  public void put(int field$, java.lang.Object value$) {
	    switch (field$) {
	    case 0: id = value$ != null ? value$.toString() : null; break;
	    case 1: username = value$ != null ? value$.toString() : null; break;
	    case 2: firstName = value$ != null ? value$.toString() : null; break;
	    case 3: lastName = value$ != null ? value$.toString() : null; break;
	    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
	    }
	  }
}

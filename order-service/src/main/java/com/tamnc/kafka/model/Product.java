package com.tamnc.kafka.model;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.AvroGenerated;
import org.apache.avro.specific.SpecificData;

@AvroGenerated
public class Product extends org.apache.avro.specific.SpecificRecordBase
		implements org.apache.avro.specific.SpecificRecord {

	private static final long serialVersionUID = -6511580554663840009L;

	public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse(
			"{\"type\":\"record\",\"name\":\"Product\",\"namespace\":\"com.food.ordering.system.kafka.order.avro.model\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"logicalType\":\"uuid\"},{\"name\":\"quantity\",\"type\":\"int\"}]}");

	public static org.apache.avro.Schema getClassSchema() {
		return SCHEMA$;
	}

	private static final SpecificData MODEL$ = new SpecificData();

	private static final BinaryMessageEncoder<Product> ENCODER = new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

	private static final BinaryMessageDecoder<Product> DECODER = new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

	/**
	 * Return the BinaryMessageEncoder instance used by this class.
	 * 
	 * @return the message encoder used by this class
	 */
	public static BinaryMessageEncoder<Product> getEncoder() {
		return ENCODER;
	}

	/**
	 * Return the BinaryMessageDecoder instance used by this class.
	 * 
	 * @return the message decoder used by this class
	 */
	public static BinaryMessageDecoder<Product> getDecoder() {
		return DECODER;
	}

	/**
	 * Create a new BinaryMessageDecoder instance for this class that uses the
	 * specified {@link SchemaStore}.
	 * 
	 * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
	 * @return a BinaryMessageDecoder instance for this class backed by the given
	 *         SchemaStore
	 */
	public static BinaryMessageDecoder<Product> createDecoder(SchemaStore resolver) {
		return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
	}

	/**
	 * Serializes this Product to a ByteBuffer.
	 * 
	 * @return a buffer holding the serialized data for this instance
	 * @throws java.io.IOException if this instance could not be serialized
	 */
	public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
		return ENCODER.encode(this);
	}

	/**
	 * Deserializes a Product from a ByteBuffer.
	 * 
	 * @param b a byte buffer holding serialized data for an instance of this class
	 * @return a Product instance decoded from the given buffer
	 * @throws java.io.IOException if the given bytes could not be deserialized into
	 *                             an instance of this class
	 */
	public static Product fromByteBuffer(java.nio.ByteBuffer b) throws java.io.IOException {
		return DECODER.decode(b);
	}

	private java.lang.String id;
	private int quantity;

	/**
	 * Default constructor. Note that this does not initialize fields to their
	 * default values from the schema. If that is desired then one should use
	 * <code>newBuilder()</code>.
	 */
	public Product() {
	}

	/**
	 * All-args constructor.
	 * 
	 * @param id       The new value for id
	 * @param quantity The new value for quantity
	 */
	public Product(java.lang.String id, java.lang.Integer quantity) {
		this.id = id;
		this.quantity = quantity;
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
			return quantity;
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
			quantity = (java.lang.Integer) value$;
			break;
		default:
			throw new IndexOutOfBoundsException("Invalid index: " + field$);
		}
	}

	/**
	 * Gets the value of the 'id' field.
	 * 
	 * @return The value of the 'id' field.
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * Sets the value of the 'id' field.
	 * 
	 * @param value the value to set.
	 */
	public void setId(java.lang.String value) {
		this.id = value;
	}

	/**
	 * Gets the value of the 'quantity' field.
	 * 
	 * @return The value of the 'quantity' field.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the value of the 'quantity' field.
	 * 
	 * @param value the value to set.
	 */
	public void setQuantity(int value) {
		this.quantity = value;
	}
 
}

package com.tamnc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import com.tamnc.domain.entity.Customer;
import com.tamnc.domain.entity.Order;
import com.tamnc.domain.entity.Product;
import com.tamnc.domain.entity.Restaurant;
import com.tamnc.domain.exception.OrderDomainException;
import com.tamnc.domain.mapper.OrderDataMapper;
import com.tamnc.domain.objects.TrackingId;
import com.tamnc.domain.ports.input.service.OrderApplicationService;
import com.tamnc.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.tamnc.domain.ports.output.repository.CustomerRepository;
import com.tamnc.domain.ports.output.repository.OrderRepository;
import com.tamnc.domain.ports.output.repository.RestaurantRepository;
import com.tamnc.domain.service.OrderDomainService;
import com.tamnc.objects.CustomerId;
import com.tamnc.objects.Money;
import com.tamnc.objects.OrderId;
import com.tamnc.objects.OrderStatus;
import com.tamnc.objects.ProductId;
import com.tamnc.objects.RestaurantId;
import com.tamnc.service.create.CreateOrderCommand;
import com.tamnc.service.create.CreateOrderResponse;
import com.tamnc.service.create.OrderAddress;
import com.tamnc.service.create.OrderItemDTO;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(classes = OrderTestConfiguration.class)
public class OrderApplicationServiceTest {

	@Autowired
	private OrderDomainService orderDomainService;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private OrderDataMapper orderDataMapper;

	@Autowired
	private OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessage;

	@Autowired
	private OrderApplicationService orderApplicationService;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
 
	private CreateOrderCommand createOrderCommand;
	private CreateOrderCommand createOrderCommandWrongPrice;
	private CreateOrderCommand createOrderCommandWrongProductPrice;
	
	private final UUID CUSTOMER_ID = UUID.fromString("9620d30b-bdbf-407a-b2c0-b78c229c3e4f");
	private final UUID RESTAURANT_ID = UUID.fromString("c69cf2a7-e0d5-4236-a521-db58b5cece9d");
	private final UUID PRODUCT_ID = UUID.fromString("8d8bf045-dba8-4b2f-a84d-dacc24b239f7");
	private final UUID ORDER_ID = UUID.fromString("22ef8a64-640d-48eb-bc4a-5e2f0804b375");
	
	private final UUID TRACKING_ID = UUID.fromString("22ef8a64-640d-48eb-bc4a-5e2f0804b375");
	
	private final BigDecimal PRICE = new BigDecimal("200.00");
	
	@BeforeAll
	public void init() {
		createOrderCommand = CreateOrderCommand.builder()
					.customerId(CUSTOMER_ID)
					.restaurantId(RESTAURANT_ID)
					.address(OrderAddress.builder()
							.street("street_1")
							.postalCode("1000AB")
							.city("Paris")
							.build())
					.price(PRICE)
					.items(List.of(OrderItemDTO.builder()
							.productId(PRODUCT_ID)
							.quantity(1)
							.price(new BigDecimal("50.00"))
							.subTotal(new BigDecimal("50.00"))
							.build(), 
							OrderItemDTO.builder()
							.productId(PRODUCT_ID)
							.quantity(3)
							.price(new BigDecimal("50.00"))
							.subTotal(new BigDecimal("150.00"))
							.build()
						))
				.build();
		
		createOrderCommandWrongPrice = CreateOrderCommand.builder()
				.customerId(CUSTOMER_ID)
				.restaurantId(RESTAURANT_ID)
				.address(OrderAddress.builder()
						.street("street_1")
						.postalCode("1000AB")
						.city("Paris")
						.build())
				.price(new BigDecimal("250.00"))
				.items(List.of(OrderItemDTO.builder()
						.productId(PRODUCT_ID)
						.quantity(1)
						.price(new BigDecimal("50.00"))
						.subTotal(new BigDecimal("50.00"))
						.build(), 
					OrderItemDTO.builder()
						.productId(PRODUCT_ID)
						.quantity(3)
						.price(new BigDecimal("50.00"))
						.subTotal(new BigDecimal("150.00"))
						.build()
					))
			.build();
		
		createOrderCommandWrongProductPrice = CreateOrderCommand.builder()
				.customerId(CUSTOMER_ID)
				.restaurantId(RESTAURANT_ID)
				.address(OrderAddress.builder()
						.street("street_1")
						.postalCode("1000AB")
						.city("Paris")
						.build())
				.price(new BigDecimal("210.00"))
				.items(List.of(OrderItemDTO.builder()
						.productId(PRODUCT_ID)
						.quantity(1)
						.price(new BigDecimal("60.00"))
						.subTotal(new BigDecimal("60.00"))
						.build(), 
						OrderItemDTO.builder()
						.productId(PRODUCT_ID)
						.quantity(3)
						.price(new BigDecimal("50.00"))
						.subTotal(new BigDecimal("150.00"))
						.build()
					))
			.build();
		
		Customer customer = new Customer();
		customer.setId(new CustomerId(CUSTOMER_ID));
		
		Restaurant restaurantResponse = Restaurant.builder()
//				.restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
				.products(List.of(
						new Product(new ProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00"))),
						new Product(new ProductId(PRODUCT_ID), "product-2", new Money(new BigDecimal("50.00")))
						))
				.active(true)
				.build();
		restaurantResponse.setId(new RestaurantId(createOrderCommand.getRestaurantId()));
		
		Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
		order.setId(new OrderId(ORDER_ID));
		order.setTrackingId(new TrackingId(TRACKING_ID));
		order.setOrderStatus(OrderStatus.PENDING);
		 
		
		when(customerRepository.findCustomer(CUSTOMER_ID)).thenReturn(Optional.of(customer));
		Restaurant restaurant =	orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
		when(restaurantRepository.findRestaurantInfomation(any())).thenReturn(Optional.of(restaurantResponse));
		when(orderRepository.save(any(Order.class))).thenReturn(order);
		
	}
		
	@Test
	public void testCreateOrder() {
		CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
		assertEquals(createOrderResponse.getOrderStatus(), OrderStatus.PENDING);
		assertEquals(createOrderResponse.getMessage(), "Order created successfully");
		assertNotNull(createOrderResponse.getOrderTrackingId());
	}
	
	@Test
	public void testCreateOrderWithWrongTotalPrice() {
		OrderDomainException orderDomainException = assertThrows(OrderDomainException.class, () -> 
				orderApplicationService.createOrder(createOrderCommandWrongPrice)
		);
	}
	 
}

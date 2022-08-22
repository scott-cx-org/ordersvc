package com.example.ordersvc;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/orders"}, produces = APPLICATION_JSON_VALUE)
public class OrderController {

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderRepository orderRepository;

//    @GetMapping()
//    public String index() {
//        return "Search for an order";
//    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Find an Order by its Id")
    public Order getOrderById(@PathVariable String id) {
        logger.debug("Found id {}", id);
        Order theOrder = orderRepository.findById(id);
        return theOrder;
    }
    @GetMapping(path = "/cust/{customerId}")
    @Operation(summary = "Find all orders for a Customer by CustomerId")
    public List<Order> getOrderByCustomerId(@PathVariable Long customerId) {
        List<Order> theOrders = orderRepository.findByCustomerId(customerId);
        logger.debug("Found {} orders for customer {}", theOrders.size(), customerId);
        return theOrders;
    }
    @PutMapping(path="/{id}")
    @Operation(summary = "Update an order")
    public Order updateOrderById(@RequestBody OrderDto orderDto) {
        logger.debug(orderDto.toString());
        Order theOrder = orderRepository.findById(orderDto.getId());
        theOrder.updateOrder(orderDto);
        logger.debug(theOrder.toString());
        orderRepository.save(theOrder);
        return theOrder;
    }


    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @Operation(summary="Create an order with a new OrderId")
    public Order postOrder(@RequestBody OrderDto orderDto) {
        Order theOrder = new Order();
        theOrder.createOrder(orderDto);
        orderRepository.save(theOrder);
        return theOrder;
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Delete an order if before shipment.")
    @Hidden
    public Order deleteOrder(@PathVariable String id) {
        logger.debug("Deleting order id {}" , id);
        Order theOrder = orderRepository.findById(id);
        theOrder.deleteOrder(id);
        logger.debug(theOrder.toString());
        orderRepository.save(theOrder);
        return theOrder;
    }

    @GetMapping(path="/search")
    @Operation(summary = "Search for an order")
    public List<Order> searchOrders(@RequestParam(name="qry") String searchStr) {
        List<Order> theOrders = orderRepository.search(searchStr);
        return theOrders;
    }
}
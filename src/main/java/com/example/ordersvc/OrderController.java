package com.example.ordersvc;

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

    @GetMapping()
    public String index() {
        return "Search for an order";
    }

    @GetMapping(path = "/{id}")
    public Order getOrderById(@PathVariable String id) {
        logger.debug("Found id {}", id);
        Order theOrder = orderRepository.findById(id);
        return theOrder;
    }
    @GetMapping(path = "/cust/{customerId}")
    public List<Order> getOrderByCustomerId(@PathVariable Long customerId) {
        List<Order> theOrders = orderRepository.findByCustomerId(customerId);
        logger.debug("Found {} orders for customer {}", theOrders.size(), customerId);
        return theOrders;
    }
    @PutMapping(path="/{id}")
    public Order updateOrderById(@RequestBody OrderDto orderDto) {
        logger.debug(orderDto.toString());
        Order theOrder = orderRepository.findById(orderDto.getId());
        theOrder.updateOrder(orderDto);
        logger.debug(theOrder.toString());
        orderRepository.save(theOrder);
        return theOrder;
    }


    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Order postOrder(@RequestBody OrderDto orderDto) {
        Order theOrder = new Order();
        theOrder.createOrder(orderDto);
        orderRepository.save(theOrder);
        return theOrder;
    }

    @GetMapping(path="/search")
    public List<Order> searchOrders(@RequestParam(name="qry") String searchStr) {
        List<Order> theOrders = orderRepository.search(searchStr);
        return theOrders;
    }
}
package com.example.ordersvc;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OrderRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Order order) {
        em.persist(order);
    }

    public Order findById(String id) {
        return em.find(Order.class, id);
    }
    public List<Order> findByCustomerId(Long customerId) {
        TypedQuery<Order> query = em.createQuery("Select o from Order o where o.customerId = " + customerId, Order.class);
        return query.getResultList();
    }

    public List<Order> search(String params) {
        String nativeSql = "select * from orders o where id like '%" + params +"%'";
        System.out.println("NativeSql: " + nativeSql);
        Query query = em.createNativeQuery(nativeSql, Order.class);
        return query.getResultList();
    }

}

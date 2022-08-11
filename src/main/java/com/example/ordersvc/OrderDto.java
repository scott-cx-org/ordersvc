package com.example.ordersvc;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class OrderDto {
    private String id;
    private Long customerId;
    private Long ccardId;
    private Date createdDate;
    private BigDecimal orderTotal;
    private String orderStatus;
    private  Date updatedDate;

    public OrderDto() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id;}

    public Long getCustomerId() { return customerId; }
    public void setCustomerId() {this.customerId = customerId; }

    public Long getCcardId() { return ccardId;}
    public void setCcardId(Long ccardId) { this.ccardId = ccardId; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    public BigDecimal getOrderTotal() { return orderTotal; }
    public void setOrderTotal(BigDecimal ordertotal) { this.orderTotal = ordertotal; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    public Date getUpdatedDate() { return updatedDate; }
    public void setUpdatedDate(Date updatedDate) { this.updatedDate = updatedDate; }
}

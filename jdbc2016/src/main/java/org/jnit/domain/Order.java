package org.jnit.domain;
public class Order {
	private int orderNo;
	private String item;
	private OrderStatus status;
	//Many to one
	private Customer customer;

	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", item=" + item + ", status="
				+ status + "]";
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
}


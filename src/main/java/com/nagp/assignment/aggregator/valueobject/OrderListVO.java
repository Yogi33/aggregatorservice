package com.nagp.assignment.aggregator.valueobject;

import java.util.List;

public class OrderListVO {

	private List<OrderDetails> orders;

	public List<OrderDetails> getOrders() {
		return orders;
	}

	public OrderListVO setOrders(List<OrderDetails> orders) {
		this.orders = orders;
		return this;
	}

	public static class OrderDetails {

		private int orderId;
		private double amount;
		private String date;

		public int getOrderId() {
			return orderId;
		}

		public OrderDetails setOrderId(int orderId) {
			this.orderId = orderId;
			return this;
		}

		public double getAmount() {
			return amount;
		}

		public OrderDetails setAmount(double amount) {
			this.amount = amount;
			return this;

		}

		public String getDate() {
			return date;
		}

		public OrderDetails setDate(String date) {
			this.date = date;
			return this;

		}

		public OrderDetails(int orderId, double amount, String date) {
			super();
			this.orderId = orderId;
			this.amount = amount;
			this.date = date;
		}

		public OrderDetails() {
			super();
		}
	}

}

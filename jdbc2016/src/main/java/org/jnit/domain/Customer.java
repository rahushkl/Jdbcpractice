package org.jnit.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//OnetoOne
//OnetoMany
//ManyToMany - Task(many) and Reviewer(many)

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	private int customerId;
	private String name;
	private String city;
	private String street;
	private String state;
	private String country;
	private int zipCode;

	private PhoneInformation phoneInfo;
	private List<Order> orders = new ArrayList<Order>();

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public PhoneInformation getPhoneInfo() {
		return phoneInfo;
	}

	public void setPhoneInfo(PhoneInformation phoneInfo) {
		this.phoneInfo = phoneInfo;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name
				+ ", city=" + city + ", street=" + street + ", state=" + state
				+ ", country=" + country + ", zipCode=" + zipCode
				+ ", phoneInfo=" + phoneInfo + ", orders=" + orders + "]";
	}

	

}

package org.jnit.domain;
import java.io.Serializable;

public class PhoneInformation implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id;
	private String home;
	private String work;
	private String cell;
	//@ManytoOne
	private Customer customer;

	@Override
	public String toString() {
		return "PhoneInformation [id=" + id + ", home=" + home + ", work="
				+ work + ", cell=" + cell + "]";
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}
}
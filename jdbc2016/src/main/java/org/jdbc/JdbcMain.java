package org.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.jnit.domain.Customer;
import org.jnit.domain.Order;
import org.jnit.domain.OrderStatus;
import org.jnit.domain.PhoneInformation;

public class JdbcMain {
	public static void main(String[] args) {
		//JdbcMain.getCustomerData();
		//Customer customer = new Customer();
		//customer.setName("Ajay");
		//customer.setStreet("144 Valley View");
		//customer.setCity("Coppell");
		//customer.setState("TX");
		//customer.setZipCode(85435);
		//insertCustomerStmt(customer);
		//JdbcMain.getCustomerData();
		//Customer cus = getCustomerDataWithPhoneInfo(1);
		//System.out.println(cus.getName());
		//System.out.println(cus.getPhoneInfo().getHome());
		Customer c = getCustomerWithOrder(1);
		System.out.println(c.getName());
		c.getOrders().forEach(o->System.out.println(o.getItem()));
		//getCustomerUsingStoredProc(6);
		//List<Customer>customers = getCustomers();
		//customers.forEach(cust-> System.out.println(cust.getName()));
		//Customer cust = getCustomerbyId(8);
		//System.out.println(cust.getName());
	}
	public static Connection getMeaConnection() throws SQLException{
		Connection conn;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javatutorial?serverTimezone=CST", "root", "R@man1611");
		return conn;
	}
	public static void insertCustomerStmt(Customer customer){
		try {
			Connection conn = getMeaConnection();
			String query = "insert into customer(firstname, street, city, state, zipcode) values('"+customer.getName()+"','"+customer.getStreet()+"','"+customer.getCity()+"','"+customer.getState()+"','"+
					customer.getZipCode()+"')";
		    Statement st = conn.createStatement();
		    
			int noOfRows = st.executeUpdate(query);
			System.out.println(noOfRows);
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void getCustomerUsingStoredProc(int customerId){
		try {
			Connection conn = getMeaConnection();
			String sql = "call fetchDetails(?)";
			CallableStatement cst = conn.prepareCall(sql);
			cst.setInt(1, customerId);
			ResultSet rs = cst.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("name"));
			}
			rs.close();
			cst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Customer getCustomerDataWithPhoneInfo(int id){
		Customer customer = new Customer();
		
		try {
			Connection conn = getMeaConnection();
			String query = "select c.firstname, c.street, c.city, p.home, p.cell, p.work from javatutorial.customer c join javatutorial.phoneinfo p on c.customerId = p.customerId where c.customerId = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			PhoneInformation p = new PhoneInformation();
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				customer.setName(rs.getString("firstname"));
				customer.setStreet(rs.getString("street"));
				customer.setCity(rs.getString("city"));
				p.setHome(rs.getString("home"));
				p.setCell(rs.getString("cell"));
				p.setWork(rs.getString("work"));
				customer.setPhoneInfo(p);
			}
			rs.close();
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customer;
	}
	public static void insertCustomer(Customer customer){
		try {
			Connection conn = getMeaConnection();
			String query = "insert into customer(firstname, street, city, state, zipcode) values(?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, customer.getName());
			pst.setString(2, customer.getStreet());
			pst.setString(3,  customer.getCity());
			pst.setString(4, customer.getState());
			pst.setInt(5, customer.getZipCode());
			int noOfRows = pst.executeUpdate();
			System.out.println(noOfRows);
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void getCustomerData(){
		try {
			Connection conn = getMeaConnection();
			String query = "select * from customer";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				System.out.println(rs.getString("firstname"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static List<Customer> getCustomers(){
		List<Customer>customers  = new ArrayList<>();
		try {
			Connection conn = getMeaConnection();
			String query = "select * from customer";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				Customer cust = new Customer();
				cust.setCity(rs.getString("city"));
				cust.setName(rs.getString("firstname"));
				cust.setCustomerId(rs.getInt("customerId"));
				cust.setStreet(rs.getString("street"));
				cust.setZipCode(rs.getInt("zipcode"));
				customers.add(cust);
				System.out.println(rs.getString("firstname"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;
	}
	public static Customer getCustomerbyId(int id){
		Customer cust =  null;
		try {
			Connection conn = getMeaConnection();
			String query = "select * from customer where customerId = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				cust = new Customer();
				cust.setCity(rs.getString("city"));
				cust.setName(rs.getString("firstname"));
				cust.setCustomerId(rs.getInt("customerId"));
				cust.setStreet(rs.getString("street"));
				cust.setZipCode(rs.getInt("zipcode"));
				
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cust;
	}
	public static Customer getCustomerWithOrder(int id){
		Customer customer = new Customer();
		List<Order>orders = new ArrayList<>();
		try {
			Connection conn = getMeaConnection();
			String query = "select c.firstname, c.city, o.item, o.order_status from javatutorial.customer c join javatutorial.ordersplaced o on c.customerId = o.customerId where c.customerId = ? ;";
			PreparedStatement ptst = conn.prepareStatement(query);
			ptst.setInt(1, id);
			ResultSet rs = ptst.executeQuery();
			while(rs.next()){
				customer.setCity(rs.getString("City"));
				customer.setName(rs.getString("firstname"));
				Order order = new Order();
				order.setItem(rs.getString("item"));
				order.setStatus(OrderStatus.valueOf((rs.getString("order_status"))));
				orders.add(order);
				
			}
			customer.setOrders(orders);
			rs.close();
			conn.close();
			ptst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}

	public static void updateCustomer(Customer customer) {
		try {
			Connection conn = getMeaConnection();
			String query = "update customer set firstname = ?, street = ?, city = ?, state = ?, zipcode = ? where customerId = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, customer.getName());
			pst.setString(2, customer.getStreet());
			pst.setString(3, customer.getCity());
			pst.setString(4, customer.getState());
			pst.setInt(5, customer.getZipCode());
			pst.setInt(6, customer.getCustomerId());
			int noOfRows = pst.executeUpdate();
			System.out.println(noOfRows);
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}

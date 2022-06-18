package br.com.san.ls.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "order_tb")
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private static final int DAYS_TO_RETURN_ORDER = 3;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "order_moment")
	private LocalDate orderMoment = LocalDate.now();
	
	@Column(name = "order_return")
	private LocalDate orderReturn;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<OrderItem> items = new ArrayList<OrderItem>();

	public Order() {

	}

	public Order(Integer id, User client) {
		this.id = id;
		this.setClient(client);
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public LocalDate getOrderMoment() {
		return orderMoment;
	}

	public LocalDate getOrderReturn() {
		return this.orderMoment.plusDays(DAYS_TO_RETURN_ORDER);
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

}

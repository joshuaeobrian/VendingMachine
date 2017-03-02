package com.tiy.pojo;

/**
 * Created by josh on 3/1/17.
 */
public class Customer {
	private Double Money;
	private Double Change;

	public Customer(Double money, Double change) {
		Money = money;
		Change = change;
	}

	public Double getMoney() {
		return Money;
	}

	public void setMoney(Double money) {
		Money = money;
	}

	public Double getChange() {
		return Change;
	}

	public void setChange(Double change) {
		Change = change;
	}
}

package com.tiy.pojo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;



/**
 * Created by josh on 3/1/17.
 */
public class Product {
	private SimpleStringProperty LotNumber;
	private SimpleStringProperty Product;
	private SimpleStringProperty Price;
	private SimpleStringProperty Qty;

	public Product(String lotNumber, String product, String price, String qty) {
		this.LotNumber = new SimpleStringProperty(this,"LotNumber",lotNumber);
		this.Product = new SimpleStringProperty(this,"Product",product);
		this.Price = new SimpleStringProperty(this,"Price",price);
		this.Qty = new SimpleStringProperty(this,"Qty",qty);
	}

	public String getLotNumber() {
		return LotNumber.get();
	}

	public SimpleStringProperty lotNumberProperty() {
		return LotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.LotNumber.set(lotNumber);
	}

	public String getProduct() {
		return Product.get();
	}

	public SimpleStringProperty productProperty() {
		return Product;
	}

	public void setProduct(String product) {
		this.Product.set(product);
	}

	public String getPrice() {
		return Price.get();
	}

	public SimpleStringProperty priceProperty() {
		return Price;
	}

	public void setPrice(String price) {
		this.Price.set(price);
	}

	public String getQty() {
		return Qty.get();
	}

	public SimpleStringProperty qtyProperty() {
		return Qty;
	}

	public void setQty(String qty) {
		this.Qty.set(qty);
	}
}

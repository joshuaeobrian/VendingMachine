package com.tiy;

import com.tiy.pojo.Customer;
import com.tiy.pojo.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

	public TableView ProductTableView;
	public TableColumn lotCol;
	public TableColumn productCol;
	public TableColumn priceCol;
	public TableColumn qtyCol;
	public TextField outputBox;
	public TextField moneyBox;
	private Customer customer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		final ObservableList<Product> product = FXCollections.observableArrayList();
		product.addAll(new Product("A01","Pretzels","1.25","10"));
		product.addAll(new Product("A02","Skittles","1.10","10"));
		product.addAll(new Product("A03","Gum","0.75","10"));
		product.addAll(new Product("B01","Sandwich","3.50","10"));
		product.addAll(new Product("B02","Chips","0.75","10"));
		product.addAll(new Product("B03","Gummies","1.00","10"));
		product.addAll(new Product("C01","Bread","2.25","10"));
		product.addAll(new Product("C02","Raisins","1.25","10"));
		product.addAll(new Product("C03","Water","1.00","10"));
		setTable(product);
		outputBox.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue.length()==3){
					System.out.println("ok");
					//TODO kick new function that removes the product and clears screen
				}
			}
		});

	}
	private void setTable(ObservableList list){
		lotCol.setCellValueFactory(new PropertyValueFactory<Product,String>("LotNumber"));
		lotCol.setCellFactory(TextFieldTableCell.forTableColumn());
		productCol.setCellValueFactory(new PropertyValueFactory<Product,String>("Product"));
		productCol.setCellFactory(TextFieldTableCell.forTableColumn());
		priceCol.setCellValueFactory(new PropertyValueFactory<Product,String>("Price"));
		priceCol.setCellFactory(TextFieldTableCell.forTableColumn());
		qtyCol.setCellValueFactory(new PropertyValueFactory<Product,Integer>("Qty"));
		qtyCol.setCellFactory(TextFieldTableCell.forTableColumn());

		ProductTableView.setItems(list);
	}

	public void buttonPress(ActionEvent actionEvent) {
		String[] Button = actionEvent.getSource().toString().split("'");
		outputBox.appendText(Button[1]);
	}

	public void insertButton(ActionEvent actionEvent) {
		customer = new Customer(Double.parseDouble(moneyBox.getText().toString()),0.00);
		System.out.println(customer.getMoney());
		//TODO flash in output INSERTED $ X.XX
	}
}

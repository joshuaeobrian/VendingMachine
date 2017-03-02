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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class Controller implements Initializable {

	public TableView ProductTableView;
	public TableColumn lotCol;
	public TableColumn productCol;
	public TableColumn priceCol;
	public TableColumn qtyCol;
	public TextField outputBox;
	public TextField moneyBox;
	public Button moneyButton;
	public TextArea vmoBox;
	public VBox pinPadPane;
	private Customer customer;
	private DecimalFormat df = new DecimalFormat("#0.00");
	private ObservableList<Product> product = FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		customer = new Customer(0.00,0.00);
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
		vmoBox.setEditable(false);
		setListeners();

	}
	private void setListeners(){
		outputBox.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue.length()==3){
					if(newValue.equals("666")){
						pinPadPane.setVisible(false);
					}else {
						getItem(newValue);
						//TODO kick new function that removes the product and clears screen
					}
				}
			}
		});
	}
	public void buttonPress(ActionEvent actionEvent) {
		if(customer.getMoney()==0.00){
			vmoBox.appendText("Please Insert Money!");
		}else {
			String[] Button = actionEvent.getSource().toString().split("'");
			outputBox.appendText(Button[1]);
		}
	}

	public void insertButton(ActionEvent actionEvent) {
		String[] moneyAction = actionEvent.getSource().toString().split("'");
		if(moneyAction[1].toLowerCase().contains("insert")){

			customer.setMoney(Double.parseDouble(moneyBox.getText()));
			vmoBox.appendText( "\n===================="+
									"\nCustomer has inserted $"+ df.format(customer.getMoney())+
									"\n====================");
			moneyButton.setText("Take Change");
			moneyBox.setEditable(false);
		}else if (moneyAction[1].toLowerCase().contains("change")){
			moneyBox.clear();
			moneyBox.setPromptText("0.00");
			moneyButton.setText("Insert Money");
			outputBox.clear();
			moneyBox.setEditable(true);
		}

	}

	//all private classes
	//Set Product table
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
	private void getItem(String lotNumber){
		int convert;
		String searchResponse="";
		for(int i = 0; i <= ProductTableView.getItems().size();i++){
			ProductTableView.getSelectionModel().select(i);
			Product item = (Product) ProductTableView.getSelectionModel().getSelectedItem();
			if(item.getLotNumber().toLowerCase().equals(lotNumber.toLowerCase())){
				if(customer.getMoney() >= Double.parseDouble(item.getPrice())) {
					if (Integer.parseInt(item.getQty()) == 0) {
						System.out.println();
						vmoBox.appendText( "\n====================="+
												"\nSorry, there are " + item.getQty() + " " + item.getProduct() + " left!"+
												"\n=====================");
					} else {
						convert = Integer.parseInt(item.getQty()) - 1;
						item.setQty(Integer.toString(convert));
						customer.setMoney(customer.getMoney()- Double.parseDouble(item.getPrice()));
						vmoBox.appendText( "\n====================="+
												"\nDropping some " + item.getProduct()+
												"\n"+item.getQty() + " " + item.getProduct() + " left!"+
												"\nHere is your change: "+ df.format(customer.getMoney())+
												"\n=====================");
						moneyBox.setText(df.format(customer.getMoney()));
						setTable(product);
					}
				}else{
					//// TODO: 3/2/17 warn user that they do not have enough in number panel



					//Shows warning in output panel.
					vmoBox.appendText( "\n====================="+
											"\nSorry you do not have enough money.." +
											"\nMoney: "+customer.getMoney()+
											"\nItem Cost: "+item.getPrice()+
											"\n=====================");
					moneyButton.setText("Insert Money");
					moneyBox.clear();
					moneyBox.setPromptText("0.00");
				}
				break;
			}else{
				//creates response if lot number doesn't exist
				if(searchResponse.equals("")){
					searchResponse = "\n=====================" +
							"\nSorry, there is no Item with "+lotNumber+
							"\nPlease try again...."+
							"\n=====================";
				}
			}
		}
		if(!searchResponse.equals("")){
			vmoBox.appendText(searchResponse);
		}

	}
}

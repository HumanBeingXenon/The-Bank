package application;

import javafx.beans.property.SimpleStringProperty;

public class _TransactionInfo {
	private final SimpleStringProperty transactionDate;
	private final SimpleStringProperty transactionDetail;
	private final SimpleStringProperty balance;
	
	_TransactionInfo(TransactionInfo info){
		this.transactionDate=new SimpleStringProperty(info.getTransactionDate());
		this.transactionDetail=new SimpleStringProperty(info.getTransactionDetail());
		this.balance=new SimpleStringProperty(Double.toString(info.getBalance()));
	}

	public SimpleStringProperty getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate.set(transactionDate);
	}

	public SimpleStringProperty getTransactionDetail() {
		return transactionDetail;
	}

	public void setTransactionDetail(String transactionContent) {
		this.transactionDetail.set(transactionContent);
	}

	public SimpleStringProperty getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance.set(Double.toString(balance));
	}
}
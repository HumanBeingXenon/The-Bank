package application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Account {
	private String accountID;
	private String password;
	private double balance;
	private boolean isCreditCard;
	private ArrayList<TransactionInfo> transactions;
	private ObservableList<_TransactionInfo> _transactions = FXCollections.observableArrayList();
	
	Account(){}
	
	Account(String password){
		this.password=password;
		balance=0;
		isCreditCard=false;
		transactions=new ArrayList<TransactionInfo>();
	}
	
	Account(String accountID, String password){
		this.accountID=accountID;
		this.password=password;
		transactions=new ArrayList<TransactionInfo>();
	}
	
	Account(String accountID, String password, double balance){
		this.accountID=accountID;
		this.password=password;
		this.balance=balance;
		isCreditCard=false;
		transactions=new ArrayList<TransactionInfo>();
		
		if(balance>0) {
			TransactionInfo temp=new TransactionInfo(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "+"+balance, this.balance);
			transactions.add(temp);
			_transactions.add(new _TransactionInfo(temp));
		}
	}
	
	Account(String accountID, String password, double balance, boolean isCreditCard){
		this.accountID=accountID;
		this.password=password;
		this.balance=balance;
		this.isCreditCard=isCreditCard;
		transactions=new ArrayList<TransactionInfo>();
		if(balance>0) {
			TransactionInfo temp = new TransactionInfo(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "+"+balance, this.balance);
			transactions.add(temp);
			_transactions.add(new _TransactionInfo(temp));
		}
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double present_balance) {
		this.balance=present_balance;
	}
	
	public void setBalance(double amount, String operator) {
		switch(operator) {
			case "+": this.balance+=amount; break;
			case "-": this.balance-=amount; break;
		}
		TransactionInfo temp=new TransactionInfo(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), operator+amount, this.balance);
		transactions.add(temp);
		_transactions.add(new _TransactionInfo(temp));
	}
	
	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isCreditCard() {
		return isCreditCard;
	}

	public void setCreditCard(boolean isCreditCard) {
		this.isCreditCard = isCreditCard;
	}

	public ArrayList<TransactionInfo> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<TransactionInfo> transactions) {
		this.transactions = transactions;
	}
	
	public ObservableList<_TransactionInfo> get_Transactions() {
		return _transactions;
	}
	
	public static void main(String[] args) {
		Account account=new Account("163253", "314159", 100);
		Transaction.SaveMoney(account, 200);
		Transaction.WithdrawMoney(account, 35);
		Transaction.WithdrawMoney(account, 265);
		Transaction.printTransactions(account);
	}
}
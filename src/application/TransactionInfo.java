package application;

public class TransactionInfo {
	private String transactionDate;
	private String transactionDetail;
	private double balance;
	
	TransactionInfo(){
		this.transactionDate=null;
		this.transactionDetail=null;
		this.balance=0.0;
	}
	
	TransactionInfo(String detail, double balance){
		this.transactionDate=null;
		this.transactionDetail=detail;
		this.balance=balance;
	}
	
	TransactionInfo(String date, String detail, double balance){
		this.transactionDate=date;
		this.transactionDetail=detail;
		this.balance=balance;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionDetail() {
		return transactionDetail;
	}

	public void setTransactionDetail(String transactionContent) {
		this.transactionDetail = transactionContent;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
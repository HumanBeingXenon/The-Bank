package application;

public class Transaction {
	Account CreateAccount(String ID,String pass) {
		Account account=new Account(ID,pass); 
		return account;
	}
	
	public static boolean CreateAccount(String ID, String pass, double amount, boolean isCredit) {
		try {
			Account account=new Account(ID, pass, amount, isCredit); 
			Operation.AddAccount(account);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean LogIn(String ID,String pass) {
		Account client=new Account(ID,pass);
		if(Operation.MatchAccount(client)) return true;
		return false;
	}
	
	public static boolean SaveMoney(Account client,double amount) {
		try {
			client.setBalance(amount,"+");
			Operation.UpdateAccount(client);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static int WithdrawMoney(Account client,double amount) {
		try{
			if(!client.isCreditCard()&&(client.getBalance()<0||client.getBalance()<amount)) return 0;
			else client.setBalance(amount,"-");
			Operation.UpdateAccount(client);
			return 1;
		} catch(Exception e) {
			return -1;
		}
	}
	
	public static void printTransactions(Account client) {
		for(int i=0;i<client.getTransactions().size();i++) {
			System.out.println("Transaction "+(i+1)+": "+client.getTransactions().get(i));
		}
	}
}

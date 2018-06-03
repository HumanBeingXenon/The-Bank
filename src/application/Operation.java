package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Operation {
	final static String path="src/AccountInfo.xml";
	
	static public void showMessage(AlertType type, String title, String head, String content) {
		Alert alert=new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(head);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static void CreateXML() {
		Document document=DocumentHelper.createDocument();
		document.addElement("Bank_Management_System");
		OutputFormat format=OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		format.setIndent(true);
		format.setIndent("\t");
		format.setNewlines(true);
		try {
			Writer out=new FileWriter(path);
			XMLWriter writer=new XMLWriter(out, format);
			writer.write(document);
			writer.close();
			System.out.println("Building XML file successfully!");
		} catch(IOException e) {
			System.out.println("Fail to build the XML file.");
			e.printStackTrace();
		}
	}
	
	public static boolean AddAccount(Account temp) {
		try {
			SAXReader reader=new SAXReader();
			Document document=reader.read(new File(path));
			Element root=document.getRootElement();
			Element account=root.addElement("account","");
			account.addAttribute("accountID", temp.getAccountID());
			Element pass=account.addElement("password");
			pass.setText(temp.getPassword());
			Element credit=account.addElement("isCreditCard");
			credit.setText(Boolean.toString(temp.isCreditCard()));
			Element balance=account.addElement("balance");
			balance.setText(Double.toString(temp.getBalance()));
			Element transaction=account.addElement("transactions");
			ArrayList<TransactionInfo> tran=temp.getTransactions();		//tran为新增账户的交易记录
			for(int i=0;i<tran.size();i++) {
				Element trans=transaction.addElement("transaction");		//trans为Element类的List
				trans.addAttribute("ID",Integer.toString(i+1));
				Element date=trans.addElement("date");
				date.setText(tran.get(i).getTransactionDate());
				Element detail=trans.addElement("detail");
				detail.setText(tran.get(i).getTransactionDetail());
				Element _balance=trans.addElement("balance");
				_balance.setText(Double.toString(tran.get(i).getBalance()));
			}
			OutputFormat format=OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			format.setIndent(true);
			format.setIndent("\t");
			format.setNewlines(true);
			XMLWriter writer=new XMLWriter(new FileWriter(path), format);
			writer.write(document);
			writer.close();
			System.out.println("Adding an account successfully!");
			return true;
		} catch(DocumentException e) {
			System.out.println("Fail to add an account.");
			e.printStackTrace();
			return false;
		} catch(IOException e) {
			System.out.println("Fail to add an account.");
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean UpdateAccount(Account temp) {
		try {
			SAXReader reader=new SAXReader();
			Document document=reader.read(new File(path));
			Element root=document.getRootElement();
			for(Iterator<Element> it=root.elementIterator();it.hasNext();) {
				Element account=it.next();
				Attribute accountID=account.attribute("accountID");
				if(temp.getAccountID().equals(accountID.getText())) {
					account.element("password").setText(temp.getPassword());
					account.element("balance").setText(Double.toString(temp.getBalance()));
					account.element("isCreditCard").setText(Boolean.toString(temp.isCreditCard()));
					Element transaction=account.element("transactions");
					//transactions.getParent().remove(transactions);
					List<Element> formerTransaction=transaction.elements();
					//Element transaction=account.addElement("transactions");
					ArrayList<TransactionInfo> tran=temp.getTransactions();
					for(int i=formerTransaction.size();i<tran.size();i++) {
						Element trans=transaction.addElement("transaction");
						trans.addAttribute("ID", Integer.toString(i+1));
						Element date=trans.addElement("date");
						date.setText(tran.get(i).getTransactionDate());
						Element detail=trans.addElement("detail");
						detail.setText(tran.get(i).getTransactionDetail());
						Element balance=trans.addElement("balance");
						balance.setText(Double.toString(tran.get(i).getBalance()));
					}
				}
			}
			OutputFormat format=OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			format.setIndent(true);
			format.setIndent("\t");
			format.setNewlines(true);
			XMLWriter writer=new XMLWriter(new FileWriter(path), format);
			writer.write(document);
			writer.close();
			System.out.println("Modifying the account successfully!");
			return true;
		} catch(DocumentException e) {
			System.out.println("Fail to modify the account.");
			e.printStackTrace();
			return false;
		} catch(IOException e) {
			System.out.println("Fail to add an account.");
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean DeleteAccount(Account temp) {
		try {
			SAXReader reader=new SAXReader();
			Document document=reader.read(new File(path));
			Element root=document.getRootElement();
			for(Iterator<Element> it=root.elementIterator();it.hasNext();) {
				Element account=it.next();
				Attribute accountID=account.attribute("accountID");
				if(temp.getAccountID().equals(accountID.getText())) {
					Element parent=account.getParent();
					parent.remove(account);
				}
			}
			OutputFormat format=OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			format.setIndent(true);
			format.setIndent("\t");
			format.setNewlines(true);
			XMLWriter writer=new XMLWriter(new FileWriter(path), format);
			writer.write(document);
			writer.close();
			System.out.println("Deleting the account successfully!");
			return true;
		} catch(DocumentException e){
			System.out.println("Fail to delete the account.");
			e.printStackTrace();
			return false;
		} catch(IOException e) {
			System.out.println("Fail to add an account.");
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean MatchAccount(Account temp) {
		try {
			SAXReader reader=new SAXReader();
			Document document=reader.read(new File(path));
			Element root=document.getRootElement();
			for(Iterator<Element> it=root.elementIterator();it.hasNext();) {
				Element account=it.next();
				Attribute accountID=account.attribute("accountID");
				if(temp.getAccountID().equals(accountID.getText())) {
					if(account.element("password").getText().equals(temp.getPassword())) return true;
				}
			}
			return false;
		}catch(DocumentException e) {
			System.out.println("Fail to open the file!");
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean MatchAccount(String id,String password) {
		try {
			SAXReader reader=new SAXReader();
			Document document=reader.read(new File(path));
			Element root=document.getRootElement();
			for(Iterator<Element> it=root.elementIterator();it.hasNext();) {
				Element account=it.next();
				Attribute accountID=account.attribute("accountID");
				if(id.equals(accountID.getText())) {
					if(account.element("password").getText().equals(password)) return true;
				}
			}
			return false;
		}catch(DocumentException e) {
			System.out.println("Fail to open the file!");
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isNewAccount(String id) {
		try {
			SAXReader reader=new SAXReader();
			Document document=reader.read(new File(path));
			Element root=document.getRootElement();
			for(Iterator<Element> it=root.elementIterator();it.hasNext();) {
				Element account=it.next();
				Attribute accountID=account.attribute("accountID");
				if(id.equals(accountID.getValue())) return false;
			}
			return true;
		}catch(DocumentException e) {
			System.out.println("Fail to open the file!");
			e.printStackTrace();
			return false;
		}
	}
	
	public static Account getAccount(String ID) {
		try {
			Account temp=new Account();
			SAXReader reader=new SAXReader();
			Document document=reader.read(new File(path));
			Element root=document.getRootElement();
			for(Iterator<Element> it=root.elementIterator();it.hasNext();) {
				Element account=it.next();
				Attribute accountID=account.attribute("accountID");
				if(ID.equals(accountID.getValue())) {
					temp.setAccountID(ID);
					temp.setPassword(account.element("password").getText());
					temp.setBalance(Double.parseDouble(account.element("balance").getText()));
					temp.setCreditCard(Boolean.parseBoolean(account.element("isCreditCard").getText()));
					ArrayList<TransactionInfo> transactions=new ArrayList<TransactionInfo>();
					Element transaction=account.element("transactions");
					List<Element> formerTransaction=transaction.elements();
					for(int i=0;i<formerTransaction.size();i++) {
						transactions.add(new TransactionInfo(formerTransaction.get(i).element("date").getText(), formerTransaction.get(i).element("detail").getText(), Double.parseDouble(formerTransaction.get(i).element("balance").getText())));
					}
					temp.setTransactions(transactions);
				}
			}
			return temp;
		}catch(DocumentException e) {
			System.out.println("Fail to open the file!");
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		CreateXML();
		Account a=new Account("123456","314159",100,false);
		Transaction.WithdrawMoney(a, 75);
		Transaction.SaveMoney(a, 200);
		AddAccount(a);
		Account b=new Account("864689","314159",1230,true);
		AddAccount(b);
		b.setPassword("271828");
		Transaction.SaveMoney(b, 2048);
		Transaction.WithdrawMoney(b, 2047);
		UpdateAccount(b);
		Transaction.SaveMoney(b, 4096);
		//DeleteAccount(b);
	}
}

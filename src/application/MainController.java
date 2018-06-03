package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainController {
	private static Account client=UserInfo.client;
	
	@FXML private Button btnSearch;
	@FXML private Button btnChange;
	@FXML private Button btnExit;
	@FXML private Button btnDeposit;
	@FXML private Button btnWithdraw;
	@FXML private Button btnTransfer;
	
	@FXML private GridPane panSearch;
	@FXML private TextField txfBalance;
	@FXML private Button btnDetail;
	@FXML private TableView<_TransactionInfo> tbvDetail;
	@FXML private TableColumn<_TransactionInfo,String> tbcDate;
	@FXML private TableColumn<_TransactionInfo,String> tbcAmount;
	@FXML private TableColumn<_TransactionInfo,String> tbcBalance;
	
	@FXML private GridPane panChange;
	@FXML private PasswordField pwfOld;
	@FXML private PasswordField pwfNew;
	@FXML private PasswordField pwfConfirm;
	@FXML private Button btnSubmit_C;
	
	@FXML private GridPane panDeposit;
	@FXML private TextField txfDeposit;
	@FXML private Label lblResult_D;
	@FXML private Button btnSubmit_D;
	
	@FXML private GridPane panWithdraw;
	@FXML private TextField txfWithdraw;
	@FXML private Label lblResult_W;
	@FXML private Button btnSubmit_W;
	
	@FXML private GridPane panTransfer;
	@FXML private TextField txfAccount;
	@FXML private TextField txfAmount;
	@FXML private PasswordField pwfPassword_T;
	@FXML private PasswordField pwfConfirm_T;
	@FXML private Button btnSubmit_T;
	
	
	
	public void search() {
		panSearch.setVisible(true);
		panChange.setVisible(false);
		panDeposit.setVisible(false);
		panWithdraw.setVisible(false);
		panTransfer.setVisible(false);
		tbvDetail.setVisible(false);
		
		txfBalance.setText(Double.toString(client.getBalance()));
	}
	
	public void detail() {
		if(btnDetail.getText().equals("��ʾ������ϸ")) {
			btnDetail.setText("���ؽ�����ϸ");
			tbvDetail.setVisible(true);
			
			tbcDate.setCellValueFactory(transaction->transaction.getValue().getTransactionDate());
			tbcAmount.setCellValueFactory(transaction->transaction.getValue().getTransactionDetail());
			tbcBalance.setCellValueFactory(transaction->transaction.getValue().getBalance());
			tbvDetail.setItems(client.get_Transactions());
		} else {
			btnDetail.setText("��ʾ������ϸ");
			tbvDetail.setVisible(false);
		}
	}
	
	/**
	 * 
	 */
	
	public void changePassword() {
		panSearch.setVisible(false);
		panChange.setVisible(true);
		panDeposit.setVisible(false);
		panWithdraw.setVisible(false);
		panTransfer.setVisible(false);
		
		pwfOld.setText(null);
		pwfNew.setText(null);
		pwfConfirm.setText(null);
	}
	
	public void sumbit_C() {
		String Old=pwfOld.getText().trim();
		String New=pwfNew.getText().trim();
		String Confirm=pwfConfirm.getText().trim();
		if(Old.isEmpty()||New.isEmpty()||Confirm.isEmpty()) {
			Operation.showMessage(AlertType.WARNING, "��ʾ", null, "����δ��д��ȫ��");
			return ;
		}
		if(!Old.equals(client.getPassword())) {
			Operation.showMessage(AlertType.ERROR, "��ʾ", null, "���������");
			return ;
		}
		if(!New.equals(Confirm)) {
			Operation.showMessage(AlertType.ERROR, "��ʾ", null, "������������벻һ�£�");
		} else {
			if(New.equals(Old)) {
				Operation.showMessage(AlertType.ERROR, "��ʾ", null, "�¾����벻��һ����");
				return ;
			}
			client.setPassword(New);
			if(Operation.UpdateAccount(client)) Operation.showMessage(AlertType.INFORMATION, "��ʾ", null, "�޸�����ɹ���");
			else Operation.showMessage(AlertType.ERROR, "��ʾ", null, "�޸�����ʧ�ܣ�");
		}
	}
	
	/**
	 * 
	 */
	
	public void deposit() {
		panSearch.setVisible(false);
		panChange.setVisible(false);
		panDeposit.setVisible(true);
		panWithdraw.setVisible(false);
		panTransfer.setVisible(false);
		
		txfDeposit.setText(null);
	}
	
	public void submit_D() {
		try {
			String amount=txfDeposit.getText();
			if(amount.trim().isEmpty()) {
				Operation.showMessage(AlertType.WARNING, "��ʾ", null, "����д����");
				return ;
			} else {
				if(Transaction.SaveMoney(client, Double.parseDouble(amount))) {
					Operation.showMessage(AlertType.INFORMATION, "��ʾ", null, "���ɹ���");
				} else {
					Operation.showMessage(AlertType.ERROR, "��ʾ", null, "ϵͳ���⣬���ʧ�ܣ�");
				}
			}
		} catch(NumberFormatException e) {
			Operation.showMessage(AlertType.ERROR, "��ʾ", null, "������Ϸ��Ľ�");
		}
	}
	
	/**
	 * 
	 */
	
	public void withdraw() {
		panSearch.setVisible(false);
		panChange.setVisible(false);
		panDeposit.setVisible(false);
		panWithdraw.setVisible(true);
		panTransfer.setVisible(false);
		
		txfWithdraw.setText(null);
	}
	
	public void submit_W() {
		try {
			String amount=txfWithdraw.getText();
			if(amount.trim().isEmpty()) {
				Operation.showMessage(AlertType.WARNING, "��ʾ", null, "����дȡ���");
				return ;
			} else {
				int result=Transaction.WithdrawMoney(client, Double.parseDouble(amount));
				switch(result) {
					case 1: Operation.showMessage(AlertType.INFORMATION, "��ʾ", null, "ȡ��ɹ���"); break;
					case 0:  Operation.showMessage(AlertType.WARNING, "��ʾ", null, "���Ŀ�Ϊ�����ÿ�������͸֧��"); break;
					case -1: Operation.showMessage(AlertType.ERROR, "��ʾ", null, "ϵͳ���⣬ȡ��ʧ�ܣ�"); break;
				}
			}
		} catch(NumberFormatException e) {
			Operation.showMessage(AlertType.ERROR, "��ʾ", null, "������Ϸ��Ľ�");
		}
	}
	
	/**
	 * 
	 */
	
	public void transfer() {
		panSearch.setVisible(false);
		panChange.setVisible(false);
		panDeposit.setVisible(false);
		panWithdraw.setVisible(false);
		panTransfer.setVisible(true);
		
		txfAccount.setText(null);
		txfAmount.setText(null);
		pwfPassword_T.setText(null);
		pwfConfirm_T.setText(null);
	}
	
	public void submit_T() {
		String id=txfAccount.getText().trim();
		String amount=txfAmount.getText().trim();
		String password=pwfPassword_T.getText().trim();
		String confirm=pwfConfirm_T.getText().trim();
		if(id.isEmpty()||amount.isEmpty()||password.isEmpty()||confirm.isEmpty()) {
			Operation.showMessage(AlertType.WARNING, "��ʾ", null, "��Ϣδ��д��ȫ��");
			return ;
		}
		if(Operation.isNewAccount(id)) {
			Operation.showMessage(AlertType.WARNING, "��ʾ", null, "�˻������ڣ�");
			return ;
		}
		if(!password.equals(confirm)) {
			Operation.showMessage(AlertType.WARNING, "��ʾ", null, "������������벻һ�£�");
			return ;
		}
		if(!Operation.MatchAccount(client.getAccountID(), password)) {
			Operation.showMessage(AlertType.ERROR, "��ʾ", null, "�������");
			return ;
		} else {
			Account payee=Operation.getAccount(id);
			int result=Transaction.WithdrawMoney(client, Double.parseDouble(amount));
			switch(result) {
				case 1: Transaction.SaveMoney(payee, Double.parseDouble(amount)); Operation.showMessage(AlertType.INFORMATION, "��ʾ", null, "ת�˳ɹ���"); break;
				case 0: Operation.showMessage(AlertType.WARNING, "��ʾ", null, "���Ŀ�Ϊ�����ÿ�������͸֧��"); break;
				case -1: Operation.showMessage(AlertType.ERROR, "��ʾ", null, "ϵͳ���⣬ת��ʧ�ܣ�"); break;
			}
		}
	}
	
	public void exit() {
		Stage stage = (Stage) btnExit.getScene().getWindow();
		stage.close();
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("LogIn.fxml"));
			Scene scene = new Scene(root,600,450);
			Stage primaryStage = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("���й���ϵͳ��¼����");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

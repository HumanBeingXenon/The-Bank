package application;

import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class RegisterController {
	@FXML private TextField txfAccount;
	@FXML private PasswordField pwfPassword;
	@FXML private PasswordField pwfConfirm;
	@FXML private TextField txfBalance;
	@FXML private CheckBox chbCredit;
	@FXML private Button btnCreate;
	@FXML private Button btnSubmit;
	
	public void create() {
		txfAccount.setText(Integer.toString(new Random().nextInt(899999)+100000));
		btnSubmit.setDisable(false);
	}
	
	public void submit() {
		chbCredit.setAllowIndeterminate(false);
		String id=txfAccount.getText().trim();
		String pass=pwfPassword.getText().trim();
		String con=pwfConfirm.getText().trim();
		boolean isCredit=chbCredit.isSelected();
		String balance=txfBalance.getText();
		if(id.isEmpty()||pass.isEmpty()||con.isEmpty()||txfBalance.getText().isEmpty()) {
			Operation.showMessage(AlertType.ERROR, "��ʾ", null, "��Ϣδ��д��ȫ��");
			return ;
		}
		if(!pass.equals(con)) {
			Operation.showMessage(AlertType.WARNING, "��ʾ", null, "������������벻һ�£�");
		} else {
			if(!Operation.isNewAccount(id)) {
				Operation.showMessage(AlertType.WARNING, "��ʾ", null, "���ʺ��Ѵ��ڣ����������ɣ�");
				return ;
			}
			try {
				Transaction.CreateAccount(id, pass, Double.parseDouble(balance), isCredit); 
				Operation.showMessage(AlertType.INFORMATION, "��ʾ", null, "ע��ɹ���");
			} catch(Exception e) {
				Operation.showMessage(AlertType.ERROR, "��ʾ", null, "����������");
			}
		}
	}
}

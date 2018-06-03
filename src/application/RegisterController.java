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
			Operation.showMessage(AlertType.ERROR, "提示", null, "信息未填写完全！");
			return ;
		}
		if(!pass.equals(con)) {
			Operation.showMessage(AlertType.WARNING, "提示", null, "两次输入的密码不一致！");
		} else {
			if(!Operation.isNewAccount(id)) {
				Operation.showMessage(AlertType.WARNING, "提示", null, "此帐号已存在，请重新生成！");
				return ;
			}
			try {
				Transaction.CreateAccount(id, pass, Double.parseDouble(balance), isCredit); 
				Operation.showMessage(AlertType.INFORMATION, "提示", null, "注册成功！");
			} catch(Exception e) {
				Operation.showMessage(AlertType.ERROR, "提示", null, "输入金额有误！");
			}
		}
	}
}

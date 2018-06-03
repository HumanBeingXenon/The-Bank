package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInController {
	@FXML private TextField txfAccount;
	@FXML private PasswordField pwfPassword;
	@FXML private Button btnSubmit;
	@FXML private Button btnRegister;
	
	public void submit() {
		String account=txfAccount.getText().trim();
		String password=pwfPassword.getText().trim();
		if(account.isEmpty()||password.isEmpty()) {
			Operation.showMessage(AlertType.WARNING, "��ʾ", null, "�û��������벻��Ϊ�գ�");
		} else {
			if(Transaction.LogIn(account,password)==false) {
				Operation.showMessage(AlertType.WARNING, "��ʾ", null, "�û������������");
			} else {
				UserInfo.client=Operation.getAccount(account);
				Stage stage = (Stage) btnSubmit.getScene().getWindow();
				stage.close();
				try {
					BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
					Scene scene = new Scene(root,600,450);
					Stage primaryStage = new Stage();
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setTitle("��ѡ����Ҫ�ķ���");
					primaryStage.setScene(scene);
					primaryStage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void register() {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Register.fxml"));
			Scene scene = new Scene(root,600,450);
			Stage primaryStage = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("��ѡ����Ҫ�ķ���");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

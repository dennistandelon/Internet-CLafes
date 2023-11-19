package view.auth;

import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import util.StageManager;
import view.Page;

public class RegisterPage extends Page{

	BorderPane bp;
	GridPane gp;
	Label name_lbl, pass_lbl, confirm_lbl, age_lbl, error_lbl;
	TextField name_tf;
	PasswordField pass_pf, confirm_pf;
	Spinner<Integer> age_sp;
	Button regis_btn, login_btn;
	
	
	public RegisterPage() {
		this.title = "Internet CLafes - Register";
	}

	@Override
	protected void init() {
		bp = new BorderPane();
		gp = new GridPane();
		
		name_lbl = new Label("Username");
		pass_lbl = new Label("Password");
		confirm_lbl = new Label("Confirm Password");
		age_lbl = new Label("Age");
		error_lbl = new Label("");
		
		name_tf = new TextField("");
		pass_pf = new PasswordField();
		confirm_pf = new PasswordField();

		age_sp = new Spinner<Integer>(0, 100, 13);
		
		regis_btn = new Button("Register");
		login_btn = new Button("Login");
	}

	@Override
	protected void setLayout() {
		
		gp.add(name_lbl, 0, 0);
		gp.add(name_tf, 1, 0);
		gp.add(pass_lbl, 0, 1);
		gp.add(pass_pf, 1, 1);
		gp.add(confirm_lbl, 0, 2);
		gp.add(confirm_pf, 1, 2);
		gp.add(age_lbl, 0, 3);
		gp.add(age_sp, 1, 3);
		gp.add(regis_btn, 0, 4);
		gp.add(login_btn, 1, 4);
		
		bp.setCenter(gp);
		bp.setBottom(error_lbl);
		
		this.scene = new Scene(bp,500,500);
	}

	@Override
	protected void setStyle() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setAction() {
		
		this.login_btn.setOnMouseClicked(e->{
			StageManager.getInstance().setPage(new LoginPage());
		});
		
		this.regis_btn.setOnMouseClicked(e->{
			String Username = name_tf.getText().toString();
			String Password = pass_pf.getText().toString();
			String ConfirmPassword = confirm_pf.getText().toString();
			Integer Age = age_sp.getValue();
			
			error_lbl.setText(UserController.AddNewUser(Username, Password, ConfirmPassword, Age));
		});
		
	}

}

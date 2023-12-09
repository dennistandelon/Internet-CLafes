package view.auth;

import controller.UserController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import view.Page;

public class RegisterPage extends Page{

	GridPane gp;
	Label name_lbl, pass_lbl, confirm_lbl, age_lbl, error_lbl;
	TextField name_tf;
	PasswordField pass_pf, confirm_pf;
	Spinner<Integer> age_sp;
	Button regis_btn;
	
	
	public RegisterPage() {
		this.title = "Internet CLafes - Register";
	}

	@Override
	protected void init() {
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
		gp.add(error_lbl, 0, 5);
		
		mainFrame.setCenter(gp);
	}

	@Override
	protected void setStyle() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setAction() {
		
		this.regis_btn.setOnAction(e->{
			String Username = name_tf.getText().toString();
			String Password = pass_pf.getText().toString();
			String ConfirmPassword = confirm_pf.getText().toString();
			Integer Age = age_sp.getValue();
			
			error_lbl.setText(UserController.AddNewUser(Username, Password, ConfirmPassword, Age));
		});
		
	}

}

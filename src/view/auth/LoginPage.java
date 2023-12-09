package view.auth;

import controller.UserController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.User;
import util.StageManager;
import view.Page;
import view.pages.ManagePC;
import view.pages.ViewAllPC;

public class LoginPage extends Page {

	GridPane gp;
	Label name_lbl, pass_lbl, error_lbl;
	TextField name_tf;
	PasswordField pass_pf;
	Button login_btn;
	
	public LoginPage() {
		this.title = "Internet Clafes - Login";
	}

	@Override
	protected void init() {
		gp = new GridPane();
		
		name_lbl = new Label("Username");
		pass_lbl = new Label("Password");
		error_lbl = new Label("");
		
		name_tf = new TextField("");
		pass_pf = new PasswordField();
		
		login_btn = new Button("Login");
	}

	@Override
	protected void setLayout() {
		
		gp.add(name_lbl, 0, 0);
		gp.add(name_tf, 1, 0);
		gp.add(pass_lbl, 0, 1);
		gp.add(pass_pf, 1, 1);
		gp.add(login_btn, 0, 2);
		gp.add(error_lbl, 0, 3);
		
		mainFrame.setCenter(gp);
	}

	@Override
	protected void setStyle() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setAction() {
	
		this.login_btn.setOnAction(e->{
			String username = name_tf.getText().toString();
			String password = pass_pf.getText().toString();
			
			error_lbl.setText(UserController.validateLogin(username, password));
			
			if(error_lbl.getText().equals("success")) {
				User user = UserController.GetUserData(username, password);
				StageManager.getInstance().setUser(user);
				if(user.getUserRole().equals("Admin")) {
					StageManager.getInstance().setPage(new ManagePC());
				} else {
					StageManager.getInstance().setPage(new ViewAllPC());					
				}
			}
		});

	}

}

package view.auth;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.User;
import util.StageManager;
import view.Page;
import view.pages.ManagePC;
import view.pages.ViewAllPC;

public class LoginPage extends Page {

	private GridPane gp;
	private VBox vb;
	private Label name_lbl, pass_lbl, error_lbl, title_lbl;
	private TextField name_tf;
	private PasswordField pass_pf;
	private Button login_btn;
	
	
	public LoginPage() {
		this.title = "Internet Clafes - Login";
	}

	@Override
	protected void init() {
		gp = new GridPane();
		vb = new VBox();
		
		title_lbl = new Label("Login");
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
		
		vb.getChildren().add(title_lbl);
		vb.getChildren().add(gp);
		vb.getChildren().add(login_btn);
		vb.getChildren().add(error_lbl);
		
		mainFrame.setCenter(vb);
	}

	@Override
	protected void setStyle() {
		VBox.setMargin(login_btn, new Insets(20));
		VBox.setMargin(title_lbl, new Insets(30));
		
		gp.setAlignment(Pos.CENTER);
		vb.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(20);
		
		
		title_lbl.setFont(Font.font("Sans",FontWeight.BOLD,32));
		name_lbl.setFont(Font.font(null,FontWeight.BOLD,16));
		pass_lbl.setFont(Font.font(null,FontWeight.BOLD,16));
		name_tf.setMinSize(200, 32);
		name_tf.setFont(Font.font(16));
		pass_pf.setMinSize(200, 32);
		pass_pf.setFont(Font.font(null,FontWeight.BOLD,16));
		
		login_btn.setCursor(Cursor.HAND);
		
		error_lbl.setStyle("-fx-text-fill: red;" + "-fx-font-weight:bold;");
		login_btn.setStyle("-fx-background-color: blue;" + "-fx-font-size: 14;" + "-fx-text-fill: white;" + "-fx-min-width: 300px;" + "-fx-min-height: 32px;" + "-fx-font-weight: bold;");
	}

	@Override
	protected void setAction() {
	
		this.login_btn.setOnAction(e->{
			String username = name_tf.getText().toString();
			String password = pass_pf.getText().toString();

			if(username.isBlank()) {
				error_lbl.setText("Username cannot be empty");
				return;
			} 
			
			if(password.isBlank()) {
				error_lbl.setText("Password cannot be empty");
				return;
			}
			
			User user = UserController.GetUserData(username, password);
			
			if(user != null) {
				StageManager.getInstance().setUser(user);
				if(user.getUserRole().equals("Admin")) {
					StageManager.getInstance().setPage(new ManagePC());
				} else {
					StageManager.getInstance().setPage(new ViewAllPC());					
				}
				
			} else {
				error_lbl.setText("Invalid username or password!");
			}
		});

	}

}

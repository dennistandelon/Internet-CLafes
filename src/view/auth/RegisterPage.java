package view.auth;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import view.Page;

public class RegisterPage extends Page{

	private GridPane gp;
	private Label name_lbl, pass_lbl, confirm_lbl, age_lbl, error_lbl, title_lbl;
	private TextField name_tf;
	private PasswordField pass_pf, confirm_pf;
	private Spinner<Integer> age_sp;
	private Button regis_btn;
	private VBox vb;
	
	public RegisterPage() {
		this.title = "Internet CLafes - Register";
	}

	@Override
	protected void init() {
		gp = new GridPane();
		vb = new VBox();
		
		title_lbl = new Label("Register");
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
		
		vb.getChildren().add(title_lbl);
		vb.getChildren().add(gp);
		vb.getChildren().add(regis_btn);
		vb.getChildren().add(error_lbl);
		
		mainFrame.setCenter(vb);
	}

	@Override
	protected void setStyle() {
		VBox.setMargin(regis_btn, new Insets(20));
		VBox.setMargin(title_lbl, new Insets(30));
		
		gp.setAlignment(Pos.CENTER);
		vb.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(20);
		
		
		title_lbl.setFont(Font.font("Sans",FontWeight.BOLD,32));
		name_lbl.setFont(Font.font(null,FontWeight.BOLD,16));
		pass_lbl.setFont(Font.font(null,FontWeight.BOLD,16));
		confirm_lbl.setFont(Font.font(null,FontWeight.BOLD,16));
		age_lbl.setFont(Font.font(null,FontWeight.BOLD,16));
		name_tf.setMinSize(200, 32);
		name_tf.setFont(Font.font(16));
		pass_pf.setMinSize(200, 32);
		pass_pf.setFont(Font.font(16));
		confirm_pf.setMinSize(200, 32);
		confirm_pf.setFont(Font.font(16));
		age_sp.setMinSize(200, 36);
		
		regis_btn.setCursor(Cursor.HAND);
		
		error_lbl.setStyle("-fx-text-fill: red;" + "-fx-font-weight:bold;");
		regis_btn.setStyle("-fx-background-color: blue;" + "-fx-font-size: 14;" + "-fx-text-fill: white;" + "-fx-min-width: 355px;" + "-fx-min-height: 32px;" + "-fx-font-weight: bold;");
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

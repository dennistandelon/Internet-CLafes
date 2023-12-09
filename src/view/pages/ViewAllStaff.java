package view.pages;

import java.util.Vector;

import controller.UserController;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.User;
import view.Page;

public class ViewAllStaff extends Page {

	private TableView<User> staffs;
	private TableColumn<User, Integer> idCol;
	private TableColumn<User, String> nameCol, ageCol, roleCol;
	
	private VBox vb;
	private GridPane gp;
	private Label role_lbl, change_lbl, error_lbl;
	private ComboBox<String> role_cb;
	private Button change_btn;
	
	public ViewAllStaff() {
		this.title = "Internet Clafes - View All Staff";
	}
	
	@Override
	protected void init() {
		gp = new GridPane();
		vb = new VBox();
		role_lbl = new Label("New Role");
		change_lbl = new Label("Change Role, Selected Staff ID: -");
		role_cb = new ComboBox<String>();
		change_btn = new Button("Change");
		error_lbl = new Label();
		
		staffs = new TableView<User>();

		idCol = new TableColumn<User,Integer>("Staff ID");
		idCol.setCellValueFactory(new PropertyValueFactory<User,Integer>("UserID"));
		
		nameCol = new TableColumn<User,String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<User,String>("UserName"));
		
		ageCol = new TableColumn<User,String>("Age");
		ageCol.setCellValueFactory(new PropertyValueFactory<User,String>("UserAge"));
		
		roleCol = new TableColumn<User, String>("Staff Role");
		roleCol.setCellValueFactory(new PropertyValueFactory<User,String>("UserRole"));

	}

	@Override
	protected void setLayout() {
		
		role_cb.getItems().addAll("Customer","Operator","Computer Technician","Admin");
		
		gp.add(role_lbl, 0, 0);
		gp.add(role_cb, 1, 0);
		gp.add(change_btn, 0, 1);
		gp.add(error_lbl, 0, 2);
		
		
		vb.getChildren().add(change_lbl);
		vb.getChildren().add(gp);
		
		staffs.getColumns().add(idCol);
		staffs.getColumns().add(nameCol);
		staffs.getColumns().add(ageCol);
		staffs.getColumns().add(roleCol);

		refreshTable();
		
		mainFrame.setCenter(staffs);
		mainFrame.setBottom(vb);
	}

	@Override
	protected void setStyle() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setAction() {
		staffs.setOnMouseClicked(e->{
			int index = staffs.getSelectionModel().getSelectedIndex();
			int staffid = staffs.getItems().get(index).getUserID();
			change_lbl.setText("Change Role, Selected Staff ID: " + staffid);
		});
		
		change_btn.setOnAction(e->{
			int index = staffs.getSelectionModel().getSelectedIndex();
			int staffid = staffs.getItems().get(index).getUserID();
			
			error_lbl.setText(UserController.ChangeUserRole(staffid, role_cb.getValue()));
			refreshTable();
		});

	}

	private void refreshTable() {
		staffs.getItems().clear();
		
		// Set staff table view with staff data (Computer Technician | Admin | Operator) 
		Vector<User> allStaffdata = new Vector<User>();
				
		for (User user : UserController.GetAllUserData()) {
			if(!user.getUserRole().equals("Customer")) {
				allStaffdata.add(user);
			}
		}
				
		staffs.getItems().addAll(allStaffdata);
	}
}

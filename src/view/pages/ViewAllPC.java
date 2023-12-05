package view.pages;

import controller.PCController;
import controller.UserController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.PC;
import model.User;
import util.StageManager;
import view.Page;
import view.component.Navbar;

public class ViewAllPC extends Page{

	private TableView<PC> pcs;
	private TableColumn<PC, String> idCol, statusCol;

	private BorderPane bp;
	
	// Admin dependency
	private Label id_lbl;
	private TextField id_tf;
	private Button insert_btn;
	private GridPane gp;
	
	public ViewAllPC() {
		this.title = "Internet Clafes - Home";
	}
	
	public void setUser(User user) {
		currentUser = user;
		
		Navbar navbar = new Navbar(currentUser);
		
		bp.setTop(navbar);

		if(currentUser.getUserRole().equals("Admin")) {
			AdminSetup();
		}
		
	}
	
	private void AdminSetup() {
		// Initialization
		gp = new GridPane();
		id_lbl = new Label("PC ID");
		id_tf = new TextField("");
		insert_btn = new Button("Add");
		
		// Layout
		gp.add(id_lbl, 0, 0);
		gp.add(id_tf, 1, 0);
		gp.add(insert_btn, 2, 0);
		
		bp.setCenter(gp);
		bp.setBottom(pcs);
		
		// Action
		this.insert_btn.setOnMouseClicked(e->{
			String msg = PCController.AddNewPC(id_tf.getText().toString());
			if(msg.toLowerCase().equals("success")) {				
				StageManager.getInstance().setPage(new ViewAllPC(), UserController.GetUserData(currentUser.getUserName(), currentUser.getUserPassword()));
			}
		});
	}
	
	@Override
	protected void init() {
		
		pcs = new TableView<PC>();
		bp = new BorderPane();

		idCol = new TableColumn<PC,String>("PC ID");
		idCol.setCellValueFactory(new PropertyValueFactory<PC,String>("PC_ID"));
		
		statusCol = new TableColumn<PC,String>("Status");
		statusCol.setCellValueFactory(new PropertyValueFactory<PC,String>("PC_Condition"));
	}

	@Override
	protected void setLayout() {
		
		pcs.getColumns().add(idCol);
		pcs.getColumns().add(statusCol);
		
		pcs.getItems().addAll(PC.GetAllPCData());
		bp.setCenter(pcs);
		
		this.scene.setRoot(bp);
	}

	@Override
	protected void setStyle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setAction() {
		
	}

}

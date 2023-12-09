package view.pages;

import controller.PCController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.PC;
import util.StageManager;
import view.Page;

public class ManagePC extends Page {

	private TableView<PC> pcs;
	private TableColumn<PC, String> idCol, statusCol;

	private Label id_lbl;
	private TextField id_tf;
	private Button insert_btn;
	private GridPane gp;
	

	public ManagePC() {
		this.title = "Internet Clafes - Home";
	}
	
	@Override
	protected void init() {
		gp = new GridPane();
		id_lbl = new Label("PC ID");
		id_tf = new TextField("");
		insert_btn = new Button("Add");
		
		gp.add(id_lbl, 0, 0);
		gp.add(id_tf, 1, 0);
		gp.add(insert_btn, 2, 0);
		
		
		pcs = new TableView<PC>();

		idCol = new TableColumn<PC,String>("PC ID");
		idCol.setCellValueFactory(new PropertyValueFactory<PC,String>("PC_ID"));
		
		statusCol = new TableColumn<PC,String>("Status");
		statusCol.setCellValueFactory(new PropertyValueFactory<PC,String>("PC_Condition"));
	}

	@Override
	protected void setLayout() {
		

		mainFrame.setCenter(gp);
		mainFrame.setBottom(pcs);
		
		pcs.getColumns().add(idCol);
		pcs.getColumns().add(statusCol);
		
		pcs.getItems().addAll(PCController.GetAllPCData());
	}

	@Override
	protected void setStyle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setAction() {
		this.insert_btn.setOnMouseClicked(e->{
			String msg = PCController.AddNewPC(id_tf.getText().toString());
			if(msg.toLowerCase().equals("success")) {				
				pcs.getItems().clear();
				pcs.getItems().addAll(PCController.GetAllPCData());
			}
		});
		
		pcs.setOnMouseClicked(e->{
			int index = pcs.getSelectionModel().getSelectedIndex();
			if(index >= 0 && currentUser.getUserRole().equals("Admin")) {
				String pcid = pcs.getItems().get(index).getPC_ID();
				StageManager.getInstance().setPage(new ViewPCDetail(pcid));
			}
		});
	}

}

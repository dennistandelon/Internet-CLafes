package view.pages;

import controller.PCController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.PC;
import view.Page;

public class ViewAllPC extends Page{

	private TableView<PC> pcs;
	private TableColumn<PC, String> idCol, statusCol;
	
	public ViewAllPC() {
		this.title = "Internet Clafes - Home";
	}
	
	@Override
	protected void init() {
		
		pcs = new TableView<PC>();

		idCol = new TableColumn<PC,String>("PC ID");
		idCol.setCellValueFactory(new PropertyValueFactory<PC,String>("PC_ID"));
		
		statusCol = new TableColumn<PC,String>("Status");
		statusCol.setCellValueFactory(new PropertyValueFactory<PC,String>("PC_Condition"));
	}

	@Override
	protected void setLayout() {
		
		pcs.getColumns().add(idCol);
		pcs.getColumns().add(statusCol);
		
		pcs.getItems().addAll(PCController.GetAllPCData());
		
		mainFrame.setCenter(pcs);
	}

	@Override
	protected void setStyle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setAction() {
		
	}

}

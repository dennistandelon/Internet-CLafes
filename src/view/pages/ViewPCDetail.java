package view.pages;

import controller.PCController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.PC;
import util.StageManager;
import view.Page;

public class ViewPCDetail extends Page {

	private Label id_lbl, condition_lbl, new_con_lbl, error_lbl;
	private TextField condition_tf;
	private Button update_btn, delete_btn;
	private PC data;
	
	private GridPane gp;
	private VBox vb;
	
	/*
	 * Page used for admin to update and delete specific PC
	 * */
	
	public ViewPCDetail(String id) {
		super();
		this.title = "Internet Clafes - PC Details @" + id;
		refreshData(id);
		
		delete_btn.setOnAction(e->{
			PCController.DeletePC(id);
			StageManager.getInstance().setPage(new ManagePC());			
		});
		
		update_btn.setOnAction(e->{
			error_lbl.setText(PCController.UpdatePCCondition(id, condition_tf.getText()));
			if(error_lbl.getText().equals("success")) StageManager.getInstance().setPage(new ManagePC());	
		});
	}

	@Override
	protected void init() {
		id_lbl = new Label("PC ID: ");
		condition_lbl = new Label("PC Condition: ");
		
		new_con_lbl = new Label("New Condition");
		condition_tf = new TextField();
		
		update_btn = new Button("Update");
		delete_btn = new Button("Delete");
		
		error_lbl = new Label();
		
		gp = new GridPane();
		vb = new VBox();
	}

	@Override
	protected void setLayout() {
		
		gp.add(new_con_lbl, 0, 0);
		gp.add(condition_tf, 1, 0);
		gp.add(update_btn, 0, 1);
		gp.add(delete_btn, 1, 1);
		
		vb.getChildren().addAll(id_lbl, condition_lbl, gp,error_lbl);
		
		mainFrame.setCenter(vb);
	}

	@Override
	protected void setStyle() {
		error_lbl.setStyle("-fx-text-fill: red;" + "-fx-font-weight:bold;");
	}

	@Override
	protected void setAction() {

	}
	
	private void refreshData(String id) {
		data = PCController.GetPCDetail(id);
		id_lbl.setText("PC ID: " + data.getPC_ID());
		condition_lbl.setText("PC Condition: "+data.getPC_Condition());
	}

}

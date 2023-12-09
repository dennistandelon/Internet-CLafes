package view.pages;

import controller.PCController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.PC;
import util.StageManager;
import view.Page;

public class ViewPCDetail extends Page {

	private Label id_lbl, condition_lbl, new_con_lbl, update_lbl;
	private TextField condition_tf;
	private Button update_btn, delete_btn, open_btn;
	private PC data;
	
	private GridPane gp;
	private VBox vb;
	private HBox button_list;
	
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
			PCController.UpdatePCCondition(id, condition_tf.getText());
			StageManager.getInstance().setPage(new ManagePC());	
		});
	}

	@Override
	protected void init() {
		id_lbl = new Label("PC ID: ");
		condition_lbl = new Label("PC Condition: ");
		
		new_con_lbl = new Label("New Condition");
		condition_tf = new TextField();
		update_lbl = new Label("Update PC Condition");
		
		update_btn = new Button("Update");
		open_btn = new Button("Make Update");
		delete_btn = new Button("Delete");
		
		button_list = new HBox();
		
		gp = new GridPane();
		vb = new VBox();
	}

	@Override
	protected void setLayout() {
		
		gp.add(update_lbl, 0, 0);
		gp.add(new_con_lbl, 0, 1);
		gp.add(condition_tf, 1, 1);
		gp.add(update_btn, 0, 2);
		
		button_list.getChildren().addAll(open_btn,delete_btn);
		vb.getChildren().addAll(id_lbl, condition_lbl, button_list);
		
		mainFrame.setCenter(vb);
	}

	@Override
	protected void setStyle() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setAction() {
		open_btn.setOnMouseClicked(e->{
			mainFrame.setBottom(gp);
		});

	}
	
	private void refreshData(String id) {
		data = PCController.GetPCDetail(id);
		id_lbl.setText("PC ID: " + data.getPC_ID());
		condition_lbl.setText("PC Condition: "+data.getPC_Condition());
	}

}

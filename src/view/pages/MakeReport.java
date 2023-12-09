package view.pages;

import controller.ReportController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import util.StageManager;
import view.Page;

public class MakeReport extends Page {

	private Label pc_lbl, note_lbl, error_lbl;
	private TextField pc_tf, note_tf;
	private Button submit_btn;
	
	private GridPane gp;
	
	public MakeReport() {
		this.title = "Internet Clafes - Make Report";
	}

	@Override
	protected void init() {
		gp = new GridPane();

		pc_lbl = new Label("PC ID");
		note_lbl = new Label("Report Note");
		error_lbl = new Label();
		
		pc_tf = new TextField();
		note_tf = new TextField();
		
		submit_btn = new Button("Submit");
	}

	@Override
	protected void setLayout() {
		gp.add(pc_lbl, 0, 0);
		gp.add(pc_tf, 1, 0);
		gp.add(note_lbl, 0, 1);
		gp.add(note_tf, 1, 1);
		gp.add(submit_btn, 0, 2);
		gp.add(error_lbl, 0, 3);
		
		mainFrame.setCenter(gp);
	}

	@Override
	protected void setStyle() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setAction() {
		submit_btn.setOnMouseClicked(e->{
			String PcID = this.pc_tf.getText();
			String ReportNote = this.note_tf.getText();
			error_lbl.setText(ReportController.AddNewReport(StageManager.getInstance().getUser().getUserRole(), PcID, ReportNote));
		});

	}

}

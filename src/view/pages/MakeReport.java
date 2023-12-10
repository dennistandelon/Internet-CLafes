package view.pages;

import controller.ReportController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import util.StageManager;
import view.Page;

public class MakeReport extends Page {

	private Label pc_lbl, note_lbl, error_lbl;
	private TextField pc_tf, note_tf;
	private Button submit_btn;
	
	private GridPane gp;
	private VBox vb;
	
	public MakeReport() {
		this.title = "Internet Clafes - Make Report";
	}

	@Override
	protected void init() {
		gp = new GridPane();
		vb = new VBox();

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

		vb.getChildren().add(gp);
		vb.getChildren().add(submit_btn);
		vb.getChildren().add(error_lbl);
		
		mainFrame.setCenter(vb);
	}

	@Override
	protected void setStyle() {
		VBox.setMargin(submit_btn, new Insets(20));
		
		gp.setAlignment(Pos.CENTER);
		vb.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(20);
		
		
		pc_lbl.setFont(Font.font(null,FontWeight.BOLD,16));
		note_lbl.setFont(Font.font(null,FontWeight.BOLD,16));
		pc_tf.setMinSize(200, 32);
		pc_tf.setFont(Font.font(16));
		note_tf.setMinSize(200, 32);
		note_tf.setFont(Font.font(null,FontWeight.BOLD,16));
		
		submit_btn.setCursor(Cursor.HAND);
		
		error_lbl.setStyle("-fx-text-fill: red;" + "-fx-font-weight:bold;");

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

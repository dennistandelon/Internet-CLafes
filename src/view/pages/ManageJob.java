package view.pages;

import controller.JobController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.Job;
import view.Page;

public class ManageJob extends Page {

	private TableView<Job> pcs;
	private TableColumn<Job, String> idCol, pcCol, statusCol, techCol;
	
	private Label pc_lbl, user_lbl, add_lbl, add_error_lbl, update_lbl, update_error_lbl, job_lbl, status_lbl;
	private TextField user_tf, pc_tf, job_tf, status_tf;
	private Button add_btn, update_btn;
	
	private HBox hb;
	private GridPane add_gp, update_gp;
	private VBox left_vb, right_vb;
	private Region filler1,filler2,filler3;

	public ManageJob() {
		this.title = "Internet Clafes - Manage Job";
	}

	@Override
	protected void init() {
		add_lbl = new Label("Add New Job");
		update_lbl = new Label("Update Job Status");
		pc_lbl = new Label("PC ID");
		user_lbl = new Label("User ID");
		job_lbl = new Label("Job ID");
		status_lbl = new Label("New Status");
		add_error_lbl = new Label();
		update_error_lbl = new Label();
		
		pc_tf = new TextField();
		user_tf = new TextField();
		job_tf = new TextField();
		status_tf = new TextField();
		
		filler1 = new Region();
		filler2 = new Region();
		filler3 = new Region();
		
		add_btn = new Button("Add");
		update_btn = new Button("Update");
		
		hb = new HBox();
		add_gp = new GridPane();
		update_gp = new GridPane();
		left_vb = new VBox();
		right_vb = new VBox();
		
		pcs = new TableView<Job>();
		idCol = new TableColumn<Job, String>("Job ID");
		idCol.setCellValueFactory(new PropertyValueFactory<Job, String>("JobID"));
		
		pcCol = new TableColumn<Job, String>("PC ID");
		pcCol.setCellValueFactory(new PropertyValueFactory<Job, String>("PC_ID"));
		
		statusCol = new TableColumn<Job, String>("Status");
		statusCol.setCellValueFactory(new PropertyValueFactory<Job, String>("JobStatus"));
		
		techCol = new TableColumn<Job, String>("Technician ID");
		techCol.setCellValueFactory(new PropertyValueFactory<Job, String>("UserID"));
		
	}

	@Override
	protected void setLayout() {
		
		add_gp.add(pc_lbl, 0, 0);
		add_gp.add(pc_tf, 1, 0);
		add_gp.add(user_lbl, 0, 1);
		add_gp.add(user_tf, 1, 1);
		left_vb.getChildren().add(add_lbl);
		left_vb.getChildren().add(add_gp);
		left_vb.getChildren().add(add_btn);
		left_vb.getChildren().add(add_error_lbl);
		
		update_gp.add(job_lbl, 0, 0);
		update_gp.add(job_tf, 1, 0);
		update_gp.add(status_lbl, 0, 1);
		update_gp.add(status_tf, 1, 1);
		right_vb.getChildren().add(update_lbl);
		right_vb.getChildren().add(update_gp);
		right_vb.getChildren().add(update_btn);
		right_vb.getChildren().add(update_error_lbl);
		
		hb.getChildren().addAll(filler1,left_vb,filler2,right_vb,filler3);
		
		pcs.getColumns().add(idCol);
		pcs.getColumns().add(pcCol);
		pcs.getColumns().add(statusCol);
		pcs.getColumns().add(techCol);
		
		pcs.getItems().addAll(JobController.GetAllJobData());
		
		mainFrame.setCenter(pcs);
		mainFrame.setBottom(hb);
	}

	@Override
	protected void setStyle() {
		idCol.prefWidthProperty().bind(pcs.widthProperty().multiply(0.25));
		pcCol.prefWidthProperty().bind(pcs.widthProperty().multiply(0.25));
		statusCol.prefWidthProperty().bind(pcs.widthProperty().multiply(0.25));
		techCol.prefWidthProperty().bind(pcs.widthProperty().multiply(0.25));
		
		HBox.setHgrow(filler1, Priority.ALWAYS);
		HBox.setHgrow(filler2, Priority.ALWAYS);
		HBox.setHgrow(filler3, Priority.ALWAYS);
		HBox.setMargin(left_vb, new Insets(20));
		HBox.setMargin(right_vb, new Insets(20));
		
		VBox.setMargin(add_btn, new Insets(10));
		VBox.setMargin(update_btn, new Insets(10));
		
		add_gp.setAlignment(Pos.CENTER);
		left_vb.setAlignment(Pos.CENTER);
		update_gp.setAlignment(Pos.CENTER);
		right_vb.setAlignment(Pos.CENTER);
		
		add_gp.setHgap(2);
		update_gp.setHgap(2);

		add_btn.setCursor(Cursor.HAND);
		update_btn.setCursor(Cursor.HAND);
		
		update_error_lbl.setStyle("-fx-text-fill: red;" + "-fx-font-weight:bold;");
		add_error_lbl.setStyle("-fx-text-fill: red;" + "-fx-font-weight:bold;");
	}

	@Override
	protected void setAction() {
		add_btn.setOnAction(event->{
			int userid = 0;
			try {				
				userid = Integer.parseInt(user_tf.getText());
			} catch (Exception e) {
				add_error_lbl.setText("User ID must be number!");
				return;
			}
			add_error_lbl.setText(JobController.AddNewJob(userid, pc_tf.getText()));
			refreshTable();
		});
		
		update_btn.setOnAction(event->{
			int jobid = 0;
			try {				
				jobid = Integer.parseInt(job_tf.getText());
			} catch (Exception e) {
				update_error_lbl.setText("Job ID must be number!");
				return;
			}
			update_error_lbl.setText(JobController.UpdateJobStatus(jobid, status_tf.getText()));
			refreshTable();
		});

	}
	
	private void refreshTable() {
		pcs.getItems().clear();
		pcs.getItems().addAll(JobController.GetAllJobData());
	}

}

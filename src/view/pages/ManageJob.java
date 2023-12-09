package view.pages;

import controller.JobController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
		
		
		add_btn = new Button("Add");
		update_btn = new Button("Update");
		
		hb = new HBox();
		add_gp = new GridPane();
		update_gp = new GridPane();
		
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
		
		add_gp.add(add_lbl, 0, 0);
		add_gp.add(pc_lbl, 0, 1);
		add_gp.add(pc_tf, 1, 1);
		add_gp.add(user_lbl, 0, 2);
		add_gp.add(user_tf, 1, 2);
		add_gp.add(add_btn, 0, 3);
		add_gp.add(add_error_lbl, 0, 4);
		
		update_gp.add(update_lbl, 0, 0);
		update_gp.add(job_lbl, 0, 1);
		update_gp.add(job_tf, 1, 1);
		update_gp.add(status_lbl, 0, 2);
		update_gp.add(status_tf, 1, 2);
		update_gp.add(update_btn, 0, 3);
		update_gp.add(update_error_lbl, 0, 4);
		
		hb.getChildren().addAll(add_gp,update_gp);
		
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
		// TODO Auto-generated method stub

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

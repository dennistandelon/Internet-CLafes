package view.pages;

import controller.JobController;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Job;
import view.Page;

public class ViewTechnicianJob extends Page {

	private TableView<Job> pcs;
	private TableColumn<Job, String> idCol, statusCol, jidCol;
	private TableColumn<Job, Void> buttonCol;
	
	public ViewTechnicianJob() {
		this.title = "Internet Clafes - Technician Jobs";
	}

	@Override
	protected void init() {
		pcs = new TableView<Job>();

		jidCol = new TableColumn<Job,String>("PC ID");
		jidCol.setCellValueFactory(new PropertyValueFactory<Job,String>("PC_ID"));
		
		idCol = new TableColumn<Job,String>("Job ID");
		idCol.setCellValueFactory(new PropertyValueFactory<Job,String>("JobID"));		
		
		statusCol = new TableColumn<Job,String>("Status");
		statusCol.setCellValueFactory(new PropertyValueFactory<Job,String>("JobStatus"));
		
		buttonCol = new TableColumn<Job, Void>("Action");
		buttonCol.setCellFactory(new Callback<TableColumn<Job,Void>, TableCell<Job,Void>>() {
			
			@Override
			public TableCell<Job,Void> call(TableColumn<Job, Void> param) {
				
				return new TableCell<Job, Void>() {
					private final Button button = new Button("Complete Job");

					{
						button.setOnAction(e->{
							Job jobdata = getTableView().getItems().get(getIndex());
							
							JobController.UpdateJobStatus(jobdata.getJobID(), "Complete");
							refreshTable();
						});
					}
					
					@Override
					protected void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                        	Job jobdata = getTableView().getItems().get(getIndex());
                        	if(jobdata.getJobStatus().equals("UnComplete")) {                        		
                        		setGraphic(button);
                        	} else {
                        		setGraphic(null);
                        	}
                        }
					}
				};
			}
		});
	}

	@Override
	protected void setLayout() {
		pcs.getColumns().add(jidCol);
		pcs.getColumns().add(idCol);
		pcs.getColumns().add(statusCol);
		pcs.getColumns().add(buttonCol);
		
		pcs.getItems().addAll(JobController.GetTechnicianJob(currentUser.getUserID()));
		mainFrame.setCenter(pcs);
	}

	@Override
	protected void setStyle() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setAction() {
		// TODO Auto-generated method stub

	}
	
	private void refreshTable() {
		pcs.getItems().clear();
		pcs.getItems().addAll(JobController.GetTechnicianJob(currentUser.getUserID()));
	}

}

package view.pages;

import controller.PCController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.PC;
import util.StageManager;
import view.Page;

public class ManagePC extends Page {

	private TableView<PC> pcs;
	private TableColumn<PC, String> idCol, statusCol;

	private Label id_lbl, error_lbl, insert_lbl;
	private TextField id_tf;
	private Button insert_btn;
	private GridPane gp;
	
	private VBox vb;

	public ManagePC() {
		this.title = "Internet Clafes - Home";
	}
	
	@Override
	protected void init() {
		vb = new VBox();
		gp = new GridPane();
		id_lbl = new Label("PC ID");
		error_lbl = new Label();
		insert_lbl = new Label("Insert new PC");
		id_tf = new TextField("");
		insert_btn = new Button("Add");
		
		pcs = new TableView<PC>();

		idCol = new TableColumn<PC,String>("PC ID");
		idCol.setCellValueFactory(new PropertyValueFactory<PC,String>("PC_ID"));
		
		statusCol = new TableColumn<PC,String>("Status");
		statusCol.setCellValueFactory(new PropertyValueFactory<PC,String>("PC_Condition"));
	}

	@Override
	protected void setLayout() {
		
		
		gp.add(id_lbl, 0, 0);
		gp.add(id_tf, 1, 0);
		gp.add(insert_btn, 2, 0);
		
		vb.getChildren().add(insert_lbl);
		vb.getChildren().add(gp);
		vb.getChildren().add(error_lbl);
		
		pcs.getColumns().add(idCol);
		pcs.getColumns().add(statusCol);
		
		pcs.getItems().addAll(PCController.GetAllPCData());

		mainFrame.setCenter(vb);
		mainFrame.setBottom(pcs);
	}

	@Override
	protected void setStyle() {
		idCol.prefWidthProperty().bind(pcs.widthProperty().multiply(0.5));
        statusCol.prefWidthProperty().bind(pcs.widthProperty().multiply(0.5));

        vb.setAlignment(Pos.CENTER);
        gp.setAlignment(Pos.CENTER);
		insert_lbl.setFont(Font.font(14));
		gp.setHgap(10);
		
		error_lbl.setStyle("-fx-text-fill: red;" + "-fx-font-weight:bold;");
		
	}

	@Override
	protected void setAction() {
		this.insert_btn.setOnMouseClicked(e->{
			error_lbl.setText(PCController.AddNewPC(id_tf.getText().toString()));
			pcs.getItems().clear();
			pcs.getItems().addAll(PCController.GetAllPCData());
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

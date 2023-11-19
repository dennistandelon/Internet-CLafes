package view.pages;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import model.PC;
import model.User;
import view.Navbar;
import view.Page;

public class ViewAllPC extends Page {

	private User currentUser;
	private TableView<PC> pcs;
	private BorderPane bp;
	
	public ViewAllPC() {
		this.title = "Internet Clafes - Home";
	}
	
	public void setNavbar(User user) {
		currentUser = user;
		
		Navbar navbar = new Navbar(currentUser);
		navbar.setComponent();
		
		bp.setTop(navbar);
	}
	
	@Override
	protected void init() {
		
		pcs = new TableView<PC>();
		bp = new BorderPane();
	}

	@Override
	protected void setLayout() {
		
		bp.setCenter(pcs);
		
		this.scene = new Scene(bp,500,500);
	}

	@Override
	protected void setStyle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setAction() {
		// TODO Auto-generated method stub
		
	}

}

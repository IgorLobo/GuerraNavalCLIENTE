package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class TelaJogoController implements Initializable {

	@FXML
	public GridPane tabuleiro;
	
	@FXML
	public Label lb_pontos;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}


}

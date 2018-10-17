package view;

import java.net.URL;
import java.util.ResourceBundle;

import classes.Arma;
import classes.Jogo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

	public void tabuleiro() {
		int linha = 5;
		int coluna = 5;
		// int tamanho = Integer.parseInt(tfTamanho.getText());
		// Tabuleiro tab = new Tabuleiro(linha, coluna);
		// tab.iniciarEmbarcacao();
		// tab.mostrar();
		tabuleiro.setVgap(10);
		tabuleiro.setHgap(10);

		Button z[][] = new Button[linha][coluna];

		for (int i = 0; i < linha; i++) {
			for (int j = 0; j < coluna; j++) {
				z[i][j] = new Button();
				z[i][j].setId(i + "-" + j);
				z[i][j].setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						Button teste = (Button) event.getSource();
						testar(teste.getId());
					}
				});
			}
		}

		for (int i = 0; i < linha; i++) {
			for (int j = 0; j < coluna; j++) {
				tabuleiro.add(z[i][j], j, i);
			}

		}

	}
	
	private void s() {
		try {
		Arma portaAviao = new Arma("Porta Avião", 5, "");
		Arma submarino = new Arma("Porta Avião", 5, "");
		Arma cruzador = new Arma("Porta Avião", 5, "");
		Arma destroier = new Arma("Porta Avião", 5, "");
		
		
			Jogo f = new Jogo(5);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	private void testar(String s) {
		System.out.println(s);
	}

}

package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Arma;
import model.Jogo;

public class TelaJogoController implements Initializable {
//*************************ATRIBUTOS**************************************
	public static int tamanho = 8;
	private Button botoesDoTabuleiro[][];
	private Jogo jogo = null;
	
	
//*********************ELEMENTOS DA TELA**********************************
	  @FXML
	    private TextArea txa_placar;

	    @FXML
	    private Label lb_meusPontos;

	    @FXML
	    private Button btn_desistir;

	    @FXML
	    private GridPane grid_tabuleiroJogo;

	    @FXML
	    void clickBtn_desistir(ActionEvent event) {

	    }
//********************METODOS********************************************
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tabuleiro();
	}

	public void tabuleiro() {
		try {
			jogo = new Jogo(tamanho);
			grid_tabuleiroJogo.resize(calcularTamanhoDaGrid(),calcularTamanhoDaGrid());
			botoesDoTabuleiro = new Button[tamanho][tamanho];

			// For para criar o array de botoes com evento
			for (int i = 0; i < tamanho; i++) {
				for (int j = 0; j < tamanho; j++) {
					botoesDoTabuleiro[i][j] = new Button();
					botoesDoTabuleiro[i][j].setId(i + "," + j);
					botoesDoTabuleiro[i][j].setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							Button botaoOnClick = (Button) event.getSource();
							jogo.disparo(botaoOnClick.getId());
							int[] coordenadas = traduzirCoordenadas(botaoOnClick.getId());
							botoesDoTabuleiro[coordenadas[0]][coordenadas[1]].setGraphic(new ImageView(new Image(jogo.getArmaURL(coordenadas[0],coordenadas[1]),60, 60, false, false)));
						}
					});
				}
			}

			for (int i = 0; i < tamanho; i++) {
				for (int j = 0; j < tamanho; j++) {
					grid_tabuleiroJogo.add(botoesDoTabuleiro[i][j], j, i);
					botoesDoTabuleiro[i][j].setGraphic(new ImageView(new Image(jogo.getArmaURL(i,j),60, 60, false, false)));
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			e.getMessage();
		}

	}
	
	private int[] traduzirCoordenadas(String coordenadas) {
		String[] coordenadasXY = coordenadas.split(",");
		int[] resultado = new int[2];
		resultado[0] = Integer.parseInt(coordenadasXY[0]);
		resultado[1] = Integer.parseInt(coordenadasXY[1]);
		return resultado;
	}
	
	private int calcularTamanhoDaGrid() {
		int espaçamento = (10 * (tamanho - 1));
		int tamanhoDosBotoes = 60 * tamanho;
		return (espaçamento + tamanhoDosBotoes);
	}

}

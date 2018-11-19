package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Jogo;

public class TelaJogoController implements Initializable {
//*************************ATRIBUTOS**************************************
	public static int tamanho = -1;
	private Button botoesDoTabuleiro[][];
	public static Jogo jogo = null;
	private int pontos = 0;
	private String mensagemServidor = "";
	public static int porta = 5555;
	public static Socket socket = null;
	public static ObjectOutputStream objectOutputStream = null;
	public static ObjectInputStream objectInputStream = null;
	public static InputStream inputStream = null;
	public static OutputStream outputStream = null;
	public volatile static boolean statusServidor = false;

//*********************ELEMENTOS DA TELA**********************************

	@FXML
	private Label lb_meusPontos;

	@FXML
	private Button btn_desistir;

	@FXML
	private Button visorVez;

	@FXML
	private GridPane grid_tabuleiroJogo;

	@FXML
	void clickBtn_desistir(ActionEvent event) {
		
	}

//********************METODOS********************************************
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		criarTabuleiro();
		//esperarServidor();
		//vezDeJogar();
		/*visorVez.setStyle("-fx-background-color: #bf230f ;-fx-border-color: #000000; ");
		visorVez.setText("Aguarde sua vez...");
		grid_tabuleiroJogo.setDisable(true);*/

	}

	public void criarTabuleiro() {
		try {
			jogo = new Jogo(tamanho);
			grid_tabuleiroJogo.resize(calcularTamanhoDaGrid(), calcularTamanhoDaGrid());
			botoesDoTabuleiro = new Button[tamanho][tamanho];

			for (int i = 0; i < tamanho; i++) {
				for (int j = 0; j < tamanho; j++) {
					botoesDoTabuleiro[i][j] = new Button();
					botoesDoTabuleiro[i][j].setMinSize(80, 80);
					botoesDoTabuleiro[i][j].setId(i + "," + j);
					botoesDoTabuleiro[i][j].setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							Button botaoOnClick = (Button) event.getSource();
							try {								
								if (esperarServidor()) {
									pontos += jogo.disparo(botaoOnClick.getId());
									lb_meusPontos.setText(Integer.toString(pontos));
								}

							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
							}
							int[] coordenadas = traduzirCoordenadas(botaoOnClick.getId());
							botoesDoTabuleiro[coordenadas[0]][coordenadas[1]].setGraphic(new ImageView(
									new Image(jogo.getArmaURL(coordenadas[0], coordenadas[1]), 60, 60, false, false)));
						}
					});
				}
			}

			for (int i = 0; i < tamanho; i++) {
				for (int j = 0; j < tamanho; j++) {
					grid_tabuleiroJogo.add(botoesDoTabuleiro[i][j], j, i);
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
		int tamanhoDosBotoes = 80 * tamanho;
		return (espaçamento + tamanhoDosBotoes);
	}

	public boolean esperarServidor() {
		try {
			socket.setSoTimeout(100);
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			mensagemServidor = objectInputStream.readUTF();
			if (mensagemServidor.equals("true")) {			
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}

	private void vezDeJogar() {

		new Thread() {
			@Override
			public void run() {
				while (true) {

					if (mensagemServidor.equals("false")) {
						visorVez.setStyle("-fx-background-color: #bf230f ;-fx-border-color: #000000; ");
						visorVez.setText("Aguarde sua vez...");
						grid_tabuleiroJogo.setDisable(true);
					} else if (mensagemServidor.equals("true")) {
						visorVez.setStyle("-fx-background-color: #0eb719;-fx-border-color: #000000; ");
						visorVez.setText("Sua vez de jogar!");
						grid_tabuleiroJogo.setDisable(false);
					}

				}
			}
		}.start();
	}
}

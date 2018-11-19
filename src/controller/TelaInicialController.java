package controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javax.swing.JOptionPane;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TelaInicialController implements Initializable {
//------------------------ATRIBUTOS--------------------------------
	Thread comunicacao = null;
	String mensagemServidor = "";
	String nickName = "";
	ObjectOutputStream objectOutputStream = null;
	ObjectInputStream objectInputStream = null;

	
//------------------COMPONENTES DA TELA FXML-----------------------
	@FXML
	private TextField txf_ipServidor;

	@FXML
	private TextField txf_portaServidor;

	@FXML
	private Button btn_conectar;

	@FXML
	private Label lb_statusDaConexao;

	@FXML
	private TextField txf_nickName;

    @FXML
    private Button ok;
	 
//-----------------------METODOS------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
	@FXML
    void ok(ActionEvent event) {
		abrirTelaDoJogo();
    }

	@FXML
	void click_btnConectar(ActionEvent event) throws IOException {
		try {
			comunicacao = new Thread() {
				@Override
				public void run() {
					try {
					nickName = txf_nickName.getText();
					TelaJogoController.porta = Integer.parseInt(txf_portaServidor.getText());
					TelaJogoController.socket = new Socket("127.0.0.1", TelaJogoController.porta);
					TelaJogoController.statusServidor = true;
					
					TelaJogoController.objectInputStream = new ObjectInputStream(TelaJogoController.socket.getInputStream());
					TelaJogoController.objectOutputStream = new ObjectOutputStream(TelaJogoController.socket.getOutputStream());		
					
					mensagemServidor = TelaJogoController.objectInputStream.readUTF();
					TelaJogoController.tamanho = Integer.parseInt(mensagemServidor);
					
					TelaJogoController.objectOutputStream.writeUTF(nickName);
					TelaJogoController.objectOutputStream.flush();
					} catch (Exception e) {
					}
				}
			};
			comunicacao.start();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void abrirTelaDoJogo() {
		try {
			Parent root = FXMLLoader.load(this.getClass().getResource("/view/TelaJogo.fxml"));
			Scene cena = new Scene(root);
			Stage tela = new Stage();
			tela.setScene(cena);
			tela.show();
			tela.setMaximized(true);
			tela.setResizable(false);
			tela.setTitle("JOGO GUERRA NAVAL");
			tela.getIcons().add(new Image(getClass().getResourceAsStream("/images/batalhaNaval.png")));

		} catch (Exception e) {
		} 
	}

}

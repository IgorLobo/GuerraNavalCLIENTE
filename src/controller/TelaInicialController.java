package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	private TextField txf_nickName;
	 
//-----------------------METODOS------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		util.MaskTextfield.campoNumericoComPonto(txf_ipServidor);
		util.MaskTextfield.campoNumerico(txf_portaServidor);
		util.MaskTextfield.tamanhoMaximo(txf_nickName, 20);
	}
	
	@FXML
	void click_btnConectar(ActionEvent event) throws IOException {
		if(txf_ipServidor.getText().trim().isEmpty() || txf_portaServidor.getText().trim().isEmpty() || txf_nickName.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,"Preencha todos os campos para fazer a conexão!", "Campos obrigatórios não preenchidos",JOptionPane.WARNING_MESSAGE);
		}else {
		
			comunicacao = new Thread() {
				@Override
				public void run() {
					try {
					nickName = txf_nickName.getText();
					TelaJogoController.porta = Integer.parseInt(txf_portaServidor.getText());
					TelaJogoController.socket = new Socket(txf_ipServidor.getText(), TelaJogoController.porta);
					TelaJogoController.statusServidor = true;
					
					TelaJogoController.objectInputStream = new ObjectInputStream(TelaJogoController.socket.getInputStream());
					TelaJogoController.objectOutputStream = new ObjectOutputStream(TelaJogoController.socket.getOutputStream());		
					
					mensagemServidor = TelaJogoController.objectInputStream.readUTF();
					TelaJogoController.tamanho = Integer.parseInt(mensagemServidor);
					
					TelaJogoController.objectOutputStream.writeUTF(nickName);
					TelaJogoController.objectOutputStream.flush();
					
					JOptionPane.showMessageDialog(null, "Conectado com sucesso");
					Platform.runLater(new Runnable() {
					    @Override
					    public void run() {
					    	abrirTelaDoJogo();
					    }
					});
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Verifique o IP e a porta", "Servidor não encontrado", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			};
			comunicacao.start();
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
			//tela.setResizable(false);
			tela.setTitle("JOGO GUERRA NAVAL");
			tela.getIcons().add(new Image(getClass().getResourceAsStream("/images/batalhaNaval.png")));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
	}

}

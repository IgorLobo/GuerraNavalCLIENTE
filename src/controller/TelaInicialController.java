package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import application.Main;
import connection.ComunicadorTCP;
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

public class TelaInicialController implements Initializable{
//------------------COMPONENTES DA TELA FXML-----------------------
	 @FXML
	    private TextField txf_ipServidor;

	    @FXML
	    private TextField txf_portaServidor;

	    @FXML
	    private Button btn_conectar;

	    @FXML
	    private Label lb_statusDaConexao;

	    
//-----------------------------------------------------------------
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

    @FXML
    void click_btnConectar(ActionEvent event) throws IOException {    
    	
    	abrirTelaDoJogo();
    	/*
    	Scanner teclado = new Scanner(System.in);
        ComunicadorTCP cliente = new ComunicadorTCP("127.0.0.1", 7777);
        
        while(true) {
            System.out.print("Mensagem: ");
            String mensagem = teclado.nextLine();
            cliente.enviarMensagem(mensagem);
            
            String resposta = cliente.receberMensagem();
            System.out.println("Resposta: " + resposta);*/
        //}
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
			System.out.println(e.getMessage());
		}finally {
			Main.primaryStage.close();
		}
	}

}

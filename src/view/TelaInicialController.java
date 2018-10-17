package view;

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
import javafx.stage.Stage;

public class TelaInicialController implements Initializable{
//------------------COMPONENTES DA TELA FXML-----------------------
	@FXML
    private Button btn_conectar;

    @FXML
    private TextField txf_IP;

    @FXML
    private TextField txf_porta;

    @FXML
    private Label lb_statusDeConexao;

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
			Parent root = FXMLLoader.load(getClass().getResource("/view/TejaJogo.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("Guerra Naval");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setMaximized(true);
			stage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			Main.primaryStage.close();
		}
	}

}

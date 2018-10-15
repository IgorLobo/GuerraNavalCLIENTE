package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import connection.Client;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//launch(args);
		try {
			final Socket cliente = new Socket("localhost", 9999);

			// lendo mensagem do servidor
			new Thread() {
				@Override
				public void run() {
					try {
						BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
						while (true) {
							/*String msg = leitor.readLine();
							System.out.println("O servidor disse: teste" + msg);*/
						}
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Impossivel ler a mensagem do servidor", "ERRO",
								JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			}.start();

			
			// escrevendo para o servidor (sync)
			PrintWriter escritor = new PrintWriter(cliente.getOutputStream(),true);
			BufferedReader leitorTerminal = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				
				
				/*String mensagemTerminal = leitorTerminal.readLine();
				escritor.println(mensagemTerminal);*/
			}
			
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "O endereço informado é inválido", "ERRO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "O servidor pode estar inacesível", "ERRO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}
}

package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.TelaJogoController;
import model.Arma;
import model.Jogador;

public class Jogo {
	private Arma[][] tabuleiro;
	private int tamanho = -1;

	
// *******************Jogador***************
	private int id = -1;
	private String ip = "";
	private String nome = "vazio";
	private int pontos = 0;


	public Jogo(int tamanho) throws Exception {
		if (tamanho < 3)
			throw new Exception("O tamanho do tabuleiro não pode ser menor que 3.");
		this.tamanho = tamanho;
		tabuleiro = new Arma[tamanho][tamanho];
		for (int i = 0; i < tamanho; i++) {
			for (int j = 0; j < tamanho; j++) {
				tabuleiro[i][j] = new Arma();
			}
		}
	}

	public int disparo(String cordenadas) throws IOException {		
		String[]cordenadasXY = traduzir(cordenadas);		
		int linha = Integer.parseInt(cordenadasXY[0]);
		int coluna = Integer.parseInt(cordenadasXY[1]);
		if (linha > tamanho || coluna > tamanho)
			try {
				throw new Exception("Disparo fora do tabuleiro!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		
		TelaJogoController.objectOutputStream = new ObjectOutputStream(TelaJogoController.socket.getOutputStream());
		TelaJogoController.objectInputStream = new ObjectInputStream(TelaJogoController.socket.getInputStream());
		
		TelaJogoController.objectOutputStream.writeUTF(cordenadas);
		TelaJogoController.objectOutputStream.flush();
		
		String mensagemDoServidor = TelaJogoController.objectInputStream.readUTF();
		String traducao[] = mensagemDoServidor.split(",");
		
		if (traducao[0].equals("true")) {
			tabuleiro[linha][coluna].setArma(traducao[1], Integer.parseInt(traducao[2]));
			return Integer.parseInt(traducao[2]);
		}else if(traducao[0].equals("false")) {
			tabuleiro[linha][coluna].setLivre(traducao[1], Integer.parseInt(traducao[2]));
			return 0;
		}
		return 0;
	}
	
	public String[] traduzir(String jogada) {
		String[] s = jogada.split(",");
		return s;
	}
	
	
	public String getArmaURL(int linha, int coluna) {
		return tabuleiro[linha][coluna].getURLimagem();
	}

	public Arma[][] getTabuleiro() {
		return tabuleiro;
	}

	public int getTamanho() {
		return tamanho;
	}

	public int getId() {
		return id;
	}

	public String getIp() {
		return ip;
	}

	public String getNome() {
		return nome;
	}

	public int getPontos() {
		return pontos;
	}	
	
}

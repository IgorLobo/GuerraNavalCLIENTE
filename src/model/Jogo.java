package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Arma;
import model.Jogador;

public class Jogo {
	private Arma[][] tabuleiro; 
	private int tamanho = -1;
	private ArrayList<Jogador> JogadoresArrayList;
	
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
	
	public int disparo(String cordenadas) {
		String[] cordenadasXY = cordenadas.split(",");
		int linha = Integer.parseInt(cordenadasXY[0]);
		int coluna = Integer.parseInt(cordenadasXY[1]);
		if (linha > tamanho || coluna > tamanho)
			try {
				throw new Exception("Disparo fora do tabuleiro!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		if (tabuleiro[linha][coluna].getSituacao()) {
			tabuleiro[linha][coluna].destruir();
			return tabuleiro[linha][coluna].getPontos();
		}
		return 0;
	}
	
	public String getArmaURL(int linha, int coluna) {
		return tabuleiro[linha][coluna].getURLimagem();
	}
	
	public Arma[][] getTabuleiro() {
		return tabuleiro;
	}
}

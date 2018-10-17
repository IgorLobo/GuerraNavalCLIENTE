package classes;

import java.util.ArrayList;

public class Jogo {
	private Arma[][] tabuleiro; 
	private int tamanho = -1;
	private ArrayList<Jogador> JogadoresArrayList;
	
	public Jogo(int tamanho) throws Exception {
		if(tamanho < 5)throw new Exception("O tamanho do tabuleiro não pode ser menor que 5.");
		
		tabuleiro = new Arma[tamanho][tamanho];
		JogadoresArrayList = new ArrayList<Jogador>();
		}
	
	public int disparo(String cordenadas)throws Exception {
		String[] cordenadasXY = cordenadas.split("-");
		int linha = Integer.parseInt(cordenadasXY[0]);
		int coluna = Integer.parseInt(cordenadasXY[1]);
		if(linha > tamanho || coluna > tamanho)throw new Exception("Disparo fora do tabuleiro!");
		
		if(tabuleiro[linha][coluna].getSituacao()) {
			tabuleiro[linha][coluna].setSituacao(false, "ULR DA IMAGEM !!!");
			return tabuleiro[linha][coluna].getPontos();
		}
		return 0;
	}
	
	public Arma[][] getTabuleiro() {
		return tabuleiro;
	}
}

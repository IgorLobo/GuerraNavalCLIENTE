package classes;

public class Jogador {
	private int ID = -1;
	private String nome = "vazio";
	private int pontos = 0;	
	
	public Jogador(int ID, String nome) {
		this.ID = ID;
		this.nome = nome;
	}
	public int getID() {return ID;}
	public String getNome() {return nome;}
	public int getPontos() {return pontos;}

	public void adicionarPontos(int pontos) {
		this.pontos += pontos;
	}
}

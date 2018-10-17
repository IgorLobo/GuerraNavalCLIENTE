package classes;

public class Arma {
	private String nome = "vazio";
	private int pontos = -1;
	private String URLimagem = "";
	private boolean situacao = true;
	
	public Arma(String nome, int pontos , String URLimagem) {
		this.nome = nome;
		this.pontos = pontos;
		this.URLimagem = URLimagem;
	}
	
	public void setSituacao(boolean situacao, String URLimagem) {
		this.situacao = situacao;
		this.URLimagem = URLimagem;
	}
	public String getNome() {return nome;}
	
	public int getPontos() {return pontos;}
	
	public String getURLimagem() {return URLimagem;}
	
	public boolean getSituacao() {return situacao;}
}

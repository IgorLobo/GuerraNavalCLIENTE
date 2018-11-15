package model;

public class Arma {
	private String nome = "vazio";
	private int pontos = -1;
	private String URLimagem = "/images/" + "Livre" + ".png";
	private boolean situacao = true;
	
	public Arma() {

	}
	
	public void setSituacao(boolean situacao, String URLimagem) {
		this.situacao = situacao;
		this.URLimagem = URLimagem;
	}
	public String getNome() {return nome;}
	
	public int getPontos() {return pontos;}
	
	public String getURLimagem() {return URLimagem;}
	
	public boolean getSituacao() {return situacao;}
	
	public void destruir() {
		this.situacao = false;
		this.URLimagem = "/images/destruido" + this.nome + ".png";
	}
}

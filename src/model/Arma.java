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

	public String getNome() {
		return nome;
	}

	public int getPontos() {
		return pontos;
	}

	public String getURLimagem() {
		return URLimagem;
	}

	public boolean getSituacao() {
		return situacao;
	}

	public void setArma(String nome, int pontos) {
		this.nome = nome;
		this.URLimagem = "/images/destruido" + this.nome + ".png";
		this.pontos = pontos;
		this.situacao = false;
	}
	
	public void setLivre(String nome, int pontos) {
		this.nome = nome;
		this.URLimagem = "/images/" + this.nome + ".png";
		this.pontos = pontos;
		this.situacao = false;
	}
}

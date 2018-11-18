package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

public class Jogador {

	private int id = -1;
	private String ip = "";
	private String nome = "vazio";
	private int pontos = 0;
	private Socket socketJogador = null;

	public Jogador(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getPontos() {
		return pontos;
	}

	public void adicionarPontos(int pontos) {
		this.pontos += pontos;
	}
}

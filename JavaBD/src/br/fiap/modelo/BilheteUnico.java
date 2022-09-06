package br.fiap.modelo;

import java.util.Random;

import br.fiap.dao.BilheteUnicoDAO;

public class BilheteUnico {
	private int numero;
	private String cpf;
	private double saldo;
	private final static double valorPassagem = 4.4;
	
	public BilheteUnico(String cpf) {
		super();
		this.numero = gerarNumero();
		this.cpf = cpf;
	}
	
	public BilheteUnico(int numero, String cpf, double saldo) {
		super();
		this.numero = numero;
		this.cpf = cpf;
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "BilheteUnico [numero=" + numero + ", cpf=" + cpf + ", saldo=" + saldo + "]";
	}

	public int getNumero() {
		BilheteUnicoDAO dao = new BilheteUnicoDAO();
		Random gerador = new Random();
		int numero;
		
		do {
			numero = gerador.nextInt(1000, Integer.MAX_VALUE);
		} while(dao.pesquisarNumero(numero));
		
		return numero;
	}

	private int gerarNumero() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getValorpassagem() {
		return valorPassagem;
	}
	
}

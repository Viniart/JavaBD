package br.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import br.fiap.conexao.Conexao;
import br.fiap.modelo.BilheteUnico;

public class BilheteUnicoDAO {
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// método para pesquisa um usuário pelo numero do bilhete
	public boolean pesquisarNumero(int numero){
		boolean aux = false;
		connection = new Conexao().conectar();
		sql = "SELECT * FROM JAVA_BILHETE WHERE NUMERO = ?";
		
		try {			
			ps = connection.prepareStatement(sql);
			ps.setInt(1, numero);
			rs = ps.executeQuery();
			aux = rs.next();
		}
		catch(SQLException e) {
			System.out.println("Erro ao pesquisar o número do bilhete\n" + e);
		}
		
		return aux;
	}
	
	// método para pesquisa um usuário pelo numero do cpf
	public BilheteUnico pesquisarPorCpf(String cpf){
			BilheteUnico bilhete = null;
			connection = new Conexao().conectar();
			sql = "SELECT * FROM JAVA_BILHETE WHERE CPF = ?";
			
			try {			
				ps = connection.prepareStatement(sql);
				ps.setString(1, cpf);
				rs = ps.executeQuery();
				if(rs.next()) {
					int numero = rs.getInt("NUMERO");
					String cpfBanco = rs.getString("CPF");
					double saldo = rs.getDouble("SALDO");
					
					bilhete = new BilheteUnico(numero, cpfBanco, saldo);
				}
			}
			catch(SQLException e) {
				System.out.println("Erro ao pesquisar o número do bilhete\n" + e);
			}
			
			return bilhete;
		}
	
	public void inserir(BilheteUnico bilhete) {

		connection = new Conexao().conectar();
		sql = "INSERT INTO JAVA_BILHETE VALUES(?, ?, ?, ?)";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, bilhete.getNumero());
			ps.setString(2, bilhete.getCpf());
			ps.setDouble(3, bilhete.getSaldo());
			ps.setDouble(4, bilhete.getValorpassagem());
			ps.execute();
			
		}	catch(SQLException e) {
			System.out.println("Erro ao inserir o bilhete\n" + e);
		}
	}
	
	public List<BilheteUnico> listar()
	{
		ArrayList<BilheteUnico> bilhetes = new ArrayList<BilheteUnico>();
		BilheteUnico bilhete = null;
		
		connection = new Conexao().conectar();
		sql = "SELECT * FROM JAVA_BILHETE";
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				bilhete = new BilheteUnico(rs.getInt("NUMERO"), rs.getString("CPF"), rs.getDouble("SALDO"));
				
				bilhetes.add(bilhete);
			}			
			
		} catch(SQLException e) {
			System.out.println("Erro ao pesquisar bilhetes\n" + e);
		}
		
		return bilhetes;
	}
	
	// Método para carregar o bilhete
	public void carregar(String cpf, double valor) {
		connection = new Conexao().conectar();
		sql = "UPDATE JAVA_BILHETE SET SALDO = ? WHERE CPF = ?";
		
		try {
			
			ps = connection.prepareStatement(sql);
			ps.setDouble(1, valor);;
			ps.setString(2, cpf);
			ps.execute();
			
		}catch(SQLException e) {
			System.out.println("Erro ao atualizar saldo do bilhete\n" + e);
		}
		
	}
	
}

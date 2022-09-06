package br.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.fiap.conexao.Conexao;
import br.fiap.modelo.Usuario;

public class UsuarioDAO {
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// método para pesquisa um usuário pelo cpf
	public boolean pesquisarCpf(String cpf){
		boolean aux = false;
		connection = new Conexao().conectar();
		sql = "SELECT * FROM JAVA_USUARIO WHERE CPF = ?";
		
		try {			
			ps = connection.prepareStatement(sql);
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			aux = rs.next();
		}
		catch(SQLException e) {
			System.out.println("Erro ao pesquisar o cpf do usuário\n" + e);
		}
		
		return aux;
	}
	
	// método para inserir um usuário na base de dados
	public void inserir(Usuario usuario) {
		connection = new Conexao().conectar();
		sql = "INSERT INTO JAVA_USUARIO(NOME, CPF, TIPO) VALUES (?, ?, ?)";
		
		try {			
			ps = connection.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getCpf());
			ps.setString(3, usuario.getTipo());
			ps.execute();
		}
		catch(SQLException e) {
			System.out.println("Erro ao inserir dados do usuário\n" + e);
		}
				
	}
}

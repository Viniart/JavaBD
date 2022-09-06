package br.fiap.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
	private String login = "rm88402";
	private String senha = "230502";
	private Connection connection;
	
	// private String login = "rm88402";
	// private String senha = "230502";
	
	// jdbc:oracle:thin:@nomeDoHost:númeroDaPorta:serviço
	public Connection conectar() {
		
		try {
			
			Class.forName(driver);
			
			connection = DriverManager.getConnection(url, login, senha);
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("Erro ao carregar o driver");
			
		} catch (SQLException e) {
			
			System.out.println("Erro a conectar no banco de dados\n" + e);
			
		}
		
		return connection;
		
	}
	
	public void fechar() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Erro ao fechar conexao\n" + e);
		}
	}
	
}


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// -> Armazenar as operações de banco de dados para o Cliente
public class ClienteDAO {
	
	// variáveis para manipulação do banco de dados
	private Connection connection; // armazena a conexão com o banco de dados
	private PreparedStatement ps; // configurar o sql para enviar para o banco de dados
	private ResultSet rs; // armazena o resultado de um select
	private String sql; // armazenar o sql que será enviado para o banco
	
	public ClienteDAO()
	{
		connection = new Conexao().conectar();
	}
	
	public void inserir(Cliente cliente) {
		sql = "INSERT INTO JAVA_CLIENTE(id, nome) VALUES (?, ?)";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, cliente.getId()); // 1 de primeira interrogação
			ps.setString(2, cliente.getNome());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir no banco de dados " + e);
		}
	}
	
	public boolean pesquisar(int id) {
		boolean aux = false;
		sql = "SELECT * FROM JAVA_CLIENTE WHERE ID = ?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			aux = rs.next();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir no banco de dados " + e);
		}
		
		return aux;
	}
	
	public List<Cliente> listar() {
		List<Cliente> lista = new ArrayList<Cliente>();
		sql = "SELECT * FROM JAVA_CLIENTE";
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				lista.add(new Cliente(rs.getInt("id"), rs.getString("nome")));
			}
		}
		catch(SQLException e) {
			System.out.println("erro ao listar dados\n" + e);
		}
		
		return lista;
	}
}

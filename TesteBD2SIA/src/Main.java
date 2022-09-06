
public class Main {

	public static void main(String[] args) {
		
		Conexao conexao = new Conexao();
		
		if(conexao.conectar() == null) {
			
			System.out.println("Não conectou");
			
		} else {
			
			System.out.println("Conectado");
			
		}
		
		ClienteDAO dao = new ClienteDAO();
		// Cliente cliente = new Cliente(2, "Carlos");
		
		// dao.inserir(cliente);
		// Lembrar de executar o comando commit se inserir os dados
		// Direto no banco
		System.out.println(dao.listar());
		
	}

}

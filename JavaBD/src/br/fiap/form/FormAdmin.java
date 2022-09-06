package br.fiap.form;
import static javax.swing.JOptionPane.*;

import java.util.List;

import br.fiap.dao.BilheteUnicoDAO;
import br.fiap.dao.UsuarioDAO;
import br.fiap.modelo.BilheteUnico;
import br.fiap.modelo.Usuario;

import static java.lang.Integer.parseInt;

public class FormAdmin {
	public void menuAdmin() {
		int opcao = 0;
		
		do {
			try {
				opcao = parseInt(showInputDialog(gerarMenuAdmin()));
				
				switch(opcao)
				{
					case 1:
						emitirBilhete();
						break;
					case 2:
						imprimir();
						break;
					case 3:
						consultar();
						break;
				}
			}
			catch(NumberFormatException e) {
				showMessageDialog(null, "A opção deve um número entre 1 e 4!\n" + e);
			}
		} while(opcao != 4);
	}
	
	private void imprimir() {
		BilheteUnicoDAO dao = new BilheteUnicoDAO();
		List<BilheteUnico> lista = dao.listar();
		String aux = "";
		
		for (BilheteUnico bilhete : lista) {
			aux += bilhete + "\n";
		}
		showMessageDialog(null, aux);
		
	}

	private void consultar() {

		String cpf = showInputDialog("CPF do usuário");
		BilheteUnicoDAO dao = new BilheteUnicoDAO();
		BilheteUnico bilhete = dao.pesquisarPorCpf(cpf);
		if(bilhete == null)
		{
			showMessageDialog(null, "CPF não está cadastrado");
		}
		else {
			showMessageDialog(null, bilhete);
		}
		
	}

	private void emitirBilhete() {

		UsuarioDAO dao = new UsuarioDAO();
		BilheteUnicoDAO bilheteDao = new BilheteUnicoDAO();
		String cpf, nome, tipo;
		String[] opcao = {"Estudante", "Professor", "Normal"};
		
		cpf = showInputDialog("CPF usuário");
		if(dao.pesquisarCpf(cpf))
		{
			showMessageDialog(null, "Usuário já tem bilhete!");
		}
		else {
			nome = showInputDialog("Nome do usuário");
			tipo = (String) showInputDialog(null, "Tipo de Usuário", "Tipo de Usuário", 0, null, opcao, opcao[2]);
			
			dao.inserir(new Usuario(nome, cpf, tipo));
			bilheteDao.inserir(new BilheteUnico(cpf));			
		}
		
	}

	private String gerarMenuAdmin() {
		String aux = "Escolha uma operação:\n";
		
		aux += "1. Emitir Bilhete\n";
		aux += "2. Imprimir Bilhete\n";
		aux += "3. Consultar Bilhete\n";
		aux += "4. Sair\n";
		
		return aux;
	}
}

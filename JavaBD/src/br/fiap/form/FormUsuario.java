package br.fiap.form;

import static javax.swing.JOptionPane.*;

import br.fiap.dao.BilheteUnicoDAO;
import br.fiap.modelo.BilheteUnico;

public class FormUsuario {
	
	public void menuUsuario(String cpf) {
		BilheteUnicoDAO dao = new BilheteUnicoDAO();
		int opcao;
		
		do {
			opcao = Integer.parseInt(showInputDialog(gerarMenuUsuario()));
			switch(opcao) {
				case 1:
				carregar(cpf);
				break;
			}
		} while(opcao != 4);
	}
	
	private void carregar(String cpf) {

		BilheteUnicoDAO dao = new BilheteUnicoDAO();
		double valor = Double.parseDouble(showInputDialog("Valor da carga"));
		
		BilheteUnico bilhete = dao.pesquisarPorCpf(cpf);
		dao.carregar(cpf, valor + bilhete.getSaldo());
		
	}

	private String gerarMenuUsuario() {
		String menu = "Escolha uma operação:\n";
		menu += "1. Carregar bilhete\n";
		menu += "2. Passar na catraca\n";
		menu += "3. Consultar saldo\n";
		menu += "4. Sair";
		
		return menu;
	}
}

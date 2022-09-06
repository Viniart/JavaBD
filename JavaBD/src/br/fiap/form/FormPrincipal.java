package br.fiap.form;
import static javax.swing.JOptionPane.*;

import br.fiap.dao.UsuarioDAO;

public class FormPrincipal {
	public void MenuPrincipal() {
		String opcao;
		
		do {
			opcao = showInputDialog("Digite sua senha ou CPF ou Sair");
			if(opcao.equalsIgnoreCase("admin")) {
				new FormAdmin().menuAdmin();
			} else if(!opcao.equalsIgnoreCase("sair")) {
				if(new UsuarioDAO().pesquisarCpf(opcao)) {
					new FormUsuario().menuUsuario(opcao);
				}
			}
		} while(!opcao.equalsIgnoreCase("sair"));
	}
}

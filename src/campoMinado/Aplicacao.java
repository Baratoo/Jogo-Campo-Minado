package campoMinado;

import campoMinado.model.Tabuleiro;
import campoMinado.view.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 3);
		
		new TabuleiroConsole(tabuleiro);
		
	}
}

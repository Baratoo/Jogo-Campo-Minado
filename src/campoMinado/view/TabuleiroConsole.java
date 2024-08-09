package campoMinado.view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import campoMinado.control.ExplosaoException;
import campoMinado.control.SairException;
import campoMinado.model.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}
	
	//Executa, reinicia e termina o jogo
	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				loopDoJogo();
				
				System.out.println("Outra partida? (S/n)");
				String resposta = entrada.nextLine();
				
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();							
				}
			}
			
			
		} catch (SairException e) {
			System.out.println("Jogo finalizado!");
		} finally {}
		entrada.close();
	}

	//Da vida ao jogo!
	private void loopDoJogo() {
		try {
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String digitado = CapturarValorDigitado("Digite (x, y): ");
				
				//Cria uma stream, separa os valores atravez da virgula, passa eles de STRING para INT e armazena no ITERATOR
				Iterator<Integer> xy = Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e.trim())).iterator();

				digitado = CapturarValorDigitado("1 - Abrir ou 2 - Marcar ou Desmarcar");
				
				if("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				} else if ("2".equals(digitado)) {
					tabuleiro.marcar(xy.next(), xy.next());
				}
			}
			System.out.println(tabuleiro);
			System.out.println("Voce ganhou!!!");
		} catch(ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Voce perdeu!");
		}
	}
	
	//Pega o valor armazenado e retorna ele, se o valor for sair o jogo acaba
	private String CapturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		return digitado;
	}
	
	
	
}







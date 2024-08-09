package campoMinado.model;

import java.util.ArrayList;
import java.util.List;

import campoMinado.control.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;
	
	private boolean aberto;
	private boolean minado;
	private boolean marcado;
	
	private List<Campo> vizinhos = new ArrayList<>();
	
	Campo(int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
	}
	
	//Define se um campo é vizinho ou nao
	boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int diferencaLinha = Math.abs(linha - vizinho.linha);
		int diferencaColuna = Math.abs(coluna - vizinho.coluna);
		int diferencaGeral = diferencaColuna + diferencaLinha;
		
		if(diferencaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		}else if(diferencaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		}else {
			return false;
		}
	}
	
	//Marca um campo
	void alternarMarcacao() {
		if(!aberto) {
			marcado = !marcado;
		}	
	}
	
	//Opcoes para quando um campo for aberto
	boolean abrir() {
		if(!aberto && !marcado) {
			aberto = true;
			if(minado) {
				throw new ExplosaoException();
			}
			if(vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			return true;
		}else {
			return false;
		}
	}
	
	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	
	void minar() {
		minado = true;
	}
	
	public boolean isMinado() {
		return minado;
	}
	
	public boolean isMarcado() {
		return marcado;
	}
	
	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	public boolean isAberto() {
		return aberto;
	}

	public int getLinha() {
		return linha;
	}
	
	public int getColuna() {
		return coluna;
	}
	
	//O campo é seguro
	boolean objetivoAlcancado(){
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	
	//Mostra quantas minas tem na vizinhanca
	long minasNaVizinhanca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}
	
	//Reinicia o jogo
	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}
	
	//O jogo visualmente
	public String toString() {
		if(marcado) {
			return "x";
		}else if(aberto && minado) {
			return "*";
		}else if(aberto && minasNaVizinhanca() > 0) {
			return Long.toString(minasNaVizinhanca());
		}else if(aberto) {
			return " ";
		}else {
			return "?";
		}
	}
	
	
	
	
	
	
}






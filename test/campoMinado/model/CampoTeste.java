package campoMinado.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import campoMinado.control.ExplosaoException;

public class CampoTeste {

	private Campo campo;
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}
	
	@Test
	void testeVizinhoRealDistancia1() {
		Campo vizinho = new Campo(3,2);		
		boolean resultado = campo.adicionarVizinho(vizinho);		
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDiagonal() {
		Campo vizinho = new Campo(2,2);		
		boolean resultado = campo.adicionarVizinho(vizinho);		
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoFalso() {
		Campo vizinho = new Campo(2,1);		
		boolean resultado = campo.adicionarVizinho(vizinho);		
		assertFalse(resultado);
	}
	
	@Test
	void testeValorPAdraoMarcado() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}

	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
	}
	
	
}

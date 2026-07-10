package sut;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LogicTest {

    private TST<Integer> tst;

    @BeforeEach
    public void setUp() {
        tst = new TST<>();
    }

    // ==========================================
    // Requisito 3: Logic-based test coverage
    // Método escolhido: keysThatMatch (método privado collect)
    // Decisão: if (c == '.' || c == x.c)
    // Critério: Correlated Active Clause Coverage (CACC)
    // ==========================================

    @Test
    public void testClauseATrue_ClauseBFalse() {
        // Cláusula A (c == '.') é VERDADEIRA.
        // Cláusula B (c == x.c) é FALSA (pois '.' não é igual à letra 'm').
        // Resultado esperado da decisão: Verdadeiro (entra no if).
        tst.put("m", 1);
        
        Iterable<String> result = tst.keysThatMatch(".");
        
        // Como entrou no if, vai encontrar a chave "m"
        assertTrue(result.iterator().hasNext());
        assertEquals("m", result.iterator().next());
    }

    @Test
    public void testClauseAFalse_ClauseBTrue() {
        // Cláusula A (c == '.') é FALSA.
        // Cláusula B (c == x.c) é VERDADEIRA (pois 'm' é igual à letra 'm' do nó).
        // Resultado esperado da decisão: Verdadeiro (entra no if).
        tst.put("m", 1);
        
        Iterable<String> result = tst.keysThatMatch("m");
        
        // Como entrou no if, vai encontrar a chave "m"
        assertTrue(result.iterator().hasNext());
        assertEquals("m", result.iterator().next());
    }

    @Test
    public void testClauseAFalse_ClauseBFalse() {
        // Cláusula A (c == '.') é FALSA.
        // Cláusula B (c == x.c) é FALSA (pois 'a' não é igual à letra 'm' do nó).
        // Resultado esperado da decisão: Falso (NÃO entra no if central, 
        // falhando o match nesta ramificação).
        tst.put("m", 1);
        
        Iterable<String> result = tst.keysThatMatch("a");
        
        // Como não há match, não devolve nenhuma chave
        assertFalse(result.iterator().hasNext());
    }
}
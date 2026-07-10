package sut;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphTest {

    private TST<Integer> tst;

    @BeforeEach
    public void setUp() {
        tst = new TST<>();
    }

    // ==========================================
    // Requisito 2: Edge-Pair e Prime Path Coverage
    // Método: longestPrefixOf(String query)
    // ==========================================

    @Test
    public void testNullQuery() {
        // Caminho Direto: Nó 1 -> Nó 2 Fim (Exceção)
        assertThrows(IllegalArgumentException.class, () -> tst.longestPrefixOf(null));
    }

    @Test
    public void testEmptyQuery() {
        // Caminho Direto: Nó 1 -> Nó 3 -> Nó 4 Fim (Retorna null)
        assertNull(tst.longestPrefixOf(""));
    }

    @Test
    public void testZeroLoopIterations() {
        // Prime Path: Entra no método, mas o ciclo While falha logo (x == null)
        // Árvore vazia.
        assertEquals("", tst.longestPrefixOf("a"));
    }

    @Test
    public void testLoopMultipleTimesMid() {
        // Cobre percorrer o Ramo do Meio várias vezes seguidas (Edge-Pair: Mid -> Mid)
        // Cobre atualizar o length quando o nó tem valor (x.val != null) e quando não tem
        tst.put("she", 1);
        tst.put("shells", 2);
        
        // Passa por 's' (sem valor), 'h' (sem valor), 'e' (com valor), 'l' (sem valor)...
        assertEquals("shells", tst.longestPrefixOf("shellsort"));
    }

    @Test
    public void testLoopLeftAndRight() {
        // Cobre Edge-Pairs mistos: Mid -> Left, Left -> Right, Right -> Left
        // Para isso precisamos de uma árvore com ramificações laterais.
        tst.put("m", 1);   // Raiz 'm'
        tst.put("c", 2);   // Esquerda de 'm'
        tst.put("a", 3);   // Esquerda de 'c'
        tst.put("f", 4);   // Direita de 'c'
        tst.put("t", 5);   // Direita de 'm'
        tst.put("p", 6);   // Esquerda de 't'
        tst.put("z", 7);   // Direita de 't'

        // Pesquisar "a": Vai para a Esquerda ('c' < 'm'), depois Esquerda ('a' < 'c'), depois Meio (match).
        // Cobre Edge-Pair: Left -> Left -> Mid
        assertEquals("a", tst.longestPrefixOf("a"));

        // Pesquisar "z": Vai para a Direita ('z' > 'm'), depois Direita ('z' > 't'), depois Meio.
        // Cobre Edge-Pair: Right -> Right -> Mid
        assertEquals("z", tst.longestPrefixOf("z"));

        // Pesquisar "f": Vai para a Esquerda ('f' < 'm'), depois Direita ('f' > 'c'), depois Meio.
        // Cobre Edge-Pair: Left -> Right -> Mid
        assertEquals("f", tst.longestPrefixOf("f"));

        // Pesquisar "p": Vai para a Direita ('p' > 'm'), depois Esquerda ('p' < 't'), depois Meio.
        // Cobre Edge-Pair: Right -> Left -> Mid
        assertEquals("p", tst.longestPrefixOf("p"));
    }
    
    @Test
    public void testLoopExitBecauseQueryEnded() {
        // Cobre a saída do ciclo quando a query acaba, mas a árvore continua.
        // O i chega ao fim da palavra "she", mas o nó x avança para o 'l'.
        tst.put("she", 1);
        tst.put("shells", 2);
        
        assertEquals("she", tst.longestPrefixOf("she"));
    }
}
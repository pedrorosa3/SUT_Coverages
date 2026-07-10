package sut;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ISPTest {

    private TST<Integer> tst;

    @BeforeEach
    public void setUp() {
        tst = new TST<>();
    }

    // ==========================================
    // Requisito 4: Input Space Partitioning (Base Choice Coverage)
    // Método: put(String key, T val)
    // ==========================================

    @Test
    public void testBaseChoice() {
        // Base Choice (B2, B6): Trie tem prefixo , Nova chave é Típica 
        tst.put("m", 1); // Prefixo
        
        // Insere chave típica
        tst.put("mac", 2); 
        
        assertEquals(2, tst.size());
        assertEquals(Integer.valueOf(2), tst.get("mac"));
    }

    @Test
    public void testVaryStateToEmpty() {
        // Variação 1 (B1, B6): Trie Vazia , Nova chave é Típica 
        // Não inserimos nada no setUp
        
        tst.put("mac", 2); 
        
        assertEquals(1, tst.size());
        assertEquals(Integer.valueOf(2), tst.get("mac"));
    }

    @Test
    public void testVaryStateToAlreadyIncludesKey() {
        // Variação 2 (B3, B6): Trie já tem a chave , Nova chave é Típica 
        tst.put("mac", 1); // Insere a primeira vez
        
        // Insere a mesma chave com valor diferente
        tst.put("mac", 2); 
        
        // O tamanho não deve aumentar, apenas o valor é atualizado
        assertEquals(1, tst.size());
        assertEquals(Integer.valueOf(2), tst.get("mac"));
    }

    @Test
    public void testVaryKeyToSmallest() {
        // Variação 3 (B2, B4): Trie tem prefixo , Nova chave é a Mais Pequena 
        // Lexicograficamente, 'a' é o mais pequeno
        tst.put("a", 1); // Prefixo
        
        // Insere a chave lexicograficamente mais pequena ("aa" vem antes de "ab", "b", etc)
        tst.put("aa", 2); 
        
        assertEquals(2, tst.size());
        assertEquals(Integer.valueOf(2), tst.get("aa"));
    }

    @Test
    public void testVaryKeyToLargest() {
        // Variação 4 (B2, B5): Trie tem prefixo , Nova chave é a Maior 
        // Lexicograficamente, 'z' é o maior
        tst.put("z", 1); // Prefixo
        
        // Insere a chave lexicograficamente maior
        tst.put("zz", 2); 
        
        assertEquals(2, tst.size());
        assertEquals(Integer.valueOf(2), tst.get("zz"));
    }
    
    @Test
    public void testPutNullKey() {
        // Cobre a linha vermelha da exceção
        assertThrows(IllegalArgumentException.class, () -> tst.put(null, 1));
    }

    @Test
    public void testBranchingLeftAndRight() {
        // Cobre as ramificações laterais perdidas pelo ISP
        tst.put("m", 1); // Raiz no 'm'
        tst.put("a", 2); // 'a' < 'm', força a ida para a Esquerda
        tst.put("z", 3); // 'z' > 'm', força a ida para a Direita
        
        assertEquals(3, tst.size());
    }
}
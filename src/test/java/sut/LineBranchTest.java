package sut;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LineBranchTest {

    private TST<Integer> tst;

    // Este método corre antes de cada teste para garantir que tens uma árvore "limpa"
    @BeforeEach
    public void setUp() {
        tst = new TST<>();
    }

    // ==========================================
    // Testes para o método get()
    // Requisito 1: Line and Branch Coverage
    // ==========================================

    @Test
    public void testGetNullKeyThrowsException() {
        // Testa o ramo: if (key == null)
        assertThrows(IllegalArgumentException.class, () -> {
            tst.get(null);
        }, "Should throw exception for null key");
    }

    @Test
    public void testGetEmptyKeyThrowsException() {
        // Testa o ramo: if (key.length() == 0)
        assertThrows(IllegalArgumentException.class, () -> {
            tst.get("");
        }, "Should throw exception for empty key");
    }

    @Test
    public void testGetNonExistentKeyReturnsNull() {
        // Testa o ramo: if (x == null) return null;
        tst.put("banana", 1);
        assertNull(tst.get("apple"), "Should return null for key not in TST");
    }

    @Test
    public void testGetExistingKeyReturnsValue() {
        // Testa o ramo normal (encontra o nó e retorna x.val)
        tst.put("banana", 1);
        tst.put("bandana", 2); // Força a descida na árvore (c < x.c, c == x.c, etc)
        
        assertEquals(Integer.valueOf(1), tst.get("banana"));
        assertEquals(Integer.valueOf(2), tst.get("bandana"));
    }

    // ==========================================
    // Testes para o método size()
    // Requisito 1: Line and Branch Coverage
    // ==========================================
    
    @Test
    public void testSize() {
        assertEquals(0, tst.size());
        tst.put("A", 1);
        assertEquals(1, tst.size());
        tst.put("B", 2);
        assertEquals(2, tst.size());
        // Se inserirmos uma chave já existente, o tamanho não deve aumentar
        tst.put("A", 3); 
        assertEquals(2, tst.size());
    }
    
    


	//==========================================
	// Testes para o método contains()
	// Requisito 1: Line and Branch Coverage
	// ==========================================
	
	@Test
	public void testContainsNullKeyThrowsException() {
	    // Testa o ramo: if (key == null)
	    assertThrows(IllegalArgumentException.class, () -> {
	        tst.contains(null);
	    });
	}
	
	@Test
	public void testContains() {
	    assertFalse(tst.contains("apple"), "Should return false for empty tree");
	    tst.put("apple", 1);
	    assertTrue(tst.contains("apple"), "Should return true after insertion");
	    assertFalse(tst.contains("banana"), "Should return false for non-existent key");
	}
	
	//==========================================
	// Testes para o método put()
	// Requisito 1: Line and Branch Coverage
	// ==========================================
	
	@Test
	public void testPutNullKeyThrowsException() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        tst.put(null, 1);
	    });
	}
	
	@Test
	public void testPutBranches() {
	    // Cobre: x == null (criação de nós novos) e d < key.length() - 1 (x.mid)
	    tst.put("cat", 1); 
	    
	    // Cobre: c < x.c (x.left)
	    tst.put("car", 2); // O 'r' é menor que o 't' no 3º caracter
	    tst.put("bat", 3); // O 'b' é menor que o 'c' no 1º caracter
	    
	    // Cobre: c > x.c (x.right)
	    tst.put("dog", 4); // O 'd' é maior que o 'c' no 1º caracter
	    tst.put("caw", 5); // O 'w' é maior que o 't' no 3º caracter
	    
	    // Cobre: a chave já existe, apenas atualiza o valor (else -> x.val = val)
	    tst.put("cat", 10);
	    
	    assertEquals(Integer.valueOf(10), tst.get("cat"));
	    assertEquals(5, tst.size(), "Size should be 5 after adding unique keys");
	}
	
	//==========================================
	// Testes para o método longestPrefixOf()
	// Requisito 1: Line and Branch Coverage
	// ==========================================
	
	@Test
	public void testLongestPrefixOfNullThrowsException() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        tst.longestPrefixOf(null);
	    });
	}
	
	@Test
	public void testLongestPrefixOfEmptyString() {
	    assertNull(tst.longestPrefixOf(""));
	}
	
	@Test
	public void testLongestPrefixOfBranches() {
	    // Árvore vazia (cobre o x == null logo de início)
	    assertEquals("", tst.longestPrefixOf("shell"));
	
	    tst.put("she", 1);
	    tst.put("shells", 2);
	    tst.put("shellsort", 3);
	    tst.put("sho", 4); // para testar ramo x.right (o > e)
	    tst.put("sha", 5); // para testar ramo x.left (a < e)
	    
	    // Match completo
	    assertEquals("shellsort", tst.longestPrefixOf("shellsort"));
	    
	    // Match parcial (encontra "shells" mas não "shellshore")
	    assertEquals("shells", tst.longestPrefixOf("shellshore"));
	    
	    // Nenhum match
	    assertEquals("", tst.longestPrefixOf("xyz"));
	}
	
	
	//==========================================
	// Testes para os métodos keys() e keysWithPrefix()
	// Requisito 1: Line and Branch Coverage
	// ==========================================
	
	@Test
	public void testKeys() {
	    tst.put("b", 1);
	    tst.put("a", 2);
	    tst.put("c", 3);
	    tst.put("bb", 4);
	    
	    int count = 0;
	    for (String k : tst.keys()) {
	        count++;
	    }
	    assertEquals(4, count, "Should iterate over all 4 keys");
	}
	
	@Test
	public void testKeysWithPrefixNullThrowsException() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        tst.keysWithPrefix(null);
	    });
	}
	
	@Test
	public void testKeysWithPrefixBranches() {
	    tst.put("she", 1);
	    tst.put("shells", 2);
	    tst.put("shore", 3);
	    
	    // Prefixo não existe na árvore (x == null)
	    Iterable<String> noKeys = tst.keysWithPrefix("xyz");
	    assertFalse(noKeys.iterator().hasNext(), "Should return empty iterable");
	    
	    // Prefixo existe (x.val != null) e tem filhos
	    Iterable<String> keys = tst.keysWithPrefix("she");
	    int count = 0;
	    for (String k : keys) count++;
	    assertEquals(2, count); // Vai encontrar "she" e "shells"
	}
	
	
	//==========================================
	// Testes para o método keysThatMatch()
	// Requisito 1: Line and Branch Coverage
	// ==========================================
	
	@Test
	public void testKeysThatMatchBranches() {
	    tst.put("bad", 1);
	    tst.put("bed", 2);
	    tst.put("bug", 3);
	    tst.put("dad", 4);
	    
	    // O '.' obriga o código a visitar os ramos left, mid e right do collect()
	    Iterable<String> keys = tst.keysThatMatch(".a.");
	    int count = 0;
	    for (String k : keys) {
	        count++;
	        assertTrue(k.equals("bad") || k.equals("dad")); // As duas chaves que dão match
	    }
	    assertEquals(2, count);
	    
	    // Testar pattern que não dá match com nada (x == null eventualmente)
	    Iterable<String> noKeys = tst.keysThatMatch("xyz");
	    assertFalse(noKeys.iterator().hasNext());
	}
	// ==========================================
    // Testes para o método delete()
    // ==========================================

    @Test
    public void testDeleteNullKeyThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> tst.delete(null));
    }

    @Test
    public void testDeleteNonExistentKey() {
        tst.put("A", 1);
        tst.delete("B"); // Chave não existe, deve fazer return imediato
        assertEquals(1, tst.size());
    }

    @Test
    public void testDeleteKeyWithNoChildren() {
        tst.put("A", 1);
        tst.delete("A"); // Apaga o nó completamente
        assertNull(tst.get("A"));
        assertEquals(0, tst.size());
    }

    @Test
    public void testDeleteKeyWithChildren() {
        tst.put("A", 1);
        tst.put("AB", 2); // 'B' é filho de 'A' (x.mid)
        tst.put("C", 3);  // 'C' é filho à direita de 'A' (x.right)
        
        tst.delete("A"); // Ao apagar o 'A', o nó mantém-se porque tem filhos
        assertNull(tst.get("A")); // O valor de A é nulo
        assertEquals(Integer.valueOf(2), tst.get("AB")); // Mas o AB continua a existir
        assertEquals(2, tst.size());
    }
 // ==========================================
    // Testes para o método equals()
    // ==========================================

    @Test
    public void testEqualsBranches() {
        assertTrue(tst.equals(tst), "Should be equal to itself");
        assertFalse(tst.equals(null), "Should not be equal to null");
        assertFalse(tst.equals("Not a TST"), "Should not be equal to a different class");

        TST<Integer> other = new TST<>();
        assertTrue(tst.equals(other), "Two empty TSTs should be equal");

        tst.put("A", 1);
        assertFalse(tst.equals(other), "Different sizes should not be equal");

        other.put("B", 1);
        assertFalse(tst.equals(other), "Same size but different keys should not be equal");

        other.delete("B");
        other.put("A", 2);
        assertFalse(tst.equals(other), "Same keys but different values should not be equal");

        other.put("A", 1);
        assertTrue(tst.equals(other), "Same keys and values should be equal");
    }
    @Test
    public void testKeysWithPrefixEmptyStringThrowsException() {
        // Inserimos um elemento para garantir que a root não é null.
        // Assim, o método privado get() não vai fazer return logo na primeira linha,
        // chegando à segunda linha que lança a exceção do length == 0.
        tst.put("A", 1); 
        
        assertThrows(IllegalArgumentException.class, () -> {
            tst.keysWithPrefix("");
        });
    }
    
    
    @Test
    public void testLongestPrefixOfQueryShorterThanTriePath() {
        // Inserimos a palavra curta e a comprida
        tst.put("she", 1); 
        tst.put("shells", 2);
        
        // O ciclo passa por 's', 'h', 'e'.
        // No 'e', guarda o length como 3 (porque agora "she" tem um valor!).
        // O 'x' avança para o 'l' (não é null).
        // Na próxima verificação: x != null (Verdade) && 3 < 3 (Falso).
        // Cobre o branch e devolve "she" corretamente!
        assertEquals("she", tst.longestPrefixOf("she"));
    }
    
    @Test
    public void testKeysWithPrefixWherePrefixIsNotAKey() {
        // Inserimos UMA palavra comprida
        tst.put("shells", 1);
        
        // Procuramos por um prefixo mais curto que NÃO é uma chave na árvore.
        // O método encontra o nó do 'e', mas o seu valor (x.val) é nulo!
        // Isto cobre a branch falsa do "if (x.val != null)"
        Iterable<String> result = tst.keysWithPrefix("she");
        
        // Apenas para garantir que o método funciona e devolve as "shells"
        int count = 0;
        for (String s : result) {
            count++;
            assertEquals("shells", s);
        }
        assertEquals(1, count);
    }
    
    @Test
    public void testKeysThatMatchMissedBranches() {
        // 1. Preparação de uma árvore com ramificações
        tst.put("m", 1);      // Raiz
        tst.put("c", 2);      // 'c' < 'm' (vai para a esquerda)
        tst.put("z", 3);      // 'z' > 'm' (vai para a direita)
        tst.put("shells", 4); // Para testar prefixos

        // --- Cobre o missing branch do && ---
        // Procuramos por "she".
        // Chega ao 'e' -> i == length - 1 é VERDADEIRO.
        // Mas "she" não é uma chave isolada -> x.val != null é FALSO.
        // Resolve a linha do && !
        Iterable<String> prefixResult = tst.keysThatMatch("she");
        assertFalse(prefixResult.iterator().hasNext());

        // --- Cobre os missing branches do || ---
        // Procurar por "a" sem wildcards.
        // O 'a' é menor que o 'm' (raiz) e menor que o 'c' (filho esquerdo).
        // Força o "c < x.c" a avaliar para VERDADEIRO várias vezes!
        tst.keysThatMatch("a");
        
        // Procurar por "z" sem wildcards.
        // O 'z' é maior que o 'm'. 
        // Força o "c < x.c" a avaliar para FALSO e passa para os próximos ifs.
        tst.keysThatMatch("z");
    }
    
    @Test
    public void testDeleteComplexBranches() {
        // 1. Testa: x.left != null (tem filho à esquerda, preserva o nó)
        tst = new TST<>();
        tst.put("c", 1);
        tst.put("a", 2); // 'a' < 'c' (filho à esquerda)
        tst.delete("c"); // Remove o valor de 'c', mas o nó mantém-se pelo 'a'
        
        // 2. Testa: x.left == null && x.right != null (tem filho à direita, preserva o nó)
        tst = new TST<>();
        tst.put("c", 1);
        tst.put("e", 2); // 'e' > 'c' (filho à direita)
        tst.delete("c"); // Remove o valor de 'c', mas o nó mantém-se pelo 'e'
        
        // 3. Testa: x.left == null && x.right == null && x.mid != null (tem filho no meio, preserva o nó)
        tst = new TST<>();
        tst.put("c", 1);
        tst.put("ca", 2); // 'a' é filho do meio (continuação da palavra)
        tst.delete("c"); // Remove o valor de 'c', mas o nó mantém-se pelo 'ca'
        
        // 4. Testa o "unrolling" da recursão onde (x.val != null) avalia para VERDADE
        tst = new TST<>();
        tst.put("c", 1);
        tst.put("ca", 2);
        // Ao apagar "ca", o nó 'a' desaparece (os 3 filhos são null e o valor fica null).
        // Quando a recursão volta atrás para o nó 'c', o 'c' ainda tem o valor 1!
        // Logo, bate no "if (x.val != null) return x;" e avalia para VERDADEIRO.
        tst.delete("ca"); 
        
        // 5. Testa ramificações x.left e x.right no percurso de pesquisa do delete (x == null)
        tst = new TST<>();
        tst.put("c", 1);
        tst.delete("a"); // Procura 'a', que é < 'c'. Vai para a esquerda. É null, retorna.
        tst.delete("e"); // Procura 'e', que é > 'c'. Vai para a direita. É null, retorna.
    }
    
    @Test
    public void testDeleteLeftAndRightBranches() {
        tst.put("m", 1); // Nó raiz
        tst.put("c", 2); // Filho à esquerda de 'm'
        tst.put("z", 3); // Filho à direita de 'm'
        
        // Como o "c" existe, o delete privado é chamado. 
        // O código compara 'c' com 'm', dá c < x.c (Verdadeiro!) e vai para a esquerda.
        tst.delete("c"); 
        
        // Como o "z" existe, o delete privado é chamado.
        // O código compara 'z' com 'm', dá c > x.c (Verdadeiro!) e vai para a direita.
        tst.delete("z");
    }
}

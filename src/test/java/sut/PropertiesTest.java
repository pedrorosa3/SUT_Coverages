package sut;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;
import static org.junit.Assert.*; // Import do JUnit 4!

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(JUnitQuickcheck.class)
public class PropertiesTest {

    // 1. A ordem de inserção de chaves diferentes não altera o valor final da árvore
    @Property
    public void testInsertionOrderIndependence(List<String> keys) {
        TST<Integer> tst1 = new TST<>();
        TST<Integer> tst2 = new TST<>();
        
        // Limpar chaves nulas, vazias e duplicadas da lista gerada pelo QuickCheck
        List<String> validKeys = new ArrayList<>();
        for (String k : keys) {
            if (k != null && k.length() > 0 && !validKeys.contains(k)) {
                validKeys.add(k);
            }
        }
        
        // Inserir na árvore 1 (ordem original)
        for (String k : validKeys) {
            tst1.put(k, 1);
        }
        
        // Baralhar as chaves e inserir na árvore 2 (ordem diferente)
        Collections.shuffle(validKeys);
        for (String k : validKeys) {
            tst2.put(k, 1);
        }
        
        // A árvore tem de ficar estruturalmente igual
        assertTrue(tst1.equals(tst2));
    }

    // 2. Se removeres todas as chaves de uma árvore, a árvore tem de ficar vazia
    @Property
    public void testDeleteAllKeysLeavesTreeEmpty(@From(TSTGenerator.class) TST<Integer> tst) {
        // Recolher as chaves todas
        List<String> keys = new ArrayList<>();
        for (String key : tst.keys()) {
            keys.add(key);
        }
        
        // Apagar uma a uma
        for (String key : keys) {
            tst.delete(key);
        }
        
        assertEquals(0, tst.size());
    }

    // 3. Dada uma árvore, inserir e depois remover a mesma chave não altera o valor inicial
    @Property
    public void testInsertAndRemoveSameKeyDoesNotChangeTree(@From(TSTGenerator.class) TST<Integer> tst, String newKey) {
        // Ignorar chaves inválidas ou chaves que a árvore já tenha
        if (newKey == null || newKey.length() == 0 || tst.contains(newKey)) {
            return; 
        }
        
        // Fazer uma cópia manual do estado inicial da árvore
        TST<Integer> initialTst = new TST<>();
        for (String k : tst.keys()) {
            initialTst.put(k, tst.get(k));
        }
        
        // Acão: Inserir e Remover logo de seguida
        tst.put(newKey, 999);
        tst.delete(newKey);
        
        // O estado final tem de ser igual ao estado inicial
        assertTrue(tst.equals(initialTst));
    }

    // 4. Selecionar um prefixo mais estrito retorna um subconjunto estrito de resultados
    @Property
    public void testStricterPrefixReturnsSubset(@From(TSTGenerator.class) TST<Integer> tst, String prefix, char extraChar) {
        if (prefix == null || prefix.length() == 0) return;
        
        // Exemplo: prefix="su", stricterPrefix="sub"
        String stricterPrefix = prefix + extraChar;
        
        List<String> prefixList = new ArrayList<>();
        for (String s : tst.keysWithPrefix(prefix)) prefixList.add(s);
        
        List<String> stricterList = new ArrayList<>();
        for (String s : tst.keysWithPrefix(stricterPrefix)) stricterList.add(s);
        
        // O conjunto estrito ("sub") tem de estar TODO contido no conjunto abrangente ("su")
        assertTrue(prefixList.containsAll(stricterList));
    }
}
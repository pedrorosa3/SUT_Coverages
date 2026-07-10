package sut;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class TSTGenerator extends Generator<TST<Integer>> {

    public TSTGenerator() {
        super((Class<TST<Integer>>) (Class<?>) TST.class);
    }

    @Override
    public TST<Integer> generate(SourceOfRandomness random, GenerationStatus status) {
        TST<Integer> tst = new TST<>();
        
        // Gera uma árvore com um tamanho aleatório entre 0 e 50 elementos
        int size = random.nextInt(0, 50); 

        for (int i = 0; i < size; i++) {
            // Gera uma string aleatória com letras minúsculas (tamanho 1 a 10)
            StringBuilder sb = new StringBuilder();
            int length = random.nextInt(1, 10);
            for (int j = 0; j < length; j++) {
                sb.append((char) random.nextInt('a', 'z'));
            }
            
            String key = sb.toString();
            Integer value = random.nextInt(); // Valor aleatório
            
            tst.put(key, value);
        }
        return tst;
    }
}
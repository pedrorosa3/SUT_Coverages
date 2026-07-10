# VVS - Assignment 1 (Unit Testing a Trie Data Structure)

**Instituição:** Faculdade de Ciências da Universidade de Lisboa (FCUL)  
**Curso:** Mestrado em Engenharia Informática (2025/2026)  
**Unidade Curricular:** Verificação e Validação de Software (VVS)  
**Autor:** Pedro Rosa (60294)

## 📌 Visão Geral do Projeto
Este repositório contém a resolução do primeiro trabalho de VVS. O objetivo central é desenvolver e aplicar uma bateria rigorosa de testes unitários para verificar a correção de uma implementação de uma estrutura de dados **Trie** (especificamente, uma árvore ternária de pesquisa).

O projeto utiliza o **JUnit** para a execução dos testes estruturais, o **PIT (PIT Mutation Testing)** para avaliar a qualidade dos testes através de mutações no código, e o **JUnit QuickCheck** para a validação de propriedades fundamentais da estrutura.

## 🧪 Critérios de Cobertura de Testes
Os testes desenvolvidos estão organizados em *packages* distintos consoante o paradigma de cobertura aplicado. Foram desenhados para satisfazer os seguintes requisitos:

1. **Line and Branch Coverage:** Testes concebidos para garantir a cobertura de linhas e ramificações em todos os métodos públicos da SUT (Software Under Test).
2. **Graph Coverage (Edge-Pair & Prime Path):** Aplicação de cobertura de grafos detalhada focada especificamente no método `longestPrefixOf`.
3. **Logic-Based Coverage:** Aplicação e justificação de um critério de cobertura lógica sobre um método da SUT.
4. **Input Space Partitioning (Base Choice Coverage):** Testes para o método `put` focados nas seguintes partições:
   * A Trie já inclui a nova chave.
   * A Trie já inclui um prefixo da nova chave.
   * A Trie está vazia.
   * A nova chave tem características específicas (a mais pequena, a maior ou uma típica em termos lexicográficos).

## 🐛 Mutation Testing (PIT)
Foi utilizada a ferramenta **PIT** para gerar mutantes do código original. O projeto inclui testes adicionais que foram desenhados e iterados com o objetivo de matar os mutantes sobreviventes e aumentar o *Mutation Coverage Score*, com um foco especial na análise comparativa de cobertura para o método `longestPrefixOf`. A análise destes resultados encontra-se no relatório anexado.

## 🎲 Property-Based Testing (JUnit QuickCheck)
A classe da SUT foi expandida com os métodos `equals()` e `delete()` para suportar testes de propriedades através de um gerador aleatório de Tries. Foram validadas as seguintes propriedades lógicas e matemáticas:
* **Comutatividade da Inserção:** A ordem de inserção de chaves diferentes não altera a árvore final.
* **Esvaziamento Total:** Remover todas as chaves previamente inseridas resulta obrigatoriamente numa árvore vazia.
* **Invariância Inserção-Remoção:** Inserir e remover imediatamente o mesmo valor não altera o estado inicial da árvore.
* **Subconjunto Estrito em Prefixos:** Um pedido de prefixo mais restrito (ex: `keysWithPrefix("sub")`) devolve sempre um subconjunto estrito de um pedido menos restrito (ex: `keysWithPrefix("su")`).


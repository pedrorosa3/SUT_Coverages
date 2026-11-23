<div align="center">

# 🌳 VVS — Assignment 1
### Testes Unitários de uma Trie (Ternary Search Tree)

![Java](https://img.shields.io/badge/Java-8%2B-ED8B00?logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?logo=apachemaven&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-5-25A162?logo=junit5&logoColor=white)
![PIT](https://img.shields.io/badge/PIT-Mutation%20Testing-blueviolet)
![QuickCheck](https://img.shields.io/badge/JUnit-QuickCheck-orange)

**Verificação e Validação de Software** · Mestrado em Engenharia Informática · FCUL

</div>

---

## 📋 Informação

| | |
|---|---|
| 👤 **Autor** | Pedro Rosa (60294) |
| 📅 **Data** | Abril de 2026 |
| 🏛️ **Instituição** | Faculdade de Ciências da Universidade de Lisboa (FCUL) |
| 🎓 **Curso** | Mestrado em Engenharia Informática (2025/2026) |
| 📚 **Unidade Curricular** | Verificação e Validação de Software (VVS) |
| 📄 **Relatório** | [vvs01_60294.pdf](vvs01_60294.pdf) |

---

## 📌 Visão Geral

Este repositório contém a resolução do primeiro trabalho de VVS. O objetivo é desenvolver e aplicar uma bateria rigorosa de testes unitários para verificar a correção de uma implementação de uma estrutura de dados **Trie** — mais concretamente, uma **árvore ternária de pesquisa** (`TST`).

Ferramentas utilizadas:

- 🧪 **JUnit 5** — testes estruturais e de cobertura
- 🧬 **PIT** — *mutation testing* para avaliar a qualidade dos testes
- 🎲 **JUnit QuickCheck** — *property-based testing*

---

## 🗂️ Estrutura do Projeto

```
SUT_Coverages/
├── data/
│   └── someWords.txt          # Palavras usadas pelo programa de demonstração
├── src/
│   ├── main/java/
│   │   ├── startup/Run.java   # Programa de demonstração da TST
│   │   └── sut/TST.java       # Software Under Test (SUT)
│   └── test/java/sut/
│       ├── LineBranchTest.java  # Line & Branch Coverage
│       ├── GraphTest.java       # Edge-Pair & Prime Path Coverage
│       ├── LogicTest.java       # Logic-Based Coverage
│       ├── ISPTest.java         # Input Space Partitioning
│       ├── PropertiesTest.java  # Property-Based Testing
│       └── TSTGenerator.java    # Gerador aleatório de Tries (QuickCheck)
├── pom.xml
└── vvs01_60294.pdf            # Relatório
```

---

## 🧪 Critérios de Cobertura

| # | Critério | Alvo | Ficheiro |
|:-:|---|---|---|
| 1 | **Line & Branch Coverage** | Todos os métodos públicos da SUT | `LineBranchTest` |
| 2 | **Graph Coverage** (Edge-Pair & Prime Path) | `longestPrefixOf` | `GraphTest` |
| 3 | **Logic-Based Coverage** | Um método da SUT (justificado no relatório) | `LogicTest` |
| 4 | **Input Space Partitioning** (Base Choice) | `put` | `ISPTest` |

Partições consideradas para o método `put`:
- A Trie já inclui a nova chave.
- A Trie já inclui um prefixo da nova chave.
- A Trie está vazia.
- A nova chave é a mais pequena, a maior ou uma típica (em ordem lexicográfica).

---

## 🐛 Mutation Testing (PIT)

Foi utilizada a ferramenta **PIT** para gerar mutantes do código original. Foram desenhados testes adicionais com o objetivo de matar os mutantes sobreviventes e aumentar o *Mutation Coverage Score*, com especial foco na análise comparativa de cobertura para o método `longestPrefixOf`. A análise completa encontra-se no relatório.

---

## 🎲 Property-Based Testing (JUnit QuickCheck)

A SUT foi expandida com os métodos `equals()` e `delete()` para suportar testes de propriedades através de um gerador aleatório de Tries. Propriedades validadas:

- 🔁 **Comutatividade da Inserção** — a ordem de inserção de chaves diferentes não altera a árvore final.
- 🧹 **Esvaziamento Total** — remover todas as chaves inseridas resulta numa árvore vazia.
- ↩️ **Invariância Inserção-Remoção** — inserir e remover de imediato o mesmo valor não altera o estado inicial.
- 🔎 **Subconjunto Estrito em Prefixos** — `keysWithPrefix("sub")` devolve sempre um subconjunto estrito de `keysWithPrefix("su")`.

---

## 🚀 Como Executar

### Pré-requisitos

- **Java JDK 8** ou superior
- **Apache Maven 3.x**

Verificar a instalação:

```bash
java -version
mvn -version
```

### 1. Clonar o repositório

```bash
git clone https://github.com/pedrorosa3/SUT_Coverages.git
cd SUT_Coverages
```

### 2. Compilar o projeto

```bash
mvn clean compile
```

### 3. Executar todos os testes

```bash
mvn test
```

Para executar apenas uma classe de testes:

```bash
mvn test -Dtest=GraphTest
```

Ou um único método de teste:

```bash
mvn test -Dtest=ISPTest#testBaseChoice
```

### 4. Executar o Mutation Testing (PIT)

```bash
mvn test-compile org.pitest:pitest-maven:mutationCoverage
```

O relatório HTML é gerado em:

```
target/pit-reports/index.html
```

### 5. Executar o programa de demonstração

O programa `startup.Run` carrega as palavras de `data/someWords.txt` para uma TST e demonstra as operações `keys`, `longestPrefixOf`, `keysWithPrefix` e `keysThatMatch`:

```bash
mvn compile
java -cp target/classes startup.Run
```

> ⚠️ Deve ser executado a partir da raiz do projeto, para que o caminho `data/someWords.txt` seja resolvido corretamente.

---

<div align="center">

Feito por **Pedro Rosa** · FCUL · Abril de 2026

</div>

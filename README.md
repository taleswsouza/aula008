# Aula 008 - Interfaces

## Visão Geral

[![Interface](https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/CPT-OOP-interfaces.svg/2000px-CPT-OOP-interfaces.svg.png)](https://en.wikibooks.org/wiki/A-level_Computing_2009/AQA/Problem_Solving,_Programming,_Operating_Systems,_Databases_and_Networking/Programming_Concepts/Object-oriented_programming_(OOP))

Segundo [Dicio (2017), interface](https://www.dicio.com.br/interface/) é um "dispositivo que causa uma ligação entre dois sistemas" e em programação orientada a objetos a interface é um artefato que permite estabelezer um contrato entre duas classes.

No geral quando se inicia o aprendizado em orientação a objetos, a verdade é que não conseguimos ver o uso pontencial para interfaces em nossas aplicações, quando muito conseguimos ver o uso para classes abstratas. O fato é que as interfaces são um poderoso artefato nas linguagens de programação que seguem o paradigma de orientação a objetos. Tão importantes importante que diversas literaturas afirmam categoricamente que **programe para interfaces**.

## Interfaces em Java

### ... como declará-las

***As interfaces são muito similares as classes abstratas*** e como principal diferença está no fato de que classes abstratas podem conter métodos concretos (implementação ou corpo de um metodo) e ***interfaces não podem conter métodos cocretos***. Observe os exemplos abaixo de uma classe abstrata e de uma interface:
- **Exemplo de uma classe abstrata em Java**
```java
public abstract class ClasseAbstrata {
    public void metodoConcreto() {
        System.out.println("Método concreto em classe abstrata!");
    }
// não tem implementação, corpo, ou seja, termina com ";" logo após o parêntesis da direita ");".    
    public abstract void metodoAbstrato();
}
```
- **Exemplo de interface em Java**
```java
public interface MinhaInterface {
    public void metodoAbstrato1();
    public void metodoAbstrato2();
}
```

### ... não podem ser instanciadas

Um ponto chave em interfaces é que tal qual uma classe abstrata, ***uma interface não pode ser instanciada***.

```java
MinhaInterface obj = new MinhaInterface(); // ERRO DE COMPILAÇÃO - NÃO FAÇA ISSO NO SEU CÓDIGO
```

ATENÇÃO: O erro se encontra apenas na tentantiva de instanciá-la ```new MinhaInterface()``` e não na sua utilização como uma referência a um objeto ```MinhaInterface obj```, ou seja:
```java
MinhaInterface obj;         // OK
obj = new MinhaInterface(); // NÃO OK
new MinhaInterface();       // NÃO OK TAMBÉM
```

**fique atento!**

### ... como utilizá-las

Bom, se podemos criar interfaces, mas não podemos instancia-las, mas o seu principal uso é o **polimorfismo**. Como já vimos o polimorfismo em Java é a capacidade de um objeto ser referênciado com a "mascará" de outro. No caso específico das interfaces, ao criarmos uma classe, nós precisamos declarar as interfaces, precisamos colocar a interface na hierarquia da classe. Porém, diferente da herança entre classes que apontamos pelo uso da palavra reservada ```extends```, a interface nos indicamos a sua **implementação** por uma classe pelo uso da palavra reservada ```implements```.

- **Declarando interface em Java**
```java
// ICliente.java
public interface ICliente {
    public String getNome();
    public long getIdade();
}
```
- **Implementando interfaces em Java**
```java
// Pessoa.java
public class Pessoa implements ICliente {
    private String nome;
    private LocalDate dataNascimento;

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {  // @Override - este método esta sendo sobrescrito
        return nome;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public long getIdade() {  // @Override - este método esta sendo sobrescrito
        LocalDate hoje = LocalDate.now();
        return ChronoUnit.YEARS.between(dataNascimento, hoje);
    }    
}
```
```java
// Empresa.java
public class Empresa implements ICliente {
    private String razaoSocial;
    private Calendar dataFundacao;

    public Empresa(String razaoSocial, int anoFundacao, int mesFundacao, int diaFundacao) {
        this.razaoSocial = razaoSocial;

        Calendar dataFundacao = Calendar.getInstance();
        dataFundacao.set(Calendar.YEAR, anoFundacao);
        dataFundacao.set(Calendar.MONTH, mesFundacao);
        dataFundacao.set(Calendar.DAY_OF_MONTH, diaFundacao);
        this.dataFundacao = dataFundacao;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public Calendar getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(Calendar dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
    public String getNome() {   // @Override - este método esta sendo sobrescrito
        return razaoSocial;     // PS: O detalhe sobre o nome estar armazenado em razaoSocial não interessa para quem é um usuário de ICliente.
    }
    public long getIdade() {
        Calendar hoje = Calendar.getInstance();
        return hoje.get(Calendar.YEAR) - dataFundacao.get(Calendar.YEAR);
    }
}
```

```java
// SistemaDeRelatorio.java
public class SistemaDeRelatorio {
    public static void main(String[] args) {
        Pessoa[] pessoas = {
            new Pessoa("Luiz Inácio Lula da Silva", LocalDate.of(1945, 10, 27)),
             new Pessoa("Fernando Henrique Cardoso", LocalDate.of(1931, 06, 18))
        };
        
        System.out.println("***Imprime a segunda pessoa do array: ");
        imprimeCliente(pessoas[1]); // Imprime Fernando Henrique 
        
        Empresa[] empresas = {
            new Empresa("Petrobras", 1953, 10, 3),
            new Empresa("Ricardo Eletro", 1989, 1, 1),
        };

        System.out.println("***Imprime a primeira empresa do array: ");
        imprimeCliente(pessoas[0]); // Imprime Petrobras 
        
        List<ICliente> clientes = new ArrayList<>();
        clientes.addAll(Arrays.asList(pessoas));
        clientes.addAll(Arrays.asList(empresas));
        
        System.out.println("***Imprime Todos os clientes: ");
        for(ICliente c : clientes) {
            imprimeCliente(c);
        }
    }
    
    public static void imprimeCliente(ICliente cliente) {
        System.out.println("Cliente: " + cliente.getNome() + ", idade: " + cliente.getIdade());
    }
}
```

## Pontos importantes sobre interfaces

Para entender melhor as interfaces podemos resumir algumas afirmações sobre elas:
  - todos os métodos de uma interface são abstratos
  - podem ou não conter métodos (se conter, devem ser abstratos)
  - não podem conter métodos cocretos
  - não podem conter métodos construtores
  - não podem ser instanciadas
  - não podem conter variáveis de instância
  - uma classe não pode extender uma interface, ela devem implementá-la
  - uma interface pode extender outra interface ou várias interfaces
  - é criada um arquivo .java e estes arquivos devem ter o mesmo nome da interface

## Interface em UML

### UML

Para representar xxxx

![UML de uma interface](https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/UmlCd_Interface-2.svg/640px-UmlCd_Interface-2.svg.png)

### Java
```java
// ISensor.java (interface genérica para qualquer tipo de sensor)
public interface ISensor {
    public void aktivieren(); // ativar
    public void lesen(); // ler
}
```
```java
// WärmeSensor.java (sensor de calor)
public class WärmeSensor {
    public void aktivieren() {
        System.out.println("Ativando sensor de calor!");
    }
    public void lesen() {
        System.out.println("Lendo sensor!");
    }
}
```

### TODOs

 - Escrever testes
 
License
----

MIT


**Bons estudos!**

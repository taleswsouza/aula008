
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

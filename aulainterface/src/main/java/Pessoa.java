
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
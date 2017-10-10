
import java.util.Calendar;

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

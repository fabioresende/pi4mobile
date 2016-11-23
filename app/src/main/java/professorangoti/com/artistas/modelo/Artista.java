package professorangoti.com.artistas.modelo;

import java.math.BigDecimal;

public class Artista {

    private Integer codArtista;
    private String nome;
    private String nacionalidade;
    private BigDecimal cache;
    private String nascimento;

    public Integer getCodArtista() {
        return codArtista;
    }

    public void setCodArtista(Integer codArtista) {
        this.codArtista = codArtista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public BigDecimal getCache() {
        return cache;
    }

    public void setCache(BigDecimal cache) {
        this.cache = cache;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    @Override
    public String toString() {
        return nome;
    }
}

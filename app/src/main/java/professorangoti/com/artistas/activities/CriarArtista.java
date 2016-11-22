package professorangoti.com.artistas.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.math.BigDecimal;

import professorangoti.com.artistas.R;
import professorangoti.com.artistas.modelo.Artista;
import professorangoti.com.artistas.webservice.ArtistasService;
import professorangoti.com.artistas.webservice.Servico;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriarArtista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_artista);
        consultaServidor();
    }

    public void consultaServidor() {

        Artista artista = new Artista();
        artista.setNacionalidade("Brasileiro");
        artista.setNascimento("01/01/1970");
        artista.setCache(new BigDecimal(10000));
        artista.setNome("Wagner Moura 883");

        ArtistasService servico = Servico.criarServico(ArtistasService.class);
        Call<Artista> chamada = servico.criar(artista);
        chamada.enqueue(new Callback<Artista>() {
            @Override
            public void onResponse(Call<Artista> call, Response<Artista> response) {

            }

            @Override
            public void onFailure(Call<Artista> call, Throwable t) {

            }
        });
    }
}

package professorangoti.com.artistas.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;

import professorangoti.com.artistas.R;
import professorangoti.com.artistas.modelo.Artista;
import professorangoti.com.artistas.webservice.ArtistasService;
import professorangoti.com.artistas.webservice.Servico;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriarArtista extends AppCompatActivity {

    Context contexto = this;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_artista);
        id = getIntent().getIntExtra("id", -1);
        if (id != -1) preencherForm();
    }


    public void cadastrar(View v) {
        Artista artista = new Artista();
        artista.setNacionalidade(((EditText) findViewById(R.id.nacionalidade)).getText().toString());
        artista.setNascimento(((EditText) findViewById(R.id.nascimento)).getText().toString());
        artista.setCache(new BigDecimal(((EditText) findViewById(R.id.cache)).getText().toString()));
        artista.setNome(((EditText) findViewById(R.id.nome)).getText().toString());
        if (id == -1)
            criar(artista);
        else
            atualizar(artista);
    }

    private void atualizar(Artista artista) {
        ArtistasService servico = Servico.criarServico(ArtistasService.class);
        Call<Void> chamada = servico.atualizar(id,artista);
        chamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 204) {
                    Toast.makeText(contexto, "Artista atualizado", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(contexto, Navegacao.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private void criar(Artista artista) {
        ArtistasService servico = Servico.criarServico(ArtistasService.class);
        Call<Artista> chamada = servico.criar(artista);
        chamada.enqueue(new Callback<Artista>() {
            @Override
            public void onResponse(Call<Artista> call, Response<Artista> response) {
                // Toast.makeText(contexto,"Código da resposta: " + response.code(),Toast.LENGTH_LONG).show();
                if (response.code() == 201) {
                    Toast.makeText(contexto, "Artista criado", Toast.LENGTH_LONG).show();
                } else if (response.code() == 400) {
                    Toast.makeText(contexto, "Já existe um artista com esse nome!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Artista> call, Throwable t) {

            }
        });
    }

    private void preencherForm(){
        ArtistasService servico = Servico.criarServico(ArtistasService.class);
        Call<Artista> chamada = servico.getArtista(id);

        chamada.enqueue(new Callback<Artista>() {
            @Override
            public void onResponse(Call<Artista> call, Response<Artista> response) {
                // Toast.makeText(contexto,"Código da resposta: " + response.code(),Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    Artista artista = response.body();
                    ((EditText) findViewById(R.id.nacionalidade)).setText(artista.getNacionalidade());
                    ((EditText) findViewById(R.id.nascimento)).setText(artista.getNascimento());
                    ((EditText) findViewById(R.id.cache)).setText(artista.getCache().toString());
                    ((EditText) findViewById(R.id.nome)).setText(artista.getNome());
                }
            }

            @Override
            public void onFailure(Call<Artista> call, Throwable t) {

            }
        });
    }
}

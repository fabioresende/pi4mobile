package professorangoti.com.artistas.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import okhttp3.ResponseBody;
import professorangoti.com.artistas.R;
import professorangoti.com.artistas.modelo.Artista;
import professorangoti.com.artistas.webservice.ArtistasService;
import professorangoti.com.artistas.webservice.Servico;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaTodos extends AppCompatActivity {

    Context contexto = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listatodos);

        consultaServidor();


    }
    public void consultaServidor() {

        ArtistasService servico = Servico.criarServico(ArtistasService.class);
        Call<List<Artista>> chamada = servico.todos();

        chamada.enqueue(new Callback<List<Artista>>() {
            @Override
            public void onResponse(Call<List<Artista>> chamada, Response<List<Artista>> resposta) {
                if (resposta.isSuccessful()) {
                    List<Artista> dados = resposta.body();
                    //verifica aqui se o corpo da resposta não é nulo
                    if (dados != null) {
                        ListView listView = (ListView)findViewById(R.id.minha_lista);
                        ArrayAdapter<Artista> adaptador = new ArrayAdapter<Artista>(contexto, android.R.layout.simple_list_item_1, dados);
                        listView.setAdapter(adaptador);
                    } else {
                        Toast.makeText(getApplicationContext(), "Resposta nula do servidor", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Resposta não foi sucesso", Toast.LENGTH_SHORT).show();
                    // segura os erros de requisição
                    ResponseBody errorBody = resposta.errorBody();
                }
            }

            @Override
            public void onFailure(Call<List<Artista>> chamada, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
                Log.d("debug",t.getMessage());
            }
        });
    }

}

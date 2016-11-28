package professorangoti.com.artistas.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import okhttp3.ResponseBody;
import professorangoti.com.artistas.R;
import professorangoti.com.artistas.modelo.Artista;
import professorangoti.com.artistas.view.ListaTodosAdaptador;
import professorangoti.com.artistas.webservice.ArtistasService;
import professorangoti.com.artistas.webservice.Servico;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaTodos2 extends AppCompatActivity {

    Context contexto = this;
    ListView listView;
    List<Artista> dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listatodos);
        listView = (ListView) findViewById(R.id.minha_lista);
        consultaServidor();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle("Opções");
        menu.add(0, dados.get(info.position).getCodArtista(), dados.get(info.position).getCodArtista(), "Excluir");
        menu.add(0, dados.get(info.position).getCodArtista(), dados.get(info.position).getCodArtista(), "Alterar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Excluir") {
            function1(item.getItemId());
        } else if (item.getTitle() == "Alterar") {
            function2(item.getItemId());
        } else {
            return false;
        }
        return true;
    }

    public void function1(int id) {
        Toast.makeText(this, "Chamar Activity para excluir item " + id, Toast.LENGTH_LONG).show();
    }

    public void function2(int id) {
        Toast.makeText(this, "Chamar Activity para alterar item " + id, Toast.LENGTH_LONG).show();
    }

    public void consultaServidor() {

        ArtistasService servico = Servico.criarServico(ArtistasService.class);
        Call<List<Artista>> chamada = servico.todos();

        chamada.enqueue(new Callback<List<Artista>>() {
            @Override
            public void onResponse(Call<List<Artista>> chamada, Response<List<Artista>> resposta) {
                if (resposta.isSuccessful()) {
                    dados = resposta.body();
                    //verifica aqui se o corpo da resposta não é nulo
                    if (dados != null) {

                        final ListaTodosAdaptador adaptador = new ListaTodosAdaptador(contexto, dados);
                        listView.setAdapter(adaptador);
                        registerForContextMenu(listView);
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
                Log.d("debug", t.getMessage());
            }
        });
    }

}

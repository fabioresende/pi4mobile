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
import professorangoti.com.artistas.modelo.Curso;
import professorangoti.com.artistas.webservice.CursoService;
import professorangoti.com.artistas.webservice.Servico;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriarCurso extends AppCompatActivity {

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
        Curso curso = new Curso();
        curso.setCargaHoraria(((EditText) findViewById(R.id.cargaHoraria)).getText().toString());
        curso.setPontuacao(((EditText) findViewById(R.id.pontuacao)).getText().toString());
        curso.setPreco(new BigDecimal(((EditText) findViewById(R.id.preco)).getText().toString()));
        curso.setNome(((EditText) findViewById(R.id.nome)).getText().toString());
        if (id == -1)
            criar(curso);
        else
            atualizar(curso);
    }

    private void atualizar(Curso curso) {
        CursoService servico = Servico.criarServico(CursoService.class);
        Call<Void> chamada = servico.atualizar(id, curso);
        chamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 204) {
                    Toast.makeText(contexto, "Curso atualizado", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(contexto, Navegacao.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private void criar(Curso curso) {
        CursoService servico = Servico.criarServico(CursoService.class);
        Call<Curso> chamada = servico.criar(curso);
        chamada.enqueue(new Callback<Curso>() {
            @Override
            public void onResponse(Call<Curso> call, Response<Curso> response) {
                // Toast.makeText(contexto,"Código da resposta: " + response.code(),Toast.LENGTH_LONG).show();
                if (response.code() == 201) {
                    Toast.makeText(contexto, "Curso criado", Toast.LENGTH_LONG).show();
                } else if (response.code() == 400) {
                    Toast.makeText(contexto, "Já existe um curso com esse nome!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Curso> call, Throwable t) {

            }
        });
    }

    private void preencherForm(){
        CursoService servico = Servico.criarServico(CursoService.class);
        Call<Curso> chamada = servico.getCurso(id);

        chamada.enqueue(new Callback<Curso>() {
            @Override
            public void onResponse(Call<Curso> call, Response<Curso> response) {
                // Toast.makeText(contexto,"Código da resposta: " + response.code(),Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    Curso curso = response.body();
                    ((EditText) findViewById(R.id.preco)).setText(curso.getPreco().toString());
                    ((EditText) findViewById(R.id.pontuacao)).setText(curso.getPontuacao().toString());
                    ((EditText) findViewById(R.id.cargaHoraria)).setText(curso.getCargaHoraria().toString());
                    ((EditText) findViewById(R.id.nome)).setText(curso.getNome());
                }
            }

            @Override
            public void onFailure(Call<Curso> call, Throwable t) {

            }
        });
    }
}

package professorangoti.com.artistas.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import professorangoti.com.artistas.R;
import professorangoti.com.artistas.modelo.Curso;

/**
 * Created by angot on 22/11/2016.
 */

public class ListaTodosAdaptador extends ArrayAdapter<Curso> {

    private Context context;
    private List<Curso> lista = null;

    public ListaTodosAdaptador(Context context, List<Curso> lista) {
        super(context, 0, lista);
        this.lista = lista;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Curso curso = lista.get(position);

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.listatodos2, null);

        TextView nome  = (TextView) view.findViewById(R.id.nome);
        nome.setText(curso.getNome());

        TextView preco  = (TextView) view.findViewById(R.id.preco);
        preco.setText(curso.getPreco().toString());

        TextView cargaHoraria  = (TextView) view.findViewById(R.id.cargaHoraria);
        cargaHoraria.setText(curso.getCargaHoraria().toString());

        TextView pontuacao  = (TextView) view.findViewById(R.id.pontuacao);
        pontuacao.setText(curso.getPontuacao().toString());

        return view;
    }
}

package professorangoti.com.artistas.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import professorangoti.com.artistas.R;
import professorangoti.com.artistas.modelo.Artista;

/**
 * Created by angot on 22/11/2016.
 */

public class ListaTodosAdaptador extends ArrayAdapter<Artista> {

    private Context context;
    private List<Artista> lista = null;

    public ListaTodosAdaptador(Context context, List<Artista> lista) {
        super(context, 0, lista);
        this.lista = lista;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Artista artista = lista.get(position);

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.listatodos2, null);

        TextView nome  = (TextView) view.findViewById(R.id.nome);
        nome.setText(artista.getNome());

        TextView nacionalidade  = (TextView) view.findViewById(R.id.nacionalidade);
        nacionalidade.setText(artista.getNacionalidade());

        TextView cache  = (TextView) view.findViewById(R.id.cache);
        cache.setText(artista.getCache()+"");

        TextView nascimento  = (TextView) view.findViewById(R.id.nascimento);
        nascimento.setText(artista.getNascimento());

        return view;
    }
}

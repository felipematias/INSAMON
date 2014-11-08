/**
 * 
 */
package insamon.insa.amerinsa;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Felipe
 *Cette classe sert à personaliser notre liste, avec la photo et le nom des elements de la liste.
 */
public class AdapterListView extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<PokInitListView> elements;

    public AdapterListView(Context context, ArrayList<PokInitListView> elements) {
        //Itens que preencheram o listview
        this.elements = elements;
        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }

    /**
     *Return la quantité d'elements dans la liste
     * @return
     */
    public int getCount() {
        return elements.size();
    }

    /**
     *Return l'element d'accord avec sa position dans l'écran
     *
     * @param position
     * @return
     */
    public PokInitListView getItem(int position) {
        return elements.get(position);
    }

    /**
     * @param position
     * @return
     */
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        //Prend l'element d'accord avec sa position.
        PokInitListView item = elements.get(position);

        view = mInflater.inflate(R.layout.item_listview, null);

        ((TextView) view.findViewById(R.id.text)).setText(item.getText());
        ((ImageView) view.findViewById(R.id.imageview)).setImageResource(item.getIconeRid());

        return view;
    }
}


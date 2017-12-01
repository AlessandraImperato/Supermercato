package it.alessandra.supermercato;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by utente7.academy on 30/11/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Prodotto> prodotti;
    public Context context;
    public static Prodotto prodotto;

    public MyAdapter(List<Prodotto> listaProdotti) {
        prodotti = listaProdotti;
    }

    public MyAdapter(List<Prodotto> lisaProdotti, Context mcontext) {
        prodotti = lisaProdotti;
        context = mcontext;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemrv, parent, false);
        ViewHolder vh = new ViewHolder(v, parent.getContext());
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        Prodotto temp = prodotti.get(position);

        if(temp instanceof Carne){
            holder.tMarca.setText(((Carne) temp).getMarca());
            holder.tPrezzo.setText(""+((Carne) temp).getPrezzo());
            holder.img.setImageResource(R.drawable.carne);
        }
        else if (temp instanceof Pesce){
            holder.tMarca.setText(((Pesce) temp).getMarca());
            holder.tPrezzo.setText(""+((Pesce) temp).getPrezzo());
            holder.img.setImageResource(R.drawable.pesce);
        }
        else if (temp instanceof Latte){
            holder.tMarca.setText(((Latte) temp).getMarca());
            holder.tPrezzo.setText(""+((Latte) temp).getPrezzo());
            holder.img.setImageResource(R.drawable.latte);
        }
    }

    @Override
    public int getItemCount() {
        return prodotti.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView tMarca;
        public TextView tPrezzo;
        public ImageView img;

        public ViewHolder(View v, final Context context) {
            super(v);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
            tMarca = (TextView) v.findViewById(R.id.tmarca);
            tPrezzo = (TextView) v.findViewById(R.id.tprezzo);
            img = (ImageView) v.findViewById(R.id.imgprod);
        }

    }

    public Context getContext() {
        return context;
    }
}

package com.example.orionstark.barberros.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orionstark.barberros.R;
import com.example.orionstark.barberros.controllers.activities.BarberInfoActivity;
import com.example.orionstark.barberros.controllers.activities.MakeAppointmentActivity;
import com.example.orionstark.barberros.controllers.fragments.BarbersFragment;
import com.example.orionstark.barberros.models.Barber;


public class BarberRecyclerAdapter extends RecyclerView.Adapter<BarberRecyclerAdapter.CardViewHolder>{
    private Context context;
    public BarberRecyclerAdapter(Context context)
    {
        this.context=context;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.barber_item_row,parent,false);
        CardViewHolder viewHolder=new CardViewHolder(view);
        return  viewHolder;
    }
    @Override
    public void onBindViewHolder(final CardViewHolder holder, final int position){
        holder.gambar.setBackground(new BitmapDrawable(Barber.barbers.get(holder.getAdapterPosition()).getImage()));
        holder.txtBarber.setText(Barber.barbers.get(holder.getAdapterPosition()).getBarber_name());
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //context.startActivity(new Intent(context, BarberInfoActivity.class).putExtra("barber_data_about", Barber.barbers.get(position).getBarber_name()));
                Intent intent = new Intent(context , BarberInfoActivity.class);
                intent.putExtra("barber_info",holder.getAdapterPosition());

                context.startActivity(intent);
            }
        });
//        holder.btnBook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, MakeAppointmentActivity.class);
//                context.startActivity(intent);
//            }
//        });

    }

    public int getItemCount(){
       if ( Barber.barbers.size() > 0 ) {
           return Barber.barbers.size();
       } else {
           return  0;
       }
    }

    public  class CardViewHolder extends RecyclerView.ViewHolder{
       Button btnMore;
       Button btnBook;
       ImageView btnFav;
       TextView txtBarber;
       ImageView icon;
       RelativeLayout gambar;

        public CardViewHolder(View itemView){
            super(itemView);
            btnMore = itemView.findViewById(R.id.btn_more);
            btnBook= itemView.findViewById(R.id.btn_book);
            btnFav= itemView.findViewById(R.id.btn_fav);
            txtBarber= itemView.findViewById(R.id.txtBarber);
            gambar = itemView.findViewById(R.id.list_img);
        }
    }


}

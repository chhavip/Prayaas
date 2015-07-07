package helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.chhavi.prayaas.R;

import java.util.ArrayList;
import java.util.List;

import app.AppConfig;
import app.AppController;
import models.Centers;
import models.CollectionCenterResponse;
import models.EventResponse;
import models.Events;

/**
 * Created by shikharkhetan on 7/6/15.
 */
public class CollectionCenterAdapter extends RecyclerView.Adapter<CollectionCenterAdapter.CollectionCenterHolder>{

    int resId;
    int position;
    List<Centers> centers;
    Context context;

    public class CollectionCenterHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView nameCollect;
        TextView phoneCollect;
        ImageView callButton;
        TextView addressCollect;
        TextView timingsCollect;
        TextView daysActiveCollect;
        CollectionCenterHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            nameCollect = (TextView)itemView.findViewById(R.id.nameCollect);
            phoneCollect = (TextView)itemView.findViewById(R.id.phoneCollect);
            callButton = (ImageView)itemView.findViewById(R.id.callCollect);
            addressCollect = (TextView)itemView.findViewById(R.id.addressCollect);
            timingsCollect = (TextView)itemView.findViewById(R.id.timingsCollect);
            daysActiveCollect = (TextView)itemView.findViewById(R.id.daysActiveCollect);
        }
    }
    public CollectionCenterAdapter(List<Centers> centers, int resId, Context context){
        this.resId = resId;
        this.centers = centers;
        this.context = context;
    }

    @Override
    public CollectionCenterAdapter.CollectionCenterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);

         CollectionCenterHolder pvh = new CollectionCenterHolder(v);
        return pvh;
    }


    @Override
    public void onBindViewHolder(final CollectionCenterAdapter.CollectionCenterHolder holder, final int position) {


        holder.nameCollect.setText(centers.get(position).getName());
        holder.addressCollect.setText(centers.get(position).getAddress());
        holder.phoneCollect.setText(centers.get(position).getContact());
        holder.timingsCollect.setText(centers.get(position).getTimings());
        holder.daysActiveCollect.setText(centers.get(position).getDays_active());
        holder.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + holder.phoneCollect.getText().toString()));
                context.startActivity(intent);
            }
        });
        holder.phoneCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + holder.phoneCollect.getText().toString()));
                context.startActivity(intent);
            }
        });
        this.position = position;
    }

    @Override
    public int getItemCount() {
        return centers.size();
    }


}


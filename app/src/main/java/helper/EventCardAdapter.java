package helper;

import android.app.usage.UsageEvents;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.engio.prayaas.R;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.AppConfig;
import app.AppController;
import models.Events;

/**
 * Created by chhavi on 8/6/15.
 */
public class EventCardAdapter extends RecyclerView.Adapter<EventCardAdapter.EventHolder> {
    int resId;
    int position;
    List<Events> events;
    Context context;
    String  url;
    HashMap<String,String> userInfo;
    public onCancelListener mOnCancelListener;

    public interface onCancelListener {
        public void onCancelButtonPressed(View v);
    }



    OnItemClickListener mItemClickListener;
    public class EventHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        CardView cv;
        TextView eventName;
        TextView eventDate;
        ImageView eventPhoto;
        Button cancelButton;

        EventHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            eventName = (TextView)itemView.findViewById(R.id.event_name);
            eventDate = (TextView)itemView.findViewById(R.id.event_date);
            eventPhoto = (ImageView)itemView.findViewById(R.id.event_image);
            cancelButton = (Button)itemView.findViewById(R.id.cancelButton);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
                Log.d("Activity", "onClick " + getPosition() + " ");
            }

        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view , int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }



    public EventCardAdapter(List<Events> events, int resId, Context context){
        this.resId = resId;
        this.events = events;
        this.context = context;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public EventCardAdapter.EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
        if  (resId == R.layout.fragment_going_event)  {
                Button cancel = (Button)v.findViewById(R.id.cancelButton);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteHandler handler = new SQLiteHandler(context);
                    userInfo = handler.getUserDetails();
                    String userId = userInfo.get("uid");
                    Events event = events.get(position);
                    int eventId = event.id;
                    Log.e("message",userId +"  " + eventId);
                    String status ="cancelled";
                    url = AppConfig.BASE_URL+ "store_user_event.php";
                    UpdateUserEvent(userId,eventId+"",status);
                }
            });
        }
        EventHolder pvh = new EventHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(EventCardAdapter.EventHolder holder, final int position) {
            holder.eventName.setText(events.get(position).name);
        holder.eventDate.setText(events.get(position).date);
        holder.eventPhoto.setImageResource(events.get(position).imageResource);
        this.position = position;
     /*   holder.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    void UpdateUserEvent(final String userId, final String eventId, final String status){

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //PD.dismiss();
                        //   item_et.setText("");
                        Log.e("response user", response);
                        Toast.makeText(context,
                                "This event has been cancelled for you. It wont display the next time",
                                Toast.LENGTH_SHORT).show();



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // PD.dismiss();
                Log.e("error", error.toString());
                Toast.makeText(context,
                        "failed to insert", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userId", userId);
                params.put("eventId",eventId);
                params.put("status",status);

                return params;
            }
        };

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(postRequest);
    }


    @Override
    public int getItemCount() {
        return events.size();
    }


}

package helper;

import android.app.usage.UsageEvents;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chhavi.prayaas.R;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.List;

import models.Events;

/**
 * Created by chhavi on 8/6/15.
 */
public class EventCardAdapter extends RecyclerView.Adapter<EventCardAdapter.EventHolder> {
    int resId;
    int position;
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


        EventHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            eventName = (TextView)itemView.findViewById(R.id.event_name);
            eventDate = (TextView)itemView.findViewById(R.id.event_date);
            eventPhoto = (ImageView)itemView.findViewById(R.id.event_image);
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

    List<Events> events;

    public EventCardAdapter(List<Events> events, int resId){
        this.resId = resId;
        this.events = events;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public EventCardAdapter.EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
        if  (resId == R.layout.fragment_going_event)  {
            Button  cancelButton = (Button) v.findViewById(R.id.cancelButton);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    events.remove(position);
                    mOnCancelListener.onCancelButtonPressed(v);

                }
            });
        }
        EventHolder pvh = new EventHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(EventCardAdapter.EventHolder holder, int position) {
            holder.eventName.setText(events.get(position).name);
        holder.eventDate.setText(events.get(position).date);
        holder.eventPhoto.setImageResource(events.get(position).imageResource);
        this.position = position;
    }

    @Override
    public int getItemCount() {
        return events.size();
    }


}

package design.swira.aennyapp.ui.aenny.adapters;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import design.swira.aennyapp.R;
import design.swira.aennyapp.pojo.aenny.ctrip.CompletedTripResponse;
import design.swira.aennyapp.ui.aenny.clientfavourites.AddClientFavouriteDialogActivity2;
import design.swira.aennyapp.ui.aenny.trips.OnRecentListClick;

public class RecentListAdapter   extends RecyclerView.Adapter<RecentListAdapter.ClientFavouriteListViewHolder> {

    List<CompletedTripResponse> list;

    OnRecentListClick onRecentListClick;
    Context context;

    public RecentListAdapter(OnRecentListClick onRecentListClick, Context context) {
        this.onRecentListClick = onRecentListClick;
        this.context=context;
    }

    public void setList(List<CompletedTripResponse> list){
        this.list=list;
    }

    @NonNull
    @Override
    public RecentListAdapter.ClientFavouriteListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_list_item,parent,false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int)v.getTag();
                CompletedTripResponse clientFavouriteResponse = list.get(pos);
                onRecentListClick.onListClick(clientFavouriteResponse);
            }
        });

        return new RecentListAdapter.ClientFavouriteListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentListAdapter.ClientFavouriteListViewHolder holder, int position) {

        //String icon=list.get(position).getClientFavouriteNotes();

        Double lat=list.get(position).getTripDestinationLatt();
        Double lon=list.get(position).getTripDestinationLong();

        String getcuraddress = getcuradr(lat, lon);

        holder.name.setText(getcuraddress);
        holder.desc.setText(list.get(position).getTripDestination());

        holder.favimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(context, AddClientFavouriteDialogActivity2.class);
                ii.putExtra("longitude", lon);
                ii.putExtra("latitude", lat);
                ii.putExtra("addresss", list.get(position).getTripDestination());
                context.startActivity(ii);
            }
        });

        holder.cview.setTag(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ClientFavouriteListViewHolder extends RecyclerView.ViewHolder {
        CardView cview;
        ImageView img;
        TextView name,desc;
        ImageView favimg;
        public ClientFavouriteListViewHolder(@NonNull View v) {
            super(v);
            cview=v.findViewById(R.id.cview);
            img=v.findViewById(R.id.img);
            name=v.findViewById(R.id.locname);
            desc=v.findViewById(R.id.locaddress);
            favimg=v.findViewById(R.id.favimg);
        }




    }

    private String getcuradr(Double latitude, Double longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());
        String city = "";

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL


        } catch (IOException e) {
            e.printStackTrace();
        }
        return city;
    }



}

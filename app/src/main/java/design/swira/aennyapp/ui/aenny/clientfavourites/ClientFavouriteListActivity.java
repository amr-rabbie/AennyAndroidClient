package design.swira.aennyapp.ui.aenny.clientfavourites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityClientFavouriteListBinding;
import design.swira.aennyapp.pojo.aenny.clientfavourites.ClientFavouriteResponse;
import design.swira.aennyapp.ui.aenny.adapters.ClientFavouriteListAdapter;
import design.swira.aennyapp.ui.aenny.customdialog.MyCustomDialogActivity;
import design.swira.aennyapp.ui.aenny.mainpage.MainClientActivity;
import design.swira.aennyapp.ui.aenny.ongonigtrip.SecondStepMapActivity;
import design.swira.aennyapp.utils.Constants;
import design.swira.aennyapp.utils.GpsTracker;
import design.swira.aennyapp.utils.Network;
import design.swira.aennyapp.utils.SwipeHelper;

public class ClientFavouriteListActivity extends AppCompatActivity implements View.OnClickListener , OnLocationFavouriteClick  {

    ActivityClientFavouriteListBinding binding;
    ClientFavouriteViewModel viewModel;
    ClientFavouriteListAdapter adapter;
    private GpsTracker gpsTracker;
    private double latitude;
    private double longitude;
    String ori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_client_favourite_list);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_client_favourite_list);

        viewModel= ViewModelProviders.of(this).get(ClientFavouriteViewModel.class);

        binding.add.setOnClickListener(this);

        adapter=new ClientFavouriteListAdapter(this,this);

        bindData();



    }

    private void bindData() {
        int clientid= Constants.getClientId(ClientFavouriteListActivity.this);
        viewModel.getClientFavouriteById(clientid);

        viewModel.clientFavouritelistMutableLiveData.observe(ClientFavouriteListActivity.this, new Observer<List<ClientFavouriteResponse>>() {
            @Override
            public void onChanged(List<ClientFavouriteResponse> clientFavouriteResponses) {
                if(clientFavouriteResponses != null){
                    adapter.setList(clientFavouriteResponses);
                    binding.recycler.setAdapter(adapter);
                    binding.recycler.setLayoutManager(new LinearLayoutManager(ClientFavouriteListActivity.this));
                    binding.pbar.setVisibility(View.GONE);
                    binding.recycler.setVisibility(View.VISIBLE);


                    SwipeHelper swipeHelper = new SwipeHelper(ClientFavouriteListActivity.this, binding.recycler) {
                        @Override
                        public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                            underlayButtons.add(new SwipeHelper.UnderlayButton(
                                    "Delete",
                                    0,
                                    Color.parseColor("#ea0000"),
                                    new SwipeHelper.UnderlayButtonClickListener() {
                                        @Override
                                        public void onClick(int pos) {
                                            // TODO: onDelete

                                            int id = clientFavouriteResponses.get(pos).getClient_Favourite_Id();
                                            //showDialog(id,"Confirm Delete","Are you sure you want to delete this client favourite ?",context);

                                            Intent i=new Intent(ClientFavouriteListActivity.this, MyCustomDialogActivity.class);
                                            i.putExtra("id",id);
                                            i.putExtra("title","Confirm delete");
                                            i.putExtra("msg","Are you sure you want to delete this client favourite ?");
                                            i.putExtra("flag","fav");
                                            startActivity(i);
                                        }
                                    }
                            ));

                            /*underlayButtons.add(new SwipeHelper.UnderlayButton(
                                    "Transfer",
                                    0,
                                    Color.parseColor("#FF9502"),
                                    new SwipeHelper.UnderlayButtonClickListener() {
                                        @Override
                                        public void onClick(int pos) {
                                            // TODO: OnTransfer
                                        }
                                    }
                            ));*/
                            underlayButtons.add(new SwipeHelper.UnderlayButton(
                                    "Edit",
                                    0,
                                    Color.parseColor("#0c66f0"),
                                    new SwipeHelper.UnderlayButtonClickListener() {
                                        @Override
                                        public void onClick(int pos) {
                                            // TODO: OnUnshare

                                            int id = clientFavouriteResponses.get(pos).getClient_Favourite_Id();
                                            //showDialog(id,"Confirm Delete","Are you sure you want to delete this client favourite ?",context);

                                            Intent i=new Intent(ClientFavouriteListActivity.this, UpdateClientFavouriteActivity.class);
                                            i.putExtra("id",id);
                                            startActivity(i);

                                        }
                                    }
                            ));
                        }
                    };


                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add){
            addFavourite();
        }

    }

    private void addFavourite() {
        Intent i=new Intent(ClientFavouriteListActivity.this,AddClientFavouritesActivity.class);
        startActivity(i);
    }


    @Override
    public void onListClick(ClientFavouriteResponse clientFavouriteResponse) {
        double latitude2 = clientFavouriteResponse.getClientFavouriteLatt();
        double longitude2 = clientFavouriteResponse.getClientFavouriteLang();
        String des = clientFavouriteResponse.getClientFavouriteDesc();

        /*Intent i = new Intent(MainClientActivity.this, RouteActivity.class);
        i.putExtra("latitude2", latitude2);
        i.putExtra("longitude2", longitude2);
        i.putExtra("des", des);
        startActivity(i);*/


        getLocation();
        getcuraddresss();

        /*latitude2=list.get(position).getTripDestinationLatt();
        longitude2=list.get(position).getTripDestinationLong();
        getgoingaddress();*/


        Intent i = new Intent(ClientFavouriteListActivity.this, SecondStepMapActivity.class);
        i.putExtra("latitude", latitude);
        i.putExtra("longitude", longitude);
        i.putExtra("latitude2", latitude2);
        i.putExtra("longitude2", longitude2);
        i.putExtra("ori", ori);
        i.putExtra("des", des);

        String lat = latitude + "";
        String lon = longitude + "";

        String lat2 = latitude2 + "";
        String lon2 = longitude2 + "";

        Constants.saveTripData(lat, lon, ori, lat2, lon2, des, ClientFavouriteListActivity.this);

        startActivity(i);

    }

    private void getcuraddresss() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(ClientFavouriteListActivity.this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            ori=address;

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void getLocation(){

        latitude=33.312805;
        longitude=44.361488;

        if(!Network.isNetworkAvailable(ClientFavouriteListActivity.this)){
            latitude=33.312805;
            longitude=44.361488;
            return;
        }
        else {

            gpsTracker = new GpsTracker(ClientFavouriteListActivity.this);
            if (gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();

            } else {
                gpsTracker.showSettingsAlert();
            }
        }



        //Toast.makeText(RouteActivity.this, "latitude: " + latitude + " - longitude: " + longitude, Toast.LENGTH_SHORT).show();
    }


}

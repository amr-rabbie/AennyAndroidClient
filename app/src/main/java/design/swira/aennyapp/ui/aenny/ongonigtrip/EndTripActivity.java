package design.swira.aennyapp.ui.aenny.ongonigtrip;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import design.swira.aennyapp.R;
import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.data.api.MapApiClient;
import design.swira.aennyapp.pojo.aenny.driver.DriverDataResponse;
import design.swira.aennyapp.pojo.aenny.googlemaps.Distance;
import design.swira.aennyapp.pojo.aenny.googlemaps.Duration;
import design.swira.aennyapp.pojo.aenny.googlemaps.GoogleMapsResponse;
import design.swira.aennyapp.pojo.aenny.googlemaps.Leg;
import design.swira.aennyapp.pojo.aenny.googlemaps.Route;
import design.swira.aennyapp.pojo.aenny.tripsrates.TripRatesResponse;
import design.swira.aennyapp.pojo.aenny.vechaels.VechaelResponse;
import design.swira.aennyapp.ui.aenny.mainpage.MainClientActivity;
import design.swira.aennyapp.utils.Constants;

public class EndTripActivity extends AppCompatActivity  implements OnMapReadyCallback, View.OnClickListener  {

    @BindView(R.id.moneysum)
    TextView moneysum;
    @BindView(R.id.kmsum)
    TextView kmsum;
    @BindView(R.id.minsum)
    TextView minsum;
    @BindView(R.id.waitsum)
    TextView waitsum;
    @BindView(R.id.pickupcity)
    TextView pickupcity;
    @BindView(R.id.pickup)
    TextView pickup;
    @BindView(R.id.dropoffcity)
    TextView dropoffcity;
    @BindView(R.id.dropoff)
    TextView dropoff;
    @BindView(R.id.busimg)
    CircleImageView busimg;
    @BindView(R.id.vechialtype)
    TextView vechialtype;
    @BindView(R.id.vechialmodel)
    TextView vechialmodel;
    @BindView(R.id.plateno)
    TextView plateno;
    @BindView(R.id.vechialrating)
    RatingBar vechialrating;
    @BindView(R.id.vechilecomm)
    EditText vechilecomm;
    @BindView(R.id.driverimg)
    CircleImageView driverimg;
    @BindView(R.id.drivername)
    TextView drivername;
    @BindView(R.id.driverrating)
    RatingBar driverrating;
    /*@BindView(R.id.chat)
    ImageView chat;
    @BindView(R.id.call)
    ImageView call;*/
    @BindView(R.id.drivercomm)
    EditText drivercomm;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.dlbl)
    TextView dlbl;
    @BindView(R.id.plbl)
    TextView plbl;
    private OnGoingViewModel viewModel;
    private int driverid,vechialid;
    private String phone;
    private Double pickuplong,pickuplate,destlong,destlate;
    private Double tripcost;
    private Double waitingtime;
    private Double normalkm;
    private Double rushkm;
    private GoogleMap mMap;
    private Marker marker1;
    private Marker marker2;
    private final int intervaltime2 = 100;
    private int tripid;
    private HubConnection hubConnection;
    private int getTripCost=0;
    private int intervaltime=1000;
    private Double totalkm;
    private String totalmins="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_trip);
        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        viewModel = ViewModelProviders.of(this).get(OnGoingViewModel.class);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
      SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        save.setOnClickListener(this);
        /*chat.setOnClickListener(this);
        call.setOnClickListener(this);*/


        String displayLanguage = Locale.getDefault().getDisplayLanguage();
        Log.i("mylan",displayLanguage);

        if(displayLanguage.equals("العربية")){
            vechialtype.setGravity(Gravity.LEFT);
            vechialmodel.setGravity(Gravity.LEFT);
            drivername.setGravity(Gravity.LEFT);
            pickupcity.setGravity(Gravity.LEFT);
            dropoffcity.setGravity(Gravity.LEFT);
            pickup.setGravity(Gravity.RIGHT);
            dropoff.setGravity(Gravity.RIGHT);
            dlbl.setGravity(Gravity.RIGHT);
            plbl.setGravity(Gravity.RIGHT);
        }else if(displayLanguage.equals("English")){
            vechialtype.setGravity(Gravity.RIGHT);
            vechialmodel.setGravity(Gravity.RIGHT);
            drivername.setGravity(Gravity.RIGHT);

            pickupcity.setGravity(Gravity.RIGHT);
            dropoffcity.setGravity(Gravity.RIGHT);
            pickup.setGravity(Gravity.LEFT);
            dropoff.setGravity(Gravity.LEFT);
            dlbl.setGravity(Gravity.LEFT);
            plbl.setGravity(Gravity.LEFT);
        }else{
            vechialtype.setGravity(Gravity.RIGHT);
            vechialmodel.setGravity(Gravity.RIGHT);
            drivername.setGravity(Gravity.RIGHT);

            pickupcity.setGravity(Gravity.RIGHT);
            dropoffcity.setGravity(Gravity.RIGHT);
            pickup.setGravity(Gravity.LEFT);
            dropoff.setGravity(Gravity.LEFT);
            dlbl.setGravity(Gravity.LEFT);
            plbl.setGravity(Gravity.LEFT);
        }




        vechialrating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //Toast.makeText(EndTripActivity.this, "Ratging: "+rating, Toast.LENGTH_SHORT).show();
               // vechialrating.setRating(rating);
                if(rating <= 2){
                    vechilecomm.setVisibility(View.VISIBLE);
                }else{
                    vechilecomm.setVisibility(View.INVISIBLE);
                }
            }
        });

        driverrating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //Toast.makeText(EndTripActivity.this, "Ratging: "+rating, Toast.LENGTH_SHORT).show();
               // driverrating.setRating(rating);
                if(rating <= 2){
                    drivercomm.setVisibility(View.VISIBLE);
                }else{
                    drivercomm.setVisibility(View.INVISIBLE);
                }
            }
        });

        String huburl= ApiClient.signalRBaseUrl;
        hubConnection = HubConnectionBuilder.create(huburl).build();

        //if(hubConnection.getConnectionState() == HubConnectionState.DISCONNECTED) {
        hubConnection.start();
        //}

        try {

            new CountDownTimer(5000, 1000) {

                public void onTick(long millisUntilFinished) {
                    //timer.setText("00 : " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    //timer.setText("done!");
                    try{
                        String clientidd= String.valueOf(Constants.getClientId(EndTripActivity.this));
                        hubConnection.send("OnConnectedAsync",clientidd,4);
                    }catch (Exception e){
                        //Toast.makeText(EndTripActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }.start();





        }catch (Exception e){
            Log.e("hub_error",e.getMessage().toString());
            //Toast.makeText(EndTripActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }



            Intent intent=getIntent();
        if(intent.hasExtra("driverid")){
            driverid=(int)intent.getExtras().get("driverid");
            fillDriverData();
            if(intent.hasExtra("vechialid")){
                vechialid=(int)intent.getExtras().get("vechialid");
                fillVechaelData();
            }
        }
        if(intent.hasExtra("pickuplong")){
            pickuplong=(Double) intent.getExtras().get("pickuplong");
        }
        if(intent.hasExtra("pickuplate")){
            pickuplate=(Double) intent.getExtras().get("pickuplate");
        }


        if(intent.hasExtra("destlong")){
            destlong=(Double) intent.getExtras().get("destlong");
        }
        if(intent.hasExtra("destlate")){
            destlate=(Double) intent.getExtras().get("destlate");
        }

         getdropoffaddress();
        getdropoffcity();
        getpickupaddress();
        getpickupcity();

        if(intent.hasExtra("tripcost")){
            tripcost=(Double) intent.getExtras().get("tripcost");
            moneysum.setText(tripcost+"");
        }
       if(intent.hasExtra("waitingtime")){
            waitingtime=(Double) intent.getExtras().get("waitingtime");
            waitsum.setText(waitingtime+"");
        }
        if(intent.hasExtra("normalkm")){
            normalkm=(Double) intent.getExtras().get("normalkm");
            //kmsum.setText(normalkm+"");
        }
        if(intent.hasExtra("rushkm")){
            rushkm=(Double) intent.getExtras().get("rushkm");
        }

        if(intent.hasExtra("tripid")){
            tripid=(int)intent.getExtras().get("tripid");
            //fillChatDat();
        }

        if(intent.hasExtra("totalkm")){
            totalkm=(Double) intent.getExtras().get("totalkm");
            kmsum.setText(totalkm+"");
        }

        if(intent.hasExtra("totalmins")){
            totalmins=(String) intent.getExtras().get("totalmins");
            minsum.setText(totalmins+"");
        }

        
        


        try {



       /* hubConnection.on("NotifiedClientTripCost", (TripCost, TotalWaitingMinutes, NormalKM ,RusKM) -> {
            //Log.i("LocClient",latitude + "," + longitude);
            Log.i("tripcost", TripCost + " , " + TotalWaitingMinutes + " , " + NormalKM + " , " + RusKM);
            tripcost=TripCost;
            waitingtime=TotalWaitingMinutes;
            normalkm=NormalKM;
            rushkm=RusKM;

            getTripCost=1;


        }, Double.class, Double.class, Double.class, Double.class);*/


        final int delay2=intervaltime * 1;

        final Handler handler2 = new Handler();
        //int delay = 1000; //milliseconds

        handler2.postDelayed(new Runnable(){
            public void run(){


                //do something


               /* if(getTripCost == 1){
                    moneysum.setText(tripcost+"");
                    getTripCost=0;
                }*/



                handler2.postDelayed(this, delay2);
            }
        }, delay2);


    }catch (Exception e){
        Log.e("hub_error",e.getMessage().toString());
        //Toast.makeText(EndTripActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
    }






    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.save){

            int clientid= Constants.getClientId(EndTripActivity.this);
            int vihealrate= (int) vechialrating.getRating();
            int driverrate=(int)driverrating.getRating();
            String driverco=drivercomm.getText().toString();
            String vichealco=vechilecomm.getText().toString();

            if(vihealrate <= 0){
                Toast.makeText(EndTripActivity.this, "You must rate vicheal to end trip", Toast.LENGTH_SHORT).show();
            }else if(driverrate <= 0){
                Toast.makeText(EndTripActivity.this, "You must rate driver to end trip", Toast.LENGTH_SHORT).show();
            }
            else if(vihealrate < 3 && vichealco.equals("") ){
                Toast.makeText(EndTripActivity.this, "You must tell us the reason of your rating  to end trip", Toast.LENGTH_SHORT).show();
            }else if(driverrate < 3 && driverco.equals("")){
                Toast.makeText(EndTripActivity.this, "You must tell us the reason of your rating  to end trip", Toast.LENGTH_SHORT).show();
            }
            else {

                TripRatesResponse tripRatesResponse = new TripRatesResponse(driverid, driverco, driverrate, vichealco, tripid, clientid, 5, vechialid, vihealrate);
                viewModel.clientRateDriverandVechieal(tripRatesResponse);

                viewModel.tripRatesResponseMutableLiveData.observe(EndTripActivity.this, new Observer<TripRatesResponse>() {
                    @Override
                    public void onChanged(TripRatesResponse tripRatesResponse) {
                        if (tripRatesResponse != null) {
                            Toast.makeText(EndTripActivity.this, "Rating added sucessfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                Intent i = new Intent(EndTripActivity.this, MainClientActivity.class);
                startActivity(i);
            }
        }/*else if(v.getId() == R.id.chat){
            Uri uri = Uri.parse("smsto:" + phone);
            Intent i = new Intent(Intent.ACTION_SENDTO, uri);
            i.setPackage("com.whatsapp");
            startActivity(Intent.createChooser(i, ""));
        }else if(v.getId() == R.id.call){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
            startActivity(intent);
        }*/
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(EndTripActivity.this, R.raw.my_map_style);
        mMap.setMapStyle(style);


        // Enable the zoom controls for the map
        mMap.getUiSettings().setZoomControlsEnabled(true);
        /*googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setTrafficEnabled(true);*/
        mMap.getUiSettings().setMapToolbarEnabled(true);


        //LatLng barcelona = new LatLng(driverlate, driverlong);
        LatLng barcelona = new LatLng(pickuplate, pickuplong);
        //mMap.addMarker(new MarkerOptions().position(barcelona).title("First Place"));


        int height1 = 100;
        int width1 = 85;
        BitmapDrawable bitmapdraw1 = (BitmapDrawable) getResources().getDrawable(R.mipmap.vector_smart_object);
        Bitmap b1 = bitmapdraw1.getBitmap();
        Bitmap smallMarker1 = Bitmap.createScaledBitmap(b1, width1, height1, false);


        int height = 90;
        int width = 75;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.mipmap.pin);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);


        marker1=mMap.addMarker(new MarkerOptions().position(barcelona).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker1)));

        LatLng madrid = new LatLng(destlate, destlong);
        //mMap.addMarker(new MarkerOptions().position(madrid).title("Second Place"));

        marker2=mMap.addMarker(new MarkerOptions().position(madrid).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

        //LatLng curloc = new LatLng(latitude, longitude);

        //Define list to get all latlng for the route
        List<LatLng> path = new ArrayList();


        //Execute Directions API request
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(MapApiClient.GooglemapKey)
                .build();

        //String origin = driverlate + "," + driverlong;
        String origin = pickuplate + "," + pickuplong;
        String destination = destlate + "," + destlong;

        //DirectionsApiRequest req = DirectionsApi.getDirections(context, "41.385064,2.173403", "40.416775,-3.70379");
        DirectionsApiRequest req = DirectionsApi.getDirections(context, origin, destination);

        try {
            DirectionsResult res = req.await();

            //Loop through legs and steps to get encoded polylines of each step
            if (res.routes != null && res.routes.length > 0) {
                DirectionsRoute route = res.routes[0];

                if (route.legs != null) {
                    for (int i = 0; i < route.legs.length; i++) {
                        DirectionsLeg leg = route.legs[i];
                        if (leg.steps != null) {
                            for (int j = 0; j < leg.steps.length; j++) {
                                DirectionsStep step = leg.steps[j];
                                if (step.steps != null && step.steps.length > 0) {
                                    for (int k = 0; k < step.steps.length; k++) {
                                        DirectionsStep step1 = step.steps[k];
                                        EncodedPolyline points1 = step1.polyline;
                                        if (points1 != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                            }
                                        }
                                    }
                                } else {
                                    EncodedPolyline points = step.polyline;
                                    if (points != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                        for (com.google.maps.model.LatLng coord : coords) {
                                            path.add(new LatLng(coord.lat, coord.lng));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("t", ex.getLocalizedMessage());
        }

        //Draw the polyline
        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLACK).width(10);
            mMap.addPolyline(opts);
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);


        LatLng curloc = new LatLng((pickuplate + destlate) / 2, (pickuplong + destlong) / 2);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curloc, 10));

        String key = MapApiClient.GooglemapKey;

        viewModel.GetDistanceAndTime(origin, destination, key);

        viewModel.googleMapsResponseMutableLiveData.observe(EndTripActivity.this, new Observer<GoogleMapsResponse>() {
            @Override
            public void onChanged(GoogleMapsResponse googleMapsResponse) {
                Route route = googleMapsResponse.getRoutes().get(0);
                Leg leg = route.getLegs().get(0);
                Distance distance = leg.getDistance();
                Integer value = distance.getValue();
                Double Mydistance = Double.valueOf(value / 1000);

                Duration duration = leg.getDuration();
                Integer value1 = duration.getValue();
                Double mymints = Double.valueOf(value1 / 60);

                AlertDialog.Builder builder = new AlertDialog.Builder(EndTripActivity.this);
                builder.setTitle("Trip distance and time");
                builder.setMessage("Distance: " + Mydistance + " Km\nDuration: " + mymints + " Mins");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });









            }
        });
    }

    private void fillDriverData() {
        viewModel.getDriverData(driverid);
        viewModel.driverDataResponseMutableLiveData.observe(EndTripActivity.this, new Observer<DriverDataResponse>() {
            @Override
            public void onChanged(DriverDataResponse driverDataResponse) {
                if(driverDataResponse != null){
                    drivername.setText(driverDataResponse.getDriverName());
                    phone=driverDataResponse.getDriverMobile();

                    String imagepath = "http://aenee.app.192-185-7-211.hgws27.hgwin.temp.domains/" + driverDataResponse.getDriverImage();

                    Glide
                            .with(EndTripActivity.this)
                            .load(imagepath)
                            .centerCrop()
                            //.placeholder(R.drawable.loading_spinner)
                            .into(driverimg);

                    //driverrating.setRating(driverDataResponse.getDriverAvgRate()/2);


                }
            }
        });
    }

    private void fillVechaelData() {
        viewModel.getVechaelData(vechialid);
        viewModel.vechaelResponseMutableLiveData.observe(EndTripActivity.this, new Observer<VechaelResponse>() {
            @Override
            public void onChanged(VechaelResponse vechaelResponse) {
                if(vechaelResponse != null){
                    vechialmodel.setText(vechaelResponse.getVehicleModel());
                    vechialtype.setText(vechaelResponse.getVehicleBrand());
                    //vechialrating.setRating(vechaelResponse.getVehicleAvgRate());
                    plateno.setText(vechaelResponse.getVehicleNumber());

                    String vehicleColorHexa = vechaelResponse.getVehicleColorHexa();



                    //holder.busimg.getDrawable().setColorFilter(Color.parseColor(vehicleColorHexa), PorterDuff.Mode.MULTIPLY );
                    //holder.busimg.getDrawable().setColorFilter(Color.parseColor(vehicleColorHexa), PorterDuff.Mode.SCREEN );

                    busimg.setColorFilter(Color.parseColor(vehicleColorHexa), PorterDuff.Mode.MULTIPLY);
                }
            }
        });
    }

    private void getpickupaddress() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(pickuplate, pickuplong, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            String myaddr="";
            if (address.length() > 45)
            {
                myaddr = address.substring(0, 45) + " ...";
            }
            else
            {
                myaddr = address;
            }
            pickup.setText(myaddr);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void getpickupcity() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(pickuplate, pickuplong, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            pickupcity.setText(city);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void getdropoffaddress() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            //addresses = geocoder.getFromLocation(driverlate, driverlong, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            addresses = geocoder.getFromLocation(destlate, destlong, 1);
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            String myaddr="";
            if (address.length() > 45)
            {
                myaddr = address.substring(0, 45) + " ...";
            }
            else
            {
                myaddr = address;
            }
            dropoff.setText(myaddr);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void getdropoffcity() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            //addresses = geocoder.getFromLocation(driverlate, driverlong, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            addresses = geocoder.getFromLocation(destlate, destlong, 1);
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            dropoffcity.setText(city);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onBackPressed() {
        /*if (!shouldAllowBack()) {
            doSomething();
        } else {
            super.onBackPressed();
        }*/
    }


}

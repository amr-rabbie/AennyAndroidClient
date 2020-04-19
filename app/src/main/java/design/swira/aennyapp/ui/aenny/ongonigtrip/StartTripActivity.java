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
import android.widget.Button;
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
import com.microsoft.signalr.HubConnectionState;

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
import design.swira.aennyapp.pojo.aenny.vechaels.VechaelResponse;
import design.swira.aennyapp.ui.aenny.chat.ChatActivity;
import design.swira.aennyapp.ui.aenny.clientfavourites.AddClientFavouriteDialogActivity2;
import design.swira.aennyapp.ui.aenny.mainpage.MainClientActivity;
import design.swira.aennyapp.utils.Constants;
import design.swira.aennyapp.utils.GpsTracker;
import design.swira.aennyapp.utils.Network;

public class StartTripActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener , GoogleMap.OnInfoWindowClickListener {

    @BindView(R.id.curplace)
    ImageView curplace;
    @BindView(R.id.changestyle)
    ImageView changestyle;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.distance)
    TextView distancee;
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
    @BindView(R.id.driverimg)
    CircleImageView driverimg;
    @BindView(R.id.drivername)
    TextView drivername;
    @BindView(R.id.driverrating)
    RatingBar driverrating;
    @BindView(R.id.chat)
    ImageView chat;
    @BindView(R.id.call)
    ImageView call;
    @BindView(R.id.share)
    Button share;
    private GoogleMap mMap;
    private GpsTracker gpsTracker;
    private double latitude;
    private double longitude;
    private double oldlatitude;
    private double oldlongitude;
    private double newlatitude;
    private double newlongitude;
    String ori, des;
    private int markerclicked;
    private Marker marker1;
    private Marker marker2;
    OnGoingViewModel viewModel;
    String phone="0000000000";
    private HubConnection hubConnection;
    double driverlong,driverlate;
    int driverid;
    int vechialid;
    private Double pickuplong;
    private Double pickuplate;
    private Double destlong;
    private Double destlate;
    private int locchanged=0;
    private final int intervaltime = 1000;
    private Double tripcost;
    private Double waitingtime;
    private Double normalkm;
    private Double rushkm;
    private int getTripCost=0;
    private final int intervaltime2 = 100;
    private int tripid;
    private int getnewmsg=0;
    private double totalkm;
    private String totalmins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_trip);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(OnGoingViewModel.class);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        String displayLanguage = Locale.getDefault().getDisplayLanguage();
        Log.i("mylan",displayLanguage);

        if(displayLanguage.equals("العربية")){
            vechialtype.setGravity(Gravity.LEFT);
            vechialmodel.setGravity(Gravity.LEFT);
            drivername.setGravity(Gravity.LEFT);
        }else if(displayLanguage.equals("English")){
            vechialtype.setGravity(Gravity.RIGHT);
            vechialmodel.setGravity(Gravity.RIGHT);
            drivername.setGravity(Gravity.RIGHT);
        }else{
            vechialtype.setGravity(Gravity.RIGHT);
            vechialmodel.setGravity(Gravity.RIGHT);
            drivername.setGravity(Gravity.RIGHT);
        }


        share.setOnClickListener(this);
        chat.setOnClickListener(this);
        call.setOnClickListener(this);
        changestyle.setOnClickListener(this);
        curplace.setOnClickListener(this);

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
        if(intent.hasExtra("driverlong")){
            driverlong=(Double) intent.getExtras().get("driverlong");
        }
        if(intent.hasExtra("driverlate")){
            driverlate=(Double) intent.getExtras().get("driverlate");
        }

        if(intent.hasExtra("tripid")){
            tripid=(int)intent.getExtras().get("tripid");
            //fillChatDat();
        }

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
                        String clientidd= String.valueOf(Constants.getClientId(StartTripActivity.this));
                        hubConnection.send("OnConnectedAsync",clientidd,4);
                    }catch (Exception e){
                        //Toast.makeText(StartTripActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }.start();



            hubConnection.on("NotifiedCurrenDriverLocationForClient", (Driver_Id, Driver_Pickup_Long, Driver_Pickup_Latt) -> {
                //Log.i("LocClient",latitude + "," + longitude);
                Log.i("DriverLoc", Driver_Id + " , " + Driver_Pickup_Long + "," + Driver_Pickup_Latt);
                driverlong=Driver_Pickup_Long;
                driverlate=Driver_Pickup_Latt;

                if(driverlong > 0 && driverlate > 0) {

                    locchanged=1;
                }


            }, Integer.class, Double.class, Double.class);


            hubConnection.on("NotifiedClientTripCost", (TripCost, TotalWaitingMinutes, NormalKM ,RusKM , TotalMinutes) -> {
                //Log.i("LocClient",latitude + "," + longitude);
                Log.i("tripcost", TripCost + " , " + TotalWaitingMinutes + " , " + NormalKM + " , " + RusKM + " , " + TotalMinutes);
                tripcost=TripCost;
                waitingtime=TotalWaitingMinutes;
                normalkm=NormalKM;
                rushkm=RusKM;
                totalkm=NormalKM+RusKM;
                totalmins=TotalMinutes;



                    getTripCost=1;





            }, Double.class, Double.class, Double.class, Double.class , String.class);


            hubConnection.on("ReceiveMessage", (Trip_Id, User_Id_From, UserId_To, Message, UserName, UserTypeFrom,UserTypeTo, MessageTime) -> {
                //Log.i("LocClient",latitude + "," + longitude);
                //Log.i("chatmsg", Trip_Id + " , " + User_Id_From );
                Log.i("recvmsg",Message + MessageTime);
                if(tripid == Trip_Id){
                    getnewmsg = 1;

                }

            }, Integer.class, Integer.class, Integer.class,String.class,String.class, Integer.class,Integer.class,String.class);


            final int delay2=intervaltime * 1;

            final Handler handler2 = new Handler();
            //int delay = 1000; //milliseconds

            handler2.postDelayed(new Runnable(){
                public void run(){


                    //do something
                    if(locchanged == 1) {


                     getdriveraddress();
                        getdestaddress();
                        //draw2Points();
                        drawDriverLoc();
                        getDistanceTime();
                        locchanged = 0;
                    }

                    if(getTripCost == 1){
                        Intent intent=new Intent(StartTripActivity.this,EndTripActivity.class);
                        intent.putExtra("tripcost",tripcost);
                        intent.putExtra("waitingtime",waitingtime);
                        intent.putExtra("normalkm",normalkm);
                        intent.putExtra("rushkm",rushkm);
                        intent.putExtra("driverid",driverid);
                        intent.putExtra("vechialid",vechialid);
                        intent.putExtra("pickuplong",pickuplong);
                        intent.putExtra("pickuplate",pickuplate);
                        intent.putExtra("destlong",destlong);
                        intent.putExtra("destlate",destlate);
                        intent.putExtra("tripid",tripid);
                        intent.putExtra("totalkm",totalkm);
                        intent.putExtra("totalmins",totalmins);


                        startActivity(intent);
                        getTripCost=0;
                    }

                    if (getnewmsg == 1) {
                        openChat();
                        getnewmsg = 0;
                    }


                    handler2.postDelayed(this, delay2);
                }
            }, delay2);


            /*hubConnection.on("NotifiedClientDriverArrived",() -> {


                Log.i("DriverArrived","DriverArrived");




            });*/





        }catch (Exception e){
            Log.e("hub_error",e.getMessage().toString());
            //Toast.makeText(StartTripActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }



    }

    private void openChat() {
        Intent intenttt=new Intent(StartTripActivity.this, ChatActivity.class);
        intenttt.putExtra("tripid", tripid);
        intenttt.putExtra("driverid", driverid);
        intenttt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivityForResult(intenttt,1);
    }

    private void getDistanceTime() {
        String key = MapApiClient.GooglemapKey;

        String origin = driverlate + "," + driverlong;
        String destination = destlate + "," + destlong;

        viewModel.GetDistanceAndTime(origin, destination, key);

        viewModel.googleMapsResponseMutableLiveData.observe(StartTripActivity.this, new Observer<GoogleMapsResponse>() {
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

                AlertDialog.Builder builder = new AlertDialog.Builder(StartTripActivity.this);
                builder.setTitle("Trip distance and time");
                builder.setMessage("Distance: " + Mydistance + " Km\nDuration: " + mymints + " Mins");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                time.setText(mymints+" Minutes");
                distancee.setText(Mydistance+" Km");

            }
        });

    }

    private void drawDriverLoc() {
        /*int height1 = 100;
        int width1 = 85;
        BitmapDrawable bitmapdraw1 = (BitmapDrawable) getResources().getDrawable(R.mipmap.vector_smart_object);
        Bitmap b1 = bitmapdraw1.getBitmap();
        Bitmap smallMarker1 = Bitmap.createScaledBitmap(b1, width1, height1, false);*/

        LatLng driverloc = new LatLng(driverlate, driverlong);
        marker1.setPosition(driverloc);
        //Toast.makeText(StartTripActivity.this, "Yours location now: "+driverlate+","+driverlong, Toast.LENGTH_SHORT).show();

        //mMap.addMarker(new MarkerOptions().position(madrid).title("Second Place"));
       /* marker1=mMap.addMarker(new MarkerOptions().position(barcelona).title(ori).icon(BitmapDescriptorFactory.fromBitmap(smallMarker1)));

        LatLng curloc = new LatLng((latitude + driverlate) / 2, (longitude + driverlong) / 2);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curloc, 12));*/


    }

    private void draw2Points() {
        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(StartTripActivity.this, R.raw.my_map_style);
        mMap.setMapStyle(style);

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/


        /*LatLng myloc = new LatLng(oldlatitude, oldlongitude);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(myloc).zoom(14).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(oldlatitude, oldlongitude), new LatLng(newlatitude, newlongitude))
                .width(5)
                .color(Color.RED));*/

        // Enable the zoom controls for the map
        mMap.getUiSettings().setZoomControlsEnabled(true);
        /*googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setTrafficEnabled(true);*/
        mMap.getUiSettings().setMapToolbarEnabled(true);


        //LatLng barcelona = new LatLng(driverlate, driverlong);
        LatLng barcelona = new LatLng(driverlate, driverlong);
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


        marker1=mMap.addMarker(new MarkerOptions().position(barcelona).title(ori).icon(BitmapDescriptorFactory.fromBitmap(smallMarker1)));

        LatLng madrid = new LatLng(destlate, destlong);
        //mMap.addMarker(new MarkerOptions().position(madrid).title("Second Place"));
        marker2=mMap.addMarker(new MarkerOptions().position(madrid).title(des).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

        LatLng curloc = new LatLng(latitude, longitude);

        //Define list to get all latlng for the route
        List<LatLng> path = new ArrayList();


        //Execute Directions API request
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(MapApiClient.GooglemapKey)
                .build();

        //String origin = driverlate + "," + driverlong;
        String origin = driverlate + "," + driverlong;
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

        //curloc = new LatLng((driverlate + destlate) / 2, (driverlong + destlong) / 2);
        curloc = new LatLng((driverlate + destlate) / 2, (driverlong + destlong) / 2);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curloc, 12));

        String key = MapApiClient.GooglemapKey;

        viewModel.GetDistanceAndTime(origin, destination, key);

        viewModel.googleMapsResponseMutableLiveData.observe(StartTripActivity.this, new Observer<GoogleMapsResponse>() {
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

                AlertDialog.Builder builder = new AlertDialog.Builder(StartTripActivity.this);
                builder.setTitle("Trip distance and time");
                builder.setMessage("Distance: " + Mydistance + " Km\nDuration: " + mymints + " Mins");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                time.setText(mymints+" Minutes");
                distancee.setText(Mydistance+" KM");




                //calcatePrice(Mydistance);

                //builder.create().show();


            }
        });


        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker arg0) {

                // Getting view from the layout file infowindowlayout.xml
                View v = getLayoutInflater().inflate(R.layout.custom_window_info, null);

                LatLng latLng = arg0.getPosition();


                TextView address = (TextView) v.findViewById(R.id.address);
                //TextView tv2 = (TextView) v.findViewById(R.id.textView2);
                /*String title=arg0.getTitle();
                String informations=arg0.getSnippet();

                tv1.setText(title);
                tv2.setText(informations);*/




                if(onMarkerClick(arg0)==true && markerclicked==1){
                    address.setText(ori);

                   /* address.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {*/
                            /*Intent ii = new Intent(SecondStepMapActivity.this, AddClientFavouriteDialogActivity2.class);
                            ii.putExtra("longitude", oldlongitude);
                            ii.putExtra("latitude", oldlatitude);
                            ii.putExtra("addresss", ori);
                            startActivity(ii);*/
                       /* }
                    });*/
                }else if(onMarkerClick(arg0)==true && markerclicked==2){
                    address.setText(des);

                    /*address.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {*/
                           /* Intent ii = new Intent(SecondStepMapActivity.this, AddClientFavouriteDialogActivity2.class);
                            ii.putExtra("longitude", newlongitude);
                            ii.putExtra("latitude", newlatitude);
                            ii.putExtra("addresss", des);
                            startActivity(ii);*/
                       /* }
                    });*/
                }




                return v;

            }
        });


        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                /*LatLng latLon = marker.getPosition();

                //Cycle through places array
                for(Place place : places){
                    if (latLon.equals(place.latlng)){
                        //match found!  Do something....
                    }

                }*/


                if(markerclicked==1){
                    Intent ii = new Intent(StartTripActivity.this, AddClientFavouriteDialogActivity2.class);
                    /*ii.putExtra("longitude", driverlong);
                    ii.putExtra("latitude", driverlate);*/
                    ii.putExtra("longitude", driverlong);
                    ii.putExtra("latitude", driverlate);
                    ii.putExtra("addresss", ori);
                    startActivity(ii);
                }else if(markerclicked==2){
                    Intent ii = new Intent(StartTripActivity.this, AddClientFavouriteDialogActivity2.class);
                    ii.putExtra("longitude", destlong);
                    ii.putExtra("latitude", destlate);
                    ii.putExtra("addresss", des);
                    startActivity(ii);
                }
            }
        });
    }

    private void fillVechaelData() {
        viewModel.getVechaelData(vechialid);
        viewModel.vechaelResponseMutableLiveData.observe(StartTripActivity.this, new Observer<VechaelResponse>() {
            @Override
            public void onChanged(VechaelResponse vechaelResponse) {
                if(vechaelResponse != null){
                    vechialmodel.setText(vechaelResponse.getVehicleModel());
                    vechialtype.setText(vechaelResponse.getVehicleBrand());
                    vechialrating.setRating(vechaelResponse.getVehicleAvgRate());
                    plateno.setText(vechaelResponse.getVehicleNumber());

                    String vehicleColorHexa = vechaelResponse.getVehicleColorHexa();



                    //holder.busimg.getDrawable().setColorFilter(Color.parseColor(vehicleColorHexa), PorterDuff.Mode.MULTIPLY );
                    //holder.busimg.getDrawable().setColorFilter(Color.parseColor(vehicleColorHexa), PorterDuff.Mode.SCREEN );

                    busimg.setColorFilter(Color.parseColor(vehicleColorHexa), PorterDuff.Mode.MULTIPLY);
                }
            }
        });
    }

    private void fillDriverData() {
        viewModel.getDriverData(driverid);
        viewModel.driverDataResponseMutableLiveData.observe(StartTripActivity.this, new Observer<DriverDataResponse>() {
            @Override
            public void onChanged(DriverDataResponse driverDataResponse) {
                if(driverDataResponse != null){
                    drivername.setText(driverDataResponse.getDriverName());
                    phone=driverDataResponse.getDriverMobile();

                    String imagepath = "http://aenee.app.192-185-7-211.hgws27.hgwin.temp.domains/" + driverDataResponse.getDriverImage();

                    Glide
                            .with(StartTripActivity.this)
                            .load(imagepath)
                            .centerCrop()
                            //.placeholder(R.drawable.loading_spinner)
                            .into(driverimg);

                    driverrating.setRating(driverDataResponse.getDriverAvgRate()/2);


                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share:


                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is live trip tracking link!");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);


                break;
            case R.id.chat:
                /*Uri uri = Uri.parse("smsto:" + phone);
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(i, ""));*/
                Intent intenttt=new Intent(StartTripActivity.this, ChatActivity.class);
                intenttt.putExtra("tripid", tripid);
                intenttt.putExtra("driverid", driverid);
                intenttt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intenttt,1);
                break;
            case R.id.call:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                startActivity(intent);
                break;
            case R.id.changestyle:
                if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                } else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
                break;
            case R.id.curplace:
                getLocation();
                MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(StartTripActivity.this, R.raw.my_map_style);
                mMap.setMapStyle(style);


                int height2 = 1;
                int width2 = 1;
                BitmapDrawable bitmapdraw2 = (BitmapDrawable) getResources().getDrawable(R.mipmap.pin);
                Bitmap b2 = bitmapdraw2.getBitmap();
                Bitmap smallMarker2 = Bitmap.createScaledBitmap(b2, width2, height2, false);
                /*LatLng myloc = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(myloc).icon(BitmapDescriptorFactory.fromBitmap(smallMarker2)).title("My Location"));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(myloc).zoom(15).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/

                LatLng curloc = new LatLng((oldlatitude + newlatitude) / 2, (oldlongitude + newlongitude) / 2);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curloc, 12));

                break;
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(StartTripActivity.this, R.raw.my_map_style);
        mMap.setMapStyle(style);

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/


        /*LatLng myloc = new LatLng(oldlatitude, oldlongitude);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(myloc).zoom(14).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(oldlatitude, oldlongitude), new LatLng(newlatitude, newlongitude))
                .width(5)
                .color(Color.RED));*/

        // Enable the zoom controls for the map
        mMap.getUiSettings().setZoomControlsEnabled(true);
        /*googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setTrafficEnabled(true);*/
        mMap.getUiSettings().setMapToolbarEnabled(true);


        //LatLng barcelona = new LatLng(driverlate, driverlong);
        LatLng barcelona = new LatLng(driverlate, driverlong);
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


        marker1=mMap.addMarker(new MarkerOptions().position(barcelona).title(ori).icon(BitmapDescriptorFactory.fromBitmap(smallMarker1)));

        LatLng madrid = new LatLng(destlate, destlong);
        //mMap.addMarker(new MarkerOptions().position(madrid).title("Second Place"));
        marker2=mMap.addMarker(new MarkerOptions().position(madrid).title(des).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

        LatLng curloc = new LatLng(latitude, longitude);

        //Define list to get all latlng for the route
        List<LatLng> path = new ArrayList();


        //Execute Directions API request
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(MapApiClient.GooglemapKey)
                .build();

        //String origin = driverlate + "," + driverlong;
        String origin = driverlate + "," + driverlong;
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

        //curloc = new LatLng((driverlate + destlate) / 2, (driverlong + destlong) / 2);
        curloc = new LatLng((driverlate + destlate) / 2, (driverlong + destlong) / 2);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curloc, 12));

        String key = MapApiClient.GooglemapKey;

        viewModel.GetDistanceAndTime(origin, destination, key);

        viewModel.googleMapsResponseMutableLiveData.observe(StartTripActivity.this, new Observer<GoogleMapsResponse>() {
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

                AlertDialog.Builder builder = new AlertDialog.Builder(StartTripActivity.this);
                builder.setTitle("Trip distance and time");
                builder.setMessage("Distance: " + Mydistance + " Km\nDuration: " + mymints + " Mins");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                time.setText(mymints+" Minutes");
                distancee.setText(Mydistance+" Km");




                //calcatePrice(Mydistance);

                //builder.create().show();


            }
        });


        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker arg0) {

                // Getting view from the layout file infowindowlayout.xml
                View v = getLayoutInflater().inflate(R.layout.custom_window_info, null);

                LatLng latLng = arg0.getPosition();


                TextView address = (TextView) v.findViewById(R.id.address);
                //TextView tv2 = (TextView) v.findViewById(R.id.textView2);
                /*String title=arg0.getTitle();
                String informations=arg0.getSnippet();

                tv1.setText(title);
                tv2.setText(informations);*/




                if(onMarkerClick(arg0)==true && markerclicked==1){
                    address.setText(ori);

                   /* address.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {*/
                            /*Intent ii = new Intent(SecondStepMapActivity.this, AddClientFavouriteDialogActivity2.class);
                            ii.putExtra("longitude", oldlongitude);
                            ii.putExtra("latitude", oldlatitude);
                            ii.putExtra("addresss", ori);
                            startActivity(ii);*/
                       /* }
                    });*/
                }else if(onMarkerClick(arg0)==true && markerclicked==2){
                    address.setText(des);

                    /*address.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {*/
                           /* Intent ii = new Intent(SecondStepMapActivity.this, AddClientFavouriteDialogActivity2.class);
                            ii.putExtra("longitude", newlongitude);
                            ii.putExtra("latitude", newlatitude);
                            ii.putExtra("addresss", des);
                            startActivity(ii);*/
                       /* }
                    });*/
                }




                return v;

            }
        });


        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                /*LatLng latLon = marker.getPosition();

                //Cycle through places array
                for(Place place : places){
                    if (latLon.equals(place.latlng)){
                        //match found!  Do something....
                    }

                }*/


                if(markerclicked==1){
                    Intent ii = new Intent(StartTripActivity.this, AddClientFavouriteDialogActivity2.class);
                    /*ii.putExtra("longitude", driverlong);
                    ii.putExtra("latitude", driverlate);*/
                    ii.putExtra("longitude", driverlong);
                    ii.putExtra("latitude", driverlate);
                    ii.putExtra("addresss", ori);
                    startActivity(ii);
                }else if(markerclicked==2){
                    Intent ii = new Intent(StartTripActivity.this, AddClientFavouriteDialogActivity2.class);
                    ii.putExtra("longitude", destlong);
                    ii.putExtra("latitude", destlate);
                    ii.putExtra("addresss", des);
                    startActivity(ii);
                }
            }
        });
    }


   /* public void getLocation() {

        oldlatitude = 33.312805;
        oldlongitude = 44.361488;

        if (!Network.isNetworkAvailable(StartTripActivity.this)) {
            oldlatitude = 33.312805;
            oldlongitude = 44.361488;
            return;
        } else {

            gpsTracker = new GpsTracker(StartTripActivity.this);
            if (gpsTracker.canGetLocation()) {
                oldlatitude = gpsTracker.getLatitude();
                oldlongitude = gpsTracker.getLongitude();

            } else {
                gpsTracker.showSettingsAlert();
            }
        }


        //Toast.makeText(TestMapsActivity.this, "latitude: " + latitude + " - longitude: " + longitude, Toast.LENGTH_SHORT).show();
    }*/

    public boolean onMarkerClick(final Marker marker) {

        if (marker.equals(marker1))
        {
            markerclicked=1;
            return true;
        }else if(marker.equals(marker2)){
            markerclicked=2;
            return true;
        }
        return false;
    }

    public void getLocation() {

        latitude = 0.0;
        longitude = 0.0;

        if (!Network.isNetworkAvailable(StartTripActivity.this)) {
            latitude = 0.0;
            longitude = 0.0;
            return;
        } else {

            gpsTracker = new GpsTracker(StartTripActivity.this);
            if (gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();

            } else {
                gpsTracker.showSettingsAlert();
            }
        }


        //Toast.makeText(TestMapsActivity.this, "latitude: " + latitude + " - longitude: " + longitude, Toast.LENGTH_SHORT).show();
    }

    private void getdestaddress() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(destlate, destlong, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            des=knownName;

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void getdriveraddress() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            //addresses = geocoder.getFromLocation(driverlate, driverlong, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            addresses = geocoder.getFromLocation(driverlate, driverlong, 1);
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            ori=knownName;

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        if (markerclicked == 1)
        {
            Intent ii = new Intent(StartTripActivity.this, AddClientFavouriteDialogActivity2.class);
            ii.putExtra("longitude", driverlong);
            ii.putExtra("latitude", driverlate);
            ii.putExtra("addresss", ori);
            startActivity(ii);
        }else if(markerclicked == 2){
            Intent ii = new Intent(StartTripActivity.this, AddClientFavouriteDialogActivity2.class);
            ii.putExtra("longitude", destlong);
            ii.putExtra("latitude", destlate);
            ii.putExtra("addresss", des);
            startActivity(ii);
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

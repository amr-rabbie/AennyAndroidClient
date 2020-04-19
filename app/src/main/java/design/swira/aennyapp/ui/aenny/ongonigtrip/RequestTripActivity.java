package design.swira.aennyapp.ui.aenny.ongonigtrip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.microsoft.signalr.HubConnectionState;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import design.swira.aennyapp.R;
import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.databinding.ActivityRequestTripBinding;
import design.swira.aennyapp.pojo.aenny.newscduletrip.AddScduleTrip;
import design.swira.aennyapp.pojo.aenny.normaltrips.AddNormalTrip;
import design.swira.aennyapp.pojo.aenny.normaltrips.AddNormalTripResponse;
import design.swira.aennyapp.pojo.aenny.ntrips.AddNTrips;
import design.swira.aennyapp.pojo.aenny.ntrips.AddNTripsResponse;
import design.swira.aennyapp.ui.aenny.mainpage.MainClientActivity;
import design.swira.aennyapp.utils.Constants;

public class RequestTripActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityRequestTripBinding binding;
    private HubConnection hubConnection;
    int driverid;
    int vechialid;
    double longitude,latitude;
    private Double driverlong;
    private Double driverlate;
    int getdriver=0;
    private final int intervaltime = 1000;
    private Double pickuplong;
    private Double pickuplate;
    private Double destlong;
    private Double destlate;
    private int openconn=0;
    private String starttime;
    private String cancelTime;
    private int drivercanceled=0;
    private int locchanged=0;
    private final int intervaltime2 = 100;
    private String visaname;
    private int paidtype;
    private String ori;
    private String des;
    private int healthynum;
    private int handcupnum;
    private Double cost;
    private Double distance;
    private String selecteddate,selectedtime;
    OnGoingViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        //setContentView(R.layout.activity_request_trip);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_request_trip);

        viewModel= ViewModelProviders.of(this).get(OnGoingViewModel.class);

        binding.cancel.setOnClickListener(this);

        Intent intent=getIntent();
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

        if(intent.hasExtra("visaname")){
            visaname=(String) intent.getExtras().get("visaname");
        }
        if(intent.hasExtra("paidtype")){
            paidtype=(int) intent.getExtras().get("paidtype");
        }
        if(intent.hasExtra("ori")){
            ori=(String) intent.getExtras().get("ori");
        }
        if(intent.hasExtra("des")){
            des=(String) intent.getExtras().get("des");
        }

        if(intent.hasExtra("healthynum")){
            healthynum=Integer.parseInt( (String) intent.getExtras().get("healthynum"));
        }
        if(intent.hasExtra("handcupnum")){
            handcupnum=Integer.parseInt( (String) intent.getExtras().get("handcupnum"));
        }
        if(intent.hasExtra("cost")){
            cost=Double.parseDouble((String) intent.getExtras().get("cost"));
        }
        if(intent.hasExtra("distance")){
            distance=Double.parseDouble((String)  intent.getExtras().get("distance"));
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
                        String clientidd= String.valueOf(Constants.getClientId(RequestTripActivity.this));
                        hubConnection.send("OnConnectedAsync",clientidd,4);
                        //openconn=1;
                    }catch (Exception e){
                       // Toast.makeText(RequestTripActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }.start();








            hubConnection.on("NotifiedForClientByNearestDriver", (Driver_Id, Vehicle_Id,StartTime,Driver_Pickup_Long,  Driver_Pickup_Latt) -> {
                //Log.i("LocClient",latitude + "," + longitude);
                Log.i("YourDriver", Driver_Id + "");



                if(Driver_Id > 0 && Vehicle_Id > 0 ){
                    driverid=Driver_Id;
                    vechialid=Vehicle_Id;
                    starttime=StartTime;

                   getdriver=1;


                }

                driverlong=Driver_Pickup_Long;
                driverlate=Driver_Pickup_Latt;

                if(driverlong > 0 && driverlate > 0) {
                    locchanged=1;
                }


            }, Integer.class,Integer.class,String.class,Double.class,Double.class);





            final int delay2=intervaltime * 1;

            final Handler handler2 = new Handler();
            //int delay = 1000; //milliseconds

            handler2.postDelayed(new Runnable(){
                public void run(){
                    //do something
                    if(getdriver == 1 && locchanged ==1) {
                        startTrip();
                        getdriver = 0;
                        locchanged=0;
                    }

                   /* if(openconn == 1){
                        openconn=2;
                    }else if(openconn == 0){
                        try{
                            String clientidd= String.valueOf(Constants.getClientId(RequestTripActivity.this));
                            hubConnection.send("OnConnectedAsync",clientidd,4);
                            openconn=2;
                        }catch (Exception e){
                            Toast.makeText(RequestTripActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }else{

                    }*/

                    if(drivercanceled == 1){
                        Toast.makeText(RequestTripActivity.this, "Driver cancel trip", Toast.LENGTH_SHORT).show();
                        cancelYourTrip();
                        drivercanceled=0;
                    }

                    handler2.postDelayed(this, delay2);
                }
            }, delay2);



            try {

                hubConnection.on("NotifiedClientDriverCancleTrip", (DoorOpenCost) -> {
                    //Log.i("LocClient",latitude + "," + longitude);
                    Log.i("dooropencost", DoorOpenCost + "");

                    drivercanceled = 1;


                }, Double.class);
            }catch (Exception e){
                Log.e("drivercancel",e.getMessage().toString());
            }


            hubConnection.on("NotifiedCurrenDriverLocationForClient", (Driver_Id, Driver_Pickup_Long, Driver_Pickup_Latt) -> {
                //Log.i("LocClient",latitude + "," + longitude);
                Log.i("DriverLoc", Driver_Id + " , " + Driver_Pickup_Long + "," + Driver_Pickup_Latt);
                driverlong=Driver_Pickup_Long;
                driverlate=Driver_Pickup_Latt;

                if(driverlong > 0 && driverlate > 0) {
                    locchanged=1;
                }


            }, Integer.class, Double.class, Double.class);








        }catch (Exception e){
            Log.e("hub_error",e.getMessage().toString());
            //Toast.makeText(RequestTripActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


        Glide
                .with(this)
                .load(R.drawable.loading_trip)
                .centerCrop()

                .into(binding.loadimg);



    }

    private void startTrip() {
        gettriptimeandDate();
        int clientid=Constants.getClientId(RequestTripActivity.this);



       /* AddNormalTrip addNormalTrip=new AddNormalTrip("",0,0,false,1,healthynum,
                ori,selecteddate,vechialid,destlate,des,destlong,paidtype,0.0,selectedtime,pickuplong,driverid,pickuplate,
                distance,visaname,0,2,clientid,handcupnum);*/

        AddNTrips addNTrips=new AddNTrips(1,healthynum,ori,selecteddate,vechialid,paidtype,selectedtime,pickuplong,driverid,pickuplate,visaname,2,clientid,handcupnum,des,destlong,destlate,distance);

        //viewModel.saveNormalTrip(addNormalTrip);
        viewModel.saveNormalTrip(addNTrips);

        viewModel.addNormalTripResponseMutableLiveData.observe(RequestTripActivity.this, new Observer<AddNTripsResponse>() {
            @Override
            public void onChanged(AddNTripsResponse addNormalTripResponse) {
                if(addNormalTripResponse != null){
                    int tripIdd = addNormalTripResponse.getTripId();



                    hubConnection.send("NotifiClientSaveTrip",driverid,tripIdd,ori,des);

                    if( tripIdd > 0) {

                        //Toast.makeText(RequestTripActivity.this, "New normal trip added", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(RequestTripActivity.this, DriverComingActivity.class);
                        intent.putExtra("driverid", driverid);
                        intent.putExtra("vechialid", vechialid);
                        intent.putExtra("pickuplong", pickuplong);
                        intent.putExtra("pickuplate", pickuplate);
                        intent.putExtra("destlong", destlong);
                        intent.putExtra("destlate", destlate);
                        intent.putExtra("starttime", starttime);
                        if (driverlong > 0 && driverlate > 0) {
                            intent.putExtra("driverlong", driverlong);
                            intent.putExtra("driverlate", driverlate);
                        }
                        intent.putExtra("tripid", tripIdd);
                        startActivity(intent);
                    }
                }
            }
        });





    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cancel){
            try {

                gettimeandDate();
                int clientid = Constants.getClientId(RequestTripActivity.this);
                //hubConnection.send("EndedTripForClient", clientid);
                hubConnection.send("NotifiClientCancleTrip", driverid,starttime,cancelTime);
                Log.i("clientcancel","Yes , " + driverid + "," +  starttime + "," + cancelTime);

            }catch (Exception e){
                Log.e("hub_error",e.getMessage().toString());
               //Toast.makeText(RequestTripActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
            Intent resultIntent = new Intent();
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }

    private void gettimeandDate() {
        //dateoftrip = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        cancelTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

    }

    private void cancelYourTrip() {
        Intent i=new Intent(RequestTripActivity.this, MainClientActivity.class);
        startActivity(i);
    }

    private void gettriptimeandDate() {
        /*selecteddate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        selectedtime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        selectedtime=selecteddate + " " + selectedtime;*/

        Date currentTime = Calendar.getInstance().getTime();
        selecteddate=currentTime.toString();
        selectedtime=currentTime.toString();
        String myFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        String mydate=sdf.format(currentTime);
        selecteddate=mydate;
        selectedtime=mydate;

        Log.i("mycurrentTime",mydate);
    }




}

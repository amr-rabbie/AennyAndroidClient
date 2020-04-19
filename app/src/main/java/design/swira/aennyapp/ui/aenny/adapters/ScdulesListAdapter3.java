package design.swira.aennyapp.ui.aenny.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.applandeo.materialcalendarview.CalendarView;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;
import design.swira.aennyapp.R;
import design.swira.aennyapp.pojo.aenny.ctrip.CompletedTripResponse;
import design.swira.aennyapp.pojo.aenny.ctrip.TripsRates;
import design.swira.aennyapp.utils.Constants;

public class ScdulesListAdapter3  extends RecyclerView.Adapter<ScdulesListAdapter3.ClientFavouriteListViewHolder> {

    List<CompletedTripResponse> list;
    Context context;

    public void setList(List<CompletedTripResponse> list) {
        this.list = list;
    }

    public ScdulesListAdapter3(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ScdulesListAdapter3.ClientFavouriteListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.scdule_trip_item3, parent, false);
        return new ScdulesListAdapter3.ClientFavouriteListViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull ScdulesListAdapter3.ClientFavouriteListViewHolder holder, int position) {

        CompletedTripResponse trip = list.get(position);

        int clientid = Constants.getClientId(context);




        String getcuraddress="";
        String getgoingaddress="";
        try {
             getcuraddress = getcuraddress(trip.getTripPickupLatt(), trip.getTripPickupLong());

            getgoingaddress = getgoingaddress(trip.getTripDestinationLatt(), trip.getTripDestinationLong());
        }catch (Exception e){

        }


        String date = trip.getTripDate();
        String time = trip.getTripTime();


        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");

        SimpleDateFormat dateFormat2 = new SimpleDateFormat(
                "MM/dd/yyyy hh:mm:ss");

        int index = time.indexOf("T");
        String myt = time.substring(index + 1);
        myt = myt.replaceAll(":00", "");
        holder.time.setText(myt);


        try {
            Date d = dateFormat.parse(date);
            String myFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            String myday = sdf.format(d);
            holder.date.setText(myday);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.handcup.setText(trip.getHandicappedNumber() + " Rider");
        holder.healthy.setText(trip.getHealthyNumber() + " Escorts");
        holder.price.setText(trip.getTripCost() + " SAR");
        holder.pickup.setText(trip.getTripPickup());
        holder.pickupcity.setText(getcuraddress);
        holder.dropoff.setText(trip.getTripDestination());
        holder.dropoffcity.setText(getgoingaddress);

        String driverName = trip.getDriver().getDriverName();


        holder.drivername.setText(driverName);

        String imagepath = "http://aenee.app.192-185-7-211.hgws27.hgwin.temp.domains/" + trip.getDriver().getDriverImage();

        Glide
                .with(context)
                .load(imagepath)
                .centerCrop()
                //.placeholder(R.drawable.loading_spinner)
                .into(holder.driverimg);

        holder.vechialtype.setText(trip.getVehicle().getVehicleBrand());
        holder.vechialmodel.setText(trip.getVehicle().getVehicleModel());
        holder.plateno.setText("Plate no "+trip.getVehicle().getVehicleNumber());

        TripsRates tripsRates = trip.getTripsRates();

        if(tripsRates != null){
            holder.vechialrating.setRating(tripsRates.getVehicleRate());
            holder.driverrating.setRating(tripsRates.getDriverRate());

        }else{
            holder.vechialrating.setRating(0);
            holder.driverrating.setRating(0);
        }


        String vehicleColorHexa = trip.getVehicle().getVehicleColorHexa();



        //holder.busimg.getDrawable().setColorFilter(Color.parseColor(vehicleColorHexa), PorterDuff.Mode.MULTIPLY );
        //holder.busimg.getDrawable().setColorFilter(Color.parseColor(vehicleColorHexa), PorterDuff.Mode.SCREEN );

        holder.busimg.setColorFilter(Color.parseColor(vehicleColorHexa), PorterDuff.Mode.MULTIPLY);





        /*String tripsRate = trip.getTripsRates();


        if( trip.getTripsRates() != null) {
            Float tripsRates = Float.valueOf(tripsRate);
            holder.personrating.setRating(tripsRates);
        }else{
            holder.personrating.setRating(0);
        }*/


                /*int tripScheduleId = trip.getTripScheduleId();

                int count=0;
                //List<String> dates=new ArrayList<>();

                for(int i=0;i<list.size();i++){
                    if(tripScheduleId == list.get(i).getTripScheduleId()){
                        count=count+1;
                        //dates.add(list.get(i).getTripDate());
                    }
                }



                holder.number.setText(count +
                        " Times");*/






                /*holder.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int id = list.get(position).getTripId();
                        //showDialog(id,"Confirm Delete","Are you sure you want to delete this client favourite ?",context);

                        Intent i = new Intent(context, UpdateRouteActivity.class);
                        i.putExtra("id", id);
                        context.startActivity(i);

                    }
                });*/

                /*holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = list.get(position).getTripId();
                        //showDialog(id,"Confirm Delete","Are you sure you want to delete this client favourite ?",context);

                        Intent i = new Intent(context, MyCustomDialogActivity.class);
                        i.putExtra("id", id);
                        i.putExtra("title", "Confirm delete");
                        i.putExtra("msg", "Are you sure you want to delete this client scdule trip ?");
                        i.putExtra("flag", "scdule");
                        context.startActivity(i);
                    }
                });*/

                /*holder.expimg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(holder.ll1.getVisibility() == View.VISIBLE){

                            int tripScheduleId = trip.getTripScheduleId();

                            //int count=0;
                            List<String> dates=new ArrayList<>();

                            for(int i=0;i<list.size();i++){
                                if(tripScheduleId == list.get(i).getTripScheduleId()){
                                    //count=count+1;
                                    dates.add(list.get(i).getTripDate());
                                }
                            }



                           // holder.number.setText(count + " Times");


                            //Toast.makeText(context, "dates:" + dates, Toast.LENGTH_SHORT).show();

                            List<EventDay> events2 = new ArrayList<>();

                            Calendar calendar = Calendar.getInstance();
                            events2.add(new EventDay(calendar, R.drawable.ic_ellipse_4));

                            //CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
                            holder.dateee.setEvents(events2);


                            List<EventDay> events = new ArrayList<>();

                            List<Calendar> calendars = new ArrayList<>();

                            for(int ii=0;ii<dates.size();ii++) {

                                SimpleDateFormat dateFormat22 = new SimpleDateFormat(
                                        "yyyy-MM-dd");

                                String dd=dates.get(ii);

                                try {
                                    Date dy = dateFormat22.parse(dd);
                                    int year = dy.getYear();
                                    int month = dy.getMonth();
                                    int day = dy.getDate();

                                    Calendar ccalendar = Calendar.getInstance();
                                    //ccalendar.set(year, month, day);
                                    ccalendar.setTime(dy);

                                    calendars.add(ccalendar);

                                    try {
                                        //holder.dateee.setDate(ccalendar);

                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }


                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }



                                holder.dateee.setSelectedDates(calendars);
                                holder.dateee.setEnabled(false);

                            }

                            holder.ll1.setVisibility(View.GONE);

                            holder.dateee.setVisibility(View.VISIBLE);
                            holder.expimg.setImageResource(R.drawable.ic_up_arrow);

                        }else{
                            holder.ll1.setVisibility(View.VISIBLE);
                            holder.dateee.setVisibility(View.GONE);
                            holder.expimg.setImageResource(R.drawable.ic_arr);
                        }
                    }
                });*/




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ClientFavouriteListViewHolder extends RecyclerView.ViewHolder {
        LinearLayout cview;
        TextView edit, delete;
        TextView time, date;
        TextView handcup, healthy;
        TextView price;
        TextView pickupcity, pickup;
        TextView dropoffcity, dropoff;
        LinearLayout ll1,ll2;
        ImageView expimg;
        TextView number;
        CalendarView dateee;
        /*TextView person;
        RatingBar personrating;*/
        //ImageView busimg;
        TextView vechialtype,vechialmodel,drivername,plateno;
        RatingBar vechialrating,driverrating;
        CircleImageView driverimg,busimg;


        public ClientFavouriteListViewHolder(@NonNull View v) {
            super(v);
            cview = v.findViewById(R.id.cview);
            /*edit = v.findViewById(R.id.edit);
            delete = v.findViewById(R.id.delete);*/

            time = v.findViewById(R.id.time);
            date = v.findViewById(R.id.date);

            handcup = v.findViewById(R.id.handcup);
            healthy = v.findViewById(R.id.healthy);

            price = v.findViewById(R.id.price);

            pickupcity = v.findViewById(R.id.pickupcity);
            pickup = v.findViewById(R.id.pickup);

            dropoffcity = v.findViewById(R.id.dropoffcity);
            dropoff = v.findViewById(R.id.dropoff);

            ll1 = v.findViewById(R.id.ll1);
            //ll2 = v.findViewById(R.id.ll2);

            /*expimg = v.findViewById(R.id.expimg);
            number = v.findViewById(R.id.number);*/

            dateee = v.findViewById(R.id.dateee);

            driverimg=v.findViewById(R.id.driverimg);
            busimg=v.findViewById(R.id.busimg);

            vechialtype=v.findViewById(R.id.vechialtype);
            vechialmodel=v.findViewById(R.id.vechialmodel);
            drivername=v.findViewById(R.id.drivername);

            vechialrating=v.findViewById(R.id.vechialrating);
            driverrating=v.findViewById(R.id.driverrating);

            plateno=v.findViewById(R.id.plateno);

        }
    }

    public void showDialog(int id, String title, String msg, Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setCancelable(false);
        dialog.setContentView(R.layout.my_custom_dialog);

        TextView dtitle = dialog.findViewById(R.id.dtitle);
        dtitle.setText(title);

        TextView dmsg = dialog.findViewById(R.id.dmsg);
        dmsg.setText(msg);

        Button ok = dialog.findViewById(R.id.ok);

        Button cancel = dialog.findViewById(R.id.cancel);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        dialog.show();
    }

    private String getcuraddress(Double latitude, Double longitude) {
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

    private String getgoingaddress(Double latitude2, Double longitude2) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());
        String city = "";

        try {
            addresses = geocoder.getFromLocation(latitude2, longitude2, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
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

    public String getDateTimeFromTimeStamp(Long time, String mDateFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(mDateFormat);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date dateTime = new Date(time);
        return dateFormat.format(dateTime);


    }


}

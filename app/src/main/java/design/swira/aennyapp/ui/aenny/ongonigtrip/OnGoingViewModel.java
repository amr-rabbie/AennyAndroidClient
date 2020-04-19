package design.swira.aennyapp.ui.aenny.ongonigtrip;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.data.api.MapApiClient;
import design.swira.aennyapp.pojo.aenny.compliants.CompliantResponse;
import design.swira.aennyapp.pojo.aenny.ctrip.CompletedTripResponse;
import design.swira.aennyapp.pojo.aenny.driver.DriverDataResponse;
import design.swira.aennyapp.pojo.aenny.googlemaps.GoogleMapsResponse;
import design.swira.aennyapp.pojo.aenny.newscduletrip.AddScduleTrip;
import design.swira.aennyapp.pojo.aenny.newscduletrip.ScduleTripsListResponse;
import design.swira.aennyapp.pojo.aenny.newscduletrip.UpdateScduleResponse;
import design.swira.aennyapp.pojo.aenny.normaltrips.AddNormalTrip;
import design.swira.aennyapp.pojo.aenny.normaltrips.AddNormalTripResponse;
import design.swira.aennyapp.pojo.aenny.ntrips.AddNTrips;
import design.swira.aennyapp.pojo.aenny.ntrips.AddNTripsResponse;
import design.swira.aennyapp.pojo.aenny.scduledtrips.ScduledTripsResponse;
import design.swira.aennyapp.pojo.aenny.schduletrips.UpdateScduleTrip;
import design.swira.aennyapp.pojo.aenny.trip.NextScduleResponse;
import design.swira.aennyapp.pojo.aenny.trips.TripsResponse;
import design.swira.aennyapp.pojo.aenny.tripsrates.TripRatesResponse;
import design.swira.aennyapp.pojo.aenny.vechaels.VechaelResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnGoingViewModel extends AndroidViewModel {

    MutableLiveData<GoogleMapsResponse> googleMapsResponseMutableLiveData=new MutableLiveData<>();
    MutableLiveData<TripsResponse> tripResponseMutableLiveData=new MutableLiveData<>();
    MutableLiveData<ResponseBody> updatetripMutableLiveData=new MutableLiveData<>();
    MutableLiveData<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> gettripbyidMutableLiveData=new MutableLiveData<>();
    public  MutableLiveData<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> deletetripbyidMutableLiveData=new MutableLiveData<>();
    MutableLiveData<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> getallscduledtripMutableLiveData=new MutableLiveData<>();
    public  MutableLiveData<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> getallscduledtripbyclientidMutableLiveData=new MutableLiveData<>();
    MutableLiveData<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> getnextscduledtripbyclientidMutableLiveData=new MutableLiveData<>();
    MutableLiveData<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> savetripMutableLiveData=new MutableLiveData<>();
    public  MutableLiveData<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> getalltripMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<AddNTripsResponse> addNormalTripResponseMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<ScduledTripsResponse> upsctripsMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<AddScduleTrip> addScduleTripMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<UpdateScduleResponse> upsctripMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<List<CompletedTripResponse>> completedtripslistMutableLiveData=new MutableLiveData<>();

    public  MutableLiveData<DriverDataResponse> driverDataResponseMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<VechaelResponse> vechaelResponseMutableLiveData=new MutableLiveData<>();


    public MutableLiveData<List<ScduledTripsResponse>> scListMutableLiveData=new MutableLiveData<>();
    public MutableLiveData<NextScduleResponse> nextscMutableLiveData=new MutableLiveData<>();
    public MutableLiveData<List<ScduleTripsListResponse>> getAllScduledTripsByClientIdmutablelivedata=new MutableLiveData<>();

    public MutableLiveData<CompliantResponse> compliantResponseMutableLiveData=new MutableLiveData<>();
    public MutableLiveData<TripRatesResponse> tripRatesResponseMutableLiveData=new MutableLiveData<>();

    public OnGoingViewModel(@NonNull Application application) {
        super(application);
    }

    public void GetDistanceAndTime(String origin ,String destination , String key){
        MapApiClient.getInstance().getDistanceAndTime(origin,destination,key).enqueue(new Callback<GoogleMapsResponse>() {
            @Override
            public void onResponse(Call<GoogleMapsResponse> call, Response<GoogleMapsResponse> response) {
                googleMapsResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GoogleMapsResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void saveTrip(TripsResponse tripsResponse){
        ApiClient.getInstance().saveTrip(tripsResponse).enqueue(new Callback<TripsResponse>() {
            @Override
            public void onResponse(Call<TripsResponse> call, Response<TripsResponse> response) {
                tripResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TripsResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }


    public void updateTrip(int id,design.swira.aennyapp.pojo.aenny.trip.ResponseTrips tripsResponse){
        ApiClient.getInstance().updateTrip(id,tripsResponse).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                updatetripMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void getTripById(int id){
        ApiClient.getInstance().getTripById(id).enqueue(new Callback<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>() {
            @Override
            public void onResponse(Call<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> call, Response<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> response) {
                gettripbyidMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void getScduledTrips(){
        ApiClient.getInstance().getScduledTrips().enqueue(new Callback<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>>() {
            @Override
            public void onResponse(Call<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> call, Response<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> response) {
                getallscduledtripMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void getScduledTripsbyClientId(int clientid){
        ApiClient.getInstance().getScduledTripsbyClientId(clientid).enqueue(new Callback<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>>() {
            @Override
            public void onResponse(Call<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> call, Response<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> response) {
                getallscduledtripbyclientidMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void getnextScduledTripsbyClientId(int clientid){
        ApiClient.getInstance().getNextScduledTripByClientId(clientid).enqueue(new Callback<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>() {
            @Override
            public void onResponse(Call<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> call, Response<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> response) {
                getnextscduledtripbyclientidMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void saveTrips(design.swira.aennyapp.pojo.aenny.trip.TripsResponse tripsResponse){
        ApiClient.getInstance().saveTrips(tripsResponse).enqueue(new Callback<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>() {
            @Override
            public void onResponse(Call<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> call, Response<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> response) {
                savetripMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> call, Throwable t) {
                Log.e("errttt",t.getMessage().toString());
            }
        });
    }

    public void deleteTripById(int id){
        ApiClient.getInstance().deleteTripById(id).enqueue(new Callback<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>() {
            @Override
            public void onResponse(Call<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> call, Response<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> response) {
                deletetripbyidMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void getAllTrips(){
        ApiClient.getInstance().getAllTrips().enqueue(new Callback<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>>() {
            @Override
            public void onResponse(Call<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> call, Response<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> response) {
                getalltripMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void getScduledTripsForClient(int clientid){
        ApiClient.getInstance().getScduledTripsForClient(clientid).enqueue(new Callback<List<ScduledTripsResponse>>() {
            @Override
            public void onResponse(Call<List<ScduledTripsResponse>> call, Response<List<ScduledTripsResponse>> response) {
                scListMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ScduledTripsResponse>> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void getNextScduledTripForClientt(int clientid) {
        ApiClient.getInstance().getNextScduledTripForClient(clientid).enqueue(new Callback<NextScduleResponse>() {
            @Override
            public void onResponse(Call<NextScduleResponse> call, Response<NextScduleResponse> response) {
                nextscMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NextScduleResponse> call, Throwable t) {
                Log.e("err", t.getMessage().toString());
            }
        });
    }

    public void UpdateScduledTrip(int id, UpdateScduleTrip updateScduleTrip){
        ApiClient.getInstance().UpdateScduledTrip(id,updateScduleTrip).enqueue(new Callback<ScduledTripsResponse>() {
            @Override
            public void onResponse(Call<ScduledTripsResponse> call, Response<ScduledTripsResponse> response) {
                upsctripsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ScduledTripsResponse> call, Throwable t) {
                Log.e("uperrlog",t.getMessage().toString());
            }
        });
    }

    public void addScduleTrip(AddScduleTrip addScduleTrip){
        ApiClient.getInstance().addScduleTrip(addScduleTrip).enqueue(new Callback<AddScduleTrip>() {
            @Override
            public void onResponse(Call<AddScduleTrip> call, Response<AddScduleTrip> response) {
                addScduleTripMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AddScduleTrip> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void getAllScduledTripsByClientId(int clientid){
        ApiClient.getInstance().getAllScduledTripsByClientId(clientid).enqueue(new Callback<List<ScduleTripsListResponse>>() {
            @Override
            public void onResponse(Call<List<ScduleTripsListResponse>> call, Response<List<ScduleTripsListResponse>> response) {
                getAllScduledTripsByClientIdmutablelivedata.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ScduleTripsListResponse>> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }



    public void getNextScduledTripsByClientId(int clientid){
        ApiClient.getInstance().getNextScduledTripsByClientId(clientid).enqueue(new Callback<NextScduleResponse>() {
            @Override
            public void onResponse(Call<NextScduleResponse> call, Response<NextScduleResponse> response) {
                //getAllScduledTripsByClientIdmutablelivedata.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NextScduleResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void updateScduledTrip(int id,design.swira.aennyapp.pojo.aenny.newscduletrip.UpdateScduleTrip updateScduleTrip){
        ApiClient.getInstance().updateScduledTrip(id,updateScduleTrip).enqueue(new Callback<UpdateScduleResponse>() {
            @Override
            public void onResponse(Call<UpdateScduleResponse> call, Response<UpdateScduleResponse> response) {
                upsctripMutableLiveData.setValue(response.body());
                Log.i("updateresponse",response.message());
            }

            @Override
            public void onFailure(Call<UpdateScduleResponse> call, Throwable t) {
                Log.e("err2",t.getMessage().toString());

            }
        });
    }

    public void getAllCompletedTripsForClient(int clientid){
        ApiClient.getInstance().getAllCompletedTripsForClient(clientid).enqueue(new Callback<List<CompletedTripResponse>>() {
            @Override
            public void onResponse(Call<List<CompletedTripResponse>> call, Response<List<CompletedTripResponse>> response) {
                completedtripslistMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<CompletedTripResponse>> call, Throwable t) {
                Log.e("err1",t.getMessage().toString());
            }
        });
    }

    public void makeClientCompliant(CompliantResponse compliantResponse){
        ApiClient.getInstance().makeClientCompliant(compliantResponse).enqueue(new Callback<CompliantResponse>() {
            @Override
            public void onResponse(Call<CompliantResponse> call, Response<CompliantResponse> response) {
                compliantResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CompliantResponse> call, Throwable t) {
                Log.e("err1",t.getMessage().toString());
            }
        });
    }

    public void clientRateDriverandVechieal(TripRatesResponse tripRatesResponse){
        ApiClient.getInstance().clientRateDriverandVechieal(tripRatesResponse).enqueue(new Callback<TripRatesResponse>() {
            @Override
            public void onResponse(Call<TripRatesResponse> call, Response<TripRatesResponse> response) {
                tripRatesResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TripRatesResponse> call, Throwable t) {
                Log.e("err1",t.getMessage().toString());
            }
        });
    }


    public void getDriverData(int id){
        ApiClient.getInstance().getDriverData(id).enqueue(new Callback<DriverDataResponse>() {
            @Override
            public void onResponse(Call<DriverDataResponse> call, Response<DriverDataResponse> response) {
                driverDataResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DriverDataResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void getVechaelData(int id){
        ApiClient.getInstance().getVechaelData(id).enqueue(new Callback<VechaelResponse>() {
            @Override
            public void onResponse(Call<VechaelResponse> call, Response<VechaelResponse> response) {
                vechaelResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<VechaelResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }


    public void saveNormalTrip(AddNTrips addNormalTrip){
        ApiClient.getInstance().saveNormalTrip(addNormalTrip).enqueue(new Callback<AddNTripsResponse>() {
            @Override
            public void onResponse(Call<AddNTripsResponse> call, Response<AddNTripsResponse> response) {
                addNormalTripResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AddNTripsResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }




}

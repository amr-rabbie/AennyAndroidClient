package design.swira.aennyapp.ui.aenny.settingscreen;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import design.swira.aennyapp.data.api.ApiClient;

import design.swira.aennyapp.pojo.aenny.logout.LogoutResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainSettingsViewModel extends AndroidViewModel {

    MutableLiveData<LogoutResponse> logoutmutableLiveData=new MutableLiveData<>();


    public MainSettingsViewModel(@NonNull Application application) {
        super(application);
    }

    public void driverLogout(int clientid){
        ApiClient.getInstance().clientLogout(clientid).enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                logoutmutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }
}

package design.swira.aennyapp.ui.aenny.settingscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityMainSettingsBinding;
import design.swira.aennyapp.pojo.aenny.logout.LogoutResponse;
import design.swira.aennyapp.ui.aenny.signin.LoginActivity;
import design.swira.aennyapp.utils.Constants;

public class MainSettingsActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainSettingsBinding binding;
    MainSettingsViewModel settingsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_settings);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main_settings);
        settingsViewModel= ViewModelProviders.of(this).get(MainSettingsViewModel.class);

        binding.signout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.signout){
            int clientId=Constants.getClientId(MainSettingsActivity.this);
            settingsViewModel.driverLogout(clientId);

            settingsViewModel.logoutmutableLiveData.observe(MainSettingsActivity.this, new Observer<LogoutResponse>() {
                @Override
                public void onChanged(LogoutResponse logoutResponse) {
                  if(logoutResponse.isStatus() == true){
                      Constants.saveClientData(0,"","","","" ,MainSettingsActivity.this);
                      Toast.makeText(MainSettingsActivity.this, logoutResponse.getMessage(), Toast.LENGTH_SHORT).show();
                      Intent i=new Intent(MainSettingsActivity.this, LoginActivity.class);
                      startActivity(i);
                  }
                }
            });
        }
    }
}

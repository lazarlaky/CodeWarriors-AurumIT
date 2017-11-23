package com.snapit.milosvuckovic.splashscreenv2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
//import android.support.design.widget.Snackbar;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.security.*;

import com.snapit.milosvuckovic.splashscreenv2.receiver.NetworkStateChangeReceiver;

import java.math.BigInteger;
import java.security.MessageDigest;

import static com.snapit.milosvuckovic.splashscreenv2.receiver.NetworkStateChangeReceiver.IS_NETWORK_AVAILABLE;

public class Screen2 extends AppCompatActivity {
    Button btn;
    EditText unesiKod;
    private Context context= this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setContentView(R.layout.activity_screen2);
        //Novi toolbar sa logom u centru
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        btn = (Button)findViewById(R.id.posalji_btn);

        if(proveri()){
            //  Intent intent = new Intent(Screen2.this, Screen2.class);
            // startActivity(intent);
        } else{
            Intent intent = new Intent(Screen2.this, Screen3.class);
            startActivity(intent);
            finish();
        }

        //Obaveštenje o statusu internet konekcije
        IntentFilter intentFilter = new IntentFilter(NetworkStateChangeReceiver.NETWORK_AVAILABLE_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean isNetworkAvailable = intent.getBooleanExtra(IS_NETWORK_AVAILABLE, false);
                String networkStatus = isNetworkAvailable ? "ukljucena" : "iskljucena";

                Toast.makeText(Screen2.this, "Internet konekcija: " + networkStatus, Toast.LENGTH_SHORT).show();
            }
        }, intentFilter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   /**osluskivanje signala na dugmetu*/
                SharedPreferences sharedPreferences = getSharedPreferences("Verifikacija", Context.MODE_PRIVATE);   /**stvaranje objekta za deljene preference*/
                SharedPreferences.Editor editor = sharedPreferences.edit();

                EditText minimalniUnos = (EditText) findViewById(R.id.unesi_kod_polje);
                String minUnos = minimalniUnos.getText().toString();

                if (minimalniUnos.getText().toString().length()<3){
                    minimalniUnos.setError("Kod mora da sadrzi najmanje 3 cifre!");
                    minimalniUnos.requestFocus();
                    minimalniUnos.setText("");
                }
                else {
                    int x=minUnos.length();
                    String srt = minUnos.substring(x-2,x);
                    String p1=minUnos.substring(0,x-2);
                    String kodIzBaze = "73402717748611147895";

                    //Provera po modulu 97
                    BigInteger bd = new BigInteger(p1);

                    bd=bd.multiply(new BigInteger("100"));
                    BigInteger x321 = bd.mod(new BigInteger("97"));
                    BigInteger mod97 = new BigInteger("97");
                    mod97=mod97.subtract(x321);
                    mod97=mod97.add(new BigInteger("1"));
                    if (mod97.toString().equals(srt)) {
                        if (minimalniUnos.getText().toString().trim().equals(kodIzBaze)) {
                            //Hesiranje
                            try {
                                MessageDigest m = MessageDigest.getInstance("MD5");
                                m.reset();
                                m.update(minUnos.getBytes());
                                byte[] digest = m.digest();
                                BigInteger bigInt = new BigInteger(1,digest);
                                String hashtext = bigInt.toString(16);
                                // Now we need to zero pad it if you actually want the full 32 chars.
                                while(hashtext.length() < 32 ){
                                    hashtext = "0"+hashtext;
                                }
                                editor.putString("verifikacioniKod", hashtext);
                                editor.apply();

                                Intent intent = new Intent(Screen2.this, Screen3.class);
                                startActivity(intent);
                                finish();

                                Toast.makeText(Screen2.this, "Vas verifikacioni kod je upamcen!", Toast.LENGTH_SHORT).show();
                            } catch (java.security.NoSuchAlgorithmException e) {
                                e.getMessage();
                            }
                            //kraj hesiranja

                            //Uzimanje android ID-a
                            String android_id = Settings.Secure.getString(context.getContentResolver(),
                                    Settings.Secure.ANDROID_ID);




                        }
                        else {
                            minimalniUnos.setError("Pogresan verifikacioni kod!");
                            minimalniUnos.setText("");
                        }
                    }
                    else {
                        minimalniUnos.setError("Pogresan verifikacioni kod!");
                        minimalniUnos.setText("");
                        }
                    }
                }
        });


    }

    public boolean proveri(){
        SharedPreferences sharedPreferences= getSharedPreferences("Verifikacija", Context.MODE_PRIVATE);

        String kod=sharedPreferences.getString("verifikacioniKod","");
        String s="";
        return kod.equals(s);
    }


    //Upis verifikacionog koda u Shared Preferences
   /* public void sacuvajVerifikacioniKod(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("Verifikacija", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Toast.makeText(Screen2.this, "Uneli ste nevazeci verifikacioni kod! ", Toast.LENGTH_LONG).show();
        if (unesiKod.getText().toString().trim().equals("probazademo10")) {
            editor.putString("verifikacioniKod", unesiKod.getText().toString());
            editor.apply();

            Intent intent = new Intent(Screen2.this, Screen3.class);
            startActivity(intent);
            finish();

            Toast.makeText(this, "Vas verifikacioni kod je upamcen!", Toast.LENGTH_SHORT).show();
        } else {
            unesiKod.setError("Pogresan verifikacioni kod!");
        }
    }*/

}

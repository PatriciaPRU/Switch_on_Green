package com.example.switchongreen;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiData extends AppCompatActivity {
    TextView output;
    String ene="Enero: ";
    String feb="Febrero: ";
    String mar="Marzo: ";
    String abr="Abril: ";
    String may="Mayo: ";
    String jun="Junio: ";
    String jul="Julio: ";
    String ago="Agosto: ";
    String sep="Septiembre: ";
    String oct="Octubre: ";
    String nov="Noviembre: ";
    String dic="Diciembre: ";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_charging);
        output= findViewById(R.id.output);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable= new ColorDrawable(Color.parseColor("#5AA4CB"));
        actionBar.setBackgroundDrawable(colorDrawable);

        getTemp();
    }

    public void getTemp(){
        String urlApi="https://power.larc.nasa.gov/api//temporal/climatology/point?start=2019&end=" +
                "2020&latitude=13&longitude=-81&community=re&parameters=T10M";
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url;
        url = null;
        HttpURLConnection conn;

        try {
            url=new URL(urlApi);
            conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            Log.d("Hola", "Hola");
            BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputline;
            StringBuffer response=new StringBuffer();

            while((inputline=reader.readLine())!= null){
                response.append(inputline);
            }
            JSONObject json= new JSONObject(String.valueOf(response)) ;
            JSONObject properties=json.getJSONObject("properties");
            JSONObject parameter= properties.getJSONObject("parameter");
            JSONObject temp=parameter.getJSONObject("T10M");

            JSONArray array=null;
            array=temp.names();
            Log.d("Hola", "Hola");

            for(int i=0;i<array.length();i++){
                String key=array.getString(i);
                Double value=temp.getDouble(key);
                switch (key){
                    case "JAN":
                        ene +=value+"\n";
                        break;
                    case "FEB":
                        feb +=value+"\n";
                        break;
                    case "MAR":
                        mar +=value+"\n";
                        break;
                    case "APR":
                        abr +=value+"\n";
                        break;
                    case "MAY":
                        may +=value+"\n";
                        break;
                    case "JUN":
                        jun +=value+"\n";
                        break;
                    case "JUL":
                        jul +=value+"\n";
                        break;
                    case "AUG":
                        ago +=value+"\n";
                        break;
                    case "SEP":
                        sep +=value+"\n";
                        break;
                    case "OCT":
                        oct +=value+"\n";
                        break;
                    case "NOV":
                        nov +=value+"\n";
                        break;
                    case "DEC":
                        dic +=value+"\n";
                        break;
                }

            }
            output.setText(ene +" "+feb);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
}

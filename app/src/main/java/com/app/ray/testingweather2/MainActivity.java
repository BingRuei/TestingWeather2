package com.app.ray.testingweather2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ealen.yahoo.weather.YahooAPI;

public class MainActivity extends AppCompatActivity {

    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = (TextView) this.findViewById(R.id.txt);
        final EditText editText = (EditText) this.findViewById(R.id.edt);
        editText.setText("Caotun Nantou Taiwan");
        Button button = (Button) this.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = editText.getText().toString();
                txtView.setText("");
                getWeather(s);
            }
        });

    }

    private void getWeather(String geolocation){
        String text = "";

        try {
//            String geolocation = "Nantou Taiwan";
//                    "Paris France";
            YahooAPI Yahoo = new YahooAPI(geolocation);

//            Yahoo.syncData();

            text +=
                    ">> Atmosphere\n" +
                            "Humidity : " + Yahoo.Atmosphere().getHumidity() + " %\n" +
                            "Pressure : " + Yahoo.Atmosphere().getPressure() + " in\n" +
                            "Rising : " + Yahoo.Atmosphere().getRising() + "\n" +
                            "Visibility : " + Yahoo.Atmosphere().getVisibility() + " mi\n";

            text +=
                    ">> Astronomy\n" +
                            "Sunrise : " + Yahoo.Astronomy().getSunrise() + "\n" +
                            "Sunset : " + Yahoo.Astronomy().getSunset() + "\n";

            text +=
                    ">> Condition\n" +
                            "Date : " + Yahoo.Condition().getDate() + "\n" +
                            "Temp : " + Yahoo.Condition().getTemp() + " °F\n" +
                            "Text : " + Yahoo.Condition().getText() + "\n";

            text +=
                    ">> Wind\n" +
                            "Chill : " + Yahoo.Wind().getChill() + "\n" +
                            "Direction : " + Yahoo.Wind().getDirection() + "\n" +
                            "Speed : " + Yahoo.Wind().getSpeed() + " mph \n";

            text += "----------------------\n";
            for (int i = 0; i < Yahoo.ListForecast().size(); i++) {
                text +=
                        "Forecast n° " + String.valueOf(i) + " : " + Yahoo.ListForecast().get(i).getDate() + "\n" +
                                "Text : " + Yahoo.ListForecast().get(i).getText() + "\n" +
                                "Hight : " + Yahoo.ListForecast().get(i).getHigh() + "°F/"+
                                        F2C(Double.parseDouble(Yahoo.ListForecast().get(i).getHigh()+""))+"°C\n" +
                                "Low : " + Yahoo.ListForecast().get(i).getLow() + "°F/"+
                                        F2C(Double.parseDouble(Yahoo.ListForecast().get(i).getLow()+""))+"°C\n";
            }
        } catch (Exception e) {
            text = "Error!";
        }

        txtView.setText(text);
    }

    private int F2C(double c){
        double d =( c - 32)*5/9.0;
        int x = d*10%10>=5 ? (int) d+1 : (int) d;
//        　　　　double f= g*9/5.0+32;
        return x;
    }
}

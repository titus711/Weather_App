package com.example.weather_app3;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    TextView temp, city_name, max_temp, min_temp, wind, humidity;
    ImageView icon;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


//        CharSequence theName = "SETTINGS";
//        SpannableString myS = new SpannableString(theName);
//        myS.setSpan(new ForegroundColorSpan(Color.BLACK), 0, theName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        getSupportActionBar().setTitle(myS);
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.argb(128, 3, 155, 229)));


        temp = findViewById(R.id.temprature);
        city_name = findViewById(R.id.city_namee);
        max_temp = findViewById(R.id.max_temp);
        min_temp = findViewById(R.id.min_temp);
        wind = findViewById(R.id.wind);
        humidity = findViewById(R.id.humidity);
        linearLayout = findViewById(R.id.linearLayout);
        icon = findViewById(R.id.cloud);
        Bundle bundle = getIntent().getExtras();


        wind.setText(getIntent().getStringExtra("wind").split("\\.")[0]);
        city_name.setText(getIntent().getStringExtra("city"));
        humidity.setText(getIntent().getStringExtra("humidity"));
        min_temp.setText(getIntent().getStringExtra("minTemp").split("\\.")[0]);
        max_temp.setText(getIntent().getStringExtra("maxTemp").split("\\.")[0]);
        temp.setText(getIntent().getStringExtra("temp").split("\\.")[0]);
        switch (getIntent().getIntExtra("pos", 0)) {
            case 0:
                linearLayout.setBackgroundResource(R.drawable.gradient_color);
                break;
            case 1:
                linearLayout.setBackgroundResource(R.drawable.gradient_yellow);

                break;
            case 2:
                linearLayout.setBackgroundResource(R.drawable.griadient_pink);

                break;
            case 3:
                linearLayout.setBackgroundResource(R.drawable.graident_gray);

                break;
            case 4:
                linearLayout.setBackgroundResource(R.drawable.graident_red);

                break;
            case 5:
                linearLayout.setBackgroundResource(R.drawable.graident_gray);

                break;
            case 6:
                linearLayout.setBackgroundResource(R.drawable.gradient_yellow);

                break;
            case 7:
                linearLayout.setBackgroundResource(R.drawable.griadient_pink);

                break;
            case 8:
                linearLayout.setBackgroundResource(R.drawable.graident_gray);

                break;
            case 9:
                linearLayout.setBackgroundResource(R.drawable.gradient_color);

                break;
            default:
                linearLayout.setBackgroundResource(R.drawable.gradient_color);


        }


        switch (getIntent().getStringExtra("icon")) {
            case "01d":
                icon.setImageResource(R.drawable.zero_one_d);
                break;
            case "02d":
                icon.setImageResource(R.drawable.zero_two_d);
                break;
            case "03d":
                icon.setImageResource(R.drawable.zero_three_d);
                break;
            case "04d":
                icon.setImageResource(R.drawable.zero_four_d);
                break;
            case "09d":
                icon.setImageResource(R.drawable.zero_nine_d);
                break;
            case "10d":
                icon.setImageResource(R.drawable.ten_d);
                break;
            case "11d":
                icon.setImageResource(R.drawable.eleven_d);
                break;
            case "13d":
                icon.setImageResource(R.drawable.thrteen_d);
                break;
            case "50d":
                icon.setImageResource(R.drawable.fifty_d);
                break;
            default:
                icon.setImageResource(R.drawable.fifty_d);

        }


    }
}
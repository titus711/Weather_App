package com.example.weather_app3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<ModelClass> weatherModelList;
    Context context;

    public WeatherAdapter(Context context, List<ModelClass> weatherModelList) {
        this.context = context;
        this.weatherModelList = weatherModelList;


    }

    public WeatherAdapter(Response.Listener<JSONObject> weatherModelList) {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelClass weatherModel = weatherModelList.get(position);
        holder.city_name.setText(weatherModel.city);

        if (position == 0) {
            holder.current_location.setVisibility(View.VISIBLE);
        } else {
            holder.current_location.setVisibility(View.GONE);
        }
        holder.time.setText(getDateCurrentTimeZone(Long.parseLong(weatherModel.date_time)));
        Calendar calendar = Calendar.getInstance();
        TimeZone tz = TimeZone.getDefault();
        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        java.util.Date currenTimeZone = new java.util.Date(Long.parseLong(weatherModel.date_time) * 1000);


        String extensionRemoved = weatherModel.temp.split("\\.")[0];

        holder.percent.setText(weatherModel.humidity + " %");
        holder.centigrade.setText(extensionRemoved + " °c");
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
//                bundle.putSerializable("data",weatherModel);
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("wind", weatherModel.wind);
                intent.putExtra("temp", weatherModel.temp);
                intent.putExtra("maxTemp", weatherModel.Max_temp);
                intent.putExtra("minTemp", weatherModel.Min_temp);
                intent.putExtra("humidity", weatherModel.humidity);
                intent.putExtra("city", weatherModel.city);
                intent.putExtra("icon", weatherModel.icon);
                intent.putExtra("pos", position);
//                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
        switch (position) {
            case 0:
                holder.linearLayout.setBackgroundResource(R.drawable.gradient_color);
                break;
            case 1:
                holder.linearLayout.setBackgroundResource(R.drawable.gradient_yellow);

                break;
            case 2:
                holder.linearLayout.setBackgroundResource(R.drawable.griadient_pink);

                break;
            case 3:
                holder.linearLayout.setBackgroundResource(R.drawable.graident_gray);

                break;
            case 4:
                holder.linearLayout.setBackgroundResource(R.drawable.graident_red);

                break;
            case 5:
                holder.linearLayout.setBackgroundResource(R.drawable.graident_gray);

                break;
            case 6:
                holder.linearLayout.setBackgroundResource(R.drawable.gradient_yellow);

                break;
            case 7:
                holder.linearLayout.setBackgroundResource(R.drawable.griadient_pink);

                break;
            case 8:
                holder.linearLayout.setBackgroundResource(R.drawable.graident_gray);

                break;
            case 9:
                holder.linearLayout.setBackgroundResource(R.drawable.gradient_color);

                break;
            default:
                holder.linearLayout.setBackgroundResource(R.drawable.graident_red);


        }

        switch (weatherModel.icon) {
            case "01d":
                holder.icon.setImageResource(R.drawable.zero_one_d);
                break;
            case "02d":
                holder.icon.setImageResource(R.drawable.zero_two_d);
                break;
            case "03d":
                holder.icon.setImageResource(R.drawable.zero_three_d);
                break;
            case "04d":
                holder.icon.setImageResource(R.drawable.zero_four_d);
                break;
            case "09d":
                holder.icon.setImageResource(R.drawable.zero_nine_d);
                break;
            case "10d":
                holder.icon.setImageResource(R.drawable.ten_d);
                break;
            case "11d":
                holder.icon.setImageResource(R.drawable.eleven_d);
                break;
            case "13d":
                holder.icon.setImageResource(R.drawable.thrteen_d);
                break;
            case "50d":
                holder.icon.setImageResource(R.drawable.fifty_d);
                break;
            default:
                holder.icon.setImageResource(R.drawable.fifty_d);

        }

    }

    @Override
    public int getItemCount() {
        return weatherModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView city_name, current_location, time, percent, centigrade;
        ImageView icon;
        ConstraintLayout constraintLayout;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            city_name = itemView.findViewById(R.id.city_name);
            current_location = itemView.findViewById(R.id.current_location);
            time = itemView.findViewById(R.id.timee);
            percent = itemView.findViewById(R.id.percentt);
            centigrade = itemView.findViewById(R.id.centigrade);
            icon = itemView.findViewById(R.id.icon);
            constraintLayout = itemView.findViewById(R.id.constraint);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

    public String getDateCurrentTimeZone(long timestamp) {
        try {

            Date date = new Date(timestamp * 1000L); // *1000 is to convert seconds to milliseconds
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy"); // the format of your date
            sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));

            return sdf.format(date);

        } catch (Exception e) {
        }
        return "";
    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("MM-dd-yyyy hh:mm aa", cal).toString();
        return date;
    }
}


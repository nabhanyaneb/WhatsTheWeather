package com.example.nabhanyaneb.weatherapplication;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    String mainString;
    String tempString;
    String cityString;

    int timeInt3;
    String timeString3="";
    String mainString3="";
    String highTempString3="";
    String downTempString3="";

    int timeInt6;
    String timeString6="";
    String mainString6="";
    String highTempString6="";
    String downTempString6="";

    int timeInt9;
    String timeString9="";
    String mainString9="";
    String highTempString9="";
    String downTempString9="";

    int timeInt12;
    String timeString12="";
    String mainString12="";
    String highTempString12="";
    String downTempString12="";

    int timeInt15;
    String timeString15="";
    String mainString15="";
    String highTempString15="";
    String downTempString15="";

    EditText zipCode;
    Button buttonGo;

    String zip="08852";

    boolean valid=true;

    ImageView imageView;
    ImageView imageView3;
    ImageView imageView6;
    ImageView imageView9;
    ImageView imageView12;
    ImageView imageView15;

    Void aVoid;

    LinearLayout layoutOne;
    LinearLayout layoutTwo;
    LinearLayout layoutThree;
    LinearLayout layoutFour;
    LinearLayout layoutFive;
    LinearLayout layoutSix;
    LinearLayout layoutSeven;

    int myDkRed=Color.rgb(0,0,153);
    int myLtRed=Color.rgb(0,0,204);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncThread weatherThread = new AsyncThread(zip);

        zipCode=(EditText)findViewById(R.id.id_editTextZip);
        buttonGo=(Button)findViewById(R.id.id_buttonGo);

        imageView=(ImageView)findViewById(R.id.id_imageView);
        imageView3=(ImageView)findViewById(R.id.id_imageView3);
        imageView6=(ImageView)findViewById(R.id.id_imageView6);
        imageView9=(ImageView)findViewById(R.id.id_imageView9);
        imageView12=(ImageView)findViewById(R.id.id_imageView12);
        imageView15=(ImageView)findViewById(R.id.id_imageView15);

        layoutOne=(LinearLayout)findViewById(R.id.id_layoutOne);
        layoutTwo=(LinearLayout)findViewById(R.id.id_layoutTwo);
        layoutThree=(LinearLayout)findViewById(R.id.id_layoutThree);
        layoutFour=(LinearLayout)findViewById(R.id.id_layoutFour);
        layoutFive=(LinearLayout)findViewById(R.id.id_layoutFive);
        layoutSix=(LinearLayout)findViewById(R.id.id_layoutSix);
        layoutSeven=(LinearLayout)findViewById(R.id.id_layoutSeven);

        layoutOne.setBackgroundColor(myDkRed);
        layoutTwo.setBackgroundColor(myLtRed);
        layoutThree.setBackgroundColor(myDkRed);
        layoutFour.setBackgroundColor(myLtRed);
        layoutFive.setBackgroundColor(myDkRed);
        layoutSix.setBackgroundColor(myLtRed);
        layoutSeven.setBackgroundColor(myDkRed);
        zipCode.setText("");

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((zipCode.getText()+"").length()!=5) buttonGo.setText("Invalid");
                else {
                    zip = zipCode.getText() + "";
                    zipCode.setText("");
                    imageView.setImageResource(R.drawable.loading);
                    imageView3.setImageResource(R.drawable.loading);
                    imageView6.setImageResource(R.drawable.loading);
                    imageView9.setImageResource(R.drawable.loading);
                    imageView12.setImageResource(R.drawable.loading);
                    imageView15.setImageResource(R.drawable.loading);
                    AsyncThread weatherThread = new AsyncThread(zip);
                    weatherThread.execute();
                }
            }
        });

        zipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                buttonGo.setText("GO!");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        weatherThread.execute();
    }

    public class AsyncThread extends AsyncTask<String,Void,Void> {
        JSONObject weather;
        String mainAct="";
        String tempAct="";
        String cityAct="";

        String timeAct3="";
        String mainAct3="";
        String highTempAct3="";
        String downTempAct3="";

        String timeAct6="";
        String mainAct6="";
        String highTempAct6="";
        String downTempAct6="";

        String timeAct9="";
        String mainAct9="";
        String highTempAct9="";
        String downTempAct9="";

        String timeAct12="";
        String mainAct12="";
        String highTempAct12="";
        String downTempAct12="";

        String timeAct15="";
        String mainAct15="";
        String highTempAct15="";
        String downTempAct15="";


        EditText editTextZip=(EditText)findViewById(R.id.id_editTextZip);
        Button buttonGo=(Button)findViewById(R.id.id_buttonGo);

        TextView textViewQuote=(TextView)findViewById(R.id.id_textViewQuote);
        TextView textViewCity=(TextView)findViewById(R.id.id_textViewCity);
        TextView textViewWeather=(TextView)findViewById(R.id.id_textViewWeather);
        TextView textViewTemp=(TextView)findViewById(R.id.id_textViewTemp);
        //ImageView imageView=(ImageView)findViewById(R.id.id_imageView);
        TextView textViewCurrent=(TextView)findViewById(R.id.id_textViewCurrent);
        boolean am=true;

        TextView textViewPlus3=(TextView)findViewById(R.id.id_textViewPlus3);
        TextView textViewInfo3=(TextView)findViewById(R.id.id_textViewInfo3);
        //ImageView imageView3=(ImageView)findViewById(R.id.id_imageView3);
        boolean am3=true;

        TextView textViewPlus6=(TextView)findViewById(R.id.id_textViewPlus6);
        TextView textViewInfo6=(TextView)findViewById(R.id.id_textViewInfo6);
        //ImageView imageView6=(ImageView)findViewById(R.id.id_imageView6);
        boolean am6=true;

        TextView textViewPlus9=(TextView)findViewById(R.id.id_textViewPlus9);
        TextView textViewInfo9=(TextView)findViewById(R.id.id_textViewInfo9);
        // ImageView imageView9=(ImageView)findViewById(R.id.id_imageView9);
        boolean am9=true;

        TextView textViewPlus12=(TextView)findViewById(R.id.id_textViewPlus12);
        TextView textViewInfo12=(TextView)findViewById(R.id.id_textViewInfo12);
        //ImageView imageView12=(ImageView)findViewById(R.id.id_imageView12);
        boolean am12=true;

        TextView textViewPlus15=(TextView)findViewById(R.id.id_textViewPlus15);
        TextView textViewInfo15=(TextView)findViewById(R.id.id_textViewInfo15);
        //ImageView imageView15=(ImageView)findViewById(R.id.id_imageView15);
        boolean am15=true;

        String myZipCode="";

        public AsyncThread(String zip){
            myZipCode=zip; //USED IN URL
        }

        @Override
        protected Void doInBackground(String... params) {

            // for (int i=0;i<100;i++)
            //Log.d("TAG","Weather Thread");
            try {
                valid=true;
                URL weatherURL=new URL("http://api.openweathermap.org/data/2.5/forecast?zip="+myZipCode+",us&units=imperial&APPID=fe9223ab32344a25d518eb34bde0d8ce");

                URLConnection weatherConnection=weatherURL.openConnection();
                InputStream weatherInput =weatherConnection.getInputStream();

                BufferedReader reader= new BufferedReader(new InputStreamReader(weatherInput));
                String text,output="";
                int count=0;
                while ((text=reader.readLine())!=null){
                    count++;
                    output+=text;

                    //if (text.contains("city not found")) valid=false;
                }

                JSONObject weatherObj=new JSONObject(output);
                //String myMessage=weatherObj.getString("message");
                //Log.d("myMessage", myMessage+"hi");

                // if (myMessage.equals("city not found")) valid=false;

                if (valid){

                    //JSONObject weatherObj=new JSONObject(output);


                    JSONObject cityObj = weatherObj.getJSONObject("city");
                    cityAct = cityObj.getString("name");


                    JSONArray weatherList = weatherObj.getJSONArray("list");
                    JSONObject weatherArr = weatherList.getJSONObject(0);
                    JSONArray weatherMain = weatherArr.getJSONArray("weather");
                    JSONObject weatherArr0 = weatherMain.getJSONObject(0);
                    mainAct = weatherArr0.getString("main");

                    JSONArray templist = weatherObj.getJSONArray("list");
                    JSONObject tempArr = templist.getJSONObject(0);
                    JSONObject tempMain = tempArr.getJSONObject("main");
                    tempAct = tempMain.getString("temp");


                    JSONObject weather3 = weatherList.getJSONObject(1);
                    JSONArray weatherMain3 = weather3.getJSONArray("weather");
                    JSONObject weatherArr03 = weatherMain3.getJSONObject(0);
                    mainAct3 = weatherArr03.getString("main");

                    JSONArray tempList3 = weatherObj.getJSONArray("list");
                    JSONObject tempArr3 = tempList3.getJSONObject(1);
                    JSONObject tempMain3 = tempArr3.getJSONObject("main");
                    downTempAct3 = tempMain3.getString("temp_min");
                    highTempAct3 = tempMain3.getString("temp_max");


                    JSONObject weather6 = weatherList.getJSONObject(2);
                    JSONArray weatherMain6 = weather6.getJSONArray("weather");
                    JSONObject weatherArr06 = weatherMain6.getJSONObject(0);
                    mainAct6 = weatherArr06.getString("main");

                    JSONArray tempList6 = weatherObj.getJSONArray("list");
                    JSONObject tempArr6 = tempList6.getJSONObject(2);
                    JSONObject tempMain6 = tempArr6.getJSONObject("main");
                    downTempAct6 = tempMain6.getString("temp_min");
                    highTempAct6 = tempMain6.getString("temp_max");


                    JSONObject weather9 = weatherList.getJSONObject(3);
                    JSONArray weatherMain9 = weather9.getJSONArray("weather");
                    JSONObject weatherArr09 = weatherMain9.getJSONObject(0);
                    mainAct9 = weatherArr09.getString("main");

                    JSONArray tempList9 = weatherObj.getJSONArray("list");
                    JSONObject tempArr9 = tempList9.getJSONObject(3);
                    JSONObject tempMain9 = tempArr9.getJSONObject("main");
                    downTempAct9 = tempMain9.getString("temp_min");
                    highTempAct9 = tempMain9.getString("temp_max");


                    JSONObject weather12 = weatherList.getJSONObject(4);
                    JSONArray weatherMain12 = weather12.getJSONArray("weather");
                    JSONObject weatherArr012 = weatherMain12.getJSONObject(0);
                    mainAct12 = weatherArr012.getString("main");

                    JSONArray tempList12 = weatherObj.getJSONArray("list");
                    JSONObject tempArr12 = tempList12.getJSONObject(4);
                    JSONObject tempMain12 = tempArr12.getJSONObject("main");
                    downTempAct12 = tempMain12.getString("temp_min");
                    highTempAct12 = tempMain12.getString("temp_max");


                    JSONObject weather15 = weatherList.getJSONObject(5);
                    JSONArray weatherMain15 = weather15.getJSONArray("weather");
                    JSONObject weatherArr015 = weatherMain15.getJSONObject(0);
                    mainAct15 = weatherArr015.getString("main");

                    JSONArray tempList15 = weatherObj.getJSONArray("list");
                    JSONObject tempArr15 = tempList15.getJSONObject(5);
                    JSONObject tempMain15 = tempArr15.getJSONObject("main");
                    downTempAct15 = tempMain15.getString("temp_min");
                    highTempAct15 = tempMain15.getString("temp_max");
                }

                else count=0;


                //Log.d("Weather", mainAct.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();

                throw new IllegalArgumentException("Invalid Url");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }catch (Exception e){

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (valid) {
                mainString = mainAct;
                tempString = tempAct;
                cityString = cityAct;
                textViewWeather.setText(mainString + "");
                textViewCity.setText(cityString + "");
                textViewTemp.setText(Math.round(Double.parseDouble(tempAct)) + "\u00b0" + " F");
                setMainImage(mainString, imageView);
                setQuote();

                downTempString3 = downTempAct3;
                highTempString3 = highTempAct3;
                mainString3 = mainAct3;

                downTempString6 = downTempAct6;
                highTempString6 = highTempAct6;
                mainString6 = mainAct6;

                downTempString9 = downTempAct9;
                highTempString9 = highTempAct9;
                mainString9 = mainAct9;

                downTempString12 = downTempAct12;
                highTempString12 = highTempAct12;
                mainString12 = mainAct12;

                downTempString15 = downTempAct15;
                highTempString15 = highTempAct15;
                mainString15 = mainAct15;

                textViewInfo3.setText( "High: " + Math.round(Double.parseDouble(highTempString3)) + "\u00b0" + " F" + "\n" +
                        "Low: " + Math.round(Double.parseDouble(downTempString3)) + "\u00b0" + " F");
                setMainImage(mainString3, imageView3);


                textViewInfo6.setText( "High: " + Math.round(Double.parseDouble(highTempString6)) + "\u00b0" + " F" + "\n" +
                        "Low: " + Math.round(Double.parseDouble(downTempString6)) + "\u00b0" + " F");
                setMainImage(mainString6, imageView6);


                textViewInfo9.setText( "High: " + Math.round(Double.parseDouble(highTempString9)) + "\u00b0" + " F" + "\n" +
                        "Low: " + Math.round(Double.parseDouble(downTempString9)) + "\u00b0" + " F");
                setMainImage(mainString9, imageView9);

                textViewInfo12.setText("High: " + Math.round(Double.parseDouble(highTempString12)) + "\u00b0" + " F" + "\n" +
                        "Low: " + Math.round(Double.parseDouble(downTempString12)) + "\u00b0" + " F");
                setMainImage(mainString12, imageView12);

                textViewInfo15.setText( "High: " + Math.round(Double.parseDouble(highTempString15)) + "\u00b0" + " F" + "\n" +
                        "Low: " + Math.round(Double.parseDouble(downTempString15)) + "\u00b0" + " F");
                setMainImage(mainString15, imageView15);


                Date current = Calendar.getInstance().getTime();
                String now = current + "";
                Log.d("current", now);

                String[] arr = now.split(" ");
                String time = arr[3];
                Log.d("time", time);


                String[] hour = time.split(":");
                int timeInt =Integer.parseInt(hour[0]);

                switch (timeInt){

                    case 0:{
                        am3=true;
                        am6=true;
                        am9=true;
                        am12=false;
                        am15=false;
                    }
                    break;

                    case 1:{
                        am3=true;
                        am6=true;
                        am9=true;
                        am12=false;
                        am15=false;
                    }
                    break;

                    case 2:{
                        am3=true;
                        am6=true;
                        am9=true;
                        am12=false;
                        am15=false;
                    }
                    break;

                    case 3:{
                        am3=true;
                        am6=true;
                        am9=false;
                        am12=false;
                        am15=false;
                    }
                    break;

                    case 4:{
                        am3=true;
                        am6=true;
                        am9=false;
                        am12=false;
                        am15=false;
                    }
                    break;

                    case 5:{
                        am3=true;
                        am6=true;
                        am9=false;
                        am12=false;
                        am15=false;
                    }
                    break;

                    case 6:{
                        am3=true;
                        am6=false;
                        am9=false;
                        am12=false;
                        am15=false;
                    }
                    break;

                    case 7:{
                        am3=true;
                        am6=false;
                        am9=false;
                        am12=false;
                        am15=false;
                    }
                    break;

                    case 8:{
                        am3=true;
                        am6=false;
                        am9=false;
                        am12=false;
                        am15=false;
                    }
                    break;

                    case 9:{
                        am3=false;
                        am6=false;
                        am9=false;
                        am12=false;
                        am15=true;
                    }
                    break;

                    case 10:{
                        am3=false;
                        am6=false;
                        am9=false;
                        am12=false;
                        am15=true;
                    }
                    break;

                    case 11:{
                        am3=false;
                        am6=false;
                        am9=false;
                        am12=false;
                        am15=true;
                    }
                    break;
                    case 12:{
                        am3=false;
                        am6=false;
                        am9=false;
                        am12=true;
                        am15=true;
                    }
                    break;

                    case 13:{
                        am3=false;
                        am6=false;
                        am9=false;
                        am12=true;
                        am15=true;
                    }
                    break;

                    case 14:{
                        am3=false;
                        am6=false;
                        am9=false;
                        am12=true;
                        am15=true;
                    }
                    break;

                    case 15:{
                        am3=false;
                        am6=false;
                        am9=true;
                        am12=true;
                        am15=true;
                    }
                    break;

                    case 16:{
                        am3=false;
                        am6=false;
                        am9=true;
                        am12=true;
                        am15=true;
                    }
                    break;

                    case 17:{
                        am3=false;
                        am6=false;
                        am9=true;
                        am12=true;
                        am15=true;
                    }
                    break;

                    case 18:{
                        am3=false;
                        am6=true;
                        am9=true;
                        am12=true;
                        am15=true;
                    }
                    break;

                    case 19:{
                        am3=false;
                        am6=true;
                        am9=true;
                        am12=true;
                        am15=true;
                    }
                    break;

                    case 20:{
                        am3=false;
                        am6=true;
                        am9=true;
                        am12=true;
                        am15=true;
                    }
                    break;

                    case 21:{
                        am3=true;
                        am6=true;
                        am9=true;
                        am12=true;
                        am15=false;
                    }
                    break;

                    case 22:{
                        am3=true;
                        am6=true;
                        am9=true;
                        am12=true;
                        am15=false;
                    }
                    break;

                    case 23:{
                        am3=true;
                        am6=true;
                        am9=true;
                        am12=true;
                        am15=false;
                    }
                    break;
                }

                if (timeInt > 12){
                    timeInt -= 12;
                    am=false;
                }
                if (timeInt==12) am=false;
                if (timeInt==0) am=true;

                textViewCurrent.setText(timeInt+":"+time.substring(1+hour[0].length(),time.length()-3));
                if (am) textViewCurrent.setText((textViewCurrent.getText()+" AM"));
                else if (!(am)) textViewCurrent.setText((textViewCurrent.getText()+" PM"));



                timeInt3 = timeInt + 3;
                if (timeInt3 > 12){
                    timeInt3 -= 12;

                }
                if (timeInt==12) am=false;
                if (timeInt==0) am=true;

                if (timeInt3 > 12){
                    timeInt3 -= 12;

                }
                if (timeInt3==12) am=false;
                if (timeInt3==0) am=true;

                timeInt6 = timeInt + 6;
                if (timeInt6 > 12){
                    timeInt6 -= 12;

                }
                if (timeInt6 > 12) {
                    timeInt6 -= 12;

                }
                if (timeInt6==12) am=false;
                if (timeInt6==0) am=true;

                timeInt9 = timeInt + 9;
                if (timeInt9 > 12){
                    timeInt9 -= 12;

                }
                if (timeInt9 > 12) {
                    timeInt9 -= 12;

                }
                if (timeInt9==12) am=false;
                if (timeInt9==0) am=true;

                timeInt12 = timeInt + 12;
                if (timeInt12 > 12) {
                    timeInt12 -= 12;

                }
                if (timeInt12 > 12) {
                    timeInt12 -= 12;

                }
                if (timeInt12==12) am=false;
                if (timeInt12==0) am=true;

                timeInt15 = timeInt + 15;
                if (timeInt15 > 12) {
                    timeInt15 -= 12;

                }
                if (timeInt15 > 12) {
                    timeInt15 -= 12;

                }
                if (timeInt15==12) am=false;
                if (timeInt15==0) am=true;

                timeString3 = timeInt3 + ":00";
                timeString6 = timeInt6 + ":00";
                timeString9 = timeInt9 + ":00";
                timeString12 = timeInt12 + ":00";
                timeString15 = timeInt15 + ":00";

                if (am3) textViewPlus3.setText(timeString3+" AM");
                else if (!(am3)) textViewPlus3.setText(timeString3+" PM");

                if (am6) textViewPlus6.setText(timeString6+" AM");
                else if (!(am6)) textViewPlus6.setText(timeString6+" PM");

                if (am9) textViewPlus9.setText(timeString9+" AM");
                else if (!(am9)) textViewPlus9.setText(timeString9+" PM");

                if (am12) textViewPlus12.setText(timeString12+" AM");
                else if (!(am12)) textViewPlus12.setText(timeString12+" PM");

                if (am15) textViewPlus15.setText(timeString15+" AM");
                else if (!(am15)) textViewPlus15.setText(timeString15+" PM");


                //if (mainString.equals("Clear") && Integer.parseInt(hour[0])>17) imageView.setImageResource(R.drawable.moon);
                setTheme(mainString);

            }

        }

        public void setMainImage(String mainString, ImageView imageView){

            if (mainString.equals("Snow")) imageView.setImageResource(R.drawable.snowreal);
            if (mainString.equals("Clear")) imageView.setImageResource(R.drawable.sunny);
            if (mainString.equals("Rain")) imageView.setImageResource(R.drawable.rainreal);
            if (mainString.equals("Thunderstorm")) imageView.setImageResource(R.drawable.thunder); //look up word for thunder
            if (mainString.equals("Clouds")) imageView.setImageResource(R.drawable.cloudy);

        }

        public void setQuote(){
            if (mainString.equals("Clear")) textViewQuote.setText(" 'Shut up about the Sun! SHUT UP about the Sun!' "+"\n\n"+" -Gabe Lewis from The Office");
            if (mainString.equals("Rain")) textViewQuote.setText(" 'Whoo! Wow, it is raining cats and dogs out there. Holy moley.' "+"\n\n"+" -Phyllis Vance from The Office");
            if (mainString.equals("Snow")) textViewQuote.setText(" 'In the end, the greatest snowball isn't a snowball at all. It's fear. Merry Christmas.' "+"\n\n"+" -Dwight Schrute from The Office");
            if (mainString.equals("Thunderstorm")) textViewQuote.setText("  'Michael tends to procrastinate if he has to do work. Once a year, it falls on the same day. I call it the Perfect Storm.' "+"\n\n"+" -Pam Beasly from The Office");
            if (mainString.equals("Clouds")) textViewQuote.setText(" 'And there's clouds. There's clouds in the sky. I think it's gonna rain. Bad for business.' "+"\n\n"+" -Michael Scott from The Office");
        }

        public void setTheme(String str){

            int myDkPurple=Color.rgb(153,0,153);
            int myLtPurple=Color.rgb(190,0,190);

            textViewQuote.setTextColor(myDkPurple);
            textViewWeather.setTextColor(myLtPurple);
            textViewCity.setTextColor(myLtPurple);
            textViewTemp.setTextColor(myDkPurple);
            textViewCurrent.setTextColor(myDkPurple);

            //int myDkRed=Color.rgb(0,0,153);
            //int myLtRed=Color.rgb(0,0,204);

            layoutOne.setBackgroundColor(myDkRed);
            layoutThree.setBackgroundColor(myDkRed);
            layoutFour.setBackgroundColor(myLtRed);
            layoutFive.setBackgroundColor(myDkRed);
            layoutSix.setBackgroundColor(myLtRed);
            layoutSeven.setBackgroundColor(myDkRed);

            zipCode.setTextColor(Color.WHITE);
            textViewInfo3.setTextColor(Color.WHITE);
            textViewPlus3.setTextColor(Color.WHITE);
            textViewInfo6.setTextColor(Color.WHITE);
            textViewPlus6.setTextColor(Color.WHITE);
            textViewInfo9.setTextColor(Color.WHITE);
            textViewPlus9.setTextColor(Color.WHITE);
            textViewInfo12.setTextColor(Color.WHITE);
            textViewPlus12.setTextColor(Color.WHITE);
            textViewInfo15.setTextColor(Color.WHITE);
            textViewPlus15.setTextColor(Color.WHITE);


            if (str.equals("Clear")) {
                layoutTwo.setBackgroundResource(R.drawable.sunback);

            }
            if (str.equals("Rain")){
                layoutTwo.setBackgroundResource(R.drawable.raining);
            }

            if (str.equals("Snow")){
                layoutTwo.setBackgroundResource(R.drawable.snowback);
            }
            if (str.equals("Thunderstorm")){
                layoutTwo.setBackgroundResource(R.drawable.thunderback);
            }
            if (str.equals("Clouds")){
                layoutTwo.setBackgroundResource(R.drawable.cloudyback);
            }




        }


    }
}

//am pm DONE
//display colors
//current time DONE
//state NOT POSSIBLE

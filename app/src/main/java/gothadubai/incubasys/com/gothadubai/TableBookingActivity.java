package gothadubai.incubasys.com.gothadubai;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.security.spec.ECField;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import CommonUtils.CommonVaraibles;
import Controllers.EventsController;
import Handlers.NetworkHandler;
import Interfaces.NetworkResponse;
import Models.EventModel;

/**
 * Created by Xain on 28/09/2016.
 */
public class TableBookingActivity extends Activity implements NetworkResponse {

    private ImageView closeBtn;
    private TextView phone, email, numberPeople, hour, comment, submit, eventName;
    private EditText firstName, lastName;
    int currentEvent;
    ProgressBar prg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_booking_layout);
        closeBtn = (ImageView)findViewById(R.id.close_icon);

        prg = (ProgressBar)findViewById(R.id.prg);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();;
            }
        });

        Typeface face = Typeface.createFromAsset(getAssets(), "gothic-regular.ttf");
        eventName = (TextView)findViewById(R.id.see_you);
        firstName = (EditText)findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        phone = (TextView)findViewById(R.id.phone);
        email = (TextView)findViewById(R.id.email);
        numberPeople = (TextView)findViewById(R.id.number_people);
        hour = (TextView)findViewById(R.id.hour);
        comment = (TextView)findViewById(R.id.comment);
        submit = (TextView)findViewById(R.id.submit_txt);
        currentEvent = getIntent().getIntExtra(CommonVaraibles.CONSTANT_PARAM_INTENT_CURRENT_BOOKING_EVENT, 0);
        eventName.setTypeface(face);
        eventName.setText(EventsController.Instance().eventsList.get(currentEvent).getName());




        firstName.addTextChangedListener(new TextWatcher() {
            int mStart = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mStart = start + count;
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                String capitalizedText;
                if (input.length() < 1)
                    capitalizedText = input;
                else
                    capitalizedText = input.substring(0, 1).toUpperCase() + input.substring(1);
                if (!capitalizedText.equals(firstName.getText().toString())) {
                    firstName.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            firstName.setSelection(mStart);
                            firstName.removeTextChangedListener(this);
                        }
                    });
                    firstName.setText(capitalizedText);
                }
            }
        });

        lastName.addTextChangedListener(new TextWatcher() {
            int mStart = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mStart = start + count;
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                String capitalizedText;
                if (input.length() < 1)
                    capitalizedText = input;
                else
                    capitalizedText = input.substring(0, 1).toUpperCase() + input.substring(1);
                if (!capitalizedText.equals(lastName.getText().toString())) {
                    lastName.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            lastName.setSelection(mStart);
                            lastName.removeTextChangedListener(this);
                        }
                    });
                    lastName.setText(capitalizedText);
                }
            }
        });


        firstName.setTypeface(face);
        lastName .setTypeface(face);
        phone.setTypeface(face);
        email.setTypeface(face);
        numberPeople.setTypeface(face);
        hour.setTypeface(face);
        comment.setTypeface(face);
        submit.setTypeface(face);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("Date", "Date = " + EventsController.Instance().eventsList.get(currentEvent).getEventDate());
                String string = EventsController.Instance().eventsList.get(currentEvent).getEventDate();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                Date date = null;
                String pDate = "";
                String pTime = "";
                try {
                    date = format.parse(string);
                    Format dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    pDate = dateFormatter.format(date);
                    Format timeFormatter = new SimpleDateFormat("HH:mm");
                    pTime = timeFormatter.format(date);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(validate()){
                    submit.setVisibility(View.GONE);
                    prg.setVisibility(View.VISIBLE);
//                   submitToAPI(EventsController.Instance().eventsList.get(currentEvent).getPlace_id(), "2", pDate, pTime, comment.getText().toString(), phone.getText().toString(), EventsController.Instance().eventsList.get(currentEvent).getCountry_id(), email.getText().toString(), "110", "10000000299", firstName.getText().toString() + " " + lastName.getText().toString(), hour.getText().toString(), numberPeople.getText().toString());
                     submitToAPI( EventsController.Instance().eventsList.get(currentEvent), pDate, pTime, comment.getText().toString(), phone.getText().toString(), email.getText().toString(),  firstName.getText().toString() + " " + lastName.getText().toString(), hour.getText().toString(), numberPeople.getText().toString());

                }else{

                }
            }
        });
    }

    private boolean validate(){
        boolean validator = true;
        if(firstName != null && firstName.getText().toString().equals("")){
            firstName.setError("Enter First Name");
            validator = false;
        }
        if(lastName != null && lastName.getText().toString().equals("")){
            lastName.setError("Enter Last Name");
            validator = false;
        }
        if(phone != null && phone.getText().toString().equals("")){
            phone.setError("Enter Phone");
            validator = false;
        }
        if(email != null && (email.getText().toString().equals("") || !email.getText().toString().contains("@"))){
            email.setError("Enter Email");
            validator = false;
        }
        if(numberPeople != null && numberPeople.getText().toString().equals("")){
            numberPeople.setError("Enter Number of People");
            validator = false;
        }
        if(hour != null && hour.getText().toString().equals("")){
            hour.setError("Enter hour");
            validator = false;
        }
        return validator;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }


    void submitToAPI(EventModel pModel, String pDate, String pTime, String pComment, String pPhone, String pEmail, String pName, String pHours, String pNumberPeople){
        try{
            pName = URLEncoder.encode(pName, "UTF-8");

            String methodParams = CommonVaraibles.METHOD_RESERVATION + CommonVaraibles.CONSTANT_PARAM_PLACE_ID + pModel.getPlace_id()
                    + CommonVaraibles.CONSTANT_PARAM_COVERS + pNumberPeople
                    + CommonVaraibles.CONSTANT_PARAM_DATE + pDate
                    + CommonVaraibles.CONSTANT_PARAM_TIME + pTime
                    + CommonVaraibles.CONSTANT_PARAM_COMMENT + URLEncoder.encode(pComment, "UTF-8")
                    + CommonVaraibles.CONSTANT_PARAM_PHONE + pPhone
                    + CommonVaraibles.CONSTANT_PARAM_COUNTRY_ID + pModel.getCountry_id()
                    + CommonVaraibles.CONSTANT_PARAM_EMAIL + pEmail
                    + CommonVaraibles.CONSTANT_PARAM_PARTNER_ID + "110"//pModel.getPartnerId()
                    + CommonVaraibles.CONSTANT_PARAM_AREA_ID + pModel.getArea_id()
                    + CommonVaraibles.CONSTANT_PARAM_BOOKING_NAME + pName
                    + CommonVaraibles.CONSTANT_PARAM_HOURS + pHours
                    + CommonVaraibles.CONSTANT_PARAM_NUMBER_OF_PEOPLE + pNumberPeople;

            NetworkHandler nHandler = new NetworkHandler(CommonVaraibles.TABLE_BOOKING_BASE_URL + methodParams, CommonVaraibles.API_TYPE.GET, this, this, CommonVaraibles.COMMON_TAG_RESERVATION);
            nHandler.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

//    void submitToAPI(String pPlaceId, String pCovers, String pDate, String pTime, String pComment, String pPhone, String pCountryId, String pEmail, String pPartnerId, String pAreaId, String pName, String pHours, String pNumberPeople){
//        try{
//            pName = URLEncoder.encode(pName, "UTF-8");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        String methodParams = CommonVaraibles.METHOD_RESERVATION + CommonVaraibles.CONSTANT_PARAM_PLACE_ID + pPlaceId
//                + CommonVaraibles.CONSTANT_PARAM_COVERS + pCovers
//                + CommonVaraibles.CONSTANT_PARAM_DATE + pDate
//                + CommonVaraibles.CONSTANT_PARAM_TIME + pTime
//                + CommonVaraibles.CONSTANT_PARAM_COMMENT + pComment
//                + CommonVaraibles.CONSTANT_PARAM_PHONE + pPhone
//                + CommonVaraibles.CONSTANT_PARAM_COUNTRY_ID + pCountryId
//                + CommonVaraibles.CONSTANT_PARAM_EMAIL + pEmail
//                + CommonVaraibles.CONSTANT_PARAM_PARTNER_ID + pPartnerId
//                + CommonVaraibles.CONSTANT_PARAM_AREA_ID + pAreaId
//                + CommonVaraibles.CONSTANT_PARAM_BOOKING_NAME + pName
//                + CommonVaraibles.CONSTANT_PARAM_HOURS + pHours
//                + CommonVaraibles.CONSTANT_PARAM_NUMBER_OF_PEOPLE + pNumberPeople;
//
//        NetworkHandler nHandler = new NetworkHandler(CommonVaraibles.TABLE_BOOKING_BASE_URL + methodParams, CommonVaraibles.API_TYPE.GET, this, this, CommonVaraibles.COMMON_TAG_RESERVATION);
//        nHandler.execute();
//
//
//    }

    @Override
    public void apiResult(String json, int pTag) {
        try {
            if (pTag == CommonVaraibles.COMMON_TAG_RESERVATION) {
                submit.setVisibility(View.VISIBLE);
                prg.setVisibility(View.GONE);
                JSONObject _json = new JSONObject(json);
                if(_json.getBoolean(CommonVaraibles.CONSTANT_PARAM_SUCCESS)){
                    Toast.makeText(TableBookingActivity.this, "Reservation succeed", Toast.LENGTH_SHORT).show();
                    JSONObject result = _json.getJSONObject(CommonVaraibles.CONSTANT_PARAM_RESULT);
                    AlertDialog.Builder builder = new AlertDialog.Builder(TableBookingActivity.this);
                    builder.setTitle("Reservation succeed!");
                    builder.setMessage("Code: "+ result.getString("code") + "\nVisit ID: " + result.getString("visitId"));
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            (TableBookingActivity.this).finish();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }else{
                    Toast.makeText(TableBookingActivity.this, "Reservation failed: " + _json.getString("error"), Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(TableBookingActivity.this, "Parsing Error Reservation failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void apiError(String msg, int pTag) {
        if (pTag == CommonVaraibles.COMMON_TAG_RESERVATION) {
        
        }
    }
}

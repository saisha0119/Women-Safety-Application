package com.example.n1;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
//This is first Activity here you have two options one is settings  and help buttons
public class N1 extends Activity {
	//Below are the objects and variables
	String msg, con1, con2, con3;
	EditText et1, et2, et3, et4;
	String StateName, CityName, CountryName, add, premises, street, dist, area,
			area1, local;
	double latitude;
	double longitude;
	List<Address> addresses;
	GPSTracker gps;
	public static final String MyPREFERENCES = "MyPrefs1";
	public static final String Word = "wordKey";
	public static final String Phone1 = "phoneKey1";

	public static final String Phone2 = "phoneKey2";

	public static final String Phone3 = "phoneKey3";
	SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_n1);
		//here is constructor class of GPS TRACKERS class

		gps = new GPSTracker(this);
//below is  setting button code
		ImageButton bt = (ImageButton) findViewById(R.id.imageButton1);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//when you click this it'll goto N2(Add Contacts) Page
				Intent it = new Intent(N1.this, N2.class);
				startActivity(it);

			}
		});
		//below code is for help button code
		Button bt1 = (Button) findViewById(R.id.button1);
		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//when you click help button get the values from storage
				sharedpreferences = getSharedPreferences(MyPREFERENCES,
						Context.MODE_PRIVATE);
				con1 = sharedpreferences.getString("phoneKey1", "");
				con2 = sharedpreferences.getString("phoneKey2", "");
				con3 = sharedpreferences.getString("phoneKey3", "");
				msg=sharedpreferences.getString("wordKey", "");
				//below code is for location provider we have two providers one
				//is Network and second is GPs
				Location nwLocation = gps
						.getLocation(LocationManager.GPS_PROVIDER);

				// check if GPS enabled
				//Here we write code for getting lattitude and longitude
				String stringLatitude = String.valueOf(nwLocation.getLatitude());

				String stringLongitude = String.valueOf(nwLocation
						.getLongitude());
				//here is message body
				String body = msg
						+ ""
						+ "http://maps.google.fr/maps?f=q&source=s_q&hl=fr&geocode=&q="
						+ stringLatitude + "," + stringLongitude;

				// \n is for new line
				// getlocaddress();
				//sending messages
				sendmsg(body);

			}

		});
	}

	protected void sendmsg(String body) {
		// TODO Auto-generated method stub
		//here send messages for your guardians when click on help button
		SmsManager sm = SmsManager.getDefault();
		sm.sendTextMessage(con1, null, body, null, null);
		sm.sendTextMessage(con2, null, body, null, null);
		sm.sendTextMessage(con3, null, body, null, null);

	}

}

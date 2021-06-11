package com.example.n1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

public class N2 extends Activity{
	String s,s1,s2,s3,s4;
	SQLiteDatabase db;
	 String msg,con1,con2,con3;
	 EditText et1,et2,et3,et4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ne1);

		ScrollView sv=(ScrollView)findViewById(R.id.scrollView1);
		 et1=(EditText)findViewById(R.id.editText1);
		 et2=(EditText)findViewById(R.id.editText2);
		 et3=(EditText)findViewById(R.id.editText3);
		 et4=(EditText)findViewById(R.id.editText4);
		Button bt=(Button)findViewById(R.id.button1);
		db = openOrCreateDatabase("msg", MODE_PRIVATE,null);
		Cursor c=db.rawQuery("select * from emergency1", null);
		c.moveToFirst();
		if(c.getCount()>0){
		if(c!=null)
		{
			do
			{
				
			String 	msg1=c.getString(c.getColumnIndex("message"));
			String	con1=c.getString(c.getColumnIndex("contact1"));
			String	con2=c.getString(c.getColumnIndex("contact2"));
			String	con3=c.getString(c.getColumnIndex("contact3"));
				System.out.println("@@@@@@@@@@@@@@@@@@@@"+msg1+con1+con2+con3);
				
				et1.setText(msg1);
				et2.setText(con1);
				et3.setText(con2);
				et4.setText(con3);
				
				
				
			}while(c.moveToNext());
		}else{
		System.out.println("dhfgdjgdhdj");	
		}}
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				s1=et1.getText().toString();

				s2=et2.getText().toString();
			    s3=et3.getText().toString();
			    s4=et4.getText().toString();
				db = openOrCreateDatabase("msg", MODE_PRIVATE,null);
	            db.execSQL("create table if not exists emergency1(message varchar,contact1 varchar,contact2 varchar,contat3 varchar)");
	            db.execSQL("insert into emergency1 values('"+s1+"','"+s2+"','"+s3+"','"+s4+"')");
	            	            Toast.makeText(getApplicationContext(), "inserted",Toast.LENGTH_LONG).show();
	            	            
	            	        	
	            	    	
			}
		});
			
			
				
		
		
	}


		
		
	
}
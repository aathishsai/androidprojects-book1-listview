package com.example.listview;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	ArrayList<String> names = new ArrayList<String>();
	ListView animalNames;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// adding values to the arraylist. What ever we add here will be 
		// displayed as each row in the listview
		names.add("Ant");
		names.add("Ball");
		names.add("Cat");
		names.add("Dog");
		names.add("Elephant");
		
		//mapping ListView to the xml
		animalNames = (ListView) findViewById(R.id.listView1);
		
		// defining an adapter for prodNames. This is an custom adapter
		// here we are passing as parameters the context and the arrayList which has the
		// list of entries to be displayed in the listview
		animalNames.setAdapter(new NamesAdapter(this,names));
		
	}

}

class NamesAdapter extends BaseAdapter
{
	 private LayoutInflater layoutInflater;
	 
	 //defining the local variables 
	 private ArrayList<String> animalDetails=new ArrayList<String>();
	 int count;
	 Typeface type;
	 Context context;
	 
	//constructor method
	public NamesAdapter(Context context, ArrayList<String> animal_details) {
	
		layoutInflater = LayoutInflater.from(context);
	
		 this.animalDetails=animal_details;
		 this.count= animal_details.size();
		 this.context = context;
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public Object getItem(int arg0) {
		return animalDetails.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		 
		 ViewHolder holder;
	     
		 if (convertView == null) 
	     {
	    	 convertView = layoutInflater.inflate(R.layout.list_details, null);
	         holder = new ViewHolder();
	         holder.animal_name = (TextView) convertView.findViewById(R.id.animal_name);
	         holder.animal_button = (Button) convertView.findViewById(R.id.animal_button);

			 convertView.setTag(holder);
	     } 
		 else 
	     {
	            holder = (ViewHolder) convertView.getTag();
	     }
	 
	      
		 holder.animal_name.setText(animalDetails.get(position));
	 
		 holder.animal_button.setOnClickListener(new MyPersonalClickListener(animalDetails.get(position),context));
		 
	     return convertView;
	}
	
	 static class ViewHolder 
	 {	        
	        TextView animal_name;
	        Button animal_button;	        
	        
	 }
	 
	//this is a customized clicklistener for button within ListView
	 public class MyPersonalClickListener implements OnClickListener
     {

	      
	      String animal_name;
	      Context context;
	      
	      //constructor method
	      public MyPersonalClickListener(String animal_name, Context context) 
	      {
	           this.animal_name = animal_name;
	           this.context = context;
	      }

	      @Override
	      public void onClick(View v)
	      {
	    	  Toast.makeText(context,"Animal "+ animal_name+" clicked", Toast.LENGTH_LONG).show();   	  
	      }

	   }
	 
}

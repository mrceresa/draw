package org.marioceresa.drawingtest.ui;



import java.util.ArrayList;

import org.marioceresa.drawingtest.MainActivity;
import org.marioceresa.drawingtest.R;



import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ObjectsListFragment extends ListFragment {
		
    	ArrayList<String> list = new ArrayList<String>();
    	ArrayAdapter<String> adapter;
 
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.list_fragment, null);
	
	        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.list_element, list);
			setListAdapter(adapter);

			return v;
		}
/*		@Override
		public void onListItemClick(ListView list, View view, int position, long id){
			Intent i = new Intent(ExampleActivity.ACTION_DISPLAY_TEXT);
			i.putExtra("text", array[position]);
			getActivity().sendBroadcast(i);
		}
	
*/
		
		private void addElement(String pos) {
			
			list.add(pos);
			adapter.notifyDataSetChanged();
		}
		
		@Override
		public void onResume(){
			super.onResume();
			IntentFilter displayIntentFilter = new IntentFilter();
			displayIntentFilter.addAction(MainActivity.ACTION_NEW_POLY);
			getActivity().registerReceiver(fragmentReceiver, displayIntentFilter);
			
		}
		public void onPause(){
			super.onPause();
			getActivity().unregisterReceiver(fragmentReceiver);
		}
		BroadcastReceiver fragmentReceiver = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				
				Toast.makeText(context, intent.getStringExtra("pos"), Toast.LENGTH_SHORT).show();
				addElement(intent.getStringExtra("pos"));
			}


		};

	
}

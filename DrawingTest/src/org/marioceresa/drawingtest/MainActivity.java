package org.marioceresa.drawingtest;

import java.util.ArrayList;

import org.marioceresa.drawingtest.ui.ObjectsListFragment;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity  {
	
		public static final String ACTION_NEW_POLY = "drawing.NEW_POLY";
		public static final String ACTION_CLEAR_CANVAS = "drawing.CLEAR_CANVAS";
		public static final String ACTION_PATH_RES = "drawing.PATH_RES";
		public static final String ACTION_PATH_CLOSE = "drawing.PATH_CLOSE";
		
		FragmentManager manager;
	
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        manager = getFragmentManager();
	        
	        

	    }
	    
	    @Override
	    public void onResume(){
	    	super.onResume();
	    	//If there isn't a 
	    	if(findViewById(R.id.content_fragment) == null){
	    		//registerReceiver(true);
	    		FragmentTransaction ft = manager.beginTransaction();
				ft.add(R.id.root, new ObjectsListFragment());
				ft.commit();
	    	}
	    }
	    

	    @Override
	    public void onBackPressed(){
	    	if(manager.getBackStackEntryCount() > 0){
	    		manager.popBackStackImmediate();
	    		//registerReceiver(true);
	    	}
	    	else
	    		super.onBackPressed();
	    }
	    


	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.main_menu, menu);
	        return true;
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle item selection
	    	Intent i;
	        switch (item.getItemId()) {
	            case R.id.new_canvas:
	        		i = new Intent(MainActivity.ACTION_CLEAR_CANVAS);
	        		sendBroadcast(i);
	                return true;
	                
	            case R.id.reset:
	            	i = new Intent(MainActivity.ACTION_PATH_RES);
	        		sendBroadcast(i);
	              
	                return true;
	                
	            case R.id.close:
	            	i = new Intent(MainActivity.ACTION_PATH_CLOSE);
	        		sendBroadcast(i);
	            	//_surface.close_current_path();
	                return true;

	            case R.id.segment:
	                //segmentSurface();
	                return true;
	            
	            default:
	                return super.onOptionsItemSelected(item);
	        }
	    }

		private void segmentSurface() {
			Toast.makeText(getApplicationContext(), "ConnectedComponent", Toast.LENGTH_SHORT).show();
			
		}

		


}

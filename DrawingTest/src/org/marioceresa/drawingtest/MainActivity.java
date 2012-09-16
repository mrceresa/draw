package org.marioceresa.drawingtest;

import java.util.ArrayList;



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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity  implements OnClickListener {
	
    	    	
    	
    	private DrawingSurface _surface;
		private ListView _list;
    	
    	
    	
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);

	        _surface = (DrawingSurface) findViewById(R.id.drawingSurface);
	        _list = (ListView) findViewById(R.id.listView1);
			_list.setAdapter(_surface.adapter);
			
	        Button pen = (Button) findViewById(R.id.btnPen);
	        pen.setOnClickListener(this);
	        Button rub = (Button) findViewById(R.id.btnRubber);
	        rub.setOnClickListener(this);


	        
	    }
	    
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.btnPen) {
	            _surface.setBrush(new PenBrush());
	            _surface.setFGColor(Color.YELLOW);
	            _surface.setPaintStyle(Paint.Style.STROKE);
			} else if (v.getId() == R.id.btnRubber) {
	        	CircleBrush r=new CircleBrush();
	        	r.setWidth(50);
	            _surface.setBrush(r);
	            _surface.setFGColor(Color.BLACK);
	            _surface.setPaintStyle(Paint.Style.FILL);
				
			} 
			 
			
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
	    	
	        switch (item.getItemId()) {
	            case R.id.new_canvas:
	        		_surface.clearCanvas();
	                return true;
	                
	            case R.id.reset:
	            	_surface.reset_current_path();
	              
	                return true;
	                
	            case R.id.close:
	            	_surface.close_current_path();
	                return true;

	            case R.id.segment:
	                segmentSurface();
	                return true;
	            
	            default:
	                return super.onOptionsItemSelected(item);
	        }
	    }

		private void segmentSurface() {
			Toast.makeText(getApplicationContext(), "ConnectedComponent", Toast.LENGTH_SHORT).show();
			
		}

		


}

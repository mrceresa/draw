package org.marioceresa.drawingtest.ui;

import org.marioceresa.drawingtest.CircleBrush;
import org.marioceresa.drawingtest.DrawingSurface;
import org.marioceresa.drawingtest.MainActivity;
import org.marioceresa.drawingtest.PenBrush;
import org.marioceresa.drawingtest.R;



import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DrawFragment extends Fragment implements OnClickListener{
	
	private DrawingSurface _surface;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.content_layout, null);
		
		_surface = (DrawingSurface) v.findViewById(R.id.drawingSurface);
		_surface.setOwnerFragment(this);
		
        Button pen = (Button) v.findViewById(R.id.btnPen);
        pen.setOnClickListener(this);
        Button rub = (Button) v.findViewById(R.id.btnRubber);
        rub.setOnClickListener(this);
		return v;
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
	public void onResume(){
		super.onResume();
		IntentFilter displayIntentFilter = new IntentFilter();
		displayIntentFilter.addAction(MainActivity.ACTION_CLEAR_CANVAS);
		getActivity().registerReceiver(fragmentReceiver, displayIntentFilter);
		
	}
	public void onPause(){
		super.onPause();
		getActivity().unregisterReceiver(fragmentReceiver);
	}
	BroadcastReceiver fragmentReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(MainActivity.ACTION_CLEAR_CANVAS)) {
				_surface.clearCanvas();
			} else if (intent.getAction().equals(MainActivity.ACTION_PATH_RES)) {
				_surface.reset_current_path();
			} else if (intent.getAction().equals(MainActivity.ACTION_PATH_CLOSE)) {
				_surface.close_current_path();
			}
		}


	};

	
    

}

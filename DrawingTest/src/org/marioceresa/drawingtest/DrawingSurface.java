package org.marioceresa.drawingtest;

import java.util.ArrayList;


import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DrawingSurface extends SurfaceView implements SurfaceHolder.Callback, OnTouchListener {
	
	private DrawingThread _thread;    
	private ArrayList<DrawingPath> _graphics = new ArrayList<DrawingPath>();
	private DrawingPath currentPath;
	private IBrush currentBrush;
	private int fgColor = Color.YELLOW;
	private Style defaultStyle = 	Paint.Style.STROKE;
	public MainActivity parent;
	ArrayAdapter<DrawingPath> adapter;
	
	public DrawingSurface(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public DrawingSurface(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

    public DrawingSurface(Context context) {
        super(context);
        init(context);
    }

    
    private void init(Context context) {
        getHolder().addCallback(this);
        _thread = new DrawingThread(getHolder(), this);
        setFocusable(true);
        setBrush(new PenBrush());
        currentPath =  new DrawingPath("Poly_0", fgColor, defaultStyle);
		setOnTouchListener(this);
		adapter = new ArrayAdapter<DrawingPath>(context, R.layout.list_element, _graphics);
	}

	public void setFGColor(int color) {
		fgColor = color;
		
	}

	public void setPaintStyle(Style style) {
		defaultStyle = style;

		
	}
	
	
	public void reset_current_path() {
		currentPath.getPath().reset();
		
	}

	public void close_current_path() {
		currentPath.getPath().close();
		
	}

	
	
	public void setBrush(IBrush brush) {
		this.currentBrush = brush;
		
	}

	public void clearCanvas() {
		synchronized (_thread.getSurfaceHolder()) {
			adapter.clear();
		}
		this.invalidate();
	}

    
    @Override
    public void onDraw(Canvas canvas) {
    	if (canvas == null)
    		return;
    	canvas.drawColor(Color.BLACK);
		for (DrawingPath path : _graphics) {
		    canvas.drawPath(path.getPath(), path.getPaint());
		}
    }
    
    public boolean onTouch(View v, MotionEvent event) {
    	
    	boolean defaultResult = v.onTouchEvent(event);
    	
    	if(event.getAction() == MotionEvent.ACTION_DOWN){
    		String pName = "Poly_"+Integer.toString(_graphics.size());
    		currentPath = new DrawingPath(pName,fgColor, defaultStyle);
    		currentBrush.mouseDown(currentPath.getPath(), event.getX(), event.getY());
        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
        	currentBrush.mouseMove(currentPath.getPath(), event.getX(), event.getY());
        }else if(event.getAction() == MotionEvent.ACTION_UP){
        	currentBrush.mouseUp(currentPath.getPath(), event.getX(), event.getY());
        	synchronized (_thread.getSurfaceHolder()) {
        	  adapter.add(currentPath);
        	}
        	
        	

        } else {

        	return defaultResult;
        }
        
        return true;
      }
      
    

    
    
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		 _thread.setRunning(true);
		 _thread.start();
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		 boolean retry = true;
		 _thread.setRunning(false);
		    while (retry) {
		        try {
		            _thread.join();
		            retry = false;
		        } catch (InterruptedException e) {
		            // we will try it again and again...
		        }
		    }
		
	}
	



	class DrawingThread extends Thread {
	        private SurfaceHolder _surfaceHolder;
	        private DrawingSurface _surface;
	        private boolean _run = false;
	     
	        public DrawingThread(SurfaceHolder surfaceHolder, DrawingSurface panel) {
	            _surfaceHolder = surfaceHolder;
	            _surface = panel;
	        }
	        
	        public SurfaceHolder getSurfaceHolder() {
	            return _surfaceHolder;
	        }
	     
	        public void setRunning(boolean run) {
	            _run = run;
	        }
	     
	        @Override
	        public void run() {
	        	Canvas c;
	            while (_run) {
	                c = null;
	                try {
	                    c = _surfaceHolder.lockCanvas(null);
	                    synchronized (_surfaceHolder) {
	                    	_surface.onDraw(c);
	                    }
	                } finally {
	                    // do this in a finally so that if an exception is thrown
	                    // during the above, we don't leave the Surface in an
	                    // inconsistent state
	                    if (c != null) {
	                        _surfaceHolder.unlockCanvasAndPost(c);
	                    }
	                }
	            }
	     
	        }
	    }






	
}

package org.marioceresa.drawingtest;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class DrawingPath {

    private IBrush brush;
    private Paint paint;
    private Path path;
    
    public DrawingPath(int fgColor, Style style) {
    	
    	 brush = new PenBrush();
		 paint = new Paint();
		 paint.setDither(true);
		 paint.setColor(fgColor);
		 paint.setStyle(style);
		 paint.setStrokeJoin(Paint.Join.ROUND);
		 paint.setStrokeCap(Paint.Cap.ROUND);
		 paint.setStrokeWidth(3);
		 path = new Path();
		 
   }
	
    public IBrush getBrush() {
		return brush;
	}
	
    public void setBrush(IBrush brush) {
		this.brush = brush;
	}
	
    public Paint getPaint() {
		return paint;
	}
	
    public void setPaint(Paint paint) {
		this.paint = paint;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}
 
	
}

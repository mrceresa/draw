package org.marioceresa.drawingtest;

import android.graphics.Path;

public class CircleBrush extends Brush {
	
	private int width = 10;

	  @Override
	  public void mouseMove(Path path, float x, float y) {
		  path.addCircle(x,y,width,Path.Direction.CW);
	  }

	public void setWidth(int i) {
		width = i;
		
	}

}

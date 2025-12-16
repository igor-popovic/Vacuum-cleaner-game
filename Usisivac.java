package v1;

import java.awt.Color;
import java.awt.Graphics;

public class Usisivac extends Figura {

	public Usisivac(int x, int y) {
		super(x, y);
		r=15;
		boja = Color.RED;
	}

	@Override
	public void crt(Graphics g) {
		int[] xx = {x,x+13, x-13};
		int yy[] = {y-15, y+7, y+7};
		
		g.fillPolygon(xx, yy, 3);
		
	}
	
	public int korak() {return r/2;}

}

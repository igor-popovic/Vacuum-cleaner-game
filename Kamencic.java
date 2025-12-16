package v1;

import java.awt.Color;
import java.awt.Graphics;

public class Kamencic extends Figura {

	public Kamencic(int x, int y) {
		super(x, y);
		r=5;
		boja = Color.BLACK;
	}

	@Override
	public void crt(Graphics g) {
		g.fillOval(x-r, y-r, 10, 10);
	}

}

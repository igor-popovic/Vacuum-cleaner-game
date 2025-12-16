package v1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;

public  abstract class Figura {

	protected int x,y;
	protected int r;
	protected Color boja;
	
	public Figura(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	public double r() {
		return r;
	}
	
	public int x() {return x;}
	public int y() {return y;}
	
	public void setX(int a) {x=a;}
	public void setY(int a) {y=a;}
	
	//vrati se na ovo
	public void crtaj(Scena s) {
		Graphics g = s.getGraphics();
		g.setColor(boja);
		crt(g);
	}
	
	public abstract void crt(Graphics g);
	
	public double rastojanje(Figura f) {
		return Math.sqrt( (x-f.x())*(x-f.x()) + (y-f.y())*(y-f.y()) );
	}
	
	public boolean preklapa(Figura f) {
		return this.rastojanje(f)<=r+f.r();
	}
	
	public boolean obuhavata(Figura f) {
		if(f.r()>r) return false;
		return this.rastojanje(f)+f.r() <=r;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Figura other = (Figura) obj;
		return x == other.x && y == other.y;
	}
	
	
	
}

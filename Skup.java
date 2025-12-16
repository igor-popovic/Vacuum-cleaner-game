package v1;

import java.util.ArrayList;

public class Skup {

	private ArrayList<Figura> figure;
	private int tek = -1;
	
	public Skup() {
		figure = new ArrayList<>();
	}
	
	public void dodaj(Figura f) throws GDod {
		if(figure.contains(f)) throw new GDod();
		figure.add(f);
	}
	
	public void staviTek() {
		if(figure.size()>0) tek=0;
		else tek=-1;
	}
	public void sled() throws GTek {
		tek++;
		if(tek==figure.size()) throw new GTek();
		
	}
	
	public Figura dohvTek() {
		if(tek>=0 && figure.get(tek)!=null) return figure.get(tek);
		else return null;
	}
	
	public boolean postojiSled() {
		return !(tek+1>=figure.size());
	}
	
	public void ispazni() {
		figure.clear();
	}
	
	public boolean nalazi(Figura f) {
		return figure.contains(f);
	}
	
	public void izbaci(Figura f) {
		if(figure.contains(f)) figure.remove(f);
	}
	
	public int broj() {
		return figure.size();
	}
	
}

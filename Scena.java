package v1;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Scena extends Canvas implements Runnable {

	private Simulacija simul;
	private Skup skup;
	private Usisivac usis;
	
	private boolean radi=true;
	private Thread nit=new Thread(this);
	
	public Scena(Simulacija s) {
		simul = s;
		usis = new Usisivac(200,150);
		skup = new Skup();
		
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_SPACE :
					if(radi) { pauziraj();}
					else { nastavi();}
					break;
				case KeyEvent.VK_ESCAPE:
					prekini(); simul.dispose();
					break;
				}
			}
		});
		
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				dodaj(new Kamencic(x,y));
				repaint();
			}
		});
		
		
		/*try {
			skup.dodaj(usis);
		} catch (GDod e1) {}*/
		
		nit.start();
	}
	
	@Override
	public void run() {
		
		try {
			
			while(!Thread.interrupted()) {
				
				synchronized (this) {
					while(!radi) wait();
				}
				
				Thread.sleep(50);
				while(skup.broj()==0) pauziraj();
				
				Figura f = najbliza();
				pomeri(f);
				if(usis.preklapa(f)) skup.izbaci(f);
				
				repaint();
			}
		} catch(InterruptedException e) {}
	}


	public synchronized void dodaj(Figura f) {
		try {
			skup.dodaj(f);
			repaint();
		} catch (GDod e) { return;}
	}

	//ne koristim ni za sta 
	//treba usisivac da se napravi i doda u figure
	public synchronized void kreni() {
		skup.ispazni();
		usis=null;
		radi = true;
		notify();
	}
	
	
	public synchronized void nastavi() {
		radi=true;
		notifyAll();
	}
	
	public synchronized void pauziraj() {
		radi = false; notifyAll();
		
	}
	
	public void prekini() {
		nit.interrupt();
	}
	
	public boolean radi() {
		return radi;
	}
	
	//onako radi
	public  synchronized void paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		skup.staviTek();
		if(skup.dohvTek()!=null) skup.dohvTek().crtaj(this);
		
		usis.crtaj(this);
		
		while(skup.postojiSled()) {
			
			try {
				skup.sled();
				if(skup.dohvTek()!=null) skup.dohvTek().crtaj(this);
			} catch (GTek e) {} 
		}
			
		
	}
	
	
	//dobro radi
	private synchronized void pomeri(Figura f) {
		int k = usis.korak();
		boolean a=true;
		
		if(usis.y() > f.y() +k && a ) {
				usis.setY(usis.y()-k); a=false; 
			
		} else if(usis.y < f.y()-k && a) {
				usis.setY(usis.y()+k); a=false;
		}
		
		if(usis.x() > f.x()+k && a) {
				usis.setX(usis.x()-k); a=false;
		} else if(usis.x < f.x()-k && a ) {
				usis.setX(usis.x()+k); a=false;
		}
		
		
		
	}
	
	//dobro radi
	private synchronized Figura najbliza() {
		
		skup.staviTek();
		
		Figura naj = skup.dohvTek();
		
		while(skup.postojiSled()) {
			Figura f = skup.dohvTek();
			if(usis.rastojanje(f) < usis.rastojanje(naj)) naj=f;
			try {
				skup.sled();
			} catch (GTek e) {}
		}
		Figura f = skup.dohvTek();
		if(usis.rastojanje(f) < usis.rastojanje(naj)) naj=f;
		
		return naj;
	}
	
	
		
}
	


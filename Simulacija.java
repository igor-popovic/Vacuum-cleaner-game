package v1;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.*;

public class Simulacija extends Frame {

	private Scena scena = new Scena(this);
	
	
	public Simulacija() {
		setBounds(700,200,400,300);
		setTitle("Simulacija");
		setResizable(false);
		setLayout(new BorderLayout());
		scena.setBackground(Color.GRAY);
		
		popuni();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				scena.prekini();
				dispose();
			}
		});
		
		setVisible(true);
	}
	

	private void popuni() {
		
		this.add(scena, BorderLayout.CENTER);
		
	}
	
	
	public static void main(String[] args) {
		new Simulacija();
	}
	
}

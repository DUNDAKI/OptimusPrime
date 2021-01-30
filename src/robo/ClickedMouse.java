package robo;

import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class ClickedMouse {
	
	JLabel  mousePosition;
	
	public void start() {
		mousePosition = new JLabel();
	//	mousePosition.setText("Coordenada do click: " + e.getX() + " , " + e.getY());
	}
	
	public void mouseClicked(MouseEvent e) {
		
		
		
		  mousePosition.setText("Coordenada do click: " + e.getX() + " , " + e.getY());
		  System.out.println();
	}

}

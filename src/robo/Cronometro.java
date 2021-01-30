package robo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Cronometro {
	private int h = 0;
	private int m = 0;
	private int s = 0;
	
	private int h2 = 0;
	private int m2 = 0;
	private int s2 = 0;
	private int cont = 0;
	
	private boolean rodando = false;
	private Timer tm;
	
	private JLabel contagemTempo, horaTotal, update;
	
	
	public void crono() {
		cont++;
		
		if (!rodando) {
			rodando = true;
			tm = new Timer();
			tm.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					
					s+=1;
					s2+=1;
					
					
					
					horaTotal.setText(String.format("%02d:%02d:%02d", h, m, s));// altera a label da
					System.out.println(h+":"+m +":"+s);
					
					if(s == 3) {
						
						m+=1;
						s=0;
					}
					
					if(m == 2) {
						
						h+=1;
						m=0;
					}
					
					
					
					
					//controla tempo de atualizacao
					if(s2 == 59) {
						m2+=1;
						s2=0;
					}
					
					if(m2 == 59) {
						m2+=1;
						s2 = 0;
					}
					
					
					if(h2 == 2) {
						 try {
							upateCrono();
							update.setText(String.format("%02d", cont++));// quantidade de atualizaçoes
						} catch (AWTException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}								
						 h2 = 0;
					}
					
//				
//					contagemTempo.setText(String.format("%02d:%02d:%02d", hora, min, seg));// altera a label da
//																							// contagem de tempo
					
					contagemTempo.setText(String.format("%02d:%02d:%02d", h2, m2, s2));// altera a label da
//
					

					
					}
					
				
			}, 1000, 1000);
			// TODO Auto-generated method stub
		}
	}

	
	public void upateCrono() throws AWTException {

		Robot optimusPrime;
		optimusPrime = new Robot();
		optimusPrime.setAutoDelay(1000);

		optimusPrime.mouseMove(1225, 445);

		optimusPrime.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		optimusPrime.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

		optimusPrime.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		optimusPrime.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

		System.out.println("Numero de Atualizações: " + cont);

	}
}

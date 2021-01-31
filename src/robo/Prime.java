package robo;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Prime extends JFrame {
	Robot optimusPrime;
	
	JLabel mousePosition;

	private int h = 0;
	private int m = 0;
	private int s = 0;

	private int h2 = 0;
	private int m2 = 0;
	private int s2 = 0;
	private int cont = 0;
	private boolean rodando = false;
	private boolean horaRoda = false;

	private JLabel horaTotal, update, hora;
	private Timer tm,tm2;

	private JButton start, stop;

	private JPanel painel;
	private JLabel lblNewLabel_2;

	double p, p1;
	private JLabel lblNewLabel;

	
	/**
	 * Launch the application.
	 */
		public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					Prime frame = new Prime();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Prime() {
		setForeground(new Color(0, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage("A:\\Programacao\\Udemy\\Rolito-master\\img\\robolito.png"));

		setTitle("Cronometro                  Versão:1.0");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 465);
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painel);
		painel.setLayout(null);

		// altera a cor do Jpanel
		painel.setBackground(new Color(255, 69, 0));

		JLabel lblNewLabel_1 = new JLabel("Monitoramento Total");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(274, 11, 200, 14);
		painel.add(lblNewLabel_1);

		
		start = new JButton("Start");
		start.setForeground(new Color(255, 0, 0));
		start.setBackground(new Color(255, 255, 255));
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 crono();
				
			}
		});
		start.setFont(new Font("Tahoma", Font.BOLD, 14));
		start.setBounds(494, 36, 110, 33);
		painel.add(start);
		
		
		
		stop = new JButton("Stop");
		stop.setForeground(new Color(255, 0, 0));
		stop.setFont(new Font("Tahoma", Font.BOLD, 14));
		stop.setBounds(494, 75, 110, 33);
		painel.add(stop);

		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (rodando) {
					tm.cancel();
					rodando = false;

					h = 0;
					m = 0;
					s = 0;
				}
			}
		});
		
		lblNewLabel_2 = new JLabel("Total de Atualiza\u00E7oes");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(274, 178, 151, 21);
		painel.add(lblNewLabel_2);
		
		lblNewLabel = new JLabel("HOR\u00C1RIO DE BRASILIA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
		lblNewLabel.setBounds(465, 183, 163, 14);
		painel.add(lblNewLabel);
		
		
		hora = new JLabel("00:00:00");
		painel.add(hora);
		hora.setHorizontalAlignment(SwingConstants.CENTER);
		hora.setBounds(484, 223, 120, 42);
		hora.setFont(new Font("Dialog", Font.PLAIN, 28));
		
				update = new JLabel("00");
				update.setBounds(311, 216, 56, 64);
				painel.add(update);
				update.setFont(new Font(update.getName(), Font.PLAIN, 50));
				
						horaTotal = new JLabel("00 00 00");
						horaTotal.setBounds(274, 36, 196, 77);
						painel.add(horaTotal);
						horaTotal.setBackground(new Color(0, 255, 102));
						horaTotal.setFont(new Font(horaTotal.getName(), Font.PLAIN, 50));
		
		
	
	}

	public void rodando() {
		if (!horaRoda) {
			horaRoda = true;
			tm2 = new Timer();
			tm2.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					
					
						
						Calendar c = Calendar.getInstance();
						int h = c.get(Calendar.HOUR_OF_DAY);
						int m = c.get(Calendar.MINUTE);
						int s = c.get(Calendar.SECOND);
						
						hora.setText(String.format("%02d:%02d:%02d", h, m, s));
					
				
				}
			}, 1000, 1000);
			
		}
	}
	
	
	public void crono() {
		cont++;
		rodando();
		if (!rodando) {
			rodando = true;
			tm = new Timer();
			tm.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					
					

					s += 1;
					s2 += 1;
					horaTotal.setText(String.format("%02d:%02d:%02d", h, m, s));// altera a label da
					System.out.println(h + ":" + m + ":" + s);

					if (s == 59) {

						m += 1;
						s = 0;
					}

					if (m == 59) {

						h += 1;
						m = 0;
					}

					
					if (h == 1) {
						try {
							upateCrono();
							update.setText(String.format("%02d", cont++));// quantidade de atualizaçoes
						} catch (AWTException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						h = 0;
					}
				
				}
			}, 1000, 1000);
			
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

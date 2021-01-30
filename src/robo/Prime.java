package robo;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Prime extends JFrame {
	JPanel painelUpdate;
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

	private JLabel horaTotal, update;
	private Timer tm;

	private JButton start, stop;

	private JPanel painel;
	private JLabel lblNewLabel_2;
	private JPanel atualiza;
	private JTextField posX;
	private JTextField posY;

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
		setTitle("Cronometro");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 330);
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painel);
		painel.setLayout(null);

		// altera a cor do Jpanel
		painel.setBackground(Color.CYAN);

		painelUpdate = new JPanel();
		painelUpdate.setBounds(47, 36, 200, 53);
		painel.add(painelUpdate);
		painelUpdate.setSize(200, 78);

		horaTotal = new JLabel("00:00:00");
		painelUpdate.add(horaTotal);
		horaTotal.setFont(new Font(horaTotal.getName(), Font.PLAIN, 50));

		atualiza = new JPanel();
		atualiza.setBounds(134, 205, 151, 75);
		painel.add(atualiza);

		update = new JLabel("00");
		atualiza.add(update);
		update.setFont(new Font(update.getName(), Font.PLAIN, 50));

		JLabel lblNewLabel_1 = new JLabel("Monitoramento Total");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(47, 11, 200, 14);
		painel.add(lblNewLabel_1);

		start = new JButton("Start");

		start.setBounds(260, 35, 110, 33);
		painel.add(start);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				crono();

			}
		});

		lblNewLabel_2 = new JLabel("Total de Atualiza\u00E7oes");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(134, 173, 151, 21);
		painel.add(lblNewLabel_2);

		stop = new JButton("Stop");

		stop.setBounds(260, 80, 110, 33);
		stop.setForeground(new Color(0, 0, 102));
		painel.add(stop);
		
		posX = new JTextField();
		posX.setBounds(10, 188, 86, 20);
		painel.add(posX);
		posX.setColumns(10);
		
		posY = new JTextField();
		posY.setBounds(10, 219, 86, 20);
		painel.add(posY);
		posY.setColumns(10);
		
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

	}

	

	public void crono() {
		cont++;

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

					// controla tempo de atualizacao
					if (s2 == 59) {
						m2 += 1;
						s2 = 0;
					}

					if (m2 == 59) {
						m2 += 1;
						s2 = 0;
					}

					if (h2 == 1) {
						try {
							upateCrono();
							update.setText(String.format("%02d", cont++));// quantidade de atualizaçoes
						} catch (AWTException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						h2 = 0;
					}

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

package main;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI implements KeyListener{

	private JFrame frame;
	//dichiaro una matrice di tipo JButton
	private JButton[][] pulsanti;
	private JLabel lbround;
	

	private boolean inizioGiocatore1 = true;
	private char giocatore1Verde = 'X';
	private char giocatore2Rosso = 'O';
	private JLabel lblReset;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Tris");
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBackground(Color.DARK_GRAY);
		frame.setResizable(false);
		frame.setSize(500, 650);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.addKeyListener(this);
		frame.setFocusable(true);
		
		
		JLabel lblTitolo = new JLabel("TRIS!");
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setBackground(Color.WHITE);
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("FiraCode Nerd Font", Font.BOLD, 42));
		lblTitolo.setBounds(12, 23, 476, 52);
		frame.getContentPane().add(lblTitolo);
		
		lbround = new JLabel("");
		lbround.setFont(new Font("FiraCode Nerd Font", Font.BOLD, 25));
		lbround.setForeground(Color.BLACK);
		lbround.setHorizontalAlignment(SwingConstants.CENTER);
		lbround.setBounds(12, 545, 476, 44);
		frame.getContentPane().add(lbround);
		
		JLabel lblRound = new JLabel("PREMI R PER RIAVVIARE");
		lblRound.setBackground(Color.WHITE);
		lblRound.setForeground(Color.WHITE);
		lblRound.setFont(new Font("FiraCode Nerd Font", Font.BOLD, 20));
		lblRound.setHorizontalAlignment(SwingConstants.CENTER);
		lblRound.setBounds(12, 601, 463, 37);
		frame.getContentPane().add(lblRound);
		
		
		
		
		//imposto la grandezza della matrice pulsanti a tre righe e tre colonne 
		pulsanti = new JButton[3][3];
		for(int riga = 0; riga < pulsanti.length; riga++) {
			
			for (int colonna = 0; colonna < pulsanti.length; colonna++) {
				
				//dichiara un pulsante nella posizione riga:colonna
				pulsanti[riga][colonna] = new JButton(" ");
				
				pulsanti[riga][colonna].setFont(new Font("FiraCode Nerd Font", Font.BOLD, 100));
				pulsanti[riga][colonna].setBounds((riga * 130) + 55, (colonna * 140) + 90, 120, 120);
				pulsanti[riga][colonna].setBackground(Color.LIGHT_GRAY);
				// aggiungo i pulsanti al frame 
				frame.getContentPane().add(pulsanti[riga][colonna]);
				pulsanti[riga][colonna].addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						System.out.println("PREMUTO");
						
						JButton temp = (JButton)e.getComponent();
						
						
						// serve per calcolare la riga e colonna
						int x = (temp.getX() - 55) / 130;
						int y = (temp.getY() - 90) / 140;
						System.out.println( (x) + "|" + (y));
						
						GestioneTris(x, y);
						//ogni volta che premo su un bottone mi mette il focus su di esso e non posso premerlo, quindi è necessario disattivarlo
						pulsanti[x][y].setFocusable(false);
						
					}
				});
				
			}
		}

			
			
		}
	private void GestioneTris(int x, int y) {
		
		if(inizioGiocatore1) {
			// posso accedere a pulsanti perchè è una variabile di instanza
			if (pulsanti[x][y].getText().equals(" ") && !controlloVittoria()){
				pulsanti[x][y].setText("" + giocatore1Verde);
				pulsanti[x][y].setForeground(Color.GREEN);
				inizioGiocatore1 = false;
				if (controlloVittoria()) {
					lbround.setForeground(Color.GREEN);
					lbround.setText("HA VINTO IL GIOCATORE: " + giocatore1Verde);
					
				}else {
				lbround.setForeground(Color.RED);
				lbround.setText("ROUND DI GIOCATORE: " + giocatore2Rosso);
				}
			}
			
		}else {
			if (pulsanti[x][y].getText().equals(" ") && !controlloVittoria())
			{
				pulsanti[x][y].setText("" + giocatore2Rosso);
				pulsanti[x][y].setForeground(Color.RED);
				inizioGiocatore1 = true;
			}
			if (controlloVittoria()) {
				lbround.setForeground(Color.RED);
				lbround.setText("HA VINTO IL  GIOCATORE: " + giocatore2Rosso);
				
			}else {
			lbround.setForeground(Color.GREEN);
			lbround.setText("ROUND DI GIOCATORE: " + giocatore1Verde);
			}
		}
		
		
		
	}
	
	private boolean controlloVittoria() {
		
		
		boolean vittoria = false;
			
		//RIGHE ORIZZONTALI	
		if (pulsanti[0][0].getText().equals(pulsanti[0][1].getText()) && pulsanti[0][1].getText().equals(pulsanti[0][2].getText()) && pulsanti[0][2].getText().equals(" ") == false)
		{
			vittoria = true;
		}
		if(pulsanti[0][1].getText().equals(pulsanti[1][1].getText()) && pulsanti[1][1].getText().equals(pulsanti[2][1].getText()) && pulsanti[2][1].getText().equals(" ") == false)
		{
			vittoria = true;
		
		}
		if (pulsanti[0][2].getText().equals(pulsanti[1][2].getText()) && pulsanti[1][2].getText().equals(pulsanti[1][2].getText()) && pulsanti[2][2].getText().equals(" ") == false)
		{
			vittoria = true;
		}
		
		
		//RIGHE VERTICALI
		if (pulsanti[0][0].getText().equals(pulsanti[1][0].getText()) && pulsanti[1][0].getText().equals(pulsanti[2][0].getText()) && pulsanti[2][0].getText().equals(" ") == false) 
		{
			vittoria = true;
		}
		if (pulsanti[1][0].getText().equals(pulsanti[1][1].getText()) && pulsanti[1][1].getText().equals(pulsanti[2][1].getText()) && pulsanti[2][1].getText().equals(" ") == false) 
		{
			vittoria = true;
		}
		if (pulsanti[2][0].getText().equals(pulsanti[2][1].getText()) && pulsanti[2][1].getText().equals(pulsanti[2][2].getText()) && pulsanti[2][2].getText().equals(" ") == false) 
		{
			vittoria = true;
		}
		
		
		//RIGHE OBLIQUE
		if (pulsanti[0][0].getText().equals(pulsanti[1][1].getText()) && pulsanti[1][1].getText().equals(pulsanti[2][2].getText()) && pulsanti[2][2].getText().equals(" ") == false) 
		{
			vittoria = true;
			
		}
		if (pulsanti[0][2].getText().equals(pulsanti[1][1].getText()) && pulsanti[1][1].getText().equals(pulsanti[2][0].getText()) && pulsanti[2][0].getText().equals(" ") == false) 
		{
			vittoria = true;
		}
		
		return vittoria;
		
	}

	
	private void Reload() {
for(int riga = 0; riga < pulsanti.length; riga++) {
			for (int colonna = 0; colonna < pulsanti.length; colonna++) {
				pulsanti[riga][colonna].setText(" ");
				
			}
	}
	lbround.setText(" ");
}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code  == KeyEvent.VK_R) {
			
			System.out.println("premuto");
			Reload();
			
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

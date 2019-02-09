import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends Thread implements ActionListener{
	private JFrame frame;
	private JPanel panel;
	private JLabel label1,label2,label3,label4;
	private JLabel text1,text2,text3,text4;
	private JButton start,stop,pause;
	private String etat;
	
	Main(){
		frame = new JFrame("Choronométre");
		frame.setSize(320, 200);
	
		label1 = new JLabel("hh");
		label1.setBounds(60,30,30,30);
		text1 = new JLabel("00");
		text1.setBounds(60,65,30,30);
		
		label2 = new JLabel("mm");
		label2.setBounds(90,30,30,30);
		text2 = new JLabel("00");
		text2.setBounds(90,65,30,30);
		
		label3 = new JLabel("ss");
		label3.setBounds(120,30,30,30);
		text3 = new JLabel("00");
		text3.setBounds(120,65,30,30);
		
		label4 = new JLabel("ms");
		label4.setBounds(150,30,30,30);
		text4 = new JLabel("000");
		text4.setBounds(150,65,30,30);
		
		start = new JButton("start");
		start.addActionListener(this);
		start.setBounds(30, 100, 80, 30);
		
		pause = new JButton("pause");
		pause.addActionListener(this);
		pause.setBounds(120, 100, 80, 30);
		
		stop = new JButton("stop");
		stop.addActionListener(this);
		stop.setBounds(210, 100, 80, 30);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(text1);
		panel.add(text2);
		panel.add(text3);
		panel.add(text4);
		panel.add(start);
		panel.add(pause);
		panel.add(stop);
		
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
	}
	
	public void run() {
		int ss=0,mm=0,hh=0;
		int i=0;
		while(!Thread.interrupted()) {	
			if(etat.compareTo("start") == 0) {
				for(;i<1000;i++){
					if(etat.compareTo("start") == 0) text4.setText(String.valueOf(i));
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				i=0;
				if(etat.compareTo("start") == 0) {
					++ss;
					if(mm == 60) {
						++hh;
						mm=0;
					}
					if(ss == 60) {
						++mm;
						ss=0;
					}
					text1.setText(String.valueOf(hh));
					text2.setText(String.valueOf(mm));
					text3.setText(String.valueOf(ss));
				}
			}
			if(etat.compareTo("pause") == 0) {
				i=Integer.parseInt(text4.getText().toString());
			}
			if(etat.compareTo("stop") == 0) {
				hh=mm=ss=0;
				text1.setText("00");
				text2.setText("00");
				text3.setText("00");
				text4.setText("000");
			}
		}
		interrupt();
	}
	
	public static void main(String[] args) throws ParseException {
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start) {
			if(etat == null) {
				etat = "start";
				start();
			}else {
				etat = "start";
			}
		}
		if(e.getSource() == pause) {
			etat = "pause";
		}
		if(e.getSource() == stop) {
			etat = "stop";
		}
	}
}

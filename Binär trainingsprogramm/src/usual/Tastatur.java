package usual;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.Robot;


public class Tastatur extends JFrame implements ActionListener, KeyListener{
	
	JPanel[] jpnl = new JPanel[4];
	Label[] lbl = new Label[6];
	JButton[] jbtn = new JButton[4];
	JTextArea jta = new JTextArea();
	JScrollPane jsp = new JScrollPane(jta);
	String[] lblInhalt = {"+1","+2","+4", "+8", "+16", "Groﬂ"};
	Boolean[] binMerk = new Boolean[6];
	Robot robo;
	Boolean keyActive = true;
	
	int auswahl = 0;
	int zahl = 0;
	

	
	
	public Tastatur(){
		
			try {
				robo = new Robot();
			} catch (AWTException e) {
				
				e.printStackTrace();
			}
	
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(600,400,600,400);
		
		for(int i=0; i< jpnl.length; i++){
			jpnl[i] = new JPanel();
			jpnl[i].addKeyListener(this);
		}
		
		
		
		setLayout(new BorderLayout());
		add(jpnl[0], "South");
		jpnl[0].setLayout(new GridLayout(1,4));
		
		for(int i=0; i< jbtn.length; i++){
			jbtn[i] = new JButton();
			jbtn[i].addActionListener(this);
			jpnl[0].add(jbtn[i]);
			jbtn[i].addKeyListener(this);
		}
		
		for(int i=0; i< binMerk.length; i++){
			binMerk[i] = false;
		}
		
		
		add(jpnl[1], "Center");
		
		jpnl[1].setLayout(new GridLayout(1,2));
		jpnl[1].add(jpnl[2]);
		
		jpnl[2].setLayout(new GridLayout(6,1));
		
		for(int i=0; i< lbl.length; i++){
			lbl[i] = new Label();
			jpnl[2].add(lbl[i]);
			lbl[i].setBackground(Color.white);
			lbl[i].setText(lblInhalt[i]);
			lbl[i].addKeyListener(this);
		}
		

		
		jpnl[1].add(jpnl[3]);
		jpnl[3].setLayout(new GridLayout(1,1));
		jpnl[3].add(jsp);
		jta.setBackground(Color.white);
		
		jbtn[0].setText("Fertig");
		jbtn[1].setText("Menu change");
		jbtn[2].setText("True/False");
		jbtn[3].setText("Keys On/Off");
		
		jta.addKeyListener(this);
		
		
		reloadLable();
		setVisible(true);
	
		
		
	}
	
	public static void main(String[] args){
		Tastatur tast = new Tastatur();
		
		
	}
	
	public void reloadLable(){
		for(int i=0; i< lbl.length; i++){
		 lbl[i].setText(lblInhalt[i] + "|" + binMerk[i]);
		}
		
		if(auswahl> 6)auswahl = 0;
		lbl[auswahl%6].setText("#"+lbl[auswahl%6].getText());
		
		
	}
	
	public char rechneBuchstabe(){
		char c;
		
		for(int j=0; j< binMerk.length; j++){
			if(binMerk[j]==true){
			zahl+= Math.pow(2,j);
			System.out.println(zahl);
			}
			
		}
		
		
		
		if (zahl>32){
			c= (char) (64+(zahl-32));
			System.out.println(c);
		}
		
		else{
			c = (char) (96+zahl);
		}

		System.out.println(zahl+"|"+c);
		return c;
	}
	
	public void actionPerformed(ActionEvent e){
		for(int i= 0; i< jbtn.length; i++){
			if(e.getSource()== jbtn[i]){
				
				if(i == 0){
					
					jta.setText(jta.getText()+rechneBuchstabe());
					zahl = 0;
					for(int j=0; j< binMerk.length; j++){
						binMerk[j] = false;
					}
					reloadLable();
					
				}
				if(i==1){
					
					auswahl++;
					reloadLable();
					
				}
				
				if(i==2){
					binMerk[auswahl%6]= !binMerk[auswahl%6];
					reloadLable();
				
				}
				
				if(i== 3){
					keyActive = !keyActive;
					
				}
				
				
			}
			
		}
		
	}
	
	public void keyPressed(KeyEvent e){
		if(keyActive){
		if(e.getKeyCode()==81){
			jta.setText(jta.getText()+rechneBuchstabe());
			zahl = 0;
			for(int j=0; j< binMerk.length; j++){
				binMerk[j] = false;
			}
			
			robo.keyPress(8);
			reloadLable();
		}
		if(e.getKeyCode()==87){
			
			auswahl++;
			reloadLable();
			robo.keyPress(8);
		}
		if(e.getKeyCode()==69){
			binMerk[auswahl%6]= !binMerk[auswahl%6];
			reloadLable();
			robo.keyPress(8);
			
		}
		
		}
		
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e) {}
	
	
	
}

package ua.dp.zaychenko.betmanager.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

import ua.dp.zaychenko.betmanager.api.ApiBetManager;

public class BetWindow {

	public JFrame mainFrame = new JFrame("Bet Manager");
	JTextField koefField;
	JTextField chanceField;
	JTextField betField;
	JComboBox teamsList1;
	JComboBox teamsList2;
	JComboBox resList;
	String teams[] = { "Boston", "Chicago", "Washington" };
	String bets[] = { "1", "1X", "1Ф(0)", "2", "X2", "2Ф(0)", "ТБ 5.5",
			"ТМ 5.5", "ТБ 4.5", "ТМ 4.5", "ТБ 6", "ТМ 6" };
	
	public BetWindow() {
		mainFrame.setSize(650, 450);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new GridLayout(4, 1));
		mainFrame.add(initHeadPanel());
		mainFrame.add(initMatchPanel());
		mainFrame.add(initButtonPanel());
		mainFrame.setVisible(true);

	}

	public JPanel initHeadPanel() {
		JPanel headPanel = new JPanel();

		headPanel.setLayout(new GridLayout(1, 2));
		JPanel bankPanel = new JPanel();
		JLabel bankLabel = new JLabel("БАНК: ");
		bankLabel.setForeground(Color.white);
		JTextField bankField = new JTextField(7);
		bankField.setEnabled(false);
		bankPanel.add(bankLabel);
		bankPanel.add(bankField);
		JPanel userPanel = new JPanel();
		JLabel user = new JLabel("ПОЛЬЗОВАТЕЛЬ:  ЗАЙЧЕНКО ДМИТРИЙ");
		user.setForeground(Color.white);
		userPanel.setBackground(Color.red);
		bankPanel.setBackground(Color.blue);
		userPanel.add(user);
		headPanel.add(userPanel);
		headPanel.add(bankPanel);
		headPanel.setPreferredSize(new Dimension(650, 200));
		return headPanel;
	}

	public JPanel initMatchPanel() {

		JPanel matchPanel = new JPanel();
		matchPanel.setPreferredSize(new Dimension(650, 150));
		matchPanel.setLayout(new FlowLayout(0, 0, 0));

		JPanel team1Panel = new JPanel();
		team1Panel.setPreferredSize(new Dimension(150, 75));
		team1Panel.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		teamsList1 = new JComboBox(teams);
		JLabel team1 = new JLabel("Команда 1");
		team1Panel.setLayout(new GridLayout(2, 1));
		team1Panel.add(team1);
		team1Panel.add(teamsList1);
		matchPanel.add(team1Panel);

		JPanel team2Panel = new JPanel();
		team2Panel.setPreferredSize(new Dimension(150, 75));
		team2Panel.setBorder(BorderFactory.createLineBorder(Color.gray));
		teamsList2 = new JComboBox(teams);
		JLabel team2 = new JLabel("Команда 2");
		team2Panel.setLayout(new GridLayout(2, 1));
		team2Panel.add(team2);
		team2Panel.add(teamsList2);
		matchPanel.add(team2Panel);

		JPanel resPanel = new JPanel();
		resPanel.setPreferredSize(new Dimension(65, 75));
		resPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		resList = new JComboBox(bets);
		JLabel resLabel = new JLabel("Исход");
		resPanel.setLayout(new GridLayout(2, 1));
		resPanel.add(resLabel);
		resPanel.add(resList);
		matchPanel.add(resPanel);

		JPanel koefPanel = new JPanel();
		koefPanel.setPreferredSize(new Dimension(65, 75));
		koefPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		koefField = new JTextField();
		JLabel koefLabel = new JLabel("Коэф.");
		koefPanel.setLayout(new GridLayout(2, 1));
		koefPanel.add(koefLabel);
		koefPanel.add(koefField);
		matchPanel.add(koefPanel);

		JPanel chancePanel = new JPanel();
		chancePanel.setPreferredSize(new Dimension(100, 75));
		chancePanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		chanceField = new JTextField();
		JLabel chanceLabel = new JLabel("Вероятность(%)");
		chancePanel.setLayout(new GridLayout(2, 1));
		chancePanel.add(chanceLabel);
		chancePanel.add(chanceField);
		matchPanel.add(chancePanel);

		JPanel betPanel = new JPanel();
		betPanel.setPreferredSize(new Dimension(100, 75));
		betPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		betField = new JTextField();
		betField.setEnabled(false);
		JLabel betLabel = new JLabel("Сумма ставки");
		betPanel.setLayout(new GridLayout(2, 1));
		betPanel.add(betLabel);
		betPanel.add(betField);
		matchPanel.add(betPanel);

		return matchPanel;
	}
	
	public JPanel initButtonPanel(){
		JPanel buttonPanel = new JPanel();
		JButton matchSearchButton = new JButton("Найти матч");
		JButton calculateButton = new JButton("Рассчитать");
		JButton writeBet = new JButton("Сделать ставку");
		JButton exitButton = new JButton("Выйти");
		
		calculateButton.addActionListener(new ButtonActionListener());
		writeBet.addActionListener(new ButtonActionListener());
		exitButton.addActionListener(new ButtonActionListener());
		buttonPanel.add(matchSearchButton);
		buttonPanel.add(calculateButton);
		buttonPanel.add(writeBet);
		buttonPanel.add(exitButton);
		return buttonPanel;
	} 
	
	public class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			if(ae.getActionCommand().equals("Рассчитать")){
				float bet = ApiBetManager.calculate(Float.parseFloat(koefField.getText()), Float.parseFloat(chanceField.getText()), 1000);
				if(bet <= 0) {JOptionPane.showMessageDialog(new JFrame(), "Ставка не выгодна!",   "Внимание!", JOptionPane.WARNING_MESSAGE);}
				else betField.setText(String.valueOf(bet));		
			}
			
			if(ae.getActionCommand().equals("Сделать ставку")){
				String s = "INSERT INTO matches(firstTeam, secondTeam, forecast, koef, chance, sumBet, date, bank) VALUES(" 
						+"'" + teams[teamsList1.getSelectedIndex()]+ "'" + "," + "'" + teams[teamsList2.getSelectedIndex()]+ "'" + "," + "'" +  bets[resList.getSelectedIndex()]+ "'" + "," 
						+ koefField.getText() + "," + chanceField.getText() + "," + betField.getText() + ", " + "'" + ApiBetManager.getCurrentDate()+ "'," + "1000)"; 
				ApiBetManager.saveMatch(s);
						
			}
			
			
			
			if(ae.getActionCommand().equals("Выйти")){
				System.exit(0);
				
				
			}
		}

	}
}

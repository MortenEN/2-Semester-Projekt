package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow frame = new StartWindow();
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
	public StartWindow() {
		setTitle("Welcome site");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWelcomeLabel = new JLabel("Welcome back!");
		lblWelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblWelcomeLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblRegisterHours = new JLabel("Register Hours");
		lblRegisterHours.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblRegisterHours = new GridBagConstraints();
		gbc_lblRegisterHours.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegisterHours.gridx = 0;
		gbc_lblRegisterHours.gridy = 1;
		panel.add(lblRegisterHours, gbc_lblRegisterHours);
		
		JButton btnEnterRegisterHours = new JButton("Open window");
		btnEnterRegisterHours.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	LoginWindow loginWindow = new LoginWindow();
		    	loginWindow.setVisible(true);
		        }
		});

		btnEnterRegisterHours.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnEnterRegisterHours = new GridBagConstraints();
		gbc_btnEnterRegisterHours.insets = new Insets(0, 0, 5, 0);
		gbc_btnEnterRegisterHours.gridx = 1;
		gbc_btnEnterRegisterHours.gridy = 1;
		panel.add(btnEnterRegisterHours, gbc_btnEnterRegisterHours);
		
		JLabel lblMakeSchedule = new JLabel("Make Schedule");
		lblMakeSchedule.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblMakeSchedule = new GridBagConstraints();
		gbc_lblMakeSchedule.insets = new Insets(0, 0, 5, 5);
		gbc_lblMakeSchedule.gridx = 0;
		gbc_lblMakeSchedule.gridy = 3;
		panel.add(lblMakeSchedule, gbc_lblMakeSchedule);
		
		JButton btnEnterMakeSchedule = new JButton("Open window");
		btnEnterMakeSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScheduleWindow scheduleWindow = new ScheduleWindow();
				scheduleWindow.setVisible(true);
			}
		});
		btnEnterMakeSchedule.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnEnterMakeSchedule = new GridBagConstraints();
		gbc_btnEnterMakeSchedule.insets = new Insets(0, 0, 5, 0);
		gbc_btnEnterMakeSchedule.gridx = 1;
		gbc_btnEnterMakeSchedule.gridy = 3;
		panel.add(btnEnterMakeSchedule, gbc_btnEnterMakeSchedule);
		
		JLabel lblCrudAvailability = new JLabel("CRUD Availability");
		lblCrudAvailability.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblCrudAvailability = new GridBagConstraints();
		gbc_lblCrudAvailability.insets = new Insets(0, 0, 5, 5);
		gbc_lblCrudAvailability.gridx = 0;
		gbc_lblCrudAvailability.gridy = 5;
		panel.add(lblCrudAvailability, gbc_lblCrudAvailability);
		
		JButton btnEnterCrudAvailability = new JButton("Open Window");
		btnEnterCrudAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInternalMessageDialog(null, "Use Case not made yet");
			}
		});
		btnEnterCrudAvailability.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnEnterCrudAvailability = new GridBagConstraints();
		gbc_btnEnterCrudAvailability.insets = new Insets(0, 0, 5, 0);
		gbc_btnEnterCrudAvailability.gridx = 1;
		gbc_btnEnterCrudAvailability.gridy = 5;
		panel.add(btnEnterCrudAvailability, gbc_btnEnterCrudAvailability);
		
		JLabel lblCrudBulletin = new JLabel("CRUD Bulletin");
		lblCrudBulletin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblCrudBulletin = new GridBagConstraints();
		gbc_lblCrudBulletin.insets = new Insets(0, 0, 0, 5);
		gbc_lblCrudBulletin.gridx = 0;
		gbc_lblCrudBulletin.gridy = 7;
		panel.add(lblCrudBulletin, gbc_lblCrudBulletin);
		
		JButton btnEnterCrudBulletin = new JButton("Open window");
		btnEnterCrudBulletin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInternalMessageDialog(null, "Use Case not made yet");
			}
		});
		btnEnterCrudBulletin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnEnterCrudBulletin = new GridBagConstraints();
		gbc_btnEnterCrudBulletin.gridx = 1;
		gbc_btnEnterCrudBulletin.gridy = 7;
		panel.add(btnEnterCrudBulletin, gbc_btnEnterCrudBulletin);
	}

}

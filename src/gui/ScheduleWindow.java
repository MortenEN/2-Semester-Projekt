package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ScheduleWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEndDate;
	private JTextField textFieldStartDate;
	private JTextField textFieldNameSchedule;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleWindow frame = new ScheduleWindow();
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
	public ScheduleWindow() {
		setTitle("Schedule");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabelNameSchedule = new JLabel("Name (Week \"xx\")");
		GridBagConstraints gbc_lblNewLabelNameSchedule = new GridBagConstraints();
		gbc_lblNewLabelNameSchedule.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelNameSchedule.gridx = 0;
		gbc_lblNewLabelNameSchedule.gridy = 0;
		panel.add(lblNewLabelNameSchedule, gbc_lblNewLabelNameSchedule);
		
		JLabel lblNewLabelStartDate = new JLabel("Start Date (dd-mm-yyy)");
		GridBagConstraints gbc_lblNewLabelStartDate = new GridBagConstraints();
		gbc_lblNewLabelStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelStartDate.gridx = 1;
		gbc_lblNewLabelStartDate.gridy = 0;
		panel.add(lblNewLabelStartDate, gbc_lblNewLabelStartDate);
		
		JLabel lblNewLabelEndDate = new JLabel("End Date (dd-mm-yyyy))");
		GridBagConstraints gbc_lblNewLabelEndDate = new GridBagConstraints();
		gbc_lblNewLabelEndDate.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabelEndDate.gridx = 2;
		gbc_lblNewLabelEndDate.gridy = 0;
		panel.add(lblNewLabelEndDate, gbc_lblNewLabelEndDate);
		
		textFieldNameSchedule = new JTextField();
		GridBagConstraints gbc_textFieldNameSchedule = new GridBagConstraints();
		gbc_textFieldNameSchedule.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNameSchedule.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNameSchedule.gridx = 0;
		gbc_textFieldNameSchedule.gridy = 1;
		panel.add(textFieldNameSchedule, gbc_textFieldNameSchedule);
		textFieldNameSchedule.setColumns(10);
		
		textFieldStartDate = new JTextField();
		GridBagConstraints gbc_textFieldStartDate = new GridBagConstraints();
		gbc_textFieldStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldStartDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldStartDate.gridx = 1;
		gbc_textFieldStartDate.gridy = 1;
		panel.add(textFieldStartDate, gbc_textFieldStartDate);
		textFieldStartDate.setColumns(10);
		
		textFieldEndDate = new JTextField();
		GridBagConstraints gbc_textFieldEndDate = new GridBagConstraints();
		gbc_textFieldEndDate.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldEndDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEndDate.gridx = 2;
		gbc_textFieldEndDate.gridy = 1;
		panel.add(textFieldEndDate, gbc_textFieldEndDate);
		textFieldEndDate.setColumns(10);
		
		JButton btnNewButtonCreateSchedule = new JButton("Create Schedule");
		GridBagConstraints gbc_btnNewButtonCreateSchedule = new GridBagConstraints();
		gbc_btnNewButtonCreateSchedule.gridx = 2;
		gbc_btnNewButtonCreateSchedule.gridy = 2;
		panel.add(btnNewButtonCreateSchedule, gbc_btnNewButtonCreateSchedule);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnNewButtonCancel = new JButton("Cancel");
		btnNewButtonCancel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(btnNewButtonCancel);
		
		JButton btnNewButtonSave = new JButton("Save");
		btnNewButtonSave.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(btnNewButtonSave);
		
		init();
	}

	private void init() {
		
	}
	
//	JTextField dateField = new JTextField("15-05-2025");
//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//
//	try {
//	    LocalDate date = LocalDate.parse(dateField.getText(), formatter);
//	    System.out.println("Valgt dato: " + date);
//	} catch (Exception e) {
//	    System.out.println("Forkert datoformat!");
//	} MÅDE MAN KAN BRUGE TEXTFIELDS TIL AT BLIVE TIL LOCALDATE


}

package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ScheduleCtr;
import model.Schedule;
import model.Shift;
import model.Worker;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class ScheduleWindow2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEnd;
	private JTextField textFieldStart;
	private JTextField textFieldFindWorkerName;
	private JTextField textFieldFoundWorker;
	private ScheduleCtr scheduleCtr;
	private DateTimeFormatter formatter;
	private JLabel lblNewLabelShiftThatIsCreated;
	private Worker worker;
	private Shift shift;
	private static Schedule schedule;
	private static ScheduleWindow mainWindow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleWindow2 frame = new ScheduleWindow2(schedule, mainWindow);
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
	public ScheduleWindow2(Schedule schedule, ScheduleWindow mainWindow) {
		ScheduleWindow2.schedule = schedule;
		ScheduleWindow2.mainWindow = mainWindow;
		try {
			scheduleCtr = new ScheduleCtr();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenter = new GridBagLayout();
		gbl_panelCenter.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelCenter.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCenter.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCenter.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCenter.setLayout(gbl_panelCenter);

		JLabel lblNewLabelStartTime = new JLabel("Start Time of Shift");
		GridBagConstraints gbc_lblNewLabelStartTime = new GridBagConstraints();
		gbc_lblNewLabelStartTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelStartTime.gridx = 0;
		gbc_lblNewLabelStartTime.gridy = 0;
		panelCenter.add(lblNewLabelStartTime, gbc_lblNewLabelStartTime);

		JLabel lblNewLabelEndTIme = new JLabel("End Time of Shift");
		GridBagConstraints gbc_lblNewLabelEndTIme = new GridBagConstraints();
		gbc_lblNewLabelEndTIme.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelEndTIme.gridx = 1;
		gbc_lblNewLabelEndTIme.gridy = 0;
		panelCenter.add(lblNewLabelEndTIme, gbc_lblNewLabelEndTIme);

		JLabel lblNewLabelFormat1 = new JLabel("(dd-MM-yyyy HH:mm)");
		GridBagConstraints gbc_lblNewLabelFormat1 = new GridBagConstraints();
		gbc_lblNewLabelFormat1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelFormat1.gridx = 0;
		gbc_lblNewLabelFormat1.gridy = 1;
		panelCenter.add(lblNewLabelFormat1, gbc_lblNewLabelFormat1);

		JLabel lblNewLabelFormat2 = new JLabel("(dd-MM-yyyy HH:mm)");
		GridBagConstraints gbc_lblNewLabelFormat2 = new GridBagConstraints();
		gbc_lblNewLabelFormat2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelFormat2.gridx = 1;
		gbc_lblNewLabelFormat2.gridy = 1;
		panelCenter.add(lblNewLabelFormat2, gbc_lblNewLabelFormat2);

		textFieldStart = new JTextField();
		GridBagConstraints gbc_textFieldStart = new GridBagConstraints();
		gbc_textFieldStart.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldStart.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldStart.gridx = 0;
		gbc_textFieldStart.gridy = 2;
		panelCenter.add(textFieldStart, gbc_textFieldStart);
		textFieldStart.setColumns(10);

		textFieldEnd = new JTextField();
		GridBagConstraints gbc_textFieldEnd = new GridBagConstraints();
		gbc_textFieldEnd.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEnd.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEnd.gridx = 1;
		gbc_textFieldEnd.gridy = 2;
		panelCenter.add(textFieldEnd, gbc_textFieldEnd);
		textFieldEnd.setColumns(10);

		JButton btnNewButtonCreateShift = new JButton("Create Shift");
		btnNewButtonCreateShift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
				try {
					if(!textFieldStart.getText().isEmpty() && !textFieldEnd.getText().isEmpty()) {
						LocalDateTime start = LocalDateTime.parse(textFieldStart.getText(), formatter);
						LocalDateTime end = LocalDateTime.parse(textFieldEnd.getText(), formatter);
						shift = scheduleCtr.createShift(start, end);
						schedule.addShiftToSchedule(shift);
						lblNewLabelShiftThatIsCreated.setText(shift.toString());
					} else {
						JOptionPane.showMessageDialog(null, "All text fields are not filled out, try again.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnNewButtonCreateShift = new GridBagConstraints();
		gbc_btnNewButtonCreateShift.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButtonCreateShift.gridx = 2;
		gbc_btnNewButtonCreateShift.gridy = 2;
		panelCenter.add(btnNewButtonCreateShift, gbc_btnNewButtonCreateShift);

		lblNewLabelShiftThatIsCreated = new JLabel("\"\"");
		GridBagConstraints gbc_lblNewLabelShiftThatIsCreated = new GridBagConstraints();
		gbc_lblNewLabelShiftThatIsCreated.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelShiftThatIsCreated.gridwidth = 2;
		gbc_lblNewLabelShiftThatIsCreated.gridx = 0;
		gbc_lblNewLabelShiftThatIsCreated.gridy = 3;
		panelCenter.add(lblNewLabelShiftThatIsCreated, gbc_lblNewLabelShiftThatIsCreated);

		JLabel lblNewLabel = new JLabel("Find Worker by Name:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 5;
		panelCenter.add(lblNewLabel, gbc_lblNewLabel);

		textFieldFindWorkerName = new JTextField();
		GridBagConstraints gbc_textFieldFindWorkerName = new GridBagConstraints();
		gbc_textFieldFindWorkerName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFindWorkerName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFindWorkerName.gridx = 1;
		gbc_textFieldFindWorkerName.gridy = 5;
		panelCenter.add(textFieldFindWorkerName, gbc_textFieldFindWorkerName);
		textFieldFindWorkerName.setColumns(10);

		JButton btnNewButtonSearchForWorker = new JButton("Search Worker");
		btnNewButtonSearchForWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { 
					if(!textFieldFindWorkerName.getText().isEmpty()) {
						worker = scheduleCtr.findWorkerByName(textFieldFindWorkerName.getText());
						textFieldFindWorkerName.setText(null);
						textFieldFoundWorker.setText(worker.getName());
					} else {
						JOptionPane.showMessageDialog(null, "The text field is not filled out, try again.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnNewButtonSearchForWorker = new GridBagConstraints();
		gbc_btnNewButtonSearchForWorker.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButtonSearchForWorker.gridx = 2;
		gbc_btnNewButtonSearchForWorker.gridy = 5;
		panelCenter.add(btnNewButtonSearchForWorker, gbc_btnNewButtonSearchForWorker);

		JLabel lblNewLabelFoundWorker = new JLabel("Found Worker:");
		GridBagConstraints gbc_lblNewLabelFoundWorker = new GridBagConstraints();
		gbc_lblNewLabelFoundWorker.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabelFoundWorker.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabelFoundWorker.gridx = 0;
		gbc_lblNewLabelFoundWorker.gridy = 6;
		panelCenter.add(lblNewLabelFoundWorker, gbc_lblNewLabelFoundWorker);

		textFieldFoundWorker = new JTextField();
		textFieldFoundWorker.setEditable(false);
		GridBagConstraints gbc_textFieldFoundWorker = new GridBagConstraints();
		gbc_textFieldFoundWorker.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldFoundWorker.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFoundWorker.gridx = 1;
		gbc_textFieldFoundWorker.gridy = 6;
		panelCenter.add(textFieldFoundWorker, gbc_textFieldFoundWorker);
		textFieldFoundWorker.setColumns(10);

		JButton btnNewButtonAddWorkerToShift = new JButton("Add Worker to Shift");
		btnNewButtonAddWorkerToShift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					scheduleCtr.addWorkerToShift(worker, shift);
					JOptionPane.showMessageDialog(null, "The shift is now added to the schedule: " + schedule.getName());
					worker = null;
					shift = null;
					textFieldStart.setText(null);
					textFieldEnd.setText(null);
					lblNewLabelShiftThatIsCreated.setText(null);
					textFieldFoundWorker.setText(null);
					mainWindow.updateSchedule(schedule);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnNewButtonAddWorkerToShift = new GridBagConstraints();
		gbc_btnNewButtonAddWorkerToShift.gridx = 2;
		gbc_btnNewButtonAddWorkerToShift.gridy = 6;
		panelCenter.add(btnNewButtonAddWorkerToShift, gbc_btnNewButtonAddWorkerToShift);

		JPanel panelSouth = new JPanel();
		contentPane.add(panelSouth, BorderLayout.SOUTH);

		JButton btnNewButtonOk = new JButton("Ok");
		btnNewButtonOk.addActionListener(e -> dispose()); //Lamba eksempel
		panelSouth.add(btnNewButtonOk);
	}

}

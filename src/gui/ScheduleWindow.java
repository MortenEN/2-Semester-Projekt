package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ScheduleCtr;
import model.Schedule;
import model.Shift;
import model.Worker;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ScheduleWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEndDate;
	private JTextField textFieldStartDate;
	private JTextField textFieldNameSchedule;
	private DateTimeFormatter formatter;
	private JTable shiftTable;
	private DefaultTableModel tableModel;
	private ScheduleCtr scheduleCtr;
	private Schedule schedule;


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
		try {
			scheduleCtr = new ScheduleCtr();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		setTitle("Schedule");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		GridBagLayout gbl_panelNorth = new GridBagLayout();
		gbl_panelNorth.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelNorth.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelNorth.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelNorth.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelNorth.setLayout(gbl_panelNorth);

		JLabel lblNewLabelNameSchedule = new JLabel("Name (Week \"xx\")");
		GridBagConstraints gbc_lblNewLabelNameSchedule = new GridBagConstraints();
		gbc_lblNewLabelNameSchedule.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelNameSchedule.gridx = 0;
		gbc_lblNewLabelNameSchedule.gridy = 0;
		panelNorth.add(lblNewLabelNameSchedule, gbc_lblNewLabelNameSchedule);

		JLabel lblNewLabelStartDate = new JLabel("Start Date (dd-mm-yyy)");
		GridBagConstraints gbc_lblNewLabelStartDate = new GridBagConstraints();
		gbc_lblNewLabelStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelStartDate.gridx = 1;
		gbc_lblNewLabelStartDate.gridy = 0;
		panelNorth.add(lblNewLabelStartDate, gbc_lblNewLabelStartDate);

		JLabel lblNewLabelEndDate = new JLabel("End Date (dd-mm-yyyy))");
		GridBagConstraints gbc_lblNewLabelEndDate = new GridBagConstraints();
		gbc_lblNewLabelEndDate.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabelEndDate.gridx = 2;
		gbc_lblNewLabelEndDate.gridy = 0;
		panelNorth.add(lblNewLabelEndDate, gbc_lblNewLabelEndDate);

		textFieldNameSchedule = new JTextField();
		GridBagConstraints gbc_textFieldNameSchedule = new GridBagConstraints();
		gbc_textFieldNameSchedule.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNameSchedule.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNameSchedule.gridx = 0;
		gbc_textFieldNameSchedule.gridy = 1;
		panelNorth.add(textFieldNameSchedule, gbc_textFieldNameSchedule);
		textFieldNameSchedule.setColumns(10);

		textFieldStartDate = new JTextField();
		GridBagConstraints gbc_textFieldStartDate = new GridBagConstraints();
		gbc_textFieldStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldStartDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldStartDate.gridx = 1;
		gbc_textFieldStartDate.gridy = 1;
		panelNorth.add(textFieldStartDate, gbc_textFieldStartDate);
		textFieldStartDate.setColumns(10);

		textFieldEndDate = new JTextField();
		GridBagConstraints gbc_textFieldEndDate = new GridBagConstraints();
		gbc_textFieldEndDate.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldEndDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEndDate.gridx = 2;
		gbc_textFieldEndDate.gridy = 1;
		panelNorth.add(textFieldEndDate, gbc_textFieldEndDate);
		textFieldEndDate.setColumns(10);

		JButton btnNewButtonCreateSchedule = new JButton("Create Schedule");
		btnNewButtonCreateSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!textFieldStartDate.getText().isEmpty() && 
						    !textFieldEndDate.getText().isEmpty() && 
						    !textFieldNameSchedule.getText().isEmpty()) {
					formatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy");
					LocalDate start = LocalDate.parse(textFieldStartDate.getText(), formatter);
					LocalDate end = LocalDate.parse(textFieldEndDate.getText(), formatter);

					Schedule schedule = scheduleCtr.SelectDate(textFieldNameSchedule.getText(), start, end);

					ScheduleWindow2 scheduleWindow2 = new ScheduleWindow2(schedule, ScheduleWindow.this);
					scheduleWindow2.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "All text fields are not filled out, try again.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnNewButtonCreateSchedule = new GridBagConstraints();
		gbc_btnNewButtonCreateSchedule.gridx = 2;
		gbc_btnNewButtonCreateSchedule.gridy = 2;
		panelNorth.add(btnNewButtonCreateSchedule, gbc_btnNewButtonCreateSchedule);

		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);

		String[] columnNames = {"Start of Shift", "End of Shift", "Worker"};

		tableModel = new DefaultTableModel(columnNames, 0);
		shiftTable = new JTable(tableModel);
		shiftTable.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(shiftTable);
		contentPane.add(scrollPane, BorderLayout.CENTER);


		JPanel panelSouth = new JPanel();
		contentPane.add(panelSouth, BorderLayout.SOUTH);

		JButton btnNewButtonCancel = new JButton("Cancel");
		btnNewButtonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButtonCancel.setHorizontalAlignment(SwingConstants.RIGHT);
		panelSouth.add(btnNewButtonCancel);

		JButton btnNewButtonSave = new JButton("Save");
		btnNewButtonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (schedule != null) {
						scheduleCtr.saveSchedule(schedule);
						resetWindow();
						JOptionPane.showMessageDialog(null, "Schedule saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "No schedule created yet to save", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButtonSave.setHorizontalAlignment(SwingConstants.RIGHT);
		panelSouth.add(btnNewButtonSave);
	}

	public void updateSchedule(Schedule schedule) {
		this.schedule = schedule;
		List<Shift> shifts = schedule.getListOfShifts();

		tableModel.setRowCount(0);

		for (Shift shift : shifts) {
			Object[] rowData = { shift.getStart(), shift.getEnd(), shift.getWorker().getName() };
			tableModel.addRow(rowData);
		}
	}
	
	private void resetWindow() {
	    textFieldNameSchedule.setText("");
	    textFieldStartDate.setText("");
	    textFieldEndDate.setText("");

	    tableModel.setRowCount(0);

	    schedule = null;
	}

}

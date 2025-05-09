package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ShiftCtr;
import controller.WorkerCtr;
import model.Shift;
import model.Worker;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.GridBagLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class loginWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static loginWindow frame;
	private JPanel contentPane;
	private WorkerCtr workerCtr;
	private ShiftCtr shiftCtr;
	private JList<String> listOfWorkers;
	private static String login;
	private JLabel lblDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		login = "";
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new loginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		new Thread(() -> {
			while(true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// should not happen - we don't interrupt this thread
					e.printStackTrace();
				}
				frame.updateWorkerList();
			}
		}).start();
	}

	/**
	 * Create the frame.
	 */
	public loginWindow() {
		setTitle("SignIn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		lblDate = new JLabel("Date-");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDate, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{142, 142, 142, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JButton btnNewButton_1num = new JButton("1");
		btnNewButton_1num.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				login = login + s;
			}
		});
		btnNewButton_1num.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnNewButton_1num = new GridBagConstraints();
		gbc_btnNewButton_1num.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1num.gridx = 0;
		gbc_btnNewButton_1num.gridy = 0;
		panel.add(btnNewButton_1num, gbc_btnNewButton_1num);

		JButton btnNewButton_2num = new JButton("2");
		btnNewButton_2num.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				login = login + s;
			}
		});
		btnNewButton_2num.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnNewButton_2num = new GridBagConstraints();
		gbc_btnNewButton_2num.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2num.gridx = 1;
		gbc_btnNewButton_2num.gridy = 0;
		panel.add(btnNewButton_2num, gbc_btnNewButton_2num);

		JButton btnNewButton_3num = new JButton("3");
		btnNewButton_3num.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				login = login + s;
			}
		});
		btnNewButton_3num.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnNewButton_3num = new GridBagConstraints();
		gbc_btnNewButton_3num.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3num.gridx = 2;
		gbc_btnNewButton_3num.gridy = 0;
		panel.add(btnNewButton_3num, gbc_btnNewButton_3num);

		JButton btnNewButton_4num = new JButton("4");
		btnNewButton_4num.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				login = login + s;
			}
		});
		btnNewButton_4num.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnNewButton_4num = new GridBagConstraints();
		gbc_btnNewButton_4num.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4num.gridx = 0;
		gbc_btnNewButton_4num.gridy = 1;
		panel.add(btnNewButton_4num, gbc_btnNewButton_4num);

		JButton btnNewButton_5num = new JButton("5");
		btnNewButton_5num.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				login = login + s;
			}
		});
		btnNewButton_5num.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnNewButton_5num = new GridBagConstraints();
		gbc_btnNewButton_5num.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5num.gridx = 1;
		gbc_btnNewButton_5num.gridy = 1;
		panel.add(btnNewButton_5num, gbc_btnNewButton_5num);

		JButton btnNewButton_6num = new JButton("6");
		btnNewButton_6num.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				login = login + s;
			}
		});
		btnNewButton_6num.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnNewButton_6num = new GridBagConstraints();
		gbc_btnNewButton_6num.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_6num.gridx = 2;
		gbc_btnNewButton_6num.gridy = 1;
		panel.add(btnNewButton_6num, gbc_btnNewButton_6num);

		JButton btnNewButton_7num = new JButton("7");
		btnNewButton_7num.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				login = login + s;
			}
		});
		btnNewButton_7num.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnNewButton_7num = new GridBagConstraints();
		gbc_btnNewButton_7num.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_7num.gridx = 0;
		gbc_btnNewButton_7num.gridy = 2;
		panel.add(btnNewButton_7num, gbc_btnNewButton_7num);

		JButton btnNewButton_8num = new JButton("8");
		btnNewButton_8num.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				login = login + s;
			}
		});
		btnNewButton_8num.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnNewButton_8num = new GridBagConstraints();
		gbc_btnNewButton_8num.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_8num.gridx = 1;
		gbc_btnNewButton_8num.gridy = 2;
		panel.add(btnNewButton_8num, gbc_btnNewButton_8num);

		JButton btnNewButton_9num = new JButton("9");
		btnNewButton_9num.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				login = login + s;
			}
		});
		btnNewButton_9num.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnNewButton_9num = new GridBagConstraints();
		gbc_btnNewButton_9num.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_9num.gridx = 2;
		gbc_btnNewButton_9num.gridy = 2;
		panel.add(btnNewButton_9num, gbc_btnNewButton_9num);

		JButton btnNewButton_0num = new JButton("0");
		btnNewButton_0num.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				login = login + s;
			}
		});
		btnNewButton_0num.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnNewButton_0num = new GridBagConstraints();
		gbc_btnNewButton_0num.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_0num.gridx = 1;
		gbc_btnNewButton_0num.gridy = 3;
		panel.add(btnNewButton_0num, gbc_btnNewButton_0num);

		JButton btnNewButton_enter = new JButton("Enter");
		btnNewButton_enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					enterLogin(login);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Not found, try again");
					e1.printStackTrace();
				}
				login = "";
			}

		});
		btnNewButton_enter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnNewButton_enter = new GridBagConstraints();
		gbc_btnNewButton_enter.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_enter.gridx = 2;
		gbc_btnNewButton_enter.gridy = 3;
		panel.add(btnNewButton_enter, gbc_btnNewButton_enter);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);

		listOfWorkers = new JList<>();
		panel_1.add(listOfWorkers);

		init();
	}

	private void init() {
		try {
			workerCtr = new WorkerCtr();
			shiftCtr = new ShiftCtr();
			updateWorkerList();
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	private void enterLogin(String login) throws SQLException {
		Worker worker = workerCtr.findWorker(login);
		if(worker.isAtWork()) {
			shiftCtr.addShiftToDB(LocalDateTime.now(), login);
		} else {
			String activeShiftStart = workerCtr.findActiveShift(login);
			shiftCtr.updateShiftInDB(activeShiftStart, LocalDateTime.now(), login);
		}
	}

	public void updateWorkerList() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-uuuu HH:mm:ss");
        String formattedDateTime = now.format(formatter);
		lblDate.setText("Date: " + formattedDateTime);
	
		SwingUtilities.invokeLater(() -> {
			List<Worker> workers;
			try {
				workers = workerCtr.findAll();
				DefaultListModel<String> list = new DefaultListModel<>();
				for (Worker w : workers) {
					list.addElement(w.getName() + "(Checked in)");
				}
				this.listOfWorkers.setModel(list);
			} catch (Exception e) {
				//e.printStackTrace();
			}

		});
	}

}

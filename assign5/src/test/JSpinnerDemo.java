package test;

import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.util.Date;
import java.util.Calendar;
import java.text.DecimalFormat;

class JSpinnerDemo implements ChangeListener {
	JFrame mainFrame;
	JPanel mainPanel;
	JSpinner listSpinner;
	JSpinner numberSpinner;
	JSpinner dateSpinner;

	public JSpinnerDemo() {
		mainFrame = new JFrame("JSpinnerDemo");
		mainPanel = new JPanel(new GridLayout(3, 1));

		String[] listData = { "SpinnerListModel", "SpinnerNumberModel", "SpinnerDateModel" };
		// 使用自定义的Model来实现cycle
		SpinnerModel listModel = new CustomSpinnerListModel(listData);
		listSpinner = new JSpinner(listModel);
		listSpinner.addChangeListener(this);
		mainPanel.add(listSpinner);

		SpinnerModel numberModel = new SpinnerNumberModel(1.0, 0.0, 2.0, 0.1);
		numberSpinner = new JSpinner(numberModel);
		numberSpinner.addChangeListener(this);
		// 通过取得JFormattedTextField来自定义Editor
		JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) numberSpinner.getEditor();
		JFormattedTextField ftf = editor.getTextField();
		ftf.setForeground(Color.red);
		mainPanel.add(numberSpinner);

		Calendar calendar = Calendar.getInstance();
		Date initDate = calendar.getTime();
		calendar.add(Calendar.YEAR, -100);
		Date earliestDate = calendar.getTime();
		calendar.add(Calendar.YEAR, 200);
		Date latestDate = calendar.getTime();
		SpinnerModel dateModel = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.YEAR);
		dateSpinner = new JSpinner(dateModel);
		dateSpinner.addChangeListener(this);
		// 通过setEditor来自定义Editor
		dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
		mainPanel.add(dateSpinner);

		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainFrame.getContentPane().add(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	public void stateChanged(ChangeEvent e) {
		JSpinner spinner = (JSpinner) e.getSource();
		System.out.println(spinner.getValue());
	}

	// 自定义的Model,实现Cycle.
	class CustomSpinnerListModel extends SpinnerListModel {
		Object[] values;

		CustomSpinnerListModel(Object[] values) {
			super(values);
			this.values = values;
		}

		public Object getPreviousValue() {
			Object value = super.getPreviousValue();
			return value != null ? value : values[values.length - 1];
		}

		public Object getNextValue() {
			Object value = super.getNextValue();
			return value != null ? value : values[0];
		}
	}

	public static void main(String[] args) {
		new JSpinnerDemo();
	}
}
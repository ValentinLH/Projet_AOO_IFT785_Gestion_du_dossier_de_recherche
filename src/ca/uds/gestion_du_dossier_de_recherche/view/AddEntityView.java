package ca.uds.gestion_du_dossier_de_recherche.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AddEntityView<T> extends JFrame {

	protected List<JLabel> labels = new ArrayList<>();
	private List<JTextField> textFields = new ArrayList<>();
	private List<JComboBox<Object>> comboBoxes = new ArrayList<>();
	private List<JCheckBox> checkBoxes = new ArrayList<>();
	private JButton okButton = new JButton("OK");
	private List<JPanel> listPanels = new ArrayList<>();
	private List<JComponent> inputFields = new ArrayList<>();

	
	public AddEntityView(Class<T> entityClass) {
		this.setTitle("Add " + entityClass.getSimpleName());
		this.setSize(600, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		JLabel titleLabel = new JLabel("Add " + entityClass.getSimpleName());
		titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	
		labels.add(titleLabel);


		int yOffset = 50;
		for (Field field : entityClass.getDeclaredFields()) {
			JLabel label = new JLabel(field.getName() + " : ");
			labels.add(label);
			label.setBounds(200, yOffset, 200, 25);
			this.add(label);

			if (field.getType().equals(String.class)) {
				JTextField textField = new JTextField();
				textFields.add(textField);
				textField.setBounds(200, yOffset + 30, 200, 25);
				this.add(textField);
			} else if (field.getType().isEnum()) {
				JComboBox<Object> comboBox = new JComboBox<>(field.getType().getEnumConstants());
				comboBoxes.add(comboBox);
				comboBox.setBounds(200, yOffset + 30, 200, 25);
				this.add(comboBox);
			} else if (field.getType().equals(LocalDate.class)) {
				JTextField dateField = new JTextField();
				textFields.add(dateField);
				dateField.setBounds(200, yOffset + 30, 200, 25);
				this.add(dateField);
				JButton datePickerButton = new JButton("Pick Date");
				datePickerButton.addActionListener(new DatePickerActionListener(dateField));
				datePickerButton.setBounds(410, yOffset + 30, 100, 25);
				this.add(datePickerButton);
			} else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
				JCheckBox checkBox = new JCheckBox();
				checkBoxes.add(checkBox);
				checkBox.setBounds(200, yOffset + 30, 100, 25);
				this.add(checkBox);
			} else if (isNumeric(field.getType())) {
				JTextField numericField = new JTextField();
				textFields.add(numericField);
				numericField.setBounds(200, yOffset + 30, 200, 25);
				this.add(numericField);
			} else if (isCollectionType(field.getType())) {
			
	                JPanel collectionPanel = new JPanel();
	                listPanels.add(collectionPanel);
	                JButton addButton = new JButton("Add " + field.getName());
	                addButton.addActionListener(new AddCollectionItemActionListener(collectionPanel, field.getType()));
	                collectionPanel.add(addButton);
	                //inputPanel.add(collectionPanel);
	           
			} else {
				// Appeler récursivement AddEntityView pour créer un panneau pour le type
				// d'objet inconnu

				JPanel panel = new JPanel();
				inputFields.add(panel);
				panel.setBounds(200, yOffset + 30, 200, 25);
				AddEntityViewPanel<?> subEntityView = new AddEntityViewPanel<>(field.getType());
				subEntityView.setBounds(0, 0, 200, 200);
				panel.add(subEntityView);
				this.add(panel);
			}

			yOffset += 70;
		}

		okButton.setBounds(250, yOffset, 100, 30);
		this.add(okButton);

		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
	}

	public Object getValueForField(Field field) {
		if (field.getType().equals(String.class)) {
			for (int i = 0; i < labels.size(); i++) {
				if (labels.get(i).getText().equals(field.getName() + " : ")) {
					return textFields.get(i).getText();
				}
			}
		} else if (field.getType().isEnum()) {
			for (int i = 0; i < labels.size(); i++) {
				if (labels.get(i).getText().equals(field.getName() + " : ")) {
					return comboBoxes.get(i).getSelectedItem();
				}
			}
		} else if (field.getType().equals(LocalDate.class)) {
			for (int i = 0; i < labels.size(); i++) {
				if (labels.get(i).getText().equals(field.getName() + " : ")) {
					return LocalDate.parse(textFields.get(i).getText());
				}
			}
		} else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
			for (int i = 0; i < labels.size(); i++) {
				if (labels.get(i).getText().equals(field.getName() + " : ")) {
					return checkBoxes.get(i).isSelected();
				}
			}
		} else if (List.class.isAssignableFrom(field.getType())) {
			for (int i = 0; i < labels.size(); i++) {
				if (labels.get(i).getText().equals(field.getName() + " : ")) {
					List<Object> items = new ArrayList<>();
					for (Component component : listPanels.get(i).getComponents()) {
						if (component instanceof JTextField) {
							items.add(((JTextField) component).getText());
						}
					}
					return items;
				}
			}
		} else if (isNumeric(field.getType())) {
			for (int i = 0; i < labels.size(); i++) {
				if (labels.get(i).getText().equals(field.getName() + " : ")) {
					String value = textFields.get(i).getText();
					if (value.isEmpty()) {
						return null;
					} else {
						return Double.parseDouble(value);
					}
				}
			}
		} else {
			// Le champ est un type d'objet inconnu, récupérer la valeur récursivement
			for (Object inputField : inputFields) {
				if (inputField instanceof JPanel) {
					AddEntityView<?> subEntityView = (AddEntityView<?>) ((JPanel) inputField).getComponent(0);
					return subEntityView.getValueForField(field);
				}
			}

		}

		return null;
	}

	public JButton getOkButton() {
		return okButton;
	}

	class DatePickerActionListener implements ActionListener {
		private JTextField dateField;

		public DatePickerActionListener(JTextField dateField) {
			this.dateField = dateField;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			DatePicker datePicker = new DatePicker((JFrame) SwingUtilities.getWindowAncestor(AddEntityView.this));
			LocalDate selectedDate = datePicker.showDatePicker();
			if (selectedDate != null) {
				dateField.setText(selectedDate.toString());
			}
		}

	}
	
    private boolean isCollectionType(Class<?> type) {
        return List.class.isAssignableFrom(type) ||
               Map.class.isAssignableFrom(type) ||
               Set.class.isAssignableFrom(type);
    }

	private boolean isNumeric(Class<?> type) {
		return type.equals(Integer.class) || type.equals(int.class) || type.equals(Double.class)
				|| type.equals(double.class) || type.equals(Float.class) || type.equals(float.class)
				|| type.equals(Long.class) || type.equals(long.class) || type.equals(Short.class)
				|| type.equals(short.class) || type.equals(Byte.class) || type.equals(byte.class);
	}
	
	 class AddCollectionItemActionListener implements ActionListener {
	        private JPanel collectionPanel;
	        private Class<?> collectionType;

	        public AddCollectionItemActionListener(JPanel collectionPanel, Class<?> collectionType) {
	            this.collectionPanel = collectionPanel;
	            this.collectionType = collectionType;
	        }

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            AddEntityViewPanel<?> panel = new AddEntityViewPanel<>(collectionType);
	            //entityPanels.add(panel);
	            collectionPanel.add(panel);
	            collectionPanel.revalidate();
	            collectionPanel.repaint();
	        }
	    }
}

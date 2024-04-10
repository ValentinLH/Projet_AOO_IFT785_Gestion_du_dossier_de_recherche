package ca.uds.gestion_du_dossier_de_recherche.view;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddEntityViewTwo<T> extends JFrame {

    private List<JLabel> labels = new ArrayList<>();
    private List<JComponent> inputFields = new ArrayList<>();
    private JButton okButton = new JButton("OK");

    public AddEntityViewTwo(Class<T> entityClass) {
        this.setTitle("Add " + entityClass.getSimpleName());
        this.setSize(600, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        int yOffset = 50;
        for (Field field : entityClass.getDeclaredFields()) {
            JLabel label = new JLabel(field.getName() + " : ");
            labels.add(label);
            label.setBounds(200, yOffset, 200, 25);
            this.add(label);

            if (field.getType().equals(String.class) || field.getType().isEnum()) {
                JTextField textField = new JTextField();
                inputFields.add(textField);
                textField.setBounds(200, yOffset + 30, 200, 25);
                this.add(textField);
            } else if (field.getType().equals(LocalDate.class)) {
                JTextField dateField = new JTextField();
                inputFields.add(dateField);
                dateField.setBounds(200, yOffset + 30, 200, 25);
                this.add(dateField);
                JButton datePickerButton = new JButton("Pick Date");
                datePickerButton.addActionListener(new DatePickerActionListener(dateField));
                datePickerButton.setBounds(410, yOffset + 30, 100, 25);
                this.add(datePickerButton);
            } else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
                JCheckBox checkBox = new JCheckBox();
                inputFields.add(checkBox);
                checkBox.setBounds(200, yOffset + 30, 200, 25);
                this.add(checkBox);
            } else if (Number.class.isAssignableFrom(field.getType())) {
                JTextField numericField = new JTextField();
                inputFields.add(numericField);
                numericField.setBounds(200, yOffset + 30, 200, 25);
                this.add(numericField);
            } else {
                JPanel panel = new JPanel();
                inputFields.add(panel);
                panel.setBounds(200, yOffset + 30, 200, 25);
                // Appeler récursivement AddEntityView pour créer un panneau pour le type d'objet inconnu
                AddEntityView<?> subEntityView = new AddEntityView<>(field.getType());
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
        for (int i = 0; i < labels.size(); i++) {
            if (labels.get(i).getText().equals(field.getName() + " : ")) {
                JComponent inputField = inputFields.get(i);
                if (field.getType().equals(String.class) || field.getType().isEnum()) {
                    return ((JTextField) inputField).getText();
                } else if (field.getType().equals(LocalDate.class)) {
                    return LocalDate.parse(((JTextField) inputField).getText());
                } else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
                    return ((JCheckBox) inputField).isSelected();
                } else if (Number.class.isAssignableFrom(field.getType())) {
                    String text = ((JTextField) inputField).getText();
                    if (text.isEmpty()) return null;
                    if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
                        return Integer.parseInt(text);
                    } else if (field.getType().equals(Double.class) || field.getType().equals(double.class)) {
                        return Double.parseDouble(text);
                    } else if (field.getType().equals(Float.class) || field.getType().equals(float.class)) {
                        return Float.parseFloat(text);
                    } else if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
                        return Long.parseLong(text);
                    } else if (field.getType().equals(Short.class) || field.getType().equals(short.class)) {
                        return Short.parseShort(text);
                    } else if (field.getType().equals(Byte.class) || field.getType().equals(byte.class)) {
                        return Byte.parseByte(text);
                    }
                } else {
                    // Le champ est un type d'objet inconnu, récupérer la valeur récursivement
                    if (inputField instanceof JPanel) {
                        AddEntityView<?> subEntityView = (AddEntityView<?>) ((JPanel) inputField).getComponent(0);
                        return subEntityView.getValueForField(field);
                    }
                }
            }
        }
        return null;
    }
    
    class DatePickerActionListener implements ActionListener {
        private JTextField dateField;

        public DatePickerActionListener(JTextField dateField) {
            this.dateField = dateField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            DatePicker datePicker = new DatePicker((JFrame) SwingUtilities.getWindowAncestor(AddEntityViewTwo.this));
            LocalDate selectedDate = datePicker.showDatePicker();
            if (selectedDate != null) {
                dateField.setText(selectedDate.toString());
            }
        }
        
    }
    
}

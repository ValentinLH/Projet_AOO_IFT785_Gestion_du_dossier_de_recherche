package ca.uds.gestion_du_dossier_de_recherche.view;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AddEntityView<T> extends JFrame {

    private List<JLabel> labels = new ArrayList<>();
    private List<JTextField> textFields = new ArrayList<>();
    private List<JComboBox<Object>> comboBoxes = new ArrayList<>();
    private JButton okButton = new JButton("OK");

    public AddEntityView(Class<T> entityClass) {
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
        }
        return null;
    }

    public JButton getOkButton() {
        return okButton;
    }
}

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

public class AddEntityViewPanel<T> extends JPanel {

    private List<JLabel> labels = new ArrayList<>();
    private List<JTextField> textFields = new ArrayList<>();
    private List<JComboBox<Object>> comboBoxes = new ArrayList<>();

    public AddEntityViewPanel(Class<T> entityClass) {
        setLayout(new GridLayout(0, 3)); // Trois colonnes par ligne

//        JLabel titleLabel = new JLabel("Add " + entityClass.getSimpleName());
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
//        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        add(new JPanel());
//        add(titleLabel, BorderLayout.NORTH);
//        add(new JPanel());
        
        for (Field field : entityClass.getDeclaredFields()) {
            JLabel label = new JLabel(field.getName() + " : ");
            labels.add(label);
            add(label);

            if (field.getType().equals(String.class)) {
                JTextField textField = new JTextField();
                textFields.add(textField);
                add(textField);
                add(new JPanel()); // Utilisé pour occuper la troisième colonne
            } else if (field.getType().isEnum()) {
                JComboBox<Object> comboBox = new JComboBox<>(field.getType().getEnumConstants());
                comboBoxes.add(comboBox);
                add(comboBox);
                add(new JPanel()); // Utilisé pour occuper la troisième colonne
            } else if (field.getType().equals(LocalDate.class)) {
                JTextField dateField = new JTextField();
                textFields.add(dateField);
                add(dateField);
                JButton datePickerButton = new JButton("Pick Date");
                datePickerButton.addActionListener(new DatePickerActionListener(dateField));
                add(datePickerButton);
            } else if (!isCollectionType(field.getType())) {
                // Si le type de champ est inconnu, créez récursivement un autre AddEntityViewPanel pour ce champ
                AddEntityViewPanel<?> panel = new AddEntityViewPanel<>(field.getType());
                add(panel);
                add(new JPanel()); // Utilisé pour occuper la troisième colonne
            }
            else 
            {
            	add(new JPanel());
            	add(new JPanel());
            }
        }
    }

    public Object[] getValuesForFields() {
        List<Object> values = new ArrayList<>();
        for (int i = 0; i < labels.size(); i++) {
            Field field = labels.get(i).getClass().getDeclaredFields()[i];
            if (field.getType().equals(String.class)) {
                values.add(textFields.get(i).getText());
            } else if (field.getType().isEnum()) {
                values.add(comboBoxes.get(i).getSelectedItem());
            } else if (field.getType().equals(LocalDate.class)) {
                values.add(LocalDate.parse(textFields.get(i).getText()));
            } else {
                // Si le type de champ est inconnu, obtenez les valeurs récursivement à partir du panneau
                values.add(((AddEntityViewPanel) getComponent(i + 1)).getValuesForFields());
            }
        }
        return values.toArray();
    }

    private boolean isCollectionType(Class<?> type) {
        return List.class.isAssignableFrom(type) ||
               Map.class.isAssignableFrom(type) ||
               Set.class.isAssignableFrom(type);
    }

    class DatePickerActionListener implements ActionListener {
        private JTextField dateField;

        public DatePickerActionListener(JTextField dateField) {
            this.dateField = dateField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            DatePicker datePicker = new DatePicker((JFrame) SwingUtilities.getWindowAncestor(AddEntityViewPanel.this));
            LocalDate selectedDate = datePicker.showDatePicker();
            if (selectedDate != null) {
                dateField.setText(selectedDate.toString());
            }
        }
    }
}

//
//
//import javax.swing.*;
//
//import ca.uds.gestion_du_dossier_de_recherche.view.AddEntityViewTwo.DatePickerActionListener;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.lang.reflect.Field;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//public class AddEntityViewPanel<T> extends JPanel {
//
//    private List<JLabel> labels = new ArrayList<>();
//    private List<JTextField> textFields = new ArrayList<>();
//    private List<JComboBox<Object>> comboBoxes = new ArrayList<>();
//
//    public AddEntityViewPanel(Class<T> entityClass) {
//        setLayout(new GridLayout(0, 3));
//        
//        for (Field field : entityClass.getDeclaredFields()) {
//            JLabel label = new JLabel(field.getName() + " : ");
//            labels.add(label);
//            add(label);
//
//            if (field.getType().equals(String.class)) {
//                JTextField textField = new JTextField();
//                textFields.add(textField);
//                add(textField);
//            } else if (field.getType().isEnum()) {
//                JComboBox<Object> comboBox = new JComboBox<>(field.getType().getEnumConstants());
//                comboBoxes.add(comboBox);
//                add(comboBox);
//            } else if (field.getType().equals(LocalDate.class)) {
//                JTextField dateField = new JTextField();
//                textFields.add(dateField);
//                add(dateField);
//                JButton datePickerButton = new JButton("Pick Date");
//                datePickerButton.addActionListener(new DatePickerActionListener(dateField));
//                add(datePickerButton);
//            } else if (!isCollectionType(field.getType())) {
//                // Si le type de champ est inconnu, créez récursivement un autre AddEntityViewPanel pour ce champ
//            	 AddEntityViewPanel<?> panel = new AddEntityViewPanel<>(field.getType());
//                add(panel);
//            }
//        }
//           
//    }
//
//    public Object[] getValuesForFields() {
//        List<Object> values = new ArrayList<>();
//        for (int i = 0; i < labels.size(); i++) {
//            Field field = labels.get(i).getClass().getDeclaredFields()[i];
//            if (field.getType().equals(String.class)) {
//                values.add(textFields.get(i).getText());
//            } else if (field.getType().isEnum()) {
//                values.add(comboBoxes.get(i).getSelectedItem());
//            } else if (field.getType().equals(LocalDate.class)) {
//                values.add(LocalDate.parse(textFields.get(i).getText()));
//            } else {
//                // Si le type de champ est inconnu, obtenez les valeurs récursivement à partir du panneau
//                values.add(((AddEntityViewPanel) getComponent(i + 1)).getValuesForFields());
//            }
//        }
//        return values.toArray();
//    }
//    
//    private boolean isCollectionType(Class<?> type) {
//        return List.class.isAssignableFrom(type) ||
//               Map.class.isAssignableFrom(type) ||
//               Set.class.isAssignableFrom(type);
//    }
//    
//    class DatePickerActionListener implements ActionListener {
//        private JTextField dateField;
//
//        public DatePickerActionListener(JTextField dateField) {
//            this.dateField = dateField;
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            DatePicker datePicker = new DatePicker((JFrame) SwingUtilities.getWindowAncestor(AddEntityViewPanel.this));
//            LocalDate selectedDate = datePicker.showDatePicker();
//            if (selectedDate != null) {
//                dateField.setText(selectedDate.toString());
//            }
//        }
//        
//    }
//    
//}


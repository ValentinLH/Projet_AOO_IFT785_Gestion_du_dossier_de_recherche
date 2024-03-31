package ca.uds.gestion_du_dossier_de_recherche.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePickerExample extends JFrame {
    private JTextField dateTextField;

    public DatePickerExample() {
        setTitle("Date Picker Example");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout());

        JLabel label = new JLabel("Select Date:");
        panel.add(label);

        dateTextField = new JTextField(10);
        panel.add(dateTextField);

        JButton datePickerButton = new JButton("...");
        datePickerButton.addActionListener(new DatePickerActionListener());
        panel.add(datePickerButton);

        add(panel);
    }

    class DatePickerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DatePicker datePicker = new DatePicker(DatePickerExample.this);
            LocalDate selectedDate = datePicker.showDatePicker();
            if (selectedDate != null) {
                dateTextField.setText(selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DatePickerExample().setVisible(true);
        });
    }
}

class DatePicker extends JDialog {
    private JSpinner datePickerSpinner;

    public DatePicker(Frame parent) {
        super(parent, "Date Picker", true);
        setSize(200, 100);
        setLocationRelativeTo(parent);

        datePickerSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(datePickerSpinner, "yyyy-MM-dd");
        datePickerSpinner.setEditor(dateEditor);
        datePickerSpinner.setValue(new java.util.Date());
        add(datePickerSpinner, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            dispose();
        });
        add(okButton, BorderLayout.SOUTH);
    }

    public LocalDate showDatePicker() {
        setVisible(true);
        return ((java.util.Date) datePickerSpinner.getValue()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    }
}

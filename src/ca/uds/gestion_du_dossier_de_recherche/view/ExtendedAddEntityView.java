package ca.uds.gestion_du_dossier_de_recherche.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExtendedAddEntityView<T> extends AddEntityView<T> {

    private List<JButton> addButtonList = new ArrayList<>();
    private List<JButton> removeButtonList = new ArrayList<>();
    private List<JTable> objectTables = new ArrayList<>();

    public ExtendedAddEntityView(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public Object getValueForField(Field field) {
        if (field.getType().equals(LocalDate.class)) {
            return null; // Implement the logic for LocalDate fields
        } else if (field.getType().equals(List.class)) {
            for (int i = 0; i < labels.size(); i++) {
                if (labels.get(i).getText().equals(field.getName() + " : ")) {
                    return getObjectList(i);
                }
            }
        }
        return super.getValueForField(field);
    }

    private List<Object> getObjectList(int index) {
        List<Object> objectList = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) objectTables.get(index).getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            objectList.add(model.getValueAt(i, 0));
        }
        return objectList;
    }

    protected void addFieldComponents(Field field, int yOffset) {
        if (field.getType().equals(String.class)) {
            // Add text field
            super.addFieldComponents(field, yOffset);
        } else if (field.getType().isEnum()) {
            // Add combo box
            super.addFieldComponents(field, yOffset);
        } else if (field.getType().equals(LocalDate.class)) {
            // Add date picker
            // Implement the logic to add date picker
        } else if (field.getType().equals(List.class)) {
            // Add list field
            JLabel label = new JLabel(field.getName() + " : ");
            labels.add(label);
            label.setBounds(200, yOffset, 200, 25);
            this.add(label);

            DefaultTableModel model = new DefaultTableModel();
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            objectTables.add(table);

            JButton addButton = new JButton("Add");
            addButtonList.add(addButton);
            addButton.setBounds(200, yOffset + 30, 80, 25);
            addButton.addActionListener(e -> model.addRow(new Object[]{""}));
            this.add(addButton);

            JButton removeButton = new JButton("Remove");
            removeButtonList.add(removeButton);
            removeButton.setBounds(300, yOffset + 30, 100, 25);
            removeButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    model.removeRow(selectedRow);
                }
            });
            this.add(removeButton);

            scrollPane.setBounds(200, yOffset + 60, 200, 100);
            this.add(scrollPane);
        }
    }
}


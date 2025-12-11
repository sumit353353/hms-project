package ui;

import dao.PatientDAO;
import models.Patient;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PatientPanel extends JPanel {
    private JTextField nameField, ageField, contactField, emailField, diseaseField;
    private JComboBox<String> genderCombo, statusCombo;
    private JTable patientTable;
    private DefaultTableModel tableModel;
    private PatientDAO patientDAO = new PatientDAO();

    public PatientPanel() {
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        // Table Panel
        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        loadPatients();
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Add Patient"));
        panel.setBackground(new Color(245, 245, 245));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);

        panel.add(new JLabel("Gender:"));
        genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        panel.add(genderCombo);

        panel.add(new JLabel("Contact:"));
        contactField = new JTextField();
        panel.add(contactField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Disease:"));
        diseaseField = new JTextField();
        panel.add(diseaseField);

        panel.add(new JLabel("Status:"));
        statusCombo = new JComboBox<>(new String[]{"Admitted", "Discharged"});
        panel.add(statusCombo);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel(
                new String[]{"ID", "Name", "Age", "Gender", "Contact", "Disease", "Status"},
                0
        );
        patientTable = new JTable(tableModel);
        patientTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(patientTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();

        JButton addButton = new JButton("Add Patient");
        addButton.addActionListener(e -> addPatient());
        panel.add(addButton);

        JButton updateButton = new JButton("Update Patient");
        updateButton.addActionListener(e -> updatePatient());
        panel.add(updateButton);

        JButton deleteButton = new JButton("Delete Patient");
        deleteButton.addActionListener(e -> deletePatient());
        panel.add(deleteButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadPatients());
        panel.add(refreshButton);

        return panel;
    }

    private void addPatient() {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = (String) genderCombo.getSelectedItem();
            String contact = contactField.getText();
            String email = emailField.getText();
            String disease = diseaseField.getText();
            String status = (String) statusCombo.getSelectedItem();

            if (name.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Patient patient = new Patient(0, name, age, gender, contact, email, disease,
                    new Date(), status, 1, 1);

            if (patientDAO.addPatient(patient)) {
                JOptionPane.showMessageDialog(this, "Patient added successfully!");
                clearFields();
                loadPatients();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add patient", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid age", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePatient() {
        int selectedRow = patientTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a patient to update");
            return;
        }

        int patientId = (int) tableModel.getValueAt(selectedRow, 0);
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = (String) genderCombo.getSelectedItem();
            String contact = contactField.getText();
            String email = emailField.getText();
            String disease = diseaseField.getText();
            String status = (String) statusCombo.getSelectedItem();

            Patient patient = new Patient(patientId, name, age, gender, contact, email, disease,
                    new Date(), status, 1, 1);

            if (patientDAO.updatePatient(patient)) {
                JOptionPane.showMessageDialog(this, "Patient updated successfully!");
                clearFields();
                loadPatients();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update patient", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid age", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletePatient() {
        int selectedRow = patientTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a patient to delete");
            return;
        }

        int patientId = (int) tableModel.getValueAt(selectedRow, 0);
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this patient?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            if (patientDAO.deletePatient(patientId)) {
                JOptionPane.showMessageDialog(this, "Patient deleted successfully!");
                clearFields();
                loadPatients();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete patient", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        contactField.setText("");
        emailField.setText("");
        diseaseField.setText("");
    }

    private void loadPatients() {
        tableModel.setRowCount(0);
        List<Patient> patients = patientDAO.getAllPatients();

        for (Patient patient : patients) {
            tableModel.addRow(new Object[]{
                    patient.getPatientId(),
                    patient.getName(),
                    patient.getAge(),
                    patient.getGender(),
                    patient.getContactNo(),
                    patient.getDisease(),
                    patient.getStatus()
            });
        }
    }
}

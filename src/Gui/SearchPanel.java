package Gui;

import Student.StudentDatabase;
import Student.StudentUser;
import Admin.AdminRole;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchPanel extends JPanel {

    private JTextField searchField;
    private JComboBox<String> searchTypeComboBox;
    private JTextArea resultArea;
    private JButton searchButton;
    private JButton backButton;
    private JButton updateButton;
    private JPanel updatePanel;
    private JTextField updateNameField;
    private JTextField updateAgeField;
    private JComboBox<String> updateDepartmentBox;
    private JComboBox<String> updateGenderBox;
    private JTextField updateGpaField;
    private JButton confirmUpdateButton;
    private JButton cancelUpdateButton;
    private JComboBox<String> studentSelectionBox;
    private JPanel selectionPanel;

    private StudentDatabase db;
    private StudentUser currentStudent;
    private ArrayList<StudentUser> foundStudents;

    public SearchPanel() {
        db = new StudentDatabase("Students.txt");
        db.readFromFile();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(151, 219, 255));

        JLabel titleLabel = new JLabel("Search & Update Student", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 26));
        titleLabel.setForeground(new Color(40, 70, 150));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(titleLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setOpaque(false);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel searchTypeLabel = new JLabel("Search by:");
        searchTypeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        searchPanel.add(searchTypeLabel, gbc);

        searchTypeComboBox = new JComboBox<>(new String[] { "ID", "Name" });
        searchTypeComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        searchPanel.add(searchTypeComboBox, gbc);

        JLabel searchLabel = new JLabel("Search term:");
        searchLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        searchPanel.add(searchLabel, gbc);

        searchField = new JTextField(25);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        searchPanel.add(searchField, gbc);

        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        searchButton.setBackground(new Color(70, 130, 255));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        searchPanel.add(searchButton, gbc);

        contentPanel.add(searchPanel);

        // Student selection panel for multiple results
        selectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        selectionPanel.setOpaque(false);
        selectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        selectionPanel.setVisible(false);

        JLabel selectStudentLabel = new JLabel("Select student:");
        selectStudentLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        selectionPanel.add(selectStudentLabel);

        studentSelectionBox = new JComboBox<>();
        studentSelectionBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        studentSelectionBox.setPreferredSize(new Dimension(300, 30));
        selectionPanel.add(studentSelectionBox);

        contentPanel.add(selectionPanel);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setOpaque(false);
        resultPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        resultArea = new JTextArea(12, 50);
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setText("Enter search criteria and click Search to find a student.");
        resultArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        resultScrollPane.setPreferredSize(new Dimension(600, 250));
        resultScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        resultScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        resultPanel.add(resultScrollPane, BorderLayout.CENTER);

        contentPanel.add(resultPanel);

        updatePanel = new JPanel(new GridLayout(6, 2, 10, 10));
        updatePanel.setOpaque(false);
        updatePanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(255, 160, 40), 2),
            "Update Student Information",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(255, 160, 40)));
        updatePanel.setVisible(false);

        JLabel nameLabel = new JLabel("Name:*");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        updatePanel.add(nameLabel);
        updateNameField = new JTextField();
        updateNameField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        updatePanel.add(updateNameField);

        JLabel ageLabel = new JLabel("Age:*");
        ageLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        updatePanel.add(ageLabel);
        updateAgeField = new JTextField();
        updateAgeField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        updatePanel.add(updateAgeField);

        JLabel deptLabel = new JLabel("Department:*");
        deptLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        updatePanel.add(deptLabel);
        updateDepartmentBox = new JComboBox<>(new String[] { 
            "Computer and Communication", "Mechatronics", "Electromechanics", 
            "Biomedical", "Petrochemical", "Civil and Architecture" 
        });
        updateDepartmentBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        updatePanel.add(updateDepartmentBox);

        JLabel genderLabel = new JLabel("Gender:*");
        genderLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        updatePanel.add(genderLabel);
        updateGenderBox = new JComboBox<>(new String[] { "Male", "Female" });
        updateGenderBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        updatePanel.add(updateGenderBox);

        JLabel gpaLabel = new JLabel("GPA:*");
        gpaLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        updatePanel.add(gpaLabel);
        updateGpaField = new JTextField();
        updateGpaField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        updatePanel.add(updateGpaField);

        JPanel updateButtonPanel = new JPanel(new FlowLayout());
        updateButtonPanel.setOpaque(false);
        confirmUpdateButton = new JButton("Confirm Update");
        confirmUpdateButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        confirmUpdateButton.setBackground(new Color(0, 150, 0));
        confirmUpdateButton.setForeground(Color.WHITE);
        confirmUpdateButton.setFocusPainted(false);
        cancelUpdateButton = new JButton("Cancel");
        cancelUpdateButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        cancelUpdateButton.setBackground(new Color(200, 0, 0));
        cancelUpdateButton.setForeground(Color.WHITE);
        cancelUpdateButton.setFocusPainted(false);

        updateButtonPanel.add(confirmUpdateButton);
        updateButtonPanel.add(cancelUpdateButton);

        updatePanel.add(new JLabel(""));
        updatePanel.add(updateButtonPanel);

        contentPanel.add(updatePanel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        updateButton = new JButton("Update Student");
        updateButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        updateButton.setBackground(new Color(255, 160, 40));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);
        updateButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        updateButton.setEnabled(false);

        backButton = new JButton("Back to Home");
        backButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        backButton.setBackground(new Color(100, 150, 255));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        buttonPanel.add(updateButton);
        buttonPanel.add(backButton);

        contentPanel.add(buttonPanel);

        add(contentPanel, BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });

        studentSelectionBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (studentSelectionBox.getSelectedIndex() >= 0 && foundStudents != null && !foundStudents.isEmpty()) {
                    int selectedIndex = studentSelectionBox.getSelectedIndex();
                    currentStudent = foundStudents.get(selectedIndex);
                    displayStudentInfo(currentStudent);
                    updateButton.setEnabled(true);
                    hideUpdatePanel();
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdatePanel();
            }
        });

        confirmUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmUpdate();
            }
        });

        cancelUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideUpdatePanel();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(SearchPanel.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }
            }
        });
    }

    private void performSearch() {
        String searchTerm = searchField.getText().trim();
        String searchType = (String) searchTypeComboBox.getSelectedItem();

        if (searchTerm.isEmpty()) {
            resultArea.setText("Please enter a search term.");
            updateButton.setEnabled(false);
            hideUpdatePanel();
            hideSelectionPanel();
            return;
        }

        db.readFromFile();
        ArrayList<StudentUser> students = db.returnAllRecords();
        foundStudents = new ArrayList<>();

        if ("ID".equals(searchType)) {
            try {
                int searchId = Integer.parseInt(searchTerm);
                for (StudentUser student : students) {
                    if (student.getId() == searchId) {
                        foundStudents.add(student);
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                resultArea.setText("Invalid ID format. Please enter a numeric ID.");
                updateButton.setEnabled(false);
                hideUpdatePanel();
                hideSelectionPanel();
                return;
            }
        } else if ("Name".equals(searchType)) {
            for (StudentUser student : students) {
                if (student.getName().equalsIgnoreCase(searchTerm)) {
                    foundStudents.add(student);
                }
            }
        }

        if (!foundStudents.isEmpty()) {
            if (foundStudents.size() == 1) {
                // Single result
                currentStudent = foundStudents.get(0);
                displayStudentInfo(currentStudent);
                updateButton.setEnabled(true);
                hideSelectionPanel();
            } else {
                // Multiple results
                displayMultipleStudents();
                showSelectionPanel();
            }
            hideUpdatePanel();
        } else {
            resultArea.setText("No student found with " + searchType.toLowerCase() + ": " + searchTerm);
            updateButton.setEnabled(false);
            hideUpdatePanel();
            hideSelectionPanel();
            currentStudent = null;
        }
    }

    private void displayMultipleStudents() {
        StringBuilder info = new StringBuilder();
        info.append("Found ").append(foundStudents.size()).append(" students with the name: ").append(foundStudents.get(0).getName()).append("\n\n");
        
        // Populate the selection combo box
        studentSelectionBox.removeAllItems();
        for (int i = 0; i < foundStudents.size(); i++) {
            StudentUser student = foundStudents.get(i);
            String displayText = "ID: " + student.getId() + " | Age: " + student.getAge() + " | Department: " + student.getDepartment();
            studentSelectionBox.addItem(displayText);
            
            info.append("Student ").append(i + 1).append(":\n")
                .append("ID: ").append(student.getId()).append("\n")
                .append("Name: ").append(student.getName()).append("\n")
                .append("Age: ").append(student.getAge()).append("\n")
                .append("Gender: ").append(student.getGender()).append("\n")
                .append("Department: ").append(student.getDepartment()).append("\n")
                .append("GPA: ").append(String.format("%.2f", student.getGpa())).append("\n")
                .append("----------------------------------------\n\n");
        }
        
        info.append("Please select a student from the dropdown above to update.");
        resultArea.setText(info.toString());
        
        // Set the first student as current by default
        if (!foundStudents.isEmpty()) {
            currentStudent = foundStudents.get(0);
            studentSelectionBox.setSelectedIndex(0);
            updateButton.setEnabled(true);
        }
    }

    private void displayStudentInfo(StudentUser student) {
        String info = String.format(
                "Student Found!\n\n" +
                "ID: %d\n" +
                "Name: %s\n" +
                "Age: %d\n" +
                "Gender: %s\n" +
                "Department: %s\n" +
                "GPA: %.2f\n\n" +
                "Click 'Update Student' to modify information.",
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getGender(),
                student.getDepartment(),
                student.getGpa());
        resultArea.setText(info);
    }

    private void showSelectionPanel() {
        selectionPanel.setVisible(true);
        revalidate();
        repaint();
    }

    private void hideSelectionPanel() {
        selectionPanel.setVisible(false);
        revalidate();
        repaint();
    }

    private void showUpdatePanel() {
        if (currentStudent != null) {
            updateNameField.setText(currentStudent.getName());
            updateAgeField.setText(String.valueOf(currentStudent.getAge()));
            updateDepartmentBox.setSelectedItem(currentStudent.getDepartment());
            updateGenderBox.setSelectedItem(currentStudent.getGender());
            updateGpaField.setText(String.valueOf(currentStudent.getGpa()));
            updatePanel.setVisible(true);
        }
    }

    private void hideUpdatePanel() {
        updatePanel.setVisible(false);
    }

    private void confirmUpdate() {
        if (currentStudent == null) {
            JOptionPane.showMessageDialog(this, "No student selected for update.");
            return;
        }

        try {
            String name = updateNameField.getText().trim();
            String ageText = updateAgeField.getText().trim();
            String department = (String) updateDepartmentBox.getSelectedItem();
            String gender = (String) updateGenderBox.getSelectedItem();
            String gpaText = updateGpaField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty.");
                return;
            }

            if (ageText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Age cannot be empty.");
                return;
            }

            int age;
            try {
                age = Integer.parseInt(ageText);
                if (age < 15 || age > 100) {
                    JOptionPane.showMessageDialog(this, "Age must be between 15 and 100.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Age must be a valid number.");
                return;
            }

            if (gpaText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "GPA cannot be empty.");
                return;
            }

            float gpa;
            try {
                gpa = Float.parseFloat(gpaText);
                if (gpa < 0 || gpa > 4) {
                    JOptionPane.showMessageDialog(this, "GPA must be between 0 and 4.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "GPA must be a valid number.");
                return;
            }

            AdminRole admin = new AdminRole();
            boolean success = admin.updateStudent(currentStudent.getId(), name, age, department, gender, gpa);

            if (success) {
                JOptionPane.showMessageDialog(this, "Student updated successfully!");
                hideUpdatePanel();
                performSearch(); // Refresh the search to show updated data
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update student.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating student: " + e.getMessage());
        }
    }
}
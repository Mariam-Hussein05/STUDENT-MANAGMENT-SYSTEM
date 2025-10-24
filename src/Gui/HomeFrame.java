package gui.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeFrame extends JFrame {

    public HomeFrame() {
        // ===== FRAME SETUP =====
        setTitle("Student Management System");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        // ===== MENU BAR =====
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(30, 30, 45));
        menuBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        String[] menus = { "Dashboard", "Add Student", "View Students", "Search & Update", "Delete Student" };
        for (String name : menus) {
            JMenu menu = new JMenu(name);
            menu.setForeground(Color.WHITE);
            menu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            menu.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
            menuBar.add(menu);
        }
        setJMenuBar(menuBar);

        // ===== BACKGROUND PANEL =====
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Gradient background
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(50, 80, 180),
                        getWidth(), getHeight(), new Color(120, 180, 255));
                g2.setPaint(gradient);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        add(backgroundPanel);

        // ===== TITLE =====
        JLabel titleLabel = new JLabel("Student Management System Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        // ===== MAIN PANEL =====
        JPanel mainPanel = new JPanel(new GridLayout(2, 2, 30, 30));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 60, 80));

        mainPanel.add(createGlassCard("Add Student", "Add new student records", new Color(0, 200, 100)));
        mainPanel.add(createGlassCard("View Students", "Browse all student data", new Color(70, 130, 255)));
        mainPanel.add(createGlassCard("Search & Update", "Find and modify records", new Color(255, 160, 40)));
        mainPanel.add(createGlassCard("Delete Student", "Remove student records", new Color(230, 70, 70)));

        backgroundPanel.add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createGlassCard(String title, String description, Color accentColor) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Semi-transparent background for glass effect
                Color bg = new Color(255, 255, 255, 70);
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };

        card.setLayout(new BorderLayout());
        card.setOpaque(false);
        card.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // ===== ICON PLACEHOLDER =====
        JLabel icon = new JLabel("👤", SwingConstants.CENTER);
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        icon.setForeground(accentColor);
        card.add(icon, BorderLayout.WEST);

        // ===== TITLE + DESCRIPTION =====
        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
        titleLabel.setForeground(accentColor);

        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descLabel.setForeground(Color.WHITE);

        textPanel.add(titleLabel);
        textPanel.add(descLabel);
        card.add(textPanel, BorderLayout.CENTER);

        // ===== HOVER ANIMATION =====
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(accentColor, 3, true),
                        BorderFactory.createEmptyBorder(17, 22, 17, 22)));
                card.setBackground(new Color(255, 255, 255, 100));
                card.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
                card.setBackground(new Color(255, 255, 255, 70));
                card.repaint();
            }
        });

        // Soft shadow container for floating effect
        JPanel shadowContainer = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 60));
                g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 30, 30);
            }
        };
        shadowContainer.setOpaque(false);
        shadowContainer.add(card);

        return shadowContainer;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Optional: FlatLaf for smoother design if added later
                UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
            } catch (Exception e) {
                System.out.println("Default LookAndFeel used.");
            }
            new HomeFrame().setVisible(true);
        });
    }
}



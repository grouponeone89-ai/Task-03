import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

// Main class - University Portal using CardLayout
// CardLayout lets us switch between panels without opening new windows
// This is good for a portal because all screens stay in one place

public class UniversityPortal  implements ActionListener {

    private JFrame frame;

    // CardLayout to switch between panels
    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel(cardLayout);

    // Buttons for navigation
    JButton btnDashboard, btnCourses, btnFees, btnResults, btnHostel, btnLibrary;

    // Constructor
    public UniversityPortal() {

        frame = new JFrame("University Student Portal");
        frame.setSize(1000, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Add top bar
        frame.add(createTopBar(), BorderLayout.NORTH);

        // Add navigation sidebar
        frame.add(createSidebar(), BorderLayout.WEST);

        // Add all the service panels to the main panel
        mainPanel.add(createDashboard(),    "dashboard");
        mainPanel.add(createCourses(),      "courses");
        mainPanel.add(createFees(),         "fees");
        mainPanel.add(createResults(),      "results");
        mainPanel.add(createHostel(),       "hostel");
        mainPanel.add(createLibrary(),      "library");

        frame.add(mainPanel, BorderLayout.CENTER);

        // Add status bar at the bottom
        frame.add(createStatusBar(), BorderLayout.SOUTH);

        // Show dashboard first
        cardLayout.show(mainPanel, "dashboard");

        frame.setVisible(true);
    }

    // Top bar with university name
    JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(255, 255, 255));
        topBar.setPreferredSize(new Dimension(0, 50));
        topBar.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        JLabel title = new JLabel("University Academic Services Portal");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(Color.black);

        // Load the logo image from the same folder as the program
        ImageIcon logoIcon = new ImageIcon("IUEA LOGO.png");

        // Resize it to fit the top bar (40x40 pixels)
        Image scaled = logoIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaled);

        // Put the logo inside a JLabel
        JLabel logoImg = new JLabel(logoIcon);
        logoImg.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // small gap on right

        JLabel student = new JLabel("Student: Mohammed Abdelbagi Omer |  ID: 24/567/BIT-M  |  Sem IV 2026/27");
        student.setFont(new Font("Arial", Font.PLAIN, 12));
        student.setForeground(Color.LIGHT_GRAY);

        // Put logo and title side by side in one small panel
        JPanel leftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        leftSide.setOpaque(false);
        leftSide.add(logoImg);
        leftSide.add(title);

        topBar.add(leftSide, BorderLayout.WEST);
        topBar.add(student, BorderLayout.EAST);

        return topBar;
    }

    // Sidebar with navigation buttons
    JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(255, 255, 255));
        sidebar.setPreferredSize(new Dimension(190, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel menuLabel = new JLabel("  MAIN MENU");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 11));
        menuLabel.setForeground(Color.GRAY);
        menuLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(menuLabel);
        sidebar.add(Box.createVerticalStrut(8));

        btnDashboard = makeNavButton("  Dashboard");
        btnCourses   = makeNavButton("  Course Registration");
        btnFees      = makeNavButton("  Fee Payment");
        btnResults   = makeNavButton("  Exam Results");
        btnHostel    = makeNavButton("  Hostel Application");
        btnLibrary   = makeNavButton("  Library Services");

        sidebar.add(btnDashboard);
        sidebar.add(btnCourses);
        sidebar.add(btnFees);
        sidebar.add(btnResults);
        sidebar.add(btnHostel);
        sidebar.add(btnLibrary);

        sidebar.add(Box.createVerticalGlue());

        return sidebar;
    }

    // Helper to create sidebar nav buttons
    JButton makeNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 13));
        btn.setForeground(Color.gray);
        btn.setBackground(new Color(251, 251, 253));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.addActionListener(this);
        return btn;
    }

    // Status bar at the bottom
    JPanel createStatusBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(new Color(230, 230, 230));
        bar.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));

        JLabel left  = new JLabel("Status: Connected | Session Active");
        JLabel right = new JLabel("Help: ict@university.ac.ke");
        left.setFont(new Font("Arial", Font.PLAIN, 11));
        right.setFont(new Font("Arial", Font.PLAIN, 11));

        bar.add(left,  BorderLayout.WEST);
        bar.add(right, BorderLayout.EAST);
        return bar;
    }


    // ActionListener - handles all navigation button clicks

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDashboard) cardLayout.show(mainPanel, "dashboard");
        if (e.getSource() == btnCourses)   cardLayout.show(mainPanel, "courses");
        if (e.getSource() == btnFees)      cardLayout.show(mainPanel, "fees");
        if (e.getSource() == btnResults)   cardLayout.show(mainPanel, "results");
        if (e.getSource() == btnHostel)    cardLayout.show(mainPanel, "hostel");
        if (e.getSource() == btnLibrary)   cardLayout.show(mainPanel, "library");
    }


    // PANEL 1: DASHBOARD

    JPanel createDashboard() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Page title
        JLabel heading = new JLabel("Welcome, Mohammed Omer !");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(heading, BorderLayout.NORTH);

        // Grid of quick-access cards
        JPanel grid = new JPanel(new GridLayout(2, 3, 12, 12));
        grid.setBackground(new Color(245, 245, 250));

        grid.add(makeTile("Course Registration", "Register for units this semester",   new Color(0, 0, 0)));
        grid.add(makeTile("Fee Payment",         "View balance and pay fees",           new Color(0, 0, 0)));
        grid.add(makeTile("Exam Results",        "Check your grades and transcript",    new Color(0, 0, 0)));
        grid.add(makeTile("Hostel Application",  "Apply for campus accommodation",      new Color(0, 0, 0)));
        grid.add(makeTile("Library Services",    "Borrow books and e-resources",        new Color(0, 0, 0)));
        grid.add(makeTile("Notifications",       "3 items need your attention",         new Color(0, 0, 0)));

        panel.add(grid, BorderLayout.CENTER);

        // Alerts at bottom
        JPanel alerts = new JPanel(new GridLayout(1, 3, 10, 0));
        alerts.setBackground(new Color(245, 245, 250));
        alerts.add(makeAlert("Fee balance USD 350  due 31 Oct", new Color(255, 243, 205)));
        alerts.add(makeAlert("Course registration closes 15 Oct",  new Color(204, 229, 255)));
        alerts.add(makeAlert("Semester IV results have been released", new Color(212, 237, 218)));

        panel.add(alerts, BorderLayout.SOUTH);

        return panel;
    }

    JPanel makeTile(String title, String desc, Color color) {
        JPanel tile = new JPanel(new BorderLayout());
        tile.setBackground(Color.WHITE);
        tile.setBorder(BorderFactory.createLineBorder(color, 2));

        JPanel top = new JPanel();
        top.setBackground(color);
        JLabel t = new JLabel(title);
        t.setFont(new Font("Arial", Font.BOLD, 13));
        t.setForeground(Color.WHITE);
        t.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        top.add(t);

        JLabel d = new JLabel("<html><center>" + desc + "</center></html>");
        d.setFont(new Font("Arial", Font.PLAIN, 12));
        d.setForeground(Color.DARK_GRAY);
        d.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        d.setHorizontalAlignment(SwingConstants.CENTER);

        tile.add(top, BorderLayout.NORTH);
        tile.add(d,   BorderLayout.CENTER);
        return tile;
    }

    JPanel makeAlert(String msg, Color bg) {
        JPanel p = new JPanel();
        p.setBackground(bg);
        p.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        JLabel l = new JLabel("<html>" + msg + "</html>");
        l.setFont(new Font("Arial", Font.PLAIN, 11));
        p.add(l);
        return p;
    }


    // PANEL 2: COURSE REGISTRATION

    JPanel createCourses() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel heading = new JLabel("Course Registration — Semester IV 2026/27");
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(heading, BorderLayout.NORTH);

        // Table of available courses
        String[] columns = {"Code", "Unit Name", "Credit Hrs", "Lecturer", "Status"};
        Object[][] data = {
                {"CS301", "Data Structures & Algorithms",  3, "Dr. A. Mwangi",  "Available"},
                {"CS302", "Operating Systems",             3, "Prof. J. Ochieng","Available"},
                {"CS303", "Database Systems",              3, "Dr. M. Kariuki",  "Available"},
                {"CS304", "Software Engineering",          3, "Dr. S. Njoroge",  "Available"},
                {"CS305", "Computer Networks",             3, "Prof. P. Wambua", "Full"},
                {"CS306", "Artificial Intelligence",       3, "Dr. L. Mutua",    "Available"},
                {"CS307", "Mobile App Development",        3, "Mr. D. Otieno",   "Available"},
                {"MATH301","Numerical Analysis",           3, "Dr. R. Gacheru",  "Available"},
        };

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.setSelectionBackground(new Color(173, 216, 230));

        JScrollPane scroll = new JScrollPane(table);

        // Right side summary panel
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBackground(Color.WHITE);
        right.setBorder(BorderFactory.createTitledBorder("Registration Summary"));
        right.setPreferredSize(new Dimension(220, 0));

        right.add(new JLabel("Max credit hours: 10"));
        right.add(Box.createVerticalStrut(5));
        right.add(new JLabel("Registered so far: 6"));
        right.add(Box.createVerticalStrut(5));
        right.add(new JLabel("Remaining: 4"));
        right.add(Box.createVerticalStrut(12));
        right.add(new JLabel("Already registered:"));
        right.add(Box.createVerticalStrut(4));
        right.add(new JLabel("  CS301 - Data Structures"));
        right.add(new JLabel("  CS302 - Operating Systems"));
        right.add(Box.createVerticalGlue());

        JButton registerBtn = new JButton("Register Selected Unit");
        registerBtn.setBackground(new Color(41, 128, 185));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFont(new Font("Arial", Font.BOLD, 12));
        registerBtn.setFocusPainted(false);
        registerBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a unit first.");
                return;
            }
            String status = (String) model.getValueAt(row, 4);
            String name   = (String) model.getValueAt(row, 1);
            if (status.equals("Full")) {
                JOptionPane.showMessageDialog(frame, name + " is full. Choose another.");
                return;
            }
            int choice = JOptionPane.showConfirmDialog(frame,
                    "Register for " + name + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION)
                JOptionPane.showMessageDialog(frame, "Successfully registered for " + name + "!");
        });
        right.add(registerBtn);

        JPanel centre = new JPanel(new BorderLayout(10, 0));
        centre.setBackground(new Color(245, 245, 250));
        centre.add(scroll, BorderLayout.CENTER);
        centre.add(right,  BorderLayout.EAST);

        panel.add(centre, BorderLayout.CENTER);
        return panel;
    }


    // PANEL 3: FEE PAYMENT

    JPanel createFees() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel heading = new JLabel("Fee Payment — Semester IV 2026/27");
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(heading, BorderLayout.NORTH);

        // Summary at the top
        JPanel summary = new JPanel(new GridLayout(1, 3, 10, 0));
        summary.setBackground(new Color(245, 245, 250));
        summary.setPreferredSize(new Dimension(0, 80));

        summary.add(makeFeeBox("Total Fees",  "$1,000", Color.DARK_GRAY));
        summary.add(makeFeeBox("Amount Paid", "$650", new Color(39, 174, 96)));
        summary.add(makeFeeBox("Balance Due", "$350", new Color(192, 57, 43)));

        // Fee breakdown table
        String[] cols = {"Fee Item", "Amount (USD)", "Paid (USD)", "Balance (USD)"};

        Object[][] rows = {
                {"Tuition Fee",       "$600", "$400", "$200"},
                {"Examination Fee",   "$50",  "$50",  "$0"},
                {"Registration Fee",  "$30",  "$30",  "$0"},
                {"Caution Fee",       "$80",  "$50",  "$30"},
                {"ICT Fee",           "$40",  "$0",   "$40"},
                {"Library Fee",       "$30",  "$0",   "$30"},
                {"Student Union Fee", "$20",  "$0",   "$20"},
                {"Medical Cover",     "$50",  "$0",   "$50"},
                {"Activity Fee",      "$30",  "$0",   "$30"},
                {"Accommodation",     "$70",  "$0",   "$70"},
        };

        JTable table = new JTable(new DefaultTableModel(rows, cols) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        });

        table.setRowHeight(27);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(table);

        // Payment form on the right
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createTitledBorder("Make a Payment"));
        form.setPreferredSize(new Dimension(230, 0));

        String[] methods = {
                "Bank Transfer",
                "Credit Card",
                "Debit Card",
                "Mobile Money"
        };

        JComboBox<String> methodBox = new JComboBox<>(methods);

        // Default payment amount is the current balance
        JTextField amountField = new JTextField("350");

        JTextField refField = new JTextField();

        methodBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        amountField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        refField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        form.add(new JLabel("Payment Method:"));
        form.add(Box.createVerticalStrut(3));
        form.add(methodBox);

        form.add(Box.createVerticalStrut(8));
        form.add(new JLabel("Amount (USD):"));
        form.add(Box.createVerticalStrut(3));
        form.add(amountField);

        form.add(Box.createVerticalStrut(8));
        form.add(new JLabel("Reference Code:"));
        form.add(Box.createVerticalStrut(3));
        form.add(refField);

        form.add(Box.createVerticalStrut(12));

        JButton payBtn = new JButton("Submit Payment");
        payBtn.setBackground(new Color(39, 174, 96));
        payBtn.setForeground(Color.WHITE);
        payBtn.setFont(new Font("Arial", Font.BOLD, 12));
        payBtn.setFocusPainted(false);
        payBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        payBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

        payBtn.addActionListener(e -> {
            String amt = amountField.getText().trim();
            String ref = refField.getText().trim();

            if (amt.isEmpty() || ref.isEmpty()) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Please fill in all fields."
                );
                return;
            }

            JOptionPane.showMessageDialog(
                    frame,
                    "Payment of $" + amt + " submitted!\nReference: " + ref
            );
        });

        form.add(payBtn);

        JPanel centre = new JPanel(new BorderLayout(10, 0));
        centre.setBackground(new Color(245, 245, 250));
        centre.add(scroll, BorderLayout.CENTER);
        centre.add(form, BorderLayout.EAST);

        JPanel inner = new JPanel(new BorderLayout(0, 10));
        inner.setBackground(new Color(245, 245, 250));
        inner.add(summary, BorderLayout.NORTH);
        inner.add(centre, BorderLayout.CENTER);

        panel.add(inner, BorderLayout.CENTER);

        return panel;
    }

    JPanel makeFeeBox(String label, String amount, Color color) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JLabel lbl = new JLabel(label, SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        lbl.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        JLabel amt = new JLabel(amount, SwingConstants.CENTER);
        amt.setFont(new Font("Arial", Font.BOLD, 18));
        amt.setForeground(color);
        p.add(lbl, BorderLayout.NORTH);
        p.add(amt, BorderLayout.CENTER);
        return p;
    }


    // PANEL 4: EXAM RESULTS

    JPanel createResults() {

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JLabel title = new JLabel("Examination Results");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(title, BorderLayout.NORTH);

        // GPA
        JPanel gpa = new JPanel(new GridLayout(1, 3));

        gpa.add(new JLabel("Semester GPA: 3.72"));
        gpa.add(new JLabel("Cumulative GPA: 3.65"));
        gpa.add(new JLabel("Class: 2nd Upper"));

        // Results table
        String[] columns = {
                "Unit Code", "Unit Name", "CAT", "Exam", "Total", "Grade"
        };

        Object[][] data = {
                {"CS301", "Data Structures", 24, 55, 79, "A-"},
                {"CS302", "Operating Systems", 26, 52, 78, "A-"},
                {"CS303", "Database Systems", 28, 58, 86, "A"},
                {"CS304", "Software Engineering", 22, 50, 72, "B+"},
                {"CS305", "Computer Networks", 19, 45, 64, "B-"},
                {"MATH301", "Numerical Analysis", 25, 47, 72, "B+"}
        };

        JTable table = new JTable(data, columns);
        JScrollPane scroll = new JScrollPane(table);

        // Print button
        JButton print = new JButton("Print Transcript");

        print.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        frame,
                        "Transcript sent to printer."
                )
        );

        JPanel center = new JPanel(new BorderLayout(10, 10));
        center.add(gpa, BorderLayout.NORTH);
        center.add(scroll, BorderLayout.CENTER);
        center.add(print, BorderLayout.SOUTH);

        panel.add(center, BorderLayout.CENTER);

        return panel;
    }


    // PANEL 5: HOSTEL APPLICATION

    JPanel createHostel() {

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JLabel title = new JLabel("Hostel Application");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(7, 2, 5, 5));

        JTextField name = new JTextField("Mohammed Omer");
        JTextField id = new JTextField("24/567/BIT-M");
        JTextField programme = new JTextField("BIT");

        JComboBox<String> year = new JComboBox<>(
                new String[]{"Year 1", "Year 2", "Year 3", "Year 4"}
        );

        JComboBox<String> room = new JComboBox<>(
                new String[]{"Single Room - $120", "Double Room - $80", "Triple Room - $60"}
        );

        JComboBox<String> priority = new JComboBox<>(
                new String[]{"Financial Need", "Distance", "Medical", "General"}
        );

        JTextField remarks = new JTextField();

        form.add(new JLabel("Full Name:"));
        form.add(name);

        form.add(new JLabel("Student ID:"));
        form.add(id);

        form.add(new JLabel("Programme:"));
        form.add(programme);

        form.add(new JLabel("Study Year:"));
        form.add(year);

        form.add(new JLabel("Room:"));
        form.add(room);

        form.add(new JLabel("Priority:"));
        form.add(priority);

        form.add(new JLabel("Remarks:"));
        form.add(remarks);

        JButton submit = new JButton("Submit Application");

        submit.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        frame,
                        "Application submitted successfully!"
                )
        );

        JPanel center = new JPanel(new BorderLayout(10, 10));
        center.add(form, BorderLayout.CENTER);
        center.add(submit, BorderLayout.SOUTH);

        panel.add(center, BorderLayout.CENTER);

        return panel;
    }

    // helper to add a label + field row in GridBagLayout
    void addRow(JPanel p, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        p.add(new JLabel(label), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        field.setPreferredSize(new Dimension(250, 30));
        p.add(field, gbc);
    }


    // PANEL 6: LIBRARY SERVICES

    JPanel createLibrary() {

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JLabel title = new JLabel("Library Services");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(title, BorderLayout.NORTH);

        // Search
        JPanel search = new JPanel(new BorderLayout());

        JTextField searchBox = new JTextField();
        JButton searchButton = new JButton("Search");

        search.add(new JLabel("Search: "), BorderLayout.WEST);
        search.add(searchBox, BorderLayout.CENTER);
        search.add(searchButton, BorderLayout.EAST);

        // Books
        String[] columns = {
                "ISBN", "Title", "Author", "Available"
        };

        Object[][] data = {
                {"978-0262046305", "Introduction to Algorithms", "Cormen", "3/5"},
                {"978-0134685991", "Effective Java", "Bloch", "2/3"},
                {"978-0596517748", "JavaScript", "Crockford", "1/2"},
                {"978-0135957059", "Pragmatic Programmer", "Thomas", "2/3"},
                {"978-0135166307", "Clean Code", "Martin", "1/3"}
        };

        JTable table = new JTable(data, columns);
        JScrollPane scroll = new JScrollPane(table);

        JButton borrow = new JButton("Borrow Book");

        borrow.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Please select a book."
                );
            } else {
                String book = table.getValueAt(row, 1).toString();

                JOptionPane.showMessageDialog(
                        frame,
                        "You borrowed: " + book
                );
            }
        });

        JPanel center = new JPanel(new BorderLayout(10, 10));
        center.add(search, BorderLayout.NORTH);
        center.add(scroll, BorderLayout.CENTER);
        center.add(borrow, BorderLayout.SOUTH);

        panel.add(center, BorderLayout.CENTER);

        return panel;
    }

    // MAIN METHOD

    public static void main(String[] args) {
        // Use system look and feel so it looks like a normal desktop app
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Run on the Event Dispatch Thread as required by Swing
        SwingUtilities.invokeLater(() -> new UniversityPortal());
    }
}
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AplicatieInterfata extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainContainer;
    private DefaultTableModel model;
    private JTable tabel;
    private JTextField numeField, emailField;

    private ArrayList<String> favorite = new ArrayList<>();
    private ArrayList<String> notificari = new ArrayList<>();

    private boolean darkMode = true;

    private JLabel totalLabel, favoriteLabel, notificariLabel;

    public AplicatieInterfata() {
        setTitle("UserFlow");
        setSize(1100, 680);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(new String[]{"Nume", "Email"}, 0);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        mainContainer.add(paginaStart(), "start");
        mainContainer.add(paginaAplicatie(), "app");

        add(mainContainer);

        cardLayout.show(mainContainer, "app");
    }

    private JPanel paginaStart() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(new Color(11, 22, 34));

        JLabel titlu = new JLabel("UserFlow");
        titlu.setFont(new Font("Segoe UI", Font.BOLD, 46));
        titlu.setForeground(Color.WHITE);

        JButton start = new JButton("Începe acum");
        stilButon(start);

        start.addActionListener(e -> cardLayout.show(mainContainer, "app"));

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(20, 35, 50));
        card.setBorder(BorderFactory.createEmptyBorder(50, 70, 50, 70));

        titlu.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(titlu);
        card.add(Box.createVerticalStrut(30));
        card.add(start);

        p.add(card);
        return p;
    }

    private JPanel paginaAplicatie() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(fundal());

        p.add(sidebar(), BorderLayout.WEST);
        p.add(zonaPrincipala(), BorderLayout.CENTER);

        return p;
    }

    private JPanel sidebar() {
        JPanel s = new JPanel();
        s.setLayout(new BoxLayout(s, BoxLayout.Y_AXIS));
        s.setPreferredSize(new Dimension(230, 680));
        s.setBackground(new Color(12, 25, 40));
        s.setBorder(BorderFactory.createEmptyBorder(20, 12, 20, 12));

        JLabel logo = new JLabel("UF   UserFlow");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 23));
        logo.setForeground(Color.WHITE);

        JButton home = butonMeniu("Home");
        JButton profil = butonMeniu("Profil");
        JButton editeaza = butonMeniu("Editează");
        JButton sterge = butonMeniu("Șterge");
        JButton cauta = butonMeniu("Caută");
        JButton fav = butonMeniu("Favorite");
        JButton chat = butonMeniu("Chat");
        JButton notif = butonMeniu("Notificări");
        JButton setari = butonMeniu("Setări");
        JButton logout = butonMeniu("Logout");

        home.addActionListener(e -> home());
        profil.addActionListener(e -> profil());
        editeaza.addActionListener(e -> editeaza());
        sterge.addActionListener(e -> sterge());
        cauta.addActionListener(e -> cauta());
        fav.addActionListener(e -> favorite());
        chat.addActionListener(e -> chat());
        notif.addActionListener(e -> notificari());
        setari.addActionListener(e -> setari());
        logout.addActionListener(e -> cardLayout.show(mainContainer, "start"));

        s.add(logo);
        s.add(Box.createVerticalStrut(25));

        JButton[] butoane = {
                home, profil, editeaza, sterge,
                cauta, fav, chat, notif, setari, logout
        };

        for (JButton b : butoane) {
            s.add(b);
            s.add(Box.createVerticalStrut(8));
        }

        return s;
    }

    private JPanel zonaPrincipala() {
        JPanel z = new JPanel(new BorderLayout(20, 20));
        z.setBackground(fundal());
        z.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel titlu = new JLabel("Gestionare utilizatori");
        titlu.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titlu.setForeground(text());

        JPanel centru = new JPanel(new GridLayout(1, 2, 25, 0));
        centru.setOpaque(false);
        centru.add(formular());
        centru.add(tabelPanel());

        z.add(titlu, BorderLayout.NORTH);
        z.add(centru, BorderLayout.CENTER);
        z.add(statistici(), BorderLayout.SOUTH);

        return z;
    }

    private JPanel formular() {
        JPanel f = card();
        f.setLayout(new BoxLayout(f, BoxLayout.Y_AXIS));

        JLabel titlu = label("Adaugă utilizator", 22, true);

        numeField = field();
        emailField = field();

        JButton save = new JButton("Save");
        stilButon(save);

        save.addActionListener(e -> adaugaUtilizator());

        f.add(titlu);
        f.add(Box.createVerticalStrut(25));
        f.add(label("Nume", 14, false));
        f.add(Box.createVerticalStrut(8));
        f.add(numeField);
        f.add(Box.createVerticalStrut(18));
        f.add(label("Email", 14, false));
        f.add(Box.createVerticalStrut(8));
        f.add(emailField);
        f.add(Box.createVerticalStrut(25));
        f.add(save);

        return f;
    }

    private JPanel tabelPanel() {
        JPanel p = card();
        p.setLayout(new BorderLayout(10, 15));

        JLabel titlu = label("Lista utilizatorilor", 22, true);

        tabel = new JTable(model);
        tabel.setRowHeight(34);
        tabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        p.add(titlu, BorderLayout.NORTH);
        p.add(new JScrollPane(tabel), BorderLayout.CENTER);

        return p;
    }

    private JPanel statistici() {
        JPanel p = new JPanel(new GridLayout(1, 3, 15, 0));
        p.setOpaque(false);

        totalLabel = new JLabel("0");
        favoriteLabel = new JLabel("0");
        notificariLabel = new JLabel("0");

        p.add(cardStat("Total utilizatori", totalLabel));
        p.add(cardStat("Favorite", favoriteLabel));
        p.add(cardStat("Notificări", notificariLabel));

        return p;
    }

    private JPanel cardStat(String titlu, JLabel valoare) {
        JPanel p = card();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        JLabel t = label(titlu, 15, false);
        valoare.setFont(new Font("Segoe UI", Font.BOLD, 30));
        valoare.setForeground(text());

        p.add(t);
        p.add(Box.createVerticalStrut(8));
        p.add(valoare);

        return p;
    }

    private void adaugaUtilizator() {
        String nume = numeField.getText().trim();
        String email = emailField.getText().trim();

        if (nume.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completează toate câmpurile!");
        } else {
            model.addRow(new Object[]{nume, email});
            notificari.add("Utilizatorul " + nume + " a fost adăugat.");
            numeField.setText("");
            emailField.setText("");
            updateStats();
        }
    }

    private void home() {
        JOptionPane.showMessageDialog(this,
                "Total utilizatori: " + model.getRowCount() +
                        "\nFavorite: " + favorite.size() +
                        "\nNotificări: " + notificari.size());
    }

private void profil() {
    if (model.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "Nu există utilizatori adăugați!");
        return;
    }

    String[] utilizatori = new String[model.getRowCount()];

    for (int i = 0; i < model.getRowCount(); i++) {
        utilizatori[i] = model.getValueAt(i, 0).toString();
    }

    String utilizatorSelectat = (String) JOptionPane.showInputDialog(
            this,
            "Selectează utilizatorul:",
            "Profil utilizator",
            JOptionPane.QUESTION_MESSAGE,
            null,
            utilizatori,
            utilizatori[0]
    );

    if (utilizatorSelectat == null) {
        return;
    }

    for (int i = 0; i < model.getRowCount(); i++) {

        String nume = model.getValueAt(i, 0).toString();

        if (nume.equals(utilizatorSelectat)) {

            String email = model.getValueAt(i, 1).toString();

            tabel.setRowSelectionInterval(i, i);

            JOptionPane.showMessageDialog(
                    this,
                    "═══════════════════════\n" +
                    "      PROFIL USER\n" +
                    "═══════════════════════\n\n" +
                    "👤 Nume: " + nume +
                    "\n📧 Email: " + email +
                    "\n🟢 Status: Activ" +
                    "\n⭐ Favorit: " +
                    (favorite.contains(nume) ? "Da" : "Nu"),
                    "Profil utilizator",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }
    }
}

    private void editeaza() {
        int r = tabel.getSelectedRow();

        if (r == -1) {
            JOptionPane.showMessageDialog(this, "Selectează un utilizator!");
            return;
        }

        JTextField n = new JTextField(model.getValueAt(r, 0).toString());
        JTextField e = new JTextField(model.getValueAt(r, 1).toString());

        JPanel p = new JPanel(new GridLayout(2, 2, 10, 10));
        p.add(new JLabel("Nume nou:"));
        p.add(n);
        p.add(new JLabel("Email nou:"));
        p.add(e);

        int raspuns = JOptionPane.showConfirmDialog(
                this,
                p,
                "Editează utilizator",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (raspuns == JOptionPane.OK_OPTION) {
            model.setValueAt(n.getText(), r, 0);
            model.setValueAt(e.getText(), r, 1);
            notificari.add("Un utilizator a fost modificat.");
            updateStats();
        }
    }

    private void sterge() {
        int r = tabel.getSelectedRow();

        if (r == -1) {
            JOptionPane.showMessageDialog(this, "Selectează un utilizator!");
        } else {
            String nume = model.getValueAt(r, 0).toString();
            model.removeRow(r);
            favorite.remove(nume);
            notificari.add("Utilizatorul " + nume + " a fost șters.");
            updateStats();
        }
    }

    private void cauta() {
        String cautare = JOptionPane.showInputDialog(this, "Caută utilizator:");

        if (cautare == null || cautare.isEmpty()) return;

        for (int i = 0; i < model.getRowCount(); i++) {
            String nume = model.getValueAt(i, 0).toString();

            if (nume.toLowerCase().contains(cautare.toLowerCase())) {
                tabel.setRowSelectionInterval(i, i);
                JOptionPane.showMessageDialog(this, "Utilizator găsit: " + nume);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Utilizatorul nu a fost găsit!");
    }

    private void favorite() {
        int r = tabel.getSelectedRow();

        if (r == -1) {
            JOptionPane.showMessageDialog(this, "Selectează un utilizator!");
            return;
        }

        String nume = model.getValueAt(r, 0).toString();

        if (!favorite.contains(nume)) {
            favorite.add(nume);
            notificari.add(nume + " a fost adăugat la favorite.");
            updateStats();
        }

        JOptionPane.showMessageDialog(this, "Favorite: " + favorite);
    }

    private void notificari() {
        if (notificari.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nu există notificări.");
        } else {
            JOptionPane.showMessageDialog(this, String.join("\n", notificari));
        }
    }

    private void chat() {
        int r = tabel.getSelectedRow();

        if (r == -1) {
            JOptionPane.showMessageDialog(this, "Selectează un utilizator!");
            return;
        }

        JFrame f = new JFrame("Chat");
        f.setSize(450, 400);
        f.setLocationRelativeTo(this);

        JTextArea zona = new JTextArea("AI: Salut! Sunt asistentul UserFlow.\n\n");
        zona.setEditable(false);

        JTextField mesaj = new JTextField();
        JButton trimite = new JButton("Trimite");

        trimite.addActionListener(e -> {
            if (!mesaj.getText().isEmpty()) {
                zona.append("Tu: " + mesaj.getText() + "\n");
                zona.append("AI: Pot răspunde la întrebări simple despre aplicație.\n\n");
                mesaj.setText("");
            }
        });

        JPanel jos = new JPanel(new BorderLayout());
        jos.add(mesaj, BorderLayout.CENTER);
        jos.add(trimite, BorderLayout.EAST);

        f.add(new JScrollPane(zona), BorderLayout.CENTER);
        f.add(jos, BorderLayout.SOUTH);
        f.setVisible(true);
    }

private void setari() {
    int raspuns = JOptionPane.showConfirmDialog(
            this,
            "Dorești să activezi Dark Mode?",
            "Setări",
            JOptionPane.YES_NO_OPTION
    );

    darkMode = raspuns == JOptionPane.YES_OPTION;

    mainContainer.removeAll();
    mainContainer.add(paginaStart(), "start");
    mainContainer.add(paginaAplicatie(), "app");

    mainContainer.revalidate();
    mainContainer.repaint();

    cardLayout.show(mainContainer, "app");

    // Reafișează valorile reale după schimbarea temei
    updateStats();

    JOptionPane.showMessageDialog(
            this,
            darkMode ? "Dark Mode activat!" : "Light Mode activat!"
    );
}

    private void updateStats() {
        totalLabel.setText(String.valueOf(model.getRowCount()));
        favoriteLabel.setText(String.valueOf(favorite.size()));
        notificariLabel.setText(String.valueOf(notificari.size()));
    }

    private JPanel card() {
        JPanel p = new JPanel();
        p.setBackground(cardColor());
        p.setBorder(BorderFactory.createEmptyBorder(22, 22, 22, 22));
        return p;
    }

    private JLabel label(String text, int size, boolean bold) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", bold ? Font.BOLD : Font.PLAIN, size));
        l.setForeground(text());
        return l;
    }

    private JTextField field() {
        JTextField f = new JTextField();
        f.setMaximumSize(new Dimension(320, 40));
        f.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return f;
    }

    private JButton butonMeniu(String text) {
        JButton b = new JButton(text);
        b.setMaximumSize(new Dimension(200, 40));
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        b.setBackground(new Color(29, 78, 216));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        return b;
    }

    private void stilButon(JButton b) {
        b.setBackground(new Color(37, 99, 235));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
    }

    private Color fundal() {
        return darkMode ? new Color(8, 15, 25) : new Color(245, 247, 250);
    }

    private Color cardColor() {
        return darkMode ? new Color(17, 29, 43) : Color.WHITE;
    }

    private Color text() {
        return darkMode ? Color.WHITE : new Color(25, 35, 50);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AplicatieInterfata().setVisible(true));
    }
}
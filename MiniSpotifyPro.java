import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MiniSpotifyPro extends JFrame {

    Color verde = new Color(30, 215, 96);
    Color fundal = new Color(18, 18, 18);
    Color card = new Color(35, 35, 35);

    String utilizator = "";
    String melodieCurenta = "Nicio melodie selectată";

    ArrayList<String> playlist = new ArrayList<>();
    ArrayList<String> favorite = new ArrayList<>();

    JTextArea continut;

    public MiniSpotifyPro(String nume) {
        utilizator = nume;

        setTitle("Mini Spotify Pro");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel sidebar = new JPanel(new GridLayout(8, 1, 10, 10));
        sidebar.setBackground(Color.BLACK);
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        JLabel logo = new JLabel(" Spotify Pro");
        logo.setForeground(verde);
        logo.setFont(new Font("Arial", Font.BOLD, 23));

        JButton home = button("Home");
        JButton player = button("Player");
        JButton search = button("Caută");
        JButton playlistBtn = button("Playlist");
        JButton favBtn = button("Favorite");
        JButton profilBtn = button("Profil utilizator");
        JButton settings = button("Setări");

        sidebar.add(logo);
        sidebar.add(home);
        sidebar.add(player);
        sidebar.add(search);
        sidebar.add(playlistBtn);
        sidebar.add(favBtn);
        sidebar.add(profilBtn);
        sidebar.add(settings);

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(fundal);
        main.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel title = new JLabel("Bun venit, " + utilizator);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 30));

        continut = new JTextArea();
        continut.setText("Aceasta este aplicația Mini Spotify Pro.\n\nFuncționalități:\n- Player muzical\n- Căutare melodii\n- Playlist\n- Favorite\n- Profil utilizator\n- Setări");
        continut.setFont(new Font("Arial", Font.PLAIN, 18));
        continut.setForeground(Color.WHITE);
        continut.setBackground(card);
        continut.setEditable(false);
        continut.setLineWrap(true);
        continut.setWrapStyleWord(true);
        continut.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        main.add(title, BorderLayout.NORTH);
        main.add(continut, BorderLayout.CENTER);

        add(sidebar, BorderLayout.WEST);
        add(main, BorderLayout.CENTER);

        home.addActionListener(e -> afiseazaHome());
        player.addActionListener(e -> new PlayerWindow());
        search.addActionListener(e -> new SearchWindow());
        playlistBtn.addActionListener(e -> new PlaylistWindow());
        favBtn.addActionListener(e -> new FavoriteWindow());
        profilBtn.addActionListener(e -> new ProfileWindow());
        settings.addActionListener(e -> new SettingsWindow(title));

        setVisible(true);
    }

    JButton button(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 15));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(25, 25, 25));
        btn.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(verde);
                btn.setForeground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(25, 25, 25));
                btn.setForeground(Color.WHITE);
            }
        });

        return btn;
    }

    void afiseazaHome() {
        continut.setText(
                "Home\n\nMelodii recomandate:\n\n" +
                "1. The Weeknd - Blinding Lights\n" +
                "2. Ed Sheeran - Shape of You\n" +
                "3. Imagine Dragons - Believer\n" +
                "4. Dua Lipa - Levitating\n" +
                "5. Coldplay - Yellow"
        );
    }

    class PlayerWindow extends JFrame {
        public PlayerWindow() {
            setTitle("Player muzical");
            setSize(450, 300);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(5, 1, 10, 10));
            getContentPane().setBackground(fundal);

            JLabel titlu = new JLabel("Player muzical", SwingConstants.CENTER);
            titlu.setForeground(Color.WHITE);
            titlu.setFont(new Font("Arial", Font.BOLD, 24));

            JLabel melodie = new JLabel("Melodie: " + melodieCurenta, SwingConstants.CENTER);
            melodie.setForeground(verde);
            melodie.setFont(new Font("Arial", Font.BOLD, 16));

            JPanel controls = new JPanel(new GridLayout(1, 5, 8, 8));
            controls.setBackground(fundal);

            JButton prev = button("Prev");
            JButton play = button("Play");
            JButton pause = button("Pause");
            JButton stop = button("Stop");
            JButton next = button("Next");

            controls.add(prev);
            controls.add(play);
            controls.add(pause);
            controls.add(stop);
            controls.add(next);

            play.addActionListener(e -> JOptionPane.showMessageDialog(this, "Redare pornită: " + melodieCurenta));
            pause.addActionListener(e -> JOptionPane.showMessageDialog(this, "Melodia a fost pusă pe pauză."));
            stop.addActionListener(e -> JOptionPane.showMessageDialog(this, "Redarea a fost oprită."));

            next.addActionListener(e -> {
                melodieCurenta = "Dua Lipa - Levitating";
                melodie.setText("Melodie: " + melodieCurenta);
            });

            prev.addActionListener(e -> {
                melodieCurenta = "The Weeknd - Blinding Lights";
                melodie.setText("Melodie: " + melodieCurenta);
            });

            add(titlu);
            add(melodie);
            add(controls);

            setVisible(true);
        }
    }

    class SearchWindow extends JFrame {
        public SearchWindow() {
            setTitle("Caută melodii");
            setSize(500, 330);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());
            getContentPane().setBackground(fundal);

            JLabel title = new JLabel("Caută o melodie", SwingConstants.CENTER);
            title.setForeground(Color.WHITE);
            title.setFont(new Font("Arial", Font.BOLD, 24));

            JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
            panel.setBackground(fundal);
            panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 50));

            JTextField field = new JTextField();
            JButton cauta = button("Caută");
            JButton addPlaylist = button("Adaugă în playlist");
            JButton addFavorite = button("Adaugă la favorite");
            JLabel rezultat = new JLabel("", SwingConstants.CENTER);
            rezultat.setForeground(verde);

            final String[] melodieGasita = {""};

            cauta.addActionListener(e -> {
                if (field.getText().isEmpty()) {
                    rezultat.setText("Introdu numele unei melodii.");
                } else {
                    melodieGasita[0] = field.getText();
                    melodieCurenta = melodieGasita[0];
                    rezultat.setText("Melodia găsită: " + melodieGasita[0]);
                }
            });

            addPlaylist.addActionListener(e -> {
                if (!melodieGasita[0].isEmpty()) {
                    playlist.add(melodieGasita[0]);
                    JOptionPane.showMessageDialog(this, "Melodia a fost adăugată în playlist.");
                }
            });

            addFavorite.addActionListener(e -> {
                if (!melodieGasita[0].isEmpty()) {
                    favorite.add(melodieGasita[0]);
                    JOptionPane.showMessageDialog(this, "Melodia a fost adăugată la favorite.");
                }
            });

            panel.add(field);
            panel.add(cauta);
            panel.add(addPlaylist);
            panel.add(addFavorite);
            panel.add(rezultat);

            add(title, BorderLayout.NORTH);
            add(panel, BorderLayout.CENTER);

            setVisible(true);
        }
    }

    class PlaylistWindow extends JFrame {
        public PlaylistWindow() {
            setTitle("Playlist");
            setSize(500, 350);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            DefaultListModel<String> model = new DefaultListModel<>();
            for (String m : playlist) model.addElement(m);

            JList<String> lista = new JList<>(model);
            lista.setBackground(card);
            lista.setForeground(Color.WHITE);
            lista.setFont(new Font("Arial", Font.PLAIN, 16));

            JTextField field = new JTextField();
            JButton add = button("Adaugă");
            JButton remove = button("Șterge");

            JPanel jos = new JPanel(new GridLayout(1, 3, 10, 10));
            jos.setBackground(fundal);
            jos.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

            jos.add(field);
            jos.add(add);
            jos.add(remove);

            add.addActionListener(e -> {
                if (!field.getText().isEmpty()) {
                    playlist.add(field.getText());
                    model.addElement(field.getText());
                    field.setText("");
                }
            });

            remove.addActionListener(e -> {
                int index = lista.getSelectedIndex();
                if (index != -1) {
                    playlist.remove(index);
                    model.remove(index);
                }
            });

            add(new JScrollPane(lista), BorderLayout.CENTER);
            add(jos, BorderLayout.SOUTH);

            setVisible(true);
        }
    }

    class FavoriteWindow extends JFrame {
        public FavoriteWindow() {
            setTitle("Favorite");
            setSize(450, 300);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            DefaultListModel<String> model = new DefaultListModel<>();
            for (String m : favorite) model.addElement(m);

            JList<String> lista = new JList<>(model);
            lista.setBackground(card);
            lista.setForeground(Color.WHITE);
            lista.setFont(new Font("Arial", Font.PLAIN, 16));

            JButton remove = button("Șterge din favorite");

            remove.addActionListener(e -> {
                int index = lista.getSelectedIndex();
                if (index != -1) {
                    favorite.remove(index);
                    model.remove(index);
                }
            });

            add(new JScrollPane(lista), BorderLayout.CENTER);
            add(remove, BorderLayout.SOUTH);

            setVisible(true);
        }
    }

    class ProfileWindow extends JFrame {
        public ProfileWindow() {
            setTitle("Profil utilizator");
            setSize(450, 300);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(5, 1, 10, 10));
            getContentPane().setBackground(fundal);

            JLabel title = new JLabel("Profil utilizator", SwingConstants.CENTER);
            title.setForeground(verde);
            title.setFont(new Font("Arial", Font.BOLD, 24));

            JLabel nume = new JLabel("Nume utilizator: " + utilizator, SwingConstants.CENTER);
            JLabel nrPlaylist = new JLabel("Melodii în playlist: " + playlist.size(), SwingConstants.CENTER);
            JLabel nrFavorite = new JLabel("Melodii favorite: " + favorite.size(), SwingConstants.CENTER);
            JLabel curenta = new JLabel("Melodie curentă: " + melodieCurenta, SwingConstants.CENTER);

            nume.setForeground(Color.WHITE);
            nrPlaylist.setForeground(Color.WHITE);
            nrFavorite.setForeground(Color.WHITE);
            curenta.setForeground(Color.WHITE);

            nume.setFont(new Font("Arial", Font.PLAIN, 16));
            nrPlaylist.setFont(new Font("Arial", Font.PLAIN, 16));
            nrFavorite.setFont(new Font("Arial", Font.PLAIN, 16));
            curenta.setFont(new Font("Arial", Font.PLAIN, 16));

            add(title);
            add(nume);
            add(nrPlaylist);
            add(nrFavorite);
            add(curenta);

            setVisible(true);
        }
    }

    class SettingsWindow extends JFrame {
        public SettingsWindow(JLabel titleMain) {
            setTitle("Setări");
            setSize(400, 260);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(4, 1, 10, 10));
            getContentPane().setBackground(fundal);

            JButton dark = button("Dark Mode");
            JButton light = button("Light Mode");
            JButton reset = button("Resetare playlist");
            JButton changeName = button("Schimbă numele");

            dark.addActionListener(e -> {
                continut.setBackground(card);
                continut.setForeground(Color.WHITE);
            });

            light.addActionListener(e -> {
                continut.setBackground(Color.WHITE);
                continut.setForeground(Color.BLACK);
            });

            reset.addActionListener(e -> {
                playlist.clear();
                JOptionPane.showMessageDialog(this, "Playlistul a fost resetat.");
            });

            changeName.addActionListener(e -> {
                String numeNou = JOptionPane.showInputDialog("Introdu noul nume:");
                if (numeNou != null && !numeNou.isEmpty()) {
                    utilizator = numeNou;
                    titleMain.setText("Bun venit, " + utilizator);
                    JOptionPane.showMessageDialog(this, "Numele a fost schimbat în: " + utilizator);
                }
            });

            add(dark);
            add(light);
            add(reset);
            add(changeName);

            setVisible(true);
        }
    }

    public static void main(String[] args) {
        JFrame login = new JFrame("Login");
        login.setSize(400, 250);
        login.setLocationRelativeTo(null);
        login.setDefaultCloseOperation(EXIT_ON_CLOSE);
        login.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel title = new JLabel("Autentificare Mini Spotify Pro", SwingConstants.CENTER);
        JTextField userField = new JTextField();
        JButton loginBtn = new JButton("Login");

        title.setFont(new Font("Arial", Font.BOLD, 20));

        login.add(title);
        login.add(new JLabel("Introdu numele utilizatorului:", SwingConstants.CENTER));
        login.add(userField);
        login.add(loginBtn);

        loginBtn.addActionListener(e -> {
            if (!userField.getText().isEmpty()) {
                login.dispose();
                new MiniSpotifyPro(userField.getText());
            } else {
                JOptionPane.showMessageDialog(login, "Introdu un nume pentru autentificare.");
            }
        });

        login.setVisible(true);
    }
}
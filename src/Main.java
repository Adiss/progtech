import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Main extends JPanel {

    private static final Random random = new Random();
    private static final JPanel cards = new JPanel(new CardLayout());
    private final String name;
    private static UzletiLogika logika = new UzletiLogika("jdbc:mysql://127.0.0.1:3306/maid-db", "root", "parro");

    public Main(String name) {
        this.name = name;
        this.setPreferredSize(new Dimension(300, 250));
        this.setBackground(new Color(random.nextInt()));

        if(name == "Login"){
            JLabel title = new JLabel(name);
            JButton button = new JButton("Login");
            JTextField userInput = new JTextField("Username");
            JPasswordField pwInput = new JPasswordField("Password");
            final JList list = new JList();

            JPanel gui = new JPanel(new BorderLayout(4,5));
            gui.setBorder(new EmptyBorder(5,5,5,5));
            this.add(gui);
            JPanel labels = new JPanel(new GridLayout(0,1));
            JPanel controls = new JPanel(new GridLayout(0,1));
            gui.add(title, BorderLayout.NORTH);
            gui.add(labels, BorderLayout.WEST);
            gui.add(controls, BorderLayout.CENTER);

            labels.add(new JLabel("Username: "));
            controls.add(userInput);
            labels.add(new JLabel("Password: "));
            controls.add(pwInput);
            gui.add(list, BorderLayout.SOUTH);
            gui.add(button, BorderLayout.EAST);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    list.setListData(logika.login(userInput.getText(), new String(pwInput.getPassword())).toArray());
                }
            });
        } else {
            JLabel title = new JLabel(name);
            JButton button = new JButton("Register");
            JTextField userInput = new JTextField("Username");
            JPasswordField pwInput = new JPasswordField("Password");
            JPasswordField pwInput2 = new JPasswordField("Password2");
            JTextField emailInput = new JTextField("E-mail");
            final JList list = new JList();

            JPanel gui = new JPanel(new BorderLayout(4,5));
            gui.setBorder(new EmptyBorder(5,5,5,5));
            this.add(gui);
            JPanel labels = new JPanel(new GridLayout(0,1));
            JPanel controls = new JPanel(new GridLayout(0,1));
            gui.add(title, BorderLayout.NORTH);
            gui.add(labels, BorderLayout.WEST);
            gui.add(controls, BorderLayout.CENTER);

            labels.add(new JLabel("Username: "));
            controls.add(userInput);
            labels.add(new JLabel("Password: "));
            controls.add(pwInput);
            labels.add(new JLabel("Password2: "));
            controls.add(pwInput2);
            labels.add(new JLabel("E-mail: "));
            controls.add(emailInput);
            gui.add(list, BorderLayout.SOUTH);
            gui.add(button, BorderLayout.EAST);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    list.setListData(logika.register(userInput.getText(), new String(pwInput.getPassword()), new String(pwInput2.getPassword()), emailInput.getText()).toArray());
                }
            });
        }

    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                create();
            }
        });
    }

    private static void create() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Main login = new Main("Login");
        cards.add(login, login.toString());
        Main register = new Main("Register");
        cards.add(register, register.toString());

        JPanel control = new JPanel();
        control.add(new JButton(new AbstractAction("Login") {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) cards.getLayout();
                cl.first(cards);
            }
        }));
        control.add(new JButton(new AbstractAction("Register") {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) cards.getLayout();
                cl.last(cards);
            }
        }));
        f.add(cards, BorderLayout.CENTER);
        f.add(control, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClientInterface extends JFrame {

    private JTextField idField;
    private JButton addButton;
    private DefaultListModel<String> model;
    private JList<String> clientList;
    private JTabbedPane tabbedPane;
    private SocketClient.Client client;

    public ClientInterface() {
        setTitle("Gestion des Clients");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        idField = new JTextField(15);
        addButton = new JButton("Ajouter");

        topPanel.add(new JLabel("ID Client: "));
        topPanel.add(idField);
        topPanel.add(addButton);

        add(topPanel, BorderLayout.NORTH);


        model = new DefaultListModel<>();
        clientList = new JList<>(model);


        tabbedPane = new JTabbedPane();


        JSplitPane splitPane = new JSplitPane();
        splitPane.setLeftComponent(new JScrollPane(clientList));
        splitPane.setRightComponent(tabbedPane);

        add(splitPane, BorderLayout.CENTER);


        addButton.addActionListener(e -> {
            String id = idField.getText().trim();
            if (!id.isEmpty()) {
                model.addElement(id);
                idField.setText("");
                try {

                    client = new SocketClient.Client("127.0.0.1", 9003, id);
                    client.startReading();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erreur connexion serveur: " + ex.getMessage());
                }

            } else {
                JOptionPane.showMessageDialog(null, "Entrer un ID !");
            }
        });


        clientList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String id = clientList.getSelectedValue();
                    int index = tabbedPane.indexOfTab(id);

                    if (index == -1) {
                        JPanel panel = createClientPanel(id);
                        tabbedPane.addTab(id, panel);
                        tabbedPane.setSelectedComponent(panel);
                    } else {
                        tabbedPane.setSelectedIndex(index);
                    }
                }
            }
        });
    }


    private JPanel createClientPanel(String clientId) {
        JPanel panel = new JPanel(new BorderLayout());


        JTextArea messagesArea = new JTextArea();
        messagesArea.setEditable(false);
        panel.add(new JScrollPane(messagesArea), BorderLayout.CENTER);


        JPanel sendPanel = new JPanel(new FlowLayout());
        JTextField tfReceiver = new JTextField(8);
        JTextField tfMessage = new JTextField(25);
        JButton btnSend = new JButton("Envoyer");

        sendPanel.add(new JLabel("ID Récepteur (ou all):"));
        sendPanel.add(tfReceiver);
        sendPanel.add(tfMessage);
        sendPanel.add(btnSend);

        panel.add(sendPanel, BorderLayout.SOUTH);

        btnSend.addActionListener(e -> {
            String receiver = tfReceiver.getText().trim();
            String message = tfMessage.getText().trim();

            if (receiver.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Entrer ID ou all !");
                return;
            }

            if (message.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Message obligatoire !");
                return;
            }


            tfMessage.setText("");
        });

        return panel;
    }

    public static void main(String[] args) {
        new ClientInterface().setVisible(true);
    }
}
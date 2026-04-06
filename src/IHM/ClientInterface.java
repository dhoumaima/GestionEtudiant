package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.ArrayList;
import SocketClient.Client;

public class ClientInterface extends JFrame {

    private JTextField idField;
    private JButton addButton;
    private DefaultListModel<String> model;
    private JList<String> clientList;
    private JTabbedPane tabbedPane;
    private HashMap<String, Client> clientPerTab = new HashMap<>();
    private HashMap<String, ArrayList<String>> messageHistory = new HashMap<>();

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
                if (!model.contains(id)) {
                    model.addElement(id);
                }
                idField.setText("");
                if (!clientPerTab.containsKey(id)) {
                    try {
                        Client newClient = new Client("127.0.0.1", 9003, id);
                        clientPerTab.put(id, newClient);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erreur connexion serveur: " + ex.getMessage());
                    }
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

        Client client = clientPerTab.get(clientId);
        ArrayList<String> history = messageHistory.getOrDefault(clientId, new ArrayList<>());

        JTextArea messagesArea = new JTextArea();
        messagesArea.setEditable(false);

        for (String msg : history) {
            messagesArea.append(msg + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(messagesArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        client.startReading(newMessage -> {
            if (!messageHistory.containsKey(clientId)) {
                messageHistory.put(clientId, new ArrayList<>());
            }
            messageHistory.get(clientId).add(newMessage);
            messagesArea.append(newMessage + "\n");
        });

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
            if (receiver.isEmpty() || message.isEmpty()) return;

            if (!messageHistory.containsKey(clientId)) {
                messageHistory.put(clientId, new ArrayList<>());
            }

            if (receiver.equalsIgnoreCase("all")) {
                client.sendMessage("@all" + message);
                messageHistory.get(clientId).add("Moi -> Tous : " + message);
                messagesArea.append("Moi -> Tous : " + message + "\n");
            } else {
                client.sendMessage("@" + receiver + ":" + message);
                messageHistory.get(clientId).add("Moi -> " + receiver + " : " + message);
                messagesArea.append("Moi -> " + receiver + " : " + message + "\n");
            }
            tfMessage.setText("");
        });

        return panel;
    }

    public static void main(String[] args) {
        new ClientInterface().setVisible(true);
    }
}
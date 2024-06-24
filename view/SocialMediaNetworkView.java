package view;

import controller.SocialMediaNetworkController;
import model.Content;
import model.Node;
import model.SocialMediaNetworkManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class SocialMediaNetworkView extends JFrame {
    private final SocialMediaNetworkManagement management;
    private final LoginPage loginPage;
    private final DetailContentView detailContentView;
    private JLabel currentUserLabel;
    private JLabel quantityFriend;
    private JTable friendTable;
    private JComboBox<String> titleComboBox;
    private JTextArea contentJTextArea;
    private JTable newsTable;
    private JLabel quantityRequest;
    private JTable requestTable;
    private JTable suggestTable;
    private JLabel mainLabel;
    private ImageIcon imageIcon1;
    private ImageIcon imageIcon2;
    private JButton deleteButton;
    private JButton postButton;
    private JButton detailButton;
    private JButton acceptButton;
    private JButton declineButton;
    private JButton addButton;
    private JScrollPane friendScrollPane;
    private JScrollPane contentScrollPane;
    private JScrollPane newsScrollPane;
    private JScrollPane requestScrollPane;
    private JScrollPane suggestScrollPane;
    private JScrollPane searchScrollPane;
    private JButton addFriendButton;
    private JButton cancelButton;
    private JTable searchTable;
    private JTextField searchFriendTextField;
    private JLabel avatarLabel;
    private ImageIcon maleAvatar;
    private ImageIcon femaleAvatar;


    public SocialMediaNetworkView(LoginPage loginPage) {
        this.loginPage = loginPage;
        this.management = new SocialMediaNetworkManagement();
        this.detailContentView = new DetailContentView();
        init();

        getRootPane().setGlassPane(new JComponent() {
            public void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        });
    }

    public void turningBlack(boolean status) {
        getRootPane().getGlassPane().setVisible(status);
    }

    private void init() {
        this.setTitle("Social Media Network");
        this.setSize(1100, 650);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(new BorderLayout());
        this.setLayout(null);

        Font font = new Font("Arial", Font.BOLD, 15);

//        Image Icon
        ImageIcon icon = new ImageIcon("src\\image\\Java-icon.png");
        this.setIconImage(icon.getImage());

//        Color of background
//        this.getContentPane().setBackground(new Color(123, 50, 250));

//        ActionListener - Controller
        SocialMediaNetworkController actionListener = new SocialMediaNetworkController(this);

        //////////////////////////////////////////////////////
        mainLabel = new JLabel();
        imageIcon1 = new ImageIcon("src\\image\\FacebookBackground12.png");
        imageIcon2 = new ImageIcon("src\\image\\FacebookBackground8.png");
        mainLabel.setIcon(imageIcon1);
        mainLabel.setBounds(0, 0, 1100, 650);

        avatarLabel = new JLabel();
        maleAvatar = new ImageIcon("src\\image\\Male1.png");
        femaleAvatar = new ImageIcon("src\\image\\Female1.png");
        avatarLabel.setBounds(20, 60, 100, 100);
        this.add(avatarLabel);

        currentUserLabel = new JLabel();
        currentUserLabel.setBounds(130, 100, 130, 35);
        currentUserLabel.setFont(new Font("Arial", Font.BOLD, 20));
        currentUserLabel.setForeground(Color.BLUE);
        this.add(currentUserLabel);

        quantityFriend = new JLabel();
        quantityFriend.setBounds(140, 235, 75, 25);
        quantityFriend.setFont(font);
        this.add(quantityFriend);

        friendTable = new JTable();
        friendTable.setModel(new DefaultTableModel(
                new Object[][]{
                }, new String[]{
                "List"
        }
        ));
        friendTable.setRowHeight(30);
        friendScrollPane = new JScrollPane(friendTable);
        friendScrollPane.setBounds(20, 270, 225, 200);

        this.add(friendScrollPane);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(20, 480, 225, 40);
        deleteButton.setFont(font);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(Color.GRAY);
        deleteButton.addActionListener(actionListener);
        this.add(deleteButton);


        searchFriendTextField = new JTextField();
        searchFriendTextField.setBounds(275, 10, 350, 30);
        this.add(searchFriendTextField);

        JButton searchFriendButton = new JButton("Search");
        searchFriendButton.setBounds(630, 10, 100, 30);
        searchFriendButton.setFont(font);
        searchFriendButton.setForeground(Color.WHITE);
        searchFriendButton.setBackground(Color.BLUE);
        searchFriendButton.addActionListener(actionListener);
        this.add(searchFriendButton);

        searchTable = new JTable();
        searchTable.setModel(new DefaultTableModel(
                new Object[][]{
                }, new String[]{
                "Account", "Gender", "Home Town", "Birthday", "Mutual friend", "Friend"
        }
        ));
        searchTable.setRowHeight(30);
        searchScrollPane = new JScrollPane(searchTable);
        searchScrollPane.setBounds(290, 85, 750, 450);

        addFriendButton = new JButton("Add friend");
        addFriendButton.setBounds(290, 550, 360, 30);
        addFriendButton.setFont(font);
        addFriendButton.setForeground(Color.WHITE);
        addFriendButton.setBackground(Color.BLUE);
        addFriendButton.addActionListener(actionListener);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(680, 550, 360, 30);
        cancelButton.setFont(font);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(Color.RED);
        cancelButton.addActionListener(actionListener);


        titleComboBox = new JComboBox<>();
        titleComboBox.setBounds(400, 115, 150, 30);
        titleComboBox.addItem("Game");
        titleComboBox.addItem("Music");
        titleComboBox.addItem("Health");
        titleComboBox.addItem("Job");
        this.add(titleComboBox);

        contentJTextArea = new JTextArea();
        contentScrollPane = new JScrollPane(contentJTextArea);
        contentScrollPane.setBounds(400, 160, 300, 30);
        this.add(contentScrollPane);

        postButton = new JButton("Post");
        postButton.setBounds(720, 160, 100, 30);
        postButton.setFont(font);
        postButton.setForeground(Color.WHITE);
        postButton.setBackground(Color.BLUE);
        postButton.addActionListener(actionListener);
        this.add(postButton);

        newsTable = new JTable();
        newsTable.setModel(new DefaultTableModel(
                new Object[][]{
                }, new String[]{
                "Notifications"
        }
        ));
        newsTable.setRowHeight(90);

        newsScrollPane = new JScrollPane(newsTable);
        newsScrollPane.setBounds(273, 225, 560, 350);
        this.add(newsScrollPane);


        detailButton = new JButton("Detail");
        detailButton.setBounds(280, 575, 545, 30);
        detailButton.setFont(font);
        detailButton.setForeground(Color.WHITE);
        detailButton.setBackground(Color.BLUE);
        detailButton.addActionListener(actionListener);
        this.add(detailButton);

        JButton logOutButton = new JButton("Log out");
        logOutButton.setBounds(910, 10, 100, 30);
        logOutButton.setFont(font);
        logOutButton.setForeground(Color.WHITE);
        logOutButton.setBackground(Color.BLUE);
        logOutButton.addActionListener(actionListener);
        this.add(logOutButton);

        quantityRequest = new JLabel();
        quantityRequest.setBounds(930, 70, 75, 25);
        quantityRequest.setFont(font);
        this.add(quantityRequest);

        requestTable = new JTable();
        requestTable.setModel(new DefaultTableModel(
                new Object[][]{
                }, new String[]{
                "List"
        }
        ));
        requestTable.setRowHeight(30);
        requestScrollPane = new JScrollPane(requestTable);
        requestScrollPane.setBounds(850, 105, 225, 200);
        this.add(requestScrollPane);

        acceptButton = new JButton("Accept");
        acceptButton.setBounds(850, 310, 100, 30);
        acceptButton.setFont(font);
        acceptButton.setForeground(Color.WHITE);
        acceptButton.setBackground(Color.BLUE);
        acceptButton.addActionListener(actionListener);
        this.add(acceptButton);

        declineButton = new JButton("Decline");
        declineButton.setBounds(975, 310, 100, 30);
        declineButton.setFont(font);
        declineButton.setForeground(Color.WHITE);
        declineButton.setBackground(Color.GRAY);
        declineButton.addActionListener(actionListener);
        this.add(declineButton);

        suggestTable = new JTable();
        suggestTable.setModel(new DefaultTableModel(
                new Object[][]{
                }, new String[]{
                "List"
        }
        ));
        suggestTable.setRowHeight(30);
        suggestScrollPane = new JScrollPane(suggestTable);
        suggestScrollPane.setBounds(850, 412, 225, 160);
        this.add(suggestScrollPane);

        addButton = new JButton("Add");
        addButton.setBounds(850, 575, 225, 30);
        addButton.setFont(font);
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.BLUE);
        addButton.addActionListener(actionListener);
        this.add(addButton);


        this.add(mainLabel);
    }

    public void addUser(Node user) {
        //Node user = new Node(name);
        this.management.createUser(user);
    }

    public void changeUser(int userId, String username) {
        this.currentUserLabel.setText(username);
        this.management.choiceUser(userId);
        if (this.management.getCurrentUser().getGender().equals("Male")) {
            this.avatarLabel.setIcon(this.maleAvatar);
        } else {
            this.avatarLabel.setIcon(this.femaleAvatar);
        }

        updateRequest();
        updateSuggest();
        updateFriend();
        updateNews();
    }

    private void updateNews() {
        DefaultTableModel model = (DefaultTableModel) newsTable.getModel();
        model.setRowCount(0);

        List<Content> listContent = this.management.getListContent();
        for (int i = listContent.size() - 1; i >= 0; i--) {
            this.addContentIntoTable(listContent.get(i));
        }
    }

    public void cancel() {
        this.remove(this.searchScrollPane);
        this.remove(this.addFriendButton);
        this.remove(this.cancelButton);
        this.remove(this.mainLabel);

        this.add(this.quantityFriend);
        this.add(this.friendScrollPane);
        this.add(this.deleteButton);
        this.add(this.titleComboBox);
        this.add(this.contentScrollPane);
        this.add(this.postButton);
        this.add(this.newsScrollPane);
        this.add(this.detailButton);
        this.add(this.quantityRequest);
        this.add(this.requestScrollPane);
        this.add(this.acceptButton);
        this.add(this.declineButton);
        this.add(this.suggestScrollPane);
        this.add(this.addButton);

        this.mainLabel.setIcon(imageIcon1);
        this.add(mainLabel);

        SwingUtilities.updateComponentTreeUI(this);
    }

    public void getIdFriend() {
        DefaultTableModel model = (DefaultTableModel) this.suggestTable.getModel();
        int selectRow = suggestTable.getSelectedRow();
        if (selectRow == -1) {
            return;
        }

        int id = Integer.parseInt(model.getValueAt(selectRow, 0).toString().split(" - ")[1]);
        this.management.addRequest(id);
    }

    public void updateRequest() {
        List<Node> list = this.management.getListRequest();
        this.quantityRequest.setText(list.size() + "");

        DefaultTableModel model = (DefaultTableModel) requestTable.getModel();
        model.setRowCount(0);
        for (Node node : list) {
            model.addRow(
                    new Object[]{
                            node.toString()
                    }
            );
        }
    }

    public void acceptFriend() {
        DefaultTableModel model = (DefaultTableModel) this.requestTable.getModel();
        int selectRow = requestTable.getSelectedRow();
        if (selectRow == -1) {
            return;
        }

        String[] user = model.getValueAt(selectRow, 0).toString().split(" - ");

        int id = Integer.parseInt(user[user.length - 1]);
        this.management.addFriend(id);
        this.management.removeRequest(id);

        updateRequest();
        updateSuggest();
        updateFriend();
        updateNews();
    }

    public void updateFriend() {
        List<Node> list = this.management.getListFriend();
        this.quantityFriend.setText(list.size() + "");

        DefaultTableModel model = (DefaultTableModel) friendTable.getModel();
        model.setRowCount(0);
        for (Node node : list) {
            model.addRow(
                    new Object[]{
                            node.toString()
                    }
            );
        }
    }

    public void refuseFriend() {
        DefaultTableModel model = (DefaultTableModel) this.requestTable.getModel();
        int selectRow = requestTable.getSelectedRow();
        if (selectRow == -1) {
            return;
        }

        String[] user = model.getValueAt(selectRow, 0).toString().split(" - ");

        int id = Integer.parseInt(user[user.length - 1]);
        this.management.removeRequest(id);
        updateRequest();
    }

    public void removeFriend() {
        DefaultTableModel model = (DefaultTableModel) this.friendTable.getModel();
        int selectRow = friendTable.getSelectedRow();
        if (selectRow == -1) {
            return;
        }

        String[] user = model.getValueAt(selectRow, 0).toString().split(" - ");

        int id = Integer.parseInt(user[user.length - 1]);
        this.management.removeFriend(id);

        updateFriend();
        updateSuggest();
        updateNews();
    }

    public void addContent() {
        String title = Objects.requireNonNull(this.titleComboBox.getSelectedItem()).toString();
        String data = this.contentJTextArea.getText();

        if (data.equals("")) {
            return;
        }

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        int idContent = this.management.getCurrentUser().getContentList().size();
        Content content = new Content(this.management.getCurrentUser(), title, data, idContent, timeStamp);

        this.management.addContent(content);
        updateNews();
    }

    private void addContentIntoTable(Content content) {
        DefaultTableModel model = (DefaultTableModel) this.newsTable.getModel();
        String str = "<html>" +
                content.getUser().toString() + " - " + content.getId() + "<br/>" +
                "has create a new content with the title " + content.getTitle() + "<br/>" +
                "<br/>" +
                content.getTimeStamp() + "<br/>" +
                "<html>";

        model.addRow(
                new Object[]{
                        str
                });
    }

    public String getContentFromTable() {
        DefaultTableModel model = (DefaultTableModel) this.newsTable.getModel();
        int selectRow = newsTable.getSelectedRow();
        if (selectRow == -1) {
            return null;
        }
        String str = model.getValueAt(selectRow, 0).toString();
        int end = str.lastIndexOf("<html>");

        String[] strings = str.substring(6, end - 5).split("<br/>");
        return strings[0];
    }

    public void saveInteract(String userContent, boolean isLike, String comment) {
        int idUser = Integer.parseInt(userContent.split(" - ")[1]);
        int idContent = Integer.parseInt(userContent.split(" - ")[2]);

        if (isLike) {
            this.management.getListUser().get(idUser).getContentList().get(idContent)
                    .addLike(this.management.getCurrentUser());
        }
        if (!comment.equals("")) {
            this.management.getListUser().get(idUser).getContentList().get(idContent)
                    .addComment(this.management.getCurrentUser(), comment);
        }
        this.updateNews();
    }

    public void newWindowDetail() {
        String data = this.getContentFromTable();
        if (data == null) {
            return;
        }
        System.out.println(data);
        int idUser = Integer.parseInt(data.split(" - ")[1]);
        int idContent = Integer.parseInt(data.split(" - ")[2]);
        Content content = this.management.getListUser().get(idUser).getContentList().get(idContent);
        this.detailContentView.setData(data, content.getTitle(),
                content.getData(), content.getStrLike(), content.getStrComment(),
                content.getTimeStamp(), this, content.getComment());
        this.detailContentView.refresh();

        this.detailContentView.setVisibleView(true);

        this.turningBlack(true);
    }

    public void showSearchFriend() {
        String name = this.searchFriendTextField.getText();
        if (name.equals("")) {
            return;
        }
        ArrayList<Node> listSearch = this.management.getSearchFriend(name, 10);
        DefaultTableModel model = (DefaultTableModel) searchTable.getModel();
        model.setRowCount(0);
        for (Node search : listSearch) {
            addUserIntoTable(search,
                    this.management.getMutualFriends(
                            this.management.getCurrentUser().getId(), search.getId()).size(),
                    this.management.isFriend(search.getId()));
        }

        this.remove(this.quantityFriend);
        this.remove(this.friendScrollPane);
        this.remove(this.deleteButton);
        this.remove(this.titleComboBox);
        this.remove(this.contentScrollPane);
        this.remove(this.postButton);
        this.remove(this.newsScrollPane);
        this.remove(this.detailButton);
        this.remove(this.quantityRequest);
        this.remove(this.requestScrollPane);
        this.remove(this.acceptButton);
        this.remove(this.declineButton);
        this.remove(this.suggestScrollPane);
        this.remove(this.addButton);
        this.remove(this.mainLabel);

        this.add(this.searchScrollPane);
        this.add(this.addFriendButton);
        this.add(this.cancelButton);

        this.mainLabel.setIcon(imageIcon2);
        this.add(mainLabel);

        SwingUtilities.updateComponentTreeUI(this);
    }

    private void addUserIntoTable(Node user, int mutualFriend, boolean isFriend) {
        DefaultTableModel model = (DefaultTableModel) this.searchTable.getModel();
        model.addRow(
                new Object[]{
                        user.toString(),
                        user.getGender(),
                        user.getHomeTown(),
                        user.getDateOfBirth(),
                        mutualFriend,
                        isFriend
                }
        );
    }

    public void logOut() {
//        this.saveFileData();
//        this.loginPage.saveDataInfo();

        this.setVisible(false);
        this.loginPage.setVisibleView(true);
    }

    public void updateSuggest() {
        ArrayList<Node> list = this.management.getSuggestFriend(this.management.getCurrentUser().getId(), 10);
        DefaultTableModel model = (DefaultTableModel) this.suggestTable.getModel();
        model.setRowCount(0);

        for (Node node : list) {
            model.addRow(
                    new Object[]{
                            node.toString()
                    }
            );
        }
    }

    public void saveFileData() {
        try {
            // save file list user data
            ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(
                    this.management.getFileNameListUser()));
            oos1.writeObject(this.management.getListUser());

            // save file user friend data
            ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(
                    this.management.getFileNameFriend()));
            oos2.writeObject(this.management.getGraphFriend().getAlist());

            // save file user request data
            ObjectOutputStream oos3 = new ObjectOutputStream(new FileOutputStream(
                    this.management.getFileNameRequest()));
            oos3.writeObject(this.management.getGraphRequest().getAlist());

            // save file username data
            ObjectOutputStream oos4 = new ObjectOutputStream(new FileOutputStream(
                    this.management.getFileNameUsername()));
            oos4.writeObject(this.management.getListUsername());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openFileData() {
        try {
            // open file list user data
            ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream(
                    this.management.getFileNameListUser()));
            ArrayList<Node> userListData =
                    new ArrayList<>((ArrayList<Node>) ois1.readObject());
            this.management.setListUser(userListData);

            this.management.setId(userListData.size());

            // open file user friend data
            ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(
                    this.management.getFileNameFriend()));
            ArrayList<LinkedList<Node>> userFriendData =
                    new ArrayList<>((ArrayList<LinkedList<Node>>) ois2.readObject());
            this.management.getGraphFriend().setAlist(userFriendData);

            // open file user request data
            ObjectInputStream ois3 = new ObjectInputStream(new FileInputStream(
                    this.management.getFileNameRequest()));
            ArrayList<LinkedList<Node>> userRequestData =
                    new ArrayList<>((ArrayList<LinkedList<Node>>) ois3.readObject());
            this.management.getGraphRequest().setAlist(userRequestData);

            // open file username data
            ObjectInputStream ois4 = new ObjectInputStream(new FileInputStream(
                    this.management.getFileNameUsername()));
            ArrayList<ArrayList<String>> usernameData =
                    new ArrayList<>((ArrayList<ArrayList<String>>) ois4.readObject());
            this.management.setListUsername(usernameData);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addFriend() {
        DefaultTableModel model = (DefaultTableModel) this.searchTable.getModel();
        int selectRow = searchTable.getSelectedRow();
        if (selectRow == -1) {
            return;
        }

        int id = Integer.parseInt(model.getValueAt(selectRow, 0).toString().split(" - ")[1]);
        this.management.addRequest(id);
    }
}
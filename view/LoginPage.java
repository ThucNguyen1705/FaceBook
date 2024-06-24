package view;

import model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;


public class LoginPage implements ActionListener {
    private final JMenuItem openItem;
    private final JMenuItem saveItem;
    private HashMap<String, String> loginInfo = new HashMap<>();
    private HashMap<String, Integer> loginId = new HashMap<>();
    private final SocialMediaNetworkView socialMediaNetwork = new SocialMediaNetworkView(this);
    private final JFrame jFrameLogin = new JFrame();
    private final JButton loginButton = new JButton("Log in");
    private final JButton registerButton = new JButton("Sign up");
    private final JTextField userIdSignUpField = new JTextField();
    private final JTextField userPasswordSignUpField = new JTextField();
    private final JTextField userIdLoginField = new JTextField();
    private final JPasswordField userPasswordLoginField = new JPasswordField();
    private final JRadioButton maleRadioButton = new JRadioButton("Male");
    private final JComboBox<Integer> dayComboBox = new JComboBox<>();
    private final JComboBox<Integer> monthComboBox = new JComboBox<>();
    private final JComboBox<Integer> yearComboBox = new JComboBox<>();
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final JComboBox<String> homeTownComboBox = new JComboBox<>();
    private final JLabel messageLabel = new JLabel();
    private int idUser = 0;
    private final String fileNameInfo = "src\\data\\DataInfo.txt";

    public HashMap<String, String> getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(HashMap<String, String> loginInfo) {
        this.loginInfo = loginInfo;
    }

    private final String fileNameIdInfo = "src\\data\\DataIdInfo.txt";

    public HashMap<String, Integer> getLoginId() {
        return loginId;
    }

    public void setLoginId(HashMap<String, Integer> loginId) {
        this.loginId = loginId;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public LoginPage() {
        this.socialMediaNetwork.setVisible(false);

        Font font = new Font("Arial", Font.BOLD, 15);

        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon("src\\image\\FacebookBackground2.png");
        label.setIcon(imageIcon);
        label.setBounds(0, 0, 1100, 650);

        JLabel userIdLoginLabel = new JLabel("Account:");
        userIdLoginLabel.setBounds(575, 25, 100, 25);
        userIdLoginLabel.setFont(font);
        userIdLoginLabel.setForeground(Color.WHITE);
        this.userIdLoginField.setBounds(575, 50, 150, 25);

        JLabel userPasswordLoginLabel = new JLabel("Password:");
        userPasswordLoginLabel.setBounds(750, 25, 100, 25);
        userPasswordLoginLabel.setFont(font);
        userPasswordLoginLabel.setForeground(Color.WHITE);
        this.userPasswordLoginField.setBounds(750, 50, 150, 25);

        this.loginButton.setBounds(925, 50, 100, 25);
        this.loginButton.setFocusable(false);
        this.loginButton.setFont(font);
        this.loginButton.setForeground(Color.WHITE);
        this.loginButton.setBackground(Color.BLUE);
        this.loginButton.addActionListener(this);

        JLabel userIdSignUpLabel = new JLabel("Account:");
        userIdSignUpLabel.setBounds(625, 260, 95, 25);
        userIdSignUpLabel.setFont(font);
        this.userIdSignUpField.setBounds(720, 260, 200, 25);

        JLabel userPasswordSignUpLabel = new JLabel("Password:");
        userPasswordSignUpLabel.setBounds(625, 310, 95, 25);
        userPasswordSignUpLabel.setFont(font);
        this.userPasswordSignUpField.setBounds(720, 310, 200, 25);

        JLabel userGenderLabel = new JLabel("Gender:");
        userGenderLabel.setBounds(625, 360, 95, 25);
        userGenderLabel.setFont(font);
        this.maleRadioButton.setBounds(720, 360, 100, 25);
        JRadioButton femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(820, 360, 100, 25);

        JLabel userDateOfBirthLabel = new JLabel("Birthday:");
        userDateOfBirthLabel.setBounds(625, 410, 95, 25);
        userDateOfBirthLabel.setFont(font);
        this.dayComboBox.setBounds(720, 410, 100, 25);
        this.dayComboBox.setMaximumRowCount(5);
        this.monthComboBox.setBounds(820, 410, 100, 25);
        this.monthComboBox.setMaximumRowCount(5);
        this.yearComboBox.setBounds(920, 410, 100, 25);
        this.yearComboBox.setMaximumRowCount(5);

        JLabel userHomeTownLabel = new JLabel("Home Town:");
        userHomeTownLabel.setBounds(625, 460, 95, 25);
        userHomeTownLabel.setFont(font);
        this.homeTownComboBox.setBounds(720, 460, 200, 25);
        this.homeTownComboBox.setMaximumRowCount(5);

        this.messageLabel.setBounds(720, 110, 350, 35);
        this.messageLabel.setFont(new Font(null, Font.ITALIC, 20));

        this.registerButton.setBounds(720, 560, 100, 25);
        this.registerButton.setFocusable(false);
        this.registerButton.setFont(font);
        this.registerButton.setForeground(Color.WHITE);
        this.registerButton.setBackground(Color.GREEN);
        this.registerButton.addActionListener(this);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleRadioButton);
        buttonGroup.add(femaleRadioButton);
        maleRadioButton.setSelected(true);
        this.addHomeTownList();

        this.jFrameLogin.add(userIdLoginLabel);
        this.jFrameLogin.add(userPasswordLoginLabel);

        this.jFrameLogin.add(userIdSignUpLabel);
        this.jFrameLogin.add(userPasswordSignUpLabel);
        this.jFrameLogin.add(userGenderLabel);
        this.jFrameLogin.add(userDateOfBirthLabel);
        this.jFrameLogin.add(userHomeTownLabel);

        this.jFrameLogin.add(this.userIdLoginField);
        this.jFrameLogin.add(this.userPasswordLoginField);

        this.jFrameLogin.add(this.userIdSignUpField);
        this.jFrameLogin.add(this.userPasswordSignUpField);

        this.jFrameLogin.add(this.maleRadioButton);
        this.jFrameLogin.add(femaleRadioButton);
        this.jFrameLogin.add(this.dayComboBox);
        this.jFrameLogin.add(this.monthComboBox);
        this.jFrameLogin.add(this.yearComboBox);
        this.jFrameLogin.add(this.homeTownComboBox);

        this.jFrameLogin.add(messageLabel);
        this.jFrameLogin.add(loginButton);
        this.jFrameLogin.add(registerButton);

        this.jFrameLogin.add(label);

        this.jFrameLogin.setTitle("Login page");
        this.jFrameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrameLogin.setResizable(false);
        this.jFrameLogin.setSize(1100, 650);
        this.jFrameLogin.setLocationRelativeTo(null);
        this.jFrameLogin.setLayout(null);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        jMenuBar.add(fileMenu);

        openItem.addActionListener(this);
        saveItem.addActionListener(this);

        this.jFrameLogin.setJMenuBar(jMenuBar);
//        this.openDataInfo();
//        this.socialMediaNetwork.openFileData();
        this.jFrameLogin.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userId = this.userIdLoginField.getText();
            String password = String.valueOf(this.userPasswordLoginField.getPassword());

            if (this.loginInfo.containsKey(userId)) {
                if (this.loginInfo.get(userId).equals(password)) {
                    this.messageLabel.setForeground(Color.GREEN);
                    this.messageLabel.setText("Login successful");

                    this.jFrameLogin.setVisible(false);

                    int id = this.loginId.get(userId);
                    this.socialMediaNetwork.changeUser(id, userId);
                    this.socialMediaNetwork.setVisible(true);
                } else {
                    this.messageLabel.setForeground(Color.RED);
                    this.messageLabel.setText("Wrong password");
                }
            } else {
                this.messageLabel.setForeground(Color.RED);
                this.messageLabel.setText("Username not found");
            }
        } else if (e.getSource() == this.registerButton) {
            String userId = this.userIdSignUpField.getText();
            String password = this.userPasswordSignUpField.getText();
            boolean userGender = this.maleRadioButton.isSelected();
            String userHomeTown = Objects.requireNonNull(this.homeTownComboBox.getSelectedItem()).toString();
            String birthday = Objects.requireNonNull(this.dayComboBox.getSelectedItem()) + "/" +
                    Objects.requireNonNull(this.monthComboBox.getSelectedItem()) + "/" +
                    Objects.requireNonNull(this.yearComboBox.getSelectedItem());
            Date userDateOfBirth;
            try {
                userDateOfBirth = this.dateFormat.parse(birthday);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

            Node user = new Node(userId, userGender, userDateOfBirth, userHomeTown);
            if (!checkPassword(password)) {
                JOptionPane.showMessageDialog(this.jFrameLogin,
                        "<html>" +
                                "Must satisfy the following requirements<br/>" +
                                "+ Minimum of 8 characters<br/>" +
                                "+ Has at least 1 number<br/>" +
                                "+ Has at least 1 letter<br/>" +
                                "<html>");
            } else {
                if (!userId.equals("")) {
                    if (this.loginInfo.containsKey(userId)) {
                        this.messageLabel.setForeground(Color.RED);
                        this.messageLabel.setText("Username has already exist!");
                    } else {
                        this.loginInfo.put(userId, password);
                        this.loginId.put(userId, idUser);
                        this.idUser += 1;
                        this.socialMediaNetwork.addUser(user);
                        this.messageLabel.setForeground(Color.GREEN);
                        this.messageLabel.setText("Success!");
//                    this.returnLogin();
                    }
                } else {
                    this.messageLabel.setForeground(Color.RED);
                    this.messageLabel.setText("Failure!");
                }
            }

        } else if (e.getSource() == this.openItem) {
            System.out.println("*beep boop* you loaded a file");

            this.socialMediaNetwork.openFileData();
            this.openDataInfo();
        } else if (e.getSource() == this.saveItem) {
            System.out.println("*beep boop* you saved a file");
            this.socialMediaNetwork.saveFileData();
            this.saveDataInfo();
        }
    }

    public void setVisibleView(boolean status) {
        this.jFrameLogin.setVisible(status);
    }

    public void saveDataInfo() {
        ObjectOutputStream oos1;
        ObjectOutputStream oos2;
        try {
            oos1 = new ObjectOutputStream(new FileOutputStream(
                    this.fileNameInfo));
            oos1.writeObject(this.getLoginInfo());

            oos2 = new ObjectOutputStream(new FileOutputStream(
                    this.fileNameIdInfo));
            oos2.writeObject(this.getLoginId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openDataInfo() {
        ObjectInputStream ois1;
        ObjectInputStream ois2;
        try {
            ois1 = new ObjectInputStream(new FileInputStream(
                    this.fileNameInfo));
            HashMap<String, String> dataInfo =
                    new HashMap<>((HashMap<String, String>) ois1.readObject());
            this.setLoginInfo(dataInfo);

            ois2 = new ObjectInputStream(new FileInputStream(
                    this.fileNameIdInfo));
            HashMap<String, Integer> dataId =
                    new HashMap<>((HashMap<String, Integer>) ois2.readObject());
            this.setLoginId(dataId);

            this.setIdUser(dataId.size());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addHomeTownList() {
        String[] homeTowns = {"Hà Giang", "Cao Bằng", "Lào Cai", "Sơn La",
                "Lai Châu", "Bắc Kạn", "Lạng Sơn", "Tuyên Quang", "Yên Bái",
                "Thái Nguyên", "Điện Biên", "Phú Thọ", "Vĩnh Phúc", "Bắc Giang",
                "Bắc Ninh", "Hà Nội", "Quảng Ninh", "Hải Dương", "Hải Phòng",
                "Hòa Bình", "Hưng Yên", "Hà Nam", "Thái Bình", "Nam Định",
                "Ninh Bình", "Thanh Hóa", "Nghệ An", "Hà Tĩnh", "Quảng Bình",
                "Quảng Trị", "Thừa Thiên Huế", "Đà Nẵng", "Quảng Nam", "Quảng Ngãi",
                "Kon Tum", "Gia Lai", "Bình Định", "Phú Yên", "Đắk Lắk", "Khánh Hòa",
                "Đắk Nông", "Lâm Đồng", "Ninh Thuận", "Bình Phước", "Tây Ninh",
                "Bình Dương", "Đồng Nai", "Bình Thuận", "Thành phố Hồ Chí Minh",
                "Long An", "Bà Rịa – Vũng Tàu", "Đồng Tháp", "An Giang", "Tiền Giang",
                "Vĩnh Long", "Bến Tre", "Cần Thơ", "Kiên Giang", "Trà Vinh", "Hậu Giang",
                "Sóc Trăng", "Bạc Liêu", "Cà Mau"
        };
        int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
        int[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] years = {1900, 1901, 1902, 1903, 1904, 1905, 1906, 1907, 1908, 1909, 1910, 1911, 1912, 1913, 1914, 1915,
                1916, 1917, 1918, 1919, 1920, 1921, 1922, 1923, 1924, 1925, 1926, 1927, 1928, 1929, 1930, 1931, 1932,
                1933, 1934, 1935, 1936, 1937, 1938, 1939, 1940, 1941, 1942, 1943, 1944, 1945, 1946, 1947, 1948, 1949,
                1950, 1951, 1952, 1953, 1954, 1955, 1956, 1957, 1958, 1959, 1960, 1961, 1962, 1963, 1964, 1965, 1966,
                1967, 1968, 1969, 1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978, 1979, 1980, 1981, 1982, 1983,
                1984, 1985, 1986, 1987, 1988, 1989, 1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999, 2000,
                2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017,
                2018, 2019, 2020, 2021, 2022, 2023,};

        //this.homeTownComboBox.addItem("");
        for (String homeTown : homeTowns) {
            this.homeTownComboBox.addItem(homeTown);
        }

        //this.dayComboBox.addItem("");
        for (int day : days) {
            this.dayComboBox.addItem(day);
        }

        //this.monthComboBox.addItem("");
        for (int month : months) {
            this.monthComboBox.addItem(month);
        }

        //this.yearComboBox.addItem("");
        for (int year : years) {
            this.yearComboBox.addItem(year);
        }
    }

    public boolean checkPassword(String password) {
        boolean hasDigit = false;
        boolean hasLetter = false;
        if (password.length() >= 8) {
            for (int i = 0; i < password.length(); i++) {
                char passChar = password.charAt(i);
                if (Character.isDigit(passChar)) {
                    hasDigit = true;
                }
                if (Character.isLetter(passChar)) {
                    hasLetter = true;
                }
                if (!Character.isLetterOrDigit(passChar)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return hasDigit && hasLetter;
    }
}
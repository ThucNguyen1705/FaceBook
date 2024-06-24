package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DetailContentView implements ActionListener {
    private SocialMediaNetworkView view;
    private final JFrame jFrame = new JFrame();
    private final JLabel jLabelUser = new JLabel();
    private final JLabel jLabelTitle = new JLabel();
    private final JLabel jLabelContent = new JLabel();
    private final JLabel jLabelLike = new JLabel();
    private final JLabel jLabelComment = new JLabel();
    private List<String> commentList;
    private final JLabel jLabelTime = new JLabel();
    private JButton saveButton;
    private JCheckBox likeCheckBox;
    private JTextField commentTextField;
    private JTable commentTable;
    private JLabel headLabel;

    public DetailContentView() {
        init();
    }

    public void setVisibleView(boolean status) {
        this.jFrame.setVisible(status);
    }

    public void setData(String user, String title, String content, String like,
                        String comment, String time, SocialMediaNetworkView view,
                        List<String> commentList) {
        this.jLabelUser.setText(user);
        this.jLabelTitle.setText(title);
        this.jLabelContent.setText(content);
        this.jLabelLike.setText(like);
        this.jLabelComment.setText(comment);
        this.jLabelTime.setText(time);
        this.view = view;
        this.commentList = new ArrayList<>(commentList);
    }

    private void init() {
        this.jFrame.setTitle("Detail content");
        this.jFrame.setSize(600, 500);
        this.jFrame.setLocationRelativeTo(null);
        this.jFrame.setResizable(false);
        //this.jFrame.setLayout(new BorderLayout());
        this.jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.jFrame.setLayout(null);

        Font font = new Font("Arial", Font.BOLD, 15);

        this.headLabel = new JLabel();

        this.headLabel.setBounds(10, 5, 560, 30);
        this.headLabel.setFont(font);
        this.jFrame.add(headLabel);

        JScrollPane contentScrollPane = new JScrollPane(this.jLabelContent);
        contentScrollPane.setBounds(10, 35, 560, 90);
        this.jFrame.add(contentScrollPane);

        JScrollPane likeScrollPane = new JScrollPane(this.jLabelLike);
        likeScrollPane.setBounds(10, 125, 560, 80);
        this.jFrame.add(likeScrollPane);

        commentTable = new JTable();
        commentTable.setModel(new DefaultTableModel(
                new Object[][]{
                }, new String[]{
                "Comment"
        }
        ));
        commentTable.setRowHeight(30);

        JScrollPane commentScrollPane = new JScrollPane(commentTable);
        commentScrollPane.setBounds(10, 240, 560, 100);
        this.jFrame.add(commentScrollPane);

        commentTextField = new JTextField();
        commentTextField.setBounds(10, 345, 560, 30);
        this.jFrame.add(commentTextField);

        likeCheckBox = new JCheckBox("Like");
        likeCheckBox.setBounds(10, 380, 100, 25);
        this.jFrame.add(likeCheckBox);

        saveButton = new JButton("Save/Back");
        saveButton.setBounds(10, 425, 560, 30);
        saveButton.setFont(font);
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(Color.BLUE);
        saveButton.addActionListener(this);
        this.jFrame.add(saveButton);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.saveButton) {
            this.view.saveInteract(this.jLabelUser.getText(), this.likeCheckBox.isSelected(),
                    this.commentTextField.getText());
            this.jFrame.setVisible(false);
            this.view.turningBlack(false);
        }
    }

    public void refresh() {
        String name = this.jLabelUser.getText().split(" - ")[0];
        String string = "<html><font size='5' color=blue> " + name + "</font>" +
                " <font size='5'color=black> " + "with title" + "</font>" +
                " <font size='5'color=orange> " + this.jLabelTitle.getText() + "</font></html>";
        this.headLabel.setText(string);
        this.commentTextField.setText("");
        this.likeCheckBox.setSelected(false);

        DefaultTableModel model = (DefaultTableModel) commentTable.getModel();
        model.setRowCount(0);

        for (String str : this.commentList) {
            model.addRow(
                    new Object[]{
                            str
                    }
            );
        }
    }
}

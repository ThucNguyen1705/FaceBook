package controller;

import view.SocialMediaNetworkView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SocialMediaNetworkController implements ActionListener {
    private final SocialMediaNetworkView view;

    public SocialMediaNetworkController(SocialMediaNetworkView view) {
        this.view = view;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
//        JOptionPane.showMessageDialog(view, actionCommand);
        switch (actionCommand) {
            case "Cancel" -> this.view.cancel();
            case "Search" -> this.view.showSearchFriend();
            case "Add" -> this.view.getIdFriend();
            case "Accept" -> this.view.acceptFriend();
            case "Decline" -> this.view.refuseFriend();
            case "Delete" -> this.view.removeFriend();
            case "Post" -> this.view.addContent();
            case "Detail" -> this.view.newWindowDetail();
            case "Log out" -> this.view.logOut();
            case "Add friend" -> this.view.addFriend();
        }
    }
}

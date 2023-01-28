package com.driver;

import java.util.List;

public class WhatsappService {
    WhatsappRepository wr;

    public WhatsappService() {
        wr = new WhatsappRepository();
    }

    public boolean alreadyExist(String mobile) {
        return wr.alreadyExist(mobile);
    }

    public String createUser(String name, String mobile) {
        return wr.createUser(name, mobile);
    }

    public Group createGroup(List<User> users) {
        return wr.createGroup(users);
    }

    public int createMessage(String content) {
        return wr.createMessage(content);
    }

    public boolean groupExists(Group group) {
        return wr.groupExists(group);
    }

    public boolean memberOfAGroup(Group group, User user) {
        return wr.memberOfAGroup(group, user);
    }

    public int sendMessage(Message message,User sender,Group group) {
        return wr.sendMessage(message, sender, group);
    }

    public boolean isAdmin(Group group, User admin) {
        return wr.isAdmin(group, admin);
    }

    public String changeAdmin(User approver,User user,Group group) {
        return wr.changeAdmin(approver, user, group);
    }
}

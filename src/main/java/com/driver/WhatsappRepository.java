package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
    private HashMap<Group, List<User>> groupUserMap;
    private HashMap<Group, User> adminMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashSet<String> userMobile;
    private int customGroupCount;
    private int messageId;

    public WhatsappRepository(){
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        this.userMobile = new HashSet<>();
        this.customGroupCount = 0;
        this.messageId = 0;
    }

    public boolean alreadyExist(String mobile) {
        return userMobile.contains(mobile);
    }

    public String createUser(String name, String mobile) {
        userMobile.add(mobile);
        User user = new User(name, mobile);
        return "SUCCESS";
    }

    public Group createGroup(List<User> users) {
        Group group;
        if (users.size() == 2) {
            group = new Group(users.get(1).getName(), users.size());
        } else {
            customGroupCount += 1;
            group = new Group("Group " + customGroupCount, users.size());
        }
        groupUserMap.put(group, users);
        adminMap.put(group, users.get(0));
        groupMessageMap.put(group, new ArrayList<>());
        return group;
    }

    public int createMessage(String content) {
        messageId += 1;
        return (new Message(messageId, content)).getId();
    }

    public boolean groupExists(Group group) {
        return groupUserMap.containsKey(group);
    }

    public boolean memberOfAGroup(Group group, User user) {
        return groupUserMap.get(group).contains(user);
    }

    public int sendMessage(Message message,User sender,Group group) {
        senderMap.put(message, sender);
        groupMessageMap.get(group).add(message);
        return groupMessageMap.get(group).size();
    }

    public boolean isAdmin(Group group, User admin) {
        return adminMap.get(group).equals(admin);
    }

    public String changeAdmin(User approver,User user,Group group) {
        adminMap.put(group, user);
        return "SUCCESS";
    }
}

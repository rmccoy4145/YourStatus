package com.mccoy.yourstatus.service;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.entity.UserFollow;
import com.mccoy.yourstatus.entity.UserStatusMessage;
import com.mccoy.yourstatus.repository.impl.UserFollowDAOImpl;
import com.mccoy.yourstatus.repository.impl.UserStatusMessageDAOImpl;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles operations around Status entity
 */

public class UserStatusMessageService {

    UserStatusMessageDAOImpl messageDao;

    UserFollowDAOImpl followDao;

    @Inject
    public UserStatusMessageService(UserStatusMessageDAOImpl messageDao, UserFollowDAOImpl followDao) {
        this.messageDao = messageDao;
        this.followDao = followDao;
    }

    /**
     * Get user message feed
     * @param user
     * @return
     */
    public List<UserStatusMessage> getUserMessageFeed(User user) {
        return messageDao.getAllByUser(user);
    }

    /**
     * Get all messages of followed Users
     * @param user
     * @return
     */
    public List<UserStatusMessage> getFollowedMessageFeed(User user) {
        List<UserStatusMessage> allFollowersMessages = new ArrayList<>();
        followDao.getAllByUser(user).forEach(userFollow -> {
            allFollowersMessages.addAll(messageDao.getAllByUser(userFollow.getFollowUser()));
        });
        return allFollowersMessages;
    }

    /**
     * Get all boradcast messages
     * @return
     */
    public List<UserStatusMessage> getAllBroadcastMessage() {
        UserStatusMessageDAOImpl usmRepoImpl = (UserStatusMessageDAOImpl) messageDao;
        return usmRepoImpl.getAllBroadcast();
    }

    /**
     * Get all messages of User and followed Users sorted by date
     * @param user
     * @return
     */
    public List<UserStatusMessage> getMainMessageFeedFor(User user) {
        List<UserStatusMessage> allMessages = new ArrayList<>();
        allMessages.addAll(getUserMessageFeed(user));
        allMessages.addAll(getFollowedMessageFeed(user));
        allMessages.addAll(getAllBroadcastMessage());
        allMessages.sort((o1, o2) -> {
            return o1.getDatetime().compareTo(o2.getDatetime());
        });
        return allMessages;
    }


    /**
     * Add message to message repo
     * @param message
     * @return
     */
    public UserStatusMessage addMessage(UserStatusMessage message) {
        return messageDao.add(message);
    }

    /**
     * Get all messages from repo
     * @return
     */
    public List<UserStatusMessage> getAllMessageFeed() {
        return messageDao.getAll();
    }


}

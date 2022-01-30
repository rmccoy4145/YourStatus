package com.mccoy.yourstatus.service;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.entity.UserFollow;
import com.mccoy.yourstatus.entity.UserStatusMessage;
import com.mccoy.yourstatus.repository.Repository;
import com.mccoy.yourstatus.repository.impl.UserFollowRepositoryImpl;
import com.mccoy.yourstatus.repository.impl.UserStatusMessageRepositoryImpl;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles operations around Status entity
 */
@ApplicationScoped
public class UserStatusMessageService {
    Repository<UserStatusMessage> messageRepo = new UserStatusMessageRepositoryImpl();
    Repository<UserFollow> followRepo = new UserFollowRepositoryImpl();

    /**
     * Get user message feed
     * @param user
     * @return
     */
    public List<UserStatusMessage> getUserMessageFeed(User user) {
        return messageRepo.getAllByUser(user);
    }

    /**
     * Get all messages of followed Users
     * @param user
     * @return
     */
    public List<UserStatusMessage> getFollowedMessageFeed(User user) {
        List<UserStatusMessage> allFollowersMessages = new ArrayList<>();
        followRepo.getAllByUser(user).forEach(userFollow -> {
            allFollowersMessages.addAll(messageRepo.getAllByUser(userFollow.getFollowUser()));
        });
        return allFollowersMessages;
    }

    /**
     * Get all boradcast messages
     * @return
     */
    public List<UserStatusMessage> getAllBroadcastMessage() {
        UserStatusMessageRepositoryImpl usmRepoImpl = (UserStatusMessageRepositoryImpl) messageRepo;
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
        return messageRepo.add(message);
    }

    /**
     * Get all messages from repo
     * @return
     */
    public List<UserStatusMessage> getAllMessageFeed() {
        return messageRepo.getAll();
    }


}

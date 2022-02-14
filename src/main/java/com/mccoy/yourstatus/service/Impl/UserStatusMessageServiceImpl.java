package com.mccoy.yourstatus.service.Impl;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.entity.UserFollow;
import com.mccoy.yourstatus.entity.UserStatusMessage;
import com.mccoy.yourstatus.service.AbstractJpaService;
import com.mccoy.yourstatus.service.GenericDAO;

import javax.ejb.Local;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles operations around Status entity
 */
@Stateful
@Local
public class UserStatusMessageServiceImpl extends AbstractJpaService<UserStatusMessage> implements GenericDAO<UserStatusMessage> {

    public UserStatusMessageServiceImpl() {
    }

    @Override
    public Class<UserStatusMessage> getType() {
        return UserStatusMessage.class;
    }

    public List<UserStatusMessage> getAllByUser(User user){
        return getEm().createQuery("SELECT f FROM UserStatusMessage f WHERE f.user = :user", UserStatusMessage.class)
                .setParameter("user", user)
                .getResultList();
    }

    public List<UserStatusMessage> getAllBroadcast() {
        return getEm().createQuery("SELECT f FROM UserStatusMessage f WHERE f.broadcast = true", UserStatusMessage.class)
                .getResultList();
    }


    /**
     * Get all messages of followed Users
     * @param user
     * @return
     */
    public List<UserStatusMessage> getFollowedMessageFeed(User user) {
        List<UserStatusMessage> allFollowersMessages = new ArrayList<>();
        List<User> followedUsers = getEm().createQuery("SELECT f FROM UserFollow f WHERE f.user = :user", UserFollow.class)
                .setParameter("user", user)
                .getResultList().stream().map(userFollow -> {
                    return userFollow.getFollowUser();
                }).collect(Collectors.toList());

        if(!followedUsers.isEmpty()) {
            followedUsers.forEach(followedUser -> {
                allFollowersMessages.addAll(getAllByUser(followedUser));
            });
        }
        return allFollowersMessages;
    }


    /**
     * Get all messages of User and followed Users sorted by date
     * @param user
     * @return
     */
    public List<UserStatusMessage> getMainMessageFeedFor(User user) {
        List<UserStatusMessage> allMessages = new ArrayList<>();
        allMessages.addAll(getAllByUser(user));
        allMessages.addAll(getFollowedMessageFeed(user));
        allMessages.addAll(getAllBroadcast());
        allMessages.sort((o1, o2) -> {
            return o1.getDatetime().compareTo(o2.getDatetime());
        });
//        Collections.sort(allMessages, Collections.reverseOrder());
        return allMessages;
    }



}

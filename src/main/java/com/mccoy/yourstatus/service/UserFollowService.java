package com.mccoy.yourstatus.service;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.entity.UserFollow;
import com.mccoy.yourstatus.repository.impl.UserFollowDAOImpl;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Handles operations around Follow relations
 */

public class UserFollowService {


    UserFollowDAOImpl dao;
    @Inject
    public UserFollowService(UserFollowDAOImpl dao) {
        this.dao = dao;
    }

    /**
     * Get all users a user is following
     * @param user
     * @return
     */
    public List<UserFollow> getFollowedBy(User user) {
        return dao.getAllByUser(user);
    }

    /**
     * Get all followers of User
     * @param user
     * @return
     */
    public List<UserFollow> getFollowersOf(User user) {
        UserFollowDAOImpl ufRepoImpl = (UserFollowDAOImpl) dao;
        return ufRepoImpl.getFollowersBy(user);
    }

    /**
     * Adds a follower relationship to the UserFollowRepo
     * @param user
     * @param follower
     * @return
     */
    public UserFollow addFollower(User user, User follower) {
        UserFollow userFollow = new UserFollow();
        userFollow.setUser(user);
        userFollow.setFollowUser(follower);
        return dao.add(userFollow);
    }

    /**
     * removes a follow relationship from the UserFollowRepo
     * @param user
     * @param follower
     */
    public void removeFollower(User user, User follower) {
        Optional<UserFollow> optUserFollow = dao.getAllByUser(user).stream().filter(userFollow -> {
            return userFollow.getFollowUser().equals(follower);
        }).findFirst();

        optUserFollow.ifPresent(userFollow -> dao.remove(userFollow));
    }

    public List<UserFollow> getAll() {
        return dao.getAll();
    }

}

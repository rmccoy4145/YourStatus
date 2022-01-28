package com.mccoy.yourstatus.service;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.entity.UserFollow;
import com.mccoy.yourstatus.repository.Repository;
import com.mccoy.yourstatus.repository.impl.UserFollowRepositoryImpl;

import java.util.List;
import java.util.Optional;

/**
 * Handles operations around Follow relations
 */
public class UserFollowService {

    Repository<UserFollow> repo = new UserFollowRepositoryImpl();

    /**
     * Get all follow relationships for User
     * @param user
     * @return
     */
    public List<UserFollow> getFollowRelationshipsOf(User user) {
        return repo.getAllByUser(user);
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
        return repo.add(userFollow);
    }

    /**
     * removes a follow relationship from the UserFollowRepo
     * @param user
     * @param follower
     */
    public void removeFollower(User user, User follower) {
        Optional<UserFollow> optUserFollow = repo.getAllByUser(user).stream().filter(userFollow -> {
            return userFollow.getFollowUser().equals(follower);
        }).findFirst();

        optUserFollow.ifPresent(userFollow -> repo.remove(userFollow));
    }

}

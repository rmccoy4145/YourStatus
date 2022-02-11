package com.mccoy.yourstatus.service.Impl;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.entity.UserFollow;
import com.mccoy.yourstatus.service.AbstractJpaService;
import com.mccoy.yourstatus.service.GenericDAO;

import javax.ejb.Local;
import javax.ejb.Stateful;
import java.util.List;
import java.util.Optional;

/**
 * Handles operations around Follow relations
 */
@Stateful
@Local
public class UserFollowServiceImpl extends AbstractJpaService<UserFollow> implements GenericDAO<UserFollow> {


    public UserFollowServiceImpl() {
    }

    @Override
    public Class<UserFollow> getType() {
        return UserFollow.class;
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
        return add(userFollow);
    }

    /**
     * removes a follow relationship from the UserFollowRepo
     * @param user
     * @param follower
     */
    public void removeFollower(User user, User follower) {
        Optional<UserFollow> optUserFollow = getAllByUser(user).stream().filter(userFollow -> {
            return userFollow.getFollowUser().equals(follower);
        }).findFirst();

        optUserFollow.ifPresent(userFollow -> remove(userFollow));
    }


    public List<UserFollow> getAllByUser(User user){
        return getEm().createQuery("SELECT f FROM UserFollow f WHERE f.user = :user", UserFollow.class)
                .setParameter("user", user)
                .getResultList();
    }


    public List<UserFollow> getFollowersOf(User user){
        return getEm().createQuery("SELECT f FROM UserFollow f WHERE f.followUser = :user", com.mccoy.yourstatus.entity.UserFollow.class)
                .setParameter("user", user)
                .getResultList();
    }


}

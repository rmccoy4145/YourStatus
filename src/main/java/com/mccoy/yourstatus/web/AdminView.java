package com.mccoy.yourstatus.web;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.entity.UserFollow;
import com.mccoy.yourstatus.entity.UserStatusMessage;
import com.mccoy.yourstatus.web.util.ServiceUtil;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import java.util.ArrayList;
import java.util.List;

@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
@Theme(themeFolder = "myapp", variant = Lumo.DARK)
@PageTitle("YourStatus - Admin")
@Route("/admin")
public class AdminView extends AppLayout {

    Tab dashboardTab;
    Tab usersTab;
    Tab messagesTab;
    Tab followTab;


    public AdminView() {
        H1 title = new H1("YourStatus");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("left", "var(--lumo-space-l)")
                .set("margin", "0")
                .set("position", "absolute");

        Tabs tabs = getTabs();
        addToNavbar(title, tabs);
        setContent(getDashboadContent());
    }

    private Tabs getTabs() {
        dashboardTab = new Tab("Dashboard");
        messagesTab =  new Tab("StatusMessages");
        usersTab =  new Tab("Users");
        followTab =  new Tab("Following");

        Tabs tabs = new Tabs();
        tabs.getStyle().set("margin", "auto");
        tabs.add(
                dashboardTab,
                usersTab,
                messagesTab,
                followTab
        );
        tabs.addSelectedChangeListener(event ->
                setNewContent(event.getSelectedTab())
        );
        return tabs;
    }

    void setNewContent(Tab selectedTab) {
        if(selectedTab.equals(dashboardTab)) {
            setContent(getDashboadContent());
        }

        if(selectedTab.equals(messagesTab)) {
            setContent(getMessageContent());
        }

        if(selectedTab.equals(usersTab)) {
            setContent(getUsersContent());
        }

        if(selectedTab.equals(followTab)) {
            setContent(getFollowContent());
        }
    }

    private Component getDashboadContent() {
        return new Text("Welcome to the YourStatus Admin Panel");
    }

    private Component getMessageContent() {
        Grid<UserStatusMessage> grid = new Grid<>();

        List<UserStatusMessage> userFollows = ServiceUtil.getUserStatusMessageService().getAll();

        grid.setItems(userFollows);
        grid.addColumn(UserStatusMessage::getId).setHeader("Id");
        grid.addColumn(UserStatusMessage::getUserId).setHeader("UserId");
        grid.addColumn(UserStatusMessage::getDatetime).setHeader("DateTime");
        grid.addColumn(UserStatusMessage::getMessage).setHeader("Message");
        grid.addColumn(UserStatusMessage::getBroadcast).setHeader("Broadcast");

        return grid;
    }

    private Component getUsersContent() {
        // Create a grid bound to the list
        Grid<User> grid = new Grid<>();

        List<User> users = ServiceUtil.getUserService().getAll();

        grid.setItems(users);
        grid.addColumn(User::getId).setHeader("Id");
        grid.addColumn(User::getUsername).setHeader("Name");

        return grid;
    }

    private Component getFollowContent() {
        Grid<UserFollow> grid = new Grid<>();

        List<UserFollow> userFollows = ServiceUtil.getUserFollowService().getAll();

        grid.setItems(userFollows);
        grid.addColumn(UserFollow::getId).setHeader("Id");
        grid.addColumn(UserFollow::getUserId).setHeader("UserId");
        grid.addColumn(UserFollow::getFollowUserId).setHeader("FollowUserId");

        return grid;
    }


}

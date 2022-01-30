package com.mccoy.yourstatus.ui;

import com.mccoy.yourstatus.service.UserStatusMessageService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
@Theme(themeFolder = "myapp", variant = Lumo.DARK)
@PageTitle("YourStatus - Admin")
@Route("/")
public class MainAppView extends AppLayout {

    @Inject
    UserStatusMessageService userStatusMessageService;

    public MainAppView() {
        H1 title = new H1("YourStatus");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("left", "var(--lumo-space-l)")
                .set("margin", "0")
                .set("position", "absolute");

        Tabs tabs = getTabs();
        addToNavbar(title, tabs);
        setContent(getMessageList());
    }

    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.getStyle().set("margin", "auto");
        tabs.add(
                createTab("Dashboard"),
                createTab("Users"),
                createTab("StatusMessages"),
                createTab("Following")
        );
        return tabs;
    }

    private Tab createTab(String viewName) {
        RouterLink link = new RouterLink();
        link.add(viewName);
        // Demo has no routes
        // link.setRoute(viewClass.java);
        link.setTabIndex(-1);
        return new Tab(link);
    }



    MessageList getMessageList() {
        MessageList messageList = new MessageList();
        List<MessageListItem> items = new ArrayList<>();
//        userStatusMessageService.getAllMessageFeed().forEach( userStatusMessage -> {
//            MessageListItem messageListItem = new MessageListItem();
//            messageListItem.setTime(Instant.parse(userStatusMessage.getDatetime().toString()));
//            messageListItem.setUserName(userStatusMessage.getUser().getUsername());
//            messageListItem.setText(userStatusMessage.getMessage());
//            items.add(messageListItem);
//        });
        if (items.isEmpty()) {
            MessageListItem messageListItem = new MessageListItem();
            messageListItem.setText("No messages...");
            items.add(messageListItem);
        }
        messageList.setItems(items);
        return messageList;
    }
}

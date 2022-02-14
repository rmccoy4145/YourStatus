package com.mccoy.yourstatus.web;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.entity.UserStatusMessage;
import com.mccoy.yourstatus.web.util.ServiceUtil;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
@Theme(themeFolder = "myapp", variant = Lumo.DARK)
@PageTitle("YourStatus - Welcome")
@Route("main/:userId")
public class UserMainView extends AppLayout implements BeforeEnterObserver {

    private static Logger LOG = Logger.getLogger(UserMainView.class.getSimpleName());

    private static ZoneId ET_ZONE = ZoneId.of("America/New_York");
    private static String USERID_QUERY_PARMA = "userId";
    private static String DEFAULT_USERID_QUERY_PARMA = "0";
    private final Tabs menu;
    private H1 viewTitle;

    public UserMainView() {
        // Use the drawer for the menu
        setPrimarySection(Section.DRAWER);

        // Put the menu in the drawer
        menu = createMenu();
        addToDrawer(createDrawerContent(menu));

    }

    /**
     * Grabs userId from Url and retreive user from user service
     * @param event
     */
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String userId = event.getRouteParameters().get("userId").get();
        LOG.info(String.format("UrlParam: userId=%s", userId));
        User user = ServiceUtil.getUserService().get(Long.parseLong(userId));
        LOG.info(String.format("Accessed as user: %s", user.getUsername()));

        // Add Navbar anad Content to the View with URL parameters
        addToNavbar(true, createHeaderContent(user));
        setContent(getMessageFeedWindow(user));
    }


    private Component createHeaderContent(User user) {

        HorizontalLayout layout = new HorizontalLayout();

        // Configure styling for the header
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(true);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        // Have the drawer toggle button on the left
        layout.add(new DrawerToggle());

        // Placeholder for the title of the current view.
        // The title will be set after navigation.
        viewTitle = new H1(String.format("Welcome! %s", user.getUsername()));
        layout.add(viewTitle);

        Image userIconImg = new Image("images/defaultAvatar.png", "Avatar");
        userIconImg.setHeight("25");
        // A user icon
        layout.add(userIconImg);

        return layout;
    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();

        // Configure styling for the drawer
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);

        // Have a drawer header with an application logo
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        Image logoImg = new Image("images/LogoIcon_sm.png", "My Status");
        logoImg.setHeight("50");
        logoLayout.add(logoImg);
        logoLayout.add(new H1("Menu"));

        // Display the logo and the menu in the drawer
        layout.add(logoLayout, menu);
        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        tabs.add(createMenuItems());
        return tabs;
    }

    private Component[] createMenuItems() {
        return new Tab[]{};
//                createTab("Hello World", HelloWorldView.class),
//                createTab("Card List", CardListView.class),
//                createTab("About", AboutView.class)};
    }

    private static Tab createTab(String text, Class<? extends Component> navigationTarget) {
        final Tab tab = new Tab();
        tab.add(new RouterLink(text, navigationTarget));
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }

    private Component getMessageFeedWindow(User user) {
        VerticalLayout messageWindow = new VerticalLayout();
        MessageList messages = new MessageList();
        List<MessageListItem> items = new ArrayList<>();

        items = ServiceUtil.getUserStatusMessageService().getMainMessageFeedFor(user).stream().map(userStatusMessage ->
        {
            MessageListItem item = new MessageListItem();
            item.setText(userStatusMessage.getMessage());
            item.setTime(userStatusMessage.getDatetime().atZone(ET_ZONE).toInstant());
            item.setUserName(userStatusMessage.getUser().getUsername());
            item.setUserImage("images/defaultAvatar_sm.png");
            return item;
        }).collect(Collectors.toList());

        messages.setItems(items);
        MessageInput input = new MessageInput();
        input.addSubmitListener(submitEvent -> {
            UserStatusMessage message = new UserStatusMessage();
            message.setMessage(submitEvent.getValue());
            message.setDatetime(LocalDateTime.now());
            message.setUser(user);

            ServiceUtil.getUserStatusMessageService().add(message);

            Notification.show("Message sent: " + submitEvent.getValue(),
                    3000, Notification.Position.MIDDLE);
        });

        messageWindow.add(messages, input);
        return messageWindow;
    }
}

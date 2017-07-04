package com.dexdevs.ism;

import com.dexdevs.views.DesktopDemo;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    
        private  FormLayout form;
        private TextField user;
        private PasswordField password;
        private Button login;
        private CheckBox remember;
        
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final GridLayout layout = new GridLayout(3,3);
        layout.setSizeFull();
        
             form=new FormLayout();
             form.setCaptionAsHtml(true);
             form.setSizeUndefined();
             form.setCaption("<h1>Login...</h1>");
        
             user=new TextField("Enter UserName:");
             user.setIcon(VaadinIcons.USER);
             user.setWidth(25,Sizeable.Unit.PERCENTAGE);
             user.setPlaceholder("Username....");
             user.focus();
             password=new PasswordField("Enter Password");
             password.setIcon(VaadinIcons.PASSWORD);
             password.setPlaceholder("******");
             password.setWidth(25, Sizeable.Unit.PERCENTAGE);
             user.setValue("admin");
             password.setValue("12345");
             login=new Button("Login");
             login.setWidth(8,Sizeable.Unit.PERCENTAGE);
             login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
             login.addClickListener((ClickEvent) -> {
                
                    if(user.getValue().equalsIgnoreCase("admin")&&password.getValue().equals("12345")){
                
                    Notification.show("User Creadinatls are correct.",Notification.Type.HUMANIZED_MESSAGE);
                    this.setContent(new DesktopDemo());
                    user.clear();
                    password.clear();
                    
            }else{
                
                Notification.show("User Creadinatls are incorrect.\n"
                        + "Pres ESC to proceed",Notification.Type.ERROR_MESSAGE);
            }
        });
        
             remember=new CheckBox("Remember Password...");
               
               
        form.addComponents(user,password,remember,login);
        
        layout.addComponent(form,1,1);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false, widgetset = "com.dexdevs.ism.AppWidgetSet")
    public static class MyUIServlet extends VaadinServlet {
    }
}

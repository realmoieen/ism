/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dexdevs.views;

import com.dexdevs.model.Customer;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chaudhary
 */
public class CustomerV extends VerticalLayout{
    
        private FormLayout form;
        private TextField name;
        private TextField address;
        private TextField phone;
        private TextField email;
        private TextArea description;
        private Button save,add_customer;
        private Button clear;
        private Customer customer;
        private Grid<Customer> grid;
        private Window window;
        private List<Customer> list=new ArrayList<>();
        
        
//        private Binder<Customer> binder=new Binder<>(Customer.class);
        
//        private CustomerJpaController cjc = new CustomerJpaController(Persistence.createEntityManagerFactory("com.mycompany_ism_war_1.0-SNAPSHOTPU"));
        
    public CustomerV(){
            
        
        form= new FormLayout();
        form.setCaption("<h3>Enter Customer Detail...</h3>");
        form.setCaptionAsHtml(true);
        form.setSizeUndefined();
        
        name=new TextField("Name :");
        name.setRequiredIndicatorVisible(true);
        name.setIcon(VaadinIcons.USER);
        name.setWidth("50%");
        name.setPlaceholder("Name...");
        
        address=new TextField();
        address.setRequiredIndicatorVisible(true);
        address.setCaption("Address :");
        address.setWidth("50%");
        address.setIcon(VaadinIcons.HOME);
        address.setPlaceholder("Enter Address");
        
        phone=new TextField();
        phone.setRequiredIndicatorVisible(true);
        phone.setCaption("Phone :");
        phone.setWidth("50%");
        phone.setIcon(VaadinIcons.PHONE);
        phone.setPlaceholder("Phone#...");
        
        email=new TextField();
        email.setRequiredIndicatorVisible(true);
        email.setWidth("50%");
        email.setCaption("Email :");
        email.setIcon(VaadinIcons.ENVELOPE);
        email.setPlaceholder("Email...");
        
        description = new TextArea();
        description.setStyleName(ValoTheme.TEXTAREA_LARGE);
        description.setCaption("Description :");
        description.setWidth("50%");
        description.setPlaceholder("Description...");
        description.setIcon(VaadinIcons.ALIGN_LEFT);
        
        
//        binder.bindInstanceFields(this);
        
        save=new Button("Save");
        save.setStyleName(ValoTheme.BUTTON_SMALL);
        save.setDescription("This Button saves and Update Customer Detail");
        save.addClickListener((event) -> {
            customer=new Customer(name.getValue(),phone.getValue(),email.getValue(),address.getValue(),description.getValue());
            list.add(customer);
//            cjc.create(customer);
            grid.setItems(list);
            window.close();
            clear();
        });
        
        
        
        
        clear=new Button("Clear");
        clear.setStyleName(ValoTheme.BUTTON_SMALL);
        clear.setDescription("Clear all Field to their Default Values(Empty)");
        clear.addClickListener(e ->{
            clear();
        });
    
        form.addComponents(name, address, phone, email, description,new HorizontalLayout(clear,save));
        form.setMargin(true);
//        form.setSizeFull();
        
        
        grid=new Grid(Customer.class);
        grid.setCaption("<h3>Enter Customer Detail...</h3>");
        grid.setCaptionAsHtml(true);
        grid.setStyleName(ValoTheme.LAYOUT_WELL);
        grid.setWidth(this.getWidth(),Unit.PERCENTAGE);
        
        HeaderRow filterRow=grid.appendHeaderRow();
        HeaderCell filternamecell=filterRow.getCell("name");
        TextField filter=new TextField();
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.setPlaceholder("Filter name...");
        filternamecell.setComponent(filter);
//        grid.setHeight();
        List names=new ArrayList();
            filter.addValueChangeListener(e ->{
                      
            names.clear();
            for (int i = 0; i <list.size(); i++) {
                
                names.add(list.get(i).getName());
            }
            Notification.show(names.toString());
        
        });
        grid.setColumns("id","name","email","address","phone","description");
        
        window=new Window();
        window.setWidth(400.0f,Unit.PIXELS);
        window.setContent(form);
        window.center();
        window.setResizeLazy(true);
        
        add_customer=new Button("Add Customer");
        add_customer.setStyleName(ValoTheme.BUTTON_SMALL);
//        add_customer.setWidth(20.0f, Unit.PERCENTAGE);
        add_customer.addClickListener(e ->{
            try {
            this.getUI().addWindow(window);
                
            } catch (IllegalArgumentException ex) {
            }
        });
        
        
       
        
        addComponents(grid,add_customer);
        setSizeFull();
//        setExpandRatio(grid,1);
    
    }
    ///Clear all Field to their Default values mean Empty....
    void clear(){
        name.setValue("");
        email.setValue("");
        phone.setValue("");
        description.setValue("");
        address.setValue("");
            
    }
    
//    public void setCustomer(Customer customer){
//        this.customer=customer;
//        binder.setBean(customer);
//        
//    }
    
    
}

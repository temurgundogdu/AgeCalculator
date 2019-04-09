package com.example.myapplication;
/*
 * 
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
//@Widgetset("com.example.testVaadin.MyAppWidgetset")
public class MyUI extends UI {
 private PopupDateField dateBirth;
 private String strAge;
 private TextField ageField;
 private FormLayout formLayout;
 private FieldGroup binder;
//Have an item
PropertysetItem item;
 @Override
 protected void init(VaadinRequest vaadinRequest) {
 final VerticalLayout layout = new VerticalLayout();
 formLayout = new FormLayout();
 // date PopupField
 dateBirth = new PopupDateField("Date :");
 dateBirth.setDateFormat("dd.MM.yyyy");
 dateBirth.addStyleName("tiny");
 dateBirth.setWidth("10em");
 // age textField
 ageField = new TextField("Age :");
 ageField.addStyleName("tiny");
 ageField.setWidth("10em");
 listenerComponent();
 
 formLayout.addComponents(dateBirth,ageField);
 layout.addComponents(formLayout);
 layout.setMargin(true);
 layout.setSpacing(true);
 
 setContent(layout);
 }

 @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
 @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
 public static class MyUIServlet extends VaadinServlet {
 }
 
 @SuppressWarnings("deprecation")
 public void listenerComponent() {
 item = new PropertysetItem();
 
 dateBirth.addValueChangeListener(new PopupDateField.ValueChangeListener() {
 @Override
 public void valueChange(ValueChangeEvent event) {
 strAge = getPersonAge((Date)event.getProperty().getValue());
 System.out.println(" ageBirthDate : " + strAge); 
 item.removeItemProperty("age");
 item.addItemProperty("age", new ObjectProperty<String>(strAge));
 FieldGroup binder = new FieldGroup(item);
 binder.bind(ageField, "age");
 }
 });
 
 }
 
 public String getPersonAge(Date birthDate) {
 // convert date to stringDateFormat MM-dd-yyyy
 SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
 // convert simpleDateFormat to string
 String dateStr = formatter.format(birthDate);
 System.out.println("date String : " + dateStr);
 
 Calendar birth = new GregorianCalendar();
 Calendar today = new GregorianCalendar();
 int age = 0;
 int factor = 0; 
//calculate age
 try {

 Date birthDt = new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
 Date currentDate = new Date(); //current date
 
 birth.setTime(birthDt);
 today.setTime(currentDate);
 
 // check if birthday has been celebrated this year
 if(today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
 factor = -1;
 //birthday not celebrated
 }
 age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + factor;
 } catch (ParseException e) {
    e.printStackTrace();
 } 

 return String.valueOf(age);
 }}


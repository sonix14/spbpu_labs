package com.example.application.views.main;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("") 
public class MainView extends VerticalLayout { 
	
	@Route("combo-box-custom-entry-1")
	public class ComboBox1 extends Div {

	    public ComboBox1() {
	        ComboBox<String> comboBox = new ComboBox<>("Browser");
	        comboBox.setAllowCustomValue(true);
	        add(comboBox);
	        comboBox.setItems("Go", "By horse", "By plane", "Run");
	        comboBox.setHelperText("Select a type of moving");
	    }

	}
	
	public class AppLayoutNavbarPlacement extends AppLayout {
		//private static final long serialVersionUID = 1L;
		public Tabs tabs = createTabs();

	    public AppLayoutNavbarPlacement() {
	        DrawerToggle toggle = new DrawerToggle();

	        H1 title = new H1("Applications");
	        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
	                .set("margin", "0");

	        //Tabs tabs = createTabs();

	        
	        addToDrawer(tabs);
	        addToNavbar(toggle, title);
	    }
	    
	    private Tabs createTabs() {
	        Tabs tabs = new Tabs();
	        //ComboBox1 box1 = new ComboBox1();
	        Tab movingHero = new Tab("Moving Hero");
	        /*
	        movingHero.getElement().addEventListener("click", e -> {
	        	add(new VerticalLayout(
	        			box1));
	        });
	        */
	        Tab Annotations = new Tab("Annotations");
	        Tab Hierarchy = new Tab("Hierarchy");
	        Tab Translator = new Tab("Translator");
	        Tab streamMethods = new Tab("Stream methods");
	        Tab supervisor = new Tab("Supervisor");
	        
	        tabs.add(movingHero, Annotations, Hierarchy, Translator, streamMethods, supervisor);
	        tabs.setOrientation(Tabs.Orientation.VERTICAL);
	        /*
	        tabs.add(createTab(VaadinIcon.BELL, "Moving Hero"),
	                createTab(VaadinIcon.CART, "Annotations"),
	                createTab(VaadinIcon.USER_HEART, "Hierarchy"),
	                createTab(VaadinIcon.PACKAGE, "Translator"),
	                createTab(VaadinIcon.RECORDS, "Stream methods"),
	                createTab(VaadinIcon.LIST, "Supervisor"));
	        tabs.setOrientation(Tabs.Orientation.VERTICAL);
	        */
	        return tabs;
	    }
	    
	    public Tabs getTabs() {
	    	return tabs;
	    }
	    
	    /*
	    private Tab createTab(VaadinIcon viewIcon, String viewName) {
	        Icon icon = viewIcon.create();
	        icon.getStyle().set("box-sizing", "border-box")
	                .set("margin-inline-end", "var(--lumo-space-m)")
	                .set("margin-inline-start", "var(--lumo-space-xs)")
	                .set("padding", "var(--lumo-space-xs)");

	        RouterLink link = new RouterLink();
	        link.add(icon, new Span(viewName));
	        // Demo has no routes
	        // link.setRoute(viewClass.java);
	        link.setTabIndex(-1);

	        return new Tab(link);
	    }
	    	        */
	}

  public MainView() {
	  /*
    VerticalLayout todosList = new VerticalLayout(); 
    TextField taskField = new TextField(); 
    Button addButton = new Button("Add"); 
    addButton.addClickListener(click -> { 
      Checkbox checkbox = new Checkbox(taskField.getValue());
      todosList.add(checkbox);
    });
    addButton.addClickShortcut(Key.ENTER); 
	  
	VerticalLayout todosList = new VerticalLayout(); 
    Button button1 = new Button("Moving Hero");
    button1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    Button button2 = new Button("Annotations");
    button2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    Button button3 = new Button("Hierarchy");
    button3.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    Button button4 = new Button("Translator");
    button4.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    Button button5 = new Button("Stream methods");
    button5.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    Button button6 = new Button("Supervisor");
    button6.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    */
	AppLayoutNavbarPlacement layout = new AppLayoutNavbarPlacement();
	ComboBox1 box1 = new ComboBox1();
	VerticalLayout todosList = new VerticalLayout(); 
	todosList.getDefaultHorizontalComponentAlignment();
	todosList.setAlignItems(getDefaultHorizontalComponentAlignment());
	todosList.add(box1);
	layout.tabs.getTabAt(0).getElement().addEventListener("click", e -> {
	        	//add(todosList);
		todosList.add(box1);
	});
    add( 
    		layout,
    		todosList
      //new HorizontalLayout()
    );
    
    /*
    Select<String> select = new Select<>();
    select.setLabel("Sort by");
    select.setItems("Most recent first", "Rating: high to low",
            "Rating: low to high", "Price: high to low",
            "Price: low to high");
    select.setValue("Most recent first");
    add(select);
    */
  }
}
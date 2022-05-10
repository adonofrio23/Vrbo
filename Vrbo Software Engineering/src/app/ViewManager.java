package app;

import javax.swing.JPanel
;
import java.awt.CardLayout;
import java.util.HashMap;

public class ViewManager {
	static JPanel Container = null;
	static HashMap<String, JPanel> Pages = new HashMap<>();
	private static final CardLayout cards = new CardLayout();
	
	public static void initializeView() {
		Container = new JPanel(cards);
		System.out.println("[App] - Container Initialized Successfully");
		switchPage("Home", Home.HomePage);
	}
	
	public static void addPage(String name, JPanel page) {
		Container.add(name, page);
		System.out.println("[ViewManager] - Added " + name + " to accessible pages");
		Pages.put(name, page);
	}
	
	public static void switchPage(String name, JPanel page) {
		if (Pages.containsKey("Listings")) {
			Container.remove(Pages.get("Listings"));
			Pages.remove("Listings");
			addPage(name, page);
		}
		
		if (!Pages.containsKey(name)) {
			addPage(name, page);
		}
		
		cards.show(Container, name);
		System.out.println("[ViewManager] - Switch active page to " + name + ".");
	}
}

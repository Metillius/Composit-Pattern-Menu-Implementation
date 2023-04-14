import java.util.ArrayList;
import java.util.List;

interface MenuItemComponent {

    void add(MenuItemComponent menuItemComponent);

    void remove(MenuItemComponent menuItemComponent);

    MenuItemComponent find(String name);

    void display();

    double getPrice();
}

class MenuItem implements MenuItemComponent {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public void add(MenuItemComponent menuItemComponent) {

    }

    @Override
    public void remove(MenuItemComponent menuItemComponent) {

    }

    @Override
    public MenuItemComponent find(String name) {
        return null;
    }

    public void display() {
        System.out.println("-" + name + " $" + price);
    }

    public double getPrice() {
        return price;
    }
}

class MenuCategory implements MenuItemComponent {
    private String name;
    private List<MenuItemComponent> menuItems;

    public MenuCategory(String name) {
        this.name = name;
        this.menuItems = new ArrayList<>();
    }

    @Override
    public void add(MenuItemComponent menuItemComponent) {
        menuItems.add(menuItemComponent);
    }

    @Override
    public void remove(MenuItemComponent menuItemComponent) {
        menuItems.remove(menuItemComponent);
    }

    @Override
    public MenuItemComponent find(String name) {
        if (this.name.equals(name)) {
            return this;
        }
        for (MenuItemComponent menuItemComponent : menuItems) {
            MenuItemComponent result = menuItemComponent.find(name);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public void display() {
        System.out.println("+" + name);
        for (MenuItemComponent menuItemComponent : menuItems) {
            menuItemComponent.display();
        }
    }

    public double getPrice() {
        double total = 0.0;
        for (MenuItemComponent menuItemComponent : menuItems) {
            total += menuItemComponent.getPrice();
        }
        return total;
    }
}

class SafetyMenuItem implements MenuItemComponent {
    private MenuItemComponent menuItemComponent;

    public SafetyMenuItem(MenuItemComponent menuItemComponent) {
        this.menuItemComponent = menuItemComponent;
    }

    @Override
    public void add(MenuItemComponent menuItemComponent) {

    }

    @Override
    public void remove(MenuItemComponent menuItemComponent) {

    }

    @Override
    public MenuItemComponent find(String name) {
        return null;
    }

    public void display() {
        menuItemComponent.display();
    }

    public double getPrice() {
        return menuItemComponent.getPrice();
    }
}
public class Test {
    public static void main(String[] args) {
        // Create the menu hierarchy
        MenuItemComponent menu = new MenuCategory("Menu");

        MenuCategory starters = new MenuCategory("Starters");
        starters.add(new MenuItem("Caesar's Salad", 5.99));
        starters.add(new MenuItem("Mixed Salad", 6.99));
        starters.add(new MenuItem("Soup of The Day", 4.99));
        menu.add(starters);

        MenuCategory entrees = new MenuCategory("Entrees");
        MenuCategory chicken = new MenuCategory("Chicken");
        chicken.add(new MenuItem("Tikka Masala", 12.99));
        entrees.add(chicken);

        MenuCategory vegetarian = new MenuCategory("Vegetarian");
        vegetarian.add(new MenuItem("Pasta", 10.99));
        vegetarian.add(new MenuItem("Tofu Stir-Fry", 9.99));
        entrees.add(vegetarian);

        menu.add(entrees);

        MenuCategory desserts = new MenuCategory("Desserts");
        desserts.add(new MenuItem("Cheesecake", 7.99));
        desserts.add(new MenuItem("Souffle", 8.99));
        menu.add(desserts);

        MenuCategory beverages = new MenuCategory("Beverages");
        MenuCategory softDrinks = new MenuCategory("Soft Drinks");
        softDrinks.add(new MenuItem("Cola", 2.99));
        softDrinks.add(new MenuItem("Sprite", 2.99));
        beverages.add(softDrinks);
        beverages.add(new MenuItem("Tea", 1.99));
        beverages.add(new MenuItem("Coffee", 2.49));
        menu.add(beverages);

        // Display the menu
        System.out.println("Menu:");
        menu.display();

        // Test the find() method
        System.out.println("\nFinding 'Entrees':");
        MenuItemComponent entreesComponent = menu.find("Entrees");
        double entreesTotalPrice = entreesComponent.getPrice();
        System.out.println("Total price of Entrees: $" + entreesTotalPrice);

        System.out.println("\nFinding 'Soft Drinks':");
        MenuItemComponent softDrinksComponent = menu.find("Soft Drinks");
        double softDrinksTotalPrice = softDrinksComponent.getPrice();
        System.out.println("Total price of Soft Drinks: $" + softDrinksTotalPrice);
    }
}
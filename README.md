# menu-builder-console
## About the package
This is a simple menu builder for java console apps that create a navigable menu
## Usage
Create the whole logic in the instanciation if the menu

The very simple way to make the first menu with only one menu item that does nothing would be:

```JAVA
Menu menu = new Menu("Main Menu", new ArrayList<Menu.MenuItem>(){{
    add(new Menu.MenuItem("1", "Say Hello", null, null));
}});

menu.showMenu();
```

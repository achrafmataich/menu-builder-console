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

Here, we have created a menu named "Main Menu" that have only one menu item ``` #1 Say Hello ```
Even if we chose that option, it will do nothing.

the result if that piece of code would be:

```txt
[Main Menu]
    #1 Say Hello
    #q Quit
? :
```

We can see thet another option is added automatically so you don't need to add the option to close the menu, the library have already thought about it :)

Now let's try to make one of the options open another Menu, here's the process:

```JAVA
Menu menu = new Menu("Main Menu", new ArrayList<Menu.MenuItem>(){{
    add(new Menu.MenuItem("1", "Say Hello", null, null));
    add(new Menu.MenuItem("2", "Open Me", new Menu(
        "Opened Menu",
        new ArrayList<Menu.MenuItem>(){{
            add(new Menu.Item("1", "Do nothing", null, null));
        }}
    ), null));
}});

menu.showMenu();
```

Here, we have created a menu named "Main Menu" that have two menu items ``` #1 Say Hello ``` and ``` #2 Open Me ``` The second option have inside anothor Menu called "Opened Menu" with one option ``` #1 Do nothing ``` that does nothing :)

the result if that piece of code would be:

```txt
[Main Menu]
    #1 Say Hello
    #2 Open Me
    #q Quit
? : 2
[Main Menu] > [Opened Menu]
    #1 Do nothing
    #q Quit
? :
```

Did you see the Menu Builder making a path in the title bar so you can always know where are you now ?

> What if I want to do specific action when an options in that menu bar is chosen ?

Good question! Let's see how we can achieve that.

### Make a static method

First, create a method with no param and returns nothing inside a class:

```JAVA
public class HeyNinjas{
    public static void sayThanks(){
        System.out.println("Thank You Sooo Much!");
    }
}
```

### Add the static method to the MenuItem

Then, we use the reference of that static method to pass it inside the MenuItem constructor :

```JAVA
Menu menu = new Menu("Main Menu", new ArrayList<Menu.MenuItem>(){{
    add(new Menu.MenuItem("1", "Say Hello", null, null));
    add(new Menu.MenuItem("2", "Open Me", new Menu(
        "Opened Menu",
        new ArrayList<Menu.MenuItem>(){{
            add(new Menu.MenuItem("1", "Do nothing", null, null));
            add(new Menu.MenuItem("2", "Give a Dirham", null, HeyNinjas::sayThanks));
        }}
    ), null));
}});

menu.showMenu();
```

We have added a second menu item in the inner menu ``` #2 Give a Dirham ``` that execute ``` HeyNinjas::sayThanks ``` when chosen:

```txt
[Main Menu]
    #1 Say Hello
    #2 Open Me
    #q Quit
? : 2
[Main Menu] > [Opened Menu]
    #1 Do nothing
    #2 Give a Dirham
    #q Quit
? : 2
--------
Thank You Sooo Much!
--------
[Main Menu] > [Opened Menu]
    #1 Do nothing
    #2 Give a Dirham
    #q Quit
? :
```

The method is executed and printed within the two lines of eight dashes

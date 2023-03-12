package com.outsiderninjadevs.menubuilderconsole;

import java.util.ArrayList;
import java.util.Scanner;


public class Menu {

    private String title;
    private ArrayList<MenuItem> menuItems;
    private Menu supMenu = null;

    public Menu(String title, ArrayList<MenuItem> menuItems) {
        this.title = title;
        this.menuItems = menuItems;
        for (Menu.MenuItem menuItem : this.menuItems) {
            if (menuItem.getSubMenu() != null) {
                menuItem.getSubMenu().setSupMenu(this);
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Menu getSupMenu() {
        return supMenu;
    }

    public void setSupMenu(Menu supMenu) {
        this.supMenu = supMenu;
    }

    public String getFullPathMenuTitle() {
        return ((this.supMenu != null) ? this.supMenu.getFullPathMenuTitle() + " > " : "") + "[" + this.title + "]";
    }

    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        String choice = null;
        do {
            System.out.println(this.getFullPathMenuTitle());
            for (MenuItem menuItem : this.menuItems) {
                System.out.println("#" + menuItem.getKey() + "\t" + menuItem.getText());
            }
            System.out.println("#q\tQuit");
            System.out.print("? : ");
            choice = sc.nextLine();
            for (MenuItem menuItem : this.menuItems) {
                if (menuItem.getKey().equals(choice)) {
                    if (menuItem.getSubMenu() != null) {
                        menuItem.getSubMenu().showMenu();
                    } else if(menuItem.getF() != null) {
                        System.out.println("--------");
                        menuItem.getF().run();
                        System.out.println("--------");
                    } else {
                        System.out.println("!!ERROR");
                    }
                }
            }
        } while (!choice.equals("q"));
    }

    public static class MenuItem {
        private String key;
        private String text;
        private Menu subMenu;
        private Runnable f;

        MenuItem(String key, String text, Menu subMenu, Runnable f) {
            this.key = key;
            this.text = text;
            this.subMenu = subMenu;
            this.f = f;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Menu getSubMenu() {
            return subMenu;
        }

        public void setSubMenu(Menu subMenu) {
            this.subMenu = subMenu;
        }

        public Runnable getF() {
            return f;
        }

        public void setF(Runnable f) {
            this.f = f;
        }
    }

    public boolean test(){
        return true;
    }
}


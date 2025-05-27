package lt.viko.eif.mstrimaitis;

import lt.viko.eif.mstrimaitis.db.FarmerRepository;
import lt.viko.eif.mstrimaitis.MENU.UserMenu;

public class Main {
    public static void main(String[] args) {
        FarmerRepository farmerRepository = null; // Replace with a real or mock implementation
        UserMenu userMenu = new UserMenu();
        userMenu.showMenu();
    }
}
package lt.viko.eif.mstrimaitis.MENU;

import lt.viko.eif.mstrimaitis.db.FarmerRepository;
import lt.viko.eif.mstrimaitis.model.Farmer;
import lt.viko.eif.mstrimaitis.model.Farmers;
import lt.viko.eif.mstrimaitis.service.XmlTransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class UserMenu {

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private XmlTransformationService xmlService;

    private List<Farmer> farmers;

    private int displayMenu(Scanner input) {
        System.out.println("\nUSER MENU");
        System.out.println("1. List farmers");
        System.out.println("2. List all connections (farmers and arrivals)");
        System.out.println("3. Transform from POJO to XML");
        System.out.println("4. Save xml file");
        System.out.println("5. Exit");
        System.out.print("Choose: ");
        while (!input.hasNextInt()) {
            System.out.print("Enter a number: ");
            input.next();
        }
        return input.nextInt();
    }

    public void showMenu() {
        Scanner input = new Scanner(System.in);
        int userChoice;
        do {
            userChoice = displayMenu(input);
            input.nextLine(); // consume newline
            switch (userChoice) {
                case 1:
                    farmers = farmerRepository.findAll();
                    if (farmers.isEmpty()) {
                        System.out.println("No farmers found.");
                    } else {
                        for (Farmer farmer : farmers) {
                            System.out.println(farmer.getName() + " " + farmer.getSurname());
                        }
                    }
                    break;
                case 2:
                    farmers = farmerRepository.findAll();
                    if (farmers.isEmpty()) {
                        System.out.println("No farmers found.");
                    } else {
                        for (Farmer farmer : farmers) {
                            System.out.println(farmer);
                        }
                    }
                    break;
                case 3:
                    ensureFarmersLoaded();
                    if (farmers.isEmpty()) {
                        System.out.println("No farmers to transform.");
                    } else {
                        Farmers farmersObj = new Farmers(farmers);
                        xmlService.transformToXml(farmersObj);
                    }
                    break;
                case 4:
                    ensureFarmersLoaded();
                    if (farmers.isEmpty()) {
                        System.out.println("No farmers to save.");
                    } else {
                        Farmers farmersToSave = new Farmers(farmers);
                        String filePath = "src/main/resources/files/farmers.xml";
                        try {
                            xmlService.saveXml(farmersToSave, filePath);
                            System.out.println("XML file saved successfully at: " + filePath);
                        } catch (Exception e) {
                            System.out.println("Failed to save XML file: " + e.getMessage());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Thank you and goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (true);
    }

    private void ensureFarmersLoaded() {
        if (farmers == null) {
            farmers = farmerRepository.findAll();
        }
    }
}
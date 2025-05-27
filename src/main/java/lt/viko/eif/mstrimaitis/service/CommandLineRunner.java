package lt.viko.eif.mstrimaitis.service;

import lt.viko.eif.mstrimaitis.MENU.UserMenu;
import lt.viko.eif.mstrimaitis.model.Arrival;
import lt.viko.eif.mstrimaitis.model.Farmer;
import lt.viko.eif.mstrimaitis.db.FarmerRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    private final FarmerRepository farmerRepository;
    private final UserMenu userMenu;

    public CommandLineRunner(FarmerRepository farmerRepository, UserMenu userMenu) {
        this.farmerRepository = farmerRepository;
        this.userMenu = userMenu;
    }

    @Override
    public void run(String... args) throws Exception {
        if (farmerRepository.count() == 0) {
            Map<String, Farmer> farmerMap = new HashMap<>();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/db.changelog/farmer-arrival.csv")))) {
                String line = br.readLine(); // skip header
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    String name = parts[0];
                    String surname = parts[1];
                    int grain = Integer.parseInt(parts[2]);
                    int area = Integer.parseInt(parts[3]);
                    int staff = Integer.parseInt(parts[4]);
                    String details = parts[5];
                    LocalDate date = LocalDate.parse(parts[6]);

                    String key = name + surname;
                    Farmer farmer = farmerMap.getOrDefault(key, null);
                    if (farmer == null) {
                        farmer = new Farmer();
                        farmer.setName(name);
                        farmer.setSurname(surname);
                        farmer.setGrainCount(grain);
                        farmer.setAreaCount(area);
                        farmer.setStaffCount(staff);
                        farmer.setArrivals(new ArrayList<>());
                        farmerMap.put(key, farmer);
                    }
                    Arrival arrival = new Arrival();
                    arrival.setArrivalDetails(details);
                    arrival.setDate(date);
                    arrival.setFarmer(farmer);
                    farmer.getArrivals().add(arrival);
                }
            }
            farmerRepository.saveAll(farmerMap.values());
        }
        userMenu.showMenu();
    }
}
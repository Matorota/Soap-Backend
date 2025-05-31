package lt.viko.eif.mstrimaitis.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "farmer")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "farmer")
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @XmlTransient
    private Long id;

    @XmlElement
    private String name;

    @XmlElement
    private String surname;

    @XmlElement
    private int grainCount;

    @XmlElement
    private int areaCount;

    @XmlElement
    private int staffCount;

    @OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @XmlElementWrapper(name = "arrivals")
    @XmlElement(name = "arrival")
    private List<Arrival> arrivals;

    public Farmer() {}

    public Farmer(Long id, String name, String surname, int grainCount, int areaCount, int staffCount, List<Arrival> arrivals) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.grainCount = grainCount;
        this.areaCount = areaCount;
        this.staffCount = staffCount;
        this.arrivals = arrivals;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public int getGrainCount() { return grainCount; }
    public void setGrainCount(int grainCount) { this.grainCount = grainCount; }
    public int getAreaCount() { return areaCount; }
    public void setAreaCount(int areaCount) { this.areaCount = areaCount; }
    public int getStaffCount() { return staffCount; }
    public void setStaffCount(int staffCount) { this.staffCount = staffCount; }
    public List<Arrival> getArrivals() { return arrivals; }
    public void setArrivals(List<Arrival> arrivals) { this.arrivals = arrivals; }

    @Override
    public String toString() {
        return "\n\tFarmer { " +
                "\n\tId : " + id +
                "\n\tName : " + name +
                "\n\tSurname : " + surname +
                "\n\tGrain count : " + grainCount +
                "\n\tArea count : " + areaCount +
                "\n\tStaff count : " + staffCount +
                "\n\tArrivals : " + arrivals +
                "\n\t}";
    }
}
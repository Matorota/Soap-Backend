package lt.viko.eif.mstrimaitis.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import java.time.LocalDate;

@XmlRootElement(name = "arrival")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "arrival")
public class Arrival {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @XmlTransient
    private Long id;

    @XmlElement
    private String arrivalDetails;

    @XmlElement
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    @XmlTransient
    private Farmer farmer;

    public Arrival() {}

    public Arrival(Long id, String arrivalDetails, LocalDate date, Farmer farmer) {
        this.id = id;
        this.arrivalDetails = arrivalDetails;
        this.date = date;
        this.farmer = farmer;
    }

    // Getters and setters...

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getArrivalDetails() { return arrivalDetails; }
    public void setArrivalDetails(String arrivalDetails) { this.arrivalDetails = arrivalDetails; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Farmer getFarmer() { return farmer; }
    public void setFarmer(Farmer farmer) { this.farmer = farmer; }

    @Override
    public String toString() {
        return "\n\tArrival { " +
                "\n\tId : " + id +
                "\n\tDetails : " + arrivalDetails +
                "\n\tDate : " + date +
                "\n\t}";
    }
}
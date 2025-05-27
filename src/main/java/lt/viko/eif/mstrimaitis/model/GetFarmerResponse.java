package lt.viko.eif.mstrimaitis.model;
import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(namespace = "http://viko.eif.lt/farmers", name = "getFarmersResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetFarmerResponse {
    private Farmer farmer;

    public Farmer getFarmer() { return farmer; }
    public void setFarmer(Farmer farmer) { this.farmer = farmer; }
    @XmlElement(name = "farmer")
    private List<Farmer> farmers;

    public List<Farmer> getFarmers() {
        return farmers;
    }

    public void setFarmers(List<Farmer> farmers) {
        this.farmers = farmers;
    }
}
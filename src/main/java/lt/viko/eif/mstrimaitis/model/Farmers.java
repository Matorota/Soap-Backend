package lt.viko.eif.mstrimaitis.model;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "farmers")
@XmlAccessorType(XmlAccessType.FIELD)
public class Farmers {

    @XmlElement(name = "farmer")
    private List<Farmer> farmers;

    public Farmers() {}

    public Farmers(List<Farmer> farmers) {
        this.farmers = farmers;
    }

    public List<Farmer> getFarmers() {
        return farmers;
    }

    public void setFarmers(List<Farmer> farmers) {
        this.farmers = farmers;
    }
}
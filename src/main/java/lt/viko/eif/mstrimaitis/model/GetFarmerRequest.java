package lt.viko.eif.mstrimaitis.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "http://viko.eif.lt/farmers", name = "getFarmersRequest")
public class GetFarmerRequest {
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
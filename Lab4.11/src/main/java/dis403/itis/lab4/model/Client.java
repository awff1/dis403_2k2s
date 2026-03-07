package dis403.itis.lab4.model;

import dis403.itis.lab4.model.Person;
import jakarta.persistence.Entity;

//@Getter@Setter
@Entity
public class Client extends Person {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

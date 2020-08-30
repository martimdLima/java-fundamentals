package mapped_supperclass_inheritance;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "car_mapped_supperclass")
public class Car extends Vehicle{

    private String brand;

}

package mapped_supperclass_inheritance;

import javax.persistence.*;

@Entity
@Table(name = "boat_mapped_supperclass")
public class Boat extends Vehicle{

    private String engine;

}

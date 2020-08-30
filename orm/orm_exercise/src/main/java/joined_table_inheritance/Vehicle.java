package joined_table_inheritance;

import javax.persistence.*;

@Entity
@Table(name = "vehicle_joined_table")
@Inheritance(strategy = InheritanceType.JOINED)

public class Vehicle {

    @Id
    private Integer id;
    private Integer maxSpeed;
}
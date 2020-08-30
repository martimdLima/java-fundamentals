package single_table_inheritance;

import javax.persistence.*;

@Entity
@Table(name = "vehicle_single_table")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type", discriminatorType = DiscriminatorType.STRING)

public abstract class Vehicle {

    @Id
    private Integer id;
    private Integer maxSpeed;
}

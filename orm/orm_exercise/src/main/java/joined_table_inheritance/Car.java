package joined_table_inheritance;

import javax.persistence.*;

@Entity
@Table(name = "car_joined_table")

public class Car extends Vehicle{

    private Integer gears;
}

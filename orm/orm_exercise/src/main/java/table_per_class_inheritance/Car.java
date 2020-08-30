package table_per_class_inheritance;

import javax.persistence.*;

@Entity
@Table(name = "car_table_per_class")

public class Car extends  Vehicle{
    private Integer gears;
}

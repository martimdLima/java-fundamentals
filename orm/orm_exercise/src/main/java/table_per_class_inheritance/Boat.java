package table_per_class_inheritance;

import javax.persistence.*;

@Entity
@Table(name = "boat_table_per_class")

public class Boat extends Vehicle{
    private Integer engines;
}

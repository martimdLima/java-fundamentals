package joined_table_inheritance;

import javax.persistence.*;

@Entity
@Table(name = "boat_joined_table")

public class Boat extends Vehicle{

    private Integer engines;
}

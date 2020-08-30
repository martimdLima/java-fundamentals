package single_table_inheritance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("boat")

public class Boat extends Vehicle{

    private Integer engines;
}

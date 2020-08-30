package single_table_inheritance;

import javax.persistence.*;

@Entity
@DiscriminatorValue("car")

public class Car extends Vehicle{

    private Integer gears;
}

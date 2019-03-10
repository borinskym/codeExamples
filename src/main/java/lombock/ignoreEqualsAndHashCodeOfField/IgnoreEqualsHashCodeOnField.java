package lombock.ignoreEqualsAndHashCodeOfField;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(exclude = {"field2"})
public class IgnoreEqualsHashCodeOnField {

    Integer field1;
    Integer field2;
    String field3;
    Long field4;
}

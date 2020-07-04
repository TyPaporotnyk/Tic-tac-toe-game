package Field;

import org.junit.Before;
import org.junit.Test;

public class FieldTest {

    @Before
    public void createField(){
        Field.createField(6,8);
    }

    @Test
    public void paintTest(){
        Field.paint();
    }
}

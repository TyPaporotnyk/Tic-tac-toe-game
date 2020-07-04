package Player;

import Field.Field;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class UserTest {
    User us1;
    User us2;

    @Before
    public void setUsers(){
        us1 = new User("Daniil", '0', User.UsersType.HUMAN);
        us2 = new User("Computer", 'X', User.UsersType.HUMAN);
    }

    @Test
    public void getUsers(){
        List<User> users = User.getUsers();
        users.forEach(System.out::println);
    }

    @Test
    public void stepTest(){
        Field.createField();

    }

}
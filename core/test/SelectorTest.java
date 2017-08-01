import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Selector;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SelectorTest {

    @Test
    public void testFirstElement() {
        Selector s = createData();

        assertEquals(2,s.count());
    }

    private Selector createData() {
        Selector s = new Selector(null);
        s.addUnic(new Vector2(1,3));
        s.addUnic(new Vector2(1,4));
        s.addUnic(new Vector2(1,5));
        return s;
    }

    @Test
    public void testIgnorDuplicate() {
        Selector s = new Selector(null);
        s.addUnic(new Vector2(1,3));
        s.addUnic(new Vector2(1,3));

        assertEquals(1,s.count());
    }

    @Test
    public void testEqualsWithLast(){
        Selector s = createData();

        assertTrue(s.equalsWithPrevLast(new Vector2(1,4)));
    }

}

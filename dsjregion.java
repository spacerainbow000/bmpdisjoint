import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aembree on 7/15/2017.
 */
public class dsjregion
{
    public ArrayList<Point> coordinates;
    public boolean type;

    public boolean has(Point coord)
    {
        for ( Point test : coordinates )
        {
            if ( test.x == coord.x && test.y == coord.y )
                return true;
        }
        return false;
    }

    public dsjregion(Point coord, boolean type)
    {
        coordinates = new ArrayList<Point>();
        coordinates.add(coord);
        this.type=type;
    }
}

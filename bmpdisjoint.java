import javax.swing.plaf.synth.Region;
import java.awt.*;
import java.util.ArrayList;

public class bmpdisjoint {
    public static void main(String[] args)
    {
        boolean[][] image = { { true, true, true }, { false, false, false }};
        System.out.println(countdisjoints(image));
        boolean[][] image2 = {
                { false, true, false, false, true },
                { true, true, false, false, false },
                { false, false, false, false, false },
                { true, true, false, false, false },
                { true, true, false, false, true }
        };

        System.out.println(countdisjoints(image2));

        for ( int x = 0; x < image2.length; x++ ) {
            for (int y = 0; y < image2.length; y++) {
                if ( image2[y][x] )
                    System.out.print("1");
                else
                    System.out.print("0");
            }
            System.out.println();
        }

    }

    static int countdisjoints(boolean[][] image)
    {
        ArrayList<dsjregion> regions = new ArrayList<dsjregion>();

        for ( int x = 0; x < image.length; x++ )
        {
            for ( int y = 0; y < image.length; y++ )
            {
                //break if already part of a region
                boolean found = false;
                for ( dsjregion test : regions )
                {
                    if ( test.has(new Point(x, y)) )
                    {
                        found = true;
                        break;
                    }
                }
                if ( found )
                    continue;

                //create a new region and get every coordinate that is a part of the new region
                regions.add(new dsjregion(new Point(x, y), image[x][y]));
                dsjregion workingregion = regions.get(regions.size() - 1);

                buildregion(workingregion, image);
            }
        }

        return regions.size();
    }

    public static void buildregion(dsjregion region, boolean[][] image)
    {
        //check for surrounding regions could totally be recursive
        //js

        ArrayList<Point> checked = new ArrayList<Point>();
        ArrayList<Point> unchecked = new ArrayList<Point>();

        unchecked.add(region.coordinates.get(0));
        while (unchecked.size() > 0)
        {
            //take last item in unchecked
            //get all the surrounding coordinates and see whether they are the same value as the item being checked
            //if they are:
            //    break if coordinate is in either checked or unchecked
            //    otherwise, add the coordinate to unchecked
            //remove the current coordinate from unchecked and add it to checked

            int loc = unchecked.size() - 1;
            Point checking = unchecked.get(loc);

            //get surrounding coordinates
            ArrayList<Point> testing = new ArrayList<Point>();
            if ( checking.x + 1 != image.length) { testing.add(new Point(checking.x + 1, checking.y)); }
            if ( checking.y + 1 != image.length) { testing.add(new Point(checking.x, checking.y + 1)); }
            if ( checking.y != 0 ) { testing.add(new Point(checking.x, checking.y - 1)); }
            if ( checking.x != 0 ) { testing.add(new Point(checking.x - 1, checking.y)); }


            //check valid surrounding coords
            for ( Point p : testing )
            {
                //match value
                if (image[p.x][p.y] != image[checking.x][checking.y])
                    continue;

                //see if point is checked or queued to check
                boolean found = false;
                for ( Point c : checked )
                {
                    if (p.x == c.x && p.y == c.y)
                    {
                        found = true;
                        break;
                    }
                }
                if (found)
                    continue;
                for ( Point c : unchecked )
                {
                    if (p.x == c.x && p.y == c.y)
                    {
                        found = true;
                        break;
                    }
                }
                if (found)
                    continue;

                //value is the same as the rest of region, and coordinate isn't known member of region -- add so surrounding coordinates can be checked
                unchecked.add(p);
            }

            //remove item from unchecked and move it to checked
            checked.add(unchecked.get(loc));
            unchecked.remove(loc);
        }

        for (Point p : checked)
            region.coordinates.add(p);
    }
}

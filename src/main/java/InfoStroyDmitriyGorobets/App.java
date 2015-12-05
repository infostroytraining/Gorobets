package InfoStroyDmitriyGorobets;

import InfoStroyDmitriyGorobets.MathUtils.MathUtils;

import java.util.Arrays;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */
public class App 
{
    public static void main( String[] args ) {
        int ar[] = new MathUtils().getPrimeSeries(20);
        System.out.println(Arrays.toString( ar));
    }
}

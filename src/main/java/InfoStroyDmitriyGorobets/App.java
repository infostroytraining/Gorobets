package InfoStroyDmitriyGorobets;

import InfoStroyDmitriyGorobets.MathUtils.MathUtils;

import java.util.Arrays;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */
public class App 
{
    public static void main( String[] args ) {
        int res= new MathUtils().getGreatestCommonDivider(0,0);
        System.out.println(res);
    }
}

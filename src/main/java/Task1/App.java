package Task1;

import Task1.TextUtils.TextUtils;
import Task1.TriangleUtils.TriangleUtils;
import javafx.scene.shape.TriangleMesh;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */
public class App 
{
    public static void main( String[] args ) {
//        String string = "hi.my name is Dima.i want to be a Java developer,but i need more slills to have.that's why " +
//                "I study every day and I know,I'll achive my goal";
//       String res= new TextUtils().correctText(string);
//        System.out.println(res);
        TriangleUtils t = new TriangleUtils();
        System.out.println(t.getTriangleArea(2,4,5));
//        System.out.println(t.isTriangle(2, 5, 6));

    }
}

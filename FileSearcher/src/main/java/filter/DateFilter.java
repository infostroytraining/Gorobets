package filter;


import java.io.File;


/**
 * Created by invincible_g_d on 12/22/15.
 */
public class DateFilter extends Filter {

    private long time;



    public DateFilter(Filter next, long time) {
        super(next);
        this.time = time;
    }

    @Override
    public boolean currentAccept(File file) {
        if (file != null) {
            long modifTime = file.lastModified();
            return modifTime <= time;
        }
        return false;
    }
}

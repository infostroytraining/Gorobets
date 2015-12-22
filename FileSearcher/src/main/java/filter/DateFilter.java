package filter;

import java.io.File;


/**
 * This class compare time parameter from command line with last modifier time of file.
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

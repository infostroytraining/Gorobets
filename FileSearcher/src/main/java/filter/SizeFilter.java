package filter;

import java.io.File;

/**
 * This class compare a size parameter from the command line with a size of file.
 */
public class SizeFilter extends Filter {

    private long size;


    public SizeFilter(Filter next, long size) {
        super(next);
        this.size = size;
    }

    @Override
    public boolean currentAccept(File file) {
        if (file != null) {
            long fileSize = file.length();
            return fileSize <= size;
        }
        return false;
    }
}

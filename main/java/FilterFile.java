import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Administrator on 9/16/14.
 */
public class FilterFile implements FilenameFilter {
    private String filter_name = ".TIF";

    @Override
    public boolean accept(File dir, String name) {

        return name.endsWith(filter_name);
    }
}

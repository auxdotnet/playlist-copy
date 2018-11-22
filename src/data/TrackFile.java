package data;

import java.io.File;

public class TrackFile extends File {
    private boolean doCopy;

    public TrackFile(String pathname) {
        super(pathname);
        doCopy = true;
    }

    public TrackFile(String parent, String child) {
        super(parent, child);
        doCopy = true;
    }

    public boolean isDoCopy() {
        return doCopy;
    }

    public void setDoCopy(boolean doCopy) {
        this.doCopy = doCopy;
        System.out.println("changed: " + getAbsolutePath() + " to " + doCopy);
    }
}

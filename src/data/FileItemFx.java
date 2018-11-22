package data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;

public class FileItemFx {
    private final StringProperty title = new SimpleStringProperty();
    private final BooleanProperty on = new SimpleBooleanProperty();
    private TrackFile dataFile;

    public TrackFile getDataFile() {
        return dataFile;
    }

    public void setDataFile(TrackFile dataFile) {
        this.dataFile = dataFile;
    }

    public FileItemFx(TrackFile file, boolean on) {
        setDataFile(file);
        setTitle(file.getAbsolutePath());
        setOn(on);
    }

    public final StringProperty titleProperty() {
        return this.title;
    }

    public final String getTitle() {
        return this.titleProperty().get();
    }

    public final void setTitle(final String title) {
        this.titleProperty().set(title);
    }

    public final BooleanProperty onProperty() {
        return this.on;
    }

    public final boolean isOn() {
        return this.onProperty().get();
    }

    public final void setOn(final boolean on) {
        this.onProperty().set(on);
    }

    @Override
    public String toString() {
        return getTitle();
    }

}

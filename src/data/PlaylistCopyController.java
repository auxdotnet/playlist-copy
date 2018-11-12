package data;

public class PlaylistCopyController {
    private static PlaylistCopyController instance;

    // Singleton getInstance
    public static PlaylistCopyController getInstance() {
        if (instance == null) {
            instance = new PlaylistCopyController();
        }
        return instance;
    }

    private PlaylistCopyController() {

    }

    public void startCopying() {

    }
}

import io.reactivex.rxjava3.core.Observable;
import model.Photo;
import util.PhotoDownloader;
import util.PhotoProcessor;
import util.PhotoSerializer;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhotoCrawler {

    private static final Logger log = Logger.getLogger(PhotoCrawler.class.getName());

    private final PhotoDownloader photoDownloader;

    private final PhotoSerializer photoSerializer;

    private final PhotoProcessor photoProcessor;

    public PhotoCrawler() throws IOException {
        this.photoDownloader = new PhotoDownloader();
        this.photoSerializer = new PhotoSerializer("./photos");
        this.photoProcessor = new PhotoProcessor();
    }

    public void resetLibrary() throws IOException {
        photoSerializer.deleteLibraryContents();
    }

    public void downloadPhotoExamples() throws IOException {
        Observable<Photo> downloadedExamples = photoDownloader.getPhotoExamples();
        downloadedExamples.subscribe(photo -> photoSerializer.savePhoto(photo));
    }

    public void downloadPhotosForQuery(String query) throws IOException, InterruptedException {
        Observable<Photo> downloadedExamples = photoDownloader.searchForPhotos(query);
        downloadedExamples.subscribe(photo -> photoSerializer.savePhoto(photo), error -> System.out.println("Error"));
    }

    public void downloadPhotosForMultipleQueries(List<String> queries) throws IOException, InterruptedException {
        Observable<Photo> downloadedExamples = photoDownloader.searchForPhotos(queries);
        downloadedExamples.subscribe(photo -> photoSerializer.savePhoto(photo), error -> System.out.println("Error"));
    }
}

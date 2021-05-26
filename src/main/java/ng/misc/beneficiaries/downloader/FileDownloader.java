package ng.misc.beneficiaries.downloader;

import ng.misc.beneficiaries.batch.JobStarter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class FileDownloader implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(FileDownloader.class);
    private String sourceUrl;
    private String targetFile;
    private JobStarter jobStarter;
    private String name;

    public FileDownloader(String name, String sourceUrl, String targetFile, JobStarter jobStarter) {
        this.sourceUrl = sourceUrl;
        this.targetFile = targetFile;
        this.jobStarter = jobStarter;
        this.name = name;
    }

    @Override
    public void run() {
        logger.info("Downloading file {} to {}", sourceUrl, targetFile);
        try (ReadableByteChannel readableByteChannel =
                     Channels.newChannel(new URL(sourceUrl).openStream())) {
            FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
            //TODO: add progress tracking?
            fileOutputStream.getChannel()
                    .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            logger.info("Completed downloading file {} to {}", sourceUrl, targetFile);
            jobStarter.startJob(name);
        } catch (MalformedURLException e) {
            logger.error("Invalid URL, check the download URL in settings", e);
        } catch (IOException e) {
            logger.error("Download has failed", e);
        }
    }
}

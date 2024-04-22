package ex03;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class MyThread implements Runnable{
    private int num;
    private UrlsList urlsList;

    public MyThread(int num, UrlsList urlsList){
        this.num = num;
        this.urlsList = urlsList;
    }
    @Override
    public void run() {
        while (!urlsList.isAllUrlDownloaded()){
            String url = urlsList.getUrl();
            int readByte, fileNum;
            File file;
            byte[] dataBuff = new byte[1024];
            if(url != null){
                file = getFile(url);
                try (BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream());
                     FileOutputStream fileOutputStream = new FileOutputStream(file))
                {
                    fileNum = urlsList.getIndexUrl(url);
                    System.out.println("Thread-" + num + " start download file number " + fileNum);

                    while((readByte = inputStream.read(dataBuff)) != -1){
                        fileOutputStream.write(dataBuff, 0, readByte);
                    }
                    System.out.println("Thread-" + num + " finish download file number " + fileNum);
                } catch (IOException e) {
                    System.err.println("No such file " + e.getMessage() + "with URL " + url);
                }
            }
        }
    }

    private File getFile(String url) {
        File file;
        String[] strings = url.split("/");

        file = new File(strings[strings.length - 1]);
        return file;
    }
}

package ex03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UrlsList {
    private final String PATH_TO_URL = "files_urls.txt";
    private boolean isAllUrlDownloaded = false;
    private Map<Integer, String> urlsMap = new HashMap<>();
    private int key = 1;

    public UrlsList() throws IOException {
        List<String> data = Files.readAllLines(Paths.get(PATH_TO_URL));
        if(data.isEmpty()){
            throw new RemoteException("No one URL in file");
        }
        for(String s : data){
            String buff = s.trim();
            String[] val = buff.split("\\s+");
            urlsMap.put(Integer.parseInt(val[0]), val[1]);
        }
    }

    public int getIndexUrl(String s){
        Set<Map.Entry<Integer, String>> entrySet = urlsMap.entrySet();
        for (Map.Entry<Integer, String> pair : entrySet) {
            if (s.equals(pair.getValue())) {
                return pair.getKey();
            }
        }
        return -1;
    }

    public synchronized String getUrl(){
        if(!urlsMap.containsKey(key)){
            return null;
        }
        String res = urlsMap.get(key++);
        if(!urlsMap.containsKey(key)){
            this.isAllUrlDownloaded = true;
        }
        return res;
    }

    public synchronized boolean isAllUrlDownloaded(){
        return this.isAllUrlDownloaded;
    }
}

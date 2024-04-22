package ex00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
    private static final String FILE_NAME = "signatures.txt";
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static final String RESULT_FILE_NAME = "result.txt";

    public static void main(String[] args) {
        Map<String, String> signatures = new HashMap<>();
        try(FileInputStream fileInputStream = new FileInputStream(FILE_NAME)){
            Scanner scanner = new Scanner(fileInputStream);
            while(scanner.hasNextLine()){
                String address = scanner.nextLine();
                String[] addressArray = address.split(",");
                signatures.put(addressArray[0], addressArray[1].replaceAll("\\s+", ""));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        Scanner scanner = new Scanner(System.in);
        String inputFile = scanner.nextLine();
        while (!inputFile.equals("42")){
            try (FileInputStream fileInputStream = new FileInputStream(inputFile)){
                byte[] bytesSignature = new byte[8];
                int readenBytes = fileInputStream.read(bytesSignature, 0, 8);
                if(readenBytes == 8){
                    compareSignature(bytesToHex(bytesSignature), signatures);
                } else {
                    throw new Exception("File too small for read bytes");
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            inputFile = scanner.nextLine();
        }
    }

    private static void compareSignature(String signature, Map<String, String> map){
        try (FileOutputStream fileOutputStream = new FileOutputStream(RESULT_FILE_NAME)){
            for(Map.Entry<String, String> signatureFromMap : map.entrySet()){
                if(signature.contains(signatureFromMap.getValue())){
                    fileOutputStream.write(signatureFromMap.getKey().getBytes());
                    fileOutputStream.write('\n');
                    System.out.println("PROCESSED");
                    return;
                }
            }
            System.out.println("UNDEFINED");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}

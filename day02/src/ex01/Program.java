package ex01;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.*;

public class Program {
    private static final String DICTIONARY_FILE_NAME = "dictionary.txt";

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Wrong number of arguments");
            System.exit(-1);
        }
        String dataFromFirstFile = getDataFromFile(args[0]);
        String dataFromSecondFile = getDataFromFile(args[1]);

        ArrayList<String> firstFileDataArray = new ArrayList<>(Arrays.asList(dataFromFirstFile.split("\\s+")));
        ArrayList<String> secondFileDataArray = new ArrayList<>(Arrays.asList(dataFromSecondFile.split("\\s+")));
        Map<String, Integer> firstFileFrequency = countWords(firstFileDataArray);
        Map<String, Integer> secondFileFrequency = countWords(secondFileDataArray);

        HashSet<String> dictionary = new HashSet<>();
        dictionary.addAll(firstFileFrequency.keySet());
        dictionary.addAll(secondFileFrequency.keySet());
        createFileDictionary(dictionary);

        String similar = createVectors(dictionary, firstFileFrequency, secondFileFrequency);
        System.out.println("Similarity = " + similar);

    }

    private static String getDataFromFile(String filename) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(filename)) {
            Scanner scanner = new Scanner(fileReader);
            String buff;
            while (scanner.hasNextLine()) {
                buff = scanner.nextLine();
                stringBuilder.append(buff);
            }
            fileReader.close();
            scanner.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        return stringBuilder.toString();
    }

    private static void createFileDictionary(HashSet<String> dictionary) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(DICTIONARY_FILE_NAME)) {
            for (String s : dictionary) {
                fileOutputStream.write(s.getBytes());
                fileOutputStream.write(' ');
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    private static Map<String, Integer> countWords(ArrayList<String> arrayList) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : arrayList) {
            Integer count = Collections.frequency(arrayList, s);
            map.put(s, count);
        }
        return map;
    }

    private static String createVectors(HashSet<String> dictionary, Map<String, Integer> firstFileFrequency, Map<String, Integer> secondFileFrequency) {
        ArrayList<String> arrayList = new ArrayList<>(dictionary);
        Collections.sort(arrayList);
        int size = arrayList.size();
        Integer[] vector1 = new Integer[size];
        Integer[] vector2 = new Integer[size];

        for (int i = 0; i < size; i++) {
            if (firstFileFrequency.get(arrayList.get(i)) == null) {
                vector1[i] = 0;
            } else {
                vector1[i] = firstFileFrequency.get(arrayList.get(i));
            }
            if (secondFileFrequency.get(arrayList.get(i)) == null) {
                vector2[i] = 0;
            } else {
                vector2[i] = secondFileFrequency.get(arrayList.get(i));
            }
        }
        double similar = calculateTheFrequency(vector1, vector2, arrayList);
        return String.format("%.3f", similar);
    }

    private static double calculateTheFrequency(Integer[] vectorA, Integer[] vectorB, ArrayList<String> arrayList) {
        int numerator = 0;
        double denominator = 0;
        double numeratorFirstFile = 0;
        double numeratorSecondFile = 0;

        for (int i = 0; i < arrayList.size(); i++) {
            numerator += vectorA[i] * vectorB[i];
            numeratorFirstFile += vectorA[i] * vectorA[i];
            numeratorSecondFile += vectorB[i] * vectorB[i];
        }

        denominator = Math.sqrt(numeratorFirstFile) * Math.sqrt(numeratorSecondFile);
        return numerator / denominator;
    }
}

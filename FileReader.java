import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileReader {
    private String title, label;
    private Map<String, Double> dataMap;
    public FileReader(String pathname) {GetDataFromFile(pathname);}
    public String getTitle() {
        return title;
    }
    public String getLabel() {
        return label;
    }
    public Map<String, Double> getDataMap() {
        return dataMap;
    }
    private void GetDataFromFile(String pathname) {
        File file = new File(pathname);
        List<String> linelist = new ArrayList<>();
        //先读取文件，将文件内容按line 分进list，并且判断是否在读取的时候有异常
        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                linelist.add(line);
            }
            this.title = linelist.get(0);
            this.label = linelist.get(1);
            processData(linelist);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
    private void processData(List<String> linelist) {
        dataMap = new HashMap<>();
        //处理文件中的相关信息
        for (int i = 2; i < linelist.size(); i++) {
            String line = linelist.get(i);
            String[] values = line.split(" ");
            processValues(values, 0, "", 0);
        }
    }
    private void processValues(
            String[] values, int index, String string, double cost) {
        if (index >= values.length) {
            dataMap.put(string, cost);
            return;
        }
        String currentValue = values[index];
        if (isaWord(currentValue)) {
            string += " " + currentValue;
            processValues(values, index + 1, string, cost);
        } else {
            cost = Double.parseDouble(currentValue);
            processValues(values, index + 1, string, cost);
        }
    }
    private boolean isaWord(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }}


mport java.io.*;
import java.util.Scanner;

public class Sender {
   public static void main(String[] args) {
        List<String> items = Arrays.asList("Apple", "Banana", "Cherry", "Date");
        
        // 問題 1: 冗餘的 index 循環與字串拼接 (Sourcery 會建議改用 StringBuilder 或 String.join)
        String result = "";
        for (int i = 0; i < items.size(); i++) {
            result += items.get(i) + ",";
        }
        System.out.println("Items: " + result);

        // 問題 2: 複雜且可簡化的條件判斷 (Sourcery 會建議合併條件或簡化為 return)
        checkStatus("ACTIVE");

        // 問題 3: 資源管理問題 (未正確關閉 File 資源，Sourcery 會建議使用 try-with-resources)
        readFile("test.txt");
    }

    public static void checkStatus(String status) {
        // 這是一個典型的「箭頭型」代碼，層次過深
        if (status != null) {
            if (status.equals("ACTIVE")) {
                System.out.println("Status is active");
            } else {
                if (status.equals("INACTIVE")) {
                    System.out.println("Status is inactive");
                }
            }
        }
    }

    public static void readFile(String fileName) {
        try {
            // 故意不使用 try-with-resources
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            // 如果在讀取時發生異常，這裡可能永遠不會被執行到
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

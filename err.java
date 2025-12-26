mport java.io.*;
import java.util.Scanner;

public class Sender {
    public static void main(String[] args) 
    {
             Scanner scanner = new Scanner(System.in);
             System.out.print("請輸入要 Ping 的伺服器位址: ");
             String ip = scanner.nextLine();

        // 漏洞點：直接將使用者輸入拼接到系統指令中
        // 如果使用者輸入 "8.8.8.8 && dir" (Windows) 或 "8.8.8.8 ; ls" (Linux)
        // 系統會先執行 ping，接著執行後面的惡意指令
        String command = "ping -n 1 " + ip; 

        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

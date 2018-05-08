import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Save {
    public static void main(String[] args) throws IOException {
       /** Здесь создаол только два потока для проверки*/

        String[] sourses = new String[5];
        sourses[0] = "C:/test/test2.txt";
        sourses[1] = "C:/test/test2.txt";
        sourses[2] = "C:/test/test3.txt";
        sourses[3] = "C:\\test\\test4.txt";
        sourses[4] = "C:\\test\\test5.txt";

        String[] word = new String[5];
        word[0] = "обман";
        word[1] = "таков";
        word[2] = "стоял";
        word[3] = "весна";
        word[4] = "любовь";
        String res = "C:\\test\\test6.txt";
        Search search = new Search(sourses, word, res);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    search.seach();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    search.saveWords(search.seach(),res);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        long startTime = System.nanoTime();
        t1.start();
        t2.start();
        long timeSpent = System.nanoTime() - startTime;
        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");

    }
}

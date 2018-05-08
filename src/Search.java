import java.io.*;
import java.net.URL;
/**  @author Azat Tinmirshin*/

public class Search {

    private String[] sources;
    private String[] word;
    private String res;


    public Search(String[] sources, String[] word, String res) throws IOException {
        this.sources = sources;
        this.word = word;
        this.res=res;

    }
/** Метод использует два массива. Массив ресурсов и массив слов*/
    public synchronized String seach() throws IOException {
        String slovo = null;
        String name;

        for (int a = 0; a < sources.length; a++) {
            if (sources[a].contains("http://")) {
                BufferedReader fin = new BufferedReader(new InputStreamReader(new URL(sources[a]).openConnection().getInputStream()));
            }

            BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(sources[a]), "Cp1251"));
/** В цикле осуществляеться поиск слов в ресурсах. Строки парсяться по предоложениям
 * И складывыаеться в String */
            while ((name = fin.readLine()) != null) {
                String [] words = name.split("(\r\n)|(\n)|([.!?]\\s++)");
                for (int i = 0; i < words.length; i++) {
                    for (int j = 0; j < word.length; j++) {
                        if (words[i].contains(word[j]))
                        {
                           slovo = words[i];
                           slovo+=slovo;
                        }

                    }
                }

                }
            }
            return slovo;
    }
    /**  Метод получает результат работы метода search и  записывает в файл */
    public synchronized boolean saveWords(String s, String res) throws IOException {
        if(s==null){
            return false;
        }
        try(FileOutputStream fos=new FileOutputStream(res))
        {
            byte[] buffer = s.getBytes();
            fos.write(buffer, 0, buffer.length);
        }

        return true;
    }




    /*public static void main(String[] args) throws IOException {
        String[] sourses = new String[5];
        sourses[0] = "C:/test/test2.txt";
        sourses[1]= "C:/test/test2.txt";
        sourses[2]= "C:/test/test3.txt";
         sourses[3]= "C:\\test\\test4.txt";
        sourses[4]= "C:\\test\\test5.txt";

        String[] word = new String[5];
        word[0] = "обман";
        word[1] = "таков";
        word[2]= "стоял";
        word[3]= "весна";
        word[4] = "любовь";
        String res = "C:\\test\\test6.txt";
        long startTime = System.nanoTime();


        Search search = new Search(sourses, word, res);
        search.seach();
     search.saveWords(search.seach(), res);
        long timeSpent = System.nanoTime() - startTime;
        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");



    }*/
}











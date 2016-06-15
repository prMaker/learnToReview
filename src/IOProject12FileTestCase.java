/**
 * Created by Administrator on 2016/6/15.
 */

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

/**
 * file相关操作
 * 1. 删除不为空的file文件夹
 */
public class IOProject12FileTestCase {

    @Test
    public void bufferedReaderAndWriterTest() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/Javasoft/《歌德谈话录》.TXT")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/Javasoft/gedeTalk.txt")));

        String string = bufferedReader.readLine();
        while(string!=null){
            bufferedWriter.newLine();
            bufferedWriter.write(string);
            bufferedWriter.flush();
            string=bufferedReader.readLine();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }

    @Test
    public void fileCopyTest() throws IOException {
        FileInputStream fileInputStream1 = new FileInputStream("D:/Javasoft/myweb_04.zip");
        FileInputStream fileInputStream2 = new FileInputStream("D:/Javasoft/myweb");
        String file1 = DigestUtils.md5Hex(fileInputStream1);
        String file2 = DigestUtils.md5Hex(fileInputStream2);
        System.out.println(file1+":"+file2);
        Assert.assertEquals(file1,file2);
    }

    @Test
    public void copyTest() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:/Javasoft/myweb_04.zip");
        FileOutputStream fileOutputStream = new FileOutputStream("D:/Javasoft/myweb");

        byte[] bytes = new byte[1024*8];
        int n = fileInputStream.read(bytes);
        while(n>0){
            if(n==1024*8){
                fileOutputStream.write(bytes);
            }
            if(n<1024*8){
                fileOutputStream.write(bytes,0,n);
            }
            fileOutputStream.flush();
            n = fileInputStream.read(bytes);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }


    @Test
    public void fileOutputStreamTest() throws IOException {
            FileOutputStream fileOutputStream = new FileOutputStream("D:/file.txt");
            String string = "Hello World!";
            fileOutputStream.write(string.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
    }

    @Test
    public void fileInputStreamTest() throws IOException {
            FileInputStream fileInputStream = new FileInputStream("D:/file.txt");
            byte[] bytes = new byte[1024];
            fileInputStream.read(bytes);
            String string = new String(bytes).trim();
            System.out.println(string);
            fileInputStream.close();
    }

    @Test
    public void factorialTest(){
        System.out.println(factorial(8));
    }

    public int factorial(int n){
        if(n==1){
            return 2;
        }
        return 2*factorial(n-1);
    }


    @Test
    public void rabbitTest(){
        System.out.println(rabbit(7));
    }

    public int rabbit(int i){
        if(i==1){
            return 1;
        }
        if(i==2){
            return 2;
        }
        return rabbit(i-1)+rabbit(i-2);
    }

    /**
     * 文件循环删除操作
     *
     */
    @Test
    public void fileDelete() {
        File file = new File("D:/file");
        fileDel(file);
    }

    private void fileDel(File file){
        if(file.isFile()){
            file.delete();
            return;
        }
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File fi : files ){
                fileDel(fi);
            }
            file.delete();
            return;
        }
    }


    /**
     * 文件CRUD操作
     * @throws IOException
     */
    @Test
    public void fileCRUDTest() throws IOException {
        File file = new File("D:/file.txt");
        file.createNewFile();
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        }
        System.out.println("是文件：" + file.isFile());
        System.out.println("是文件夹：" + file.isDirectory());
        System.out.println("相对路径：" + file.getPath());
        System.out.println("绝对路径：" + file.getAbsolutePath());
        System.out.println("文件名：" + file.getName());
        System.out.println("文件大小Long：" + file.length());
        File directory1 = new File("D:/file/file/file1");
        File directory2 = new File("D:/file/file/file2");
        File directory3 = new File("D:/file");

        directory1.mkdirs();
        directory2.mkdirs();
        String[] s = directory3.list();
        for (String str : s) {
            System.out.println("file列表：" + str);

        }
        File[] files = file.listFiles();
    }
}

package com.demo.tools;

import java.io.*;

public class FileOperation {

    /**
     * 创建文件夹
     * @param path
     * @return
     */
    public File createFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 复制文件夹
     * @param source_path
     * @param dest_path
     * @return
     */
    public boolean copyFile(String source_path, String dest_path) {
        System.out.println("source_path:"+ source_path);
        System.out.println("dest_path:"+ dest_path);
        boolean flag = false;
        createFile(dest_path);
        File[] file_source = new File(source_path).listFiles();
        for (int i = 0; i < file_source.length; i++) {
            if (file_source[i].isFile()) {
                try {
                    FileInputStream input = new FileInputStream(file_source[i]);
                    FileOutputStream output = new FileOutputStream(dest_path
                            + "/" + file_source[i].getName());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) > 0) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                // 如果发现源目录下包含子目录的话，则递归调用copyFile()方法;
                if (file_source[i].isDirectory()) {
                    copyFile(source_path + "/" + file_source[i].getName(),
                            dest_path + "/" + file_source[i].getName());
                }
            }
            flag = true;
        }
        return flag;
    }

    /**
     * 读流
     * @param path
     * @return
     */
    public String readStream(String path) {
        FileInputStream fileinputstream = null;
        InputStreamReader reader = null;
        //byte[] bytes = null;
        try {
            fileinputstream = new FileInputStream(path);
            reader = new InputStreamReader(fileinputstream,"UTF-8");

            //bytes = new byte[1024 * 5];
            //fileinputstream.read(bytes);
            //fileinputstream.close();
            BufferedReader br = new BufferedReader(reader);
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
            br.close();
            reader.close();
            return content.toString();
        }
        catch (Exception e) {
            System.out.println(e);
            return "";
        }
        finally {
            if (fileinputstream != null) {
                try {
                    fileinputstream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * 写流
     * @param template
     * @param path
     * @param id
     */
    public void writeStream(String template, String path, Integer id) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        path = path + "/content" + id + ".html";
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter op = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            op = new OutputStreamWriter(fileOutputStream,"UTF-8");
            //byte tag_bytes[] = template.getBytes("UTF-8");
            //fileOutputStream.write(tag_bytes);
            op.append(template);
            op.flush();
            op.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void writeStream(String template, String path, String net_page) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        if (net_page.split("/").length > 0) {
            net_page = net_page.substring(net_page.lastIndexOf("/") + 1);
        }
        path = path + "/" + net_page;
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter op = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            op = new OutputStreamWriter(fileOutputStream,"UTF-8");
            //byte tag_bytes[] = template.getBytes("UTF-8");
            //fileOutputStream.write(tag_bytes);
            op.append(template);
            op.flush();
            op.close();

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}

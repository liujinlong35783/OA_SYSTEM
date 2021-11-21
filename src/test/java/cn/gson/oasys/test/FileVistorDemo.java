package cn.gson.oasys.test;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

/**
 * 遍历目录和文件
 */
public class FileVistorDemo {
    public static void main(String[] args) throws IOException {
        String path = "D:\\Google";
        //walkFileTree
        /**
         * 参数1：路径   参数2：FileVisitor对象
         *  public static Path walkFileTree(Path start, FileVisitor<? super Path> visitor)
         *
         */
        Files.walkFileTree(Paths.get(path),new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                //访问到目录的时候 就会触发该方法 就可以对遍历的目录进行操作
                System.out.println("正在操作的目录是："+dir);

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                //访问文件的时候会触发visitFile方法 重写该方法可以对遍历的文件进行处理
                System.out.println("当前正在处理的文件："+file);
                if (file.endsWith("abc.txt")) {
                    System.out.println("有对应的文件");
                    return FileVisitResult.TERMINATE;//终止操作 找到之后不再处理了
                }

                return FileVisitResult.CONTINUE;//CONTINUE表示继续遍历文件
            }

        });
    }
    @Test
    public void test() throws IOException {
        String filepath = "G:\\QQGame";
        Files.walkFileTree(Paths.get(filepath),new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException
            {
                //访问到目录的时候 就会触发该方法 就可以对遍历的目录进行操作
                System.out.println("正在操作的目录是："+dir);
                return FileVisitResult.CONTINUE;
            }

            /**
             * Invoked for a file in a directory.
             *
             * <p> Unless overridden, this method returns {@link FileVisitResult#CONTINUE
             * CONTINUE}.
             */
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException
            {
                //访问文件的时候会触发visitFile方法 重写该方法可以对遍历的文件进行处理
                if (file.endsWith("bugreport.exe")){
                    System.out.println("当前正在处理的文件："+file.getFileName());
                    System.out.println("bugreport.exe".equals(file.getFileName()));
                    System.out.println("bugreport.exe".equals(file.getFileName().toString()));
                   return FileVisitResult.TERMINATE;
                }
                System.out.println("当前正在处理的文件："+file);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}


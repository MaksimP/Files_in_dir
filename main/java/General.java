import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class General extends Thread{

    @Override
    public void run()
    {
        String[] array_names; // массив имен файлов
        String buffer_name_file = null; //строка-буффер для хранения имени папки
        File dir; //родительская папка
        File target_dir = null; // папка с заказом
        File[] list_files; //список файлов в родительской папке

        while (true)
        {
            dir = new File(Const.getDir_path());
            array_names = Tools.present_files();
            if (!(array_names.length == 0))
            {
                buffer_name_file = Tools.file_name(array_names);
            }
            target_dir = Tools.create_dir(buffer_name_file);
            list_files = dir.listFiles(new FilterFile());
            if (!(list_files.length == 0))
            {
                try {
                    FileUtils.moveFileToDirectory(list_files[0], target_dir, true);
                    System.out.println("*****");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.gc();
        }
    }

    public static void main(String[] args)
    {
        General general = new General();
        general.start();
        try {
            general.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

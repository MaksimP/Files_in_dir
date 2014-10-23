import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class General extends Thread{

    String[] array_names; // массив имен файлов
    String buffer_name_file = null; //строка-буффер для хранения имени папки
    File dir = dir = new File(Const.getDir_path()); //родительская папка
    File target_dir = null; // папка с заказом
    File[] list_files; //список файлов в родительской папке

    @Override
    public void run() {
        while (true)
        {
            array_names = Tools.present_files();
            if (array_names.length == 0)
            {
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            } else {

                buffer_name_file = Tools.file_name(array_names);
                target_dir = Tools.create_dir(buffer_name_file);
                list_files = dir.listFiles(new FilterFile());
                if (!(list_files.length == 0))
                {
                    try {
                        FileUtils.moveFileToDirectory(list_files[0], target_dir, true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.gc();
            }
        }
    }

    public static void main(String[] args)
    {
        General my_general = new General();
        my_general.start();
    }
}

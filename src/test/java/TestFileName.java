import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import java.io.File;

public class TestFileName {
    // TODO: 2019/5/26

    @Test
    public void testFileName() {
        String url = "https://i.meizitu.net/2019/04/20a01.jpg";
        String name = FilenameUtils.getName(url);

        System.out.println("getName is:->" + name.toString());
        System.out.println("getBaseName is:->" + FilenameUtils.getBaseName(url));
        System.out.println("getExtension is:->" + FilenameUtils.getExtension(url));


        System.out.println("getPathNoEndSeparator is:->" + FilenameUtils.getPathNoEndSeparator(url));
        System.out.println("getFullPathNoEndSeparator is:->" + FilenameUtils.getFullPathNoEndSeparator(url));
        System.out.println("Prefix is:->" + FilenameUtils.getPrefix(url));
        System.out.println("removeExtension is:->" + FilenameUtils.removeExtension(url));



    }

    @Test
    public  void testSb(){
        //http://img.mmmjpg.com/1461/2.jpg
        String base="http://img.mmmjpg.com/1461/2.jpg";
        String baseName = FilenameUtils.getPathNoEndSeparator(base);
        String pathNoEndSeparator = FilenameUtils.getBaseName(baseName);
        System.out.println(baseName);


        String na="http://img.mmmjpg.com/1463/";
        System.out.println(FilenameUtils.getBaseName(base));
        System.out.println(FilenameUtils.getName(base));
    }

}


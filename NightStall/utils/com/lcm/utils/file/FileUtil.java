package com.lcm.utils.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	 public static boolean makeDirs(String filePath) {
        File folder = new File(filePath);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }
	 
	 public static List<String> getFile(String path){   
        File file = new File(path);   
        File[] array = file.listFiles();   
        List<String> list = new ArrayList<String>();
          
        for(int i=0;i<array.length;i++){   
            if(array[i].isFile()){   
                // only take file name   
//	                System.out.println("^^^^^" + array[i].getName());   
                // take file path and name   
//	                System.out.println("#####" + array[i]);  
                list.add(array[i].toString());
                // take file path and name   
//	                System.out.println("*****" + array[i].getPath());   
            }else if(array[i].isDirectory()){   
                getFile(array[i].getPath());   
            }   
        }   
        
        return list;
    }   
}

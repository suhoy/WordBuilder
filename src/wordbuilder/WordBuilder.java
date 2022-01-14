/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordbuilder;

import Props.Props;
import ScannerUtility.ScannerUtility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sergey Sukhorukov Works with lib\aspose.words.jdk16-7.0.0.jar on Java
 * 8
 */
public class WordBuilder {

    final static Map<String, List<String>> args = new HashMap<>();

    /**
     * @param arg the command line arguments 
     * -config config.txt
     * -template template1.docx
     * -imagesFolder C:\images
     * -addAttach false
     * -attachFolder C:\attachs
     * -outFolder C:\out
     * -outName out1
     */
    public static void main(String[] arg) {
        try {
            System.out.println("\n==========WordBuilder started==========");
            ReadParams(arg);
            //String config, String template, ArrayList imageNames, ArrayList attachmentNames, String outFolder, String outFileName
            Props p = new Props(
                    args.get("config").get(0),
                    args.get("template").get(0),
                    args.get("imagesFolder").get(0),
                    args.get("attachFolder").get(0),
                    args.get("outFolder").get(0),
                    String.join(" ", args.get("outName")),
                    //args.get("outName").get(0),
                    Boolean.parseBoolean(args.get("addAttach").get(0))
            );

            Builder b = new Builder(args.get("template").get(0), p);
            
            if(p.isThereImages)
            {
              b.replaceImages();  
            }
                        
            if(p.isThereAttachment)
            {
              b.addAttachments(); 
            }
                        
            b.saveDoc();

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("\n==========WordBuilder finished==========");
            System.exit(1);
        }
        System.out.println("\n==========WordBuilder finished==========");
        System.exit(0);
    }

    /**
     * @param arg CLI into List
     */
    public static void ReadParams(String[] arg) {
        List<String> options = null;
        for (int i = 0; i < arg.length; i++) {
            final String a = arg[i];

            if (a.charAt(0) == '-') {
                if (a.length() < 2) {
                    System.err.println("Error at argument " + a);
                    return;
                }

                options = new ArrayList<>();
                args.put(a.substring(1), options);
            } else if (options != null) {
                options.add(a);
            } else {
                System.err.println("Illegal parameter usage");
                return;
            }
        }
        System.out.println("Arguments:\r\n");
        System.out.println("Config: " + args.get("config").get(0));
        System.out.println("Template: " + args.get("template").get(0));
        System.out.println("Images Folder: " + args.get("imagesFolder").get(0));
        System.out.println("Is there attachs: " + args.get("addAttach").get(0));
        System.out.println("Attachment Folder: " + args.get("attachFolder").get(0));
        System.out.println("Output Folder: " + args.get("outFolder").get(0));
        System.out.println("Output Name: " + args.get("outName").get(0));
    }
}

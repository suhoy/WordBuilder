/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Props;

import ScannerUtility.ScannerUtility;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 *
 * @author Sergey
 */
public class Props {

    public Map<String, String> params = new TreeMap<>();
    public String outFolder;
    public String outFileName;
    public String template;
    public ArrayList<String> imageNames;
    public boolean isThereImages = true;
    public ArrayList<String> attachmentNames;
    public boolean isThereAttachment = false;
    private Properties prop;

    public Props(String config, String template, String imageNamesPaths, String attachmentNamesPaths, String outFolder, String outFileName, boolean addAtach) {

        this.template = template;
        //images load
        this.imageNames = loadArrays(imageNamesPaths, "Images Folder");
        if (imageNames.equals(null)) {
            isThereImages = false;
        }
        //System.out.println("this.imageNames="+this.imageNames.toString());
        //attach load
        this.attachmentNames = loadArrays(attachmentNamesPaths, "Attachments Folder");
        if (imageNames.equals(null)) {
            isThereAttachment = false;
        }
        //System.out.println("this.attachmentNames="+this.attachmentNames.toString());
        this.outFolder = outFolder;
        this.outFileName = outFileName;
        this.isThereAttachment = addAtach;

        try {
            //config load
            InputStream input = new FileInputStream(config);
            prop = new Properties();
            prop.load(input);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        loadParams();
    }

    private void loadParams() {
        params.put("image.wight", prop.getProperty("image.wight"));
        params.put("image.height", prop.getProperty("image.height"));
        params.put("doc.extension", prop.getProperty("doc.extension"));
        System.out.println("\r\nConfig:");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + "=" + val);
        }
    }

    private ArrayList loadArrays(String path, String opName) {
        System.out.println("\r\nReading " + opName);
        if (!path.equals(null)) {
            return (ArrayList<String>) ScannerUtility.Scan(path);
        } else {

            System.out.println(opName + " is null");
            return null;
        }
    }

}

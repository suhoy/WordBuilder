/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordbuilder;

import Props.Props;
import ZipUtility.ZipUtility;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.OlePackage;
import com.aspose.words.Shape;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;

/**
 *
 * @author Sergey
 */
public class Builder {

    public Props conf;
    public Document doc;

    public Builder(String template, Props p) {
        try {
            this.conf = p;
            this.doc = new Document(template);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("\r\n1)Template opened");
    }

    public void replaceImages() throws Exception {
        for (String fullPath : this.conf.imageNames) {
            Path path = Paths.get(fullPath);
            Path fileName = path.getFileName();
            Pattern regex = Pattern.compile(fileName.toString());

            this.doc.getRange().replace(
                    regex,
                    new ReplaceWithImageEvaluator(
                            fullPath,
                            Integer.parseInt(this.conf.params.get("image.wight")),
                            Integer.parseInt(this.conf.params.get("image.height"))),
                    false);
        }
        System.out.println("\r\n2)Images replaced");
    }

    public void addAttachments() {

        String[] stockArr = new String[this.conf.attachmentNames.size()];
        stockArr = this.conf.attachmentNames.toArray(stockArr);
        ZipUtility zipUtil = new ZipUtility();
        String zippy = conf.outFolder + "\\" + conf.outFileName + "_attachments" + ".zip";

        try {
            zipUtil.zip(stockArr, zippy);

            //addAttachments zippy
            
            byte[] zipFileBytes = Files.readAllBytes(Paths.get(zippy));

            InputStream stream = new ByteArrayInputStream(zipFileBytes);
            DocumentBuilder builder = new DocumentBuilder(doc);
            Shape shape = builder.insertOleObject(stream, "Package", true, null);
            OlePackage setOlePackage = shape.getOleFormat().getOlePackage();
            setOlePackage.setFileName("attachments.zip");
            setOlePackage.setDisplayName("attachments.zip");
             

            /*
            //ДОБАВИТЬ ЭКСЕЛЬ КАК КАРТИНКУ            
            BufferedImage image = ImageIO.read(new File("C:\\Users\\Sergey\\Documents\\NetBeansProjects\\WordBuilder\\env\\in\\excel.png"));
            Shape oleObject = builder.insertOleObject(this.conf.attachmentNames.get(0), true, false, image );
             */
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("\r\n3)Zip added");

    }

    public void saveDoc() {
        try {

            System.out.println("\r\nSaving output: " + conf.outFolder + "\\" + conf.outFileName + "." + "" + conf.params.get("doc.extension"));
            doc.save(conf.outFolder + "\\" + conf.outFileName + "." + "" + conf.params.get("doc.extension"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

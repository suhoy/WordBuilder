
import ZipUtility.ZipUtility;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import wordbuilder.WordBuilder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sergey
 */
public class Zippy {

    public void Test() {
        ZipUtility zipUtil = new ZipUtility();
        try {
            zipUtil.zip(new String[]{"C:\\Users\\Sergey\\Documents\\NetBeansProjects\\Ь\\Maven_Test\\in\\attach"}, "C:\\out.zip");
        } catch (IOException ex) {
            Logger.getLogger(WordBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

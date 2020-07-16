package wordbuilder;


import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.IReplacingCallback;
import com.aspose.words.ReplaceAction;
import com.aspose.words.ReplacingArgs;
import com.aspose.words.Shape;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Sergey
 */
public class ReplaceWithImageEvaluator implements IReplacingCallback {
    private String imagePath;
    private int wight;
    private int height;
    
    public ReplaceWithImageEvaluator(String imagePath, int wight, int height) {
        this.imagePath = imagePath;
        this.wight = wight;
        this.height = height;
        
    }

    @Override
    public int replacing(ReplacingArgs e) {
        try {
            DocumentBuilder builder = new DocumentBuilder((Document) e.getMatchNode().getDocument());
            builder.moveTo(e.getMatchNode());
            //"C:\\Users\\Sergey\\Documents\\NetBeansProjects\\Ь\\Maven_Test\\in\\report\\test3.png"
            //450
            //200
            Shape img = builder.insertImage(imagePath,wight, height);
        } catch (Exception ex) {
            Logger.getLogger(ReplaceWithImageEvaluator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ReplaceAction.REPLACE;
    }



    

}

# WordBuilder
Create docx report from template with pics and attachments  
Java 8 with free version Aspose words (aspose-words-18.1-jdk16.jar)  

### Arguments
***-config***		Path to config file  
***-template***		Path to template file  
***-imagesFolder***	Images folder  
***-attachFolder***	Attachments folder  
***-outFolder***	Output folder  
***-outName***		Output file name with no extension  

### Start example
java -jar -config C:\config.txt -template C:\template1.docx -imagesFolder C:\images -attachFolder C:\attachs -outFolder C:\out -outName out1

### Config example
image.wight=450  
image.height=200  
doc.extension=docx  

### Template example
@attachment.zip  

pic1.png  
Pic description1  

pic2.png  
Pic description2  

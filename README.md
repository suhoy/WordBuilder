# WordBuilder
Create docx report from template with pics and attachments  
Java 8 with free version Aspose words (aspose-words-18.1-jdk16.jar)  

### Arguments
```java
-config         Path to config file  
-template       Path to template file  
-imagesFolder   Images folder  
-addAttach      Is there attachments  
-attachFolder   Attachments folder  
-outFolder      Output folder  
-outName        Output file name with no extension  
```
### Start example
```java
java -jar WordBuilder.jar -config C:\config.txt -template C:\template1.docx -imagesFolder C:\images -addAttach true -attachFolder C:\attachs -outFolder C:\out -outName out1
```
### Config example
```python
image.wight=450  
image.height=200  
doc.extension=docx  
```
### Template example
```python
@attachment.zip  

pic1.png  
Pic description1  

pic2.png  
Pic description2  
```

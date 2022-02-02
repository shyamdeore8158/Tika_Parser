import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class TikaParser {
	
	private static Logger logger = LoggerFactory.getLogger("TikaParser");

	public static void main(String[] args) throws IOException, SAXException, TikaException {
		// PDF
		 //checkPdfMetadata(new File("C:\\demo\\Preventions.pdf"),"application/pdf");

		// DOCX
		// checkPdfMetadata(new
		 //File("C:\\demo\\2.docx"),"application/vnd.openxmlformats-officedocument.wordprocessingml.document");

		// XLS && XLSX
		 //checkPdfMetadata(new
		 //File("C:\\demo\\Missing.xlsx"),"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

		// PPT && PPTX

		 //checkPdfMetadata(new
		// File("C:\\demo\\Event.pptx"),"application/vnd.openxmlformats-officedocument.presentationml.presentation");

		// HTML

		 //checkPdfMetadata(new File("C:\\demo\\CWE-79.html"),"text/html");
		// charset=windows-1252");

		// PST

		 checkPdfMetadata(new File("C:\\demo\\demo.pst"),"text/plain");

		// ODT
		//checkPdfMetadata(new File("C:\\demo\\file-sample_100kB.odt"), "application/vnd.oasis.opendocument.text");

	}

	public static boolean checkPdfMetadata(File file, String contentType) {
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		try (FileInputStream inputstream = new FileInputStream(file)) {
			try {
				ParseContext pcontext = new ParseContext();
				AutoDetectParser pdfparser = new AutoDetectParser();
				metadata.set(Metadata.CONTENT_TYPE, file.getName());				
				pdfparser.parse(inputstream, handler, metadata, pcontext);
			
				
				Tika t =new Tika();
				String parseString = t.parseToString(file);
				//System.out.println(parseString);
				
				Tika tika = new Tika();
			    Metadata metadata1 = new Metadata();

			    tika.parse(file, metadata1);
			    
			    System.out.println(tika.detect(file));
				
			      
			} catch (TikaException | SAXException e) {
				logger.error(e.getMessage(),e);
				return false;
			}
			//System.out.println(metadata.get(Metadata.CONTENT_TYPE));
			if (contentType.equals(metadata.get(Metadata.CONTENT_TYPE))) {
				System.out.println(true);
				return true;
			} else {
				logger.info("Content Type is Different");
				return false;
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage(),e1);
			return false;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage(),e1);
			return false;
		}
	}
}

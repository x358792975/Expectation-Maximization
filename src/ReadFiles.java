import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ReadFiles {

	public ReadFiles(String fileName, int num) throws IOException{
		FileReader reader = new FileReader(fileName);
		BufferedReader bfReader = new BufferedReader(reader);
		String line = bfReader.readLine();
		while((line = bfReader.readLine())!=null){
			System.out.println(line);
		}
		//System.out.println(line);
	}
}

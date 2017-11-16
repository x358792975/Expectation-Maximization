import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ReadFiles {

	
	public ReadFiles(String fileName, int num) throws IOException{
		Scanner scan = new Scanner(new File(fileName));

		List<String[]> myList = new ArrayList<String[]>();
		System.out.println(scan.nextLine());
		System.out.println("******************");
		while(scan.hasNext()){
			new ParseString(scan.nextLine(),myList);
			//System.out.println(scan.nextLine());
		}
		System.out.println(myList.size());
		for(int i=0; i<20; i++){
			System.out.println(Arrays.deepToString(myList.get(i)));
		}

	}
}

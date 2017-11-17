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
		
		List<String> genderList = new ArrayList<String>();
		List<String> weightList = new ArrayList<String>();
		List<String> heightList = new ArrayList<String>();

		System.out.println(scan.nextLine());
		System.out.println("******************");
		while(scan.hasNext()){
			ParseString parse = new ParseString();
			parse.makeList(scan.nextLine(),genderList, weightList, heightList);
		}
		
		Calculation Cal = new Calculation();
		Gender gender = new Gender(Cal.CalPro(genderList));
		Weight weight = new Weight(Cal.CalPro(weightList));
		Gender height = new Gender(Cal.CalPro(heightList));

		System.out.println(gender.getPrabability() + " " + weight.getPrabability() + " " + height.getPrabability());

	}
}

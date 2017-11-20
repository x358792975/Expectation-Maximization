import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ReadFiles {

	
	public ReadFiles(String fileName, int num) throws IOException{
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(new File(fileName));
		
		List<String> genderList = new ArrayList<String>();
		List<String> weightList = new ArrayList<String>();
		List<String> heightList = new ArrayList<String>();
		List<Integer> missingList= new ArrayList<Integer>(); 

		System.out.println(scan.nextLine());
		System.out.println("******************");
		while(scan.hasNext()){
			ParseString parse = new ParseString();
			parse.makeList(scan.nextLine(),genderList, weightList, heightList);
		}
		

		Calculation cal = new Calculation();
		Gender gender = new Gender(cal.CalPro(genderList, missingList));
		Weight weight = new Weight(cal.CalPro(weightList));
		Gender height = new Gender(cal.CalPro(heightList));
		
		Model m = new Model(weight.getPrabability(),height.getPrabability());
		//System.out.println(gender.getPrabability() +  " " + weight.getPrabability() + " " + height.getPrabability());
		/*for(int i=0; i<missingList.size(); i++){
			
			System.out.println(missingList.get(i));
		}*/
		System.out.println("P(W=1) is " + m.getP_W1());
		System.out.println("P(W=0) is " + m.getP_W0());
		System.out.println("P(H=1) is " + m.getP_H1());
		System.out.println("P(H=0) is " + m.getP_H0());

	}
}

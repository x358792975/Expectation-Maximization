import java.io.File;
import java.math.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ReadFiles {

	
	public ReadFiles(String fileName, int num) throws IOException{
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(new File(fileName));
		float likelihood = 0.00000f;
		List<String> genderList = new ArrayList<String>();
		List<String> weightList = new ArrayList<String>();
		List<String> heightList = new ArrayList<String>();
		
		List<Integer> missingList= new ArrayList<Integer>(); 

		System.out.println(scan.nextLine());
		//System.out.println("******************");
		while(scan.hasNext()){
			ParseString parse = new ParseString();
			parse.makeList(scan.nextLine(),genderList, weightList, heightList);
		}
		Calculation cal = new Calculation();		
		Weight weight = new Weight(cal.CalPro(weightList));
		Gender height = new Gender(cal.CalPro(heightList));		

		Gender gender = new Gender(cal.CalPro(genderList, missingList));
		//Model m = new Model();
		Model m = new Model(weight.getPrabability(),height.getPrabability());		
		gender.setPrabability(m.getP_G(1));
		
		//compute prob of missing data		
		for(int i=0; i<missingList.size();i++){			
			genderList.set(missingList.get(i),replaceMissing(gender.getPrabability()));

		}

		
		//count p(g=0) p(g=1) p(wg) p(hg)
		//recompute prob of parameter
		
		//p(g) = p(g=?)/20
		m.setP_G(1,cal.Count(genderList));
		
		//p(w|g) = p(wg)/p(g)
		m.setP_WG(cal.CountComb(weightList,"0",genderList,"0"),
				  cal.CountComb(weightList,"0",genderList,"1"),
				  cal.CountComb(weightList,"1",genderList,"0"),
				  cal.CountComb(weightList,"1",genderList,"1"));	
		
		//p(h|g) = p(hg)/p(g)
		m.setP_HG(cal.CountComb(heightList,"0",genderList,"0"),
				  cal.CountComb(heightList,"0",genderList,"1"),
				  cal.CountComb(heightList,"1",genderList,"0"),
				  cal.CountComb(heightList,"1",genderList,"1"));
		
		/*		
		float P_G_1 = cal.Count(genderList);
		float P_G_0 = 1 - P_G_1;
		float P_W0_G0 = cal.CountComb(weightList,"0",genderList,"0");
		float P_W0_G1 = cal.CountComb(weightList,"0",genderList,"1");		
		float P_W1_G0 = cal.CountComb(weightList,"1",genderList,"0");
		float P_W1_G1 = cal.CountComb(weightList,"1",genderList,"1");

		float P_H0_G0 = cal.CountComb(heightList,"0",genderList,"0");
		float P_H1_G0 = cal.CountComb(heightList,"1",genderList,"0");
		float P_H0_G1 = cal.CountComb(heightList,"0",genderList,"1");
		float P_H1_G1 = cal.CountComb(heightList,"1",genderList,"1");
		 */
		
		

		//compute likelihood of this iteration
		
		//if difference of likelihood of this iteration and the previous iteration is <0.001 -> terminate
		
		

		//System.out.println(gender.getPrabability() +  " " + weight.getPrabability() + " " + height.getPrabability());
		/*
		for(int i=0; i<missingList.size(); i++){
			System.out.println("Missing List size: " + missingList.size());
			System.out.println(missingList.get(i));
		}
		
		System.out.println("P(W=1) is " + m.getP_W1());
		System.out.println("P(W=0) is " + m.getP_W0());
		System.out.println("P(H=1) is " + m.getP_H1());
		System.out.println("P(H=0) is " + m.getP_H0());
		
		for(int i=0 ;i < genderList.size();i++){
			System.out.println(weightList.get(i));
		}*/
		
		for(int i=0; i<genderList.size();i++){
			likelihood += m.getP_GWH(Integer.parseInt(genderList.get(i)), Integer.parseInt(weightList.get(i)), Integer.parseInt(heightList.get(i)));
		}/**/

		System.out.println(likelihood);
		System.out.println(Math.log(likelihood));
	}
	public String replaceMissing(float prob){
		if(prob >= 0.5) return "1";
		else return "0";
	}
}

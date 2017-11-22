import java.io.File;
import java.math.*;
import java.text.DecimalFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ReadFiles {

	
	public ReadFiles(String fileName, int num) throws IOException{
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(new File(fileName));
		
		DecimalFormat df = new DecimalFormat("#.####");
		//df.setRoundingMode(RoundingMode.CEILING);
		
		float likelihood = 0.00000f;
		float likelihood_pre = 0.0000f;
		float likelihood_diff = 0.0000f;
		
		
		int iteration =0;
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
		do {
		for(int i=0; i<missingList.size();i++){			
			genderList.set(missingList.get(i),
					replaceMissing(m,Integer.parseInt(weightList.get(i)),
							Integer.parseInt(heightList.get(i))));

		}

		int Count_P_0 =0;
		int Count_P_1 =0;
		for(int i=0; i<genderList.size();i++){
			if(genderList.get(i).equals("0")) Count_P_0++;
			else Count_P_1++;
		}
		
		//count p(g=0) p(g=1) p(wg) p(hg)
		//recompute prob of parameter
		
		//p(g) = p(g=?)/20
		//m.setP_G(1,cal.Count(genderList));
		m.setP_G(1,(float)Count_P_1/20);
		System.out.println("P(G=1)=  " + m.getP_G(1));
		//p(w|g) = p(wg)/p(g)
		m.setP_WG(cal.CountComb(weightList,"0",genderList,"0",Count_P_0),
				
				1-cal.CountComb(weightList,"0",genderList,"0",Count_P_0),
				  //cal.CountComb(weightList,"0",genderList,"1",Count_P_1),
				  
				  cal.CountComb(weightList,"0",genderList,"1",Count_P_1),
				  1-cal.CountComb(weightList,"0",genderList,"1",Count_P_1));	
		
		//p(h|g) = p(hg)/p(g)
		m.setP_HG(cal.CountComb(heightList,"0",genderList,"0",Count_P_0),
				  1-cal.CountComb(heightList,"0",genderList,"0",Count_P_0),
				 // cal.CountComb(heightList,"0",genderList,"1",Count_P_1),
				  cal.CountComb(heightList,"0",genderList,"1",Count_P_1),
				  1-cal.CountComb(heightList,"0",genderList,"1",Count_P_1));
		//m.printResult();
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
			System.out.println(genderList.get(i));
		}*/
	      likelihood_pre = likelihood;
	      likelihood = 0.000000f;
		for(int i=0; i<genderList.size();i++){
			likelihood += m.getEM(Integer.parseInt(genderList.get(i)),
									 Integer.parseInt(weightList.get(i)),
									 Integer.parseInt(heightList.get(i)));
		}/**/

		likelihood =(float) Math.log((float)likelihood);
		likelihood_diff = (float)(Math.abs(((float)likelihood - (float)likelihood_pre)));
		System.out.println("Likelihood difference is:   "+likelihood_diff);
		//System.out.println(Math.log(likelihood_diff));
		iteration++;
		System.out.println("Log Likelihood is  "+ likelihood);
		m.printResult(iteration);
		} while (likelihood_diff>=0.000100);
		
		System.out.println("Iteration = " + iteration);
		m.printResult();
	}
	public String replaceMissing(Model m, int w, int h){

		float p1 = m.getG_WH(1,w,h);
		float p0 = m.getG_WH(0, w, h);
		//System.out.println("P1 = " + p1 + "   . P0 =  "+ p0);
		
		if (p1 >=p0) return "1";
		else return "0";
	}
}

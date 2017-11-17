import java.util.List;

public class Calculation {
	public Calculation(){
		
	}
	
	public float CalPro(List<String> dataset){
		float prob = 0.0f;
		int count =0;		
		for(int i=0; i<dataset.size();i++){
			if ((dataset.get(i)).equals("1")) count++;
		}
		System.out.println("Count is " + count);
		prob = (float)count/dataset.size();
		System.out.println("Probability is  " + prob);
		return prob;
	}
}

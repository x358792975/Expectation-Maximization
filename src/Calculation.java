import java.util.List;

public class Calculation {
	
	public float CalPro(List<String> dataset){
		float[] prob = new float[2];
		int count =0;		
		for(int i=0; i<dataset.size();i++){
			if ((dataset.get(i)).equals("1")) count++;
		}
		//System.out.println("Count is " + count);
		prob[1] = (float)count/dataset.size();
		prob[0] = 1 - prob[1];
		//System.out.println("Probability is  " + prob[1]);
		return prob[1];
	}
	
	public float CalPro(List<String> dataset, List<Integer> missingList){
		float[] prob = new float[2];
		int count =0;		
		for(int i=0; i<dataset.size();i++){
			if ((dataset.get(i)).equals("1")) count++;
			else if (dataset.get(i).equals("0")){
				continue;
			}
			else {
				missingList.add(i);
				//dataset.set(i, getEM(1,1,1));
			}
		}
		//System.out.println("Count is " + count);
		prob[1] = (float)count/dataset.size();
		prob[0] = 1 - prob[1];
		//System.out.println("Probability is  " + prob[1]);
		return prob[1];
	}
	
	public float Count(List<String> myList){
		int count=0;
		for(int i=0;i<myList.size();i++){
			if(myList.get(i).equals("1")) count++;
		}
		//System.out.println((float) count/20);
		return (float) count/20;
	}
	public float CountComb(List<String> list1,String index1, List<String> list2, String index2,int count_P){
		float prob = 0.0000000f;
		int count = 0;
		for(int i=0; i<list1.size();i++){
			if((list1.get(i).equals(index1) ) && (list2.get(i).equals(index2))){
				count++;
			}
		}
		prob = (float)count/count_P;
		//System.out.println(" P_0_G_0 = "+ prob);
		return prob;
	}
}

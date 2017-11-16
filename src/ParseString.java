import java.util.ArrayList;
import java.util.List;

public class ParseString {


	
	public ParseString(String str, List<String[]> myList){
		String[] myarr = str.split("\\s+");
		myList.add(myarr);
		//System.out.println("Size of list " + myList.size());
		//System.out.println("**************");
		//System.out.println(myarr[1]);
		
		for(int i=0; i<myarr.length;i++){
			//System.out.print(myarr[i]);
		}
		//System.out.println("");
	}
}

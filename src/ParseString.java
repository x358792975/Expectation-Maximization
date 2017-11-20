import java.util.List;

public class ParseString {

	public void makeList(String str, List<String> gender, List<String> weight, List<String> height){
		String[] myarr = str.split("\\s+");
		gender.add(myarr[0]);
		weight.add(myarr[1]);
		height.add(myarr[2]);
	}
}

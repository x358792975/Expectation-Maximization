import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//String[] fileNumber = {"10","100", "30","50","70"};
		String[] fileNumber = {"10"};
		for(int i=0; i<fileNumber.length;i++){
			String pre = "hw2dataset_";
			String fileName = pre+fileNumber[i]+".txt";
			//System.out.println(fileName);
			try {
				new ReadFiles(fileName,Integer.parseInt(fileNumber[i]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("File not found!");
				e.printStackTrace();
			}
			System.out.println("*************************************");
		}
	}

}

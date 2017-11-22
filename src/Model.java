
public class Model {
	private float[] P_G = {0.7f,0.3f};
	private float[][] P_WG = {{0.8f, 0.4f}, {0.2f, 0.6f}};
	private float[][] P_HG = {{0.7f, 0.3f}, {0.3f, 0.7f}};
	private float P_W0;
	private float P_W1;
	private float P_H0;
	private float P_H1;
	
	public Model(float P_W1, float P_H1){
		this.P_W1 = P_W1;
		this.P_W0 = 1-P_W1;
		this.P_H1 = P_H1;
		this.P_H0 = 1-P_H1;
	}
	
	public void setP_G(int index, float data){
		if(index ==1){
			P_G[1] = data;
			P_G[0] = 1- data;
		}
		else if(index == 0){
			P_G[0] = data;
			P_G[1] = 1-data;
		}
		
	}
	public void setP_WG(float a, float b, float c, float d){
		P_WG[0][0] = a;
		P_WG[0][1] = b;
		P_WG[1][0] = c;
		P_WG[1][1] = d;
		
	}
	public void setP_HG(float a, float b, float c, float d){
		P_HG[0][0] = a;
		P_HG[0][1] = b;
		P_HG[1][0] = c;
		P_HG[1][1] = d;
		
	}
	
	//P(W=1)
	public float getP_W1(){
		return this.P_W1;
	}
	//P(W=0)
	public float getP_W0(){
		return this.P_W0;
	}
	//P(H=1)
	public float getP_H1(){
		return this.P_H1;
	}
	//P(H=0)
	public float getP_H0(){
		return this.P_H0;
	}
	
	//return G[0] or G[1]
	public float getP_G(int index){
		return P_G[index];
	}
	
	//table for P(W|G)
	public float getP_WG(int w, int g){
		return P_WG[w][g];
	}
	// P(W|G=0 or G=1)
	public float getP_WG_equals(int index){
		return (P_WG[0][index] + P_WG[1][index]);
	}
	
	// P(W=0 or W=1|G)
	public float getP_W_equalsG(int index){
		return (P_WG[index][0] + P_WG[index][1]);
	}
	
	// P(H|G)
	public float getP_HG(int h, int g){
		return P_HG[h][g];
	}
	// P(H|G=0 or G=1)
	public float getP_HG_equals(int index){
		return (P_HG[0][index] + P_HG[1][index]);
	}
	// P(H=0 or H=1 | G)
	public float getP_H_equalsG(int index){
		return (P_HG[index][0] + P_HG[index][1]);
	}
	
	// P(GWH)
	public float getP_GWH(int g, int w, int h){
		return getP_WG(w,g) * getP_HG(h,g) * getP_G(g); 
	}
	
	public float getEM(int g, int w, int h){
		int g_xor = g^1;
		return  this.getP_GWH(g,w, h)/( this.getP_GWH(g,w,h)+ this.getP_GWH(g_xor, w, h));

	}
	public float getG_WH(int g_val, int w_val, int h_val) {
		    int g_val_xor = g_val^1;
		    return (
		      this.getP_GWH(g_val, w_val, h_val)/(this.getP_GWH(g_val, w_val, h_val) + this.getP_GWH(g_val_xor, w_val, h_val))
		      );
		  }
	public void printResult(int iteration){
		System.out.println("Iteration "+iteration);
		System.out.println("P(G=0) = "+ this.getP_G(0) + "     P(G=1) " + this.getP_G(1));
		System.out.println("P(W = 0| G =0) " + this.getP_WG(0, 0));
		System.out.println("P(W = 0| G =1) " + this.getP_WG(0, 1));
		System.out.println("P(W = 1| G =0) " + this.getP_WG(1, 0));
		System.out.println("P(W = 1| G =1) " + this.getP_WG(1, 1));
		
		System.out.println("P(H = 0| G =0) " + this.getP_HG(0, 0));
		System.out.println("P(H = 0| G =1) " + this.getP_HG(0, 1));
		System.out.println("P(H = 1| G =0) " + this.getP_HG(1, 0));
		System.out.println("P(H = 1| G =1) " + this.getP_HG(1, 1));
		System.out.println("----------------------------------");
		
	}
	public void printResult(){
		System.out.println("Final Result");
		System.out.println("P(G=0) = "+ this.getP_G(0) + "     P(G=1) " + this.getP_G(1));
		System.out.println("P(W = 0| G =0) " + this.getP_WG(0, 0));
		System.out.println("P(W = 0| G =1) " + this.getP_WG(0, 1));
		System.out.println("P(W = 1| G =0) " + this.getP_WG(1, 0));
		System.out.println("P(W = 1| G =1) " + this.getP_WG(1, 1));
		
		System.out.println("P(H = 0| G =0) " + this.getP_HG(0, 0));
		System.out.println("P(H = 0| G =1) " + this.getP_HG(0, 1));
		System.out.println("P(H = 1| G =0) " + this.getP_HG(1, 0));
		System.out.println("P(H = 1| G =1) " + this.getP_HG(1, 1));
		System.out.println("----------------------------------");
		
	}
}

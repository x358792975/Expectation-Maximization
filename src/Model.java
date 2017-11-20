import java.util.List;

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
	
	public String getEM(int g, int w, int h){
		int g_xor = g;
		return  String.valueOf(this.getP_GWH(g,w, h)/( this.getP_GWH(g,w,h)+ this.getP_GWH(g_xor, w, h)));
				//(this.getP_GWH(g,w, h)/( this.getP_GWH(g,w,h)+ this.getP_GWH(g_xor, w, h))).toString();
	}
	


}

import java.math.BigDecimal;
import java.util.HashMap;

public class Redistribute {
	
	private HashMap<String,Double> map;
	private HashMap<String, Double> temp;
	private double sum = 0;
	private double temp_sum = 0;
	private String maxName = "";
	
	public Redistribute(HashMap<String,Double> input){
		map = new HashMap<String,Double> (input);
		temp = new HashMap<String,Double> (input);
		
		double max = 0.00;
		BigDecimal sum_p = new BigDecimal(Double.toString(0.00));
		
		for(String key : map.keySet()) {
			if(map.get(key) > max) {
				maxName = key;
				max = map.get(key);
			}
			sum_p = sum_p.add(new BigDecimal(Double.toString(map.get(key))));
			//sum+=map.get(key);
		}
		
		sum = sum_p.doubleValue();
		temp_sum = sum;
		
	}
	
	public HashMap<String,Double> setTotal(double total){
		//temp.clear();
		BigDecimal sum_p = new BigDecimal(Double.toString(0.00));
		BigDecimal num;
		
		for(String key : map.keySet()) {
			//num = map.get(key)*ratio
			num = new BigDecimal(Double.toString(map.get(key))).multiply(new BigDecimal(total/sum)).setScale(2,BigDecimal.ROUND_HALF_UP);
			//sum = map.get(key)*ratio round
			sum_p = sum_p.add(num);
			temp.put(key,num.doubleValue());
		}
		
		if(sum_p.compareTo(new BigDecimal(Double.toString(total))) != 0) {
			//System.out.println(new BigDecimal(temp.get(maxName).toString()));
			temp.put(maxName, new BigDecimal(Double.toString(temp.get(maxName))).add(new BigDecimal(total).add(sum_p.negate())).doubleValue());
			//System.out.println(sum_p.negate());
			//System.out.println(new BigDecimal(total));
		}
		
		
		temp_sum = total;
		//System.out.println(sum);
		//if(sum!=total)
		//	map.put(maxName, map.get(maxName)+(sum-total));
		
		//sum = total;
		
		return temp;
	}
	
	public double getSum() {
		return temp_sum;
	}
	
	public HashMap<String,Double> getMap(){
		return temp;
	}
	
	public void print() {
		for(String key : map.keySet()) {
			System.out.printf("%-6s : %-10f kg\n",key,temp.get(key));
		}
		System.out.println("===========================================");
		System.out.printf("total weight : %.2f kg\n", temp_sum);
	}
}

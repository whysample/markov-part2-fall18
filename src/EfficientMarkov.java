import java.util.*;
public class EfficientMarkov extends BaseMarkov{
	Map<String,ArrayList<String>> myMap; 
	
	public EfficientMarkov(int order) {
		super(order);
		myMap= new HashMap<>();
	}
	
	public EfficientMarkov() {
		this(3);
	}
	
	@Override
	public void setTraining(String text) {
		this.myMap=new HashMap<>();
		for(int i=0;i<(text.length()-this.myOrder);i++) {
			String c= text.substring(i, i+this.myOrder);
			ArrayList<String> name= new ArrayList<String>();
			if(!(this.myMap.containsKey(c))){
				this.myMap.put(c,name);
			}
			this.myMap.get(c).add((text.substring(i+this.myOrder, 1+i+this.myOrder)));

		}
	}
	
	@Override
	public ArrayList<String> getFollows(String key){
		if(!(this.myMap.containsKey(key))) {
			throw new NoSuchElementException(key+" not in map");
		}
		return this.myMap.get(key);
	}
}


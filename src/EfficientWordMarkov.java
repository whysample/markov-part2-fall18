import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EfficientWordMarkov extends BaseWordMarkov{
	private Map<WordGram, ArrayList<String>> myMap;

	public EfficientWordMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}

	public EfficientWordMarkov() {
		this(3);
	}
	@Override
	public void setTraining(String text) {
		myWords = text.split("\\s+");
		this.myMap = new HashMap<>();
		for (int i = 0; i < myWords.length-this.myOrder+1; i++) {
			WordGram ch= new WordGram(myWords,i,this.myOrder);
			ArrayList<String> name = new ArrayList<String>();
			if (!(this.myMap.containsKey(ch))) {
				this.myMap.put(ch, name);
			}
			if(i+this.myOrder+1<=myWords.length) {
				this.myMap.get(ch).add(myWords[i+1]);
			}
			else {
				this.myMap.get(ch).add(PSEUDO_EOS);
			}
		}
	}


}

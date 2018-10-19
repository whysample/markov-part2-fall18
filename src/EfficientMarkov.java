import java.util.*;


public class EfficientMarkov extends BaseMarkov {
	private Map<String, ArrayList<String>> myMap;
	
	/**
	 * The constructor initializes the object EfficientMarkov by calling super(order) and using the the BaseMarkov
	 * which is extended. This then also creates the HashMap myMap.
	 * @param order represents the specific order wanted for the number of variables in myOrder.
	 */
	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}
	
	/**
	 * this constructor initializes the object EfficientMarkov without parameters. It calls the other constructor to assign
	 * the order 3 to the object.
	 */
	public EfficientMarkov() {
		this(3);
	}
	
	/**
	 * This method runs through the text given to the parameter to initialize the myMap variable for this object. It runs through
	 * every substring of size my.Order in text and creates a key of these substrings. Then it saves the next letter/character following each order size
	 * substring into the value which is string arraylist.
	 * @param text is a large String that is run through to create the keys and values of the map.
	 */
	@Override
	public void setTraining(String text) {
		myText = text;
		this.myMap = new HashMap<>();
		for (int i = 0; i < text.length()-this.myOrder+1; i++) {
			String c = text.substring(i, i + this.myOrder);
			ArrayList<String> name = new ArrayList<String>();
			if (!(this.myMap.containsKey(c))) {
				this.myMap.put(c, name);
			}
			if(i+this.myOrder+1<=text.length()) {
				this.myMap.get(c).add((text.substring(i + this.myOrder, 1 + i + this.myOrder)));
			}
			else {
				this.myMap.get(c).add(PSEUDO_EOS);
			}
		}
	}
	
	/**
	 * This returns a value associated with a certain key in this object's myMap. If the myMap variable does not have the specific key
	 * it throws an exception.
	 * @param key is a String that is what is looked for in the map.
	 */
	@Override
	public ArrayList<String> getFollows(String key) {
		if (!(this.myMap.containsKey(key))) {
			throw new NoSuchElementException(key + " not in map");
		}
		return this.myMap.get(key);
	}
}

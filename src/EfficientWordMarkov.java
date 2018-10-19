import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov {
	private Map<WordGram, ArrayList<String>> myMap;
	
	/**
	 * This constructor creates an EfficientWordMarkov object and initializes it using the parameter order. It also
	 * utilizes the extended BaseWordMarkov class. It then creates the objects myMap variable. 
	 * @param order is an int that represents the created objects order. 
	 */
	public EfficientWordMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}
	
	/**
	 * This constructor creates an EfficientWordMarkov object. It calls the other constructor to give the created object
	 * an order of 3.
	 */
	public EfficientWordMarkov() {
		this(3);
	}
	
	/**
	 * This method splits the parameter text into separate words by a space marks. It then initializes this object's myMap.
	 * It creates an object WordGram out of the my.Order amount of words and saves this object as a key in the map. Then it takes
	 * the next word and saves it in the value of the key which is an ArrayList<String>.
	 * @param text is a String that holds all the words used to initialize and fill the map
	 */
	@Override
	public void setTraining(String text) {
		myWords = text.split("\\s+");
		this.myMap = new HashMap<>();
		for (int i = 0; i < myWords.length - this.myOrder + 1; i++) {
			WordGram ch = new WordGram(myWords, i, this.myOrder);
			ArrayList<String> name = new ArrayList<String>();
			if (!(this.myMap.containsKey(ch))) {
				this.myMap.put(ch, name);
			}
			if (i + this.myOrder < myWords.length) {
				this.myMap.get(ch).add(myWords[i + this.myOrder]);
			} 
			else {
				this.myMap.get(ch).add(PSEUDO_EOS);
			}
		}
	}
	
	/**
	 * This method finds a specific value using the given key in the map. If the key is not present, the code throws an exception.
	 * @param key is the WordGram object used that represents the specific key being looked for in the map.
	 */
	@Override
	public ArrayList<String> getFollows(WordGram key) {
		if (!(this.myMap.containsKey(key))) {
			throw new NoSuchElementException(key + " not in map");
		}
		return this.myMap.get(key);

	}
}

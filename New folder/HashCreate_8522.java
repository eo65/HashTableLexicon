//ELIZABETH OVIEDO cs 435 8522 mp

public class HashCreate_8522 extends Hash_8522 {
	public int numSlots;
	public int numOfElements;
	public int init;
	int[] A;
	int[] T;
	
	public HashCreate_8522(int m) {
		this.numSlots = m;
		this.numOfElements = 0;
		this.init = 0;
		this.A = new int[15 * numSlots];
		this.T = new int[m];
		createTable(T);
	}
	
	public boolean HashEmpty(){
		if (numOfElements == 0) {
			return true;
		}
		return false;
	}
	
	public boolean HashFull() {
		if (numOfElements == numSlots) {
			return true;
		}
		return false;
	}
	
	public void HashPrint() {
		for(int i = 0; i < T.length; i++) {
			System.out.print(Integer.toString(i)+ ": ");
			if(T[i] != -1) {
				System.out.println(Integer.toString(T[i]));
			}
			else {
				System.out.println();
			}
		}
		System.out.println();
		
		for(int j = 0; j < init; j++){
			if (A[j] == '\0'){
				System.out.print("\\");
			}
			else if(A[j] == -1)
			{
				System.out.print("*");
			}
			else {
				System.out.print(String.valueOf(Character.toChars(A[j])));
			}
		}
		System.out.println();
		System.out.println();
	}
	
	public void HashInsert(String word) {
		if (HashFull() == true) {
			System.out.println("Lexicon is Empty");
			return;
		}		
		
		int sum = 0; 
		int i;	
		int j = 0;
		
		for (i = init; i < init + word.length(); i++) {
			if(i < A.length) {
				A[i] = word.charAt(j);
				sum += A[i];
				j++;
			}
			else {
				System.out.println("Array is full. Create a new Lexicon.");
				return;
			}
		}
		
		if(i < A.length) {
			A[i] = '\0';
		}
		else {
			System.out.println("Array is full. Create a new Lexicon.");
		}
			
		int loc = h(sum, numSlots, init, A, T);
		
		if (loc == -1) {
			System.out.println("No place in Table to put word");
			return;
		}
			
		T[loc] = init;
		init += (word.length() + 1);
		numOfElements++;
	}
	
	public void HashSearch(String word) {
		int addition = 0;
		int index = 0;
		int j = 0;
		int placement;
		int charac;
		
		for(int k = 0; k < word.length(); k++){
			addition += word.charAt(k);
		}
		
		while(index < T.length) {
			placement = (addition + (index * index)) % numSlots;
		
			if (T[placement] == -1) {
				System.out.println( word + " not found");
				return;
			}
			else{
				for (int i = T[placement]; i < T[placement] + word.length(); i++) {
					charac = word.charAt(j);
					
					if(charac == A[i]){
						if(A[i+1] == '\0') {
							System.out.println(word + " found in slot " + String.valueOf(placement));
							return;
						}
						
						j++;
						continue;
					}
				}
			}
			index++;
		}
		System.out.println(word + " not found");
	}
	
	public void HashDelete(String word) {
		int addition = 0;
		int index = 0;
		int j = 0;
		int placement;
		int charac;
		
		if (HashEmpty() == true) {
			System.out.println("Lexicon is Empty");
			return;
		}
		
		for(int k = 0; k < word.length(); k++){
			addition += word.charAt(k);
		}
		
		while(index < T.length) {
			placement = (addition + (index * index)) % numSlots;
		
			if (T[placement] == -1) {
				System.out.println( word + " not found to delete");
				return;
			}
			else{
				for (int i = T[placement]; i < T[placement] + word.length(); i++) {
					charac = word.charAt(j);
					
					if(charac == A[i]){
						A[i] = -1;
						if(A[i+1] == '\0') {
							System.out.println(word + " deleted from slot " + String.valueOf(placement));
							T[placement] = -1;
							numOfElements--;
							return;
						}
						
						j++;
						continue;
					}
				}
			}
			index++;
		}
		System.out.println(word + " not found to delete");
	}
	
	private static void createTable(int[] Table){
		for(int i = 0; i < Table.length; i++) {
			Table[i] = -1;
		}
	}
	
	private static int h(int sum, int slots, int start, int[] array, int[] table) {
		int index = 0;
		
		while(index < slots) {
			int placement = (sum + (index * index)) % slots;
		
			if (placement == array.length){
				System.out.println("OUT OF BOUNDS ERROR");
				return -1;
			}
		
			if(table[placement] == -1){
				return placement;
			} 
			else
			{
				index++;
				continue;
			}
		}
		return -1;
	}
}
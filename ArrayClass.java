public class ArrayClass {
	private int[] myArray;
	private int capacity;
	private int size;

	// Allocate array for size of s. Set capacity to s and size to 0.
	public ArrayClass(int s) {
		capacity = s;
		myArray = new int[capacity];
		size = 0;
	}

	// No-args constructor, default capacity to 10, size to 0.
	public ArrayClass() {
		this(10);
	}


	// Return size
	public int size() {
		return size;
	}

	// Return true if size is 0
	public boolean isEmpty() {
		return size == 0;
	}

	// Add num to index i in array. Move all current elements of array over to the right
	// starting with the element currently at index i. Update size. If there is no room in
	// the array, throw an ArrayIndexOutOfBoundsException.
	public void add(int i, int num) {
		if(size >= capacity) {
			throw new ArrayIndexOutOfBoundsException();
		}
		for(int index = size; index >= i; index--) {
			myArray[index + 1] = myArray[index];
		}
		myArray[i] = num;
		size++;
	}

	// If room in array, add num to end of array. If there is not room, allocate memory for
	// a new array that is twice the current array's capacity. Copy all current elements from
	// myArray into the new array.  Add num to the end of the array.  Reset myArray reference to
	// the new array. Return true.
	public boolean add(int num) {
		if(size >= capacity) {
			capacity *= 2;
			int[] updatedArray = new int[capacity];
			for(int i = 0; i < size; i++) {
				updatedArray[i] = myArray[i];
			}
			updatedArray[size++] = num;
			myArray = updatedArray;
			return true;
		}
		myArray[size++] = num;
		return true;
	}

	// Return the element at index i. If i is outside of the valid range, throw an ArrayIndexOutOfBoundsException.
	public int get(int i) {
		if(i < 0 || i > size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return myArray[i];
	}

	// Set the element at index i to num, return the current element at index i. If i is outside of the valid range, throw an ArrayIndexOutOfBoundsException.
	public int set(int i, int num) {
		if(i < 0 || i > size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		int prev = myArray[i];
		myArray[i] = num;
		return prev;
	}

	// Delete the element at index i, move all elements to the right of i, over one to the left.
	// Decrement size, return the element that was deleted. If i is outside of the valid range, throw an ArrayIndexOutOfBoundsException.
	public int remove(int i) {
		if(i < 0 || i > size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		int del = myArray[i];
		for(int index = i + 1; index < size; index++) {
			myArray[index - 1] = myArray[index];
		}
		myArray[--size] = 0;
		return del;
	}

	// Return true if num is in the array, otherwise return false.
	public boolean contains(int num) {
		for(int i = 0; i < size; i++) {
			if(myArray[i] == num) {
				return true;
			}
		}
		return false;
	}

	// Return the index of num if it is in the array. Otherwise, return -1.
	public int indexOf(int num) {
		for(int i = 0; i < size; i++) {
			if(myArray[i] == num) {
				return i;
			}
		}
		return -1;
	}

	// Return a String representation of the array.
	public String toString() {
		String output = "";
		for(int i = 0; i < size; i++) {
			output += myArray[i];
			if(i != size - 1) {
				output += ", ";
			}
		}
		return output + " // size: " + size + ", capacity: " + capacity;
	}

	public static void main(String[] args) {
		ArrayClass array = new ArrayClass(2);
		array.add(0, 2);
		System.out.println(array);
		array.add(3);
		System.out.println(array);
		array.add(8);
		System.out.println(array);
		array.add(5);
		System.out.println(array);
		System.out.println("contains(8) -> " + array.contains(8));
		System.out.println("indexOf(8) -> " + array.indexOf(8));
		array.remove(2);
		System.out.println(array);
		System.out.println("contains(8) -> " + array.contains(8));
		System.out.println("indexOf(8) -> " + array.indexOf(8));
	}
}
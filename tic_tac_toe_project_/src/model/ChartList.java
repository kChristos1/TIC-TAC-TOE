package model;

import java.io.Serializable;
import java.util.Arrays;

public class ChartList<T extends Comparable<T>>  implements Serializable{
	
	private static final long serialVersionUID =0L;
	private int size;
	private Object[] data;
	
	/********************************************/
	
	public ChartList(int capacity) {
		this.data= new Object[capacity];
		this.size = 0;
	}
	
	/********************************************/
	
	
	/*capacity() method returns the length of this.data[] array  */
	public int capacity() {
		return data.length;
	}

	

	private void checkAccessBounds(int i) {
		if (i < 0 || i > this.capacity())
			throw new ArrayIndexOutOfBoundsException(i + " is not a valid index");
	}
	
	
	
	/*
	 * adds an item in the data[] array and 
	 * using compareTo() its sorts the 
	 * items (or not) based on the comparable interface
	 * they implement. For example if data[] is a String array 
	 * all elements in it will be placed alphabetically 
	 */
	public void add(T item) {
		for (int i = 0; i < this.capacity(); i++) {
			T current = this.get(i);
			int newSize=0;
			if (current == null) {
				this.data[i] = item;
				size++;
				return;			
			} else {
				if (item.compareTo(current) < 0) { 
					for (int j=this.capacity()-1; j>i;j--) {
						data[j]=data[j-1];						
						if (data[j]!=null && newSize==0) { 
							newSize=j+1;
						}
					}
					data[i]=item;
					size= newSize;
					return;
				}
			}		
		}
	}	
	
	
	
	/*
	 *@SuppressWarnings("unchecked") annotation used to 
	 *effectively tell the compiler that 
	 * what we are doing doing really will be 
	 * legal at execution time.
	 */
	@SuppressWarnings("unchecked")
	public T get(int i) {
		checkAccessBounds(i);
		return (T) data[i];
	}
	
	
	
	/*
	 * adding one by one [using this.add()] 
	 * all elements of a new array 
	 * to the data[] array 
	 */
	public void addAll(T[] chart) {			
		for (int i = 0; i < chart.length; i++) {
			if (chart[i]!=null)
			this.add(chart[i]);
		}
	}

	

	public boolean isEmpty() {
		if(data !=null) {
			return false;
		}	
		else 
			return true;
	}

	
	
	public void clear() {
		this.data = new Object[data.length];
	}
	
	
	
	public int size() {
		return size;
	}

	
	
	/*
	 * same as add with the deference
	 * that addExtend()  increases 
	 * the size of the array
	 * when needed
	 */
	public void addExtend(T item) {
		if(this.capacity() == this.size) {
			this.growSize();
		}
		for (int i = 0; i < this.capacity(); i++) {
			T temp = this.get(i);
			int newSize=0;
			if (temp == null) {
				this.data[i] = item;
				size++;
				return;			
			} else {
				if(temp!=null) {
					if (item.compareTo(temp) < 0) { 
						for (int j=this.capacity()-1; j>i;j--) {
							data[j]=data[j-1];						
								if (data[j]!=null && newSize==0) { 
									newSize=j+1;
								}
						}
						data[i]=item;
						size= newSize;
						return;
					}
				}	
			}
		}
	}
	
	
	
	private void growSize() {
		Object temp[];
		temp = null;	
		temp = new Object[data.length + 1];
		for(int i=0; i<data.length;i++) {
			temp[i] = data[i];
		}
		data = temp;
	}

	
	
	/*
	 * method used to add only the best games of player
	 * invokes findBestGame() method (defined in gameRecord class)
	 */
	public void addBestGame(GameRecord current, Player player,int k) {
		if(this.capacity() == this.size) {
		this.growSize();
	}
		for (int i = 0; i < this.capacity(); i++) {
			GameRecord previous = (GameRecord) this.get(i);
			int newSize=0;
			if (previous == null) {
				this.data[i] = current;
				this.size++;
				return;			
			} 
			else {
				if (previous != null &&( previous.findBestGame(current, player,k)==-1)) {//if current is better the method returns -1 
					//if the current game is better than the previous we move all elements
					//a spot down so the current can fit in higher spot 
						for (int j=this.capacity()-1; j>i;  j--) {
							data[j]=data[j-1];						
							if(data[j]!=null && newSize==0) {
								newSize=j+1;
							}
						}
						//then after we've moved the elements , we add the current where it belongs
						data[i]=current;
						this.size= newSize;
						return;
					}	
				}
		}			
	}	
	


	public T remove(int i) {
		checkAccessBounds(i);
		T dead = this.get(i);
		if(dead == null) {
			return dead;
		}
		for (int j = i; j < this.size; j++) {
			if (j+1 < this.capacity()) {
				data[j]= data[j+1];
			}else {
				data[j]=null;
			}
			
		}
		size--;
		return dead;
	}
}
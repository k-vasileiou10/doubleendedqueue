package org.hua.grp38131.DeQue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleEndedQueueArray<E> implements DeQueue<E> {
	private static int DEFAULT_CAP=16;
	private static final int MAX_SIZE = Integer.MAX_VALUE-8;
	volatile int modCount;
	
	private Object[] elemArray;
	private int frontPointer;
	private int endPointer;
	
	public DoubleEndedQueueArray() {
		elemArray=new Object[DEFAULT_CAP];
		this.frontPointer=0;
		this.endPointer=0;
		modCount=0;
	}
	
	@Override
	public void pushFirst(E elem) {
		if(elem==null) {
			throw new IllegalArgumentException();
		}
		
		if(size()==elemArray.length) {
			doubleCapacity();
			decreaseFrontPointer();
		} else if(isEmpty()) {
			increaseEndPointer();
		} else {
			decreaseFrontPointer();
		}
		
		elemArray[frontPointer]=elem;
		modCount++;
	}
	

	@Override
	public void pushLast(E elem) {
		if(elem==null) {
			throw new IllegalArgumentException();
		}
		
		if(size()==elemArray.length) {
			doubleCapacity();
		}
		
		elemArray[endPointer]=elem;
		increaseEndPointer();
		modCount++;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public E popFirst() {
		if(isEmpty()) {
			throw new NoSuchElementException("This element doesn't exist.");
		}

		if(size()<elemArray.length/4) {
			quarterCapacity();
		}
		
		E tmp=(E) elemArray[frontPointer];
		elemArray[frontPointer]=null;
		increaseFrontPointer();
		
		modCount++;
		return tmp;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public E popLast() {
		if(isEmpty()) {
			throw new NoSuchElementException("This element doesn't exist.");
		}

		if(size()<=elemArray.length/4) {
			quarterCapacity();
		}
		
		decreaseEndPointer();
		E tmp=(E) elemArray[endPointer];
		elemArray[endPointer]=null;
		
		modCount++;
		return tmp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E first() {
		if(isEmpty()) {
			throw new NoSuchElementException("This element doesn't exist.");
		}
		
		return (E) elemArray[frontPointer];
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E last() {
		if(isEmpty()) {
			throw new NoSuchElementException("This element doesn't exist.");
		}
		
		int toReturn=endPointer;
		return (E) elemArray[--toReturn];
	}
	
	@Override
	public boolean isEmpty() {
		return (frontPointer==endPointer && elemArray[frontPointer]==null);
	}
	
	@Override
	public int size() {
		if(frontPointer<endPointer) {
			return endPointer-frontPointer;
		} else if(frontPointer>endPointer){
			return elemArray.length-(frontPointer-endPointer);
		} else if(frontPointer==endPointer) {
			if(isEmpty()) {
				return 0;
			} else {
				return elemArray.length;
			}
		}
		
		return 0;
	}
	
	@Override
	public void clear() {
		elemArray=new Object[DEFAULT_CAP];
		this.frontPointer=0;
		this.endPointer=0;
		modCount++;
	}
	
	private void doubleCapacity() {
		int oldCapacity=elemArray.length, oldFrontPointer=frontPointer;
		modCount++;

		if(elemArray.length*2>MAX_SIZE) {
			throw new IllegalStateException("The DeQueue cannot be expanded any further.");
		}
		
		Object[] newelemArray=new Object[oldCapacity*2];
		for(int i=endPointer-1; i>=0; i--) {
			newelemArray[i]=elemArray[i];
		}
		for(int i=frontPointer; i<oldCapacity; i++) {
			newelemArray[oldCapacity+i]=elemArray[i];
		}
		
		elemArray=newelemArray;
		frontPointer=oldCapacity+oldFrontPointer;
	}
	
	private void quarterCapacity() {
		int oldCapacity=elemArray.length;
		int j=0;

		modCount++;
		
		if(oldCapacity/4<DEFAULT_CAP) {
			oldCapacity=DEFAULT_CAP;
			return;
		}
		
		Object[] newelemArray=new Object[oldCapacity/4];
		if(endPointer>frontPointer) {
			for(int i=frontPointer; i<endPointer; i++) {
				newelemArray[j]=elemArray[i];
				j++;
			}

			endPointer=newelemArray.length-1;
			frontPointer=0;
		} else {
			for(int i=endPointer-1; i!=frontPointer-1; i--) {
				if(i<0) {
					i=elemArray.length-1;
				}
			
				j=i%newelemArray.length;

				newelemArray[j]=elemArray[i];	 
			}

			endPointer=endPointer%newelemArray.length;
			frontPointer=frontPointer%newelemArray.length;
		}
		
		elemArray=newelemArray;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new iteratorImpl();
	}
	
	@Override
	public Iterator<E> descendingIterator() {
		return new descendingIteratorImpl();
	}
	
	private class iteratorImpl implements Iterator<E> {
		private int cur;
		int itModCount;
		
		private iteratorImpl() {
			cur=frontPointer;
			itModCount=modCount;
		}
		
		@Override
		public boolean hasNext() {
			if(itModCount!=modCount) {
				throw new ConcurrentModificationException();
			}

			while(cur!=endPointer) {
				if(cur>elemArray.length-1) {
					cur=0;
				}
				if(elemArray[cur]!=null) {
					return true;
				}

				cur++;
			}
			
			return false;
		}

		@SuppressWarnings("unchecked")
		public E next() {
			if(cur>elemArray.length-1) {
				cur=0;
			}
			if(elemArray[cur]==null) {
				return null;
			}
			
			return (E) elemArray[cur++];
		}
	}
	
	private class descendingIteratorImpl implements Iterator<E> {
		private int cur;
		int ditModCount;
		
		private descendingIteratorImpl() {
			cur=endPointer;
			ditModCount=modCount;
		}

		@Override
		public boolean hasNext() {
			while(cur!=frontPointer-1) {
				if(ditModCount!=modCount) {
					throw new ConcurrentModificationException();
				}
				if(cur<0) {
					cur=elemArray.length-1;
				}
				if(elemArray[cur]!=null) {
					return true;
				}
				
				cur--;
			}
			
			return false;
		}
		
		@Override
		@SuppressWarnings("unchecked")
		public E next() {
			if(cur<0) {
				cur=elemArray.length-1;
			}
			if(elemArray[cur]==null) {
				return null;
			}
			
			return (E) elemArray[cur--];
		}
	}
	
	private void decreaseEndPointer() {
		if(--endPointer<0) {
			endPointer=elemArray.length-1;
		}
	}
	
	private void decreaseFrontPointer() {
		if(--frontPointer<0) {
			frontPointer=elemArray.length-1;
		}
	}
	
	private void increaseEndPointer() {
		if(++endPointer>elemArray.length-1) {
			endPointer=0;
		}
	}

	private void increaseFrontPointer() {
		if(++frontPointer>elemArray.length-1) {
			frontPointer=0;
		}
	}
}
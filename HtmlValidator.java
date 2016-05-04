/**Nisha Lad
 * This class examines HTML from a file or URL to figure out whether it represents a "valid" sequences of tags */

import java.util.*;

public class HtmlValidator {
	private Queue<HtmlTag> copyQueue;
	private int queueSize;
	
	/** Initialises a new empty queue of type HtmlTag by default */
	public HtmlValidator() {
		this(new LinkedList<HtmlTag>());
		queueSize = 0;
	}
	
	/**Pre: Takes in a Queue<HtmlTag> @param tags, which is not null to be validated
	 * throws IllegalArgumentException if tags is null
	 * Post: Initialises a new copy of the Queue passed in, so that it has the same
	 * state to the original Queue passed in */
	public HtmlValidator(Queue<HtmlTag> tags) {
		if (tags == null) {
			throw new IllegalArgumentException();
		}
		copyQueue = new LinkedList<HtmlTag>(tags);
		queueSize = copyQueue.size();
	}
	
	/** Pre: Takes in HtmlTag @param tag, which is not null 
	 *  throws IllegalArgumentException if tag is null
	 *  Post: Add the tag to the end of the queue, original queue is unchanged */
	public void addTag(HtmlTag tag) {
		if (tag == null) {
			throw new IllegalArgumentException();
		}
		copyQueue.add(tag);
		queueSize++;
	}
	
	/** @return a copy of the validator's queue of HTML tags
	 *  Post: original queue is unchanged */
	public Queue<HtmlTag> getTags() {
		return copyQueue;
	}
	
	/** Removes any HtmlTags that match the given String @param element
	 *  Pre: element is valid and is not null
	 *  if null is passed, IllegalArgumentExeption is thrown
	 *  Post: original queue is not modified, the order of the queue is preserved */
	public void removeAll(String element) {
		if (element == null) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < queueSize; i++) {
			HtmlTag queueTag = copyQueue.remove();
			if (!queueTag.getElement().equals(element)) {
				copyQueue.add(queueTag);
			}
		}
		queueSize = copyQueue.size();
	}
	
	/** Prints an indented text representation of the HTML tags in queue
	 *  Pre: assumes the order of tags is the order to print them out
	 *  Post: preserves order of queue
	 *  Prints Errors if a closing tag does not match most recent open tag
	 *  and if tags are still open at the end of HTML input */
	public void validate() {
		Stack<HtmlTag> tagStack = new Stack<HtmlTag>();
		int numIndent = 0;
		for (int i = 0; i < queueSize; i++) {
			HtmlTag current = copyQueue.peek();
			if(!current.isSelfClosing() && current.isOpenTag()) {
				printIndent(numIndent);
				numIndent++;
				tagStack.push(current);
			} else if (current.isSelfClosing() && current.isOpenTag()) {
				printIndent(numIndent);
			} else if (!tagStack.isEmpty() && tagStack.peek().matches(current)) {
			   	tagStack.pop();
			   	numIndent--;
			   	printIndent(numIndent);
			} else {
				 System.out.print("ERROR unexpected tag: ");
			}
			System.out.println(current);
			copyQueue.add(copyQueue.remove());
		}
		while (!tagStack.isEmpty()) {
			System.out.println("ERROR unclosed tag: " + tagStack.pop());
		}
	}
	
	/** Internal method: prints out the indentation for the given @param numIndent */
	private void printIndent(int numIndent) {
		for (int i = 0; i < numIndent; i++) {
			System.out.print("    ");
		}
	}

}

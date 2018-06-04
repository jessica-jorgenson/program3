package pro3;

import java.util.ArrayList;

/**
 *
 * @author jorge
 */
public class myHashTable {

	public ArrayList<HashNode> leArray;
	public int size = 0;
	public float percent = (float) 0.8;
	public static int max;
	public int modulus = 10;

	public myHashTable(int length) {
		leArray = new ArrayList<HashNode>(length);
		max = length;

		for (int i = 0; i < max; i++) {
			leArray.add(null);
		}
	}

	public HashNode add(ArrayList<HashNode> current, int data) {
		int ind = data % modulus;
		int origInd = ind;
		int count = 0;
		while (current.get(ind) != null) {
			count++;
			if (count > max) {
				return null;
			}
			ind = hashIt(origInd, count);
		}
		HashNode node = new HashNode(ind, data);
		current.set(ind, node);
		size++;
		return node;
	}

	public HashNode add(int data) {
		return add(leArray, data);
	}

	public int delete(int data) {
		HashNode del = findByValue(data);
		if (del == null) {
			return -1;
		} else {
			leArray.set(del.key, null);
			return del.key;
		}

	}

	public int hashIt(int origInd, int count) {
		return origInd + (count * count) % max;
	}

	public void checkRehash() {
		size++;
		float check = (float) size / max;
		if (check == percent) {
			size = 0;
			max *= 2;
			ArrayList<HashNode> tempTabl = new ArrayList<HashNode>(max);
			for (int i = 0; i < max; i++) {
				tempTabl.add(null);
			}
			for (HashNode node : leArray) {
				if (node != null) {
					int leData = node.value;
					add(tempTabl, leData);
				}
			}
			leArray = tempTabl;
		}
	}

	public HashNode findByValue(int val) {
		for (HashNode node : leArray) {
			if (node != null) {
				if (node.value == val) {
					return node;
				}
			}
		}
		return null;
	}
}

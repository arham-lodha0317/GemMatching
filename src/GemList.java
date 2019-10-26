public class GemList 
{
	private static class Node{
		private Gem gem;
		private Node next;

		public Node(){
			this.gem = null;
			this.next = null;
		}
		Node(Gem gem, Node next){
			this.gem = gem;
			this.next = next;
		}
		Node(Gem gem){
			this.gem = gem;
			this.next = null;
		}

		Gem getGem() {
			return gem;
		}
		Node getNext() {
			return next;
		}

		void setNext(Node next){
			this.next = next;
		}
		void setGem(Gem gem){
			this.gem = gem;
		}
	}
	private int size;
	private Node head;

	GemList(){
		size = 0;
		head = null;
	}

	void draw(double y){
		Node currentNode = head;

		for (int i = 0; i < size; i++) {
			currentNode.getGem().draw(GemGame.indexToX(i), y);
			currentNode = currentNode.getNext();
		}
	}

	public String toString(){
		StringBuilder str = new StringBuilder();

		if(!this.isEmpty()) {
			Node currentNode = head;
			for (int i = 0; i < size; i++) {
				str.append(currentNode.getGem().toString()).append("\n");
				currentNode = currentNode.getNext();
			}
		}
		else {
			System.out.println("Gemlist is empty");
		}

		return str.toString();
	}

	private boolean isEmpty(){
		return size == 0;
	}

	int size(){
		return size;
	}

	void insertBefore(Gem gem, int index){
		if(isEmpty()){
			head = new Node(gem);
		}
		else if (index <= 0){
			Node node = new Node(gem);
			if(head != null) node.setNext(head);
			head = node;
		}
		else {
			if(index > size){
				index = size-1;
			}
			Node currentNode = head;
			for (int i = 0; i < index - 1; i++) {
				currentNode = currentNode.getNext();
			}

			Node node = new Node(gem, currentNode.getNext());
			currentNode.setNext(node);
		}
		size++;
	}

	void removeAt(int index){

		if(head != null){
			Node temp = head;

			if(index == 0){
				head = temp.getNext();
				size--;
				return;
			}

			for (int i = 0; temp != null && i < index -1 ; i++) {
				temp = temp.getNext();
			}

			if(temp == null || temp.getNext() == null){
				return;
			}

			Node next = temp.getNext().getNext();

			temp.setNext(next);

			size--;
		}
	}

	int score(){
		int score = 0;

		Node node1 = head;
		int i = 0;
		while (i < size){

			int multiplier = 1;
			int block = node1.getGem().getPoints();

			Node node2 = node1;
			while (i + multiplier < size && node2.getGem().getType().equals(node2.getNext().getGem().getType())){
				multiplier++;
				node2 = node2.getNext();
				block += node2.getGem().getPoints();
			}

			block *= multiplier;

			score += block;

			i += multiplier;

			node1 = node2.next;
		}

		return score;
	}

	public static void main(String [] args) {
		GemList list = new GemList();
		list.insertBefore(new Gem(GemType.ORANGE, 10), 0);
		System.out.println(list);

		list.removeAt(0);
		System.out.println(list);

		list.insertBefore(new Gem(GemType.ORANGE, 10), 0);
		list.insertBefore(new Gem(GemType.ORANGE, 20), 1);
		System.out.println(list);

		list.removeAt(1);
		System.out.println(list);

		System.out.println(Integer.MIN_VALUE);
//		System.out.println(list);
//		System.out.println("size = " + list.size() + ", score = " + list.score());
//		list.draw(0.9);
//
//		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
//		System.out.println("\n" + list);
//		System.out.println("size = " + list.size() + ", score = " + list.score());
//		list.draw(0.8);
//
//		list.insertBefore(new Gem(GemType.BLUE, 20), 99);  //not a mistake, should still work
//		System.out.println("\n" + list);
//		System.out.println("size = " + list.size() + ", score = " + list.score());
//		list.draw(0.7);
//
//		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
//		System.out.println("\n" + list);
//		System.out.println("size = " + list.size() + ", score = " + list.score());
//		list.draw(0.6);
//
//		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
//		System.out.println("\n" + list);
//		System.out.println("size = " + list.size() + ", score = " + list.score());
//		list.draw(0.5);
//
//		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
//		System.out.println("\n" + list);
//		System.out.println("size = " + list.size() + ", score = " + list.score());
//		list.draw(0.4);
//
//		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
//		System.out.println("\n" + list);
//		System.out.println("size = " + list.size() + ", score = " + list.score());
//		list.draw(0.3);
//
//		list.removeAt(1);
//		System.out.println("\n" + list);
//		System.out.println("size = " + list.size() + ", score = " + list.score());
//		list.draw(0.1);
//
//		list.removeAt(0);
//		System.out.println("\n" + list);
//		System.out.println("size = " + list.size() + ", score = " + list.score());
//		list.draw(0.1);
//
//		list.removeAt(list.size() -1);
//		System.out.println("\n" + list);
//		System.out.println("size = " + list.size() + ", score = " + list.score());
//		list.draw(0.1);
	}
}

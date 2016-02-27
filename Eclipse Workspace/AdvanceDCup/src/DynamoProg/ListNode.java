package DynamoProg;

public class ListNode {
	int val;
	ListNode next;
	ListNode(int x){
		val = x;
		next = null;
	}

	public static void main(String args[]){
		ListNode ln1 = new ListNode(1);
		ListNode ln2 = new ListNode(2);
		ListNode ln3 = new ListNode(3);
		ListNode ln4 = new ListNode(4);
		ListNode ln5 = new ListNode(5);
		ListNode ln6 = new ListNode(6);

		ln1.next = ln2;
		ln2.next = ln3;
		ln3.next = ln4;
		ln4.next = ln5;
		ln5.next = ln6;

		ListNodeSolution lnSol = new ListNodeSolution();
		lnSol.reverseKGroup(ln1, 2);

	}
}

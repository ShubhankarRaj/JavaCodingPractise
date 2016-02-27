package DynamoProg;

import java.util.LinkedList;
import java.util.Queue;

public class ListNodeSolution {
	public ListNode reverseKGroup(ListNode head, int k) {
		int count=1;
		ListNode cursor = head;
		ListNode current = null;
		ListNode remainingHalf = null;
		Queue linkQueue = new LinkedList();

		if(head==null){
			return null;
		}

		if(k<=1){
			return head;
		}
		if(k>findSize(head)){
			return head;
		}
		if(k==2){
			return reverse(head);
		}

		while(cursor!=null || cursor.next!=null){

			while(count!=k){
				if(cursor!=null){
					cursor = cursor.next;
					
				}

				count++;
			}
			if(cursor!=null){
				remainingHalf = cursor.next;
				cursor.next=null;

				ListNode partial = reverse(head);
				linkQueue.add(partial);

				cursor = remainingHalf;
				head = remainingHalf;
			}
			else{
				linkQueue.add(remainingHalf);

				break;
			}
			count=1;
			if(cursor==null || cursor.next==null){
				linkQueue.add(remainingHalf);

				break;
			}

		}
		boolean headFlag=false;
		ListNode toRet=null;
		while(!linkQueue.isEmpty()){
			ListNode first = (ListNode)linkQueue.poll();
			ListNode end=null;
			if(first!=null){
				end =  findEnd(first);
				if(headFlag==false){
					toRet = first;
					headFlag=true;
				}
			}
			else{
				break;
			}
			ListNode second = (ListNode)linkQueue.poll();
			if(second==null){
				ListNode temp = findEnd(toRet);
				temp.next=first;
				break;
			}
			end.next=second;


			if(linkQueue.size()==1 || linkQueue.size()==0){
				ListNode last = findEnd(toRet);
				last.next = (ListNode)linkQueue.poll();

			}
		}
		System.out.println(linkQueue);
		return toRet;

	}

	public int findSize(ListNode head){
		int count=0;
		while(head!=null){
			count++; head=head.next;
		}
		System.out.println("Count of ELEMENTS in QUEUE is: "+count);
		return count;
	}

	public ListNode findEnd(ListNode head){
		while(head.next!=null){
			head = head.next;
		}
		return head;
	}


	public ListNode reverse(ListNode head){
		if(head==null){
			return null;
		}
		if(head.next==null){
			return head;
		}
		ListNode remaining = head.next;
		head.next = null;
		ListNode temp = reverse(remaining);
		remaining.next = head;
		System.out.println(head.val);
		return temp;
	}
}
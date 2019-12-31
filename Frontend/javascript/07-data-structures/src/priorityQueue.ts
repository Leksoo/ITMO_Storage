import { LinkedList } from './linkedList';

export class PriorityQueue<E> {
  private readonly linkedList1 = new LinkedList<E>();
  private readonly linkedList2 = new LinkedList<E>();
  private readonly linkedList3 = new LinkedList<E>();

  enqueue(el: E, priority: number): void {
    switch (priority) {
      case 1: {
        this.linkedList1.push(el);
        break;
      }
      case 2: {
        this.linkedList2.push(el);
        break;
      }
      case 3: {
        this.linkedList3.push(el);
        break;
      }
    }
  }

  dequeue(): E | undefined {
    if (this.linkedList3.size > 0) {
      return this.linkedList3.shift();
    }
    if (this.linkedList2.size > 0) {
      return this.linkedList2.shift();
    }
    if (this.linkedList1.size > 0) {
      return this.linkedList1.shift();
    }
  }

  get size(): number {
    return this.linkedList1.size + this.linkedList2.size + this.linkedList3.size;
  }
}

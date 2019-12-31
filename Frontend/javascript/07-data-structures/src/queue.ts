import { LinkedList } from './linkedList';

export class Queue<E> {
  private readonly linkedList = new LinkedList<E>();

  get(index: number): E | undefined {
    return this.linkedList.get(this.linkedList.size - 1 - index);
  }

  enqueue(el: E): void {
    this.linkedList.push(el);
  }

  dequeue(): E | undefined {
    return this.linkedList.shift();
  }

  get size(): number {
    return this.linkedList.size;
  }
}

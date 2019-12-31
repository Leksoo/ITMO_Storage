class Node<T> {
  private readonly _data: T;
  private _next: Node<T>;
  private _prev: Node<T>;

  constructor(data: T, prev: Node<T>, next: Node<T>) {
    this._data = data;
    this._prev = prev;
    this._next = next;
  }

  set prev(value: Node<T>) {
    this._prev = value;
  }

  get prev(): Node<T> {
    return this._prev;
  }

  get data(): T {
    return this._data;
  }

  get next(): Node<T> {
    return this._next;
  }

  set next(value: Node<T>) {
    this._next = value;
  }
}

export class RingBuffer<T> {
  private readonly _capacity: number;
  private _size = 0;
  private head: Node<T> = new Node<T>(null, null, null);
  private tail: Node<T> = new Node<T>(null, null, null);

  constructor(capacity: number) {
    this._capacity = capacity;
    this.head.next = this.tail;
    this.tail.prev = this.head;
  }

  static concat<E>(...buffers: RingBuffer<E>[]): RingBuffer<E> {
    const capacity = buffers.map(it => it.capacity).reduce((accum, cur) => accum + cur, 0);
    const newBuffer = new RingBuffer<E>(capacity);
    buffers.forEach(buffer => {
      let curNode = buffer.head.next;
      for (let i = 0; i < buffer.size; i++) {
        newBuffer.push(curNode.data);
        curNode = curNode.next;
      }
    });

    return newBuffer;
  }

  get(index: number): T | undefined {
    if (index >= this._size || index < 0) {
      return;
    }
    let curNode = this.head.next;
    for (let i = 0; i < index; i++) {
      curNode = curNode.next;
    }

    return curNode.data;
  }

  push(el: T): void {
    if (this._size === this._capacity) {
      if (!this.shift()) {
        return;
      }
    }
    const newEl = new Node(el, this.tail.prev, this.tail);
    newEl.prev.next = newEl;
    this.tail.prev = newEl;
    this._size++;
  }

  shift(): T | undefined {
    if (this._size === 0) {
      return;
    }
    const firstEl = this.head.next;
    firstEl.next.prev = this.head;
    this.head.next = firstEl.next;
    this._size--;

    return firstEl.data;
  }

  get capacity(): number {
    return this._capacity;
  }

  get size(): number {
    return this._size;
  }
}

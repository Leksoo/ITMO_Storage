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

export class LinkedList<T> {
  private _size = 0;
  private head: Node<T> = new Node<T>(null, null, null);
  private tail: Node<T> = new Node<T>(null, null, null);

  constructor() {
    this.head.next = this.tail;
    this.tail.prev = this.head;
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
    const newEl = new Node(el, this.tail.prev, this.tail);
    newEl.prev.next = newEl;
    this.tail.prev = newEl;
    this._size++;
  }

  pop(): T | undefined {
    if (this._size === 0) {
      return undefined;
    }
    const lastEl = this.tail.prev;
    lastEl.prev.next = this.tail;
    this.tail.prev = lastEl.prev;
    this._size--;

    return lastEl.data;
  }

  unshift(el: T): void {
    const newEl = new Node(el, this.head, this.head.next);
    newEl.next.prev = newEl;
    this.head.next = newEl;
    this._size++;
  }

  shift(): T | undefined {
    if (this._size === 0) {
      return undefined;
    }
    const firstEl = this.head.next;
    firstEl.next.prev = this.head;
    this.head.next = firstEl.next;
    this._size--;

    return firstEl.data;
  }

  get size(): number {
    return this._size;
  }
}

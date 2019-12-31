class Node<T> {
  readonly key: string | object;
  readonly val: T;

  constructor(key: string | object, val: T) {
    this.key = key;
    this.val = val;
  }
}

export class HashTable<E> {
  private readonly CAPACITY = 200;
  private _size = 0;
  private data = new Array<Array<Node<E>>>(this.CAPACITY);

  get(key: string | object): E {
    const bucketInd = this.getBucketInd(key);

    return this.data[bucketInd]?.find(node => node.key === key)?.val;
  }

  put(key: string | object, val: E): void {
    const bucketInd = this.getBucketInd(key);
    if (!this.data[bucketInd]) {
      this.data[bucketInd] = new Array<Node<E>>();
    }
    this.data[bucketInd].push(new Node<E>(key, val));
    this._size++;
  }

  clear(): void {
    this._size = 0;
    this.data = new Array<Array<Node<E>>>(this.CAPACITY);
  }

  get size(): number {
    return this._size;
  }

  private getBucketInd(key: string | object): number {
    const str = typeof key === 'string' ? key : JSON.stringify(key);
    let hash = 0;
    if (str.length === 0) {
      return hash;
    }
    for (let i = 0; i < str.length; i++) {
      hash = (hash << 5) - hash + str.charCodeAt(i);
      hash |= 0;
    }

    return hash % this.data.length;
  }
}

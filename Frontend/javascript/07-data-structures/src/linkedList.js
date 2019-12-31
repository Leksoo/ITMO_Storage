"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Node = /** @class */ (function () {
    function Node(data, prev, next) {
        this._data = data;
        this._prev = prev;
        this._next = next;
    }
    Object.defineProperty(Node.prototype, "prev", {
        get: function () {
            return this._prev;
        },
        set: function (value) {
            this._prev = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Node.prototype, "data", {
        get: function () {
            return this._data;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Node.prototype, "next", {
        get: function () {
            return this._next;
        },
        set: function (value) {
            this._next = value;
        },
        enumerable: true,
        configurable: true
    });
    return Node;
}());
var LinkedList = /** @class */ (function () {
    function LinkedList() {
        this._size = 0;
        this.head = new Node(null, null, null);
        this.tail = new Node(null, null, null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    LinkedList.prototype.get = function (index) {
        if (index >= this._size || index < 0) {
            return;
        }
        var curNode = this.head.next;
        for (var i = 0; i < index; i++) {
            curNode = curNode.next;
        }
        return curNode.data;
    };
    LinkedList.prototype.push = function (el) {
        var newEl = new Node(el, this.tail.prev, this.tail);
        newEl.prev.next = newEl;
        this.tail.prev = newEl;
        this._size++;
    };
    LinkedList.prototype.pop = function () {
        if (this._size === 0) {
            return undefined;
        }
        var lastEl = this.tail.prev;
        lastEl.prev.next = this.tail;
        this.tail.prev = lastEl.prev;
        this._size--;
        return lastEl.data;
    };
    LinkedList.prototype.unshift = function (el) {
        var newEl = new Node(el, this.head, this.head.next);
        newEl.next.prev = newEl;
        this.head.next = newEl;
        this._size++;
    };
    LinkedList.prototype.shift = function () {
        if (this._size === 0) {
            return undefined;
        }
        var firstEl = this.head.next;
        firstEl.next.prev = this.head;
        this.head.next = firstEl.next;
        this._size--;
        return firstEl.data;
    };
    Object.defineProperty(LinkedList.prototype, "size", {
        get: function () {
            return this._size;
        },
        enumerable: true,
        configurable: true
    });
    return LinkedList;
}());
exports.LinkedList = LinkedList;

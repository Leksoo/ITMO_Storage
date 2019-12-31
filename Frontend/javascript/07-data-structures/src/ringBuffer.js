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
var RingBuffer = /** @class */ (function () {
    function RingBuffer(capacity) {
        this._size = 0;
        this.head = new Node(null, null, null);
        this.tail = new Node(null, null, null);
        this._capacity = capacity;
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    RingBuffer.concat = function () {
        var buffers = [];
        for (var _i = 0; _i < arguments.length; _i++) {
            buffers[_i] = arguments[_i];
        }
        var capacity = buffers.map(function (it) { return it.capacity; }).reduce(function (accum, cur) { return accum + cur; }, 0);
        var newBuffer = new RingBuffer(capacity);
        buffers.forEach(function (buffer) {
            var curNode = buffer.head.next;
            for (var i = 0; i < buffer.size; i++) {
                newBuffer.push(curNode.data);
                curNode = curNode.next;
            }
        });
        return newBuffer;
    };
    RingBuffer.prototype.get = function (index) {
        if (index >= this._size || index < 0) {
            return;
        }
        var curNode = this.head.next;
        for (var i = 0; i < index; i++) {
            curNode = curNode.next;
        }
        return curNode.data;
    };
    RingBuffer.prototype.push = function (el) {
        if (this._size === this._capacity) {
            if (!this.shift()) {
                return;
            }
        }
        var newEl = new Node(el, this.tail.prev, this.tail);
        newEl.prev.next = newEl;
        this.tail.prev = newEl;
        this._size++;
    };
    RingBuffer.prototype.shift = function () {
        if (this._size === 0) {
            return;
        }
        var firstEl = this.head.next;
        firstEl.next.prev = this.head;
        this.head.next = firstEl.next;
        this._size--;
        return firstEl.data;
    };
    Object.defineProperty(RingBuffer.prototype, "capacity", {
        get: function () {
            return this._capacity;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(RingBuffer.prototype, "size", {
        get: function () {
            return this._size;
        },
        enumerable: true,
        configurable: true
    });
    return RingBuffer;
}());
exports.RingBuffer = RingBuffer;

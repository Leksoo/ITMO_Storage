"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var linkedList_1 = require("./linkedList");
var Queue = /** @class */ (function () {
    function Queue() {
        this.linkedList = new linkedList_1.LinkedList();
    }
    Queue.prototype.get = function (index) {
        return this.linkedList.get(this.linkedList.size - 1 - index);
    };
    Queue.prototype.enqueue = function (el) {
        this.linkedList.push(el);
    };
    Queue.prototype.dequeue = function () {
        return this.linkedList.shift();
    };
    Object.defineProperty(Queue.prototype, "size", {
        get: function () {
            return this.linkedList.size;
        },
        enumerable: true,
        configurable: true
    });
    return Queue;
}());
exports.Queue = Queue;

"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var linkedList_1 = require("./linkedList");
var PriorityQueue = /** @class */ (function () {
    function PriorityQueue() {
        this.linkedList1 = new linkedList_1.LinkedList();
        this.linkedList2 = new linkedList_1.LinkedList();
        this.linkedList3 = new linkedList_1.LinkedList();
    }
    PriorityQueue.prototype.enqueue = function (el, priority) {
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
    };
    PriorityQueue.prototype.dequeue = function () {
        if (this.linkedList3.size > 0) {
            return this.linkedList3.shift();
        }
        if (this.linkedList2.size > 0) {
            return this.linkedList2.shift();
        }
        if (this.linkedList1.size > 0) {
            return this.linkedList1.shift();
        }
    };
    Object.defineProperty(PriorityQueue.prototype, "size", {
        get: function () {
            return this.linkedList1.size + this.linkedList2.size + this.linkedList3.size;
        },
        enumerable: true,
        configurable: true
    });
    return PriorityQueue;
}());
exports.PriorityQueue = PriorityQueue;

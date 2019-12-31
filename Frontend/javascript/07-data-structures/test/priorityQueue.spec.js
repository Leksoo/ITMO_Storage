"use strict";
/* eslint-disable @typescript-eslint/no-explicit-any */
Object.defineProperty(exports, "__esModule", { value: true });
var src_1 = require("../src");
describe('PriorityQueue', function () {
    it('позволяет добавлять элементы', function () {
        var priorityQueue = new src_1.PriorityQueue();
        expect(priorityQueue.size).toBe(0);
        priorityQueue.enqueue(1, 3);
        priorityQueue.enqueue(2, 1);
        priorityQueue.enqueue(3, 2);
        expect(priorityQueue.size).toBe(3);
    });
    it('позволяет доставать элементы', function () {
        var priorityQueue = new src_1.PriorityQueue();
        priorityQueue.enqueue(1, 3);
        priorityQueue.enqueue(2, 1);
        priorityQueue.enqueue(3, 2);
        expect(priorityQueue.size).toBe(3);
        expect(priorityQueue.dequeue()).toBe(1);
        expect(priorityQueue.dequeue()).toBe(3);
        expect(priorityQueue.dequeue()).toBe(2);
        expect(priorityQueue.size).toBe(0);
    });
});

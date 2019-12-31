"use strict";
/* eslint-disable @typescript-eslint/no-explicit-any */
Object.defineProperty(exports, "__esModule", { value: true });
var src_1 = require("../src");
describe('Queue', function () {
    it('позволяет добавлять элементы', function () {
        var queue = new src_1.Queue();
        expect(queue.size).toBe(0);
        queue.enqueue(1);
        queue.enqueue(2);
        expect(queue.size).toBe(2);
    });
    it('позволяет получать элемент по индексу', function () {
        var queue = new src_1.Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        expect(queue.get(0)).toBe(2);
    });
    it('позволяет доставать элементы', function () {
        var queue = new src_1.Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        expect(queue.size).toBe(2);
        expect(queue.dequeue()).toBe(1);
        expect(queue.dequeue()).toBe(2);
        expect(queue.size).toBe(0);
    });
});

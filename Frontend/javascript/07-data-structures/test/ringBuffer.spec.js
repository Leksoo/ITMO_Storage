"use strict";
/* eslint-disable @typescript-eslint/no-explicit-any */
Object.defineProperty(exports, "__esModule", { value: true });
var src_1 = require("../src");
describe('RingBuffer', function () {
    it('позволяет добавлять элементы', function () {
        var ringBuffer = new src_1.RingBuffer(3);
        expect(ringBuffer.capacity).toBe(3);
        expect(ringBuffer.size).toBe(0);
        ringBuffer.push(1);
        ringBuffer.push(2);
        expect(ringBuffer.size).toBe(2);
    });
    it('позволяет получать элемент по индексу', function () {
        var ringBuffer = new src_1.RingBuffer(3);
        ringBuffer.push(1);
        ringBuffer.push(2);
        expect(ringBuffer.get(1)).toBe(2);
    });
    it('должен переполниться, если больше элементов, чем размер буфера', function () {
        var ringBuffer = new src_1.RingBuffer(3);
        ringBuffer.push(1);
        ringBuffer.push(2);
        ringBuffer.push(3);
        expect(ringBuffer.size).toBe(3);
        ringBuffer.push(4);
        expect(ringBuffer.size).toBe(3);
    });
    it('позволяет доставать элементы', function () {
        var ringBuffer = new src_1.RingBuffer(3);
        ringBuffer.push(1);
        ringBuffer.push(2);
        ringBuffer.push(3);
        ringBuffer.push(4);
        expect(ringBuffer.size).toBe(3);
        expect(ringBuffer.shift()).toBe(2);
        expect(ringBuffer.shift()).toBe(3);
        expect(ringBuffer.size).toBe(1);
    });
});

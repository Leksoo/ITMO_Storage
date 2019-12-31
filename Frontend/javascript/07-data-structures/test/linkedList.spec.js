"use strict";
/* eslint-disable @typescript-eslint/no-explicit-any */
Object.defineProperty(exports, "__esModule", { value: true });
var src_1 = require("../src");
describe('LinkedList', function () {
    it('позволяет добавлять элементы', function () {
        var linkedList = new src_1.LinkedList();
        expect(linkedList.size).toBe(0);
        linkedList.push(1);
        linkedList.push(2);
        expect(linkedList.size).toBe(2);
    });
    if (src_1.isExtraTaskSolved) {
        it('позволяет ходить по списку с помощью prev / next', function () {
            var linkedList = new src_1.LinkedList();
            linkedList.push(1);
            linkedList.push(2);
            expect(linkedList.next()).toBe(1);
            expect(linkedList.prev()).toBe(2);
            expect(linkedList.next()).toBe(1);
            expect(linkedList.prev()).toBe(2);
        });
    }
    it('позволяет доставать элементы', function () {
        var linkedList = new src_1.LinkedList();
        linkedList.push(1);
        linkedList.push(2);
        expect(linkedList.size).toBe(2);
        expect(linkedList.pop()).toBe(2);
        expect(linkedList.shift()).toBe(1);
        expect(linkedList.size).toBe(0);
    });
});

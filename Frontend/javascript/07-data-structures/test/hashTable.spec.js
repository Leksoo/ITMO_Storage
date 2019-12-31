"use strict";
/* eslint-disable @typescript-eslint/no-explicit-any */
Object.defineProperty(exports, "__esModule", { value: true });
var src_1 = require("../src");
describe('HashTable', function () {
    it('позволяет добавлять элементы', function () {
        var hashTable = new src_1.HashTable();
        var object = {};
        expect(hashTable.size).toBe(0);
        hashTable.put(1, 1);
        hashTable.put('1', 2);
        hashTable.put(object, 3);
        expect(hashTable.size).toBe(3);
    });
    it('позволяет получать элемент по ключу', function () {
        var hashTable = new src_1.HashTable();
        var object = {};
        hashTable.put(1, 1);
        hashTable.put('1', 2);
        hashTable.put(object, 3);
        expect(hashTable.get(1)).toBe(1);
        expect(hashTable.get('1')).toBe(2);
        expect(hashTable.get(object)).toBe(3);
    });
    it('можно очистить', function () {
        var hashTable = new src_1.HashTable();
        var object = {};
        hashTable.put(1, 1);
        hashTable.put('1', 2);
        hashTable.put(object, 3);
        expect(hashTable.size).toBe(3);
        hashTable.clear();
        expect(hashTable.size).toBe(0);
    });
});

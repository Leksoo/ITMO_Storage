"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Node = /** @class */ (function () {
    function Node(key, val) {
        this.key = key;
        this.val = val;
    }
    return Node;
}());
var HashTable = /** @class */ (function () {
    function HashTable() {
        this.CAPACITY = 200;
        this._size = 0;
        this.data = new Array(this.CAPACITY);
    }
    HashTable.prototype.get = function (key) {
        var _a, _b;
        var bucketInd = this.getBucketInd(key);
        return (_b = (_a = this.data[bucketInd]) === null || _a === void 0 ? void 0 : _a.find(function (node) { return node.key === key; })) === null || _b === void 0 ? void 0 : _b.val;
    };
    HashTable.prototype.put = function (key, val) {
        var bucketInd = this.getBucketInd(key);
        if (!this.data[bucketInd]) {
            this.data[bucketInd] = new Array();
        }
        this.data[bucketInd].push(new Node(key, val));
        this._size++;
    };
    HashTable.prototype.clear = function () {
        this._size = 0;
        this.data = new Array(this.CAPACITY);
    };
    Object.defineProperty(HashTable.prototype, "size", {
        get: function () {
            return this._size;
        },
        enumerable: true,
        configurable: true
    });
    HashTable.prototype.getBucketInd = function (key) {
        var str = typeof key === 'string' ? key : JSON.stringify(key);
        var hash = 0;
        if (str.length === 0) {
            return hash;
        }
        for (var i = 0; i < str.length; i++) {
            hash = (hash << 5) - hash + str.charCodeAt(i);
            hash |= 0;
        }
        return hash % this.data.length;
    };
    return HashTable;
}());
exports.HashTable = HashTable;

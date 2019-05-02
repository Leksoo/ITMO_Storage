"use strict";


function operation(newOp, str) {
    function Expr() {
        this.ops = arguments;
    }

    Expr.prototype.toString = function () {
        var result = "";
        for (var i = 0; i < this.ops.length; i++) {
            result += (this.ops[i].toString.apply(this.ops[i]));
            result += " ";
        }
        result += str;
        return result;
    };
    Expr.prototype.evaluate = function () {
        var args = arguments;
        var result = [];
        for (var i = 0; i < this.ops.length; i++) {
            result.push(this.ops[i].evaluate.apply(this.ops[i], args));
        }
        return newOp.apply(null, result);
    };
    return Expr;
}

function Const(x) {
    this.evaluate = function () {
        return x;
    };
    this.toString = function () {
        return x.toString();
    };
}

function Variable(x) {
    var varName = x;
    this.evaluate = function (value) {
        switch (varName) {
            case "x":
                return arguments[0];
            case "y":
                return arguments[1];
            case "z":
                return arguments[2];
        }
    };
    this.toString = function () {
        return varName;
    };
}

var Add = operation(
    function (left, right) {
        return left + right
    }
    , "+");
var Subtract = operation(
    function (left, right) {
        return left - right
    }
    , "-");
var Multiply = operation(
    function (left, right) {
        return left * right
    }
    , "*");
var Divide = operation(
    function (left, right) {
        return left / right
    }
    , "/");
var Negate = operation(
    function (left) {
        return -left;
    }
    , "negate");

var Square = operation(
    function (left) {
        return left * left;
    }
    , "square");

var Sqrt = operation(
    function (left) {
        return Math.sqrt(Math.abs(left));
    }
    , "sqrt");

var Power = operation(
    function (left, right) {
        return Math.pow(left, right);
    }
    , "pow");

var Log = operation(
    function (left, right) {
        return Math.log(Math.abs(right)) / Math.log(Math.abs(left));
    }
    , "log");
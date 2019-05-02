// var op = function (newOp) {
//     return function () {
//         var args = arguments;
//         return function () {
//             var result = [];
//             for (var i = 0; i < args.length; i++) {
//                 result.push(args[i].apply(null, arguments));
//             }
//             return newOp.apply(null, result);
//         }
//     }
// };

var op = function (newOp) {
    return function () {
        var args = arguments;
        return function () {
            var result = [];
            var args2 = arguments;
            // for (var i in args) {
            //     result.push(args[i].apply(null, args2));
            // }
            args.forEach(function (value) {
                result.push(args[value].apply(null, args2));
            });
            return newOp.apply(null, result);
        }
    }
};


// Unary
var cnst = function (x) {
    return function () {
        return x;
    }
};


var variable = function (name) {
    return function () {
        switch (name) {
            case "x":
                return arguments[0];
            case "y":
                return arguments[1];
            case "z":
                return arguments[2];
        }
    }

};

var negate = op(function (left) {
    return -left;
});

var cube = op(function (left) {
    return left * left * left;
});

var cuberoot = op(function (left) {
    return Math.pow(left, 1 / 3);
});

// Binary ops
var multiply = op(
    function (left, right) {
        return left * right;
    });

var add = op(
    function (left, right) {
        return left + right;
    });

var subtract = op(
    function (left, right) {
        return left - right;
    });

var divide = op(
    function (left, right) {
        return left / right;
    });
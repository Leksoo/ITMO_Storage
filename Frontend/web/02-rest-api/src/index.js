"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const tslib_1 = require("tslib");
const app_1 = tslib_1.__importDefault(require("./app"));
const port = 3000;
app_1.default.listen(port, () => {
    console.log(`Example app listening on port ${port}!`);
});

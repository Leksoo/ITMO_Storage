"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const sequelize_1 = require("sequelize");
const sequelize = new sequelize_1.Sequelize('itmo', 'admin', 'admin', {
    dialect: 'postgres',
    host: 'postgres'
});
const placeholder = { sequelize };
exports.default = placeholder;

"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const tslib_1 = require("tslib");
const sequelize_1 = require("sequelize");
const index_1 = tslib_1.__importDefault(require("../index"));
class Location extends sequelize_1.Model {
}
Location.init({
    id: {
        type: sequelize_1.DataTypes.INTEGER,
        allowNull: false,
        primaryKey: true,
        autoIncrement: true
    },
    name: {
        type: sequelize_1.DataTypes.STRING,
        allowNull: false
    },
    description: {
        type: sequelize_1.DataTypes.STRING,
        allowNull: false
    },
    country: {
        type: sequelize_1.DataTypes.STRING,
        allowNull: true
    },
    city: {
        type: sequelize_1.DataTypes.STRING,
        allowNull: true
    },
    visited: {
        type: sequelize_1.DataTypes.BOOLEAN,
        defaultValue: false,
        allowNull: false
    }
}, {
    sequelize: index_1.default.sequelize,
    tableName: 'Location'
});
exports.default = Location;

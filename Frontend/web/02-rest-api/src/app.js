"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const tslib_1 = require("tslib");
const express_1 = tslib_1.__importDefault(require("express"));
const body_parser_1 = tslib_1.__importDefault(require("body-parser"));
const LocationController_1 = tslib_1.__importDefault(require("./controller/LocationController"));
const app = express_1.default();
app.use(body_parser_1.default.json());
app.use(body_parser_1.default.urlencoded({ extended: true }));
app
    .route('/locations')
    .get(LocationController_1.default.getAll)
    .post(LocationController_1.default.create)
    .delete(LocationController_1.default.deleteAll);
app.route('/locations/pages').get(LocationController_1.default.getByPages);
app
    .route('/locations/:id')
    .get(LocationController_1.default.get)
    .post(LocationController_1.default.setDescription)
    .patch(LocationController_1.default.setVisited)
    .delete(LocationController_1.default.deleteOne);
app.route('/locations/search/:description').get(LocationController_1.default.searchByDescription);
app.all('*', (req, res) => {
    res.sendStatus(404);
});
exports.default = app;

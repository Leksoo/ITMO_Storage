"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const tslib_1 = require("tslib");
const Location_1 = tslib_1.__importDefault(require("../db/model/Location"));
const getByOrder = (order) => {
    if (!order) {
        return Location_1.default.findAll();
    }
    else if (order === 'name') {
        return Location_1.default.findAll({ order: ['name'] });
    }
    else if (order === 'time') {
        return Location_1.default.findAll({ order: ['createdAt'] });
    }
    return null;
};
const getAll = (req, res) => {
    const order = req.query.order;
    const dbRequest = getByOrder(order);
    if (dbRequest === null) {
        res.status(400);
        res.send('unknown query value');
        return;
    }
    dbRequest
        .then(locations => {
        res.status(200);
        res.send(locations);
    })
        .catch(error => {
        res.status(500);
        res.send(error);
    });
};
const deleteAll = (req, res) => {
    Location_1.default.destroy({ where: {} })
        .then(() => {
        res.sendStatus(204);
    })
        .catch(error => {
        res.status(500);
        res.send(error);
    });
};
const create = (req, res) => {
    const body = req.body;
    const data = {
        name: body.name,
        description: body.description,
        country: body.country,
        city: body.city
    };
    Location_1.default.create(data)
        .then(() => {
        res.sendStatus(204);
    })
        .catch(error => {
        res.status(500);
        res.send(error);
    });
};
const get = (req, res) => {
    const id = +req.params.id;
    if (!id) {
        res.sendStatus(400);
        return;
    }
    Location_1.default.findByPk(id)
        .then(location => {
        if (location) {
            res.send(location);
        }
        else {
            res.sendStatus(404);
        }
    })
        .catch(error => {
        res.status(500);
        res.send(error);
    });
};
const update = (field, value, id, res) => {
    Location_1.default.update({ [field]: value }, { where: { id } })
        .then(() => {
        res.sendStatus(204);
    })
        .catch(error => {
        res.status(500);
        res.send(error);
    });
};
const setDescription = (req, res) => {
    const id = req.params.id;
    const description = req.body.description;
    if (!description) {
        res.sendStatus(400);
        return;
    }
    update('description', description, id, res);
};
const setVisited = (req, res) => {
    const id = req.params.id;
    const visited = req.query.visited;
    if (!visited || (visited !== 'true' && visited !== 'false')) {
        res.sendStatus(400);
        return;
    }
    update('visited', visited, id, res);
};
const deleteOne = (req, res) => {
    const id = req.params.id;
    Location_1.default.destroy({ where: { id } })
        .then(() => {
        res.sendStatus(204);
    })
        .catch(error => {
        res.status(500);
        res.send(error);
    });
};
const getByPages = (req, res) => {
    const sizePerPage = +req.query.size;
    if (!sizePerPage) {
        res.sendStatus(400);
        return;
    }
    Location_1.default.findAll()
        .then(locations => {
        const pages = [];
        for (let i = 0; i < locations.length; i += sizePerPage) {
            pages.push(locations.slice(i, i + sizePerPage));
        }
        res.send(pages);
    })
        .catch(error => {
        res.status(500);
        res.send(error);
    });
};
const searchByDescription = (req, res) => {
    const description = req.params.description;
    Location_1.default.findAll({ where: { description: description } })
        .then(locations => {
        res.send(locations);
    })
        .catch(error => {
        res.status(500);
        res.send(error);
    });
};
const placeholder = {
    getAll,
    create,
    deleteAll,
    get,
    setDescription,
    setVisited,
    deleteOne,
    getByPages,
    searchByDescription
};
exports.default = placeholder;

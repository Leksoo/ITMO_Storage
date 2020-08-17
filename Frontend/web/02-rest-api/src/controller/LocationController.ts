import { Request, Response } from 'express';

import Location from '../db/model/Location';

const getByOrder = (order: string): Promise<Location[]> | null => {
  if (!order) {
    return Location.findAll();
  } else if (order === 'name') {
    return Location.findAll({ order: ['name'] });
  } else if (order === 'time') {
    return Location.findAll({ order: ['createdAt'] });
  }

  return null;
};

const getAll = (req: Request, res: Response): void => {
  const order: string = req.query.order;
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

const deleteAll = (req: Request, res: Response): void => {
  Location.destroy({ where: {} })
    .then(() => {
      res.sendStatus(204);
    })
    .catch(error => {
      res.status(500);
      res.send(error);
    });
};

const create = (req: Request, res: Response): void => {
  const body = req.body;
  const data = {
    name: body.name,
    description: body.description,
    country: body.country,
    city: body.city
  };

  Location.create(data)
    .then(() => {
      res.sendStatus(204);
    })
    .catch(error => {
      res.status(500);
      res.send(error);
    });
};

const get = (req: Request, res: Response): void => {
  const id = +req.params.id;

  if (!id) {
    res.sendStatus(400);

    return;
  }

  Location.findByPk(id)
    .then(location => {
      if (location) {
        res.send(location);
      } else {
        res.sendStatus(404);
      }
    })
    .catch(error => {
      res.status(500);
      res.send(error);
    });
};

const update = (field: string, value: string, id: string, res: Response): void => {
  Location.update({ [field]: value }, { where: { id } })
    .then(() => {
      res.sendStatus(204);
    })
    .catch(error => {
      res.status(500);
      res.send(error);
    });
};

const setDescription = (req: Request, res: Response): void => {
  const id = req.params.id;
  const description = req.body.description;

  if (!description) {
    res.sendStatus(400);

    return;
  }

  update('description', description, id, res);
};

const setVisited = (req: Request, res: Response): void => {
  const id = req.params.id;
  const visited = req.query.visited;

  if (!visited || (visited !== 'true' && visited !== 'false')) {
    res.sendStatus(400);

    return;
  }

  update('visited', visited, id, res);
};

const deleteOne = (req: Request, res: Response): void => {
  const id = req.params.id;

  Location.destroy({ where: { id } })
    .then(() => {
      res.sendStatus(204);
    })
    .catch(error => {
      res.status(500);
      res.send(error);
    });
};

const getByPages = (req: Request, res: Response): void => {
  const sizePerPage = +req.query.size;

  if (!sizePerPage) {
    res.sendStatus(400);

    return;
  }

  Location.findAll()
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

const searchByDescription = (req: Request, res: Response): void => {
  const description = req.params.description;

  Location.findAll({ where: { description: description } })
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
export default placeholder;

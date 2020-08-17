import express, { Request, Response } from 'express';
import bodyParser from 'body-parser';

import controller from './controller/LocationController';

const app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app
  .route('/locations')
  .get(controller.getAll)
  .post(controller.create)
  .delete(controller.deleteAll);

app.route('/locations/pages').get(controller.getByPages);

app
  .route('/locations/:id')
  .get(controller.get)
  .post(controller.setDescription)
  .patch(controller.setVisited)
  .delete(controller.deleteOne);

app.route('/locations/search/:description').get(controller.searchByDescription);

app.all('*', (req: Request, res: Response) => {
  res.sendStatus(404);
});

export default app;

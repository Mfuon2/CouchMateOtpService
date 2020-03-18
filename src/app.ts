// @ts-ignore
import * as express from 'express';
// @ts-ignore
import * as dotenv from 'dotenv';
// @ts-ignore
import * as logger from '../src/middlewares/logger';
// @ts-ignore
import * as routes from '../src/routes/user';

const app = express();
const PORT = 5000;
dotenv.config();
app.use(logger);
app.get('/', (request, response) => {
    response.send('Hello World');
  });
app.listen(PORT, () => {
    console.log(`Listening on ${PORT}`);
})


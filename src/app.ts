// @ts-ignore
import * as express from 'express';
// @ts-ignore
import * as dotenv from 'dotenv';
// @ts-ignore
import * as logger from '../src/middlewares/logger';
// @ts-ignore
import * as routes from '../src/routes/user';

const app = express();
dotenv.config();
app.use(logger);
app.use(routes);

app.listen(5000, () => {
    //TODO: Add env with the port number
    console.log(`Listening on ${5000}`);
})


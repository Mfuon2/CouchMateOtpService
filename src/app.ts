// @ts-ignore
import * as express from 'express';
import * as dotenv from 'dotenv';

const app = express();
dotenv.config();


app.listen(5000, () => {
    //TODO: Add env with the port number
    console.log(`Listening on ${5000}`);
})


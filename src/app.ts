// @ts-ignore
import * as express from 'express'

const app = express();

app.listen(5000, () => {
    //TODO: Add env with the port number
    console.log(`Listening on ${5000}`);
})
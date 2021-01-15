import express from 'express';
import * as dotenv from 'dotenv';
// @ts-ignore
import consoleLogger from './middlewares/console';
import * as log from './middlewares/logger';
// @ts-ignore
import user from '../src/routes/user';
// @ts-ignore
import config from './configs/server';

const app = express();

app.use(consoleLogger);
app.use('/user',user);

const port = config.server_port;

if(port !== undefined){
    app.listen(port, () => {
        log.msg(`Listening on ${port}`)
    })
} else {
    log.err(' Server failed Starting ', 'Server Port Error is : ' + port)
}



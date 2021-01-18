import express from 'express';
import * as configs from './configs'
import {msg, err} from "./api/middlewares";

const app = express();
const port  = configs.envs.PORT;
const startServer = async () => {
    await require('./loaders/').default({ expressApp: app});
    app.listen(port,() => {
        msg(`
      ##########################################
      🔥  Server listening on port: ${port} 🔥
      ##########################################
    `)
    }).on('error',(error)=>{
        err('Server Could not start',`Error : => ${error}`)
    })
}

startServer().then(r => {
    msg('Server Started')
});

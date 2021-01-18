import mongoose from 'mongoose'
import * as configs from '../configs'
import {msg} from '../api/middlewares'
import { Db } from 'mongodb';

//Set up default mongoose connection
let mongoDB = configs.envs.DB_URI === undefined ? '80' : configs.envs.DB_URI;

const dbConnection = async () : Promise<Db> => {
    const conn = await mongoose.connect(mongoDB, {useNewUrlParser: true, useUnifiedTopology: true});

    const db = mongoose.connection;
    db.once('open', function() {
        msg('🔥 Successfully connected to MongoDB! 🔥');
    });

    db.once('error', function() {
        msg('🔥 Failed to Connect to Database 🔥');
    });

    return conn.connection.db;
}

export {
    dbConnection
}

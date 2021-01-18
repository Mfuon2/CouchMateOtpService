
import {dbConnection} from './mongoose'
import expressLoader from './express';
import {msg} from "../api/middlewares";

// @ts-ignore
export default async ({ expressApp }): Promise<void> => {
    const mongoConnection = await dbConnection();
    msg('🔥 ===> Database loaded');
    const express = expressLoader({ app: expressApp});
    msg('🔥 ===> Express loaded');
}

import dotenv from 'dotenv'

dotenv.config()
export const envs =  {
    BASE_URL: process.env.API_URL,
    API_KEY: process.env.API_KEY,
    PORT: process.env.PORT,

    //API
    API_PREFIX: process.env.API_PREFIX,

    //Database
    DB_URI: process.env.MONGODB_URI,

    //logger
    level: process.env.LOG_LEVEL || 'silly',
};

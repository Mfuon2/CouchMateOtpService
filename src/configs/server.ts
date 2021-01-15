import dotenv from 'dotenv'

dotenv.config()
export = {
    base_ul: process.env.API_URL,
    API_KEY: process.env.API_KEY,
    server_port: process.env.PORT
};

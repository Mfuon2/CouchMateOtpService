import {Application} from 'express'
import cors from 'cors'
import bodyParser from 'body-parser'


export default ({app}:{app: Application}) : void => {
    app.get('/status',(req, res) => {
        res.status(200).json({'status':"up"});
    })

    app.head('/status',(req, res) => {
        res.status(200).end();
    })

    // Useful if you're behind a reverse proxy (Heroku, Bluemix, AWS ELB, Nginx, etc)
    // It shows the real origin IP in the heroku or Cloudwatch logs
    app.enable('trust proxy');

    // The magic package that prevents frontend developers going nuts
    // Alternate description:
    // Enable Cross Origin Resource Sharing to all origins by default
    app.use(cors());

    // Middleware that transforms the raw string of req.body into json
    app.use(bodyParser.json());

}


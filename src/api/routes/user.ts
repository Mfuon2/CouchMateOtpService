import {Router, Request, Response, NextFunction} from 'express';
import {err, msg} from "../middlewares";

const user = Router();

export default (app: Router): void => {
    app.use('/', user);

    user.get('/me',
        async (req: Request, res: Response, next: NextFunction) => {
            msg('[me] Calling My Profile Details endpoint');
            try {
                return res.status(200).send({
                    success: true,
                    message: 'Profile details fetched successfully',
                    data: {'user':'user'},
                });
            } catch (e) {
                err('🔥 error: %o', e.stack);
                return next(e);
            }
        },
    );
}

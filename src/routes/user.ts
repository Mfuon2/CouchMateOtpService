

const user = (app:any) => {
    app.get("/", function(req:any,res:any){
        res.body('Response on get');
        res.status('OK').json(res.body)
    })

    app.get("/user", function(req:any,res:any){
        res.body('/user');
    })
}

module.exports = user;

module.exports = function(app){
    app.get("/", function(req,res){
        res.body('Response on get');
    })
    app.get("/user", function(req,res){
        res.body('/user');
    })
}
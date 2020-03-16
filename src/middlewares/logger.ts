
module.exports = {
    error: function(req, res, next){
        console.log('Error : ' + req );
        next();
    }
}
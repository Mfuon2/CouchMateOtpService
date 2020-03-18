
// module.exports = {
//     error: function(req, res, next){
//         console.log('Error : ' + req );
//     }
// }

const logger = (req, res, next) => {
    console.log(`Time: ${new Date()} - Method: ${req.method} - Path: ${req.originalUrl}`);
    next();
  };
  
  module.exports = logger;
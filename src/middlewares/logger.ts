import chalk from 'chalk';

const err = (msg: string, stack: string) => {
    console.log(chalk.redBright(' ERR: ' + msg + ' STACK: ' + stack))
}

const msg = (msg: string) => {
    console.log(chalk.green(' SUC: ' + msg))
}

const wrn = (msg: string) => {
    console.log(chalk.strikethrough.yellow(' WRN: ' + msg))
}

export {
    err,
    msg,
    wrn
}

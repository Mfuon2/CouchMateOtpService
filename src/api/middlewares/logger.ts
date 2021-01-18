import chalk from 'chalk';

export const err = (msg: string, stack: string) => {
    console.log(chalk.redBright(' ERR: ' + msg + ' STACK: ' + stack))
}

export const msg = (msg: string) => {
    console.log(chalk.green(' SUC: ' + msg))
}

export const wrn = (msg: string) => {
    console.log(chalk.strikethrough.yellow(' WRN: ' + msg))
}



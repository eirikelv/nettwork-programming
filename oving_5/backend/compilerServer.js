const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const cors = require('cors');
const fWriter = require('fs/promises');
let {response} = require("express");


app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:true}));
app.use(cors({origin: "http://localhost:5173"}));



const server = app.listen(1234, () => {
    console.log(`Listening to port ${server.address().port}`);
});

app.post('/compiler', async (request, response) => {
    const { code } = request.body;

    try {
        await codeWriter(code)
        await execute("docker build \"./cpp\" -t compiler");
        const output = await execute("docker run --rm compiler");
        await execute("docker rmi compiler");
        response.status(200).json({ compiledCode: output })
    } catch (error){
        console.log(error)
        response.status(500).json({ error: error })
    }
});

async function codeWriter(code) {
    try {
        await fWriter.writeFile('cpp/main.cpp', code);
    } catch (error){
        response = error.toString()
    }
}
//runs command
async function execute(command) {
    return new Promise((res,reject) => {
        const { exec } = require("child_process");
        exec(command, (error, stdout, stderr) => {
            if(error){
                console.error(error)
                reject(stderr)
            } else {
                res(stdout);
            }
        })
    })
}

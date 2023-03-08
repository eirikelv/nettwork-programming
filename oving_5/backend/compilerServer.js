const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const cors = require('cors');
const fWriter = require('fs/promises');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:true}));
app.use(cors({origin: "http://localhost:5173"}));

const server = app.listen(1234, () => {
    console.log(`Listening to port ${server.address().port}`);
});

app.post('/compiler', async (request, response) => {
    const { code } = request.body;

    try {
        console.log(request.body)
    } catch (error){
        console.log(error)
    }
});

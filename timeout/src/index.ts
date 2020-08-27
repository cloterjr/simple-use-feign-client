import express from 'express';

const app = express();

const server = app.listen(3000);

const appFakeLatencyMillis = 4000 //8 minutes
app.use(function(req,res,next){setTimeout(next,appFakeLatencyMillis)});

app.get('/users/:id', (req, res)=>{
    const {id} = req.params;

    const users = [
          { "id": 1, "name": "user 1" },
          { "id": 2, "name": "user 2" },
          { "id": 3, "name": "user 3" }
        ];

    const user = users.find(user=>user.id===Number(id));

    return res.json(user);
})

// increase the timeout to 4 minutes
server.timeout = appFakeLatencyMillis + 10000 // 8 minutes + 10 seconds;
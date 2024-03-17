const express = require('express');
const { spawn } = require('child_process');
const path = require('path')
const app = express();
const port = 3000;
app.get('/api/frame/:id', async (req,res) => {
    const {id} = req.params
    const pythonProcess = spawn('python', ['./page/work.py',id]);
    pythonProcess.stderr.on('data', (data) => {console.error(data.toString());});
    pythonProcess.stdout.on('data', (data) => {
        try {
            res.send(data.toString())
        } catch (e) {}
    });
    if (id == 0) {
        const sound = spawn('python', ['./page/sound.py']);
        sound.stderr.on('data', (data) => {console.error(data.toString());});
        sound.stdout.on('data', (data) => {});
    }
});
app.get('/sound', (req,res) => {
    res.sendFile(path.join(__dirname,'./source/video.wav'))
});
app.get('/script.js', (req,res) => {
    res.sendFile(path.join(__dirname,'./page/script.js'))
});
app.get('/bad_apple', (req,res) => {
    res.sendFile(path.join(__dirname,'./page/page.html'))
});
app.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});